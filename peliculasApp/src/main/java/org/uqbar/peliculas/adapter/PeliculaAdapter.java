package org.uqbar.peliculas.adapter;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.uqbar.peliculas.domain.Pelicula;
import org.uqbar.peliculasapp.R;

import java.util.List;

/**
 * Created by fernando on 27/10/15.
 */
public class PeliculaAdapter extends ArrayAdapter<Pelicula> {

    public PeliculaAdapter(Context context, List<Pelicula> peliculas) {
        super(context, R.layout.pelicula_row, peliculas);
    }

    @Override
    public long getItemId(int position) {
        return getItem(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.pelicula_row, parent, false);
        final Pelicula pelicula = getItem(position);

        TextView tvPelicula = (TextView) rowView.findViewById(R.id.lblPelicula);
        tvPelicula.setText(pelicula.toString());

        TextView tvActores = (TextView) rowView.findViewById(R.id.lblActores);
        tvActores.setText(pelicula.getActores());
        return rowView;
    }
}
