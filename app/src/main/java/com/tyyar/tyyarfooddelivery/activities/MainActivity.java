package com.tyyar.tyyarfooddelivery.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.LinearLayout;

import com.tyyar.tyyarfooddelivery.R;
import com.tyyar.tyyarfooddelivery.adapters.MerchantsAdapter;
import com.tyyar.tyyarfooddelivery.model.Merchant;
import com.tyyar.tyyarfooddelivery.utils.UiUtils;

import java.util.ArrayList;

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
        UiUtils.showDrawer(this, mToolbar).setSelection(1, false);

        ArrayList<Merchant> merchants = new ArrayList<Merchant>() {{
//            add(Merchant.create("Burger king", "Headquartered ", "file:///android_asset/abouShakra.png"));
//            add(Merchant.create("Cook Door", "Sandwiches, Fast Food", "file:///android_asset/ArabiataAlShabrawy.png"));
//            add(Merchant.create("Farroga ", "Oriental ,Sandwiches ,Grills", "file:///android_asset/BobSushi.jpg"));
//            add(Merchant.create("Papa John", "pizza", "file:///android_asset/Burger king .png"));
//            add(Merchant.create("Arabiata Al", "Sandwiches and Oriental food", "file:///android_asset/Canton.jpg"));
//            add(Merchant.create("Bob Sushi", "Japanese Food", "file:///android_asset/Cook Door.png"));
//            add(Merchant.create("Iskandarany ", "Fast Food, Grills, Oriental ,Pasta , Sandwiches", "file:///android_asset/Farroga.png"));
        }};


        mMerchantsRecycler.setLayoutManager(new LinearLayoutManager(this));
        mMerchantsRecycler.setAdapter(new MerchantsAdapter(merchants));

    }
}
