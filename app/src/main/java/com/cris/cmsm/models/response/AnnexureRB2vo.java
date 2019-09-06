package com.cris.cmsm.models.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 *
 */

public class AnnexureRB2vo {

    @SerializedName("rlycode")
    @Expose
    private String rlycode;
    @SerializedName("pconsumption")
    @Expose
    private String pconsumption;
    @SerializedName("pbilling")
    @Expose
    private String pbilling;
    @SerializedName("paverage")
    @Expose
    private String paverage;
    @SerializedName("consumption")
    @Expose
    private String consumption;
    @SerializedName("billing")
    @Expose
    private String billing;
    @SerializedName("average")
    @Expose
    private String average;
    @SerializedName("inccon")
    @Expose
    private String inccon;
    @SerializedName("inccost")
    @Expose
    private String inccost;
    @SerializedName("incrate")
    @Expose
    private String incrate;

    /**
     * @return The rlycode
     */
    public String getRlycode() {
        return rlycode;
    }

    /**
     * @param rlycode The rlycode
     */
    public void setRlycode(String rlycode) {
        this.rlycode = rlycode;
    }

    /**
     * @return The pconsumption
     */
    public String getPconsumption() {
        return pconsumption;
    }

    /**
     * @param pconsumption The pconsumption
     */
    public void setPconsumption(String pconsumption) {
        this.pconsumption = pconsumption;
    }

    /**
     * @return The pbilling
     */
    public String getPbilling() {
        return pbilling;
    }

    /**
     * @param pbilling The pbilling
     */
    public void setPbilling(String pbilling) {
        this.pbilling = pbilling;
    }

    /**
     * @return The paverage
     */
    public String getPaverage() {
        return paverage;
    }

    /**
     * @param paverage The paverage
     */
    public void setPaverage(String paverage) {
        this.paverage = paverage;
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
     * @return The inccon
     */
    public String getInccon() {
        return inccon;
    }

    /**
     * @param inccon The inccon
     */
    public void setInccon(String inccon) {
        this.inccon = inccon;
    }

    /**
     * @return The inccost
     */
    public String getInccost() {
        return inccost;
    }

    /**
     * @param inccost The inccost
     */
    public void setInccost(String inccost) {
        this.inccost = inccost;
    }

    /**
     * @return The incrate
     */
    public String getIncrate() {
        return incrate;
    }

    /**
     * @param incrate The incrate
     */
    public void setIncrate(String incrate) {
        this.incrate = incrate;
    }

}
