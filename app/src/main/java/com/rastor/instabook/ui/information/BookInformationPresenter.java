package com.rastor.instabook.ui.information;

import com.rastor.instabook.data.books.models.Book;
import com.rastor.instabook.data.books.source.BookRepository;
import com.rastor.instabook.data.favorites.models.Favorite;
import com.rastor.instabook.data.favorites.source.FavoritesRepository;
import com.rastor.instabook.util.FragmentScoped;

import javax.inject.Inject;
import javax.inject.Named;

import lombok.NonNull;

/**
 * Created by Krishna Chaitanya Kandula on 7/8/17.
 */

@FragmentScoped
public class BookInformationPresenter implements BookInformationContract.Presenter {

    private final String bookId;

    private final BookRepository booksRepository;

    private final FavoritesRepository favoritesRepository;

    private final BookInformationContract.View view;

    private ViewState viewState;

    private boolean isFavorited;

    @Inject
    public BookInformationPresenter(@NonNull @Named(value = "BookInformationId") String bookId,
                                    @NonNull BookInformationContract.View view,
                                    @NonNull BookRepository booksRepository,
                                    @NonNull FavoritesRepository favoritesRepository) {
        this.bookId = bookId;
        this.booksRepository = booksRepository;
        this.favoritesRepository = favoritesRepository;
        this.view = view;
        viewState = ViewState.START;
        isFavorited = false;
    }

    @Override
    public void start() {
        switch (viewState) {
            case START:
                getBook();
                getIsFavorited();
                break;
            case SHOW_LOADING:
                //Do nothing
                break;
            case SHOW_CONTENT:
                view.showBookInformation(view.getExistingData());
                getIsFavorited();
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
        booksRepository.getBook(this.bookId, new BookRepository.LoadBookCallback() {
            @Override
            public void onBookLoaded(Book book) {
                view.showBookInformation(book);
            }

            @Override
            public void onBookNotAvailable() {
                view.showError("Could not load book");
            }
        });
        viewState = ViewState.SHOW_CONTENT;
    }

    @Override
    public void getIsFavorited() {
        viewState = ViewState.SHOW_LOADING;
        favoritesRepository.isFavorited(this.bookId, favorited -> {
            isFavorited = favorited;
            view.showIsFavorited(favorited);
        });
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
        //TODO: Show message that favorite was added
        isFavorited = !isFavorited;
        view.showIsFavorited(isFavorited);

        if(this.isFavorited) {
            favoritesRepository.addFavorite(new Favorite(bookId));
        } else {
            favoritesRepository.deleteFavorite(bookId);
        }
    }

    enum ViewState {
        START,
        SHOW_LOADING,
        SHOW_CONTENT,
        SHOW_DETAILS,
        SHOW_ERROR
    }
}
