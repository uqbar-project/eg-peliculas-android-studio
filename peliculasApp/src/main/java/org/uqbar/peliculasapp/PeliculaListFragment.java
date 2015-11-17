package org.uqbar.peliculasapp;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import org.uqbar.peliculas.adapter.PeliculaAdapter;
import org.uqbar.peliculas.domain.Pelicula;
import org.uqbar.peliculas.service.PeliculasService;

import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * De  * A list fragment representing a list of Peliculas. This fragment
 * also supports tablet devices by allowing list items to be given an
 * 'activated' state upon selection. This helps indicate which item is
 * currently being viewed in a {@link PeliculaDetailFragment}.
 * <p/>
 * Activities containing this fragment MUST implement the {@link Callbacks}
 * interface.
 */
public class PeliculaListFragment extends ListFragment implements View.OnClickListener {

    public static int MIN_BUSQUEDA_PELICULAS = 2;

    /**
     * The serialization (saved instance state) Bundle key representing the
     * activated item position. Only used on tablets.
     */
    private static final String STATE_ACTIVATED_POSITION = "activated_position";

    /**
     * The fragment's current callback object, which is notified of list item
     * clicks.
     */
    private Callbacks mCallbacks = sDummyCallbacks;

    /**
     * The current activated item position. Only used on tablets.
     */
    private int mActivatedPosition = ListView.INVALID_POSITION;

    private PeliculasService peliculaService;

    /**
     * A callback interface that all activities containing this fragment must
     * implement. This mechanism allows activities to be notified of item
     * selections.
     */
    public interface Callbacks {
        void onItemSelected(Pelicula pelicula);
    }

    @Override
    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
        return super.onCreateAnimation(transit, enter, nextAnim);
    }

    /**
     * A dummy implementation of the {@link Callbacks} interface that does
     * nothing. Used only when this fragment is not attached to an activity.
     */
    private static Callbacks sDummyCallbacks = new Callbacks() {
        @Override
        public void onItemSelected(Pelicula pelicula) {
        }
    };

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public PeliculaListFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Esta URL apunta al proyecto con ORM de Grails
        // 		val API_URL = "http://10.0.2.2:8080/videoclub-ui-orm-grails"
        // Esta URL apunta a la solución en Grails con Homes hechos en Xtend
        //String SERVER_IP = "10.0.2.2";

        // IMPORTANTE
        // Por un bug de retrofit 2.0, la BASE_URL debe tener una / al final
        // y la dirección del service debe comenzar sin /, como un path relativo
        String BASE_URL = "http://10.0.2.2:8080/videoclub-ui-grails-homes-xtend/";

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        peliculaService = retrofit.create(PeliculasService.class);
    }

    private void buscarPeliculas() {
        EditText campoBusqueda = (EditText) this.getView().findViewById(R.id.tituloContiene);
        String titulo = campoBusqueda.getText().toString();

        Call<List<Pelicula>> peliculaCall = peliculaService.getPeliculas(titulo);

        peliculaCall.enqueue(new Callback<List<Pelicula>>() {
            @Override
            public void onResponse(Response<List<Pelicula>> response, Retrofit retrofit) {
                List<Pelicula> peliculas = response.body();

                setListAdapter(new PeliculaAdapter(
                        getActivity(),
                        peliculas));

            }

            @Override
            public void onFailure(Throwable t) {
                t.printStackTrace();
                Log.e("PeliculasApp", t.getMessage());
            }
        });
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Restore the previously serialized activated item position.
        if (savedInstanceState != null
                && savedInstanceState.containsKey(STATE_ACTIVATED_POSITION)) {
            setActivatedPosition(savedInstanceState.getInt(STATE_ACTIVATED_POSITION));
        }

        // Comportamiento del checkbox que indica si se busca a medida que se escribe
        final CheckBox chkBuscar = (CheckBox) view.findViewById(R.id.chkBuscarOnline);
        final View myView = view;
        chkBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageButton btnBuscar = (ImageButton) myView.findViewById(R.id.btnBuscar);
                if (chkBuscar.isChecked()) {
                    btnBuscar.setVisibility(View.INVISIBLE);
                } else {
                    btnBuscar.setVisibility(View.VISIBLE);
                }
            }
        });

        // Comportamiento del título de búsqueda
        EditText tituloContiene = (EditText) view.findViewById(R.id.tituloContiene);
        tituloContiene.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (chkBuscar.isChecked() && editable.length() >= MIN_BUSQUEDA_PELICULAS) {
                    buscarPeliculas();
                }
            }
        });

        ((ImageButton) view.findViewById(R.id.btnBuscar)).setOnClickListener(this);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        // Activities containing this fragment must implement its callbacks.
        if (!(activity instanceof Callbacks)) {
            throw new IllegalStateException("Activity must implement fragment's callbacks.");
        }

        mCallbacks = (Callbacks) activity;
    }

    @Override
    public void onDetach() {
        super.onDetach();

        // Reset the active callbacks interface to the dummy implementation.
        mCallbacks = sDummyCallbacks;
    }

    @Override
    public void onListItemClick(ListView listView, View view, int position, long id) {
        super.onListItemClick(listView, view, position, id);

        Pelicula pelicula = (Pelicula) listView.getAdapter().getItem(position);
        Toast.makeText(getContext(), pelicula.getTitulo(), Toast.LENGTH_LONG).show();

        mCallbacks.onItemSelected(pelicula);


    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (mActivatedPosition != ListView.INVALID_POSITION) {
            // Serialize and persist the activated item position.
            outState.putInt(STATE_ACTIVATED_POSITION, mActivatedPosition);
        }
    }

    /**
     * Turns on activate-on-click mode. When this mode is on, list items will be
     * given the 'activated' state when touched.
     */
    public void setActivateOnItemClick(boolean activateOnItemClick) {
        // When setting CHOICE_MODE_SINGLE, ListView will automatically
        // give items the 'activated' state when touched.
        getListView().setChoiceMode(activateOnItemClick
                ? ListView.CHOICE_MODE_SINGLE
                : ListView.CHOICE_MODE_NONE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.pelicula_list_fragment, null, false);
    }

    private void setActivatedPosition(int position) {
        if (position == ListView.INVALID_POSITION) {
            getListView().setItemChecked(mActivatedPosition, false);
        } else {
            getListView().setItemChecked(position, true);
        }

        mActivatedPosition = position;
    }

    @Override
    public void onClick(View v) {
        buscarPeliculas();
    }

}
