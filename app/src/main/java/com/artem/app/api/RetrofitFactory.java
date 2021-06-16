package com.artem.app.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitFactory {

    private static RetrofitFactory instance;

    public static RetrofitFactory getInstance() {
        if (instance == null)
            instance = new RetrofitFactory();
        return instance;
    }

    public ApiApp create(String url) {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(url)
                .client(new OkHttpClient()
                        .newBuilder()
                        .build())
                .build();

        return retrofit.create(ApiApp.class);
    }
}
