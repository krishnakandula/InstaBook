package com.canvas.instabook.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.canvas.instabook.R;
import com.canvas.instabook.app.MainApplication;
import com.canvas.instabook.models.Book;
import com.canvas.instabook.network.InstaBookApi;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Inject
    InstaBookApi instaBookApi;

    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((MainApplication) getApplication()).getAppComponent().inject(this);

        instaBookApi.getBook("The Count of Monte CristoAlexandre Dumas", false).enqueue(new Callback<Book>() {
            @Override
            public void onResponse(Call<Book> call, Response<Book> response) {
                Log.d(LOG_TAG, response.body().getAuthor());
            }

            @Override
            public void onFailure(Call<Book> call, Throwable t) {
                Log.e(LOG_TAG, t.getMessage(), t);
            }
        });
    }
}
