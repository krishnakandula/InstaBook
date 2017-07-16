package com.rastor.instabook.ui.main;

import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.rastor.instabook.R;
import com.rastor.instabook.app.MainApplication;
import com.rastor.instabook.ui.coverflow.CoverFlowFragment;
import com.rastor.instabook.ui.random.RandomMainFragment;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainContract.View,
        CoverFlowFragment.OnCoverFlowFragmentInteractionListener {

    @Inject MainContract.Presenter mainPresenter;

    @BindView(R.id.mainBottomNavView_mainActivity) BottomNavigationView mainNavMenu;

    private CoverFlowFragment coverFlowFragment;
    private RandomMainFragment randomMainFragment;
    private MainContract.ViewState savedViewState;

    private static final String COVER_FLOW_FRAGMENT_TAG = "cover_flow_fragment_tag";
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
        final String COVERFLOW_BACKSTACK = "coverflow_backstack";
        coverFlowFragment = (CoverFlowFragment) getSupportFragmentManager().findFragmentByTag(COVER_FLOW_FRAGMENT_TAG);

        getSupportFragmentManager().popBackStackImmediate(COVERFLOW_BACKSTACK, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if(coverFlowFragment == null) {
            coverFlowFragment = CoverFlowFragment.newInstance();
        } else {
            transaction.addToBackStack(COVERFLOW_BACKSTACK);
        }
        transaction.replace(R.id.fragmentContainer_mainActivity, coverFlowFragment, COVER_FLOW_FRAGMENT_TAG).commit();
    }

    @Override
    public void launchBookView() {
        final String BOOKVIEW_BACKSTACK = "bookview_backstack";
        randomMainFragment = (RandomMainFragment) getSupportFragmentManager().findFragmentByTag(RandomMainFragment.LOG_TAG);

        getSupportFragmentManager().popBackStackImmediate(BOOKVIEW_BACKSTACK, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if(randomMainFragment == null) {
            randomMainFragment = RandomMainFragment.newInstance();
        } else {
            transaction.addToBackStack(BOOKVIEW_BACKSTACK);
        }
        transaction.replace(R.id.fragmentContainer_mainActivity, randomMainFragment, RandomMainFragment.LOG_TAG).commit();
    }

    @Override
    public void launchFavoritesView() {

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putSerializable(this.VIEW_STATE_TAG, this.mainPresenter.getState());
        super.onSaveInstanceState(outState);
    }
}
