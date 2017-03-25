package com.tyyar.tyyarfooddelivery.screens.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.Spinner;

import com.tyyar.tyyarfooddelivery.R;
import com.tyyar.tyyarfooddelivery.screens.SearchMerchantsActivity;
import com.tyyar.tyyarfooddelivery.screens.intro.IntroActivity;
import com.tyyar.tyyarfooddelivery.utils.UiUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.tyyar.tyyarfooddelivery.screens.intro.IntroActivity.KEY_FROM_INTRO;
import static com.tyyar.tyyarfooddelivery.screens.intro.login_and_signup.SignupFragment.KEY_FROM_SIGNUP;

/**
 * MainActivity
 */
public class HomeActivity extends AppCompatActivity {

    @BindView(R.id.toolbar) Toolbar mToolbar;
    @BindView(R.id.menu_spinner) Spinner mSpinner;
    @BindView(R.id.menu_search) ImageView mMenuSearch;
    @BindView(R.id.tabs) TabLayout mTabLayout;
    @BindView(R.id.container) ViewPager mContainer;
    @BindView(R.id.main_content) CoordinatorLayout mMainContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        UiUtils.showDrawer(this, mToolbar, 1);
        mMenuSearch.setOnClickListener(v -> startActivity(new Intent(this, SearchMerchantsActivity.class)));
        mContainer.setAdapter(new SectionsPagerAdapter(getSupportFragmentManager()));
        mTabLayout.setupWithViewPager(mContainer);

        if (!(getIntent().getBooleanExtra(KEY_FROM_INTRO, false) ||
                getIntent().getBooleanExtra(KEY_FROM_SIGNUP, false))) {
            startActivity(new Intent(this, IntroActivity.class));
        }

    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the tabs.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return RestaurantsFragment.newInstance("");
                case 1:
                    return PharmaciesFragment.newInstance("");
                case 2:
                    return MarketsFragment.newInstance("");
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Restaurants";
                case 1:
                    return "Pharmacies";
                case 2:
                    return "Supermarkets";
                default:
                    return "";
            }
        }
    }

}
