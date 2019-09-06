package com.cris.cmsm.models.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 *
 */

public class SolarPVPanelBldgVO {

    @SerializedName("rlyCode")
    @Expose
    private String rlyCode;
    @SerializedName("divison")
    @Expose
    private String divison;
    @SerializedName("location")
    @Expose
    private Object location;
    @SerializedName("state")
    @Expose
    private String state;
    @SerializedName("nameOfBuilding")
    @Expose
    private String nameOfBuilding;
    @SerializedName("connectedLoad")
    @Expose
    private String connectedLoad;
    @SerializedName("capacity")
    @Expose
    private String capacity;
    @SerializedName("typeOfLoad")
    @Expose
    private String typeOfLoad;
    @SerializedName("averageCost")
    @Expose
    private String averageCost;
    @SerializedName("standBy")
    @Expose
    private String standBy;
    @SerializedName("yearOfCommissioning")
    @Expose
    private String yearOfCommissioning;
    @SerializedName("modeOfMaintenance")
    @Expose
    private String modeOfMaintenance;
    @SerializedName("annexureId")
    @Expose
    private String annexureId;

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

    public Object getLocation() {
        return location;
    }

    public void setLocation(Object location) {
        this.location = location;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getNameOfBuilding() {
        return nameOfBuilding;
    }

    public void setNameOfBuilding(String nameOfBuilding) {
        this.nameOfBuilding = nameOfBuilding;
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

    public String getAverageCost() {
        return averageCost;
    }

    public void setAverageCost(String averageCost) {
        this.averageCost = averageCost;
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

    public String getAnnexureId() {
        return annexureId;
    }

    public void setAnnexureId(String annexureId) {
        this.annexureId = annexureId;
    }
}
