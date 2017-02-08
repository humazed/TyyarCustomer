package com.tyyar.tyyarfooddelivery.model;

import android.os.Parcelable;

import com.google.auto.value.AutoValue;

import java.util.List;

/**
 * User: YourPc
 * Date: 1/27/2017
 */

@AutoValue
public abstract class Merchant implements Parcelable, BaseItem {

    public abstract String name();

    public abstract String password();

    public abstract String email();

    public abstract String phone();

    public abstract int rating();

    public abstract String logoImageUrl();

    public abstract String description();

    public abstract int pricing();

    public abstract Location location();

    public abstract List<Category> menu();

    public abstract List<Review> reviews();

    public abstract String deliveryFee();

    public abstract String deliveryETA();


    public static Merchant create(String _ID, String name, String password, String email, String phone, int rating, String logoImageUrl, String description, int pricing, Location location, List<Category> menu, List<Review> reviews, String deliveryFee, String deliveryETA) {return new AutoValue_Merchant(_ID, name, password, email, phone, rating, logoImageUrl, description, pricing, location, menu, reviews, deliveryFee, deliveryETA);}

}
