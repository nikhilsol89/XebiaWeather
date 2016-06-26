package com.example.charu.xebiaweatherapp.model;

import android.graphics.Bitmap;

import com.google.gson.annotations.SerializedName;

import java.io.ByteArrayOutputStream;
import java.io.Serializable;

/**
 * Created by Charu on 6/26/2016.
 */
public class WeatherModel implements Serializable {
    @SerializedName("main")
    String weatherTitle;
    @SerializedName("description")
    String weatherDescription;
    @SerializedName("icon")
    String icon;
    Bitmap myImageBitMap;
    

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

    public Bitmap getMyImageBitMap() {
        return myImageBitMap;
    }

    public void setMyImageBitMap(Bitmap myImageBitMap) {
        this.myImageBitMap = myImageBitMap;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
