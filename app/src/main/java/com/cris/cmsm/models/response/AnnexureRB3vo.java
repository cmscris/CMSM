package com.cris.cmsm.models.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 *
 */

public class AnnexureRB3vo {

    @SerializedName("finyear")
    @Expose
    private String finyear;
    @SerializedName("consumptionTrd")
    @Expose
    private String consumptionTrd;
    @SerializedName("consumptionGen")
    @Expose
    private String consumptionGen;
    @SerializedName("consumptionTotal")
    @Expose
    private String consumptionTotal;
    @SerializedName("billingTrd")
    @Expose
    private String billingTrd;
    @SerializedName("billingGen")
    @Expose
    private String billingGen;
    @SerializedName("billingTotal")
    @Expose
    private String billingTotal;
    @SerializedName("averageTrd")
    @Expose
    private String averageTrd;
    @SerializedName("averageGen")
    @Expose
    private String averageGen;

    /**
     * @return The finyear
     */
    public String getFinyear() {
        return finyear;
    }

    /**
     * @param finyear The finyear
     */
    public void setFinyear(String finyear) {
        this.finyear = finyear;
    }

    /**
     * @return The consumptionTrd
     */
    public String getConsumptionTrd() {
        return consumptionTrd;
    }

    /**
     * @param consumptionTrd The consumptionTrd
     */
    public void setConsumptionTrd(String consumptionTrd) {
        this.consumptionTrd = consumptionTrd;
    }

    /**
     * @return The consumptionGen
     */
    public String getConsumptionGen() {
        return consumptionGen;
    }

    /**
     * @param consumptionGen The consumptionGen
     */
    public void setConsumptionGen(String consumptionGen) {
        this.consumptionGen = consumptionGen;
    }

    /**
     * @return The consumptionTotal
     */
    public String getConsumptionTotal() {
        return consumptionTotal;
    }

    /**
     * @param consumptionTotal The consumptionTotal
     */
    public void setConsumptionTotal(String consumptionTotal) {
        this.consumptionTotal = consumptionTotal;
    }

    /**
     * @return The billingTrd
     */
    public String getBillingTrd() {
        return billingTrd;
    }

    /**
     * @param billingTrd The billingTrd
     */
    public void setBillingTrd(String billingTrd) {
        this.billingTrd = billingTrd;
    }

    /**
     * @return The billingGen
     */
    public String getBillingGen() {
        return billingGen;
    }

    /**
     * @param billingGen The billingGen
     */
    public void setBillingGen(String billingGen) {
        this.billingGen = billingGen;
    }

    /**
     * @return The billingTotal
     */
    public String getBillingTotal() {
        return billingTotal;
    }

    /**
     * @param billingTotal The billingTotal
     */
    public void setBillingTotal(String billingTotal) {
        this.billingTotal = billingTotal;
    }

    /**
     * @return The averageTrd
     */
    public String getAverageTrd() {
        return averageTrd;
    }

    /**
     * @param averageTrd The averageTrd
     */
    public void setAverageTrd(String averageTrd) {
        this.averageTrd = averageTrd;
    }

    /**
     * @return The averageGen
     */
    public String getAverageGen() {
        return averageGen;
    }

    /**
     * @param averageGen The averageGen
     */
    public void setAverageGen(String averageGen) {
        this.averageGen = averageGen;
    }

    public String getOverAll() {
        return overAll;
    }

    public void setOverAll(String overAll) {
        this.overAll = overAll;
    }

    String overAll = "";
}
