package com.canvas.instabook.ui.main;


/**
 * Created by Krishna Chaitanya Kandula on 7/1/17.
 */

public interface MainContract {
    interface View {
        void launchCoverFlowView();
        void launchBookView();
        void launchFavoritesView();
    }

    interface Presenter {
        void start();
        void onCoverFlowNavItemClicked();
        void onRandomBookNavItemClicked();
        void onFavoritesNavItemClicked();
    }
}
