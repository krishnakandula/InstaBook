package com.rastor.instabook.network;

import com.rastor.instabook.app.Constants;

/**
 * Created by Krishna Chaitanya Kandula on 7/9/17.
 */

public class ImageRouteCreator {
    public static String createCoverImageRoute(String bookId) {
        return String.format("%s/books/cover/%s", Constants.INSTABOOK_API_BASE_URL, bookId);
    }

    public static String createBackgroundImageRoute(String bookId) {
        return String.format("%s/books/background/%s", Constants.INSTABOOK_API_BASE_URL, bookId);

    }
}
