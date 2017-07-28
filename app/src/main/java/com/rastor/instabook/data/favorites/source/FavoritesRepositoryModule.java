package com.rastor.instabook.data.favorites.source;

import android.database.sqlite.SQLiteDatabase;

import com.rastor.instabook.data.books.source.BookRepository;
import com.rastor.instabook.data.favorites.source.database.FavoritesBaseHelper;
import com.rastor.instabook.util.ApplicationScoped;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import lombok.NonNull;

/**
 * Created by Krishna Chaitanya Kandula on 7/23/17.
 */

@Module
public abstract class FavoritesRepositoryModule {

    @Provides
    @ApplicationScoped
    public static SQLiteDatabase provideFavoritesDatabase(@NonNull FavoritesBaseHelper baseHelper) {
        return baseHelper.getWritableDatabase();
    }

    @Binds
    @ApplicationScoped
    public abstract FavoritesRepository provideFavoritesRepository(@NonNull StoringFavoritesRepository repository);
}
