package com.cris.cmsm.models.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 *
 */

public class HtPannelChargerVO {


    @SerializedName("assestId")
    @Expose
    private String assestId;
    @SerializedName("paramId")
    @Expose
    private String paramId;
    @SerializedName("feederType")
    @Expose
    private String feederType;
    @SerializedName("commissionDate")
    @Expose
    private String commissionDate;
    @SerializedName("chargerCapacity")
    @Expose
    private String chargerCapacity;
    @SerializedName("make")
    @Expose
    private String make;
    @SerializedName("voltage")
    @Expose
    private String voltage;
    @SerializedName("batteryCapacity")
    @Expose
    private String batteryCapacity;
    @SerializedName("batteryLugDate")
    @Expose
    private String batteryLugDate;
    @SerializedName("remarks")
    @Expose
    private String remarks;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("make_desc")
    @Expose
    private String makeDesc;
    @SerializedName("voltage_desc")
    @Expose
    private String voltageDesc;
    @SerializedName("acquisitionCost")
    @Expose
    private String acquisitionCost;

    public String getAssestId() {
        return assestId;
    }

    public void setAssestId(String assestId) {
        this.assestId = assestId;
    }

    public String getParamId() {
        return paramId;
    }

    public void setParamId(String paramId) {
        this.paramId = paramId;
    }

    public String getFeederType() {
        return feederType;
    }

    public void setFeederType(String feederType) {
        this.feederType = feederType;
    }

    public String getCommissionDate() {
        return commissionDate;
    }

    public void setCommissionDate(String commissionDate) {
        this.commissionDate = commissionDate;
    }

    public String getChargerCapacity() {
        return chargerCapacity;
    }

    public void setChargerCapacity(String chargerCapacity) {
        this.chargerCapacity = chargerCapacity;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getVoltage() {
        return voltage;
    }

    public void setVoltage(String voltage) {
        this.voltage = voltage;
    }

    public String getBatteryCapacity() {
        return batteryCapacity;
    }

    public void setBatteryCapacity(String batteryCapacity) {
        this.batteryCapacity = batteryCapacity;
    }

    public String getBatteryLugDate() {
        return batteryLugDate;
    }

    public void setBatteryLugDate(String batteryLugDate) {
        this.batteryLugDate = batteryLugDate;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMakeDesc() {
        return makeDesc;
    }

    public void setMakeDesc(String makeDesc) {
        this.makeDesc = makeDesc;
    }

    public String getVoltageDesc() {
        return voltageDesc;
    }

    public void setVoltageDesc(String voltageDesc) {
        this.voltageDesc = voltageDesc;
    }

    public String getAcquisitionCost() {
        return acquisitionCost;
    }

    public void setAcquisitionCost(String acquisitionCost) {
        this.acquisitionCost = acquisitionCost;
    }
}
