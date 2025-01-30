package com.pruebaTecnica.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class StarshipSearchResponse {
    @JsonProperty("result")
    private List<Starship> starships;

    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Starship {
        private String uid;
        private Properties properties;
    }

    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Properties {
        private String name;
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
        private String MGLT;
        @JsonProperty("cargo_capacity")
        private String cargoCapacity;
        private String consumables;
        private List<String> pilots;
    }

    @JsonProperty("total_records")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer totalRecords;

    @JsonProperty("total_pages")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer totalPages;
}
