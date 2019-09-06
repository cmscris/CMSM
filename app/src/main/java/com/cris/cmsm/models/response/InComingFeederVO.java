package com.cris.cmsm.models.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 *
 */

public class InComingFeederVO {

    @SerializedName("assestId")
    @Expose
    private String assestId;
    @SerializedName("paramId")
    @Expose
    private String paramId;
    @SerializedName("feederType")
    @Expose
    private String feederType;
    @SerializedName("feederName")
    @Expose
    private String feederName;
    @SerializedName("voltage")
    @Expose
    private String voltage;
    @SerializedName("typeOfBreaker")
    @Expose
    private String typeOfBreaker;
    @SerializedName("make")
    @Expose
    private String make;
    @SerializedName("rating")
    @Expose
    private String rating;
    @SerializedName("breakingCapacity")
    @Expose
    private String breakingCapacity;
    @SerializedName("commissionDate")
    @Expose
    private String commissionDate;
    @SerializedName("ctratio")
    @Expose
    private String ctratio;
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
    @SerializedName("breaker_desc")
    @Expose
    private String breakerDesc;
    @SerializedName("acquisitionCost")
    @Expose
    private String acquisitionCost;
    @SerializedName("relayCount")
    @Expose
    private Integer relayCount;

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

    public String getFeederName() {
        return feederName;
    }

    public void setFeederName(String feederName) {
        this.feederName = feederName;
    }

    public String getVoltage() {
        return voltage;
    }

    public void setVoltage(String voltage) {
        this.voltage = voltage;
    }

    public String getTypeOfBreaker() {
        return typeOfBreaker;
    }

    public void setTypeOfBreaker(String typeOfBreaker) {
        this.typeOfBreaker = typeOfBreaker;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getBreakingCapacity() {
        return breakingCapacity;
    }

    public void setBreakingCapacity(String breakingCapacity) {
        this.breakingCapacity = breakingCapacity;
    }

    public String getCommissionDate() {
        return commissionDate;
    }

    public void setCommissionDate(String commissionDate) {
        this.commissionDate = commissionDate;
    }

    public String getCtratio() {
        return ctratio;
    }

    public void setCtratio(String ctratio) {
        this.ctratio = ctratio;
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

    public String getBreakerDesc() {
        return breakerDesc;
    }

    public void setBreakerDesc(String breakerDesc) {
        this.breakerDesc = breakerDesc;
    }

    public String getAcquisitionCost() {
        return acquisitionCost;
    }

    public void setAcquisitionCost(String acquisitionCost) {
        this.acquisitionCost = acquisitionCost;
    }

    public Integer getRelayCount() {
        return relayCount;
    }

    public void setRelayCount(Integer relayCount) {
        this.relayCount = relayCount;
    }
}
