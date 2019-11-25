package com.example.submission4madeaziz.adapter;


import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.submission4madeaziz.R;
import com.example.submission4madeaziz.detail.TvShowDetail;
import com.example.submission4madeaziz.item.TvShowItem;

import java.util.ArrayList;

public class TvShowAdapter extends RecyclerView.Adapter<TvShowAdapter.TvShowViewHolder> {


    private ArrayList<TvShowItem> tvsData = new ArrayList<>();


    public void setData(ArrayList<TvShowItem> items) {
        tvsData.clear();
        tvsData.addAll(items);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TvShowViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_tv_show, viewGroup, false);
        return new TvShowViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TvShowViewHolder holder, final int position) {

        holder.bind(tvsData.get(position));

        holder.btnDetailTvs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final TvShowItem tvs = tvsData.get(position);
                tvs.setTitleTvs(tvs.getTitleTvs());
                tvs.setReleaseDateTvs(tvs.getReleaseDateTvs());
                tvs.setRatingTvs(tvs.getRatingTvs());
                tvs.setImgTvs(tvs.getImgTvs());
                tvs.setVoteCountTvs(tvs.getVoteCountTvs());
                tvs.setLanguangeTvs(tvs.getLanguangeTvs());
                tvs.setPopularityTvs(tvs.getPopularityTvs());
                tvs.setDescriptionTvs(tvs.getDescriptionTvs());

                Intent moveTvShowDetail = new Intent(v.getContext(), TvShowDetail.class);
                moveTvShowDetail.putExtra(TvShowDetail.EXTRA_TVS, tvs);
                v.getContext().startActivity(moveTvShowDetail);


            }
        });

    }

    @Override
    public int getItemCount() {
        return tvsData.size();
    }



    class TvShowViewHolder extends RecyclerView.ViewHolder {

        ImageView imgTvs;
        TextView tvTitleTvs, tvDescriptionTvs, tvRatingTvs, tvReleaseDateTvs;
        Button btnDetailTvs;

        TvShowViewHolder(@NonNull View itemView) {
            super(itemView);
            imgTvs = itemView.findViewById(R.id.img_tvs);
            tvTitleTvs = itemView.findViewById(R.id.tv_title_tvs);
            tvReleaseDateTvs = itemView.findViewById(R.id.tv_release_date_tvs);
            tvRatingTvs = itemView.findViewById(R.id.tv_rating_tvs);
            tvDescriptionTvs = itemView.findViewById(R.id.tv_overview_tvs);
            btnDetailTvs = itemView.findViewById(R.id.btn_detail_tvs);

        }

        void bind(TvShowItem tvs) {
            Glide.with(itemView.getContext())
                    .load(tvs.getImgTvs())
                    .apply(new RequestOptions().override(350, 550))
                    .into(imgTvs);
            tvTitleTvs.setText(tvs.getTitleTvs());
            tvReleaseDateTvs.setText(tvs.getReleaseDateTvs());
            tvRatingTvs.setText(tvs.getRatingTvs());
            tvDescriptionTvs.setText(tvs.getDescriptionTvs());

        }
    }
}
