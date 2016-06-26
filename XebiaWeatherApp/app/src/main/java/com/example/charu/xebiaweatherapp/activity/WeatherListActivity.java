package com.example.charu.xebiaweatherapp.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.charu.xebiaweatherapp.R;
import com.example.charu.xebiaweatherapp.UrlConstants;
import com.example.charu.xebiaweatherapp.adapter.WeatherListAdapter;
import com.example.charu.xebiaweatherapp.application.XebiaWeatherMainApplication;
import com.example.charu.xebiaweatherapp.listener.WeatherCellClickListener;
import com.example.charu.xebiaweatherapp.model.BitmapDataObject;
import com.example.charu.xebiaweatherapp.model.CityDataModel;
import com.example.charu.xebiaweatherapp.model.DailyTempModel;
import com.example.charu.xebiaweatherapp.model.WeatherDataModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
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
                    Log.e("nikhil", response.toString());
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
            Log.e("nikhil", "error in fecth weather ");
            pDialog.hide();
        }
    });

    private void parseWeatherResponse(JSONObject response) {

        weatherDataModel = new WeatherDataModel();
        try {
            Gson gson = new Gson();
            weatherDataModel.setCityDataModel(gson.fromJson(response.getJSONObject("city").toString(), CityDataModel.class));

            Log.e("nikhil", "city" + weatherDataModel.getCityDataModel().getName() + " "
                    + weatherDataModel.getCityDataModel().getCountry());

            List<DailyTempModel> postsList = Arrays.asList(gson.fromJson(response.getJSONArray("list").toString(),
                    DailyTempModel[].class));
            weatherDataModel.setDailyTempModelList(postsList);

            ImageLoader imageLoader = XebiaWeatherMainApplication.getInstance().getImageLoader();
            for (int i = 0; i < weatherDataModel.getDailyTempModelList().size(); i++) {
                this.position = i;
                String iconUrl = UrlConstants.fetchIconUrl + weatherDataModel.getDailyTempModelList().get(position).getWeatherModel().get(0).getIcon() + ".png";

                Log.e("nikhil", "iconUrl " + iconUrl);
                // If you are using normal ImageView
                imageLoader.get(iconUrl, new ImageLoader.ImageListener() {

                    @Override
                    public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
                        if (response.getBitmap() != null) {
                            // load image into imageview
                            Log.e("nikhil", "Bitmap came " + response.getBitmap());

                            Bitmap bmp = response.getBitmap();

                            ByteArrayOutputStream stream = new ByteArrayOutputStream();
                            bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
                            byte[] byteArray = stream.toByteArray();
                            Log.e("nikhil", "Byte Array Lenghth:" + byteArray.length);
                            weatherDataModel.getDailyTempModelList().get(position).getWeatherModel().get(0).setImageByteArray(byteArray);
                            if (weatherDataModel.getDailyTempModelList().get(position).getWeatherModel().get(0).getImageByteArray() != null) {
                                listAdapter.setWeatherDataModel(weatherDataModel);
                                listAdapter.notifyDataSetChanged();
                            }

                        }
                    }

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("nikhil", "Image Load Error: " + error.getMessage());
                    }
                });
            }
            if (weatherDataModel != null) {
                listAdapter.setWeatherDataModel(weatherDataModel);
                listAdapter.notifyDataSetChanged();
            }
        } catch (JSONException e) {
            Log.e("nikhil", "ex " + e.getMessage());
            Toast.makeText(this, "Weather Api Error", Toast.LENGTH_LONG).show();
        } catch (Exception ex) {
            Log.e("nikhil", "ex " + ex.getMessage());
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
