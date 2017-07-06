package com.canvas.instabook.ui.main;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.canvas.instabook.R;
import com.canvas.instabook.app.MainApplication;
import com.canvas.instabook.ui.coverflow.CoverFlowContract;
import com.canvas.instabook.ui.coverflow.CoverFlowFragment;
import com.canvas.instabook.ui.coverflow.CoverFlowPresenter;
import com.canvas.instabook.ui.coverflow.CoverFlowPresenterModule;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainContract.View,
        CoverFlowFragment.OnCoverFlowFragmentInteractionListener {

    @Inject
    MainContract.Presenter mainPresenter;

    @Inject
    CoverFlowPresenter coverFlowPresenter;

    @BindView(R.id.mainBottomNavView_mainActivity)
    BottomNavigationView mainNavMenu;

    private CoverFlowContract.View coverFlowView;

    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeNavFragments();

        DaggerMainComponent.builder()
                .appComponent(((MainApplication) getApplication()).getAppComponent())
                .mainPresenterModule(new MainPresenterModule(this))
                .coverFlowPresenterModule(new CoverFlowPresenterModule(coverFlowView))
                .build()
                .inject(this);

        ButterKnife.bind(this);

        mainNavMenu.setOnNavigationItemSelectedListener(menuItem -> {
            switch (menuItem.getItemId()) {
                case R.id.menu_item_activity_main_cover_flow:
                    mainPresenter.onCoverFlowNavItemClicked();
                    return true;
                default:
                    return false;
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        mainPresenter.start();
    }

    private void initializeNavFragments() {
        coverFlowView = CoverFlowFragment.newInstance();
    }

    @Override
    public void launchCoverFlowView() {
        if(coverFlowView == null) {
            coverFlowView = CoverFlowFragment.newInstance();
        }
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainer_mainActivity, (Fragment) coverFlowView)
                .commit();
    }

    @Override
    public void launchBookView() {

    }

    @Override
    public void launchFavoritesView() {

    }
}
