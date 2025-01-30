package com.pruebaTecnica.domain.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class FilmsResponse {
    @JsonProperty("result")
    private List<Film> results;

    @Getter
    @Setter
    public static class Film {
        String uid;
        String description;
        Properties properties;
        @Getter
        @Setter
        public static class Properties {
            private String title;
            private String episode_id;
            private String opening_crawl;
            private String director;
            private String producer;
            private String created;
            private String edited;
        }
    }
}
