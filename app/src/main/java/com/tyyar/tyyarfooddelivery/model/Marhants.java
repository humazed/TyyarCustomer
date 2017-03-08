package com.tyyar.tyyarfooddelivery.model;

/**
 * User: YourPc
 * Date: 3/8/2017
 */

public class Marhants {

    @com.google.gson.annotations.SerializedName("id")
    private String mId;

    @com.google.gson.annotations.SerializedName("name")
    private String mName;

    @com.google.gson.annotations.SerializedName("type")
    private String mType;

    @com.google.gson.annotations.SerializedName("url")
    private String mUrl;

    public Marhants() {
    }

    public Marhants(String id, String name, String type, String url) {
        mId = id;
        mName = name;
        mType = type;
        mUrl = url;
    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getType() {
        return mType;
    }

    public void setType(String type) {
        mType = type;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String url) {
        mUrl = url;
    }

    @Override
    public String toString() {
        return "Marhants{" +
                "mId='" + mId + '\'' +
                ", mName='" + mName + '\'' +
                ", mType='" + mType + '\'' +
                ", mUrl='" + mUrl + '\'' +
                '}';
    }
}
