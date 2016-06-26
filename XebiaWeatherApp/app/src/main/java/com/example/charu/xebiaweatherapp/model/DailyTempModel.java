package com.example.charu.xebiaweatherapp.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Charu on 6/26/2016.
 */
public class DailyTempModel implements Serializable {

    @SerializedName("temp")
    TemperatureModel temperatureModel;
    @SerializedName("weather")
    ArrayList<WeatherModel> weatherModel;
    @SerializedName("dt")
    String date;
    @SerializedName("pressure")
    String pressure;
    @SerializedName("humidity")
    String humidity;
    @SerializedName("speed")
    String windSpeed;


    public DailyTempModel() {
        this.temperatureModel = new TemperatureModel();
        this.weatherModel = new ArrayList<WeatherModel>();
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(String windSpeed) {
        this.windSpeed = windSpeed;
    }

    public TemperatureModel getTemperatureModel() {
        return temperatureModel;
    }

    public void setTemperatureModel(TemperatureModel temperatureModel) {
        this.temperatureModel = temperatureModel;
    }

    public ArrayList<WeatherModel> getWeatherModel() {
        return weatherModel;
    }

    public void setWeatherModel(ArrayList<WeatherModel> weatherModel) {
        this.weatherModel = weatherModel;
    }
}
