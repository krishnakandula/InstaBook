package com.canvas.instabook.ui.information;

import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.canvas.instabook.R;
import com.canvas.instabook.app.MainApplication;
import com.canvas.instabook.data.models.Book;
import com.mancj.slideup.SlideUp;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import jp.wasabeef.picasso.transformations.BlurTransformation;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import static com.canvas.instabook.network.ImageRouteCreator.*;

/**
 * Created by Krishna Chaitanya Kandula on 7/8/17.
 */
@NoArgsConstructor
public class BookInformationFragment extends Fragment implements BookInformationContract.View, AppBarLayout.OnOffsetChangedListener {

    @Inject BookInformationContract.Presenter presenter;

    @BindView(R.id.coverImageView_bookInformationFragment) ImageView backgroundImageView;
    @BindView(R.id.toolbar_bookInformationFragment) Toolbar toolbar;
    @BindView(R.id.titleTextView_bookInformationFragment) TextView tileTextView;
    @BindView(R.id.authorTextView_bookInformationFragment) TextView authorTextView;
    @BindView(R.id.fab_bookInformationFragment) FloatingActionButton fab;
    @BindView(R.id.bookCoverImageView_bookInformationFragment) CircleImageView coverImageView;
    @BindView(R.id.titleContainerLayout_bookInformationFragment) LinearLayout titleContainerLayout;
    @BindView(R.id.appBar_bookInformationFragment) AppBarLayout appBarLayout;
    @BindView(R.id.container_samplePage) View samplePageFrame;
    @BindView(R.id.readSampleButton_bookInformationFragment) Button sampleButton;
    @BindView(R.id.page_text_view_samplePageLayout) TextView sampleTextView;

    private SlideUp samplePageSlideUp;
    private int mMaxScrollRange;
    private boolean mCoverImageIsShown = true;

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
        mMaxScrollRange = appBarLayout.getTotalScrollRange();
        appBarLayout.addOnOffsetChangedListener(this::onOffsetChanged);
        setupToolbar();
        setupSamplePage();
        setupSampleButton();
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
        final int animationDuration = 200;
        final int COVER_IMAGE_ANIMATION_PERCENTAGE = 20;

        if (mMaxScrollRange == 0)
            mMaxScrollRange = appBarLayout.getTotalScrollRange();

        int percentage = (Math.abs(verticalOffset)) * 100 / mMaxScrollRange;

        if (percentage >= COVER_IMAGE_ANIMATION_PERCENTAGE && mCoverImageIsShown) {
            mCoverImageIsShown = false;

            coverImageView.animate()
                    .scaleY(0).scaleX(0)
                    .setDuration(animationDuration)
                    .start();
        }

        if (percentage <= COVER_IMAGE_ANIMATION_PERCENTAGE && !mCoverImageIsShown) {
            mCoverImageIsShown = true;

            coverImageView.animate()
                    .scaleY(1).scaleX(1)
                    .setDuration(animationDuration)
                    .start();
        }
    }

    private void setupToolbar() {
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    private void setupSamplePage() {
        samplePageSlideUp = new SlideUp.Builder(samplePageFrame)
                .withStartState(SlideUp.State.HIDDEN)
                .withStartGravity(Gravity.BOTTOM)
                .build();
    }

    private void setupSampleButton() {
        sampleButton.setText("Read Sample");
        sampleButton.setOnClickListener(v -> {
            if(!samplePageSlideUp.isVisible()) {
                fab.hide();
                samplePageSlideUp.show();
                sampleButton.setText("Done");
            } else {
                samplePageSlideUp.hide();
                fab.show();
                sampleButton.setText("Read Sample");
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.getBook();
    }

    @Override
    public void showBookInformation(@NonNull Book book) {
        tileTextView.setText(book.getTitle());
        authorTextView.setText(book.getAuthor());
        sampleTextView.setText(book.getPage());

        coverImageView.post(() -> {
            Picasso.with(getContext())
                    .load(createCoverImageRoute(book.getId()))
                    .into(coverImageView, setFabThemeColorCallback);
        });
        backgroundImageView.post(() -> {
            Picasso.with(getContext())
                    .load(createBackgroundImageRoute(book.getId()))
                    .transform(new BlurTransformation(getContext()))
                    .resize(backgroundImageView.getMeasuredWidth(), backgroundImageView.getMeasuredHeight())
                    .centerCrop()
                    .into(backgroundImageView, setThemeColorCallback);
        });
    }

    @Override
    public void showSamplePage() {

    }

    @Override
    public void showError(String message) {

    }

    private final Callback setThemeColorCallback = new Callback() {
        @Override
        public void onSuccess() {
            Bitmap bitmap = ((BitmapDrawable) backgroundImageView.getDrawable()).getBitmap();
            int defaultColor = getResources().getColor(R.color.colorPrimary);
            Palette.from(bitmap).generate(palette -> {
                toolbar.setBackgroundColor(palette.getMutedColor(defaultColor));
                titleContainerLayout.setBackgroundColor(palette.getMutedColor(defaultColor));
            });
        }

        @Override
        public void onError() {
            Log.e(LOG_TAG, "Couldn't set theme color");
        }
    };

    private final Callback setFabThemeColorCallback = new Callback() {
        @Override
        public void onSuccess() {
            Bitmap bitmap = ((BitmapDrawable) coverImageView.getDrawable()).getBitmap();
            int defaultColor = getResources().getColor(R.color.colorAccent);
            Palette.from(bitmap).generate(palette -> {
                fab.setBackgroundTintList(ColorStateList.valueOf(palette.getDominantColor(defaultColor)));
            });
        }

        @Override
        public void onError() {
            Log.e(LOG_TAG, "Couldn't set theme color");
        }
    };
}
