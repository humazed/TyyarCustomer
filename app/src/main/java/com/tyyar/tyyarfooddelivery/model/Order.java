package com.tyyar.tyyarfooddelivery.model;

import android.os.Parcelable;

import com.google.auto.value.AutoValue;

import java.util.ArrayList;

/**
 * User: YourPc
 * Date: 2/4/2017
 */

@AutoValue
public abstract class Order implements Parcelable, BaseItem {

    public abstract int count();

    public abstract int orderNumber();

    public abstract String itemID();

    public abstract ArrayList<String> choicesIDs();

    public abstract String instructions();


    public static Order create(String _ID, int count, int orderNumber, String itemID, ArrayList<String> choicesIDs, String instructions) {return new AutoValue_Order(_ID, count, orderNumber, itemID, choicesIDs, instructions);}

}
