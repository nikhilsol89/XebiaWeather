package com.example.charu.xebiaweatherapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.charu.xebiaweatherapp.R;
import com.example.charu.xebiaweatherapp.model.WeatherDataModel;

/**
 * Created by Charu on 6/26/2016.
 */
public class WeatherListAdapter extends RecyclerView.Adapter<WeatherListAdapter.WeatherViewHolder>{

    Context context;
    private WeatherDataModel weatherDataModel;

    public WeatherListAdapter(Context context){
        this.context = context;
    }

    public void setWeatherDataModel(WeatherDataModel weatherDataModel) {
        this.weatherDataModel = weatherDataModel;
    }

    @Override
    public WeatherViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(WeatherViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class WeatherViewHolder extends RecyclerView.ViewHolder {

        View view;
        ImageView weatherIconImageView;
        TextView dateTextView, tempTextView, pressureTextView, humidityTextview, windSpeedTextview, titleWeatherTextView, descriptionTextView;

        public WeatherViewHolder(View itemView) {
            super(itemView);
            this.view = itemView;

            this.dateTextView = (TextView)this.view.findViewById(R.id.dateText);
            this.tempTextView = (TextView)this.view.findViewById(R.id.temperatureText);
            this.pressureTextView = (TextView)this.view.findViewById(R.id.pressureText);
            this.humidityTextview = (TextView)this.view.findViewById(R.id.humidityText);
            this.windSpeedTextview = (TextView)this.view.findViewById(R.id.windSpeedText);
            this.titleWeatherTextView = (TextView)this.view.findViewById(R.id.titleWeatherText);
            this.descriptionTextView = (TextView)this.view.findViewById(R.id.descriptionText);
            this.weatherIconImageView = (ImageView)this.view.findViewById(R.id.weather_icon);
        }
    }
}
