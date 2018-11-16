
package com.rit.logisticapplication.shipment_summery_models;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TrackSummaryResponse {

    @SerializedName("ErrorCode")
    @Expose
    private String errorCode;
    @SerializedName("ErrorMessage")
    @Expose
    private String errorMessage;
    @SerializedName("SummaryList")
    @Expose
    private List<SummaryList> summaryList = null;

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public List<SummaryList> getSummaryList() {
        return summaryList;
    }

    public void setSummaryList(List<SummaryList> summaryList) {
        this.summaryList = summaryList;
    }

}
