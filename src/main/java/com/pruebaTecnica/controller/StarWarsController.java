package com.pruebaTecnica.controller;

import com.pruebaTecnica.domain.entities.FilmsResponse;
import com.pruebaTecnica.domain.entities.PeopleResponse;
import com.pruebaTecnica.domain.entities.StarshipsResponse;
import com.pruebaTecnica.domain.entities.VehiclesResponse;
import com.pruebaTecnica.service.StarWarsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "API", description = "API que se integra con la API de Star Wars y lista People, Films, Starships y Vehicles")
@RestController
@RequestMapping("/StarWars")
public class StarWarsController {
    @Autowired
    private StarWarsService starWarsService;

    @Operation(summary = "Obtiene personajes", description = "Devuelve una lista paginada de 'personajes' con filtros opcionales.")
    @GetMapping("/people")
    public ResponseEntity<PeopleResponse> getAllPeople(@RequestParam(name = "pageNumber", defaultValue = "1") Integer pageNumber,
                                                       @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                                       @RequestParam(name = "id", required = false) List<Integer> id,
                                                       @RequestParam(name = "name", required = false) String name){
        return ResponseEntity.ok().body(starWarsService.getAllPeople(pageNumber, pageSize, id, name));
    }

    @Operation(summary = "Obtiene películas", description = "Devuelve una lista paginada de 'películas' con filtros opcionales.")
    @GetMapping("/films")
    public ResponseEntity<Page<FilmsResponse.Film>> getAllFilms(@RequestParam(name = "pageNumber", defaultValue = "0") Integer pageNumber,
                                                                @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                                                @RequestParam(name = "id", required = false) List<Integer> id,
                                                                @RequestParam(name = "name", required = false) String name){
        return ResponseEntity.ok().body(starWarsService.getAllFilms(PageRequest.of(pageNumber, pageSize), id, name));
    }

    @Operation(summary = "Obtiene naves espaciales", description = "Devuelve una lista paginada de 'naves' con filtros opcionales.")
    @GetMapping("/starships")
    public ResponseEntity<StarshipsResponse> getAllStarships(@RequestParam(name = "pageNumber", defaultValue = "1") Integer pageNumber,
                                                                            @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                                                            @RequestParam(name = "id", required = false) List<Integer> id,
                                                                            @RequestParam(name = "name", required = false) String name){
        return ResponseEntity.ok().body(starWarsService.getAllStarships(pageNumber, pageSize, id, name));
    }

    @Operation(summary = "Obtiene vehículos", description = "Devuelve una lista paginada de 'vehículos' con filtros opcionales.")
    @GetMapping("/vehicles")
    public ResponseEntity<VehiclesResponse> getAllVehicles(@RequestParam(name = "pageNumber", defaultValue = "1") Integer pageNumber,
                                                           @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                                           @RequestParam(name = "id", required = false) List<Integer> id,
                                                           @RequestParam(name = "name", required = false) String name){
        return ResponseEntity.ok().body(starWarsService.getAllVehicles(pageNumber, pageSize, id, name));
    }

}
