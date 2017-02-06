package com.tyyar.tyyarfooddelivery.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.tyyar.tyyarfooddelivery.R;
import com.tyyar.tyyarfooddelivery.adapters.MenuCategoryDetailsAdapter;
import com.tyyar.tyyarfooddelivery.utils.UiUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MenuCategoryItemsActivity extends AppCompatActivity {

    @BindView(R.id.toolbar) Toolbar mToolbar;
    @BindView(R.id.recycler) RecyclerView mRecycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catergory_items);

        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        UiUtils.showDrawer(this, mToolbar).setSelection(1, false);

        mRecycler.setLayoutManager(new LinearLayoutManager(this));
        mRecycler.setAdapter(new MenuCategoryDetailsAdapter(new ArrayList<String>() {{
            add("Neapolitan Pizza");
            add("Chicago Pizza");
            add("Sicilian Pizza");
            add("New York Style Pizza");
            add("Greek Pizza");
        }}));


    }
}
