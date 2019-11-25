package com.example.submission4madeaziz.db;

import android.provider.BaseColumns;

public class DatabaseContract {

    static String TABLE_MOVIE = "movie";

    public static final class MovieColoumns implements BaseColumns {

       public static String TITLE_MV = "title_mv";
       public static String DESCRIPTION_MV = "overview_mv";
       public static String PHOTO_MV = "image_mv";
       public static String RELEASE_DATE_MV = "releasedate_mv";
       public static String RATING_MV = "rating_mv";
       public static String VOTE_COUNT_MV = "voteCount_mv";
       public static String LANGUANGE_MV = "languange_mv";
       public static String POPULARITY_MV = "popularity_mv";
    }

    static String TABLE_TVSHOW = "tvshow";

    public  static final class TvColoumns implements BaseColumns {

        public static String TITLE_TVS = "title_tvs";
        public static String DESCRIPTION_TVS = "overview_tvs";
        public static String PHOTO_TVS = "image_tvs";
        public static String RELEASE_DATE_TVS = "releasedate_tvs";
        public static String RATING_TVS = "rating_tvs";
        public static String VOTE_COUNT_TVS = "voteCount_tvs";
        public static String LANGUANGE_TVS = "languange_tvs";
        public static String POPULARITY_TVS = "popularity_tvs";

    }
}
