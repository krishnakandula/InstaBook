package com.rastor.instabook.ui.favorites;

import com.rastor.instabook.app.AppComponent;
import com.rastor.instabook.util.FragmentScoped;

import dagger.Component;

/**
 * Created by Krishna Chaitanya Kandula on 7/27/17.
 */

@FragmentScoped
@Component(modules = FavoritesModule.class, dependencies = AppComponent.class)
public interface FavoritesComponent {
    void inject(FavoritesFragment target);
}
