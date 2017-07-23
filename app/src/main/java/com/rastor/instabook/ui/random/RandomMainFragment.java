package com.rastor.instabook.ui.random;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rastor.instabook.R;
import com.rastor.instabook.app.Constants;
import com.rastor.instabook.app.MainApplication;
import com.rastor.instabook.data.books.models.Book;
import com.rastor.instabook.data.books.models.Books;
import com.rastor.instabook.data.books.source.BookRepository;
import com.google.common.collect.Lists;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Krishna Chaitanya Kandula on 7/14/17.
 */

public class RandomMainFragment extends Fragment {

    @Inject BookRepository bookRepository;

    @BindView(R.id.viewPager_randomFragment) ViewPager randomViewPager;

    private RandomPagerAdapter pagerAdapter;
    private List<Book> bookList;

    public static final String LOG_TAG = RandomMainFragment.class.getSimpleName();

    public static RandomMainFragment newInstance() {
        Bundle args = new Bundle();

        RandomMainFragment fragment = new RandomMainFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaggerRandomComponent.builder()
                .appComponent(((MainApplication) getActivity().getApplication()).getAppComponent())
                .build()
                .inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_random, container, false);
        setRetainInstance(true);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupViewPager();
    }

    private void setupViewPager() {
        pagerAdapter = new RandomPagerAdapter(getChildFragmentManager(), Lists.newArrayList());
        randomViewPager.setAdapter(pagerAdapter);
        if(bookList == null || bookList.isEmpty()) {
            bookRepository.getBooks(Constants.BOOKS_LIMIT, 0, false, new BookRepository.LoadBooksCallback() {
                @Override
                public void onBooksLoaded(Books books) {
                    Collections.shuffle(books.getBooks());
                    bookList = books.getBooks();
                    pagerAdapter.setData(books.getBooks());
                }

                @Override
                public void onDataNotAvailable() {
                    //TODO: Show some error message here
                }
            });
        } else {
            pagerAdapter.setData(bookList);
        }
    }
}
