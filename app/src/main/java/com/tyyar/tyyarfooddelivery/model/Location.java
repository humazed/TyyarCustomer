package com.tyyar.tyyarfooddelivery.model;

import android.os.Parcelable;

import com.google.auto.value.AutoValue;

/**
 * User: YourPc
 * Date: 2/8/2017
 */

@AutoValue
public abstract class Location implements Parcelable, BaseItem {
    public abstract double longitude();

    public abstract double latitude();

    public abstract String address();


    public static Location create(String _ID, double longitude, double latitude, String address) {return new AutoValue_Location(_ID, longitude, latitude, address);}
}
