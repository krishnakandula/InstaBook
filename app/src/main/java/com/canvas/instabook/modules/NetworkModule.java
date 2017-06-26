package com.canvas.instabook.modules;

import com.canvas.instabook.app.Config;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * Created by Krishna Chaitanya Kandula on 6/4/17.
 */
@Module
public class NetworkModule {

    @Provides
    @Named("BASE_URL")
    String provideBaseUrl(){
        return Config.INSTABOOK_API_BASE_URL;
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(Converter.Factory converter, @Named("BASE_URL") String baseUrl){
        OkHttpClient client = new OkHttpClient.Builder().build();
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(converter)
                .baseUrl(baseUrl)
                .client(client)
                .build();

        return retrofit;
    }
}
