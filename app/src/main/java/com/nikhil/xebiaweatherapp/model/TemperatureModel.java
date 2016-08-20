package com.nikhil.xebiaweatherapp.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by nikhil on 6/26/2016.
 */
public class TemperatureModel implements Serializable {
    @SerializedName("min")
    String minimumTemp;
    @SerializedName("max")
    String maximumTemp;
    @SerializedName("day")
    String dayTemp;
    @SerializedName("morn")
    String morningTemp;
    @SerializedName("eve")
    String eveningTemp;
    @SerializedName("night")
    String nightTemp;

    public String getMinimumTemp() {
        return minimumTemp;
    }

    public void setMinimumTemp(String minimumTemp) {
        this.minimumTemp = minimumTemp;
    }

    public String getMaximumTemp() {
        return maximumTemp;
    }

    public void setMaximumTemp(String maximumTemp) {
        this.maximumTemp = maximumTemp;
    }

    public String getDayTemp() {
        return dayTemp;
    }

    public void setDayTemp(String dayTemp) {
        this.dayTemp = dayTemp;
    }

    public String getMorningTemp() {
        return morningTemp;
    }

    public void setMorningTemp(String morningTemp) {
        this.morningTemp = morningTemp;
    }

    public String getEveningTemp() {
        return eveningTemp;
    }

    public void setEveningTemp(String eveningTemp) {
        this.eveningTemp = eveningTemp;
    }

    public String getNightTemp() {
        return nightTemp;
    }

    public void setNightTemp(String nightTemp) {
        this.nightTemp = nightTemp;
    }
}
