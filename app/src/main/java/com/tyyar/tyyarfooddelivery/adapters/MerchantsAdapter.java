package com.tyyar.tyyarfooddelivery.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.facebook.drawee.view.SimpleDraweeView;
import com.tyyar.tyyarfooddelivery.R;
import com.tyyar.tyyarfooddelivery.activities.MenuCategoryActivity;
import com.tyyar.tyyarfooddelivery.model.Merchant;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * User: YourPc
 * Date: 1/27/2017
 */

public class MerchantsAdapter extends RecyclerView.Adapter<MerchantsAdapter.ViewHolder> {
    private static final String TAG = MerchantsAdapter.class.getSimpleName();


    private List<Merchant> mMerchants;

    public MerchantsAdapter(List<Merchant> merchants) {
        mMerchants = merchants;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_merchant, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Merchant merchant = mMerchants.get(position);
        holder.bind(merchant);
    }

    @Override
    public int getItemCount() {
        return mMerchants.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.merchant_logo_image) SimpleDraweeView mMerchantLogoImage;
        @BindView(R.id.merchant_item_status) TextView mMerchantItemStatus;
        @BindView(R.id.merchant_name) TextView mMerchantName;
        @BindView(R.id.merchant_description) TextView mMerchantDescription;
        @BindView(R.id.merchant_delivery_eta) TextView mMerchantDeliveryEta;
        @BindView(R.id.merchant_delivery_fee) TextView mMerchantDeliveryFee;
        private Context mContext;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();

            itemView.setOnClickListener(v -> {
                mContext.startActivity(new Intent(mContext, MenuCategoryActivity.class));
            });
        }

        public void bind(Merchant merchant) {
            Glide.with(mContext)
                    .load(Uri.parse(merchant.logoImageUri()))
                    .into(mMerchantLogoImage);
            mMerchantName.setText(merchant.name());
            mMerchantDescription.setText(merchant.description());
            mMerchantDeliveryEta.setText(merchant.deliveryETA());
            mMerchantDeliveryFee.setText(merchant.deliveryFee());
        }
    }


}