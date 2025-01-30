package com.pruebaTecnica.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.pruebaTecnica.domain.entities.PeopleResponse;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class PeopleSearchResponse {
    @JsonProperty("result")
    private List<People> people;
    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class People {
        private String uid;
        private Properties properties;
    }
    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Properties {
        private String name;
        @JsonProperty("eye_color")
        private String eyeColor;
        @JsonProperty("birth_year")
        private String birthYear;
        private String gender;
        private String height;
        private String mass;
        @JsonProperty("hair_color")
        private String hairColor;
        @JsonProperty("skin_color")
        private String skinColor;
        private String homeworld;
    }
    @JsonProperty("total_records")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer totalRecords;
    @JsonProperty("total_pages")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer totalPages;
}
