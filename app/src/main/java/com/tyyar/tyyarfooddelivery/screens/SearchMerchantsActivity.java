package com.tyyar.tyyarfooddelivery.screens;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.arlib.floatingsearchview.FloatingSearchView;
import com.tyyar.tyyarfooddelivery.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchMerchantsActivity extends AppCompatActivity {
    private static final String TAG = SearchMerchantsActivity.class.getSimpleName();

    @BindView(R.id.results_recyclerView) RecyclerView mResultsRecyclerView;
    @BindView(R.id.floating_searchView) FloatingSearchView mFloatingSearchView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_merchants);
        ButterKnife.bind(this);


        mFloatingSearchView.setOnQueryChangeListener((oldQuery, newQuery) -> {
            Log.d(TAG, "newQuery = " + newQuery);

//            mFloatingSearchView.swapSuggestions(newSuggestions);
        });

        mFloatingSearchView.setOnHomeActionClickListener(this::finish);


    }
}
