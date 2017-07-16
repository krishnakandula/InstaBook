package com.rastor.instabook.ui.coverflow;

import com.rastor.instabook.app.AppComponent;
import com.rastor.instabook.util.FragmentScoped;

import dagger.Component;

/**
 * Created by Krishna Chaitanya Kandula on 7/8/17.
 */
@FragmentScoped
@Component(modules = CoverFlowModule.class, dependencies = AppComponent.class)
public interface CoverFlowComponent {
    void inject(CoverFlowFragment target);
}
