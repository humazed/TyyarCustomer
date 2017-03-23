package com.tyyar.tyyarfooddelivery.model;

import android.os.Parcelable;

import com.google.auto.value.AutoValue;

/**
 * To be used only in recyclerView
 */

@AutoValue
public abstract class MerchantView implements Parcelable, BaseItem {

    public abstract String name();

    public abstract int rating();

    public abstract String logoImageUrl();

    public abstract String description();

    public abstract int pricing();

    public abstract String deliveryFee();

    public abstract String deliveryETA();

    public static MerchantView create(String _ID, String name, int rating, String logoImageUrl, String description, int pricing, String deliveryFee, String deliveryETA) {return new AutoValue_MerchantView(_ID, name, rating, logoImageUrl, description, pricing, deliveryFee, deliveryETA);}

}
