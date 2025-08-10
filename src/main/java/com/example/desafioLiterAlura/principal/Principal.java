package com.example.desafioLiterAlura.principal;

import com.example.desafioLiterAlura.model.Autores;
import com.example.desafioLiterAlura.model.DatosLibro;
import com.example.desafioLiterAlura.model.Libro;
import com.example.desafioLiterAlura.model.constantes.textoLibroEncontrado;
import com.example.desafioLiterAlura.repository.SerieRepository;
import com.example.desafioLiterAlura.service.ConsumoAPI;
import com.example.desafioLiterAlura.service.ConvertirStringAJson;
import com.example.desafioLiterAlura.service.ConvierteDatos;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.*;

import static com.example.desafioLiterAlura.model.constantes.textoLibroEncontrado.mostrarTextoLibroEcontrado;

public class Principal {
    private Scanner teclado = new Scanner(System.in);
    private ConsumoAPI consumoApi = new ConsumoAPI();
    private final String URL_BASE = "https://gutendex.com/books/";
    private SerieRepository repositorio;
    private ConvierteDatos conversor = new ConvierteDatos();
    private ConvertirStringAJson convertirStringAJson= new ConvertirStringAJson();
    private List<Libro> libros;

    public Principal(SerieRepository repositorio) {
        this.repositorio = repositorio;
    }

    public void muestraElMenu() {

        var opcion = -1;
        while (opcion != 0) {
            var menu = """
                    Elija la opción a través de su número
                    1 - Buscar libro por titulo 
                    2 - Listar libros registrados
                    3 - Listar autores registrados
                    4 - Listar autores vivos en un determinado año
                    5 - Listar libros por idioma                                  
                    0 - Salir
                    """;
            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
                case 1:
                    buscarLibro();
                    break;
                    
                case 2:
                    listarLibrosRegistrados();
                    break;

                case 3:
                    listarAutoresRegistrados();
                    break;

                case 4:
                    listarAutoresVivosEnUnDeterminadoAnio();
                    break;

                case 5:
                    listarLibrosPorIdioma();
                    break;


                case 0:
                    System.out.println("Cerrando la aplicación");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }

    }

    private void listarLibrosPorIdioma() {
        System.out.println("Escribe las siglas del idioma a buscar Ej. en, es ");
        var idioma = teclado.nextLine();
        libros=repositorio.libroPorIdioma(idioma);
        if(libros.isEmpty()){
            System.out.println("No se encontraron libros en ese idioma");
        }else {
            libros.stream()
                    .sorted(Comparator.comparing(Libro::getName_author))
                    .forEach(textoLibroEncontrado::mostrarTextoLibrosAlmacenados);
        }
    }

    private void listarAutoresVivosEnUnDeterminadoAnio() {
        System.out.println("Escribe el año que deseas buscar");
        var anio = Integer.parseInt(teclado.nextLine());
        libros=repositorio.autorPorAnio(anio);
        if(libros.isEmpty()){
            System.out.println("No se encontraron autores en ese anio");
        }else {
            libros.stream()
                    .sorted(Comparator.comparing(Libro::getName_author))
                    .forEach(textoLibroEncontrado::mostrarTextoAutores);
        }

    }

    private void listarAutoresRegistrados() {
        libros = repositorio.findAll();
        libros.stream()
                .sorted(Comparator.comparing(Libro::getName_author))
                .forEach(textoLibroEncontrado::mostrarTextoAutores);
    }

    private void listarLibrosRegistrados() {
        libros = repositorio.findAll();

        libros.stream()
                .sorted(Comparator.comparing(Libro::getName_author))
                .forEach(textoLibroEncontrado::mostrarTextoLibrosAlmacenados);

    }

    private void buscarLibro() {
            DatosLibro datos = getDatosLibros();
            Libro libro = new Libro(datos);
            mostrarTextoLibroEcontrado(datos);

            System.out.println("Los datos obtenidos finales fueron: "+datos);
            System.out.println("Los datos converitdos a libro fueron: "+libro);

            try {
                repositorio.save(libro);
                System.out.println("Libro guardado correctamente");
            }catch (DataIntegrityViolationException e){
                System.out.println("Excepcion ocurrida: "+e.getMessage());
            }


    }

    private DatosLibro getDatosLibros() {
        System.out.println("Escribe el nombre del libro que deseas buscar");
        var nombreLibro = teclado.nextLine();
        var json = consumoApi.obtenerDatos(URL_BASE +"?search="+ nombreLibro.replace(" ", "+"));
        System.out.println("El json obtenido fue: "+json);

        //
        String title=convertirStringAJson.ConvertirDesdeArrayPrincipalTexto(json,"title");
        System.out.println("El valor obtenido del titulo fue: "+title);

        int downloadCount=convertirStringAJson.ConvertirDesdeArrayPrincipalInt(json,"download_count");
        System.out.println("El valor obtenido de la cantidad de descargas fue: "+downloadCount);

        String languages=convertirStringAJson.ConvertirDesdeVariasArraysTexto(json,"languages");
        System.out.println("El valor obtenido del lenguage fue: "+languages);

        Autores autores=convertirStringAJson.ConvertirDesdeVariasArraysAutores(json);
        System.out.println("El valor obtenido completo de los autores fue: "+autores);

        //
        return new DatosLibro(title,autores,languages,downloadCount);
    }
}
