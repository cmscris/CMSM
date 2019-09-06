package com.cris.cmsm.models.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 *
 */

public class AnnexureRB4vo {

    @SerializedName("sebs")
    @Expose
    private String sebs;
    @SerializedName("consumption")
    @Expose
    private String consumption;
    @SerializedName("consumption1")
    @Expose
    private String consumption1;
    @SerializedName("average")
    @Expose
    private String average;
    @SerializedName("average1")
    @Expose
    private String average1;
    @SerializedName("billing")
    @Expose
    private String billing;
    @SerializedName("billing1")
    @Expose
    private String billing1;

    /**
     * @return The sebs
     */
    public String getSebs() {
        return sebs;
    }

    /**
     * @param sebs The sebs
     */
    public void setSebs(String sebs) {
        this.sebs = sebs;
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
     * @return The consumption1
     */
    public String getConsumption1() {
        return consumption1;
    }

    /**
     * @param consumption1 The consumption1
     */
    public void setConsumption1(String consumption1) {
        this.consumption1 = consumption1;
    }

    /**
     * @return The average
     */
    public String getAverage() {
        return average;
    }

    /**
     * @param average The average
     */
    public void setAverage(String average) {
        this.average = average;
    }

    /**
     * @return The average1
     */
    public String getAverage1() {
        return average1;
    }

    /**
     * @param average1 The average1
     */
    public void setAverage1(String average1) {
        this.average1 = average1;
    }

    /**
     * @return The billing
     */
    public String getBilling() {
        return billing;
    }

    /**
     * @param billing The billing
     */
    public void setBilling(String billing) {
        this.billing = billing;
    }

    /**
     * @return The billing1
     */
    public String getBilling1() {
        return billing1;
    }

    /**
     * @param billing1 The billing1
     */
    public void setBilling1(String billing1) {
        this.billing1 = billing1;
    }

}
