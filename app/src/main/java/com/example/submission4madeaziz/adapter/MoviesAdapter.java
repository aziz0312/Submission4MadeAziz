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
import com.example.submission4madeaziz.detail.MoviesDetail;
import com.example.submission4madeaziz.item.MoviesItem;

import java.util.ArrayList;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder> {


    private ArrayList<MoviesItem> moviesData = new ArrayList<>();


    public void setData(ArrayList<MoviesItem> items) {
        moviesData.clear();
        moviesData.addAll(items);
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public MoviesViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_movies, viewGroup, false);
        return new MoviesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MoviesViewHolder holder, final int position) {

        holder.bind(moviesData.get(position));

        holder.btnDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final MoviesItem movie = moviesData.get(position);
                movie.setTitleMovies(movie.getTitleMovies());
                movie.setReleaseDate(movie.getReleaseDate());
                movie.setRating(movie.getRating());
                movie.setImgMovies(movie.getImgMovies());
                movie.setVoteCount(movie.getVoteCount());
                movie.setLanguange(movie.getLanguange());
                movie.setPopularity(movie.getPopularity());
                movie.setDescription(movie.getDescription());

                Intent moveMoviesDetail = new Intent(v.getContext(), MoviesDetail.class);
                moveMoviesDetail.putExtra(MoviesDetail.EXTRA_MOVIE, movie);
                v.getContext().startActivity(moveMoviesDetail);

            }
        });


    }

    @Override
    public int getItemCount() {
        return  moviesData.size();
    }

    class MoviesViewHolder extends RecyclerView.ViewHolder {
        ImageView imgMovies;
        TextView tvTitle, tvDescription, tvRating, tvReleaseDate;
        Button btnDetail;

        MoviesViewHolder(@NonNull View itemView) {
            super(itemView);
            imgMovies = itemView.findViewById(R.id.img_movies);
            tvTitle = itemView.findViewById(R.id.tv_title_movies);
            tvReleaseDate = itemView.findViewById(R.id.tv_release_date_movies);
            tvRating = itemView.findViewById(R.id.tv_rating_movies);
            tvDescription = itemView.findViewById(R.id.tv_description_movies);
            btnDetail = itemView.findViewById(R.id.btn_detail_movies);
        }

        void bind(MoviesItem movie) {
            Glide.with(itemView.getContext())
                    .load(movie.getImgMovies())
                    .apply(new RequestOptions().override(350, 550))
                    .into(imgMovies);
            tvTitle.setText(movie.getTitleMovies());
            tvReleaseDate.setText(movie.getReleaseDate());
            tvRating.setText(movie.getRating());
            tvDescription.setText(movie.getDescription());

        }
    }
}
