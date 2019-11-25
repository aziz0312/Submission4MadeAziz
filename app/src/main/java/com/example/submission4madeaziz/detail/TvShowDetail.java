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
import com.example.submission4madeaziz.db.TvShowHelper;

import com.example.submission4madeaziz.item.TvShowItem;

import static com.example.submission4madeaziz.db.DatabaseContract.TvColoumns.DESCRIPTION_TVS;
import static com.example.submission4madeaziz.db.DatabaseContract.TvColoumns.TITLE_TVS;
import static com.example.submission4madeaziz.db.DatabaseContract.TvColoumns.LANGUANGE_TVS;
import static com.example.submission4madeaziz.db.DatabaseContract.TvColoumns.PHOTO_TVS;
import static com.example.submission4madeaziz.db.DatabaseContract.TvColoumns.POPULARITY_TVS;
import static com.example.submission4madeaziz.db.DatabaseContract.TvColoumns.RATING_TVS;
import static com.example.submission4madeaziz.db.DatabaseContract.TvColoumns.RELEASE_DATE_TVS;
import static com.example.submission4madeaziz.db.DatabaseContract.TvColoumns.VOTE_COUNT_TVS;


public class TvShowDetail extends AppCompatActivity implements View.OnClickListener {

    public static final String EXTRA_TVS = "extra_tvs";

    public TextView tvTitleTvsD, tvReleaseDateTvsD, tvOverviewTvsD, tvRatingTvsD, tvVoteCountD, tvLanguangeTvsD, tvPupularityD;
    public ImageView imgTvsDetail;

    Button btn_favorit_tvs;

    TvShowHelper tvShowHelper;
    int action;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tv_show_detail);


        tvTitleTvsD = findViewById(R.id.tv_titletvs_detail);
        tvReleaseDateTvsD = findViewById(R.id.tv_releasedate_tvs_detail);
        tvRatingTvsD =findViewById(R.id.tv_rating_tvs_detail);
        tvOverviewTvsD =findViewById(R.id.tv_overview_tvs_detail);
        tvPupularityD =findViewById(R.id.tv_popularity_tvs_detail);
        tvVoteCountD =findViewById(R.id.tv_vote_count_tvs_detail);
        tvLanguangeTvsD =findViewById(R.id.tv_languanges_tvs_detail);
        imgTvsDetail = findViewById(R.id.img_tvs_detail);

        TvShowItem tvs = getIntent().getParcelableExtra(EXTRA_TVS);

        assert tvs != null;

        tvTitleTvsD.setText(tvs.getTitleTvs());
        tvRatingTvsD.setText(getString(R.string.rating)+tvs.getRatingTvs());
        tvReleaseDateTvsD.setText(getString(R.string.release_date)+tvs.getReleaseDateTvs());
        tvOverviewTvsD.setText(tvs.getDescriptionTvs());
        tvVoteCountD.setText(getString(R.string.vote_count)+tvs.getVoteCountTvs());
        tvPupularityD.setText(getString(R.string.popularity)+tvs.getPopularityTvs());
        tvLanguangeTvsD.setText(getString(R.string.languange)+tvs.getLanguangeTvs());
        btn_favorit_tvs = findViewById(R.id.btn_favorit_tvs);
        btn_favorit_tvs.setOnClickListener(this);

        Glide.with(TvShowDetail.this)
                .load(tvs.getImgTvs())
                .apply(new RequestOptions().override(350, 550))
                .into(imgTvsDetail);


        tvShowHelper = TvShowHelper.getInstance(getApplicationContext());
        tvShowHelper.open();




        String tvsTitle = tvs.getTitleTvs();
        Log.d("test", "onCreate: "+tvsTitle+tvShowHelper.getOne(tvsTitle));

        if ( tvShowHelper.getOne(tvsTitle)){
            //delete
            btn_favorit_tvs.setText(getString(R.string.unfavorit));
            action = 0;
        } else if (!tvShowHelper.getOne(tvsTitle)){
            //insert
            btn_favorit_tvs.setText(getString(R.string.favorit));
            action = 1;
        }



    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onClick(View v) {

        TvShowItem tvs = getIntent().getParcelableExtra(EXTRA_TVS);

        if (action == 1){

            ContentValues values = new ContentValues();
            values.put(TITLE_TVS, tvs.getTitleTvs());
            values.put(DESCRIPTION_TVS, tvs.getDescriptionTvs());
            values.put(PHOTO_TVS, tvs.getImgTvs());
            values.put(RELEASE_DATE_TVS, tvs.getReleaseDateTvs());
            values.put(RATING_TVS, tvs.getRatingTvs());
            values.put(VOTE_COUNT_TVS, tvs.getVoteCountTvs());
            values.put(LANGUANGE_TVS, tvs.getLanguangeTvs());
            values.put(POPULARITY_TVS, tvs.getPopularityTvs());
            long result = TvShowHelper.insert(values);

            btn_favorit_tvs.setText(getString(R.string.unfavorit));
            action =  0;

            if (result > 0) {
                Toast.makeText(TvShowDetail.this, getString(R.string.succes)+" "+getString(R.string.favorit), Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(TvShowDetail.this, getString(R.string.failed)+" "+getString(R.string.favorit), Toast.LENGTH_SHORT).show();
            }

        }else if(action == 0){

            long result = tvShowHelper.deleteByTitle(tvs.getTitleTvs());
            if (result > 0) {
                Toast.makeText(TvShowDetail.this, getString(R.string.succes)+" "+getString(R.string.unfavorit), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(TvShowDetail.this, MainActivity.class);
                startActivity(intent);
                action = 1;
                btn_favorit_tvs.setText(getString(R.string.favorit));
            } else {
                Toast.makeText(TvShowDetail.this, getString(R.string.failed)+" "+getString(R.string.unfavorit), Toast.LENGTH_SHORT).show();
            }

        }


    }
}
