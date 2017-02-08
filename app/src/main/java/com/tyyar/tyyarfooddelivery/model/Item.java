package com.tyyar.tyyarfooddelivery.model;

import android.os.Parcelable;

import com.google.auto.value.AutoValue;

import java.util.List;

/**
 * User: YourPc
 * Date: 2/8/2017
 */

@AutoValue
public abstract class Item implements Parcelable, BaseItem {
    public abstract String _ID();

    public abstract String name();

    public abstract String description();

    public abstract String imageUrl();

    public abstract boolean avaliable();

    public abstract List<Option> options();

    public static Item create(String _ID, String name, String description, String imageUrl, boolean avaliable, List<Option> options) {return new AutoValue_Item(_ID, name, description, imageUrl, avaliable, options);}


}
