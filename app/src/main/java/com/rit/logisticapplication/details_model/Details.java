
package com.rit.logisticapplication.details_model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Details {

    @SerializedName("TrackDetailsResponse")
    @Expose
    private TrackDetailsResponse trackDetailsResponse;

    public  TrackDetailsResponse getTrackDetailsResponse() {
        return trackDetailsResponse;
    }

    public void setTrackDetailsResponse(TrackDetailsResponse trackDetailsResponse) {
        this.trackDetailsResponse = trackDetailsResponse;
    }

}
