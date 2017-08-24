package com.rastor.instabook.ui.random;

import com.rastor.instabook.app.Constants;
import com.rastor.instabook.data.books.models.Book;
import com.rastor.instabook.data.books.models.Books;
import com.rastor.instabook.data.books.source.BookRepository;
import com.rastor.instabook.util.FragmentScoped;

import java.util.Collections;

import javax.inject.Inject;

import lombok.NonNull;

/**
 * Created by Krishna Chaitanya Kandula on 8/23/2017.
 */

@FragmentScoped
public class RandomPresenter implements RandomContract.Presenter {

    private final BookRepository booksRepository;

    private final RandomContract.View view;

    private Book currentBook;

    @Inject
    public RandomPresenter(@NonNull BookRepository booksRepository, @NonNull RandomContract.View view) {
        this.booksRepository = booksRepository;
        this.view = view;
    }

    @Override
    public void start() {
        if(currentBook == null) {
            getRandomBook();
        } else {
            view.showRandomPageText(currentBook.getPage());
        }
    }

    @Override
    public void getRandomBook() {
        view.showLoading();
        booksRepository.getBooks(Constants.BOOKS_LIMIT, 0, false, new BookRepository.LoadBooksCallback() {
            @Override
            public void onBooksLoaded(Books books) {
                Collections.shuffle(books.getBooks());
                currentBook = books.getBooks().get(0);
                view.showRandomPageText(currentBook.getPage());
                view.stopLoading();
            }

            @Override
            public void onBooksNotAvailable() {
                view.stopLoading();
                //TODO: Show some error message here
            }
        });
    }

    @Override
    public void onShowBookClicked() {

    }

    @Override
    public void onRefresh() {

    }
}
