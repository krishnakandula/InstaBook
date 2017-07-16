package com.rastor.instabook.ui.information;

import com.rastor.instabook.data.models.Book;
import com.rastor.instabook.data.source.BookRepositoryContract;

import lombok.NonNull;

/**
 * Created by Krishna Chaitanya Kandula on 7/8/17.
 */

public class BookInformationPresenter implements BookInformationContract.Presenter {

    private final String bookId;

    private final BookRepositoryContract booksRepository;

    private final BookInformationContract.View view;

    private ViewState viewState;

    public BookInformationPresenter(@NonNull String bookId, @NonNull BookInformationContract.View view,
                                    @NonNull BookRepositoryContract booksRepository) {
        this.bookId = bookId;
        this.booksRepository = booksRepository;
        this.view = view;
        viewState = ViewState.START;
    }

    @Override
    public void start() {
        switch (viewState) {
            case START:
                getBook();
                break;
            case SHOW_LOADING:
                //Do nothing
                break;
            case SHOW_CONTENT:
                view.showBookInformation(view.getExistingData());
                break;
            case SHOW_DETAILS:
                Book book = view.getExistingData();
                view.showBookInformation(book);
                view.showSamplePage(true);
                break;
            case SHOW_ERROR:
                //TODO: Change this to a string resource
                view.showError("Unable to load data");
        }
    }

    @Override
    public void getBook() {
        viewState = ViewState.SHOW_LOADING;
        booksRepository.getBook(this.bookId, new BookRepositoryContract.LoadBookCallback() {
            @Override
            public void onBookLoaded(Book book) {
                view.showBookInformation(book);
            }

            @Override
            public void onDataNotAvailable() {
                view.showError("Could not load book");
            }
        });
        viewState = ViewState.SHOW_CONTENT;
    }

    @Override
    public void onViewSamplePage(boolean pageIsVisible) {
        if(pageIsVisible){
            viewState = ViewState.SHOW_CONTENT;
            view.hideSamplePage();
        } else {
            viewState = ViewState.SHOW_DETAILS;
            view.showSamplePage(false);
        }
    }

    @Override
    public void onFavorited() {
        //Add to favorites db
    }

    enum ViewState {
        START,
        SHOW_LOADING,
        SHOW_CONTENT,
        SHOW_DETAILS,
        SHOW_ERROR
    }
}
