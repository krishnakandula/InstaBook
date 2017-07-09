package com.canvas.instabook.ui.main;

import com.canvas.instabook.ui.BasePresenter;

/**
 * Created by Krishna Chaitanya Kandula on 7/1/17.
 */

public interface MainContract {
    interface View {
        void launchCoverFlowView();
        void launchBookView();
        void launchFavoritesView();
    }

    interface Presenter extends BasePresenter{
        void onCoverFlowNavItemClicked();
        void onRandomBookNavItemClicked();
        void onFavoritesNavItemClicked();
    }
}
