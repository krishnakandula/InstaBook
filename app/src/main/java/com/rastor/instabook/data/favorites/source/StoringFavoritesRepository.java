package com.rastor.instabook.data.favorites.source;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.google.common.collect.Lists;
import com.rastor.instabook.data.favorites.models.Favorite;
import com.rastor.instabook.data.favorites.source.database.FavoritesCursorWrapper;
import com.rastor.instabook.data.favorites.source.database.FavoritesSchema;
import com.rastor.instabook.util.ApplicationScoped;

import java.util.List;

import javax.inject.Inject;

import lombok.NonNull;

import static com.rastor.instabook.data.favorites.source.database.FavoritesSchema.*;

/**
 * Created by Krishna Chaitanya Kandula on 7/23/17.
 */

@ApplicationScoped
public class StoringFavoritesRepository implements FavoritesRepository {

    @NonNull
    private final SQLiteDatabase database;

    @Inject
    public StoringFavoritesRepository(@NonNull SQLiteDatabase database) {
        this.database = database;
    }

    @Override
    public void addFavorite(@NonNull Favorite favorite) {
        database.insert(FavoriteTable.NAME, null, getContentValues(favorite));
    }

    private ContentValues getContentValues(@NonNull Favorite favorite) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(FavoriteTable.Cols.BOOK_ID, favorite.getBookId());

        return contentValues;
    }

    @Override
    public void getFavorites(@NonNull LoadFavoritesCallback callback) {
        Cursor cursor = database.query(FavoriteTable.NAME,
                null,
                null,
                null,
                null,
                null,
                null);

        FavoritesCursorWrapper cursorWrapper = new FavoritesCursorWrapper(cursor);
        List<Favorite> favorites = Lists.newArrayList();
        while(!cursor.isAfterLast()) {
            favorites.add(cursorWrapper.getFavorite());
            cursor.moveToNext();
        }
        cursorWrapper.close();

        callback.onFavoritesLoaded(favorites);
    }
}
