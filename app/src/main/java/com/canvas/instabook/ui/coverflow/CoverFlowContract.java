package com.canvas.instabook.ui.coverflow;

import android.support.annotation.NonNull;

import com.canvas.instabook.data.models.Book;
import com.canvas.instabook.ui.BasePresenter;

import java.util.List;

/**
 * Created by Krishna Chaitanya Kandula on 7/2/17.
 */

public interface CoverFlowContract {
    interface View {
        void showCoverGrid(@NonNull List<Book> books);
        void updateCoverGrid(@NonNull List<Book> additionalBooks);
        void showBookView(@NonNull Book book);
    }

    interface Presenter extends BasePresenter<CoverFlowContract.View> {
        void getBookList(int offset);
        void onCoverClickedListener(@NonNull Book book);
    }
}
