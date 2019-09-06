package com.cris.cmsm.models.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 *
 */

public class LiftAssestVO {

    @SerializedName("rlyCode")
    @Expose
    private String rlyCode;
    @SerializedName("divison")
    @Expose
    private String divison;
    @SerializedName("locationType")
    @Expose
    private String locationType;
    @SerializedName("location")
    @Expose
    private String location;
    @SerializedName("yearOfInstallation")
    @Expose
    private String yearOfInstallation;
    @SerializedName("modeOfMaintenance")
    @Expose
    private String modeOfMaintenance;
    @SerializedName("make")
    @Expose
    private String make;

    public String getRlyCode() {
        return rlyCode;
    }

    public void setRlyCode(String rlyCode) {
        this.rlyCode = rlyCode;
    }

    public String getDivison() {
        return divison;
    }

    public void setDivison(String divison) {
        this.divison = divison;
    }

    public String getLocationType() {
        return locationType;
    }

    public void setLocationType(String locationType) {
        this.locationType = locationType;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getYearOfInstallation() {
        return yearOfInstallation;
    }

    public void setYearOfInstallation(String yearOfInstallation) {
        this.yearOfInstallation = yearOfInstallation;
    }

    public String getModeOfMaintenance() {
        return modeOfMaintenance;
    }

    public void setModeOfMaintenance(String modeOfMaintenance) {
        this.modeOfMaintenance = modeOfMaintenance;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }
}
