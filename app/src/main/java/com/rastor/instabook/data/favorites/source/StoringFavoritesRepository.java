package com.rastor.instabook.data.favorites.source;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

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

    private static final String LOG_TAG = StoringFavoritesRepository.class.getSimpleName();

    @Inject
    public StoringFavoritesRepository(@NonNull SQLiteDatabase database) {
        this.database = database;
    }

    @Override
    public void addFavorite(@NonNull Favorite favorite) {
        try {
            database.insert(FavoriteTable.NAME, null, getContentValues(favorite));
        } catch (SQLiteConstraintException e) {
            Log.e(LOG_TAG, e.getMessage(), e);
        }
    }

    private ContentValues getContentValues(@NonNull Favorite favorite) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(FavoriteTable.Cols.BOOK_ID, favorite.getBookId());

        return contentValues;
    }

    @Override
    public void getFavorites(int offset, @NonNull LoadFavoritesCallback callback) {
        Cursor cursor = database.rawQuery("select * from " + FavoriteTable.NAME, null);

        FavoritesCursorWrapper cursorWrapper = new FavoritesCursorWrapper(cursor);
        List<Favorite> favorites = Lists.newArrayList();

        cursor.moveToFirst();
        cursor.move(offset);
        while (!cursor.isAfterLast()) {
            favorites.add(cursorWrapper.getFavorite());
            cursor.moveToNext();
        }
        cursorWrapper.close();

        callback.onFavoritesLoaded(favorites);
    }

    @Override
    public void isFavorited(@NonNull String bookId, @NonNull IsFavoritedCallback callback) {
        Cursor cursor = database.rawQuery("select * from " + FavoriteTable.NAME +
                " where " + FavoriteTable.Cols.BOOK_ID + " = '" + bookId + "'", null);

        boolean isFavorited = cursor.getCount() > 0;
        callback.onSuccess(isFavorited);
    }

    @Override
    public void deleteFavorite(@NonNull String bookId) {
        database.execSQL("delete from " + FavoriteTable.NAME + " where " + FavoriteTable.Cols.BOOK_ID + " = '" + bookId + "'");
    }
}
