package com.tyyar.tyyarfooddelivery.model;

import android.location.Location;
import android.os.Parcelable;

import com.google.auto.value.AutoValue;

import java.util.ArrayList;

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

    public abstract ArrayList<Location> locationsList();

    public abstract ArrayList<Review> reviews();


    public static Profile create(String _ID, String name, String password, String email, String phone, String logoImageUrl, ArrayList<Location> locationsList, ArrayList<Review> reviews) {return new AutoValue_Profile(_ID, name, password, email, phone, logoImageUrl, locationsList, reviews);}


}
