package com.tyyar.tyyarfooddelivery.model;

import android.os.Parcelable;

import com.google.auto.value.AutoValue;

import java.util.ArrayList;

/**
 * User: YourPc
 * Date: 2/8/2017
 */

@AutoValue
public abstract class Item implements Parcelable, BaseItem {
    public abstract String name();

    public abstract String description();

    public abstract double price();

    public abstract String imageUrl();

    public abstract boolean available();

    public abstract ArrayList<Option> options();

    private boolean selected;

    public static Item create(String _ID, String name, String description, double price, String imageUrl, boolean available, ArrayList<Option> options) {return new AutoValue_Item(_ID, name, description, price, imageUrl, available, options);}


    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    /*public static Category map(com.appspot.tayyar_trial.restaurantAPI.model.Item i) {
        return Item.create(i.getId(),i.getName(),"description",i.);
    }*/
}
