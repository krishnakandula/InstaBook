package com.canvas.instabook.network;

import com.canvas.instabook.models.Book;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Krishna Chaitanya Kandula on 7/1/17.
 */

public interface InstaBookApi {

    @GET("/books/{id}/{image}")
    Call<Book> getBook(@Path("id") String id);
}
