package com.tyyar.tyyarfooddelivery.adapters;

import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.SectionEntity;
import com.tyyar.tyyarfooddelivery.R;
import com.tyyar.tyyarfooddelivery.model.Order;

import java.util.List;

import butterknife.ButterKnife;

public class OptionsAdapter extends BaseSectionQuickAdapter<OptionsAdapter.OrderSection, BaseViewHolder> {
    private static final String TAG = OptionsAdapter.class.getSimpleName();


    public OptionsAdapter(List<OrderSection> orderSections) {
        super(R.layout.row_item_option, R.layout.row_item_option_header, orderSections);
    }

    @Override
    protected void convert(BaseViewHolder helper, OrderSection item) {
        ButterKnife.bind(this, helper.getConvertView());
        Order order = item.getOrder();

    }

    @Override
    protected void convertHead(BaseViewHolder helper, final OrderSection item) {
        helper.setText(R.id.header_title, item.header)
                .setText(R.id.header_description, item.getCount() + "");

//        helper.setOnClickListener(R.id.more,
//                (View.OnClickListener) v ->
//                        Toast.makeText(mContext, item.header + "more..", Toast.LENGTH_LONG).show());
    }

    ///////////////////////////////////////////////////////////////////////////
    // OrderSection
    ///////////////////////////////////////////////////////////////////////////
    public static class OrderSection extends SectionEntity<Order> {
        private int mCount;
        private boolean isMore;
        private Order mOrder;

        public OrderSection(boolean isHeader, String header, int count) {
            super(isHeader, header);
            mCount = count;
        }

        public OrderSection(Order order) {
            super(order);
            mOrder = order;
        }

        public int getCount() {
            return mCount;
        }

        public void setCount(int count) {
            mCount = count;
        }

        public Order getOrder() {
            return mOrder;
        }

        public void setOrder(Order status) {
            mOrder = status;
        }
    }
}