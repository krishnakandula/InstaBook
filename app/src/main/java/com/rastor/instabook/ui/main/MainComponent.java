package com.rastor.instabook.ui.main;

import com.rastor.instabook.app.AppComponent;
import com.rastor.instabook.util.FragmentScoped;

import dagger.Component;

/**
 * Created by Krishna Chaitanya Kandula on 7/4/17.
 */
@FragmentScoped
@Component(dependencies = AppComponent.class, modules = MainModule.class)
public interface MainComponent {
    void inject(MainActivity target);
}
