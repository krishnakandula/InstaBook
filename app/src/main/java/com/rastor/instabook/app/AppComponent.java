package com.rastor.instabook.app;

import com.rastor.instabook.data.source.BookRepositoryContract;
import com.rastor.instabook.data.source.BooksRepositoryModule;
import com.rastor.instabook.util.ApplicationScoped;

import dagger.Component;

/**
 * Created by Krishna Chaitanya Kandula on 6/4/17.
 */
@ApplicationScoped
@Component(modules = {AppModule.class, BooksRepositoryModule.class})
public interface AppComponent {
    BookRepositoryContract getBookRepository();
}
