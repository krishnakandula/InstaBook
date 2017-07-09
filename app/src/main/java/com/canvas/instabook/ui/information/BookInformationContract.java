package com.canvas.instabook.ui.information;

import com.canvas.instabook.data.models.Book;

import lombok.NonNull;

/**
 * Created by Krishna Chaitanya Kandula on 7/8/17.
 */

public interface BookInformationContract {
    interface View {
        void showBookInformation(@NonNull Book book);
        void showSamplePage();
        void showLoading();
        void stopLoading();
        void showError(String message);
    }

    interface Presenter {
        void start();
        void getBook();
        void onViewSamplePage();
        void onFavorited();
    }
}
