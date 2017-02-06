package com.tyyar.tyyarfooddelivery.adapters;

import android.content.Intent;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tyyar.tyyarfooddelivery.R;
import com.tyyar.tyyarfooddelivery.activities.MenuCategoryItemsActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * User: YourPc
 * Date: 1/27/2017
 */

public class MenuCategoriesAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    private static final String TAG = MenuCategoriesAdapter.class.getSimpleName();

    @BindView(R.id.menu_category_name) TextView mMenuCategoryName;
    @BindView(R.id.menu_category_count) TextView mMenuCategoryCount;
    @BindView(R.id.row_container) RelativeLayout mRowContainer;


    public MenuCategoriesAdapter(List<String> categories) {
        super(R.layout.row_menu_category, categories);
    }

    @Override
    protected void convert(BaseViewHolder viewHolder, String category) {
        ButterKnife.bind(this, viewHolder.getConvertView());

        mMenuCategoryName.setText(category);
        mMenuCategoryCount.setText("7");

        mRowContainer.setOnClickListener(v -> mContext.startActivity(new Intent(mContext, MenuCategoryItemsActivity.class)));
    }
}