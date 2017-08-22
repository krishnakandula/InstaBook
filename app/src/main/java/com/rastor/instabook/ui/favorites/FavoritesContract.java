package com.rastor.instabook.ui.favorites;

import com.rastor.instabook.data.books.models.Book;

import java.util.List;

/**
 * Created by Krishna Chaitanya Kandula on 7/23/17.
 */

public interface FavoritesContract {
    interface View {
        void setData(List<Book> data);
        List<Book> getExistingData();
        void updateData(List<Book> additionalData);
        void showBookInfoView(String bookId);
        void showLoading();
        void stopLoading();
        void showErrorMessage();
    }

    interface Presenter {
        void start();
        void getData(int offset);
        void onRefresh();
        void onFavoriteClicked(int position);
    }
}
