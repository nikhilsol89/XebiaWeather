package com.example.charu.xebiaweatherapp.application;

import android.app.Application;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by Charu on 6/26/2016.
 */
public class XebiaWeatherMainApplication extends Application {

    String tagForRequestQueue = XebiaWeatherMainApplication.class.getSimpleName();
    RequestQueue volleyrequestQueue;
    private static XebiaWeatherMainApplication mainApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        mainApplication = this;
    }

    public static XebiaWeatherMainApplication getInstance() {
        return mainApplication;
    }

    public RequestQueue getRequestQueue() {
        if (volleyrequestQueue == null) {
            volleyrequestQueue = Volley.newRequestQueue(getApplicationContext());
        }
        return volleyrequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> request, String tag) {
        request.setTag(tag == null ? tagForRequestQueue : tag);
        getRequestQueue().add(request);
    }

    public <T> void addToRequestQueue(Request<T> request) {
        request.setTag(tagForRequestQueue);
        getRequestQueue().add(request);
    }

    public void cancelPendingRequest(Object tag) {
        if (volleyrequestQueue != null) {
            volleyrequestQueue.cancelAll(tag);
        }
    }
}
