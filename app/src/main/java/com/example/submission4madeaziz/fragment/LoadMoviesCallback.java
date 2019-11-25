package com.example.submission4madeaziz.fragment;

import com.example.submission4madeaziz.item.MoviesItem;

import java.util.ArrayList;

interface LoadMoviesCallback {
    void preExecute();
    void postExecute(ArrayList<MoviesItem> movies);
}
