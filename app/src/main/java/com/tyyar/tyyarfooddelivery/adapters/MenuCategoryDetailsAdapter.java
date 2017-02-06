package com.tyyar.tyyarfooddelivery.adapters;

import android.content.Intent;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tyyar.tyyarfooddelivery.R;
import com.tyyar.tyyarfooddelivery.activities.ItemOrderActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * User: YourPc
 * Date: 1/27/2017
 */

public class MenuCategoryDetailsAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    private static final String TAG = MenuCategoryDetailsAdapter.class.getSimpleName();

    @BindView(R.id.item_name_textView) TextView mDishName;
    @BindView(R.id.item_description_textView) TextView mDescription;
    @BindView(R.id.row_container) RelativeLayout mRowContainer;

    public MenuCategoryDetailsAdapter(List<String> items) {
        super(R.layout.row_item, items);
    }

    @Override
    protected void convert(BaseViewHolder viewHolder, String item) {
        ButterKnife.bind(this, viewHolder.getConvertView());

        mDishName.setText(item);

        mRowContainer.setOnClickListener(v -> mContext.startActivity(new Intent(mContext, ItemOrderActivity.class)));

    }
}