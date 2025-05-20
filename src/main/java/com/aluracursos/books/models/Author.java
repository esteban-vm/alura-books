package com.aluracursos.books.models;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Author(
        String name,
        @JsonAlias("birth_year") String birthYear
) {
}
