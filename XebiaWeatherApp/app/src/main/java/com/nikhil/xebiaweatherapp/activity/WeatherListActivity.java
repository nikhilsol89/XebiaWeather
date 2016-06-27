package com.nikhil.xebiaweatherapp.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.nikhil.xebiaweatherapp.R;
import com.nikhil.xebiaweatherapp.UrlConstants;
import com.nikhil.xebiaweatherapp.adapter.WeatherListAdapter;
import com.nikhil.xebiaweatherapp.application.XebiaWeatherMainApplication;
import com.nikhil.xebiaweatherapp.listener.WeatherCellClickListener;
import com.nikhil.xebiaweatherapp.model.CityDataModel;
import com.nikhil.xebiaweatherapp.model.DailyTempModel;
import com.nikhil.xebiaweatherapp.model.WeatherDataModel;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.List;

public class WeatherListActivity extends AppCompatActivity implements WeatherCellClickListener {

    private String tag_weather_request = "fetch_weather";
    private ProgressDialog pDialog;
    private RecyclerView recyclerView;
    private WeatherListAdapter listAdapter;
    WeatherDataModel weatherDataModel;
    int position = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.recyclerView = (RecyclerView) findViewById(R.id.weather_list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        this.recyclerView.setLayoutManager(linearLayoutManager);
        this.recyclerView.setItemAnimator(new DefaultItemAnimator());

        this.listAdapter = new WeatherListAdapter(this, this);
        this.recyclerView.setAdapter(this.listAdapter);

        makeWeatherCall();
    }

    private void makeWeatherCall() {
        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading Weather");
        pDialog.setCancelable(false);
        pDialog.show();
        XebiaWeatherMainApplication.getInstance().addToRequestQueue(fetchWeatherJsonRequest, tag_weather_request);
    }

    JsonObjectRequest fetchWeatherJsonRequest = new JsonObjectRequest(Request.Method.GET, UrlConstants.fetchWeatherUrl, null,
            new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        if (response.getString("cod").equalsIgnoreCase("200")) {
                            parseWeatherResponse(response);
                        } else if (!response.getString("cod").equalsIgnoreCase("200")) {
                            return;
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    pDialog.hide();
                }
            }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            pDialog.hide();
        }
    });

    private void parseWeatherResponse(JSONObject response) {

        weatherDataModel = new WeatherDataModel();
        try {
            Gson gson = new Gson();
            weatherDataModel.setCityDataModel(gson.fromJson(response.getJSONObject("city").toString(), CityDataModel.class));

            List<DailyTempModel> weatherList = Arrays.asList(gson.fromJson(response.getJSONArray("list").toString(),
                    DailyTempModel[].class));
            weatherDataModel.setDailyTempModelList(weatherList);


            if (weatherDataModel != null) {
                listAdapter.setWeatherDataModel(weatherDataModel);
                listAdapter.notifyDataSetChanged();
            }
        } catch (JSONException e) {
            Toast.makeText(this, "Weather Api Error", Toast.LENGTH_LONG).show();
        } catch (Exception ex) {
            Toast.makeText(this, "Weather Api Error", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void cellClicked(int position) {

        Intent intent = new Intent(WeatherListActivity.this, WeatherDetailActivity.class);
        intent.putExtra("weather", weatherDataModel);
        intent.putExtra("position", position);
        startActivity(intent);
    }
}
