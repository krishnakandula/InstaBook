package com.canvas.instabook.models;

import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

/**
 * Created by Krishna Chaitanya Kandula on 6/25/17.
 */
@Data
@AllArgsConstructor(suppressConstructorProperties = true)
public class Book {

    @NonNull
    @SerializedName("author")
    private final String author;

    @NonNull
    @SerializedName("title")
    private final String title;

    @NonNull
    @SerializedName("page")
    private final String page;
}
