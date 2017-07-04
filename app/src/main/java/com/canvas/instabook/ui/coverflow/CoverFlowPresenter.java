package com.canvas.instabook.ui.coverflow;

import android.support.annotation.NonNull;

import com.canvas.instabook.data.models.Book;
import com.canvas.instabook.data.models.Books;
import com.canvas.instabook.data.source.BookRepository;
import com.canvas.instabook.data.source.BookRepositoryContract;

import javax.inject.Inject;

/**
 * Created by Krishna Chaitanya Kandula on 7/2/17.
 */

public class CoverFlowPresenter implements CoverFlowContract.Presenter {

    @NonNull
    private final CoverFlowContract.View view;

    @NonNull
    private final BookRepository bookRepository;

    @Inject
    public CoverFlowPresenter(@NonNull CoverFlowContract.View view, @NonNull BookRepository bookRepository) {
        this.view = view;
        this.bookRepository = bookRepository;
    }

    @Override
    public void start() {

    }

    @Override
    public CoverFlowContract.View getView() {
        return view;
    }

    @Override
    public void getBookList(int offset) {

    }

    @Override
    public void onCoverClickedListener(@NonNull Book book) {

    }
}
