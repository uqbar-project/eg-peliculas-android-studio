package org.uqbar.peliculas.service;

import org.uqbar.peliculas.domain.Pelicula;

import java.util.List;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by fernando on 11/11/15.
 */
public interface PeliculasService {

    @GET("peliculas/{tituloContiene}")
    public Call<List<Pelicula>> getPeliculas(@Path("tituloContiene") String tituloContiene);

}
