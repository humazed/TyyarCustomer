package com.tyyar.tyyarfooddelivery;

import android.app.Application;

import com.blankj.utilcode.utils.Utils;
import com.tyyar.tyyarfooddelivery.model.CartItem;

import java.util.ArrayList;

/**
 * User: YourPc
 * Date: 1/27/2017
 */

public class App extends Application {

    private ArrayList<CartItem> mCartItems;

    @Override
    public void onCreate() {
        super.onCreate();

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
