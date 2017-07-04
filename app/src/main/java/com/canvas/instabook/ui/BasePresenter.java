package com.canvas.instabook.ui;

import android.support.annotation.NonNull;

/**
 * Created by Krishna Chaitanya Kandula on 7/1/17.
 */

public interface BasePresenter<VIEW> {
    void start();

    VIEW getView();
}
