package com.canvas.instabook.ui.main;

import javax.inject.Inject;

import lombok.NonNull;

/**
 * Created by Krishna Chaitanya Kandula on 7/1/17.
 */
public class MainPresenter implements MainContract.Presenter {
    @NonNull
    private final MainContract.View view;

    @Inject
    public MainPresenter(MainContract.View view) {
        this.view = view;
    }

    @Override
    public void start() {
        view.launchCoverFlowView();
    }

    @Override
    public MainContract.View getView() {
        return view;
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
