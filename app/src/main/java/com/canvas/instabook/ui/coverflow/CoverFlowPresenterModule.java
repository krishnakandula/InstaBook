package com.canvas.instabook.ui.coverflow;

import com.canvas.instabook.data.source.BookRepository;
import com.canvas.instabook.data.source.BookRepositoryContract;

import dagger.Module;
import dagger.Provides;
import lombok.NonNull;
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
    public CoverFlowContract.View provideView() {
        return mView;
    }

    @Provides
    public CoverFlowContract.Presenter provideCoverFlowPresenter(CoverFlowContract.View view, BookRepository bookRepository) {
        return new CoverFlowPresenter(view);
    }
 }
