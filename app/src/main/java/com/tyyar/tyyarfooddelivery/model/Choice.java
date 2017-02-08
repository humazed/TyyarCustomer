package com.tyyar.tyyarfooddelivery.model;

import android.os.Parcelable;

import com.google.auto.value.AutoValue;

/**
 * User: YourPc
 * Date: 2/8/2017
 */

@AutoValue
public abstract class Choice implements Parcelable, BaseItem {
    public abstract String name();

    public abstract String description();

    public abstract double price();

    public abstract boolean available();

    public abstract boolean addToPrice();

    public static Choice create(String _ID, String name, String description, double price, boolean available, boolean addToPrice) {return new AutoValue_Choice(_ID, name, description, price, available, addToPrice);}


}
