package com.example.charu.xebiaweatherapp.activity;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.charu.xebiaweatherapp.R;
import com.example.charu.xebiaweatherapp.UrlConstants;
import com.example.charu.xebiaweatherapp.adapter.WeatherListAdapter;
import com.example.charu.xebiaweatherapp.application.XebiaWeatherMainApplication;
import com.example.charu.xebiaweatherapp.model.CityDataModel;
import com.example.charu.xebiaweatherapp.model.DailyTempModel;
import com.example.charu.xebiaweatherapp.model.WeatherDataModel;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class WeatherListActivity extends AppCompatActivity {

    private String tag_weather_request = "fetch_weather";
    private ProgressDialog pDialog;
    private RecyclerView recyclerView;
    private WeatherListAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.recyclerView =  (RecyclerView)findViewById(R.id.weather_list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        this.recyclerView.setLayoutManager(linearLayoutManager);
        this.recyclerView.setItemAnimator(new DefaultItemAnimator());

        this.listAdapter = new WeatherListAdapter(this);
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
                    Log.e("nikhil", response.toString());
                    parseWeatherResponse(response);
                    pDialog.hide();
                }
            }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            Log.e("nikhil", "error in fecth weather ");
            pDialog.hide();
        }
    });

    private void parseWeatherResponse(JSONObject response) {
        WeatherDataModel weatherDataModel = new WeatherDataModel();
        try {
            Gson gson = new Gson();
            weatherDataModel.setCityDataModel( gson.fromJson(response.getJSONObject("city").toString(),CityDataModel.class));

            Log.e("nikhil","city" +weatherDataModel.getCityDataModel().getName()+ " "
                    +weatherDataModel.getCityDataModel().getCountry());

            JSONArray jsonArray = response.getJSONArray("list");
            for (int i = 0; i < jsonArray.length(); i++) {
                Log.e("nikhil","iteration "+i);
                weatherDataModel.getDailyTempModelList().add(gson.fromJson(jsonArray.get(i).toString(), DailyTempModel.class));
            }

        } catch (Exception ex) {
            Log.e("nikhil",ex.getMessage());
        }

        Log.e("nikhil","size "+weatherDataModel.getDailyTempModelList().size());
        if(weatherDataModel!=null){
            this.listAdapter.setWeatherDataModel(weatherDataModel);
            this.listAdapter.notifyDataSetChanged();
        }

    }
}
