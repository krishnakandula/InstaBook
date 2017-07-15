package com.canvas.instabook.ui.main;

import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.canvas.instabook.R;
import com.canvas.instabook.app.MainApplication;
import com.canvas.instabook.ui.coverflow.CoverFlowContract;
import com.canvas.instabook.ui.coverflow.CoverFlowFragment;
import com.canvas.instabook.ui.random.RandomMainFragment;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainContract.View,
        CoverFlowFragment.OnCoverFlowFragmentInteractionListener {

    @Inject
    MainContract.Presenter mainPresenter;

    @BindView(R.id.mainBottomNavView_mainActivity)
    BottomNavigationView mainNavMenu;

    private CoverFlowContract.View coverFlowView;
    private RandomMainFragment randomMainFragment;

    private static final String COVER_FLOW_FRAGMENT_TAG = "cover_flow_fragment_tag";
    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeNavFragments();
        DaggerMainComponent.builder()
                .appComponent(((MainApplication) getApplication()).getAppComponent())
                .mainModule(new MainModule(this))
                .build()
                .inject(this);

        ButterKnife.bind(this);
        setupBottomNavBar();
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
        mainPresenter.start();
    }

    private void initializeNavFragments() {
        coverFlowView = (CoverFlowFragment) getSupportFragmentManager().findFragmentByTag(COVER_FLOW_FRAGMENT_TAG);
        randomMainFragment = (RandomMainFragment) getSupportFragmentManager().findFragmentByTag(RandomMainFragment.LOG_TAG);

        if(coverFlowView == null) {
            coverFlowView = CoverFlowFragment.newInstance();
        }

        if(randomMainFragment == null) {
            randomMainFragment = RandomMainFragment.newInstance();
        }
    }

    @Override
    public void launchCoverFlowView() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainer_mainActivity, (Fragment) coverFlowView, COVER_FLOW_FRAGMENT_TAG)
                .commit();
    }

    @Override
    public void launchBookView() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainer_mainActivity, randomMainFragment, RandomMainFragment.LOG_TAG)
                .commit();
    }

    @Override
    public void launchFavoritesView() {

    }
}
