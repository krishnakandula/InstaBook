package com.canvas.instabook.ui.information;

import com.canvas.instabook.data.models.Book;
import com.canvas.instabook.data.source.BookRepositoryContract;

import lombok.NonNull;

/**
 * Created by Krishna Chaitanya Kandula on 7/8/17.
 */

public class BookInformationPresenter implements BookInformationContract.Presenter {

    private final String bookId;

    private final BookRepositoryContract booksRepository;

    private final BookInformationContract.View view;

    public BookInformationPresenter(@NonNull String bookId, @NonNull BookInformationContract.View view,
                                    @NonNull BookRepositoryContract booksRepository) {
        this.bookId = bookId;
        this.booksRepository = booksRepository;
        this.view = view;
    }

    @Override
    public void start() {
        getBook();
    }

    @Override
    public void getBook() {
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
    }

    @Override
    public void onViewSamplePage() {

    }

    @Override
    public void onFavorited() {

    }
}
