package com.tyyar.tyyarfooddelivery.model;

import android.os.Parcelable;

import com.google.auto.value.AutoValue;

import java.util.ArrayList;

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

    public abstract String deliveryFee();

    public abstract String deliveryETA();

    public abstract Location location();

    public abstract ArrayList<Category> menu();

    public abstract ArrayList<Review> reviews();


    public static Merchant create(String _ID, String name, String password, String email, String phone, int rating, String logoImageUrl, String description, int pricing, String deliveryFee, String deliveryETA, Location location, ArrayList<Category> menu, ArrayList<Review> reviews) {return new AutoValue_Merchant(_ID, name, password, email, phone, rating, logoImageUrl, description, pricing, deliveryFee, deliveryETA, location, menu, reviews);}

}
