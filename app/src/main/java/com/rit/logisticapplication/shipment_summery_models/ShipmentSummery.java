
package com.rit.logisticapplication.shipment_summery_models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ShipmentSummery {

    @SerializedName("TrackSummaryResponse")
    @Expose
    private TrackSummaryResponse trackSummaryResponse;

    public TrackSummaryResponse getTrackSummaryResponse() {
        return trackSummaryResponse;
    }

    public void setTrackSummaryResponse(TrackSummaryResponse trackSummaryResponse) {
        this.trackSummaryResponse = trackSummaryResponse;
    }

}
