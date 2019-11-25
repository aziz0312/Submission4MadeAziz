package com.example.submission4madeaziz.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.submission4madeaziz.item.TvShowItem;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class TvShowViewModel extends ViewModel {

    private static final String API_KEY = "90a675a9a40d7071f0eb19cd96fc8a52";
    private MutableLiveData<ArrayList<TvShowItem>> listTvs = new MutableLiveData<>();


    public LiveData<ArrayList<TvShowItem>> getTvs() {
        return listTvs;
    }

    public void setTvs(final String language) {

        AsyncHttpClient client = new AsyncHttpClient();

        final ArrayList<TvShowItem> listItems = new ArrayList<>();



        String url = "https://api.themoviedb.org/3/discover/tv?api_key=" + API_KEY + "&language=" + language;

        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String result = new String(responseBody);
                    JSONObject responseObject = new JSONObject(result);
                    JSONArray list = responseObject.getJSONArray("results");
                    for (int i = 0; i < list.length(); i++) {

                        JSONObject tvs = list.getJSONObject(i);
                        TvShowItem tvShowItem = new TvShowItem();
                        tvShowItem.setTitleTvs(tvs.getString("original_name"));
                        tvShowItem.setReleaseDateTvs(tvs.getString("first_air_date"));
                        tvShowItem.setRatingTvs(tvs.getString("vote_average"));
                        tvShowItem.setDescriptionTvs(tvs.getString("overview"));
                        tvShowItem.setLanguangeTvs(tvs.getString("original_language"));
                        tvShowItem.setPopularityTvs(tvs.getString("popularity"));
                        tvShowItem.setVoteCountTvs(tvs.getString("vote_count"));
                        String poster= tvs.getString("poster_path" );
                        tvShowItem.setImgTvs("https://image.tmdb.org/t/p/w185/" + poster);

                        listItems.add(tvShowItem);
                    }
                    listTvs.postValue(listItems);
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
