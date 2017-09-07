package com.rastor.instabook.ui.information;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.rastor.instabook.R;

public class BookInformationActivity extends AppCompatActivity {

    private BookInformationContract.View bookInformationView;

    private String bookId;

    public static final String BOOK_ID_TAG = "book_id_tag";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_information);

        this.bookId = getIntent().getStringExtra(BOOK_ID_TAG);
        initializeFragments();
    }

    private void initializeFragments() {
        bookInformationView = (BookInformationFragment) getSupportFragmentManager()
                .findFragmentByTag(BookInformationFragment.LOG_TAG);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (bookInformationView == null) {
            bookInformationView = BookInformationFragment.newInstance(bookId);
        }

        transaction.add(R.id.fragmentContainer_bookInformationActivity,
                (Fragment) bookInformationView,
                BookInformationFragment.LOG_TAG)
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

    @Override
    protected void onPause() {
        super.onPause();
        getSupportFragmentManager().beginTransaction()
                .remove((Fragment) bookInformationView)
                .commit();
    }
}
