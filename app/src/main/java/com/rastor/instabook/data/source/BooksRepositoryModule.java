package com.rastor.instabook.data.source;

import com.rastor.instabook.app.AppModule;
import com.rastor.instabook.app.AppStatus;
import com.rastor.instabook.network.InstaBookApi;
import com.rastor.instabook.network.NetworkModule;
import com.rastor.instabook.util.ApplicationScoped;

import dagger.Module;
import dagger.Provides;
import lombok.NonNull;

/**
 * Created by Krishna Chaitanya Kandula on 7/3/17.
 */

@Module(includes = {NetworkModule.class, AppModule.class})
public class BooksRepositoryModule {

    @Provides
    @ApplicationScoped
    public BookRepositoryContract provideBookRepository(@NonNull InstaBookApi instaBookApi, @NonNull AppStatus appStatus) {
        return new BookRepository(instaBookApi, appStatus);
    }
}
