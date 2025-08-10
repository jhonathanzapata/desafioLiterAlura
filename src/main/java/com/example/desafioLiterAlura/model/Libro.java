package com.example.desafioLiterAlura.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "libros")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String title;
    private String name_author;
    private int birth_year;
    private int death_year;
    private String languages;
    private int download_count;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName_author() {
        return name_author;
    }

    public void setName_author(String name_author) {
        this.name_author = name_author;
    }

    public int getBirth_year() {
        return birth_year;
    }

    public void setBirth_year(int birth_year) {
        this.birth_year = birth_year;
    }

    public int getDeath_year() {
        return death_year;
    }

    public void setDeath_year(int death_year) {
        this.death_year = death_year;
    }

    public String getLanguages() {
        return languages;
    }

    public void setLanguages(String languages) {
        this.languages = languages;
    }

    public int getDownload_count() {
        return download_count;
    }

    public void setDownload_count(int download_count) {
        this.download_count = download_count;
    }

    public Libro() {
    }

    public Libro(DatosLibro libro) {
        this.title = libro.title();
        this.name_author = libro.authors().getName();
        this.birth_year = libro.authors().getBirth_year();
        this.death_year = libro.authors().getDeath_year();
        this.languages = libro.languages();
        this.download_count=libro.download_count();
    }

    @Override
    public String toString() {
        return "Libro{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", name_author='" + name_author + '\'' +
                ", birth_year=" + birth_year +
                ", death_year=" + death_year +
                ", languages='" + languages + '\'' +
                ", download_count=" + download_count +
                '}';
    }
}
