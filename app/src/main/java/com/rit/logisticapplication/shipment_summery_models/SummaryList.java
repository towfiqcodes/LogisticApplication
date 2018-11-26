
package com.rit.logisticapplication.shipment_summery_models;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SummaryList {

    @SerializedName("HawbNo")
    @Expose
    private String hawbNo;
    @SerializedName("XR1")
    @Expose
    private String xR1;
    @SerializedName("XR2")
    @Expose
    private List<String> xR2 = null;
    @SerializedName("ShipmentDate")
    @Expose
    private String shipmentDate;
    @SerializedName("DeliveryDate")
    @Expose
    private String deliveryDate;
    @SerializedName("RecipientName")
    @Expose
    private Object recipientName;
    @SerializedName("SignedName")
    @Expose
    private String signedName;
    @SerializedName("OriginStationCode")
    @Expose
    private String originStationCode;
    @SerializedName("OriginStationDescription")
    @Expose
    private String originStationDescription;
    @SerializedName("OriginCountryCode")
    @Expose
    private String originCountryCode;
    @SerializedName("OriginCountryDescription")
    @Expose
    private String originCountryDescription;
    @SerializedName("DestinationStationCode")
    @Expose
    private String destinationStationCode;
    @SerializedName("DestinationStationDescription")
    @Expose
    private String destinationStationDescription;
    @SerializedName("DestinationCountryCode")
    @Expose
    private String destinationCountryCode;
    @SerializedName("DestinationCountryDescription")
    @Expose
    private Object destinationCountryDescription;
    @SerializedName("EventCode")
    @Expose
    private String eventCode;
    @SerializedName("EventDescription")
    @Expose
    private String eventDescription;
    @SerializedName("ReasonCode")
    @Expose
    private String reasonCode;
    @SerializedName("ReasonDescription")
    @Expose
    private String reasonDescription;
    @SerializedName("CAddressOne")
    @Expose
    private Object cAddressOne;
    @SerializedName("CAddressTwo")
    @Expose
    private Object cAddressTwo;
    @SerializedName("CAddressThree")
    @Expose
    private Object cAddressThree;
    @SerializedName("CAddressFour")
    @Expose
    private Object cAddressFour;
    @SerializedName("CCity")
    @Expose
    private String cCity;
    @SerializedName("CPostCode")
    @Expose
    private String cPostCode;
    @SerializedName("CState")
    @Expose
    private String cState;
    @SerializedName("XR2AgentCode")
    @Expose
    private String xR2AgentCode;
    @SerializedName("TrackingURL")
    @Expose
    private String trackingURL;
    @SerializedName("Events")
    @Expose
    private Object events;

    public String getHawbNo() {
        return hawbNo;
    }

    public void setHawbNo(String hawbNo) {
        this.hawbNo = hawbNo;
    }

    public String getXR1() {
        return xR1;
    }

    public void setXR1(String xR1) {
        this.xR1 = xR1;
    }

    public List<String> getXR2() {
        return xR2;
    }

    public void setXR2(List<String> xR2) {
        this.xR2 = xR2;
    }

    public String getShipmentDate() {
        return shipmentDate;
    }

    public void setShipmentDate(String shipmentDate) {
        this.shipmentDate = shipmentDate;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String  deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public Object getRecipientName() {
        return recipientName;
    }

    public void setRecipientName(Object recipientName) {
        this.recipientName = recipientName;
    }

    public String getSignedName() {
        return signedName;
    }

    public void setSignedName(String signedName) {
        this.signedName = signedName;
    }

    public String getOriginStationCode() {
        return originStationCode;
    }

    public void setOriginStationCode(String originStationCode) {
        this.originStationCode = originStationCode;
    }

    public String getOriginStationDescription() {
        return originStationDescription;
    }

    public void setOriginStationDescription(String originStationDescription) {
        this.originStationDescription = originStationDescription;
    }

    public String getOriginCountryCode() {
        return originCountryCode;
    }

    public void setOriginCountryCode(String originCountryCode) {
        this.originCountryCode = originCountryCode;
    }

    public String getOriginCountryDescription() {
        return originCountryDescription;
    }

    public void setOriginCountryDescription(String originCountryDescription) {
        this.originCountryDescription = originCountryDescription;
    }

    public String getDestinationStationCode() {
        return destinationStationCode;
    }

    public void setDestinationStationCode(String destinationStationCode) {
        this.destinationStationCode = destinationStationCode;
    }

    public String getDestinationStationDescription() {
        return destinationStationDescription;
    }

    public void setDestinationStationDescription(String destinationStationDescription) {
        this.destinationStationDescription = destinationStationDescription;
    }

    public String getDestinationCountryCode() {
        return destinationCountryCode;
    }

    public void setDestinationCountryCode(String destinationCountryCode) {
        this.destinationCountryCode = destinationCountryCode;
    }

    public Object getDestinationCountryDescription() {
        return destinationCountryDescription;
    }

    public void setDestinationCountryDescription(Object destinationCountryDescription) {
        this.destinationCountryDescription = destinationCountryDescription;
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

    public String getReasonCode() {
        return reasonCode;
    }

    public void setReasonCode(String reasonCode) {
        this.reasonCode = reasonCode;
    }

    public String getReasonDescription() {
        return reasonDescription;
    }

    public void setReasonDescription(String reasonDescription) {
        this.reasonDescription = reasonDescription;
    }

    public Object getCAddressOne() {
        return cAddressOne;
    }

    public void setCAddressOne(Object cAddressOne) {
        this.cAddressOne = cAddressOne;
    }

    public Object getCAddressTwo() {
        return cAddressTwo;
    }

    public void setCAddressTwo(Object cAddressTwo) {
        this.cAddressTwo = cAddressTwo;
    }

    public Object getCAddressThree() {
        return cAddressThree;
    }

    public void setCAddressThree(Object cAddressThree) {
        this.cAddressThree = cAddressThree;
    }

    public Object getCAddressFour() {
        return cAddressFour;
    }

    public void setCAddressFour(Object cAddressFour) {
        this.cAddressFour = cAddressFour;
    }

    public String getCCity() {
        return cCity;
    }

    public void setCCity(String cCity) {
        this.cCity = cCity;
    }

    public String getCPostCode() {
        return cPostCode;
    }

    public void setCPostCode(String cPostCode) {
        this.cPostCode = cPostCode;
    }

    public String getCState() {
        return cState;
    }

    public void setCState(String cState) {
        this.cState = cState;
    }

    public String getXR2AgentCode() {
        return xR2AgentCode;
    }

    public void setXR2AgentCode(String xR2AgentCode) {
        this.xR2AgentCode = xR2AgentCode;
    }

    public String getTrackingURL() {
        return trackingURL;
    }

    public void setTrackingURL(String trackingURL) {
        this.trackingURL = trackingURL;
    }

    public Object getEvents() {
        return events;
    }

    public void setEvents(Object events) {
        this.events = events;
    }

}
