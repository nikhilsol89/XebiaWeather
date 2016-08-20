package com.nikhil.xebiaweatherapp.application;

import android.app.Application;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.nikhil.xebiaweatherapp.model.LruBitmapCache;

/**
 * Created by nikhil on 6/26/2016.
 */
public class XebiaWeatherMainApplication extends Application {

    String tagForRequestQueue = XebiaWeatherMainApplication.class.getSimpleName();
    RequestQueue volleyrequestQueue;
    ImageLoader volleyImageLoader;

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

    public ImageLoader getImageLoader() {
        getRequestQueue();
        if (volleyImageLoader == null) {
            volleyImageLoader = new ImageLoader(this.volleyrequestQueue,
                    new LruBitmapCache());
        }
        return this.volleyImageLoader;
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
