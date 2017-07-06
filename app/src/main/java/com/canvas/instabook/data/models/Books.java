package com.canvas.instabook.data.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Data;
import lombok.NonNull;

/**
 * Created by Krishna Chaitanya Kandula on 7/3/17.
 */

@Data
public class Books {

    @SerializedName("offset")
    private int offset;

    @NonNull
    @SerializedName("books")
    private List<Book> books;
}
