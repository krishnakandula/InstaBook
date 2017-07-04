package com.canvas.instabook.ui.coverflow;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.canvas.instabook.R;
import com.canvas.instabook.app.Constants;
import com.canvas.instabook.app.MainApplication;
import com.canvas.instabook.data.models.Book;
import com.canvas.instabook.data.models.Books;
import com.canvas.instabook.network.InstaBookApi;
import com.squareup.picasso.Picasso;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CoverFlowFragment extends Fragment implements CoverFlowContract.View {

    private OnCoverFlowFragmentInteractionListener mListener;

    @Inject
    InstaBookApi instaBookApi;

    @BindView(R.id.coverImageView_coverFlowFragment)
    ImageView coverImageView;

    public static final String TAG = CoverFlowFragment.class.getSimpleName();

    public CoverFlowFragment() {
    }

    public static CoverFlowFragment newInstance() {
        return new CoverFlowFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((MainApplication) getActivity().getApplication()).getAppComponent().inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cover_flow, container, false);
        ButterKnife.bind(this, view);

        String id = "The Count of Monte CristoAlexandre Dumas";
        Picasso.with(getContext())
                .load(String.format("%s/books/cover/%s", Constants.INSTABOOK_API_BASE_URL, id))
                .into(coverImageView);

        return view;
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
    public void showCoverGrid(@NonNull List<Book> books) {

    }

    @Override
    public void updateCoverGrid(@NonNull List<Book> additionalBooks) {

    }

    @Override
    public void showBookView(@NonNull Book book) {

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnCoverFlowFragmentInteractionListener {
    }
}
