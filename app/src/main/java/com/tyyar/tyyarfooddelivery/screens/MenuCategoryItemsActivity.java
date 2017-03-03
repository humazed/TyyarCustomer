package com.tyyar.tyyarfooddelivery.screens;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.tyyar.tyyarfooddelivery.App;
import com.tyyar.tyyarfooddelivery.R;
import com.tyyar.tyyarfooddelivery.adapters.MenuCategoriesAdapter;
import com.tyyar.tyyarfooddelivery.adapters.MenuCategoryDetailsAdapter;
import com.tyyar.tyyarfooddelivery.model.Category;
import com.tyyar.tyyarfooddelivery.utils.UiUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MenuCategoryItemsActivity extends AppCompatActivity {
    private static final String TAG = MenuCategoryItemsActivity.class.getSimpleName();

    @BindView(R.id.toolbar) Toolbar mToolbar;
    @BindView(R.id.recycler) RecyclerView mRecycler;
    @BindView(R.id.cart_fab) FloatingActionButton mCartFab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catergory_items);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        UiUtils.showDrawer(this, mToolbar, 1);

        Category category = getIntent().getParcelableExtra(MenuCategoriesAdapter.KEY_CATEGORY);

        if (getSupportActionBar() != null) getSupportActionBar().setTitle(category.name());

        mRecycler.setLayoutManager(new LinearLayoutManager(this));
        mRecycler.setAdapter(new MenuCategoryDetailsAdapter(category));

        mCartFab.setOnClickListener(v -> startActivity(new Intent(this, CartActivity.class)));

    }


    @Override
    protected void onResume() {
        super.onResume();
        if (((App) getApplication()).getCartItems().isEmpty())
            mCartFab.setVisibility(View.GONE);
        else mCartFab.setVisibility(View.VISIBLE);
    }
}
