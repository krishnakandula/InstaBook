package com.rastor.instabook.ui.favorites;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rastor.instabook.R;
import com.rastor.instabook.app.MainApplication;
import com.rastor.instabook.data.books.models.Book;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class FavoritesFragment extends Fragment implements FavoritesContract.View {

    @Inject FavoritesContract.Presenter presenter;

    @BindView(R.id.recylerview_favoritesFragment) RecyclerView favoritesRecyclerView;

    private FavoritesAdapter favoritesAdapter;

    public static final String LOG_TAG = FavoritesFragment.class.getSimpleName();

    public static FavoritesFragment newInstance() {
        FavoritesFragment fragment = new FavoritesFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DaggerFavoritesComponent.builder()
                .appComponent(((MainApplication) getActivity().getApplication()).getAppComponent())
                .favoritesModule(new FavoritesModule(this))
                .build()
                .inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_favorites, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        if(favoritesAdapter == null) {
            favoritesAdapter = new FavoritesAdapter(getContext());
        }
        favoritesRecyclerView.setAdapter(favoritesAdapter);
        favoritesRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.start();
    }

    @Override
    public void setData(List<Book> data) {
        this.favoritesAdapter.setData(data);
    }

    @Override
    public void updateData(List<Book> additionalData) {
        this.favoritesAdapter.addData(additionalData);
    }

    @Override
    public List<Book> getExistingData() {
        return favoritesAdapter.getData();
    }

    @Override
    public void showBookInfoView(String bookId) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void stopLoading() {

    }

    @Override
    public void showErrorMessage() {

    }
}
