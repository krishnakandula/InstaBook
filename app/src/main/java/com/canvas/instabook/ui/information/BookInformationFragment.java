package com.canvas.instabook.ui.information;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.canvas.instabook.R;
import com.canvas.instabook.app.MainApplication;
import com.canvas.instabook.data.models.Book;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.picasso.transformations.BlurTransformation;
import jp.wasabeef.picasso.transformations.gpu.PixelationFilterTransformation;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import static com.canvas.instabook.network.ImageRouteCreator.*;

/**
 * Created by Krishna Chaitanya Kandula on 7/8/17.
 */
@NoArgsConstructor
public class BookInformationFragment extends Fragment implements BookInformationContract.View {

    @Inject BookInformationContract.Presenter presenter;

    @BindView(R.id.coverImageView_bookInformationFragment) ImageView coverImageView;
    @BindView(R.id.collapsingToolbar_bookInformationFragment) CollapsingToolbarLayout collapsingToolbarLayout;
    @BindView(R.id.toolbar_bookInformationFragment) Toolbar toolbar;

    private Book book;

    public static final String BOOK_ID_KEY = "book_id_key";
    public static final String LOG_TAG = BookInformationFragment.class.getSimpleName();

    public static BookInformationFragment newInstance(@NonNull String bookId) {
        Bundle args = new Bundle();
        args.putString(BOOK_ID_KEY, bookId);
        BookInformationFragment fragment = new BookInformationFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String bookId = getArguments().getString(BOOK_ID_KEY);
        DaggerBookInformationComponent.builder()
                .appComponent(((MainApplication) getActivity().getApplication()).getAppComponent())
                .bookInformationModule(new BookInformationModule(bookId, this))
                .build()
                .inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return LayoutInflater.from(getContext()).inflate(R.layout.fragment_book_information, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.getBook();
    }

    @Override
    public void showBookInformation(@NonNull Book book) {
        collapsingToolbarLayout.setTitle(book.getTitle());
        Picasso.with(getContext())
                .load(createBackgroundImageRoute(book.getId()))
                .transform(new BlurTransformation(getContext()))
                .resize(coverImageView.getMeasuredWidth(), coverImageView.getMeasuredHeight())
                .centerCrop()
                .into(coverImageView);
    }

    @Override
    public void showSamplePage() {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void stopLoading() {

    }

    @Override
    public void showError(String message) {

    }
}
