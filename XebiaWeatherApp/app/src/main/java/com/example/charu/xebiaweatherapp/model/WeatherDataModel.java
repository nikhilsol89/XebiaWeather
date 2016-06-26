package com.example.charu.xebiaweatherapp.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Charu on 6/26/2016.
 */
public class WeatherDataModel {

    CityDataModel cityDataModel;
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


