package com.example.submission4madeaziz.detail;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.submission4madeaziz.MainActivity;
import com.example.submission4madeaziz.R;
import com.example.submission4madeaziz.db.MovieHelper;
import com.example.submission4madeaziz.item.MoviesItem;



import static com.example.submission4madeaziz.db.DatabaseContract.MovieColoumns.DESCRIPTION_MV;
import static com.example.submission4madeaziz.db.DatabaseContract.MovieColoumns.LANGUANGE_MV;
import static com.example.submission4madeaziz.db.DatabaseContract.MovieColoumns.PHOTO_MV;
import static com.example.submission4madeaziz.db.DatabaseContract.MovieColoumns.POPULARITY_MV;
import static com.example.submission4madeaziz.db.DatabaseContract.MovieColoumns.RATING_MV;
import static com.example.submission4madeaziz.db.DatabaseContract.MovieColoumns.RELEASE_DATE_MV;
import static com.example.submission4madeaziz.db.DatabaseContract.MovieColoumns.TITLE_MV;
import static com.example.submission4madeaziz.db.DatabaseContract.MovieColoumns.VOTE_COUNT_MV;

public class MoviesDetail extends AppCompatActivity implements View.OnClickListener {

    public static final String EXTRA_MOVIE = "extra_movie";

    public TextView tvTitle, tvReleaseDate, tvOverview, tvRating, tvVoteCount, tvLanguange, tvPopularity;
    public ImageView imgMovies_detail;
    Button btn_favorit_movies;

    MovieHelper movieHelper;
    int action;



    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies_detail);

        tvTitle = findViewById(R.id.tv_titlemovies_detail);
        tvReleaseDate = findViewById(R.id.tv_releasedate_movies_detail);
        tvRating =findViewById(R.id.tv_rating_detail);
        tvOverview =findViewById(R.id.tv_overview);
        tvPopularity =findViewById(R.id.tv_popularity);
        tvVoteCount =findViewById(R.id.tv_vote_count);
        tvLanguange =findViewById(R.id.tv_languanges);
        imgMovies_detail = findViewById(R.id.img_movies_detail);
        btn_favorit_movies = findViewById(R.id.btn_favorite_movies);
        btn_favorit_movies.setOnClickListener(this);

        MoviesItem movie = getIntent().getParcelableExtra(EXTRA_MOVIE);

        assert movie != null;

        tvTitle.setText(movie.getTitleMovies());
        tvRating.setText(getString(R.string.rating)+movie.getRating());
        tvReleaseDate.setText(getString(R.string.release_date)+movie.getReleaseDate());
        tvOverview.setText(movie.getDescription());
        tvVoteCount.setText(getString(R.string.vote_count)+movie.getVoteCount());
        tvPopularity.setText(getString(R.string.popularity)+movie.getPopularity());
        tvLanguange.setText(getString(R.string.languange)+movie.getLanguange());

        Glide.with(MoviesDetail.this)
                .load(movie.getImgMovies())
                .apply(new RequestOptions().override(350, 550))
                .into(imgMovies_detail);

        movieHelper = MovieHelper.getInstance(getApplicationContext());
        movieHelper.open();




        String movieTitle = movie.getTitleMovies();
        Log.d("test", "onCreate: "+movieTitle+movieHelper.getOne(movieTitle));

        if (movieHelper.getOne(movieTitle)){
            //delete
            btn_favorit_movies.setText(getString(R.string.unfavorit));
            action = 0;
        } else if (!movieHelper.getOne(movieTitle)){
            //insert
            btn_favorit_movies.setText(getString(R.string.favorit));
            action = 1;
        }


    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onClick(View view) {

        MoviesItem movie = getIntent().getParcelableExtra(EXTRA_MOVIE);

        if (action==1){

            ContentValues values = new ContentValues();
            values.put(TITLE_MV, movie.getTitleMovies());
            values.put(DESCRIPTION_MV, movie.getDescription());
            values.put(PHOTO_MV, movie.getImgMovies());
            values.put(RELEASE_DATE_MV, movie.getReleaseDate());
            values.put(RATING_MV, movie.getRating());
            values.put(VOTE_COUNT_MV, movie.getVoteCount());
            values.put(LANGUANGE_MV, movie.getLanguange());
            values.put(POPULARITY_MV, movie.getPopularity());
            long result = MovieHelper.insert(values);

            btn_favorit_movies.setText(getString(R.string.unfavorit));
            action = 0;

            if (result > 0) {
                Toast.makeText(MoviesDetail.this, getString(R.string.succes)+" "+getString(R.string.favorit), Toast.LENGTH_SHORT).show();

            } else {
                Toast.makeText(MoviesDetail.this, getString(R.string.failed)+" "+getString(R.string.favorit), Toast.LENGTH_SHORT).show();
            }

        }else if(action == 0){

            long result = movieHelper.deleteByTitle(movie.getTitleMovies());
            if (result > 0) {
                Toast.makeText(MoviesDetail.this, getString(R.string.succes)+" "+getString(R.string.unfavorit), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MoviesDetail.this, MainActivity.class);
                startActivity(intent);
                action = 1;
                btn_favorit_movies.setText(getString(R.string.favorit));
            } else {
                Toast.makeText(MoviesDetail.this, getString(R.string.failed)+" "+getString(R.string.unfavorit), Toast.LENGTH_SHORT).show();
            }

        }


    }
}