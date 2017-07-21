package com.rastor.instabook.data.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import lombok.Data;
import lombok.NonNull;

/**
 * Created by Krishna Chaitanya Kandula on 6/25/17.
 */
@Data
public class Book implements Parcelable {

    //Needed for parceling
    protected Book(Parcel in) {
        id = in.readString();
        author = in.readString();
        title = in.readString();
        page = in.readString();
        information = in.readString();
    }

    @NonNull
    @SerializedName("_id")
    private final String id;

    @NonNull
    @SerializedName("author")
    private final String author;

    @NonNull
    @SerializedName("title")
    private final String title;

    @NonNull
    @SerializedName("page")
    private final String page;

    @NonNull
    @SerializedName("information")
    private final String information;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(author);
        dest.writeString(title);
        dest.writeString(page);
        dest.writeString(information);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Book> CREATOR = new Parcelable.Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel in) {
            return new Book(in);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };
}
