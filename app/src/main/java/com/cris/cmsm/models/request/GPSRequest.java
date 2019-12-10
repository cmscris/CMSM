package com.cris.cmsm.models.request;

public class GPSRequest {

    private String latitude;
    private String longitude;
    private int noOfStations;


    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public int getNoOfStations() {
        return noOfStations;
    }

    public void setNoOfStations(int noOfStations) {
        this.noOfStations = noOfStations;
    }
}