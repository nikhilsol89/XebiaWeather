package com.nikhil.xebiaweatherapp.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.nikhil.xebiaweatherapp.R;
import com.nikhil.xebiaweatherapp.UrlConstants;
import com.nikhil.xebiaweatherapp.activity.WeatherListActivity;
import com.nikhil.xebiaweatherapp.application.XebiaWeatherMainApplication;
import com.nikhil.xebiaweatherapp.model.WeatherDataModel;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by nikhil on 6/26/2016.
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
    public void onBindViewHolder(final WeatherViewHolder holder, final int position) {
        holder.weatherIconImageView.setImageBitmap(null);
        if (this.weatherDataModel.getDailyTempModelList().get(position).getWeatherModel().get(0).getImageByteArray() != null) {
            holder.weatherIconImageView.setImageBitmap(BitmapFactory.decodeByteArray(this.weatherDataModel.getDailyTempModelList().get(position).getWeatherModel().get(0).getImageByteArray(),
                    0, this.weatherDataModel.getDailyTempModelList().get(position).getWeatherModel().get(0).getImageByteArray().length));
        }else{
            ImageLoader imageLoader = XebiaWeatherMainApplication.getInstance().getImageLoader();
            String iconUrl = UrlConstants.fetchIconUrl + weatherDataModel.getDailyTempModelList().get(position).getWeatherModel().get(0).getIcon() + ".png";

            imageLoader.get(iconUrl, new ImageLoader.ImageListener() {

                @Override
                public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
                    if (response.getBitmap() != null) {
                        Bitmap bmp = response.getBitmap();

                        ByteArrayOutputStream stream = new ByteArrayOutputStream();
                        bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
                        byte[] byteArray = stream.toByteArray();
                        weatherDataModel.getDailyTempModelList().get(position).getWeatherModel().get(0).setImageByteArray(byteArray);
                        if (weatherDataModel.getDailyTempModelList().get(position).getWeatherModel().get(0).getImageByteArray() != null) {
                            holder.weatherIconImageView.setImageBitmap(BitmapFactory.decodeByteArray(weatherDataModel.getDailyTempModelList().get(position).getWeatherModel().get(0).getImageByteArray(), 0, weatherDataModel.getDailyTempModelList().get(position).getWeatherModel().get(0).getImageByteArray().length));
                        }

                    }
                }

                @Override
                public void onErrorResponse(VolleyError error) {
                }
            });
        }

        holder.dateTextView.setText(new SimpleDateFormat("EEE dd MMM,yyyy").
                format(new Date(Long.parseLong(weatherDataModel.getDailyTempModelList().get(position).getDate()) * 1000)));
        holder.tempTextView.setText(weatherDataModel.getDailyTempModelList().get(position).getTemperatureModel().getDayTemp() + context.getString(R.string.weather_unit));
        holder.humidityTextView.setText(context.getString(R.string.text_humidity)+":"+weatherDataModel.getDailyTempModelList().get(position).getHumidity());
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
        TextView dateTextView, tempTextView, humidityTextView, descriptionTextView;

        public WeatherViewHolder(View itemView) {
            super(itemView);
            this.view = itemView;

            this.dateTextView = (TextView) this.view.findViewById(R.id.dateText);
            this.tempTextView = (TextView) this.view.findViewById(R.id.temperatureText);
            this.humidityTextView = (TextView) this.view.findViewById(R.id.humidityText);
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
