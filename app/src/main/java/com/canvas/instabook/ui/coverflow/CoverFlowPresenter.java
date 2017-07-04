package com.canvas.instabook.ui.coverflow;

import android.support.annotation.NonNull;

import com.canvas.instabook.data.models.Book;

/**
 * Created by Krishna Chaitanya Kandula on 7/2/17.
 */

public class CoverFlowPresenter implements CoverFlowContract.Presenter {

    private CoverFlowContract.View view;

    @Override
    public void initialize(@NonNull CoverFlowContract.View view) {
        this.view = view;
    }

    @Override
    public CoverFlowContract.View getView() {
        return view;
    }

    @Override
    public void getBookList(int offset) {

    }

    @Override
    public void onCoverClickedListener(@NonNull Book book) {

    }
}
