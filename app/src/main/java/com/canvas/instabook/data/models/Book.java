package com.canvas.instabook.data.models;

import com.google.gson.annotations.SerializedName;

import lombok.Data;
import lombok.NonNull;

/**
 * Created by Krishna Chaitanya Kandula on 6/25/17.
 */
@Data
public class Book {

    @NonNull
    @SerializedName("_id")
    private final String id;

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
