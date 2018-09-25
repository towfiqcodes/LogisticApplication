package com.rit.logisticapplication.web_api;

import com.rit.logisticapplication.details_model.Details;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TrackNumber {
    @GET("?")
    Call<Details>getTrackingNumber(@Query( "ids" ) String ids);
}
