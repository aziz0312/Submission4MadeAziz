package com.example.submission4madeaziz.helper;

import android.database.Cursor;

import com.example.submission4madeaziz.db.DatabaseContract;

import com.example.submission4madeaziz.item.TvShowItem;

import java.util.ArrayList;

public class TvShowMappingHelper {

    public static ArrayList<TvShowItem> mapCursorToArrayList(Cursor TvShowItemsCursor) {
        ArrayList<TvShowItem> TvShowItemsList = new ArrayList<>();
        while (TvShowItemsCursor.moveToNext()) {
            int id = TvShowItemsCursor.getInt(TvShowItemsCursor.getColumnIndexOrThrow(DatabaseContract.TvColoumns._ID));
            String title = TvShowItemsCursor.getString(TvShowItemsCursor.getColumnIndexOrThrow(DatabaseContract.TvColoumns.TITLE_TVS));
            String description = TvShowItemsCursor.getString(TvShowItemsCursor.getColumnIndexOrThrow(DatabaseContract.TvColoumns.DESCRIPTION_TVS));
            String image = TvShowItemsCursor.getString(TvShowItemsCursor.getColumnIndexOrThrow(DatabaseContract.TvColoumns.PHOTO_TVS));
            String release_date = TvShowItemsCursor.getString(TvShowItemsCursor.getColumnIndexOrThrow(DatabaseContract.TvColoumns.RELEASE_DATE_TVS));
            String rating = TvShowItemsCursor.getString(TvShowItemsCursor.getColumnIndexOrThrow(DatabaseContract.TvColoumns.RATING_TVS));
            String vote = TvShowItemsCursor.getString(TvShowItemsCursor.getColumnIndexOrThrow(DatabaseContract.TvColoumns.VOTE_COUNT_TVS));
            String languange = TvShowItemsCursor.getString(TvShowItemsCursor.getColumnIndexOrThrow(DatabaseContract.TvColoumns.LANGUANGE_TVS));
            String popularity = TvShowItemsCursor.getString(TvShowItemsCursor.getColumnIndexOrThrow(DatabaseContract.TvColoumns.POPULARITY_TVS));
            TvShowItemsList.add(new TvShowItem(id, title, description, image, release_date, rating, vote, languange, popularity));
        }
        return TvShowItemsList;
    }
}
