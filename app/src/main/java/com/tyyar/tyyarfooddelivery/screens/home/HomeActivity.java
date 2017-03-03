package com.tyyar.tyyarfooddelivery.screens.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.tyyar.tyyarfooddelivery.DataServer;
import com.tyyar.tyyarfooddelivery.R;
import com.tyyar.tyyarfooddelivery.adapters.MerchantsAdapter;
import com.tyyar.tyyarfooddelivery.screens.SearchMerchantsActivity;
import com.tyyar.tyyarfooddelivery.utils.UiUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity {

    @BindView(R.id.toolbar) Toolbar mToolbar;
    @BindView(R.id.merchants_recycler) RecyclerView mMerchantsRecycler;
    @BindView(R.id.activity_main) LinearLayout mActivityMain;
    @BindView(R.id.menu_spinner) Spinner mSpinner;
    @BindView(R.id.menu_search) ImageView mMenuSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        UiUtils.showDrawer(this, mToolbar, 1);
        mMenuSearch.setOnClickListener(v -> startActivity(new Intent(this, SearchMerchantsActivity.class)));


        mMerchantsRecycler.setLayoutManager(new LinearLayoutManager(this));
        mMerchantsRecycler.setAdapter(new MerchantsAdapter(DataServer.getMerchants()));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
