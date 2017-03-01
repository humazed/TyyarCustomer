package com.tyyar.tyyarfooddelivery.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import com.tyyar.tyyarfooddelivery.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchMerchantsActivity extends AppCompatActivity {
    private static final String TAG = SearchMerchantsActivity.class.getSimpleName();

    @BindView(R.id.search_results_recyclerView) RecyclerView mSearchResultsRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_merchants);
        ButterKnife.bind(this);


    }
}
