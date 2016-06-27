package com.example.charu.xebiaweatherapp.adapter;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.charu.xebiaweatherapp.R;
import com.example.charu.xebiaweatherapp.activity.WeatherListActivity;
import com.example.charu.xebiaweatherapp.model.WeatherDataModel;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Charu on 6/26/2016.
 */
public class WeatherListAdapter extends RecyclerView.Adapter<WeatherListAdapter.WeatherViewHolder> {

    Context context;
    private WeatherDataModel weatherDataModel;
    private WeatherListActivity weatherListActivity;

    public WeatherListAdapter(Context context, WeatherListActivity weatherListActivity) {
        this.context = context;
        this.weatherListActivity = weatherListActivity;
    }

    public void setWeatherDataModel(WeatherDataModel weatherDataModel) {
        this.weatherDataModel = weatherDataModel;
    }

    @Override
    public WeatherViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.weather_cell, parent, false);
        return new WeatherViewHolder(view);
    }

    @Override
    public void onBindViewHolder(WeatherViewHolder holder, int position) {
        if (this.weatherDataModel.getDailyTempModelList().get(position).getWeatherModel().get(0).getImageByteArray() != null) {
            Log.e("nikhil", "weaher " + weatherDataModel.getDailyTempModelList().get(position).getWeatherModel().get(0).getImageByteArray().length + " ");
            holder.weatherIconImageView.setImageBitmap(BitmapFactory.decodeByteArray(this.weatherDataModel.getDailyTempModelList().get(position).getWeatherModel().get(0).getImageByteArray(), 0, this.weatherDataModel.getDailyTempModelList().get(position).getWeatherModel().get(0).getImageByteArray().length));
        }

        holder.dateTextView.setText(new SimpleDateFormat("EEE dd MMM,yyyy").
                format(new Date(Long.parseLong(weatherDataModel.getDailyTempModelList().get(position).getDate()) * 1000)));
        holder.tempTextView.setText(weatherDataModel.getDailyTempModelList().get(position).getTemperatureModel().getDayTemp() + context.getString(R.string.weather_unit));
        holder.titleWeatherTextView.setText(weatherDataModel.getDailyTempModelList().get(position).getWeatherModel().get(0).getWeatherTitle());
        holder.descriptionTextView.setText(weatherDataModel.getDailyTempModelList().get(position).getWeatherModel().get(0).getWeatherDescription());
    }

    @Override
    public int getItemCount() {
        if (weatherDataModel != null && this.weatherDataModel.getDailyTempModelList().size() > 0)
            return this.weatherDataModel.getDailyTempModelList().size();
        return 0;
    }

    public class WeatherViewHolder extends RecyclerView.ViewHolder {

        View view;
        ImageView weatherIconImageView;
        TextView dateTextView, tempTextView, titleWeatherTextView, descriptionTextView;

        public WeatherViewHolder(View itemView) {
            super(itemView);
            this.view = itemView;

            this.dateTextView = (TextView) this.view.findViewById(R.id.dateText);
            this.tempTextView = (TextView) this.view.findViewById(R.id.temperatureText);
            this.titleWeatherTextView = (TextView) this.view.findViewById(R.id.titleWeatherText);
            this.descriptionTextView = (TextView) this.view.findViewById(R.id.descriptionText);
            this.weatherIconImageView = (ImageView) this.view.findViewById(R.id.weather_icon);

            this.view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    weatherListActivity.cellClicked(getAdapterPosition());
                }
            });
        }
    }
}
