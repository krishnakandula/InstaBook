package com.rastor.instabook.ui.favorites;

import com.rastor.instabook.util.FragmentScoped;

import dagger.Module;
import dagger.Provides;
import lombok.NonNull;

/**
 * Created by Krishna Chaitanya Kandula on 7/23/17.
 */

@Module
public class FavoritesModule {

    private final FavoritesContract.View favoritesView;

    public FavoritesModule(@NonNull FavoritesContract.View favoritesView) {
        this.favoritesView = favoritesView;
    }

    @Provides
    @FragmentScoped
    public FavoritesContract.View provideFavoritesView() {
        return this.favoritesView;
    }

    @Provides
    @FragmentScoped
    public FavoritesContract.Presenter provideFavoritesPresenter(@NonNull FavoritesPresenter presenter) {
        return presenter;
    }
}
