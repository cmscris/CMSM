package com.cris.cmsm.models.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 *
 */
public class AnnexureReportVO {

    @SerializedName("railway")
    @Expose
    private String railway;
    @SerializedName("connectedLoad")
    @Expose
    private String connectedLoad;
    @SerializedName("consumption")
    @Expose
    private String consumption;
    @SerializedName("bill")
    @Expose
    private String bill;
    @SerializedName("avCost")
    @Expose
    private String avCost;
    @SerializedName("totalLoad")
    @Expose
    private String totalLoad;
    @SerializedName("totalConsumption")
    @Expose
    private String totalConsumption;
    @SerializedName("totalBill")
    @Expose
    private String totalBill;
    @SerializedName("totalAvCost")
    @Expose
    private String totalAvCost;

    /**
     * @return The railway
     */
    public String getRailway() {
        return railway;
    }

    /**
     * @param railway The railway
     */
    public void setRailway(String railway) {
        this.railway = railway;
    }

    /**
     * @return The connectedLoad
     */
    public String getConnectedLoad() {
        return connectedLoad;
    }

    /**
     * @param connectedLoad The connectedLoad
     */
    public void setConnectedLoad(String connectedLoad) {
        this.connectedLoad = connectedLoad;
    }

    /**
     * @return The consumption
     */
    public String getConsumption() {
        return consumption;
    }

    /**
     * @param consumption The consumption
     */
    public void setConsumption(String consumption) {
        this.consumption = consumption;
    }

    /**
     * @return The bill
     */
    public String getBill() {
        return bill;
    }

    /**
     * @param bill The bill
     */
    public void setBill(String bill) {
        this.bill = bill;
    }

    /**
     * @return The avCost
     */
    public String getAvCost() {
        return avCost;
    }

    /**
     * @param avCost The avCost
     */
    public void setAvCost(String avCost) {
        this.avCost = avCost;
    }

    /**
     * @return The totalLoad
     */
    public String getTotalLoad() {
        return totalLoad;
    }

    /**
     * @param totalLoad The totalLoad
     */
    public void setTotalLoad(String totalLoad) {
        this.totalLoad = totalLoad;
    }

    /**
     * @return The totalConsumption
     */
    public String getTotalConsumption() {
        return totalConsumption;
    }

    /**
     * @param totalConsumption The totalConsumption
     */
    public void setTotalConsumption(String totalConsumption) {
        this.totalConsumption = totalConsumption;
    }

    /**
     * @return The totalBill
     */
    public String getTotalBill() {
        return totalBill;
    }

    /**
     * @param totalBill The totalBill
     */
    public void setTotalBill(String totalBill) {
        this.totalBill = totalBill;
    }

    /**
     * @return The totalAvCost
     */
    public String getTotalAvCost() {
        return totalAvCost;
    }

    /**
     * @param totalAvCost The totalAvCost
     */
    public void setTotalAvCost(String totalAvCost) {
        this.totalAvCost = totalAvCost;
    }

}
