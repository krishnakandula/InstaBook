package com.rastor.instabook.app;

import com.rastor.instabook.data.books.source.BookRepository;
import com.rastor.instabook.data.books.source.BooksRepositoryModule;
import com.rastor.instabook.util.ApplicationScoped;

import dagger.Component;

/**
 * Created by Krishna Chaitanya Kandula on 6/4/17.
 */
@ApplicationScoped
@Component(modules = BooksRepositoryModule.class)
public interface AppComponent {
    BookRepository getBookRepository();
}
