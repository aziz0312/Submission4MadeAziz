package com.example.submission4madeaziz.fragment;


import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.submission4madeaziz.R;
import com.example.submission4madeaziz.adapter.MoviesAdapter;
import com.example.submission4madeaziz.db.MovieHelper;
import com.example.submission4madeaziz.helper.MovieMappingHelper;
import com.example.submission4madeaziz.item.MoviesItem;


import java.lang.ref.WeakReference;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavoritMoviesFragment extends Fragment implements LoadMoviesCallback{

    private MoviesAdapter adapter;
    private ProgressBar progressBarMoviesFavorit;
    private MovieHelper movieHelper;


    private ArrayList<MoviesItem> items = new ArrayList<>();

    private RecyclerView rvMovies;


    public FavoritMoviesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_favorit_movies, container, false);
        rvMovies = view.findViewById(R.id.rv_movies_favorit);
        rvMovies.setHasFixedSize(true);

        progressBarMoviesFavorit = view.findViewById(R.id.progressBarMoviesFavorit);

        movieHelper = MovieHelper.getInstance(getContext());
        movieHelper.open();


        showRecyclerView();

        new FavoritMoviesFragment.LoadMoviesAsync(movieHelper, this).execute();
        return view;

    }

    private void showRecyclerView() {
        adapter = new MoviesAdapter();
        adapter.notifyDataSetChanged();
        rvMovies.setLayoutManager(new LinearLayoutManager(getContext()));
        rvMovies.setAdapter(adapter);
    }


    @Override
    public void preExecute() {
        progressBarMoviesFavorit.setVisibility(View.VISIBLE);
    }

    @Override
    public void postExecute(ArrayList<MoviesItem> movies) {
        progressBarMoviesFavorit.setVisibility(View.INVISIBLE);
        adapter.setData(movies);
        items.addAll(movies);
    }

    @SuppressLint("StaticFieldLeak")
    private class LoadMoviesAsync  extends AsyncTask<Void, Void, ArrayList<MoviesItem>> {

        private final WeakReference<MovieHelper> weakMovieHelper;
        private final WeakReference<LoadMoviesCallback> weakCallback;

        private LoadMoviesAsync(MovieHelper movieHelper, LoadMoviesCallback callback) {
            weakMovieHelper = new WeakReference<>(movieHelper);
            weakCallback = new WeakReference<>(callback);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            weakCallback.get().preExecute();
        }

        @Override
        protected ArrayList<MoviesItem> doInBackground(Void... voids) {
            Cursor dataCursor = weakMovieHelper.get().queryAll();
            return MovieMappingHelper.mapCursorToArrayList(dataCursor);
        }

        @Override
        protected void onPostExecute(ArrayList<MoviesItem> notes) {
            super.onPostExecute(notes);

            weakCallback.get().postExecute(notes);

        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        movieHelper.close();
    }

}
