package com.cris.cmsm.models.response;

/**
 *
 */
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TractionDetailVO {

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
    private Object railCode;
    @SerializedName("divisionCode")
    @Expose
    private Object divisionCode;
    @SerializedName("totalTrdConnectedLoad")
    @Expose
    private String totalTrdConnectedLoad;
    @SerializedName("totalTrdAvgCost")
    @Expose
    private String totalTrdAvgCost;

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

    public Object getRailCode() {
        return railCode;
    }

    public void setRailCode(Object railCode) {
        this.railCode = railCode;
    }

    public Object getDivisionCode() {
        return divisionCode;
    }

    public void setDivisionCode(Object divisionCode) {
        this.divisionCode = divisionCode;
    }

    public String getTotalTrdConnectedLoad() {
        return totalTrdConnectedLoad;
    }

    public void setTotalTrdConnectedLoad(String totalTrdConnectedLoad) {
        this.totalTrdConnectedLoad = totalTrdConnectedLoad;
    }

    public String getTotalTrdAvgCost() {
        return totalTrdAvgCost;
    }

    public void setTotalTrdAvgCost(String totalTrdAvgCost) {
        this.totalTrdAvgCost = totalTrdAvgCost;
    }
}