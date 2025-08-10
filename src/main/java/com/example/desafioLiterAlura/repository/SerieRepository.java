package com.example.desafioLiterAlura.repository;

import com.example.desafioLiterAlura.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SerieRepository extends JpaRepository<Libro,Long> {

    @Query("SELECT l FROM Libro l WHERE l.birth_year <= :anio AND l.death_year >= :anio")
    List<Libro> autorPorAnio(int anio);

    @Query("SELECT l FROM Libro l WHERE l.languages = :idioma")
    List<Libro> libroPorIdioma(String idioma);

    //List<Libro> findBybirth_yearLessThananioAnddeath_yearGreaterThananio(int anio);
    /*
    Optional<Libro> findByTitleContainsIgnoreCase(String nombreSerie);

    List<Libro> findTop5ByOrderByEvaluacionDesc();
    List<Serie> findByTotalTemporadasLessThanEqualAndEvaluacionGreaterThanEqual(int totalTemporadas, Double evaluacion);

    @Query("SELECT s FROM Serie s WHERE s.totalTemporadas <= :totalTemporadas AND s.evaluacion >= :evaluacion")
    List<Libro> seriesPorTemparadaYEvaluacion(int totalTemporadas, Double evaluacion);

    @Query("SELECT e FROM Serie s JOIN s.episodios e WHERE e.titulo ILIKE %:nombreEpisodio%")
    List<Libro> episodiosPorNombre(String nombreEpisodio);

    @Query("SELECT e FROM Serie s JOIN s.episodios e WHERE s = :serie ORDER BY e.evaluacion DESC LIMIT 5 ")
    List<Libro> top5Episodios(Libro libro);

     */
}
