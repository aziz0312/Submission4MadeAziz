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
import com.example.submission4madeaziz.adapter.TvShowAdapter;
import com.example.submission4madeaziz.db.TvShowHelper;
import com.example.submission4madeaziz.helper.TvShowMappingHelper;
import com.example.submission4madeaziz.item.TvShowItem;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavoritTvShowFragment extends Fragment implements LoadTvShowCallback{

    private TvShowAdapter adapter;
    private ProgressBar progressBarTvShowFavorit;
    private TvShowHelper tvShowHelperHelper;


    private ArrayList<TvShowItem> items = new ArrayList<>();

    private RecyclerView rvTvShow;


    public FavoritTvShowFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_favorit_tv_show, container, false);
        rvTvShow = view.findViewById(R.id.rv_tv_show_favorit);
        rvTvShow.setHasFixedSize(true);

        progressBarTvShowFavorit = view.findViewById(R.id.progressBarTvsFavorit);

        tvShowHelperHelper = TvShowHelper.getInstance(getContext());
        tvShowHelperHelper.open();


        showRecyclerView();

        new FavoritTvShowFragment.LoadTvShowAsync(tvShowHelperHelper, this).execute();
        return view;

    }

        private void showRecyclerView() {
        adapter = new TvShowAdapter();
        adapter.notifyDataSetChanged();
        rvTvShow.setLayoutManager(new LinearLayoutManager(getContext()));
        rvTvShow.setAdapter(adapter);
    }


    @Override
    public void preExecute() {
        progressBarTvShowFavorit.setVisibility(View.VISIBLE);
    }

    @Override
    public void postExecute(ArrayList<TvShowItem> tvShowItems) {
        progressBarTvShowFavorit.setVisibility(View.INVISIBLE);
        adapter.setData(tvShowItems);
        items.addAll(tvShowItems);
    }

    @SuppressLint("StaticFieldLeak")
    private class LoadTvShowAsync  extends AsyncTask<Void, Void, ArrayList<TvShowItem>> {

        private final WeakReference<TvShowHelper> weakTvShowHelper;
        private final WeakReference<LoadTvShowCallback> weakCallback;

        private LoadTvShowAsync(TvShowHelper tvShowHelper, LoadTvShowCallback callback) {
            weakTvShowHelper= new WeakReference<>(tvShowHelper);
            weakCallback = new WeakReference<>(callback);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            weakCallback.get().preExecute();
        }

        @Override
        protected ArrayList<TvShowItem> doInBackground(Void... voids) {
            Cursor dataCursor = weakTvShowHelper.get().queryAll();
            return TvShowMappingHelper.mapCursorToArrayList(dataCursor);
        }

        @Override
        protected void onPostExecute(ArrayList<TvShowItem> tvShowItems) {
            super.onPostExecute(tvShowItems);

            weakCallback.get().postExecute(tvShowItems);

        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        tvShowHelperHelper.close();
    }


}
