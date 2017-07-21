package com.rastor.instabook.app;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * Created by Krishna Chaitanya Kandula on 7/20/17.
 */

@RequiredArgsConstructor
public class AppStatus {

    @NonNull
    private Context applicationContext;

    public boolean isConnected() {
        ConnectivityManager cm = (ConnectivityManager) applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();

        return (networkInfo != null) && networkInfo.isConnectedOrConnecting();
    }
}
