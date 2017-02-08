package com.tyyar.tyyarfooddelivery.model;

import android.location.Location;
import android.os.Parcelable;

import com.google.auto.value.AutoValue;

import java.util.List;

/**
 * User: YourPc
 * Date: 1/27/2017
 */

@AutoValue
public abstract class Profile implements Parcelable, BaseItem {

    public abstract String name();

    public abstract String password();

    public abstract String email();

    public abstract String phone();

    public abstract String logoImageUrl();

    public abstract List<Location> locationsList();

    public abstract List<Review> reviews();


    public static Profile create(String _ID, String name, String password, String email, String phone, String logoImageUrl, List<Location> locationsList, List<Review> reviews) {return new AutoValue_Profile(_ID, name, password, email, phone, logoImageUrl, locationsList, reviews);}


}
