package com.rastor.instabook.ui.coverflow;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.rastor.instabook.R;
import com.rastor.instabook.data.books.models.Book;
import com.google.common.collect.Lists;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import lombok.NonNull;

import static com.rastor.instabook.network.ImageRouteCreator.*;

/**
 * Created by Krishna Chaitanya Kandula on 7/5/17.
 */

public class CoverFlowAdapter extends RecyclerView.Adapter<CoverFlowAdapter.CoverFlowViewHolder> {

    @NonNull
    private final CoverFlowItemViewInteractionListener itemViewTouchListener;

    private List<Book> books;

    private Context context;

    public CoverFlowAdapter(@NonNull Context context,
                            CoverFlowItemViewInteractionListener itemViewTouchListener) {
        this.context = context;
        this.itemViewTouchListener = itemViewTouchListener;
        books = Lists.newArrayList();
    }

    @Override
    public CoverFlowViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View itemView = inflater.inflate(R.layout.itemview_cover_flow, parent, false);

        return new CoverFlowViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CoverFlowViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    public void setData(List<Book> books) {
        notifyItemRangeRemoved(0, getItemCount());
        this.books = books;
        notifyItemRangeInserted(0, getItemCount());
    }

    public List<Book> getData() {
        return this.books;
    }

    public void addData(List<Book> books) {
        int currentRange = this.books.size();
        this.books.addAll(books);
        notifyItemRangeInserted(currentRange, this.books.size());
    }

    public class CoverFlowViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.bookCoverImageView_favoritesItemView)
        ImageView bookCoverImageView;

        int position;

        public CoverFlowViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            bookCoverImageView.setOnClickListener(this::onClick);
        }

        public void onBind(int position) {
            this.position = position;
            Book book = books.get(position);
            if (position == books.size() - 1) {
                //Load more books
                itemViewTouchListener.onReachCoverFlowEnd();
            }

            bookCoverImageView.post(() -> {
                Picasso.with(context)
                        .load(createCoverImageRoute(book.getId()))
                        .placeholder(R.drawable.ic_cloud_download_black_24dp)
                        .resize(bookCoverImageView.getMeasuredWidth(), bookCoverImageView.getMeasuredHeight())
                        .centerCrop()
                        .into(bookCoverImageView);
            });

        }

        @Override
        public void onClick(View v) {
            itemViewTouchListener.onClickCoverFlowItem(this.position);
        }
    }

    interface CoverFlowItemViewInteractionListener {
        void onClickCoverFlowItem(int position);

        void onReachCoverFlowEnd();
    }
}
