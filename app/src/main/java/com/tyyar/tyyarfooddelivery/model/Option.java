package com.tyyar.tyyarfooddelivery.model;

import android.os.Parcelable;

import com.google.auto.value.AutoValue;

import java.util.ArrayList;

/**
 * User: YourPc
 * Date: 2/8/2017
 */

@AutoValue
public abstract class Option implements Parcelable, BaseItem {

    public abstract String name();

    public abstract boolean required();

    public abstract boolean singleChoice();

    public abstract ArrayList<Choice> choices();

    public static Option create(String _ID, String name, boolean required, boolean singleChoice, ArrayList<Choice> choices) {return new AutoValue_Option(_ID, name, required, singleChoice, choices);}
}
