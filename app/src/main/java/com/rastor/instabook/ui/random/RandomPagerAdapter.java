package com.rastor.instabook.ui.random;

import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.rastor.instabook.data.models.Book;

import java.util.List;

import lombok.NonNull;

/**
 * Created by Krishna Chaitanya Kandula on 7/14/17.
 */

public class RandomPagerAdapter extends FragmentStatePagerAdapter {

    private List<Book> data;

    public RandomPagerAdapter(@NonNull FragmentManager fm, List<Book> data) {
        super(fm);
        setData(data);
    }

    @Override
    public Fragment getItem(int position) {
        return RandomPageFragment.newInstance(data.get(position));
    }

    @Override
    public int getCount() {
        return data.size();
    }

    public void setData(List<Book> newData) {
        this.data = newData;
        notifyDataSetChanged();
    }

    public void addData(List<Book> additionalData) {
        this.data.addAll(additionalData);
        notifyDataSetChanged();
    }

    @Override
    public void restoreState(Parcelable state, ClassLoader loader) {
        //Do nothing
    }
}
