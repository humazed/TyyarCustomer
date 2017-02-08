package com.tyyar.tyyarfooddelivery.model;

import android.os.Parcelable;

import com.google.auto.value.AutoValue;

import java.util.List;

/**
 * User: YourPc
 * Date: 2/8/2017
 */

@AutoValue
public abstract class Category implements Parcelable, BaseItem {
    public abstract String _ID();

    public abstract List<Item> items();

    public static Category create(String _ID, List<Item> items) {return new AutoValue_Category(_ID, items);}

}
