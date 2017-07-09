package com.canvas.instabook.ui.information;

import com.canvas.instabook.app.AppComponent;
import com.canvas.instabook.util.FragmentScoped;

import dagger.Component;

/**
 * Created by Krishna Chaitanya Kandula on 7/8/17.
 */
@FragmentScoped
@Component(modules = BookInformationModule.class, dependencies = AppComponent.class)
public interface BookInformationComponent {
    void inject(BookInformationFragment target);
}
