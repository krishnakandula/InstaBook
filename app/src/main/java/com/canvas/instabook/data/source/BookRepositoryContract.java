package com.canvas.instabook.data.source;

import com.canvas.instabook.data.models.Book;
import com.canvas.instabook.data.models.Books;

import lombok.NonNull;

/**
 * Created by Krishna Chaitanya Kandula on 7/2/17.
 */

public interface BookRepositoryContract {

    interface LoadBookCallback {
        void onBookLoaded(Book book);

        void onDataNotAvailable();
    }

    interface LoadBooksCallback {
        void onBooksLoaded(Books books);

        void onDataNotAvailable();
    }

    void getBook(@NonNull LoadBookCallback callback);

    void getBooks(@NonNull LoadBooksCallback callback);

    void refreshBooks();
}
