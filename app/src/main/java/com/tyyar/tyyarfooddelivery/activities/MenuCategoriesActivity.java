package com.tyyar.tyyarfooddelivery.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.tyyar.tyyarfooddelivery.R;
import com.tyyar.tyyarfooddelivery.adapters.MenuCategoriesAdapter;
import com.tyyar.tyyarfooddelivery.adapters.MerchantsAdapter;
import com.tyyar.tyyarfooddelivery.model.Category;
import com.tyyar.tyyarfooddelivery.utils.UiUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MenuCategoriesActivity extends AppCompatActivity {
    @BindView(R.id.toolbar) Toolbar mToolbar;
    @BindView(R.id.categories_recycler) RecyclerView mCategoriesRecycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_categories);

        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        UiUtils.showDrawer(this, mToolbar).setSelection(1, false);

        ArrayList<Category> menu = getIntent().getParcelableArrayListExtra(MerchantsAdapter.KEY_MENU);

        mCategoriesRecycler.setLayoutManager(new LinearLayoutManager(this));
        mCategoriesRecycler.setAdapter(new MenuCategoriesAdapter(menu));

    }
}
