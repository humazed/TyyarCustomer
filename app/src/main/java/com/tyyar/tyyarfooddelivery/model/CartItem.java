package com.tyyar.tyyarfooddelivery.model;

import android.os.Parcelable;

import com.google.auto.value.AutoValue;

import java.util.List;

/**
 * User: YourPc
 * Date: 2/14/2017
 */

@AutoValue
public abstract class CartItem implements Parcelable {
    public abstract int itemCount();

    public abstract String itemID();

    public abstract String itemName();

    public abstract String CategoryName();

    public abstract double totalPrice();

    public abstract List<Choice> selectedChoices();

    public static CartItem create(int itemCount, String itemID, String itemName, String CategoryName, double totalPrice, List<Choice> selectedChoices) {return new AutoValue_CartItem(itemCount, itemID, itemName, CategoryName, totalPrice, selectedChoices);}


}
