package com.tyyar.tyyarfooddelivery.screens;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.appspot.tayyar_trial.restaurantAPI.RestaurantAPI;
import com.appspot.tayyar_trial.restaurantAPI.model.Category;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.tyyar.tyyarfooddelivery.R;
import com.tyyar.tyyarfooddelivery.adapters.MerchantsAdapter;
import com.tyyar.tyyarfooddelivery.model.MerchantView;
import com.tyyar.tyyarfooddelivery.utils.UiUtils;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static com.tyyar.tyyarfooddelivery.screens.home.RestaurantsFragment.ROOT_URL;

public class MenuCategoriesActivity extends AppCompatActivity {
    private static final String TAG = MenuCategoriesActivity.class.getSimpleName();

    @BindView(R.id.toolbar) Toolbar mToolbar;
    @BindView(R.id.categories_recycler) RecyclerView mCategoriesRecycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_categories);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        UiUtils.showDrawer(this, mToolbar, 1);

        // TODO: 3/24/2017 add method to get the data from the server
        MerchantView merchantView = getIntent().getParcelableExtra(MerchantsAdapter.KEY_MERCHANT);

        if (getSupportActionBar() != null) getSupportActionBar().setTitle(merchantView.name());

        Observable.fromCallable(() -> getRestaurant(merchantView._ID()))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
//                .map(category -> com.tyyar.tyyarfooddelivery.model.Category.create())
                .subscribe(restaurant -> {
                    Log.d(TAG, "restaurant = " + restaurant);
                    mCategoriesRecycler.setLayoutManager(new LinearLayoutManager(this));
//                    mCategoriesRecycler.setAdapter(new MenuCategoriesAdapter(restaurant.getCategories()));

                }, throwable -> Log.e(TAG, "onCreate: ", throwable));

    }

    private Category getRestaurant(String id) throws IOException {
        RestaurantAPI restaurantAPI = new RestaurantAPI.Builder(new NetHttpTransport(), new AndroidJsonFactory(), null)
                .setGoogleClientRequestInitializer(clientRequest -> clientRequest.setDisableGZipContent(true))
                .setRootUrl(ROOT_URL)
                .build();

        return restaurantAPI.getCategoryByID(id).execute();
    }
}
