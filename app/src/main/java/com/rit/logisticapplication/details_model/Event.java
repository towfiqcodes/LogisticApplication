
package com.rit.logisticapplication.details_model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Event {

    @SerializedName("TransactionDate")
    @Expose
    private String transactionDate;
    @SerializedName("StrDate")
    @Expose
    private String strDate;
    @SerializedName("StrTime")
    @Expose
    private String strTime;
    @SerializedName("StationCode")
    @Expose
    private String stationCode;
    @SerializedName("StationDescription")
    @Expose
    private String stationDescription;
    @SerializedName("CountryCode")
    @Expose
    private String countryCode;
    @SerializedName("CountryDescription")
    @Expose
    private String countryDescription;
    @SerializedName("EventCode")
    @Expose
    private String eventCode;
    @SerializedName("EventDescription")
    @Expose
    private String eventDescription;
    @SerializedName("ReasonCode")
    @Expose
    private Object reasonCode;
    @SerializedName("ReasonDescription")
    @Expose
    private Object reasonDescription;
    @SerializedName("CompanyName")
    @Expose
    private String companyName;
    @SerializedName("StationName")
    @Expose
    private String stationName;
    @SerializedName("Remarks")
    @Expose
    private Object remarks;

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getStrDate() {
        return strDate;
    }

    public void setStrDate(String strDate) {
        this.strDate = strDate;
    }

    public String getStrTime() {
        return strTime;
    }

    public void setStrTime(String strTime) {
        this.strTime = strTime;
    }

    public String getStationCode() {
        return stationCode;
    }

    public void setStationCode(String stationCode) {
        this.stationCode = stationCode;
    }

    public String getStationDescription() {
        return stationDescription;
    }

    public void setStationDescription(String stationDescription) {
        this.stationDescription = stationDescription;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCountryDescription() {
        return countryDescription;
    }

    public void setCountryDescription(String countryDescription) {
        this.countryDescription = countryDescription;
    }

    public String getEventCode() {
        return eventCode;
    }

    public void setEventCode(String eventCode) {
        this.eventCode = eventCode;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    public Object getReasonCode() {
        return reasonCode;
    }

    public void setReasonCode(Object reasonCode) {
        this.reasonCode = reasonCode;
    }

    public Object getReasonDescription() {
        return reasonDescription;
    }

    public void setReasonDescription(Object reasonDescription) {
        this.reasonDescription = reasonDescription;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public Object getRemarks() {
        return remarks;
    }

    public void setRemarks(Object remarks) {
        this.remarks = remarks;
    }

}
