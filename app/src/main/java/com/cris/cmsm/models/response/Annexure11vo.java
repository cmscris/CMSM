package com.cris.cmsm.models.response;

/**
 *
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Annexure11vo {

    @SerializedName("finyear")
    @Expose
    private String finyear;
    @SerializedName("consumption")
    @Expose
    private String consumption;
    @SerializedName("billing")
    @Expose
    private String billing;
    @SerializedName("average")
    @Expose
    private String average;
    @SerializedName("lowpf")
    @Expose
    private String lowpf;
    @SerializedName("excessSMD")
    @Expose
    private String excessSMD;
    @SerializedName("excessConsumption")
    @Expose
    private String excessConsumption;
    @SerializedName("excessFCA")
    @Expose
    private String excessFCA;
    @SerializedName("delayedPayment")
    @Expose
    private String delayedPayment;
    @SerializedName("surcharge")
    @Expose
    private String surcharge;
    @SerializedName("percentage")
    @Expose
    private String percentage;

    /**
     *
     * @return
     * The finyear
     */
    public String getFinyear() {
        return finyear;
    }

    /**
     *
     * @param finyear
     * The finyear
     */
    public void setFinyear(String finyear) {
        this.finyear = finyear;
    }

    /**
     *
     * @return
     * The consumption
     */
    public String getConsumption() {
        return consumption;
    }

    /**
     *
     * @param consumption
     * The consumption
     */
    public void setConsumption(String consumption) {
        this.consumption = consumption;
    }

    /**
     *
     * @return
     * The billing
     */
    public String getBilling() {
        return billing;
    }

    /**
     *
     * @param billing
     * The billing
     */
    public void setBilling(String billing) {
        this.billing = billing;
    }

    /**
     *
     * @return
     * The average
     */
    public String getAverage() {
        return average;
    }

    /**
     *
     * @param average
     * The average
     */
    public void setAverage(String average) {
        this.average = average;
    }

    /**
     *
     * @return
     * The lowpf
     */
    public String getLowpf() {
        return lowpf;
    }

    /**
     *
     * @param lowpf
     * The lowpf
     */
    public void setLowpf(String lowpf) {
        this.lowpf = lowpf;
    }

    /**
     *
     * @return
     * The excessSMD
     */
    public String getExcessSMD() {
        return excessSMD;
    }

    /**
     *
     * @param excessSMD
     * The excessSMD
     */
    public void setExcessSMD(String excessSMD) {
        this.excessSMD = excessSMD;
    }

    /**
     *
     * @return
     * The excessConsumption
     */
    public String getExcessConsumption() {
        return excessConsumption;
    }

    /**
     *
     * @param excessConsumption
     * The excessConsumption
     */
    public void setExcessConsumption(String excessConsumption) {
        this.excessConsumption = excessConsumption;
    }

    /**
     *
     * @return
     * The excessFCA
     */
    public String getExcessFCA() {
        return excessFCA;
    }

    /**
     *
     * @param excessFCA
     * The excessFCA
     */
    public void setExcessFCA(String excessFCA) {
        this.excessFCA = excessFCA;
    }

    /**
     *
     * @return
     * The delayedPayment
     */
    public String getDelayedPayment() {
        return delayedPayment;
    }

    /**
     *
     * @param delayedPayment
     * The delayedPayment
     */
    public void setDelayedPayment(String delayedPayment) {
        this.delayedPayment = delayedPayment;
    }

    /**
     *
     * @return
     * The surcharge
     */
    public String getSurcharge() {
        return surcharge;
    }

    /**
     *
     * @param surcharge
     * The surcharge
     */
    public void setSurcharge(String surcharge) {
        this.surcharge = surcharge;
    }

    /**
     *
     * @return
     * The percentage
     */
    public String getPercentage() {
        return percentage;
    }

    /**
     *
     * @param percentage
     * The percentage
     */
    public void setPercentage(String percentage) {
        this.percentage = percentage;
    }

}