package com.canvas.instabook.ui.coverflow;

import android.support.annotation.NonNull;

import com.canvas.instabook.data.source.BookRepositoryContract;
import com.canvas.instabook.util.FragmentScoped;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Krishna Chaitanya Kandula on 7/4/17.
 */
@Module
public class CoverFlowPresenterModule {

    @Provides
    @FragmentScoped
    public CoverFlowContract.Presenter provideCoverFlowContractPresenter(CoverFlowPresenter presenter) {
        return presenter;
    }

    @Provides
    @FragmentScoped
    public CoverFlowPresenter provideCoverFlowPresenter(@NonNull BookRepositoryContract bookRepository) {
        return new CoverFlowPresenter(bookRepository);
    }
 }
