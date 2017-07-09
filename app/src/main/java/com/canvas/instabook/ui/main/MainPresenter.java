package com.canvas.instabook.ui.main;

import android.support.annotation.NonNull;
import android.util.Log;

import javax.inject.Inject;

/**
 * Created by Krishna Chaitanya Kandula on 7/1/17.
 */
public class MainPresenter implements MainContract.Presenter {

    @NonNull
    private final MainContract.View view;

    @Inject
    public MainPresenter(@NonNull MainContract.View view) {
        this.view = view;
    }

    @Override
    public void setView(MainContract.View view) {

    }

    public void start() {
        view.launchCoverFlowView();
    }

    @Override
    public void onCoverFlowNavItemClicked() {
        view.launchCoverFlowView();
    }

    @Override
    public void onRandomBookNavItemClicked() {
        view.launchBookView();
    }

    @Override
    public void onFavoritesNavItemClicked() {
        view.launchFavoritesView();
    }
}
