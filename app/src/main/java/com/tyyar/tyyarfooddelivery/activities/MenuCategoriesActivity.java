package com.tyyar.tyyarfooddelivery.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.tyyar.tyyarfooddelivery.R;
import com.tyyar.tyyarfooddelivery.adapters.MenuCategoriesAdapter;
import com.tyyar.tyyarfooddelivery.adapters.MerchantsAdapter;
import com.tyyar.tyyarfooddelivery.model.Merchant;
import com.tyyar.tyyarfooddelivery.utils.UiUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MenuCategoriesActivity extends AppCompatActivity {
    private static final String TAG = MenuCategoriesActivity.class.getSimpleName();

    @BindView(R.id.toolbar) Toolbar mToolbar;
    @BindView(R.id.categories_recycler) RecyclerView mCategoriesRecycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_categories);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        UiUtils.showDrawer(this, mToolbar, 1);

        Merchant merchant = getIntent().getParcelableExtra(MerchantsAdapter.KEY_MERCHANT);

        if (getSupportActionBar() != null) getSupportActionBar().setTitle(merchant.name());

        mCategoriesRecycler.setLayoutManager(new LinearLayoutManager(this));
        mCategoriesRecycler.setAdapter(new MenuCategoriesAdapter(merchant.menu()));

    }
}
