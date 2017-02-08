package com.tyyar.tyyarfooddelivery.model;

import android.os.Parcelable;

import com.google.auto.value.AutoValue;

import java.util.ArrayList;

/**
 * User: YourPc
 * Date: 2/4/2017
 */

@AutoValue
public abstract class Request implements Parcelable, BaseItem {

    public abstract String customerName();

    public abstract String customerPhone();

    public abstract String driverID();

    public abstract long pickUpTime();

    public abstract ArrayList<Order> orders();


    public static Request create(String _ID, String customerName, String customerPhone, String driverID, long pickUpTime, ArrayList<Order> orders) {return new AutoValue_Request(_ID, customerName, customerPhone, driverID, pickUpTime, orders);}


}
