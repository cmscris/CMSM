package com.cris.cmsm.models.response;

/**
 *
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GeneralDetailVO {
    @SerializedName("xAxixVal")
    @Expose
    private String xAxixVal;
    @SerializedName("consumption")
    @Expose
    private String consumption;
    @SerializedName("bill")
    @Expose
    private String bill;
    @SerializedName("railCode")
    @Expose
    private String railCode;
    @SerializedName("divisionCode")
    @Expose
    private String divisionCode;
    @SerializedName("totalGenConnectedLoad")
    @Expose
    private String totalGenConnectedLoad;
    @SerializedName("totalGenAvgCost")
    @Expose
    private String totalGenAvgCost;

    public String getXAxixVal() {
        return xAxixVal;
    }

    public void setXAxixVal(String xAxixVal) {
        this.xAxixVal = xAxixVal;
    }

    public String getConsumption() {
        return consumption;
    }

    public void setConsumption(String consumption) {
        this.consumption = consumption;
    }

    public String getBill() {
        return bill;
    }

    public void setBill(String bill) {
        this.bill = bill;
    }

    public String getRailCode() {
        return railCode;
    }

    public void setRailCode(String railCode) {
        this.railCode = railCode;
    }

    public String getDivisionCode() {
        return divisionCode;
    }

    public void setDivisionCode(String divisionCode) {
        this.divisionCode = divisionCode;
    }

    public String getTotalGenConnectedLoad() {
        return totalGenConnectedLoad;
    }

    public void setTotalGenConnectedLoad(String totalGenConnectedLoad) {
        this.totalGenConnectedLoad = totalGenConnectedLoad;
    }

    public String getTotalGenAvgCost() {
        return totalGenAvgCost;
    }

    public void setTotalGenAvgCost(String totalGenAvgCost) {
        this.totalGenAvgCost = totalGenAvgCost;
    }

}
