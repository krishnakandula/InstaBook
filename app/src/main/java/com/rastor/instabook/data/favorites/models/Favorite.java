package com.rastor.instabook.data.favorites.models;

import lombok.Data;
import lombok.NonNull;

/**
 * Created by Krishna Chaitanya Kandula on 7/23/17.
 */

@Data
public class Favorite {
    @NonNull
    private final String bookId;

}
