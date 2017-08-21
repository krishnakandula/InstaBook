package com.rastor.instabook.ui.coverflow;

import com.rastor.instabook.data.books.source.BookRepository;
import com.rastor.instabook.util.FragmentScoped;

import dagger.Module;
import dagger.Provides;
import lombok.NonNull;

/**
 * Created by Krishna Chaitanya Kandula on 7/4/17.
 */
@Module
public class CoverFlowModule {

    private final CoverFlowContract.View view;

    public CoverFlowModule(@NonNull CoverFlowContract.View view) {
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
    public CoverFlowPresenter provideCoverFlowPresenter(@NonNull BookRepository bookRepository) {
        return new CoverFlowPresenter(this.view, bookRepository);
    }
 }
