package com.cris.cmsm.models.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 *
 */
public class ConSummaryResponse {
    @SerializedName("year")
    @Expose
    private String year;
    @SerializedName("totalBill")
    @Expose
    private String totalBill;
    @SerializedName("totalConsumption")
    @Expose
    private String totalConsumption;
    @SerializedName("totalConnectedLoad")
    @Expose
    private String totalConnectedLoad;
    @SerializedName("totalAvgCost")
    @Expose
    private String totalAvgCost;

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getTotalBill() {
        return totalBill;
    }

    public void setTotalBill(String totalBill) {
        this.totalBill = totalBill;
    }

    public String getTotalConsumption() {
        return totalConsumption;
    }

    public void setTotalConsumption(String totalConsumption) {
        this.totalConsumption = totalConsumption;
    }

    public String getTotalConnectedLoad() {
        return totalConnectedLoad;
    }

    public void setTotalConnectedLoad(String totalConnectedLoad) {
        this.totalConnectedLoad = totalConnectedLoad;
    }

    public String getTotalAvgCost() {
        return totalAvgCost;
    }

    public void setTotalAvgCost(String totalAvgCost) {
        this.totalAvgCost = totalAvgCost;
    }

}
