package com.tyyar.tyyarfooddelivery.screens;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.tyyar.tyyarfooddelivery.R;
import com.tyyar.tyyarfooddelivery.utils.UiUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OrdersHistoryActivity extends AppCompatActivity {
    private static final String TAG = OrdersHistoryActivity.class.getSimpleName();

    @BindView(R.id.toolbar) Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders_history);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        UiUtils.showDrawer(this, mToolbar, 2);


    }
}
