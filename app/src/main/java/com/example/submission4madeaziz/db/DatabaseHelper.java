package com.example.submission4madeaziz.db;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.submission4madeaziz.db.DatabaseContract.MovieColoumns;
import com.example.submission4madeaziz.db.DatabaseContract.TvColoumns;

import static com.example.submission4madeaziz.db.DatabaseContract.TABLE_MOVIE;
import static com.example.submission4madeaziz.db.DatabaseContract.TABLE_TVSHOW;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static String DB_NAME = "dbfavorit";

    private static final int DB_VERSION = 1;

    DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    private static final String SQL_CREATE_TABLE_MOVIE = String.format("CREATE TABLE %s"
                    +"(%s INTEGER PRIMARY KEY AUTOINCREMENT,"+
                    "%s TEXT NOT NULL UNIQUE,"+
                    "%s TEXT NOT NULL ,"+
                    "%s TEXT NOT NULL ,"+
                    "%s TEXT NOT NULL ,"+
                    "%s TEXT NOT NULL ,"+
                    "%s TEXT NOT NULL ,"+
                    "%s TEXT NOT NULL ,"+
                    "%s TEXT NOT NULL )",
            TABLE_MOVIE,
            MovieColoumns._ID,
            MovieColoumns.TITLE_MV,
            MovieColoumns.DESCRIPTION_MV,
            MovieColoumns.PHOTO_MV,
            MovieColoumns.RELEASE_DATE_MV,
            MovieColoumns.RATING_MV,
            MovieColoumns.VOTE_COUNT_MV,
            MovieColoumns.LANGUANGE_MV,
            MovieColoumns.POPULARITY_MV
    );

    private static final String SQL_CREATE_TABLE_TVS= String.format("CREATE TABLE %s"
                    +"(%s INTEGER PRIMARY KEY AUTOINCREMENT,"+
                    "%s TEXT NOT NULL UNIQUE,"+
                    "%s TEXT NOT NULL ,"+
                    "%s TEXT NOT NULL ,"+
                    "%s TEXT NOT NULL ,"+
                    "%s TEXT NOT NULL ,"+
                    "%s TEXT NOT NULL ,"+
                    "%s TEXT NOT NULL ,"+
                    "%s TEXT NOT NULL )",
            TABLE_TVSHOW,
            TvColoumns._ID,
            TvColoumns.TITLE_TVS,
            TvColoumns.DESCRIPTION_TVS,
            TvColoumns.PHOTO_TVS,
            TvColoumns.RELEASE_DATE_TVS,
            TvColoumns.RATING_TVS,
            TvColoumns.VOTE_COUNT_TVS,
            TvColoumns.LANGUANGE_TVS,
            TvColoumns.POPULARITY_TVS
    );

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE_MOVIE);
        db.execSQL(SQL_CREATE_TABLE_TVS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_MOVIE);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_TVSHOW);

        onCreate(db);
    }
}
