package com.canvas.instabook.ui.coverflow;

import android.support.annotation.NonNull;

import com.canvas.instabook.data.source.BookRepositoryContract;

import dagger.Module;
import dagger.Provides;

import lombok.RequiredArgsConstructor;

/**
 * Created by Krishna Chaitanya Kandula on 7/4/17.
 */
@Module
@RequiredArgsConstructor
public class CoverFlowPresenterModule {
    @NonNull
    private final CoverFlowContract.View mView;

    @Provides
    public CoverFlowContract.Presenter provideCoverFlowPresenter(@NonNull BookRepositoryContract bookRepository) {
        return new CoverFlowPresenter(mView, bookRepository);
    }
 }
