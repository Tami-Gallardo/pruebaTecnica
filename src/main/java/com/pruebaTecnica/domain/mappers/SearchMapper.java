package com.pruebaTecnica.domain.mappers;

import com.pruebaTecnica.domain.entities.PeopleSearchResponse;
import com.pruebaTecnica.domain.entities.StarshipSearchResponse;
import com.pruebaTecnica.domain.entities.VehicleSearchResponse;
import com.pruebaTecnica.domain.entities.PeopleResponse;
import com.pruebaTecnica.domain.entities.StarshipsResponse;
import com.pruebaTecnica.domain.entities.VehiclesResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SearchMapper {
    public PeopleResponse mapFilteredResponse(PeopleSearchResponse filteredResponse) {
        PeopleResponse peopleResponse = new PeopleResponse();

        if (filteredResponse.getPeople() != null) {
            List<PeopleResponse.People> peopleList = filteredResponse.getPeople().stream()
                    .map(result -> {
                        PeopleResponse.People person = new PeopleResponse.People();
                        person.setUid(result.getUid());
                        person.setName(result.getProperties().getName());
                        person.setEyeColor(result.getProperties().getEyeColor());
                        person.setBirthYear(result.getProperties().getBirthYear());
                        person.setGender(result.getProperties().getGender());
                        person.setHeight(result.getProperties().getHeight());
                        person.setMass(result.getProperties().getMass());
                        person.setHairColor(result.getProperties().getHairColor());
                        person.setSkinColor(result.getProperties().getSkinColor());
                        person.setHomeworld(result.getProperties().getHomeworld());
                        return person;
                    })
                    .collect(Collectors.toList());

            peopleResponse.setPeople(peopleList);
        }
        return peopleResponse;
    }

    public StarshipsResponse mapFilteredResponse(StarshipSearchResponse filteredResponse) {
        StarshipsResponse starshipsResponse = new StarshipsResponse();

        if (filteredResponse.getStarships() != null) {
            List<StarshipsResponse.Starship> starshipList = filteredResponse.getStarships().stream()
                    .map(result -> {
                        StarshipsResponse.Starship starship = new StarshipsResponse.Starship();
                        starship.setUid(result.getUid());
                        starship.setName(result.getProperties().getName());
                        starship.setModel(result.getProperties().getModel());
                        starship.setStarshipClass(result.getProperties().getStarshipClass());
                        starship.setManufacturer(result.getProperties().getManufacturer());
                        starship.setCostInCredits(result.getProperties().getCostInCredits());
                        starship.setLength(result.getProperties().getLength());
                        starship.setCrew(result.getProperties().getCrew());
                        starship.setPassengers(result.getProperties().getPassengers());
                        starship.setMaxAtmospheringSpeed(result.getProperties().getMaxAtmospheringSpeed());
                        starship.setHyperdriveRating(result.getProperties().getHyperdriveRating());
                        starship.setMGLT(result.getProperties().getMGLT());
                        starship.setCargoCapacity(result.getProperties().getCargoCapacity());
                        starship.setConsumables(result.getProperties().getConsumables());
                        starship.setPilots(result.getProperties().getPilots());
                        return starship;
                    })
                    .collect(Collectors.toList());

            starshipsResponse.setResults(starshipList);
        }
        return starshipsResponse;
    }

    public VehiclesResponse mapFilteredResponse(VehicleSearchResponse filteredResponse) {
        VehiclesResponse vehiclesResponse = new VehiclesResponse();

        if (filteredResponse.getVehicles() != null) {
            List<VehiclesResponse.Vehicle> vehicleList = filteredResponse.getVehicles().stream()
                    .map(result -> {
                        VehiclesResponse.Vehicle vehicle = new VehiclesResponse.Vehicle();
                        vehicle.setUid(result.getUid());
                        vehicle.setName(result.getProperties().getName());
                        vehicle.setModel(result.getProperties().getModel());
                        vehicle.setVehicleClass(result.getProperties().getVehicleClass());
                        vehicle.setManufacturer(result.getProperties().getManufacturer());
                        vehicle.setCostInCredits(result.getProperties().getCostInCredits());
                        vehicle.setLength(result.getProperties().getLength());
                        vehicle.setCrew(result.getProperties().getCrew());
                        vehicle.setPassengers(result.getProperties().getPassengers());
                        vehicle.setMaxAtmospheringSpeed(result.getProperties().getMaxAtmospheringSpeed());
                        vehicle.setCargoCapacity(result.getProperties().getCargoCapacity());
                        vehicle.setConsumables(result.getProperties().getConsumables());
                        vehicle.setPilots(result.getProperties().getPilots());
                        return vehicle;
                    })
                    .collect(Collectors.toList());

            vehiclesResponse.setResults(vehicleList);
        }
        return vehiclesResponse;
    }
}
