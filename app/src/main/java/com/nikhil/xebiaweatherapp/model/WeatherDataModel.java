package com.nikhil.xebiaweatherapp.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nikhil on 6/26/2016.
 */
public class WeatherDataModel implements Serializable {

    @SerializedName("city")
    CityDataModel cityDataModel;
    @SerializedName("list")
    List<DailyTempModel> dailyTempModelList;

    public WeatherDataModel() {
        cityDataModel = new CityDataModel();
        dailyTempModelList = new ArrayList<DailyTempModel>();
    }

    public CityDataModel getCityDataModel() {
        return cityDataModel;
    }

    public void setCityDataModel(CityDataModel cityDataModel) {
        this.cityDataModel = cityDataModel;
    }

    public List<DailyTempModel> getDailyTempModelList() {
        return dailyTempModelList;
    }

    public void setDailyTempModelList(List<DailyTempModel> dailyTempModelList) {
        this.dailyTempModelList = dailyTempModelList;
    }
}


