package com.canvas.instabook.ui;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.canvas.instabook.R;
import com.canvas.instabook.app.MainApplication;
import com.canvas.instabook.models.Book;
import com.canvas.instabook.network.InstaBookApi;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.canvas.instabook.app.Constants.*;

public class MainActivity extends AppCompatActivity {

    @Inject
    InstaBookApi instaBookApi;

    @BindView(R.id.cover_image_view_main_activity)
    ImageView coverImageView;

    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((MainApplication) getApplication()).getAppComponent().inject(this);
        ButterKnife.bind(this);

        final String bookId = "The Count of Monte CristoAlexandre Dumas";
        Uri imageCoverUri = Uri.parse(String.format("%s/books/cover/%s", INSTABOOK_API_BASE_URL, bookId));
        Picasso.with(this)
                .load(imageCoverUri)
                .into(coverImageView, new com.squareup.picasso.Callback() {
                    @Override
                    public void onSuccess() {
                        Log.d(LOG_TAG, "loaded");
                    }

                    @Override
                    public void onError() {
                        Log.d(LOG_TAG, "couldn't load");
                    }
                });

        instaBookApi.getBook(bookId).enqueue(new Callback<Book>() {
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
