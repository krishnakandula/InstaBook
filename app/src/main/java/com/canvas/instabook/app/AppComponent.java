package com.canvas.instabook.app;

import com.canvas.instabook.data.source.BookRepositoryContract;
import com.canvas.instabook.data.source.BooksRepositoryModule;
import com.canvas.instabook.util.ApplicationScoped;

import dagger.Component;

/**
 * Created by Krishna Chaitanya Kandula on 6/4/17.
 */
@ApplicationScoped
@Component(modules = {AppModule.class, BooksRepositoryModule.class})
public interface AppComponent {
    BookRepositoryContract getBookRepository();
}
