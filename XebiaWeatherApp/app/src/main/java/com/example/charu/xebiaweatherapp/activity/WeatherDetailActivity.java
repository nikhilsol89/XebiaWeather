package com.example.charu.xebiaweatherapp.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.charu.xebiaweatherapp.R;
import com.example.charu.xebiaweatherapp.model.WeatherDataModel;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Charu on 6/26/2016.
 */
public class WeatherDetailActivity extends AppCompatActivity {

    private WeatherDataModel weatherDataModel;
    ImageView weatherIconImageView;
    TextView dateTextView, tempTextView, titleWeatherTextView, descriptionTextView, pressureTextView, humidityTextview, windSpeedTextview;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        initViews();

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            this.weatherDataModel = (WeatherDataModel) bundle.getSerializable("weather");
            this.position = bundle.getInt("position");
        }
        if (this.weatherDataModel != null) {
            setData();
        }


    }

    private void initViews() {
        this.dateTextView = (TextView) findViewById(R.id.details_dateText);
        this.tempTextView = (TextView) findViewById(R.id.details_temperatureText);
        this.pressureTextView = (TextView) findViewById(R.id.details_pressureText);
        this.humidityTextview = (TextView) findViewById(R.id.details_humidityText);
        this.windSpeedTextview = (TextView) findViewById(R.id.details_windSpeedText);
        this.titleWeatherTextView = (TextView) findViewById(R.id.details_titleWeatherText);
        this.descriptionTextView = (TextView) findViewById(R.id.details_descriptionText);
        this.weatherIconImageView = (ImageView) findViewById(R.id.details_weather_icon);

    }

    private void setData() {
        this.dateTextView.setText(new SimpleDateFormat("EEE dd MMM,yyyy").
                format(new Date(Long.parseLong(weatherDataModel.getDailyTempModelList().get(position).getDate()) * 1000)));
        this.tempTextView.setText(weatherDataModel.getDailyTempModelList().get(position).getTemperatureModel().getDayTemp() + this.getString(R.string.weather_unit));
        this.titleWeatherTextView.setText(weatherDataModel.getDailyTempModelList().get(position).getWeatherModel().get(0).getWeatherTitle());
        this.descriptionTextView.setText(weatherDataModel.getDailyTempModelList().get(position).getWeatherModel().get(0).getWeatherDescription());
        this.humidityTextview.setText(weatherDataModel.getDailyTempModelList().get(position).getHumidity());
        this.pressureTextView.setText(weatherDataModel.getDailyTempModelList().get(position).getPressure());
        this.windSpeedTextview.setText(weatherDataModel.getDailyTempModelList().get(position).getWindSpeed());
    }

}
