package com.pruebaTecnica.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class VehicleDetailsResponse {
    private Result result;
    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Result {
        private Properties properties;
    }
    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Properties {
        private String model;
        @JsonProperty("vehicle_class")
        private String vehicleClass;
        private String manufacturer;
        @JsonProperty("cost_in_credits")
        private String costInCredits;
        private String length;
        private String crew;
        private String passengers;
        @JsonProperty("max_atmosphering_speed")
        private String maxAtmospheringSpeed;
        @JsonProperty("cargo_capacity")
        private String cargoCapacity;
        private String consumables;
        private List<String> pilots;
    }
}
