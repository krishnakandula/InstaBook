package com.rastor.instabook.data.source;

import com.rastor.instabook.data.models.Book;
import com.rastor.instabook.data.models.Books;

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

    void getBook(String bookId, LoadBookCallback callback);

    void getBooks(int limit, int offset, boolean refresh, LoadBooksCallback callback);
}
