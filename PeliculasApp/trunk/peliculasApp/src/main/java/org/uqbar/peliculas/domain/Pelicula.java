package org.uqbar.peliculas.domain;

import java.io.Serializable;

/**
 * Created by fernando on 26/10/15.
 */
public class Pelicula implements Serializable {
    Long id;
    String titulo;
    Genero genero;
    String sinopsis;
    String actores;

    public Pelicula(String titulo, Genero genero, String sinopsis, String actores) {
        this.titulo = titulo;
        this.genero = genero;
        this.sinopsis = sinopsis;
        this.actores = actores;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getSinopsis() {
        return sinopsis;
    }

    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }

    public String getActores() {
        return actores;
    }

    public void setActores(String actores) {
        this.actores = actores;
    }

    public String toString() {
        return titulo;
    }

    public String getDescripcionGenero() {
        if (genero == null) {
            return "";
        }
        return genero.getDescripcion();
    }

}
