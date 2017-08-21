package com.rastor.instabook.util;

import android.support.v7.util.DiffUtil;

import com.rastor.instabook.data.books.models.Book;

import java.util.List;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * Created by Krishna Chaitanya Kandula on 7/27/17.
 */

@RequiredArgsConstructor
public class BookListDiffUtil extends DiffUtil.Callback {

    @NonNull
    private final List<Book> oldBooks;

    @NonNull
    private final List<Book> newBooks;

    @Override
    public int getOldListSize() {
        return this.oldBooks.size();
    }

    @Override
    public int getNewListSize() {
        return this.newBooks.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        final String oldBookId = oldBooks.get(oldItemPosition).getId();
        final String newBookId = newBooks.get(newItemPosition).getId();

        return oldBookId.equalsIgnoreCase(newBookId);
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return oldBooks.get(oldItemPosition).equals(newBooks.get(newItemPosition));
    }
}
