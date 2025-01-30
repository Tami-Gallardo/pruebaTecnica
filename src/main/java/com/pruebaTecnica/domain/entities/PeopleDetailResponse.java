package com.pruebaTecnica.domain.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PeopleDetailResponse {
    private Result result;
    @Getter
    @Setter
    public static class Result {
        private Properties properties;
    }
    @Getter
    @Setter
    public static class Properties {
        private String height;
        private String mass;
        @JsonProperty("hair_color")
        private String hairColor;
        @JsonProperty("skin_color")
        private String skinColor;
        @JsonProperty("eye_color")
        private String eyeColor;
        @JsonProperty("birth_year")
        private String birthYear;
        private String gender;
        private String homeworld;
    }
}
