package com.tyyar.tyyarfooddelivery.model;

import android.os.Parcelable;

import com.google.auto.value.AutoValue;

/**
 * User: YourPc
 * Date: 1/27/2017
 */

@AutoValue
public abstract class Merchant implements Parcelable {

    public abstract String name();

    public abstract String description();

    public abstract String logoImageUri();

    public abstract String deliveryFee();

    public abstract String deliveryETA();

    public static Merchant create(String name, String description, String logo_image) {
        return new AutoValue_Merchant(name, description, logo_image, "0.99$", "45 Min");
    }
}
