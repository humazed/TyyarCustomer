package com.tyyar.tyyarfooddelivery.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.LinearLayout;

import com.tyyar.tyyarfooddelivery.DataServer;
import com.tyyar.tyyarfooddelivery.R;
import com.tyyar.tyyarfooddelivery.adapters.MerchantsAdapter;
import com.tyyar.tyyarfooddelivery.utils.UiUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.toolbar) Toolbar mToolbar;
    @BindView(R.id.merchants_recycler) RecyclerView mMerchantsRecycler;
    @BindView(R.id.activity_main) LinearLayout mActivityMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        UiUtils.showDrawer(this, mToolbar, 1);

        mMerchantsRecycler.setLayoutManager(new LinearLayoutManager(this));
        mMerchantsRecycler.setAdapter(new MerchantsAdapter(DataServer.getMerchants()));

    }
}
