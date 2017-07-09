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
            getData(0, true);
        } else {
            view.setData(view.getExistingData());
        }
    }

    @Override
    public void getData(int offset, boolean refresh) {
        this.view.showLoading();
        this.viewState = ViewState.SHOW_LOADING;

        bookRepository.getBooks(Constants.BOOKS_LIMIT, offset, new BookRepositoryContract.LoadBooksCallback() {
            @Override
            public void onBooksLoaded(Books books) {
                if(refresh) {
                    view.setData(books.getBooks());
                } else {
                    view.updateData(books.getBooks());
                }

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

    @Override
    public void onCoverClicked(int position) {
        view.showBookView(view.getExistingData().get(position));
    }
}
