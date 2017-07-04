package com.canvas.instabook.data.source;

import com.canvas.instabook.network.InstaBookApi;

import lombok.RequiredArgsConstructor;

/**
 * Created by Krishna Chaitanya Kandula on 7/4/17.
 */
@RequiredArgsConstructor
public class BookRepository implements BookRepositoryContract {

    private final InstaBookApi instaBookApi;

    @Override
    public void getBook(LoadBookCallback callback) {

    }

    @Override
    public void getBooks(LoadBooksCallback callback) {

    }

    @Override
    public void refreshBooks() {

    }
}
