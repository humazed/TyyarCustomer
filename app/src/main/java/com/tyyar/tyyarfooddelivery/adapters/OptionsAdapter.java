package com.tyyar.tyyarfooddelivery.adapters;

import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.SectionEntity;
import com.tyyar.tyyarfooddelivery.R;
import com.tyyar.tyyarfooddelivery.model.Choice;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OptionsAdapter extends BaseSectionQuickAdapter<OptionsAdapter.OrderSection, BaseViewHolder> {
    private static final String TAG = OptionsAdapter.class.getSimpleName();

    @BindView(R.id.row_container) LinearLayout mRowContainer;
    @BindView(R.id.option_name_textView) TextView mOptionNameTextView;
    @BindView(R.id.option_description_textView) TextView mOptionDescriptionTextView;
    @BindView(R.id.checkBox) CheckBox mCheckBox;


    public OptionsAdapter(List<OrderSection> orderSections) {
        super(R.layout.row_item_option, R.layout.row_item_option_header, orderSections);
    }

    @Override
    protected void convert(BaseViewHolder helper, OrderSection item) {
        ButterKnife.bind(this, helper.getConvertView());
        Choice choice = item.getChoice();

        mOptionNameTextView.setText(choice.name());
        mOptionDescriptionTextView.setText(choice.description());
        mCheckBox.setText(mContext.getString(R.string.common_price, choice.price()));

        helper.addOnClickListener(R.id.checkBox);

        mCheckBox.setOnCheckedChangeListener((buttonView, isChecked) -> {

        });

    }

    @Override
    protected void convertHead(BaseViewHolder helper, final OrderSection item) {
        helper.setText(R.id.header_title, item.header)
                .setText(R.id.header_description, item.getCount() - 1 + "");

//        helper.setOnClickListener(R.id.more,
//                (View.OnClickListener) v ->
//                        Toast.makeText(mContext, item.header + "more..", Toast.LENGTH_LONG).show());
    }

    ///////////////////////////////////////////////////////////////////////////
    // OrderSection
    ///////////////////////////////////////////////////////////////////////////
    public static class OrderSection extends SectionEntity<Choice> {
        private int mCount;
        private Choice mChoice;

        public OrderSection(boolean isHeader, String header, int count) {
            super(isHeader, header);
            mCount = count;
        }

        public OrderSection(Choice choice) {
            super(choice);
            mChoice = choice;
        }

        public int getCount() {
            return mCount;
        }

        public void setCount(int count) {
            mCount = count;
        }

        public Choice getChoice() {
            return mChoice;
        }

        public void setChoice(Choice choice) {
            mChoice = choice;
        }

        @Override
        public String toString() {
            return "OrderSection{" +
                    "mCount=" + mCount +
                    ", mChoice=" + mChoice +
                    "}\n";
        }
    }
}