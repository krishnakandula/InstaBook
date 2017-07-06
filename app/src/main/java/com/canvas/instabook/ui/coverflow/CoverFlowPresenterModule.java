package com.canvas.instabook.ui.coverflow;

import android.support.annotation.NonNull;

import com.canvas.instabook.util.FragmentScoped;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Krishna Chaitanya Kandula on 7/4/17.
 */
@Module
public class CoverFlowPresenterModule {
    @NonNull
    private final CoverFlowContract.View mView;

    public CoverFlowPresenterModule(@NonNull CoverFlowContract.View mView) {
        this.mView = mView;
    }

    @Provides
    @FragmentScoped
    public CoverFlowContract.Presenter provideCoverFlowPresenter(CoverFlowPresenter presenter) {
        return presenter;
    }

    @Provides
    @FragmentScoped
    public CoverFlowContract.View provideCoverFlowView() {
        return mView;
    }
 }
