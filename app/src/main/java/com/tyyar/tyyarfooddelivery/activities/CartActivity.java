package com.tyyar.tyyarfooddelivery.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tyyar.tyyarfooddelivery.App;
import com.tyyar.tyyarfooddelivery.R;
import com.tyyar.tyyarfooddelivery.adapters.CartAdapter;
import com.tyyar.tyyarfooddelivery.model.CartItem;
import com.tyyar.tyyarfooddelivery.utils.UiUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CartActivity extends AppCompatActivity {
    private static final String TAG = CartActivity.class.getSimpleName();
    public static final String KEY_TOTAL_PRICE = "totalPrice";

    @BindView(R.id.toolbar) Toolbar mToolbar;
    @BindView(R.id.order_recyclerView) RecyclerView mOrderRecyclerView;
    @BindView(R.id.checkout_subtotal_textView) TextView mCheckoutSubtotalTextView;
    @BindView(R.id.checkout_button) LinearLayout mCheckoutButton;
    @BindView(R.id.subtotal_container) LinearLayout mSubtotalContainer;
    @BindView(R.id.subtotal_textView) TextView mSubtotalTextView;
    @BindView(R.id.taxes_and_fees_container) LinearLayout mTaxesAndFeesContainer;
    @BindView(R.id.taxes_textView) TextView mTaxesTextView;
    @BindView(R.id.delivery_container) LinearLayout mDeliveryContainer;
    @BindView(R.id.delivery_textView) TextView mDeliveryTextView;
    @BindView(R.id.discount_container) LinearLayout mDiscountContainer;
    @BindView(R.id.discount_textView) TextView mDiscountTextView;
    @BindView(R.id.service_fee_container) RelativeLayout mServiceFeeContainer;
    @BindView(R.id.service_fee_textView) TextView mServiceFeeTextView;
    @BindView(R.id.extra_order_fee_container) RelativeLayout mExtraOrderFeeContainer;
    @BindView(R.id.extra_order_fee_textView) TextView mExtraOrderFeeTextView;
    @BindView(R.id.tip_info_container) LinearLayout mTipInfoContainer;
    @BindView(R.id.tip_textView) TextView mTipTextView;
    @BindView(R.id.total_container) LinearLayout mTotalContainer;
    @BindView(R.id.total_textView) TextView mTotalTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        UiUtils.showDrawer(this, mToolbar, 1);

        ArrayList<CartItem> cartItems = ((App) getApplication()).getCartItems();
        Log.d(TAG, "cartItems = " + cartItems);

        mOrderRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mOrderRecyclerView.setAdapter(new CartAdapter(cartItems));
        mOrderRecyclerView.setHasFixedSize(true);
        mOrderRecyclerView.setNestedScrollingEnabled(false);

        double totalPrice = getTotalPrice(cartItems);

        mCheckoutSubtotalTextView.setText(getString(R.string.common_price, totalPrice));
        mSubtotalTextView.setText(getString(R.string.common_price, totalPrice));
//        mDeliveryTextView.setText(getString(R.string.common_price, totalPrice));
//        mDiscountTextView.setText(getString(R.string.common_price, totalPrice));
//        mTotalTextView.setText(getString(R.string.common_price, totalPrice));

        mCheckoutButton.setOnClickListener(v -> {
            Intent intent = new Intent(CartActivity.this, CheckoutActivity.class);
            intent.putExtra(KEY_TOTAL_PRICE, totalPrice);

            startActivity(intent);
        });
    }

    private double getTotalPrice(ArrayList<CartItem> cartItems) {
        double totalPrice = 0;
        for (CartItem cartItem : cartItems)
            totalPrice += cartItem.totalPrice() * cartItem.itemCount();
        return totalPrice;
    }
}
