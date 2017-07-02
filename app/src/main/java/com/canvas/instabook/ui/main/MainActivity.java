package com.canvas.instabook.ui.main;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.canvas.instabook.R;
import com.canvas.instabook.app.MainApplication;
import com.canvas.instabook.ui.coverflow.CoverFlowFragment;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainContract.View,
        CoverFlowFragment.OnCoverFlowFragmentInteractionListener {

    @Inject
    MainContract.Presenter presenter;

    @BindView(R.id.mainBottomNavView_mainActivity)
    BottomNavigationView mainNavMenu;

    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((MainApplication) getApplication()).getAppComponent().inject(this);
        ButterKnife.bind(this);

        presenter.initialize(this);

        mainNavMenu.setOnNavigationItemSelectedListener(menuItem -> {
            switch (menuItem.getItemId()) {
                case R.id.menu_item_activity_main_cover_flow:
                    presenter.onCoverFlowNavItemClicked();
                    return true;
                default:
                    return false;
            }
        });
    }

    @Override
    public void launchCoverFlowView() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainer_mainActivity, CoverFlowFragment.newInstance(), CoverFlowFragment.TAG)
                .commit();
    }

    @Override
    public void launchBookView() {

    }

    @Override
    public void launchFavoritesView() {

    }
}
