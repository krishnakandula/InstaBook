package com.rastor.instabook.ui.information;

import com.rastor.instabook.data.books.models.Book;

import lombok.NonNull;

/**
 * Created by Krishna Chaitanya Kandula on 7/8/17.
 */

public interface BookInformationContract {
    interface View {
        void showBookInformation(@NonNull Book book);
        void showSamplePage(boolean immediate);
        void hideSamplePage();
        void showError(String message);
        Book getExistingData();
    }

    interface Presenter {
        void start();
        void getBook();
        void onViewSamplePage(boolean pageIsVisible);
        void onFavorited();
    }
}
