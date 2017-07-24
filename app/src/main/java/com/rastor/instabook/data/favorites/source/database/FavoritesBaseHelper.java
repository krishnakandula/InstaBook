package com.rastor.instabook.data.favorites.source.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.rastor.instabook.util.ApplicationScoped;

import javax.inject.Inject;

import lombok.NonNull;

import static com.rastor.instabook.data.favorites.source.database.FavoritesSchema.*;

/**
 * Created by Krishna Chaitanya Kandula on 7/23/17.
 */

@ApplicationScoped
public class FavoritesBaseHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "favorites_db";

    public static final int DB_VERSION = 1;

    @Inject
    public FavoritesBaseHelper(@NonNull Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String createStatement = "create table " + FavoriteTable.NAME + "("
                                        + FavoriteTable.Cols.BOOK_ID + "_id STRING primary key)";
        db.execSQL(createStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //TODO: Add drop table or some type of upgrade
    }
}
