package com.example.desafioLiterAlura.model.constantes;

import com.example.desafioLiterAlura.model.DatosLibro;
import com.example.desafioLiterAlura.model.Libro;

public class textoLibroEncontrado {

    public static void mostrarTextoLibroEcontrado(DatosLibro libro){
        var texto =
                "----- LIBRO ----" + '\n' +
                        "Titulo: " + libro.title() + '\n' +
                        "Autor: " + libro.authors().getName() + '\n' +
                        "Idioma: " + libro.languages() + '\n' +
                        "Número de descargas: " + libro.download_count()
                    ;
        System.out.println(texto);
    }

    public static void mostrarTextoLibrosAlmacenados(Libro libro){
        var texto =
                "" + '\n'+"----- LIBRO ----   "+ '\n'
                +
                "Titulo: " + libro.getTitle() + '\n' +
                "Autor: " + libro.getName_author() + '\n' +
                "Idioma: " + libro.getLanguages() + '\n' +
                "Número de descargas: " + libro.getDownload_count()+
                '\n'
                +'\n'
                ;
        System.out.println(texto);
    }

    public static void mostrarTextoAutores(Libro libro){
        var texto =
                "" + '\n'+"Autor: "+libro.getName_author()+'\n'+
                        "Fecha de nacimiento: " + libro.getBirth_year() + '\n' +
                        "Fecha de fallecimiento: " + libro.getDeath_year() + '\n' +
                        "Libros: " + libro.getTitle()
                        +'\n'
                ;
        System.out.println(texto);
    }
}
