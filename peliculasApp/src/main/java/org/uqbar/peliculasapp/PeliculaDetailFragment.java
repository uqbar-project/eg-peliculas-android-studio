package org.uqbar.peliculasapp;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.uqbar.peliculas.adapter.GeneroAdapter;
import org.uqbar.peliculas.domain.Pelicula;

/**
 * A fragment representing a single Pelicula detail screen.
 * This fragment is either contained in a {@link PeliculaListActivity}
 * in two-pane mode (on tablets) or a {@link PeliculaDetailActivity}
 * on handsets.
 */
public class PeliculaDetailFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";

    /**
     * The dummy content this fragment is presenting.
     */
    private Pelicula pelicula;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public PeliculaDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            pelicula = (Pelicula) getArguments().get(ARG_ITEM_ID);

            Activity activity = this.getActivity();
            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
            if (appBarLayout != null) {
                appBarLayout.setTitle(pelicula.getTitulo());
            } else {
                activity.setTitle(pelicula.getTitulo());
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_pelicula_detail, container, false);

        // Show the dummy content as text in a TextView.
        if (pelicula != null) {
            ((TextView) rootView.findViewById(R.id.pelicula_genero)).setText(pelicula.getDescripcionGenero());
            ImageView imgGenero = ((ImageView) rootView.findViewById(R.id.imgGenero));
            imgGenero.setImageDrawable(getResources().getDrawable(new GeneroAdapter().getIconoGenero(pelicula)));
            ((TextView) rootView.findViewById(R.id.pelicula_actores)).setText(pelicula.getActores());
            ((TextView) rootView.findViewById(R.id.pelicula_sinopsis)).setText(pelicula.getSinopsis());
        }

        return rootView;
    }
}
