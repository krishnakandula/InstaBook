package com.canvas.instabook.ui.coverflow;

import com.canvas.instabook.data.source.BookRepositoryContract;
import com.canvas.instabook.util.FragmentScoped;

import dagger.Module;
import dagger.Provides;
import lombok.NonNull;

/**
 * Created by Krishna Chaitanya Kandula on 7/4/17.
 */
@Module
public class CoverFlowPresenterModule {

    private final CoverFlowContract.View view;

    public CoverFlowPresenterModule(@NonNull CoverFlowContract.View view) {
        this.view = view;
    }

    @Provides
    @FragmentScoped
    public CoverFlowContract.View provideCoverFlowView() {
        return this.view;
    }

    @Provides
    @FragmentScoped
    public CoverFlowContract.Presenter provideCoverFlowContractPresenter(CoverFlowPresenter presenter) {
        return presenter;
    }

    @Provides
    @FragmentScoped
    public CoverFlowPresenter provideCoverFlowPresenter(@NonNull BookRepositoryContract bookRepository) {
        return new CoverFlowPresenter(this.view, bookRepository);
    }
 }
