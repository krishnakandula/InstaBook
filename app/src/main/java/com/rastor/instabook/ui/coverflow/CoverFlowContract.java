package com.rastor.instabook.ui.coverflow;


import com.rastor.instabook.data.models.Book;

import java.util.List;

import lombok.NonNull;

/**
 * Created by Krishna Chaitanya Kandula on 7/8/17.
 */

public interface CoverFlowContract {
    interface View {
        void showLoading();
        void stopLoading();
        void setData(@NonNull List<Book> books);
        List<Book> getExistingData();
        void updateData(@NonNull List<Book> additionalBooks);
        void showBookView(@NonNull String bookId);
        void showError(String message);
    }

    interface Presenter {
        void start();
        void getData(int offset, boolean refresh);
        void onCoverClicked(int position);
    }
}
