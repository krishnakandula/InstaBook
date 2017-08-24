package com.rastor.instabook.ui.random;

import com.rastor.instabook.util.FragmentScoped;

import dagger.Module;
import dagger.Provides;
import lombok.NonNull;

/**
 * Created by Krishna Chaitanya Kandula on 8/23/2017.
 */

@Module
public class RandomModule {

    private final RandomContract.View view;

    public RandomModule(@NonNull RandomContract.View view) {
        this.view = view;
    }

    @Provides
    @FragmentScoped
    public RandomContract.View provideRandomView() {
        return this.view;
    }

    @Provides
    @FragmentScoped
    public RandomContract.Presenter provideRandomPresenter(RandomPresenter presenter) {
        return presenter;
    }
}
