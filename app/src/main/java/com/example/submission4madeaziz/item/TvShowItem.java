package com.example.submission4madeaziz.item;

import android.os.Parcel;
import android.os.Parcelable;

public class TvShowItem implements Parcelable {


    private int id;

    private String imgTvs;
    private String titleTvs;
    private String releaseDateTvs;
    private String descriptionTvs;
    private String ratingTvs;
    private String popularityTvs;
    private String languangeTvs;
    private String voteCountTvs;

    public TvShowItem(int id, String title, String description, String image, String release_date, String rating, String vote, String languange, String popularity) {

        this.id = id;
        this.titleTvs = title;
        this.descriptionTvs = description;
        this.imgTvs = image;
        this.releaseDateTvs = release_date;
        this.ratingTvs = rating;
        this.voteCountTvs = vote;
        this.languangeTvs = languange;
        this.popularityTvs = popularity;
    }

    public String getImgTvs() {
        return imgTvs;
    }

    public void setImgTvs(String imgTvs) {
        this.imgTvs = imgTvs;
    }

    public String getTitleTvs() {
        return titleTvs;
    }

    public void setTitleTvs(String titleTvs) {
        this.titleTvs = titleTvs;
    }

    public String getReleaseDateTvs() {
        return releaseDateTvs;
    }

    public void setReleaseDateTvs(String releaseDateTvs) {
        this.releaseDateTvs = releaseDateTvs;
    }

    public String getDescriptionTvs() {
        return descriptionTvs;
    }

    public void setDescriptionTvs(String descriptionTvs) {
        this.descriptionTvs = descriptionTvs;
    }

    public String getRatingTvs() {
        return ratingTvs;
    }

    public void setRatingTvs(String ratingTvs) {
        this.ratingTvs = ratingTvs;
    }

    public String getPopularityTvs() {
        return popularityTvs;
    }

    public void setPopularityTvs(String popularityTvs) {
        this.popularityTvs = popularityTvs;
    }

    public String getLanguangeTvs() {
        return languangeTvs;
    }

    public void setLanguangeTvs(String languangeTvs) {
        this.languangeTvs = languangeTvs;
    }

    public String getVoteCountTvs() {
        return voteCountTvs;
    }

    public void setVoteCountTvs(String voteCountTvs) {
        this.voteCountTvs = voteCountTvs;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TvShowItem() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.imgTvs);
        dest.writeString(this.titleTvs);
        dest.writeString(this.releaseDateTvs);
        dest.writeString(this.descriptionTvs);
        dest.writeString(this.ratingTvs);
        dest.writeString(this.popularityTvs);
        dest.writeString(this.languangeTvs);
        dest.writeString(this.voteCountTvs);
    }

    private TvShowItem(Parcel in) {
        this.id = in.readInt();
        this.imgTvs = in.readString();
        this.titleTvs = in.readString();
        this.releaseDateTvs = in.readString();
        this.descriptionTvs = in.readString();
        this.ratingTvs = in.readString();
        this.popularityTvs = in.readString();
        this.languangeTvs = in.readString();
        this.voteCountTvs = in.readString();
    }

    public static final Creator<TvShowItem> CREATOR = new Creator<TvShowItem>() {
        @Override
        public TvShowItem createFromParcel(Parcel source) {
            return new TvShowItem(source);
        }

        @Override
        public TvShowItem[] newArray(int size) {
            return new TvShowItem[size];
        }
    };
}
