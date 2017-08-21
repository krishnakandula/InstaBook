package com.rastor.instabook.network;

import android.content.Context;

import com.rastor.instabook.app.AppModule;
import com.rastor.instabook.app.Constants;
import com.rastor.instabook.util.ApplicationScoped;

import java.io.File;
import java.util.concurrent.TimeUnit;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Krishna Chaitanya Kandula on 6/4/17.
 */
@Module(includes = AppModule.class)
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
    Cache provideInstaBookCache(Context context) {
        final String cachedInstaBookResponses = "cached_instabook_responses";
        File cacheDir = new File(context.getCacheDir(), cachedInstaBookResponses);
        long maxSize = 20 * 1024 * 1024;

        return new Cache(cacheDir, maxSize);
    }

    @Provides
    @ApplicationScoped
    OkHttpClient provideOkHttpClient(Cache cache) {
        return new OkHttpClient.Builder()
                .cache(cache)
                .connectTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .build();
    }

    @Provides
    @ApplicationScoped
    Retrofit provideRetrofit(@Named("gsonConverterFactory") Converter.Factory converter,
                             @Named("BASE_URL") String baseUrl,
                             OkHttpClient okHttpClient){

        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(converter)
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .build();

        return retrofit;
    }

    @Provides
    @ApplicationScoped
    InstaBookApi provideInstaBookApi(Retrofit retrofit) {
        return retrofit.create(InstaBookApi.class);
    }
}
