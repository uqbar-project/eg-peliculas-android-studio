package org.uqbar.peliculas.domain;

import java.io.Serializable;

/**
 * Created by fernando on 26/10/15.
 */
public class Genero implements Serializable {
    String descripcion;

    public Genero(String descripcionGenero) {
        this.descripcion = descripcionGenero;
    }

    public String toString() {
        return descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
