package com.example.desafioLiterAlura.model;

import com.fasterxml.jackson.annotation.*;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosLibro (
        String title,
        Autores authors,
        String languages,
        int download_count) {


}
