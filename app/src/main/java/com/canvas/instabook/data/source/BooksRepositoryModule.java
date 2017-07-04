package com.canvas.instabook.data.source;

import com.canvas.instabook.network.InstaBookApi;
import com.canvas.instabook.network.NetworkModule;
import com.canvas.instabook.util.ApplicationScoped;

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
