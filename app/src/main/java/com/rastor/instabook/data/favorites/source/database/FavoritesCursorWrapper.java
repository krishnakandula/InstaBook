package com.rastor.instabook.data.favorites.source.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.rastor.instabook.data.favorites.models.Favorite;

import static com.rastor.instabook.data.favorites.source.database.FavoritesSchema.*;

/**
 * Created by Krishna Chaitanya Kandula on 7/23/17.
 */

public class FavoritesCursorWrapper extends CursorWrapper {

    public FavoritesCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Favorite getFavorite() {
        final String bookId = getString(getColumnIndex(FavoriteTable.Cols.BOOK_ID));

        return new Favorite(bookId);
    }
}
