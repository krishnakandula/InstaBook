package com.rastor.instabook.ui.main;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.rastor.instabook.R;
import com.rastor.instabook.app.MainApplication;
import com.rastor.instabook.ui.coverflow.CoverFlowFragment;
import com.rastor.instabook.ui.favorites.FavoritesFragment;
import com.rastor.instabook.ui.random.RandomMainFragment;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainContract.View {

    @Inject MainContract.Presenter mainPresenter;

    @BindView(R.id.mainBottomNavView_mainActivity) BottomNavigationView mainNavMenu;

    private CoverFlowFragment coverFlowFragment;
    private RandomMainFragment randomMainFragment;
    private FavoritesFragment favoritesFragment;
    private MainContract.ViewState savedViewState;

    private static final String VIEW_STATE_TAG = "view_state_tag";
    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DaggerMainComponent.builder()
                .appComponent(((MainApplication) getApplication()).getAppComponent())
                .mainModule(new MainModule(this))
                .build()
                .inject(this);

        ButterKnife.bind(this);
        setupBottomNavBar();
        if(savedInstanceState != null) {
            this.savedViewState = (MainContract.ViewState) savedInstanceState.getSerializable(this.VIEW_STATE_TAG);
        }
    }

    private void setupBottomNavBar() {
        mainNavMenu.setOnNavigationItemSelectedListener(menuItem -> {
            switch (menuItem.getItemId()) {
                case R.id.menu_item_activity_main_cover_flow:
                    mainPresenter.onCoverFlowNavItemClicked();
                    return true;
                case R.id.menu_item_activity_main_random_book:
                    mainPresenter.onRandomBookNavItemClicked();
                    return true;
                case R.id.menu_item_activity_main_favorites:
                    mainPresenter.onFavoritesNavItemClicked();
                    return true;
                default:
                    return false;
            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();
        mainPresenter.start(this.savedViewState);
    }

    @Override
    public void launchCoverFlowView() {
        coverFlowFragment = (CoverFlowFragment) getSupportFragmentManager().findFragmentByTag(CoverFlowFragment.LOG_TAG);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if(randomMainFragment != null && randomMainFragment.isAdded()) {
            transaction.detach(randomMainFragment);
        }

        if(favoritesFragment != null && favoritesFragment.isAdded()) {
            transaction.detach(favoritesFragment);
        }

        if(coverFlowFragment == null) {
            coverFlowFragment = CoverFlowFragment.newInstance();
            transaction.add(R.id.fragmentContainer_mainActivity, coverFlowFragment, CoverFlowFragment.LOG_TAG);
        } else {
            transaction.attach(coverFlowFragment);
        }
        transaction.commit();
    }

    @Override
    public void launchBookView() {
        randomMainFragment = (RandomMainFragment) getSupportFragmentManager().findFragmentByTag(RandomMainFragment.LOG_TAG);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if(coverFlowFragment != null && coverFlowFragment.isAdded()) {
            transaction.detach(coverFlowFragment);
        }

        if(favoritesFragment != null && favoritesFragment.isAdded()) {
            transaction.detach(favoritesFragment);
        }

        if(randomMainFragment == null) {
            randomMainFragment = RandomMainFragment.newInstance();
            transaction.add(R.id.fragmentContainer_mainActivity, randomMainFragment, RandomMainFragment.LOG_TAG);
        } else {
            transaction.attach(randomMainFragment);
        }
        transaction.commit();
    }

    @Override
    public void launchFavoritesView() {
        favoritesFragment = (FavoritesFragment) getSupportFragmentManager().findFragmentByTag(FavoritesFragment.LOG_TAG);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if(coverFlowFragment != null && coverFlowFragment.isAdded()) {
            transaction.detach(coverFlowFragment);
        }

        if(randomMainFragment != null && randomMainFragment.isAdded()) {
            transaction.detach(randomMainFragment);
        }

        if(favoritesFragment == null) {
            favoritesFragment = FavoritesFragment.newInstance();
            transaction.add(R.id.fragmentContainer_mainActivity, favoritesFragment, FavoritesFragment.LOG_TAG);
        } else {
            transaction.attach(favoritesFragment);
        }
        transaction.commit();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putSerializable(this.VIEW_STATE_TAG, this.mainPresenter.getState());
        super.onSaveInstanceState(outState);
    }
}
