package com.example.desafioLiterAlura.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConvierteDatos implements IConvierteDatos {
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public <T> T obtenerDatos(String json, Class<T> clase) {
        try {
            return objectMapper.readValue(json,clase);

        } catch (JsonProcessingException e) {
            System.out.println("Hubo un problema al convertir el json");
            throw new RuntimeException(e);
        }
    }
}
