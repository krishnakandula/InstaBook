package com.canvas.instabook.ui.random;

import com.canvas.instabook.app.AppComponent;
import com.canvas.instabook.util.FragmentScoped;

import dagger.Component;

/**
 * Created by Krishna Chaitanya Kandula on 7/14/17.
 */
@FragmentScoped
@Component(dependencies = AppComponent.class)
public interface RandomComponent {
    void inject(RandomMainFragment target);
}
