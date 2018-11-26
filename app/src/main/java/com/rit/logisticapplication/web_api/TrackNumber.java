package com.rit.logisticapplication.web_api;

import com.rit.logisticapplication.details_model.Details;
import com.rit.logisticapplication.shipment_summery_models.ShipmentSummery;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface TrackNumber {
    @GET("Details/?")
    Call<Details>getTrackingNumber(@Query( "ids" ) String ids);
    @GET("Summary/?")
    Call<ShipmentSummery>getShipmentSummery(@Query("ids") String ids);
}
