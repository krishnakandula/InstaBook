package com.rastor.instabook.ui.coverflow;

import com.rastor.instabook.app.Constants;
import com.rastor.instabook.data.models.Books;
import com.rastor.instabook.data.source.BookRepositoryContract;

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
        this.viewState = ViewState.START;
    }

    @Override
    public void start() {
        switch (viewState) {
            case START:
                getData(0, true);
                break;
            case SHOW_LOADING:
                view.showLoading();
                break;
            case SHOW_CONTENT:
                view.setData(view.getExistingData());
                break;
            case SHOW_ERROR:
                //TODO: Change to string resource
                view.showError("Unable to retrieve data");
                break;
        }
    }

    @Override
    public void getData(int offset, boolean refresh) {
        this.view.showLoading();
        this.viewState = ViewState.SHOW_LOADING;

        bookRepository.getBooks(Constants.BOOKS_LIMIT, offset, refresh, new BookRepositoryContract.LoadBooksCallback() {
            @Override
            public void onBooksLoaded(Books books) {
                if(!books.getBooks().isEmpty()) {
                    if (refresh) {
                        view.setData(books.getBooks());
                    } else {
                        view.updateData(books.getBooks());
                    }
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
        String bookId = view.getExistingData().get(position).getId();
        view.showBookView(bookId);
    }

    enum ViewState {
        START,
        SHOW_LOADING,
        SHOW_CONTENT,
        SHOW_ERROR
    }
}
