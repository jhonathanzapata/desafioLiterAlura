package com.example.desafioLiterAlura.service;

import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsumoAPI {


    public String obtenerDatos(String url){
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();
        //System.out.println("La uri se envió");
        HttpResponse<String> response = null;
        try {
            response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());

            /*
            System.out.println("El host obtenido fue: "+response.uri().getHost());
            System.out.println("El header obtenido fue:" +response.headers().allValues("location"));
            System.out.println("Los headers son; "+response.headers());

            System.out.println("Se recibio la respuesta");
            System.out.println("La respuesta en texto fue: "+response.toString());
            System.out.println("La respuesta fue: "+response.statusCode());

             */
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        /*
        JSONObject jsonConvertido = new JSONObject(json);
        String holder = jsonConvertido.getJSONArray("results").getJSONObject(0).getString("title");
        System.out.println("El json con el path fue: "+holder);

         */
        return response.body();
    }
}
