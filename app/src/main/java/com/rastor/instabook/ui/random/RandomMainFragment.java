package com.rastor.instabook.ui.random;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rastor.instabook.R;
import com.rastor.instabook.app.MainApplication;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Krishna Chaitanya Kandula on 7/14/17.
 */

public class RandomMainFragment extends Fragment implements RandomContract.View {

    @Inject RandomContract.Presenter presenter;

    @BindView(R.id.page_text_view_samplePageLayout) TextView pageTextView;
    @BindView(R.id.swipeRefreshLayout_randomFragment) SwipeRefreshLayout swipeRefreshLayout;

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
                .randomModule(new RandomModule(this))
                .build()
                .inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_random, container, false);
        setRetainInstance(true);
        ButterKnife.bind(this, view);
        swipeRefreshLayout.setOnRefreshListener(this::onSwipeRefreshLayout);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.start();
    }

    @Override
    public void showLoading() {
        swipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void stopLoading() {
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showRandomPageText(String pageText) {
        pageTextView.setText(pageText);
    }

    @Override
    public void launchBookInformationView() {
        //TODO:
    }

    private void onSwipeRefreshLayout() {
        presenter.onRefresh();
    }
}
