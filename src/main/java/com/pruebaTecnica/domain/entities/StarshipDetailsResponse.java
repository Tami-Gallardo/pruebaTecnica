package com.pruebaTecnica.domain.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class StarshipDetailsResponse {
    private Result result;
    @Getter
    @Setter
    public static class Result {
        private Properties properties;
    }
    @Getter
    @Setter
    public static class Properties {
        private String model;
        @JsonProperty("starship_class")
        private String starshipClass;
        private String manufacturer;
        @JsonProperty("cost_in_credits")
        private String costInCredits;
        private String length;
        private String crew;
        private String passengers;
        @JsonProperty("max_atmosphering_speed")
        private String maxAtmospheringSpeed;
        @JsonProperty("hyperdrive_rating")
        private String hyperdriveRating;
        @JsonProperty("MGLT")
        private String MGLT;
        @JsonProperty("cargo_capacity")
        private String cargoCapacity;
        private String consumables;
        private List<String> pilots;
    }
}
