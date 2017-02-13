package com.tyyar.tyyarfooddelivery.adapters;

import android.content.Intent;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tyyar.tyyarfooddelivery.R;
import com.tyyar.tyyarfooddelivery.activities.OrderItemActivity;
import com.tyyar.tyyarfooddelivery.model.Item;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * User: YourPc
 * Date: 1/27/2017
 */

public class MenuCategoryDetailsAdapter extends BaseQuickAdapter<Item, BaseViewHolder> {
    private static final String TAG = MenuCategoryDetailsAdapter.class.getSimpleName();
    public static final String KEY_ITEM = "itemKey";

    @BindView(R.id.item_name_textView) TextView mItemNameTextView;
    @BindView(R.id.item_description_textView) TextView mDescription;
    @BindView(R.id.row_container) RelativeLayout mRowContainer;
    @BindView(R.id.item_price_textView) TextView mItemPriceTextView;

    public MenuCategoryDetailsAdapter(ArrayList<Item> items) {
        super(R.layout.row_item, items);
    }

    @Override
    protected void convert(BaseViewHolder viewHolder, Item item) {
        ButterKnife.bind(this, viewHolder.getConvertView());

        mItemNameTextView.setText(item.name());
        mItemPriceTextView.setText(mContext.getString(R.string.common_price, item.price()));

        mRowContainer.setOnClickListener(v -> {
            Intent intent = new Intent(mContext, OrderItemActivity.class);
            intent.putExtra(KEY_ITEM, item);
            mContext.startActivity(intent);
        });

    }
}