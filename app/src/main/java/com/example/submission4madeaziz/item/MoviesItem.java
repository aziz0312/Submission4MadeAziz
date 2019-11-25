package com.example.submission4madeaziz.item;

import android.os.Parcel;
import android.os.Parcelable;

public class MoviesItem implements Parcelable {

    private int id;
    private String titleMovies;
    private String description;
    private String imgMovies;
    private String releaseDate;
    private String rating;
    private String voteCount;
    private String languange;
    private String popularity;

    public MoviesItem(int id, String title, String description, String image, String release_date, String rating, String vote, String languange, String popularity) {
        this.id = id;
        this.titleMovies = title;
        this.description = description;
        this.imgMovies = image;
        this.releaseDate = release_date;
        this.rating = rating;
        this.voteCount = vote;
        this.languange = languange;
        this.popularity = popularity;
    }

    public String getImgMovies() {
        return imgMovies;
    }

    public void setImgMovies(String imgMovies) {
        this.imgMovies = imgMovies;
    }

    public String getTitleMovies() {
        return titleMovies;
    }

    public void setTitleMovies(String titleMovies) {
        this.titleMovies = titleMovies;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(String voteCount) {
        this.voteCount = voteCount;
    }

    public String getLanguange() {
        return languange;
    }

    public void setLanguange(String languange) {
        this.languange = languange;
    }

    public String getPopularity() {
        return popularity;
    }

    public void setPopularity(String popularity) {
        this.popularity = popularity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public MoviesItem() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.imgMovies);
        dest.writeString(this.titleMovies);
        dest.writeString(this.releaseDate);
        dest.writeString(this.description);
        dest.writeString(this.rating);
        dest.writeString(this.voteCount);
        dest.writeString(this.languange);
        dest.writeString(this.popularity);
    }

    private MoviesItem(Parcel in) {
        this.id = in.readInt();
        this.imgMovies = in.readString();
        this.titleMovies = in.readString();
        this.releaseDate = in.readString();
        this.description = in.readString();
        this.rating = in.readString();
        this.voteCount = in.readString();
        this.languange = in.readString();
        this.popularity = in.readString();
    }

    public static final Creator<MoviesItem> CREATOR = new Creator<MoviesItem>() {
        @Override
        public MoviesItem createFromParcel(Parcel source) {
            return new MoviesItem(source);
        }

        @Override
        public MoviesItem[] newArray(int size) {
            return new MoviesItem[size];
        }
    };
}
