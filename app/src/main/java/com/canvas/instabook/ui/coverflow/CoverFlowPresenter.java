package com.canvas.instabook.ui.coverflow;

import android.util.Log;

import com.canvas.instabook.app.Constants;
import com.canvas.instabook.data.models.Book;
import com.canvas.instabook.data.models.Books;
import com.canvas.instabook.data.source.BookRepositoryContract;
import com.canvas.instabook.ui.ViewState;

import lombok.NonNull;

/**
 * Created by Krishna Chaitanya Kandula on 7/2/17.
 */

public class CoverFlowPresenter implements CoverFlowContract.Presenter {

    private final CoverFlowContract.View view;

    private final BookRepositoryContract bookRepository;

    private ViewState viewState;

    private static final String LOG_TAG = CoverFlowPresenter.class.getSimpleName();

    public CoverFlowPresenter(@NonNull CoverFlowContract.View view, @NonNull BookRepositoryContract bookRepository) {
        this.view = view;
        this.bookRepository = bookRepository;
        this.viewState = ViewState.SHOW_LOADING;
    }

    @Override
    public void start() {
        if(viewState == ViewState.SHOW_LOADING) {
            getData(0, false);
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
                viewState = ViewState.SHOW_CONTENT;
            }

            @Override
            public void onDataNotAvailable() {
                //Show view error screen
                viewState = ViewState.SHOW_ERROR;
            }
        });
    }

    public void onCoverClickedListener(@NonNull Book book) {
        view.showBookView(book);
    }
}
