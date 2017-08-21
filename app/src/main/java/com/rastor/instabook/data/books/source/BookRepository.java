package com.rastor.instabook.data.books.source;

import com.rastor.instabook.data.books.models.Book;
import com.rastor.instabook.data.books.models.Books;
import com.rastor.instabook.data.favorites.models.Favorite;

import java.util.List;

/**
 * Created by Krishna Chaitanya Kandula on 7/2/17.
 */

public interface BookRepository {

    interface LoadBookCallback {
        void onBookLoaded(Book book);

        void onBookNotAvailable();
    }

    interface LoadBooksCallback {
        void onBooksLoaded(Books books);

        void onBooksNotAvailable();
    }

    interface LoadFavoriteBooksCallback {
        void onFavoriteBooksLoaded(List<Book> books);
    }

    void getBook(String bookId, LoadBookCallback callback);

    void getBooks(int limit, int offset, boolean refresh, LoadBooksCallback callback);

    void getFavoriteBooks(List<Favorite> favorites, LoadFavoriteBooksCallback callback);
}
