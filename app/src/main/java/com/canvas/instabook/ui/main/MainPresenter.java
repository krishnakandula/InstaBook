package com.canvas.instabook.ui.main;

import android.support.annotation.NonNull;

import javax.inject.Inject;

/**
 * Created by Krishna Chaitanya Kandula on 7/1/17.
 */
public class MainPresenter implements MainContract.Presenter {

    @NonNull
    private MainContract.View view;

    private ViewState state = ViewState.SHOW_COVERFLOW;

    @Inject
    public MainPresenter(@NonNull MainContract.View view) {
        this.view = view;
    }

    @Override
    public void start() {
        switch (state) {
            case SHOW_COVERFLOW:
                onCoverFlowNavItemClicked();
                break;
            case SHOW_RANDOM_BOOK:
                onRandomBookNavItemClicked();
                break;
            case SHOW_FAVORITES:
                onFavoritesNavItemClicked();
                break;
        }
    }

    @Override
    public void onCoverFlowNavItemClicked() {
        state = ViewState.SHOW_COVERFLOW;
        view.launchCoverFlowView();
    }

    @Override
    public void onRandomBookNavItemClicked() {
        state = ViewState.SHOW_RANDOM_BOOK;
        view.launchBookView();
    }

    @Override
    public void onFavoritesNavItemClicked() {
        state = ViewState.SHOW_FAVORITES;
        view.launchFavoritesView();
    }

    enum ViewState {
        SHOW_COVERFLOW,
        SHOW_RANDOM_BOOK,
        SHOW_FAVORITES
    }
}
