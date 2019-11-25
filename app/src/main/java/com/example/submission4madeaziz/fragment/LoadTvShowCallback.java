package com.example.submission4madeaziz.fragment;

import com.example.submission4madeaziz.item.TvShowItem;

import java.util.ArrayList;

public interface LoadTvShowCallback {
    void preExecute();
    void postExecute(ArrayList<TvShowItem> tvShowItems);
}
