package com.cris.cmsm.interactor;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.cris.cmsm.presenterview.APIView;
import com.cris.cmsm.util.URLS;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.OkHttpClient.Builder;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WebServices {
    private static WebServices instance = null;
    Gson gson;

    final OkHttpClient okHttpClient;
    Retrofit retrofit;
    APIView service;

    private WebServices() {
        this.gson = new GsonBuilder().disableHtmlEscaping().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").create();
        this.okHttpClient = new Builder().readTimeout(60, TimeUnit.SECONDS).connectTimeout(60, TimeUnit.SECONDS).build();
        this.retrofit = new Retrofit.Builder().baseUrl(URLS.BASE_URL).addConverterFactory(GsonConverterFactory.create(this.gson)).client(this.okHttpClient).build();
        this.service = this.retrofit.create(APIView.class);
    }

    public static WebServices getInstance() {
        if (instance == null) {
            instance = new WebServices();
        }
        return instance;
    }

    public APIView getService() {
        return this.service;
    }

    public void cancelAllRequest() {
        if (okHttpClient != null)
            okHttpClient.dispatcher().cancelAll();
    }

}
