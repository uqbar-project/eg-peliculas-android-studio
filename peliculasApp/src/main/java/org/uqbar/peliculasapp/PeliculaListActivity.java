package org.uqbar.peliculasapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.uqbar.peliculas.domain.Pelicula;

import java.io.Serializable;


/**
 * An activity representing a list of Peliculas. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link PeliculaDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 * <p/>
 * The activity makes heavy use of fragments. The list of items is a
 * {@link PeliculaListFragment} and the item details
 * (if present) is a {@link PeliculaDetailFragment}.
 * <p/>
 * This activity also implements the required
 * {@link PeliculaListFragment.Callbacks} interface
 * to listen for item selections.
 */
public class PeliculaListActivity extends AppCompatActivity
        implements PeliculaListFragment.Callbacks {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pelicula_app_bar);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (findViewById(R.id.pelicula_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-large and
            // res/values-sw600dp). If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;

            // In two-pane mode, list items should be given the
            // 'activated' state when touched.
            ((PeliculaListFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.pelicula_list))
                    .setActivateOnItemClick(true);
        }

    }

    /**
     * Callback method from {@link PeliculaListFragment.Callbacks}
     * indicating that the item with the given ID was selected.
     */
    @Override
    public void onItemSelected(Pelicula pelicula) {
        if (mTwoPane) {
            // In two-pane mode, show the detail view in this activity by
            // adding or replacing the detail fragment using a
            // fragment transaction.
            Bundle arguments = new Bundle();
            arguments.putSerializable(PeliculaDetailFragment.ARG_ITEM_ID, pelicula);
            PeliculaDetailFragment fragment = new PeliculaDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.pelicula_detail_container, fragment)
                    .commit();

        } else {
            // In single-pane mode, simply start the detail activity
            // for the selected item ID.
            Intent detailIntent = new Intent(this, PeliculaDetailActivity.class);
            Log.w("Pelis", pelicula.getTitulo());
            detailIntent.putExtra(PeliculaDetailFragment.ARG_ITEM_ID, pelicula);
            startActivity(detailIntent);
        }
    }
}
