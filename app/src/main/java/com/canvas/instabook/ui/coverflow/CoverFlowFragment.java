package com.canvas.instabook.ui.coverflow;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.canvas.instabook.R;
import com.canvas.instabook.app.MainApplication;
import com.canvas.instabook.data.models.Book;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@NoArgsConstructor
public class CoverFlowFragment extends Fragment
        implements CoverFlowContract.View, SwipeRefreshLayout.OnRefreshListener {

    @Inject CoverFlowContract.Presenter presenter;

    @BindView(R.id.coverRecyclerView_coverFlowFragment) RecyclerView coverRecyclerView;
    @BindView(R.id.coverRecyclerView_refreshLayout) SwipeRefreshLayout refreshLayout;

    private OnCoverFlowFragmentInteractionListener mListener;
    private CoverFlowAdapter coverFlowAdapter;

    public static final String LOG_TAG = CoverFlowFragment.class.getSimpleName();

    public static CoverFlowFragment newInstance() {
        return new CoverFlowFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnCoverFlowFragmentInteractionListener) {
            mListener = (OnCoverFlowFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnCoverFlowFragmentInteractionListener");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);

        DaggerCoverFlowComponent.builder()
                .appComponent(((MainApplication) getActivity().getApplication()).getAppComponent())
                .coverFlowPresenterModule(new CoverFlowPresenterModule(this))
                .build()
                .inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_cover_flow, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        if(coverFlowAdapter == null) {
            coverFlowAdapter = new CoverFlowAdapter(getContext());
        }
        coverRecyclerView.setAdapter(coverFlowAdapter);
        coverRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        refreshLayout.setOnRefreshListener(this::onRefresh);
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.start();
    }

    @Override
    public void setData(List<Book> data) {
        coverFlowAdapter.setData(data);
    }

    @Override
    public List<Book> getExistingData() {
        return coverFlowAdapter.getData();
    }

    @Override
    public void updateData(@NonNull List<Book> additionalBooks) {

    }

    @Override
    public void showBookView(@NonNull Book book) {

    }

    @Override
    public void onRefresh() {
        presenter.getData(0, true);
    }

    @Override
    public void showLoading() {
        refreshLayout.setRefreshing(true);
    }

    @Override
    public void stopLoading() {
        refreshLayout.setRefreshing(false);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnCoverFlowFragmentInteractionListener {
    }
}
