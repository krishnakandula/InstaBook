package com.canvas.instabook.app;

import android.app.Application;

import com.canvas.instabook.modules.AppComponent;
import com.canvas.instabook.modules.AppModule;
import com.canvas.instabook.modules.DaggerAppComponent;

/**
 * Created by Krishna Chaitanya Kandula on 6/4/17.
 */

public class MainApplication extends Application {
    private AppComponent appComponent;

    public AppComponent getAppComponent() {
        return appComponent;
    }

    protected AppComponent initDagger(MainApplication application){
        return DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = initDagger(this);
    }
}
