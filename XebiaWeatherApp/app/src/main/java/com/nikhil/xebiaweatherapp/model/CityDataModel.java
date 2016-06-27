package com.nikhil.xebiaweatherapp.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by nikhil on 6/26/2016.
 */
public class CityDataModel implements Serializable {
    @SerializedName("name")
    String name;
    @SerializedName("country")
    String country;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
