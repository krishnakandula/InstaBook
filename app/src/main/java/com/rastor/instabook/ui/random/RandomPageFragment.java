package com.rastor.instabook.ui.random;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.rastor.instabook.R;
import com.rastor.instabook.data.books.models.Book;
import com.rastor.instabook.ui.information.BookInformationActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Krishna Chaitanya Kandula on 7/14/17.
 */

public class RandomPageFragment extends Fragment {

    @BindView(R.id.page_text_view_samplePageLayout) TextView pageTextView;
    @BindView(R.id.viewBookButton_randomPageFragment) Button viewBookButton;

    private Book mBook;

    public static final String BOOK_KEY = "book_key";

    public static RandomPageFragment newInstance(@NonNull Book book) {

        Bundle args = new Bundle();
        args.putParcelable(BOOK_KEY, book);
        RandomPageFragment fragment = new RandomPageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mBook = getArguments().getParcelable(BOOK_KEY);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_random_page, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        pageTextView.setText(mBook.getPage());
    }

    @OnClick(R.id.viewBookButton_randomPageFragment)
    public void onClickViewBookButton() {
        Intent intent = new Intent(getContext(), BookInformationActivity.class);
        intent.putExtra(BookInformationActivity.BOOK_ID_TAG, mBook.getId());
        startActivity(intent);
    }
}
