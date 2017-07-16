package com.rastor.instabook.data.source;

import com.rastor.instabook.network.InstaBookApi;
import com.rastor.instabook.network.NetworkModule;
import com.rastor.instabook.util.ApplicationScoped;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Krishna Chaitanya Kandula on 7/3/17.
 */

@Module(includes = NetworkModule.class)
public class BooksRepositoryModule {

    @Provides
    @ApplicationScoped
    public BookRepositoryContract provideBookRepository(InstaBookApi instaBookApi) {
        return new BookRepository(instaBookApi);
    }
}
