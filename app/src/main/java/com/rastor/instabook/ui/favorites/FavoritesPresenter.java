package com.rastor.instabook.ui.favorites;

import com.google.common.collect.Lists;
import com.rastor.instabook.data.books.models.Book;
import com.rastor.instabook.data.books.source.BookRepository;
import com.rastor.instabook.data.favorites.models.Favorite;
import com.rastor.instabook.data.favorites.source.FavoritesRepository;
import com.rastor.instabook.util.FragmentScoped;

import java.util.List;

import javax.inject.Inject;

import lombok.NonNull;

/**
 * Created by Krishna Chaitanya Kandula on 7/23/17.
 */

@FragmentScoped
public class FavoritesPresenter implements FavoritesContract.Presenter {

    @NonNull
    private final BookRepository bookRepository;

    @NonNull
    private final FavoritesRepository favoritesRepository;

    @NonNull
    private final FavoritesContract.View view;

    private ViewState viewState;

    @Inject
    public FavoritesPresenter(@NonNull BookRepository bookRepository,
                              @NonNull FavoritesRepository favoritesRepository,
                              @NonNull FavoritesContract.View view) {

        this.bookRepository = bookRepository;
        this.favoritesRepository = favoritesRepository;
        this.view = view;
        this.viewState = ViewState.START;
    }

    @Override
    public void start() {
        switch (this.viewState) {
            case START:
                getData(0);
                break;
            case SHOW_LOADING:
                view.showLoading();
                break;
            case SHOW_CONTENT:
                view.setData(view.getExistingData());
                getData(0);
                break;
            case SHOW_ERROR:
                view.stopLoading();
                view.showErrorMessage();
                break;
        }
    }

    @Override
    public void getData(int offset) {
        view.showLoading();
        this.viewState = ViewState.SHOW_LOADING;

        //Load favorites, then load books
        favoritesRepository.getFavorites(offset, new FavoritesRepository.LoadFavoritesCallback() {
            @Override
            public void onFavoritesLoaded(List<Favorite> favorites) {
                bookRepository.getFavoriteBooks(favorites, (books) -> {
                    view.stopLoading();
                    view.setData(books);
                    viewState = ViewState.SHOW_ERROR;
                });
            }

            @Override
            public void onFavoritesNotAvailable() {
                view.stopLoading();
                view.showErrorMessage();
                viewState = ViewState.SHOW_ERROR;
            }
        });
    }

    @Override
    public void onFavoriteClicked(int position) {
        String bookId = view.getExistingData().get(position).getId();
        view.showBookInfoView(bookId);
    }

    enum ViewState {
        START,
        SHOW_LOADING,
        SHOW_CONTENT,
        SHOW_ERROR
    }
}
