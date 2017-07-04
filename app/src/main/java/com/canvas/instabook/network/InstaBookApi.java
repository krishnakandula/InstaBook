package com.canvas.instabook.network;

import com.canvas.instabook.data.models.Book;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Krishna Chaitanya Kandula on 7/1/17.
 */

public interface InstaBookApi {

    @GET("/books/{id}")
    Call<Book> getBook(@Path("id") String id);

    @GET("/books")
    Call<List<Book>> getBooks(@Query("count") Integer count, @Query("offset") Integer offset);
}
