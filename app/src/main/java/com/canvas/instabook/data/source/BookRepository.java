package com.canvas.instabook.data.source;

import android.util.Log;

import com.canvas.instabook.data.models.Book;
import com.canvas.instabook.data.models.Books;
import com.canvas.instabook.network.InstaBookApi;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import java.util.Map;
import java.util.Set;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Krishna Chaitanya Kandula on 7/4/17.
 */
@RequiredArgsConstructor
public class BookRepository implements BookRepositoryContract {

    @NonNull
    private final InstaBookApi instaBookApi;

    private Map<String, Book> inMemoryBooksCache = Maps.newHashMap();

    private static final String LOG_TAG = BookRepository.class.getSimpleName();

    @Override
    public void getBook(@NonNull String bookId, @NonNull LoadBookCallback callback) {
        if(!inMemoryBooksCache.containsKey(bookId)) {
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
                    callback.onDataNotAvailable();
                }
            });
        } else {
            callback.onBookLoaded(inMemoryBooksCache.get(bookId));
        }
    }

    @Override
    public void getBooks(int limit, int offset, boolean refresh, LoadBooksCallback callback) {
        instaBookApi.getBooks(limit, offset).enqueue(new Callback<Books>() {
            @Override
            public void onResponse(Call<Books> call, Response<Books> response) {
                if(response.body() == null) {
                    onFailure(call, new Exception("Unable to retrieve books"));
                } else {
                    callback.onBooksLoaded(response.body());
                }
            }

            @Override
            public void onFailure(Call<Books> call, Throwable t) {
                Log.e(LOG_TAG, t.getMessage(), t);
                callback.onDataNotAvailable();
            }
        });
    }
}
