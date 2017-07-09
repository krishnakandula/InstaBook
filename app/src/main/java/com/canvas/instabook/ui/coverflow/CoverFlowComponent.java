package com.canvas.instabook.ui.coverflow;

import com.canvas.instabook.app.AppComponent;
import com.canvas.instabook.util.FragmentScoped;

import dagger.Component;

/**
 * Created by Krishna Chaitanya Kandula on 7/8/17.
 */
@FragmentScoped
@Component(modules = CoverFlowModule.class, dependencies = AppComponent.class)
public interface CoverFlowComponent {
    void inject(CoverFlowFragment target);
}
