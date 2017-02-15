package com.tyyar.tyyarfooddelivery.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tyyar.tyyarfooddelivery.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CheckoutActivity extends AppCompatActivity {
    private static final String TAG = CheckoutActivity.class.getSimpleName();

    @BindView(R.id.toolbar) Toolbar mToolbar;
    @BindView(R.id.checkout_error_textView) TextView mCheckoutErrorTextView;
    @BindView(R.id.address_title_textView) TextView mAddressTitleTextView;
    @BindView(R.id.address_details_textView) TextView mAddressDetailsTextView;
    @BindView(R.id.change_address_arrow) ImageView mChangeAddressArrow;
    @BindView(R.id.delivery_instructions_editText) EditText mDeliveryInstructionsEditText;
    @BindView(R.id.eta_content_textView) TextView mEtaContentTextView;
    @BindView(R.id.credit_card_type_textView) TextView mCreditCardTypeTextView;
    @BindView(R.id.last_four_digits_textView) TextView mLastFourDigitsTextView;
    @BindView(R.id.menu_options_header) TextView mMenuOptionsHeader;
    @BindView(R.id.menu_options_list) LinearLayout mMenuOptionsList;
    @BindView(R.id.menu_options_container) LinearLayout mMenuOptionsContainer;
    @BindView(R.id.driver_tip_title) TextView mDriverTipTitle;
    @BindView(R.id.driver_tip_textView) TextView mDriverTipTextView;
    @BindView(R.id.price_textView) TextView mPriceTextView;
    @BindView(R.id.order_button) RelativeLayout mOrderButton;
    @BindView(R.id.activity_checkout) LinearLayout mActivityCheckout;

    private double mTotalPrice;
    private double mFinalTotalPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);

        getSupportActionBar().setTitle(R.string.chat_activity_title);

        Intent intent = getIntent();
        mTotalPrice = intent.getDoubleExtra(CartActivity.KEY_TOTAL_PRICE, 0);

        mDriverTipTextView.setText(getString(R.string.common_price, calculateTip(.15)));

    }

    private double calculateTip(double tipPercentage) {
        double tip = mTotalPrice * tipPercentage;
        mDriverTipTextView.setText(getString(R.string.common_price, tip));
        mFinalTotalPrice = mTotalPrice + tip;
        mPriceTextView.setText(getString(R.string.common_price, mFinalTotalPrice));
        return tip;
    }

    public void onTipRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch (view.getId()) {
            case R.id.tip_10: if (checked)
                calculateTip(.10);
                break;
            case R.id.tip_15: if (checked)
                calculateTip(.15);
                break;
            case R.id.tip_20: if (checked)
                calculateTip(.20);
                break;
            case R.id.tip_other: if (checked)
                calculateTip(0);
                break;
        }
    }
}