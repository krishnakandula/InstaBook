package com.canvas.instabook.ui.main;

import lombok.NonNull;

/**
 * Created by Krishna Chaitanya Kandula on 7/1/17.
 */

public class MainPresenter implements MainContract.Presenter {
    @NonNull
    private MainContract.View view;

    @Override
    public void initialize(@NonNull MainContract.View view) {
        this.view = view;
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
