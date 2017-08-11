package com.rastor.instabook.data.favorites.source;

import com.rastor.instabook.data.favorites.models.Favorite;

import java.util.List;

/**
 * Created by Krishna Chaitanya Kandula on 7/23/17.
 */

public interface FavoritesRepository {

    interface LoadFavoritesCallback {
        void onFavoritesLoaded(List<Favorite> favorites);

        void onFavoritesNotAvailable();
    }

    interface IsFavoritedCallback {
        void onSuccess(boolean isFavorited);
    }

    void addFavorite(Favorite favorite);

    void deleteFavorite(String bookId);

    void getFavorites(int offset, LoadFavoritesCallback callback);

    void isFavorited(String bookId, IsFavoritedCallback callback);
}

