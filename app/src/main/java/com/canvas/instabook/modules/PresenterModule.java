package com.canvas.instabook.modules;

import com.canvas.instabook.ui.main.MainContract;
import com.canvas.instabook.ui.main.MainPresenter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Krishna Chaitanya Kandula on 7/1/17.
 */
@Module
public class PresenterModule {

    @Provides
    @Singleton
    MainContract.Presenter provideMainPresenter() {
        return new MainPresenter();
    }
}
