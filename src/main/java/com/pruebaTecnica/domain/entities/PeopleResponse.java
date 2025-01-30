package com.pruebaTecnica.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PeopleResponse {
    @JsonProperty("results")
    private List<People> people;
    public void setResults(List<People> people) {
    }
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class People {

        private String uid;
        private String name;
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

        public People(String number, String lukeSkywalker) {
            this.uid = uid;
            this.name = name;
        }
    }
    @JsonProperty("total_records")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer totalRecords;
    @JsonProperty("total_pages")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer totalPages;
}
