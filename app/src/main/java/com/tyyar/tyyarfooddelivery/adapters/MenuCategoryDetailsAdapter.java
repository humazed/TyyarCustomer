package com.tyyar.tyyarfooddelivery.adapters;

import android.content.Intent;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tyyar.tyyarfooddelivery.R;
import com.tyyar.tyyarfooddelivery.activities.OrderItemActivity;
import com.tyyar.tyyarfooddelivery.model.Category;
import com.tyyar.tyyarfooddelivery.model.Item;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * User: YourPc
 * Date: 1/27/2017
 */

public class MenuCategoryDetailsAdapter extends BaseQuickAdapter<Item, BaseViewHolder> {
    private static final String TAG = MenuCategoryDetailsAdapter.class.getSimpleName();
    public static final String KEY_ITEM = "itemKey";
    public static final String KEY_CATEGORY_NAME = "CategoryName";

    @BindView(R.id.item_name_textView) TextView mItemNameTextView;
    @BindView(R.id.item_description_textView) TextView mDescription;
    @BindView(R.id.row_container) RelativeLayout mRowContainer;
    @BindView(R.id.item_price_textView) TextView mItemPriceTextView;

    private final String mCategoryName;

    public MenuCategoryDetailsAdapter(Category category) {
        super(R.layout.row_item, category.items());
        mCategoryName = category.name();
    }

    @Override
    protected void convert(BaseViewHolder viewHolder, Item item) {
        ButterKnife.bind(this, viewHolder.getConvertView());

        mItemNameTextView.setText(item.name());
        mItemPriceTextView.setText(mContext.getString(R.string.common_price, item.price()));

        mRowContainer.setOnClickListener(v -> {
            Intent intent = new Intent(mContext, OrderItemActivity.class);
            intent.putExtra(KEY_ITEM, item);
            intent.putExtra(KEY_CATEGORY_NAME, mCategoryName);
            mContext.startActivity(intent);
        });

    }
}