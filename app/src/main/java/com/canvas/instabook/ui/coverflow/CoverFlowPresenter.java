package com.canvas.instabook.ui.coverflow;

import android.support.annotation.NonNull;

import com.canvas.instabook.app.Constants;
import com.canvas.instabook.data.models.Book;
import com.canvas.instabook.data.models.Books;
import com.canvas.instabook.data.source.BookRepositoryContract;

import javax.inject.Inject;

/**
 * Created by Krishna Chaitanya Kandula on 7/2/17.
 */

public class CoverFlowPresenter implements CoverFlowContract.Presenter {

    @NonNull
    private final BookRepositoryContract bookRepository;

    @NonNull
    private final CoverFlowContract.View view;

    @Inject
    public CoverFlowPresenter(@NonNull BookRepositoryContract bookRepository, @NonNull CoverFlowContract.View view) {
        this.bookRepository = bookRepository;
        this.view = view;
    }

    @Override
    public void start() {
        getBookList(0);
    }

    @Inject
    @Override
    public void setupListeners() {
        view.setPresenter(this);
    }

    @Override
    public void getBookList(int offset) {
        bookRepository.getBooks(Constants.BOOKS_LIMIT, offset, new BookRepositoryContract.LoadBooksCallback() {
            @Override
            public void onBooksLoaded(Books books) {
                view.showCoverGrid(books.getBooks());
            }

            @Override
            public void onDataNotAvailable() {
                //Show view error screen
            }
        });
    }

    @Override
    public void onCoverClickedListener(@NonNull Book book) {
        view.showBookView(book);
    }
}
