package com.rastor.instabook.ui.main;


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
        void start(ViewState previousState);
        void onCoverFlowNavItemClicked();
        void onRandomBookNavItemClicked();
        void onFavoritesNavItemClicked();
        ViewState getState();
    }

    enum ViewState {
        SHOW_COVERFLOW,
        SHOW_RANDOM_BOOK,
        SHOW_FAVORITES
    }
}
