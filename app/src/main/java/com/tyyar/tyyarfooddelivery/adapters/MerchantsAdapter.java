package com.tyyar.tyyarfooddelivery.adapters;

import android.content.Intent;
import android.net.Uri;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tyyar.tyyarfooddelivery.R;
import com.tyyar.tyyarfooddelivery.activities.MenuCategoriesActivity;
import com.tyyar.tyyarfooddelivery.model.Merchant;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * User: YourPc
 * Date: 1/27/2017
 */

public class MerchantsAdapter extends BaseQuickAdapter<Merchant, BaseViewHolder> {
    private static final String TAG = MerchantsAdapter.class.getSimpleName();

    @BindView(R.id.merchant_logo_image) ImageView mMerchantLogoImage;
    @BindView(R.id.merchant_item_status) TextView mMerchantItemStatus;
    @BindView(R.id.merchant_name) TextView mMerchantName;
    @BindView(R.id.merchant_description) TextView mMerchantDescription;
    @BindView(R.id.merchant_delivery_eta) TextView mMerchantDeliveryEta;
    @BindView(R.id.merchant_delivery_fee) TextView mMerchantDeliveryFee;
    @BindView(R.id.row_container) LinearLayout mRowContainer;


    public MerchantsAdapter(List<Merchant> merchants) {
        super(R.layout.row_merchant, merchants);
    }

    @Override
    protected void convert(BaseViewHolder viewHolder, Merchant merchant) {
        ButterKnife.bind(this, viewHolder.getConvertView());
        Glide.with(mContext)
                .load(Uri.parse(merchant.logoImageUri()))
                .placeholder(R.drawable.placeholder)
                .into(mMerchantLogoImage);

        mMerchantName.setText(merchant.name());
        mMerchantDescription.setText(merchant.description());
        mMerchantDeliveryEta.setText(merchant.deliveryETA());
        mMerchantDeliveryFee.setText(merchant.deliveryFee());

        mRowContainer.setOnClickListener(v -> mContext.startActivity(new Intent(mContext, MenuCategoriesActivity.class)));
    }
}