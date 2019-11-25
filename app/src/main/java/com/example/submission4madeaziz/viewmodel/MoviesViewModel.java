package com.example.submission4madeaziz.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.submission4madeaziz.item.MoviesItem;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class MoviesViewModel extends ViewModel {

    private static final String API_KEY = "90a675a9a40d7071f0eb19cd96fc8a52";
    private MutableLiveData<ArrayList<MoviesItem>> listMovies = new MutableLiveData<>();



    public LiveData<ArrayList<MoviesItem>> getMovies() {
        return listMovies;
    }

    public void setMovies(final String language) {

        AsyncHttpClient client = new AsyncHttpClient();
        final ArrayList<MoviesItem> listItems = new ArrayList<>();



        String url = "https://api.themoviedb.org/3/discover/movie?api_key=" + API_KEY + "&language=" + language;

        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String result = new String(responseBody);
                    JSONObject responseObject = new JSONObject(result);
                    JSONArray list = responseObject.getJSONArray("results");
                    for (int i = 0; i < list.length(); i++) {

                        JSONObject movie = list.getJSONObject(i);
                        MoviesItem moviesItem = new MoviesItem();
                        moviesItem.setTitleMovies(movie.getString("title"));
                        moviesItem.setReleaseDate(movie.getString("release_date"));
                        moviesItem.setRating(movie.getString("vote_average"));
                        moviesItem.setDescription(movie.getString("overview"));
                        moviesItem.setLanguange(movie.getString("original_language"));
                        moviesItem.setPopularity(movie.getString("popularity"));
                        moviesItem.setVoteCount(movie.getString("vote_count"));
                        String poster= movie.getString("poster_path" );
                        moviesItem.setImgMovies("https://image.tmdb.org/t/p/w185/" + poster);

                        listItems.add(moviesItem);
                    }
                    listMovies.postValue(listItems);
                } catch (Exception e) {
                    Log.d("Exception", e.getMessage());
                }
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.d("onFailure", error.getMessage());
            }
        });
    }


}
