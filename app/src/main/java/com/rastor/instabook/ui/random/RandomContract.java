package com.rastor.instabook.ui.random;

/**
 * Created by Krishna Chaitanya Kandula on 8/23/2017.
 */

public interface RandomContract {
    interface View {
        void showLoading();
        void stopLoading();
        void showRandomPageText(String pageText);
        void launchBookInformationView(String bookId);
    }

    interface Presenter {
        void start();
        void getRandomBook();
        void onShowBookClicked();
        void onRefresh();
    }
}
