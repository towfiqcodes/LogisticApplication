
package com.rit.logisticapplication.details_model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EventList {

    @SerializedName("HawbNo")
    @Expose
    private String hawbNo;
    @SerializedName("Events")
    @Expose
    private List<Event> events = null;

    public String getHawbNo() {
        return hawbNo;
    }

    public void setHawbNo(String hawbNo) {
        this.hawbNo = hawbNo;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

}
