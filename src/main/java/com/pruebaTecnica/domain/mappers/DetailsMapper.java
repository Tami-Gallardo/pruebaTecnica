package com.pruebaTecnica.domain.mappers;

import com.pruebaTecnica.domain.entities.PeopleDetailResponse;
import com.pruebaTecnica.domain.entities.StarshipDetailsResponse;
import com.pruebaTecnica.domain.entities.VehicleDetailsResponse;
import com.pruebaTecnica.domain.entities.PeopleResponse;
import com.pruebaTecnica.domain.entities.StarshipsResponse;
import com.pruebaTecnica.domain.entities.VehiclesResponse;
import org.springframework.stereotype.Component;

@Component
public class DetailsMapper {
    public PeopleResponse.People mapToPeople(PeopleResponse.People people, PeopleDetailResponse detailResponse) {
        if (detailResponse == null || detailResponse.getResult() == null) {
            return people;
        }
        PeopleDetailResponse.Properties properties = detailResponse.getResult().getProperties();
        if (properties != null) {
            people.setEyeColor(properties.getEyeColor());
            people.setHeight(properties.getHeight());
            people.setMass(properties.getMass());
            people.setHairColor(properties.getHairColor());
            people.setSkinColor(properties.getSkinColor());
            people.setBirthYear(properties.getBirthYear());
            people.setGender(properties.getGender());
            people.setHomeworld(properties.getHomeworld());
        }

        return people;
    }

    public StarshipsResponse.Starship mapToStarship(StarshipsResponse.Starship s, StarshipDetailsResponse detailResponse) {
        StarshipDetailsResponse.Properties properties = detailResponse.getResult().getProperties();
        if(properties != null){
            s.setModel(properties.getModel());
            s.setStarshipClass(properties.getStarshipClass());
            s.setManufacturer(properties.getManufacturer());
            s.setCostInCredits(properties.getCostInCredits());
            s.setLength(properties.getLength());
            s.setCrew(properties.getCrew());
            s.setPassengers(properties.getPassengers());
            s.setMaxAtmospheringSpeed(properties.getMaxAtmospheringSpeed());
            s.setHyperdriveRating(properties.getHyperdriveRating());
            s.setMGLT(properties.getMGLT());
            s.setCargoCapacity(properties.getCargoCapacity());
            s.setConsumables(properties.getConsumables());
            s.setPilots(properties.getPilots());
        }
        return s;
    }

    public VehiclesResponse.Vehicle mapToVehicle(VehiclesResponse.Vehicle v, VehicleDetailsResponse detailResponse) {
        VehicleDetailsResponse.Properties properties = detailResponse.getResult().getProperties();
        if (properties != null) {
            v.setModel(properties.getModel());
            v.setVehicleClass(properties.getVehicleClass());
            v.setManufacturer(properties.getManufacturer());
            v.setCostInCredits(properties.getCostInCredits());
            v.setLength(properties.getLength());
            v.setCrew(properties.getCrew());
            v.setPassengers(properties.getPassengers());
            v.setMaxAtmospheringSpeed(properties.getMaxAtmospheringSpeed());
            v.setCargoCapacity(properties.getCargoCapacity());
            v.setConsumables(properties.getConsumables());
            v.setPilots(properties.getPilots());
        }
        return v;
    }
}
