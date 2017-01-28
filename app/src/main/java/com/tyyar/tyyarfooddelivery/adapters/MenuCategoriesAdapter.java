package com.tyyar.tyyarfooddelivery.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tyyar.tyyarfooddelivery.R;
import com.tyyar.tyyarfooddelivery.activities.MenuCategoryDetailsActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * User: YourPc
 * Date: 1/27/2017
 */

public class MenuCategoriesAdapter extends RecyclerView.Adapter<MenuCategoriesAdapter.ViewHolder> {
    private static final String TAG = MenuCategoriesAdapter.class.getSimpleName();

    private List<String> mCategories;

    public MenuCategoriesAdapter(List<String> categories) {
        mCategories = categories;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_menu_category, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String catogry = mCategories.get(position);
        holder.bind(catogry);
    }

    @Override
    public int getItemCount() {
        return mCategories.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private Context mContext;
        @BindView(R.id.menu_category_name) TextView mMenuCategoryName;
        @BindView(R.id.menu_category_count) TextView mMenuCategoryCount;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();

            itemView.setOnClickListener(v -> {
                mContext.startActivity(new Intent(mContext, MenuCategoryDetailsActivity.class));
            });
        }

        public void bind(String category) {
            mMenuCategoryName.setText(category);
            mMenuCategoryCount.setText("7");
        }
    }


}