package com.example.desafioLiterAlura.service;

import com.example.desafioLiterAlura.model.Autores;
import org.json.JSONObject;

import java.util.List;

public class ConvertirStringAJson {

    public String ConvertirDesdeArrayPrincipalTexto(String json, String propiedad){
        JSONObject jsonConvertido = new JSONObject(json);
        return jsonConvertido.getJSONArray("results").getJSONObject(0).getString(propiedad);
    }

    public int ConvertirDesdeArrayPrincipalInt(String json, String propiedad){
        JSONObject jsonConvertido = new JSONObject(json);
        return jsonConvertido.getJSONArray("results").getJSONObject(0).getInt(propiedad);
    }

    public String ConvertirDesdeTexto(String json, String propiedad){
        JSONObject jsonConvertido = new JSONObject(json);
        return jsonConvertido.getString(propiedad);
    }

    public int ConvertirDesdeNumero(String json, String propiedad){
        JSONObject jsonConvertido = new JSONObject(json);
        return jsonConvertido.getInt(propiedad);
    }

    public String ConvertirDesdeVariasArrays(String json, String array, String propiedad){
        JSONObject jsonConvertido = new JSONObject(json);
        return jsonConvertido.getJSONArray("results").getJSONObject(0).getJSONArray(array).getJSONObject(0).getString(propiedad);
    }

    public Autores ConvertirDesdeVariasArraysAutores(String json){
        JSONObject jsonConvertido = new JSONObject(json);
        String nombre= jsonConvertido.getJSONArray("results").getJSONObject(0).getJSONArray("authors").getJSONObject(0).getString("name");
        int fecha_inicial=jsonConvertido.getJSONArray("results").getJSONObject(0).getJSONArray("authors").getJSONObject(0).getInt("birth_year");
        int fecha_final=jsonConvertido.getJSONArray("results").getJSONObject(0).getJSONArray("authors").getJSONObject(0).getInt("death_year");
        return new Autores(nombre,fecha_inicial,fecha_final);
    }

    public String ConvertirDesdeVariasArraysTexto(String json, String array){
        JSONObject jsonConvertido = new JSONObject(json);
        return jsonConvertido.getJSONArray("results").getJSONObject(0).getJSONArray(array).get(0).toString();
    }
}
