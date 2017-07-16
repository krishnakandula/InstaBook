package com.rastor.instabook.network;

import com.rastor.instabook.app.Constants;
import com.rastor.instabook.util.ApplicationScoped;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Krishna Chaitanya Kandula on 6/4/17.
 */
@Module
public class NetworkModule {

    @Provides
    @Named("BASE_URL")
    @ApplicationScoped
    String provideBaseUrl(){
        return Constants.INSTABOOK_API_BASE_URL;
    }

    @Provides
    @Named("gsonConverterFactory")
    @ApplicationScoped
    Converter.Factory provideConverterFactory() {
        return GsonConverterFactory.create();
    }

    @Provides
    @ApplicationScoped
    Retrofit provideRetrofit(@Named("gsonConverterFactory") Converter.Factory converter, @Named("BASE_URL") String baseUrl){
        OkHttpClient client = new OkHttpClient.Builder().build();
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(converter)
                .baseUrl(baseUrl)
                .client(client)
                .build();

        return retrofit;
    }

    @Provides
    @ApplicationScoped
    InstaBookApi provideInstaBookApi(Retrofit retrofit) {
        return retrofit.create(InstaBookApi.class);
    }
}