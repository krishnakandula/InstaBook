package com.rastor.instabook.data.books.source;

import android.util.Log;

import com.google.common.collect.Lists;
import com.rastor.instabook.app.AppStatus;
import com.rastor.instabook.data.books.models.Book;
import com.rastor.instabook.data.books.models.Books;
import com.rastor.instabook.data.favorites.models.Favorite;
import com.rastor.instabook.network.InstaBookApi;
import com.google.common.collect.Maps;

import java.util.List;
import java.util.Map;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Krishna Chaitanya Kandula on 7/4/17.
 */
@RequiredArgsConstructor
public class CachingBookRepository implements BookRepository {

    @NonNull
    private final InstaBookApi instaBookApi;

    @NonNull
    private final AppStatus appStatus;

    private Map<String, Book> inMemoryBooksCache = Maps.newHashMap();

    private static final String LOG_TAG = CachingBookRepository.class.getSimpleName();

    @Override
    public void getBook(@NonNull String bookId, @NonNull LoadBookCallback callback) {
        if(!inMemoryBooksCache.containsKey(bookId)) {
            if(!appStatus.isConnected()) {
                callback.onBookNotAvailable();

            } else {
                instaBookApi.getBook(bookId).enqueue(new Callback<Book>() {
                    @Override
                    public void onResponse(Call<Book> call, Response<Book> response) {
                        if (response.body() == null) {
                            onFailure(call, new Exception(String.format("Unable to retrieve book with id = %s", bookId)));
                        } else {
                            Book book = response.body();
                            inMemoryBooksCache.put(book.getId(), book);
                            callback.onBookLoaded(book);
                        }
                    }

                    @Override
                    public void onFailure(Call<Book> call, Throwable t) {
                        Log.e(LOG_TAG, t.getMessage(), t);
                        callback.onBookNotAvailable();
                    }
                });
            }
        } else {
            callback.onBookLoaded(inMemoryBooksCache.get(bookId));
        }
    }

    @Override
    public void getBooks(int limit, int offset, boolean refresh, @NonNull LoadBooksCallback callback) {
        if(!appStatus.isConnected()) {
            callback.onBooksNotAvailable();
        } else {
            instaBookApi.getBooks(limit, offset).enqueue(new Callback<Books>() {
                @Override
                public void onResponse(Call<Books> call, Response<Books> response) {
                    if (response.body() == null) {
                        onFailure(call, new Exception("Unable to retrieve books"));
                    } else {
                        callback.onBooksLoaded(response.body());
                    }
                }

                @Override
                public void onFailure(Call<Books> call, Throwable t) {
                    Log.e(LOG_TAG, t.getMessage(), t);
                    callback.onBooksNotAvailable();
                }
            });
        }
    }

    @Override
    public void getFavoriteBooks(@NonNull List<Favorite> favorites, @NonNull LoadFavoriteBooksCallback callback) {
        getFavoriteBooksHelper(favorites, Lists.newArrayList(), callback);
    }

    private void getFavoriteBooksHelper(@NonNull List<Favorite> favorites,
                                              @NonNull List<Book> favoriteBooks,
                                              @NonNull LoadFavoriteBooksCallback callback) {
        if(favorites.isEmpty()) {
            callback.onFavoriteBooksLoaded(favoriteBooks);
            return;
        }

        final String favoriteId = favorites.get(favorites.size() - 1).getBookId();
        favorites.remove(favorites.size() - 1);

        getBook(favoriteId, new LoadBookCallback() {
            @Override
            public void onBookLoaded(Book book) {
                favoriteBooks.add(book);
                getFavoriteBooksHelper(favorites, favoriteBooks, callback);
            }

            @Override
            public void onBookNotAvailable() {
                getFavoriteBooksHelper(favorites, favoriteBooks, callback);
            }
        });
    }
}
