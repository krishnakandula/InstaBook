package com.canvas.instabook.modules;

import com.canvas.instabook.ui.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Krishna Chaitanya Kandula on 6/4/17.
 */
@Singleton
@Component(modules = {AppModule.class, NetworkModule.class})
public interface AppComponent {
    void inject(MainActivity target);
}
