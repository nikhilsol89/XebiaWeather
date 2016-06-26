package com.example.charu.xebiaweatherapp.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Charu on 6/26/2016.
 */
public class WeatherModel {
    @SerializedName("main")
    String weatherTitle;
    @SerializedName("description")
    String weatherDescription;

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
}
