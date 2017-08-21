package com.rastor.instabook.network;

import com.rastor.instabook.data.books.models.Book;
import com.rastor.instabook.data.books.models.Books;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Krishna Chaitanya Kandula on 7/1/17.
 */

public interface InstaBookApi {

    @GET("/books/{id}")
    Call<Book> getBook(@Path("id") String bookId);

    @GET("/books")
    Call<Books> getBooks(@Query("count") Integer count, @Query("offset") Integer offset);
}
