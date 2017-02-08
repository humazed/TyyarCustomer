package com.tyyar.tyyarfooddelivery.model;

import android.os.Parcelable;

import com.google.auto.value.AutoValue;

import java.util.List;

/**
 * User: YourPc
 * Date: 2/4/2017
 */

@AutoValue
public abstract class Order implements Parcelable, BaseItem {

    public abstract int count();

    public abstract int orderNumber();

    public abstract String itemID();

    public abstract List<String> choicesIDs();

    public abstract String instructions();



}
