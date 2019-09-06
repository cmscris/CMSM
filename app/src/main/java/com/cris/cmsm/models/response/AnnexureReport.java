package com.cris.cmsm.models.response;

/**
 *
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AnnexureReport {

    @SerializedName("railway")
    @Expose
    private String railway;
    @SerializedName("consumption")
    @Expose
    private String consumption;
    @SerializedName("bill")
    @Expose
    private String bill;
    @SerializedName("avCost")
    @Expose
    private String avCost;
    @SerializedName("totalConsumption")
    @Expose
    private Object totalConsumption;
    @SerializedName("totalBill")
    @Expose
    private Object totalBill;
    @SerializedName("totalAvCost")
    @Expose
    private Object totalAvCost;
    @SerializedName("sebName")
    @Expose
    private Object sebName;

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
     * @return The totalConsumption
     */
    public Object getTotalConsumption() {
        return totalConsumption;
    }

    /**
     * @param totalConsumption The totalConsumption
     */
    public void setTotalConsumption(Object totalConsumption) {
        this.totalConsumption = totalConsumption;
    }

    /**
     * @return The totalBill
     */
    public Object getTotalBill() {
        return totalBill;
    }

    /**
     * @param totalBill The totalBill
     */
    public void setTotalBill(Object totalBill) {
        this.totalBill = totalBill;
    }

    /**
     * @return The totalAvCost
     */
    public Object getTotalAvCost() {
        return totalAvCost;
    }

    /**
     * @param totalAvCost The totalAvCost
     */
    public void setTotalAvCost(Object totalAvCost) {
        this.totalAvCost = totalAvCost;
    }

    /**
     * @return The sebName
     */
    public Object getSebName() {
        return sebName;
    }

    /**
     * @param sebName The sebName
     */
    public void setSebName(Object sebName) {
        this.sebName = sebName;
    }

}
