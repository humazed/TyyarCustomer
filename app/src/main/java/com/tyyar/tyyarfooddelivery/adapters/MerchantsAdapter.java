package com.tyyar.tyyarfooddelivery.adapters;

import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tyyar.tyyarfooddelivery.R;
import com.tyyar.tyyarfooddelivery.model.MerchantView;
import com.tyyar.tyyarfooddelivery.screens.MenuCategoriesActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * User: YourPc
 * Date: 1/27/2017
 */

public class MerchantsAdapter extends BaseQuickAdapter<MerchantView, BaseViewHolder> {
    private static final String TAG = MerchantsAdapter.class.getSimpleName();
    public static final String KEY_MERCHANT = "merchantKey";

    @BindView(R.id.merchant_logo_image) ImageView mMerchantLogoImage;
    @BindView(R.id.merchant_item_status) TextView mMerchantItemStatus;
    @BindView(R.id.merchant_name) TextView mMerchantName;
    @BindView(R.id.merchant_description) TextView mMerchantDescription;
    @BindView(R.id.merchant_delivery_eta) TextView mMerchantDeliveryEta;
    @BindView(R.id.merchant_delivery_fee) TextView mMerchantDeliveryFee;
    @BindView(R.id.row_container) LinearLayout mRowContainer;


    public MerchantsAdapter(List<MerchantView> merchants) {
        super(R.layout.row_merchant, merchants);
        Log.d(TAG, "merchants = " + merchants);
    }

    @Override
    protected void convert(BaseViewHolder viewHolder, MerchantView merchant) {
        ButterKnife.bind(this, viewHolder.getConvertView());
        Glide.with(mContext)
                .load(Uri.parse(merchant.logoImageUrl()))
                .placeholder(R.drawable.placeholder)
                .into(mMerchantLogoImage);

        mMerchantName.setText(merchant.name());
        mMerchantDescription.setText(merchant.description());
        mMerchantDeliveryEta.setText(merchant.deliveryETA());
        mMerchantDeliveryFee.setText(merchant.deliveryFee());

        mRowContainer.setOnClickListener(v -> {
            Intent intent = new Intent(mContext, MenuCategoriesActivity.class);
            intent.putExtra(KEY_MERCHANT, merchant);
            mContext.startActivity(intent);
        });
    }
}