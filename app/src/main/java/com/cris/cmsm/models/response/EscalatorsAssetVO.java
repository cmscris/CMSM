package com.cris.cmsm.models.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 *
 */

public class EscalatorsAssetVO {

    @SerializedName("rlyCode")
    @Expose
    private String rlyCode;
    @SerializedName("divison")
    @Expose
    private String divison;
    @SerializedName("location")
    @Expose
    private String location;
    @SerializedName("make")
    @Expose
    private String make;
    @SerializedName("yearOfInstallation")
    @Expose
    private String yearOfInstallation;
    @SerializedName("modeOfMaintenance")
    @Expose
    private String modeOfMaintenance;

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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
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
}
