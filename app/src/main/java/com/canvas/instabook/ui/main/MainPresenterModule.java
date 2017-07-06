package com.canvas.instabook.ui.main;

import android.support.annotation.NonNull;

import com.canvas.instabook.util.FragmentScoped;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Krishna Chaitanya Kandula on 7/4/17.
 */
@Module
public class MainPresenterModule {

    @NonNull
    private final MainContract.View mView;

    public MainPresenterModule(@NonNull MainContract.View mView) {
        this.mView = mView;
    }

    @Provides
    public MainContract.Presenter provideMainPresenter(MainPresenter mainPresenter) {
        return mainPresenter;
    }

    @Provides
    @FragmentScoped
    public MainContract.View provideMainView() {
        return mView;
    }
}
