package com.tyyar.tyyarfooddelivery.model;

import android.os.Parcelable;

import com.google.auto.value.AutoValue;

import java.util.ArrayList;

/**
 * User: YourPc
 * Date: 2/8/2017
 */

@AutoValue
public abstract class Category implements Parcelable, BaseItem {
    public abstract String name();

    public abstract ArrayList<Item> items();

    public static Category create(String _ID, String name, ArrayList<Item> items) {return new AutoValue_Category(_ID, name, items);}


}
