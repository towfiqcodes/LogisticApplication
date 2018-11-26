package com.rit.logisticapplication.web_api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    public static Retrofit getRetrofit(){
        return new Retrofit.Builder()
                .baseUrl("http://api.unixus.com.my/Tracking/V2/Tracking.svc/json/")
                .addConverterFactory( GsonConverterFactory.create())
                .build();



    }

}
