package com.cris.cmsm.models.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 *
 */

public class Annexure14cvo {

    @SerializedName("finYear")
    @Expose
    private String finYear;
    @SerializedName("consumption")
    @Expose
    private String consumption;
    @SerializedName("billing")
    @Expose
    private String billing;
    @SerializedName("average")
    @Expose
    private String average;

    /**
     * @return The finYear
     */
    public String getFinYear() {
        return finYear;
    }

    /**
     * @param finYear The finYear
     */
    public void setFinYear(String finYear) {
        this.finYear = finYear;
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
}
