package com.tyyar.tyyarfooddelivery.model;

import android.os.Parcelable;

import com.google.auto.value.AutoValue;

/**
 * User: YourPc
 * Date: 2/8/2017
 */

@AutoValue
public abstract class Review implements Parcelable, BaseItem {

    public abstract String customerID();

    public abstract String restaurantID();

    public abstract String comment();

    public abstract int rating();

    public static Review create(String _ID, String customerID, String restaurantID, String comment, int rating) {return new AutoValue_Review(_ID, customerID, restaurantID, comment, rating);}

}
