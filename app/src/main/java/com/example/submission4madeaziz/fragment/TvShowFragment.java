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
import com.example.submission4madeaziz.adapter.TvShowAdapter;
import com.example.submission4madeaziz.item.TvShowItem;
import com.example.submission4madeaziz.viewmodel.TvShowViewModel;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class TvShowFragment extends Fragment {

    private TvShowAdapter adapter;
    private ProgressBar progressBarTvs;


    public TvShowFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_tv_show, container, false);
        RecyclerView rvTvShow = view.findViewById(R.id.rv_tv_show);
        rvTvShow.setHasFixedSize(true);
        progressBarTvs = view.findViewById(R.id.progressBarTvs);

        rvTvShow.setLayoutManager(new LinearLayoutManager(this.getContext()));

        String language = getString(R.string.language);

        adapter = new TvShowAdapter();
        adapter.notifyDataSetChanged();
        rvTvShow.setAdapter(adapter);

        TvShowViewModel tvShowViewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(TvShowViewModel.class);
        tvShowViewModel.setTvs(language);

        showLoading(true);

        tvShowViewModel.getTvs().observe(this, new Observer<ArrayList<TvShowItem>>() {
            @Override
            public void onChanged(ArrayList<TvShowItem> tvsItems) {
                if (tvsItems != null) {
                    adapter.setData(tvsItems);
                    showLoading(false);
                }
            }
        });


        return view;
    }



    private void showLoading(Boolean state) {
        if (state) {
            progressBarTvs.setVisibility(View.VISIBLE);
        } else {
            progressBarTvs.setVisibility(View.GONE);
        }
    }


}
