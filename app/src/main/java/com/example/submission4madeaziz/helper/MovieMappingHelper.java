package com.example.submission4madeaziz.helper;

import android.database.Cursor;

import com.example.submission4madeaziz.db.DatabaseContract;
import com.example.submission4madeaziz.item.MoviesItem;

import java.util.ArrayList;

public class MovieMappingHelper {

    public static ArrayList<MoviesItem> mapCursorToArrayList(Cursor MoviesItemsCursor) {
        ArrayList<MoviesItem> MoviesItemsList = new ArrayList<>();
        while (MoviesItemsCursor.moveToNext()) {
            int id = MoviesItemsCursor.getInt(MoviesItemsCursor.getColumnIndexOrThrow(DatabaseContract.MovieColoumns._ID));
            String title = MoviesItemsCursor.getString(MoviesItemsCursor.getColumnIndexOrThrow(DatabaseContract.MovieColoumns.TITLE_MV));
            String description = MoviesItemsCursor.getString(MoviesItemsCursor.getColumnIndexOrThrow(DatabaseContract.MovieColoumns.DESCRIPTION_MV));
            String image = MoviesItemsCursor.getString(MoviesItemsCursor.getColumnIndexOrThrow(DatabaseContract.MovieColoumns.PHOTO_MV));
            String release_date = MoviesItemsCursor.getString(MoviesItemsCursor.getColumnIndexOrThrow(DatabaseContract.MovieColoumns.RELEASE_DATE_MV));
            String rating = MoviesItemsCursor.getString(MoviesItemsCursor.getColumnIndexOrThrow(DatabaseContract.MovieColoumns.RATING_MV));
            String vote = MoviesItemsCursor.getString(MoviesItemsCursor.getColumnIndexOrThrow(DatabaseContract.MovieColoumns.VOTE_COUNT_MV));
            String languange = MoviesItemsCursor.getString(MoviesItemsCursor.getColumnIndexOrThrow(DatabaseContract.MovieColoumns.LANGUANGE_MV));
            String popularity = MoviesItemsCursor.getString(MoviesItemsCursor.getColumnIndexOrThrow(DatabaseContract.MovieColoumns.POPULARITY_MV));
            MoviesItemsList.add(new MoviesItem(id, title, description,image, release_date, rating, vote, languange, popularity));
        }
        return MoviesItemsList;
    }
    
}
