package com.tyyar.tyyarfooddelivery;

import android.support.multidex.MultiDexApplication;

import com.blankj.utilcode.utils.Utils;
import com.squareup.leakcanary.LeakCanary;
import com.tyyar.tyyarfooddelivery.model.CartItem;

import java.util.ArrayList;

/**
 * User: YourPc
 * Date: 1/27/2017
 */

public class App extends MultiDexApplication {

    private ArrayList<CartItem> mCartItems;

    @Override
    public void onCreate() {
        super.onCreate();
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);

        // Normal app init code...
        Utils.init(this);

        mCartItems = new ArrayList<>();
    }


    public void addCartItem(CartItem cartItem) {
        mCartItems.add(cartItem);
    }

    public ArrayList<CartItem> getCartItems() {
        return mCartItems;
    }

}
