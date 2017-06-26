package com.canvas.instabook.modules;

import com.canvas.instabook.MainActivity;

import dagger.Component;

/**
 * Created by Krishna Chaitanya Kandula on 6/4/17.
 */
@Component(modules = {AppModule.class})
public interface AppComponent {
    void inject(MainActivity target);
}
