package com.canvas.instabook.ui.information;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.canvas.instabook.R;

public class BookInformationActivity extends AppCompatActivity {

    public static final String BOOK_ID_TAG = "book_id_tag";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_information);

        String bookId = getIntent().getStringExtra(BOOK_ID_TAG);
        BookInformationFragment bookInformationFragment = BookInformationFragment.newInstance(bookId);
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction()
                .replace(R.id.fragmentContainer_bookInformationActivity, bookInformationFragment)
                .commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return false;
        }
    }
}
