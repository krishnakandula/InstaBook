package com.canvas.instabook.ui;

/**
 * Created by Krishna Chaitanya Kandula on 7/1/17.
 */

public interface BasePresenter<VIEW> {
    void setView(VIEW view);
    void start();
}
