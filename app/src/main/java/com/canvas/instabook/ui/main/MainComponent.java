package com.canvas.instabook.ui.main;

import com.canvas.instabook.app.AppComponent;
import com.canvas.instabook.ui.coverflow.CoverFlowPresenterModule;
import com.canvas.instabook.util.FragmentScoped;

import dagger.Component;

/**
 * Created by Krishna Chaitanya Kandula on 7/4/17.
 */
@FragmentScoped
@Component(dependencies = AppComponent.class, modules = {MainPresenterModule.class, CoverFlowPresenterModule.class})
public interface MainComponent {
    void inject(MainActivity target);
}
