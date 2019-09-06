package com.cris.cmsm.models.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 *
 */

public class SolarPVPanelStationAssetVO {
    @SerializedName("rlyCode")
    @Expose
    private String rlyCode;
    @SerializedName("divison")
    @Expose
    private String divison;
    @SerializedName("state")
    @Expose
    private String state;
    @SerializedName("nameOfStation")
    @Expose
    private String nameOfStation;
    @SerializedName("connectedLoad")
    @Expose
    private String connectedLoad;
    @SerializedName("capacity")
    @Expose
    private String capacity;
    @SerializedName("typeOfLoad")
    @Expose
    private String typeOfLoad;
    @SerializedName("standBy")
    @Expose
    private String standBy;
    @SerializedName("yearOfCommissioning")
    @Expose
    private String yearOfCommissioning;
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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getNameOfStation() {
        return nameOfStation;
    }

    public void setNameOfStation(String nameOfStation) {
        this.nameOfStation = nameOfStation;
    }

    public String getConnectedLoad() {
        return connectedLoad;
    }

    public void setConnectedLoad(String connectedLoad) {
        this.connectedLoad = connectedLoad;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public String getTypeOfLoad() {
        return typeOfLoad;
    }

    public void setTypeOfLoad(String typeOfLoad) {
        this.typeOfLoad = typeOfLoad;
    }

    public String getStandBy() {
        return standBy;
    }

    public void setStandBy(String standBy) {
        this.standBy = standBy;
    }

    public String getYearOfCommissioning() {
        return yearOfCommissioning;
    }

    public void setYearOfCommissioning(String yearOfCommissioning) {
        this.yearOfCommissioning = yearOfCommissioning;
    }

    public String getModeOfMaintenance() {
        return modeOfMaintenance;
    }

    public void setModeOfMaintenance(String modeOfMaintenance) {
        this.modeOfMaintenance = modeOfMaintenance;
    }
}
