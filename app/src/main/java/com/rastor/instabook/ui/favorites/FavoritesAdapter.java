package com.rastor.instabook.ui.favorites;

import android.content.Context;
import android.content.Intent;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.common.collect.Lists;
import com.rastor.instabook.R;
import com.rastor.instabook.data.books.models.Book;
import com.rastor.instabook.network.ImageRouteCreator;
import com.rastor.instabook.ui.information.BookInformationActivity;
import com.rastor.instabook.util.BookListDiffUtil;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * Created by Krishna Chaitanya Kandula on 7/27/17.
 */

@RequiredArgsConstructor
public class FavoritesAdapter extends RecyclerView.Adapter<FavoritesAdapter.FavoritesViewHolder> {

    @NonNull
    private final Context context;

    private List<Book> data = Lists.newArrayList();

    @Override
    public FavoritesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(this.context);
        View itemView = inflater.inflate(R.layout.itemview_favorites, parent, false);

        return new FavoritesViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(FavoritesViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setData(List<Book> data) {
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new BookListDiffUtil(this.data, data));
        this.data.clear();
        this.data.addAll(data);
        diffResult.dispatchUpdatesTo(this);
    }

    public void addData(List<Book> data) {
        int currentRange = this.data.size();
        this.data.addAll(data);
        notifyItemRangeInserted(currentRange, this.data.size());
    }

    public List<Book> getData() {
        return this.data;
    }

    public class FavoritesViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.bookCoverImageView_favoritesItemView) ImageView bookCoverImageView;
        @BindView(R.id.bookTitleTextView_favoritesItemView) TextView bookTitleTextView;
        @BindView(R.id.bookInfoTextView_favoritesItemView) TextView bookInfoTextView;

        private Book currentBook;

        public FavoritesViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void onBind(int position) {
            currentBook = data.get(position);

            bookTitleTextView.setText(currentBook.getTitle());
            bookInfoTextView.setText(currentBook.getInformation());

            bookCoverImageView.post(() -> {
                Picasso.with(context)
                        .load(ImageRouteCreator.createCoverImageRoute(currentBook.getId()))
                        .resize(bookCoverImageView.getMeasuredWidth(), bookCoverImageView.getMeasuredHeight())
                        .centerCrop()
                        .into(bookCoverImageView);
            });
        }

        @OnClick(R.id.viewInfoBtn_favoritesItemView)
        public void onClickViewInfoBtn() {
            Intent intent = new Intent(context, BookInformationActivity.class);
            intent.putExtra(BookInformationActivity.BOOK_ID_TAG, this.currentBook.getId());
            context.startActivity(intent);
        }
    }
}
