package org.uqbar.peliculas.adapter;

import org.uqbar.peliculas.domain.Pelicula;
import org.uqbar.peliculasapp.R;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by fernando on 03/11/15.
 */
public class GeneroAdapter {

    static Map<String, Integer> mapaGeneros;

    private Map<String, Integer> getMapaGeneros() {
        if (mapaGeneros == null) {
            mapaGeneros = new HashMap<String, Integer>();
            mapaGeneros.put("Infantil", R.drawable.infantil);
            mapaGeneros.put("Infantil/Anim", R.drawable.infantil);
            mapaGeneros.put("Accion", R.drawable.accion);
            mapaGeneros.put("Series", R.drawable.default2);
            mapaGeneros.put("Drama", R.drawable.drama);
            mapaGeneros.put("Comedia", R.drawable.comedia);
            mapaGeneros.put("Clasicos", R.drawable.comedia2);
            mapaGeneros.put("Infantil / Peli", R.drawable.infantil);
            mapaGeneros.put("C.Ficcion", R.drawable.sci_fi);
            mapaGeneros.put("Ciencia Ficcion", R.drawable.sci_fi);
            mapaGeneros.put("Musical", R.drawable.drama);
            mapaGeneros.put("C.Romantica", R.drawable.romantica);
            mapaGeneros.put("Suspenso", R.drawable.suspenso);
            mapaGeneros.put("Terror", R.drawable.horror);
            mapaGeneros.put("Infantil/Peli", R.drawable.infantil);
            mapaGeneros.put("Aventuras", R.drawable.fantasia);
            mapaGeneros.put("Nacional", R.drawable.default3);
            mapaGeneros.put("Familia", R.drawable.comedia2);
            mapaGeneros.put("Belica", R.drawable.horror);
            mapaGeneros.put("Documental", R.drawable.default2);
            mapaGeneros.put("Infantil-Peli", R.drawable.infantil);
            mapaGeneros.put("Infantil-Anim", R.drawable.infantil);
            mapaGeneros.put("Teatral", R.drawable.default3);
        }
        return mapaGeneros;
    }

    public int getIconoGenero(Pelicula pelicula) {
        Integer result = getMapaGeneros().get(pelicula.getDescripcionGenero());
        if (result == null) {
            return R.drawable.default3;
        }
        return result.intValue();
    }

}
