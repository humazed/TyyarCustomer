package com.tyyar.tyyarfooddelivery.activities;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.tyyar.tyyarfooddelivery.App;
import com.tyyar.tyyarfooddelivery.R;
import com.tyyar.tyyarfooddelivery.adapters.MenuCategoryDetailsAdapter;
import com.tyyar.tyyarfooddelivery.adapters.OptionsAdapter;
import com.tyyar.tyyarfooddelivery.adapters.OptionsAdapter.OrderSection;
import com.tyyar.tyyarfooddelivery.model.CartItem;
import com.tyyar.tyyarfooddelivery.model.Choice;
import com.tyyar.tyyarfooddelivery.model.Item;
import com.tyyar.tyyarfooddelivery.model.Option;
import com.tyyar.tyyarfooddelivery.utils.DataUtils;
import com.tyyar.tyyarfooddelivery.utils.UiUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.tyyar.tyyarfooddelivery.adapters.MenuCategoryDetailsAdapter.KEY_CATEGORY_NAME;

public class OrderItemActivity extends AppCompatActivity {
    private static final String TAG = OrderItemActivity.class.getSimpleName();

    @BindView(R.id.toolbar) Toolbar mToolbar;
    @BindView(R.id.item_imageView) ImageView mItemImageView;
    @BindView(R.id.item_name_textView) TextView mItemNameTextView;
    @BindView(R.id.item_description_textView) TextView mItemDescriptionTextView;
    @BindView(R.id.options_recyclerView) RecyclerView mOptionsRecyclerView;
    @BindView(R.id.total_priceTextView) TextView mTotalPriceTextView;
    @BindView(R.id.add_to_order_button) RelativeLayout mAddToOrderButton;
    @BindView(R.id.instructions_editText) EditText mInstructionsEditText;
    @BindView(R.id.substitution_title) TextView mSubstitutionTitle;
    @BindView(R.id.substitution_type_spinner) Spinner mSubstitutionTypeSpinner;
    @BindView(R.id.quantity_textView) TextView mQuantityTextView;
    @BindView(R.id.quantity_plus_button) Button mQuantityPlusButton;
    @BindView(R.id.quantity_minus_button) Button mQuantityMinusButton;

    private Item mItem;
    private double mTotalPrice = 0;
    private List<OrderSection> mOptionsSectionedList;
    private int mQuantity = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_item);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        UiUtils.showDrawer(this, mToolbar).setSelection(1, false);

        mItem = getIntent().getParcelableExtra(MenuCategoryDetailsAdapter.KEY_ITEM);
        mOptionsSectionedList = DataUtils.getOptionsSectionedList(mItem.options());

        Glide.with(this).load(Uri.parse(mItem.imageUrl())).into(mItemImageView);

        mItemNameTextView.setText(mItem.name());
        mItemDescriptionTextView.setText(mItem.description());
        mTotalPriceTextView.setText(getString(R.string.common_price, mItem.price()));
        mQuantityTextView.setText(getString(R.string.quantity_number, mQuantity));

        mQuantityPlusButton.setOnClickListener(v -> {
            mQuantityTextView.setText(getString(R.string.quantity_number, ++mQuantity));
            mTotalPriceTextView.setText(getString(R.string.common_price, mTotalPrice * mQuantity));
        });
        mQuantityMinusButton.setOnClickListener(v -> {
            mQuantityTextView.setText(getString(R.string.quantity_number, --mQuantity));
            mTotalPriceTextView.setText(getString(R.string.common_price, mTotalPrice * mQuantity));
        });

        //Options list code
        OptionsAdapter optionsAdapter = new OptionsAdapter(mOptionsSectionedList);
        mOptionsRecyclerView.setAdapter(optionsAdapter);
        mOptionsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mOptionsRecyclerView.setHasFixedSize(true);
        mOptionsRecyclerView.setNestedScrollingEnabled(false);

        List<Option> requiredOptions = new ArrayList<Option>() {{
            for (Option option : mItem.options())
                if (option.required()) add(option);
        }};

        ArrayList<Choice> selectedRequiredChoices = new ArrayList<>();
        ArrayList<Choice> selectedOptionalChoices = new ArrayList<>();
        ArrayList<Choice> selectedChoices = new ArrayList<>();
        mOptionsRecyclerView.addOnItemTouchListener(new OnItemChildClickListener() {
            @Override
            public void onSimpleItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                CheckBox checkBox = (CheckBox) view;
                Choice choice = mOptionsSectionedList.get(position).getChoice();
                boolean checked = !checkBox.isChecked();

                activateAddButton(choice, checked, requiredOptions, selectedRequiredChoices);
                selectedChoices.addAll(selectedRequiredChoices);
                selectedChoices.addAll(selectedOptionalChoices);

                changePrice(choice, checked, selectedOptionalChoices);
            }
        });


        mAddToOrderButton.setOnClickListener(v -> {
            ((App) getApplication()).addCartItem(
                    CartItem.create(mQuantity, mItem._ID(), mItem.name(),
                            getIntent().getStringExtra(KEY_CATEGORY_NAME),
                            mTotalPrice, selectedChoices));
            finish();
        });


    }

    private void activateAddButton(Choice choice, boolean checked, List<Option> requiredOptions,
                                   ArrayList<Choice> pickedRequiredChoices) {
        //for activating add button after choose all the required.
        // TODO: 2/13/2017 make it work with more than 1.
        for (Option requiredOption : requiredOptions) {
            if (requiredOption.choices().contains(choice)) {
                if (checked) pickedRequiredChoices.add(choice);
                else pickedRequiredChoices.remove(choice);

                if (pickedRequiredChoices.size() > 0)
                    mAddToOrderButton.setBackgroundResource(R.drawable.dd_button_red_flat);
                else mAddToOrderButton.setBackgroundResource(R.drawable.dd_button_gray);
            }
        }
    }

    private void changePrice(Choice choice, boolean checked, ArrayList<Choice> pickedOptionalChoices) {
        if (checked) {
            pickedOptionalChoices.add(choice);
            mTotalPrice = calculateTotalPrice(pickedOptionalChoices, mItem.price());
        } else {
            pickedOptionalChoices.remove(choice);
            mTotalPrice = calculateTotalPrice(pickedOptionalChoices, mItem.price());
        }

        mTotalPriceTextView.setText(getString(R.string.common_price, mTotalPrice));
    }

    double calculateTotalPrice(ArrayList<Choice> choices, double initPrice) {
        // TODO: 2/13/2017 add the singel choice moode to single choice items
        for (Choice choice : choices)
            if (!choice.addToPrice()) initPrice = choice.price();

        for (Choice choice : choices) {
            if (choice.addToPrice())
                initPrice += choice.price();
        }
        return initPrice;
    }
}
