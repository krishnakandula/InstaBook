package com.canvas.instabook.ui.coverflow;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.canvas.instabook.R;
import com.canvas.instabook.app.Constants;
import com.canvas.instabook.data.models.Book;
import com.google.common.collect.Lists;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Krishna Chaitanya Kandula on 7/5/17.
 */

public class CoverFlowAdapter extends RecyclerView.Adapter<CoverFlowAdapter.CoverFlowViewHolder> {

    private List<Book> books;

    @NonNull
    private Context context;

    public CoverFlowAdapter(@NonNull Context context) {
        this.context = context;
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
        holder.onBind(books.get(position));
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
        int currentRange = this.books.size() - 1;
        this.books.addAll(books);
        notifyItemRangeInserted(currentRange, this.books.size() - 1);
    }

    public class CoverFlowViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.bookTitleTextView_coverFlowViewHolder)
        TextView bookTitleTextView;

        @BindView(R.id.bookCoverImageView_coverFlowViewHolder)
        ImageView bookCoverImageView;

        public CoverFlowViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void onBind(Book book) {
            Picasso.with(context)
                    .load(String.format("%s/books/cover/%s", Constants.INSTABOOK_API_BASE_URL, book.getId()))
                    .resizeDimen(R.dimen.image_width, R.dimen.image_height)
                    .into(bookCoverImageView);
            bookTitleTextView.setText(book.getTitle());
        }
    }
}
