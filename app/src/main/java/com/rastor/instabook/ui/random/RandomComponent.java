package com.rastor.instabook.ui.random;

import com.rastor.instabook.app.AppComponent;
import com.rastor.instabook.util.FragmentScoped;

import dagger.Component;

/**
 * Created by Krishna Chaitanya Kandula on 7/14/17.
 */
@FragmentScoped
@Component(dependencies = AppComponent.class, modules = RandomModule.class)
public interface RandomComponent {
    void inject(RandomMainFragment target);
}
