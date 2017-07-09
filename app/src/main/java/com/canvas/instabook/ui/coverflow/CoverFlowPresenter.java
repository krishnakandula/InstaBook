package com.canvas.instabook.ui.coverflow;

import android.util.Log;

import com.canvas.instabook.app.Constants;
import com.canvas.instabook.data.models.Book;
import com.canvas.instabook.data.models.Books;
import com.canvas.instabook.data.source.BookRepositoryContract;
import com.google.common.collect.Lists;

import java.util.List;

import lombok.NonNull;

/**
 * Created by Krishna Chaitanya Kandula on 7/2/17.
 */

public class CoverFlowPresenter implements CoverFlowContract.Presenter {

    @NonNull
    private CoverFlowContract.View view;

    @NonNull
    private final BookRepositoryContract bookRepository;

    private int state = 0;

    private static final String LOG_TAG = CoverFlowPresenter.class.getSimpleName();

    public CoverFlowPresenter(@NonNull BookRepositoryContract bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public void setView(@NonNull CoverFlowContract.View view) {
        this.view = view;
    }

    @Override
    public void start() {
        if(state == 0) {
            getData(0, false);
            state = 1;
        } else {
            view.setData(view.getExistingData());
        }
    }

    @Override
    public void getData(int offset, boolean refresh) {
        Log.i(LOG_TAG, "Getting book list");
        view.showLoading();
        bookRepository.getBooks(Constants.BOOKS_LIMIT, offset, new BookRepositoryContract.LoadBooksCallback() {
            @Override
            public void onBooksLoaded(Books books) {
                view.setData(books.getBooks());
                view.stopLoading();
            }

            @Override
            public void onDataNotAvailable() {
                //Show view error screen
            }
        });
    }

    public void onCoverClickedListener(@NonNull Book book) {
        view.showBookView(book);
    }
}
