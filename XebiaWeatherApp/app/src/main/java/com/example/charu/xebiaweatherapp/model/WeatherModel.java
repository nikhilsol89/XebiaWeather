package com.example.charu.xebiaweatherapp.model;

import android.graphics.Bitmap;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Charu on 6/26/2016.
 */
public class WeatherModel {
    @SerializedName("main")
    String weatherTitle;
    @SerializedName("description")
    String weatherDescription;
    @SerializedName("icon")
    String icon;
    Bitmap imageBitMap;

    public String getWeatherTitle() {
        return weatherTitle;
    }

    public void setWeatherTitle(String weatherTitle) {
        this.weatherTitle = weatherTitle;
    }

    public String getWeatherDescription() {
        return weatherDescription;
    }

    public void setWeatherDescription(String weatherDescription) {
        this.weatherDescription = weatherDescription;
    }

    public String getIcon() {
        return icon;
    }

    public Bitmap getImageBitMap() {
        return imageBitMap;
    }

    public void setImageBitMap(Bitmap imageBitMap) {
        this.imageBitMap = imageBitMap;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
