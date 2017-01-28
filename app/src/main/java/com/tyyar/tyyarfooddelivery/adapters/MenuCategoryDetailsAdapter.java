package com.tyyar.tyyarfooddelivery.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tyyar.tyyarfooddelivery.R;
import com.tyyar.tyyarfooddelivery.activities.ItemOrderActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * User: YourPc
 * Date: 1/27/2017
 */

public class MenuCategoryDetailsAdapter extends RecyclerView.Adapter<MenuCategoryDetailsAdapter.ViewHolder> {
    private static final String TAG = MenuCategoryDetailsAdapter.class.getSimpleName();


    private List<String> foods;

    public MenuCategoryDetailsAdapter(List<String> foods) {
        this.foods = foods;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_sub_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String foods = this.foods.get(position);
        holder.bind(foods);
    }

    @Override
    public int getItemCount() {
        return foods.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        private Context mContext;

        @BindView(R.id.dish_name) TextView mDishName;
        @BindView(R.id.description) TextView mDescription;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();

            itemView.setOnClickListener(v ->
                    mContext.startActivity(new Intent(mContext, ItemOrderActivity.class)));

        }

        public void bind(String merchant) {
            mDishName.setText(merchant);
        }
    }


}