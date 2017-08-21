package com.rastor.instabook.app;

import android.app.Application;
import android.content.Context;

import com.rastor.instabook.util.ApplicationScoped;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Krishna Chaitanya Kandula on 6/4/17.
 */
@Module
public class AppModule {

    private Application application;

    public AppModule(Application application){
        this.application = application;
    }

    @Provides
    @ApplicationScoped
    public Context provideContext(){
        return application;
    }

    @Provides
    @ApplicationScoped
    public AppStatus provideAppStatus() {
        return new AppStatus(application);
    }
}
