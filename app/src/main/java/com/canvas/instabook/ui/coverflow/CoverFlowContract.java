package com.canvas.instabook.ui.coverflow;


import com.canvas.instabook.data.models.Book;
import com.canvas.instabook.ui.BasePresenter;

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
        void showBookView(@NonNull Book book);
    }

    interface Presenter extends BasePresenter<CoverFlowContract.View> {
        void getData(int offset, boolean refresh);
    }
}
