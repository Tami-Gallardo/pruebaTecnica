package com.pruebaTecnica.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pruebaTecnica.domain.entities.*;
import com.pruebaTecnica.domain.mappers.DetailsMapper;
import com.pruebaTecnica.domain.mappers.SearchMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StarWarsService {
    /**
     * Se obtienen las distintas listas de personajes, vehículos, naves y peliculas con filtros opcionales como name y/o id
     * param pageNumber Numero de la pagina a recuperar.
     * param pageSize Cantidad de objetos por pagina.
     * param ids Lista de IDs a buscar.
     * param name Nombre parcial o completo del objeto.
     */
    @Autowired
    private WebClient webClient;
    @Autowired
    private DetailsMapper detailsMapper;
    @Autowired
    private SearchMapper searchMapper;

    /** Lista de personajes */
    public PeopleResponse getAllPeople(Integer pageNumber, Integer pageSize, List<Integer> ids, String name) {
        PeopleResponse response = webClient.get()
                .uri(uriBuilder -> {
                    uriBuilder.path("/people");
                    if (ids != null && !ids.isEmpty()) {
                        uriBuilder.queryParam("page", 1);
                        uriBuilder.queryParam("limit", 100);
                    } else {
                        uriBuilder.queryParam("page", pageNumber);
                        uriBuilder.queryParam("limit", pageSize);
                    }
                    if (name != null && !name.isEmpty()) {
                        uriBuilder.queryParam("name", name);
                    }
                    return uriBuilder.build();
                })
                .retrieve()
                .bodyToMono(String.class)
                .map(this::mapToPeopleResponse)
                .block();

        if (response == null) {
            return new PeopleResponse();
        }
        //Manually filtered by ids
        if (ids != null && !ids.isEmpty()) {
            response.setPeople(response.getPeople().stream()
                    .filter(p -> p.getUid() != null && ids.contains(Integer.valueOf(p.getUid())))
                    .map(p -> {
                        PeopleDetailResponse detailResponse = getPeopleDetails(p.getUid());
                        return detailsMapper.mapToPeople(p, detailResponse);
                    })
                    .collect(Collectors.toList()));
            response.setTotalRecords(null);
            response.setTotalPages(null);
        }
        return response;
    }

    /** Map JSON response to PeopleResponse:
     * Esto se hace porque al utilizar las respuestas de la api tanto para 'listar todo' y para la 'busqueda' por name,
     * como dichas respuestas tienen un formato distinto primero verifico si viene result o results y luego creo PeopleResponse*/
    private PeopleResponse mapToPeopleResponse(String s) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(s);
            if (rootNode.has("results")) {
                return objectMapper.readValue(s, PeopleResponse.class);
            }
            else if (rootNode.has("result")) {
                PeopleSearchResponse filteredResponse = objectMapper.readValue(s, PeopleSearchResponse.class);
                return searchMapper.mapFilteredResponse(filteredResponse);
            }
            else if (rootNode.has("message")) {
                return new PeopleResponse();
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error procesando la respuesta JSON", e);
        }
        return new PeopleResponse();
    }
    private PeopleDetailResponse getPeopleDetails(String uid) {
        return webClient.get()
                .uri("https://www.swapi.tech/api/people/" + uid)
                .retrieve()
                .bodyToMono(PeopleDetailResponse.class)
                .block();
    }

    /** Lista de peliculas */
    public Page<FilmsResponse.Film> getAllFilms(Pageable pageable, List<Integer> ids, String name) {
        FilmsResponse response = webClient.get()
                .uri("/films")
                .retrieve()
                .bodyToMono(FilmsResponse.class)
                .block();

        if (name != null && !name.isEmpty()) {
            assert response != null;
            List<FilmsResponse.Film> filteredFilms = response.getResults().stream()
                    .filter(f -> f.getProperties().getTitle().toLowerCase().contains(name.toLowerCase()))
                    .collect(Collectors.toList());
            response.setResults(filteredFilms);
        }

        if (ids != null && !ids.isEmpty()) {
            assert response != null;
            List<FilmsResponse.Film> filteredFilms = response.getResults().stream()
                    .filter(f -> f.getUid() != null && ids.contains(Integer.valueOf(f.getUid())))
                    .collect(Collectors.toList());
            response.setResults(filteredFilms);
        }

        assert response != null;
        int start = (int) pageable.getOffset();
        int end = Math.min(start + pageable.getPageSize(), response.getResults().size());
        if (start >= response.getResults().size()) {
            return Page.empty(pageable);
        }
        return new PageImpl<>(response.getResults().subList(start, end), pageable, response.getResults().size());
    }

    /** Lista de naves */
    public StarshipsResponse getAllStarships(Integer pageNumber, Integer pageSize, List<Integer> ids, String name) {
        StarshipsResponse response = webClient.get()
                .uri(uriBuilder -> {
                    uriBuilder.path("/starships");
                    if (ids != null && !ids.isEmpty()) {
                        uriBuilder.queryParam("page", 1);
                        uriBuilder.queryParam("limit", 100);
                    } else {
                        uriBuilder.queryParam("page", pageNumber);
                        uriBuilder.queryParam("limit", pageSize);
                    }
                    if (name != null && !name.isEmpty()) {
                        uriBuilder.queryParam("name", name);
                    }
                    return uriBuilder.build();
                })
                .retrieve()
                .bodyToMono(String.class)
                .map(this::mapToStarshipResponse)
                .block();

        if (response == null) {
            return new StarshipsResponse();
        }
        //Manually filtered by ids
        List<StarshipsResponse.Starship> starships = response.getResults();
        if (ids != null && !ids.isEmpty()) {
            starships = starships.stream()
                    .filter(s -> s.getUid() != null && ids.contains(Integer.valueOf(s.getUid())))
                    .map(s -> {
                        StarshipDetailsResponse starshipDetailsResponse = getStarshipDetails(s.getUid());
                        return detailsMapper.mapToStarship(s, starshipDetailsResponse);
                    })
                    .collect(Collectors.toList());
            response.setResults(starships);
            response.setTotalPages(null);
            response.setTotalRecords(null);
        }
        return response;
    }

    /** Map JSON response to StarshipsResponse */
    private StarshipsResponse mapToStarshipResponse(String s) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(s);
            if (rootNode.has("results")) {
                return objectMapper.readValue(s, StarshipsResponse.class);
            } else if (rootNode.has("result")) {
                StarshipSearchResponse filteredResponse = objectMapper.readValue(s, StarshipSearchResponse.class);
                return searchMapper.mapFilteredResponse(filteredResponse);
            } else if (rootNode.has("message")) {
                return new StarshipsResponse();
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error procesando la respuesta JSON", e);
        }
        return new StarshipsResponse();
    }

    private StarshipDetailsResponse getStarshipDetails(String uid) {
        return webClient.get()
                .uri("https://www.swapi.tech/api/starships/" + uid)
                .retrieve()
                .bodyToMono(StarshipDetailsResponse.class)
                .block();
    }

    /** Lista de vehiculos */
    public VehiclesResponse getAllVehicles(Integer pageNumber, Integer pageSize, List<Integer> ids, String name) {
        VehiclesResponse response = webClient.get()
                .uri(uriBuilder -> {
                    uriBuilder.path("/vehicles");
                    if (ids != null && !ids.isEmpty()) {
                        uriBuilder.queryParam("page", 1);
                        uriBuilder.queryParam("limit", 100);
                    } else {
                        uriBuilder.queryParam("page", pageNumber);
                        uriBuilder.queryParam("limit", pageSize);
                    }
                    if (name != null && !name.isEmpty()) {
                        uriBuilder.queryParam("name", name);
                    }
                    return uriBuilder.build();
                })
                .retrieve()
                .bodyToMono(String.class)
                .map(this::mapToVehiclesResponse)
                .block();

        if (response == null) {
            return new VehiclesResponse();
        }

        //Manually filtered by ids
        List<VehiclesResponse.Vehicle> vehicles = response.getResults();
        if (ids != null && !ids.isEmpty()) {
            vehicles = vehicles.stream()
                    .filter(v -> v.getUid() != null && ids.contains(Integer.valueOf(v.getUid())))
                    .map(v -> {
                        VehicleDetailsResponse vehicleDetailsResponse = getVehicleDetails(v.getUid());
                        return detailsMapper.mapToVehicle(v, vehicleDetailsResponse);
                    })
                    .collect(Collectors.toList());
            response.setResults(vehicles);
            response.setTotalRecords(null);
            response.setTotalPages(null);
        }
        return response;
    }

    /** Map JSON response to VehiclesResponse */
    private VehiclesResponse mapToVehiclesResponse(String s) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(s);
            if (rootNode.has("results")) {
                return objectMapper.readValue(s, VehiclesResponse.class);
            } else if (rootNode.has("result")) {
                VehicleSearchResponse filteredResponse = objectMapper.readValue(s, VehicleSearchResponse.class);
                return searchMapper.mapFilteredResponse(filteredResponse);
            } else if (rootNode.has("message")) {
                return new VehiclesResponse();
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error procesando la respuesta JSON", e);
        }
        return new VehiclesResponse();
    }

    private VehicleDetailsResponse getVehicleDetails(String uid) {
        return webClient.get()
                .uri("https://www.swapi.tech/api/vehicles/" + uid)
                .retrieve()
                .bodyToMono(VehicleDetailsResponse.class)
                .block();
    }

}
