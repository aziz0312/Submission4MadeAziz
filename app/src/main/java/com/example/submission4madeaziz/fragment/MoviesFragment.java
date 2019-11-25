package com.example.submission4madeaziz.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.submission4madeaziz.R;
import com.example.submission4madeaziz.adapter.MoviesAdapter;
import com.example.submission4madeaziz.item.MoviesItem;
import com.example.submission4madeaziz.viewmodel.MoviesViewModel;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class MoviesFragment extends Fragment {

    private MoviesAdapter adapter;
    private ProgressBar progressBarMovies;


    public MoviesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        View view = inflater.inflate(R.layout.fragment_movies, container, false);
        RecyclerView rvMovies = view.findViewById(R.id.rv_movies);
        rvMovies.setHasFixedSize(true);
        progressBarMovies = view.findViewById(R.id.progressBarMovies);

        rvMovies.setLayoutManager(new LinearLayoutManager(this.getContext()));

        String language = getString(R.string.language);

        adapter = new MoviesAdapter();
        adapter.notifyDataSetChanged();
        rvMovies.setAdapter(adapter);

        MoviesViewModel moviesViewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(MoviesViewModel.class);
        moviesViewModel.setMovies(language);

        showLoading(true);

        moviesViewModel.getMovies().observe(this, new Observer<ArrayList<MoviesItem>>() {
            @Override
            public void onChanged(ArrayList<MoviesItem> moviesItems) {
                if (moviesItems != null) {
                    adapter.setData(moviesItems);
                    showLoading(false);
                }
            }
        });


        return view;
    }



    private void showLoading(Boolean state) {
        if (state) {
            progressBarMovies.setVisibility(View.VISIBLE);
        } else {
            progressBarMovies.setVisibility(View.GONE);
        }
    }

}
