package com.tyyar.tyyarfooddelivery.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tyyar.tyyarfooddelivery.R;
import com.tyyar.tyyarfooddelivery.utils.UiUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OrderItemActivity extends AppCompatActivity {
    private static final String TAG = OrderItemActivity.class.getSimpleName();

    @BindView(R.id.toolbar) Toolbar mToolbar;
    @BindView(R.id.item_imageView) ImageView mItemImageView;
    @BindView(R.id.item_name_textView) TextView mItemNameTextView;
    @BindView(R.id.item_description_textView) TextView mItemDescriptionTextView;
    @BindView(R.id.options_recyclerView) RecyclerView mOptionsRecyclerView;
    @BindView(R.id.total_priceTextView) TextView mTotalPriceTextView;
    @BindView(R.id.add_to_order_button) RelativeLayout mAddToOrderButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_item);

        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        UiUtils.showDrawer(this, mToolbar).setSelection(1, false);


    }
}
