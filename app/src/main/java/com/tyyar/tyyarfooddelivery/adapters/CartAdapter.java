package com.tyyar.tyyarfooddelivery.adapters;

import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tyyar.tyyarfooddelivery.R;
import com.tyyar.tyyarfooddelivery.model.CartItem;
import com.tyyar.tyyarfooddelivery.model.Choice;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * User: YourPc
 * Date: 1/27/2017
 */

public class CartAdapter extends BaseQuickAdapter<CartItem, BaseViewHolder> {
    private static final String TAG = CartAdapter.class.getSimpleName();

    @BindView(R.id.row_container) LinearLayout mRowContainer;
    @BindView(R.id.category_name_textView) TextView mCategoryNameTextView;
    @BindView(R.id.quantity_textView) TextView mQuantityTextView;
    @BindView(R.id.name_textView) TextView mNameTextView;
    @BindView(R.id.price_textView) TextView mPriceTextView;
    @BindView(R.id.options_textView) TextView mOptionsTextView;
    @BindView(R.id.checkbox) CheckBox mCheckbox;


    public CartAdapter(List<CartItem> cartItems) {
        super(R.layout.row_cart_item, cartItems);
    }

    @Override
    protected void convert(BaseViewHolder viewHolder, CartItem cartItem) {
        ButterKnife.bind(this, viewHolder.getConvertView());

        mCategoryNameTextView.setText(cartItem.CategoryName());
        mQuantityTextView.setText((String.valueOf(cartItem.itemCount())));
        mNameTextView.setText(cartItem.itemName());
        mPriceTextView.setText(mContext.getString(R.string.common_price, cartItem.totalPrice()));

        StringBuilder choices = new StringBuilder();
        for (Choice choice : cartItem.selectedChoices())
            choices.append("Added ").append(choice.name()).append("\n");

        mOptionsTextView.setText(choices);


    }
}