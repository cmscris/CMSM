package com.cris.cmsm.models.response;

/**
 *
 */
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Annexure14bvo {

    @SerializedName("railCode")
    @Expose
    private String railCode;
    @SerializedName("divCode")
    @Expose
    private String divCode;
    @SerializedName("sebName")
    @Expose
    private String sebName;
    @SerializedName("consumption")
    @Expose
    private String consumption;
    @SerializedName("billing")
    @Expose
    private String billing;
    @SerializedName("average")
    @Expose
    private String average;
    @SerializedName("date")
    @Expose
    private String date;

    /**
     *
     * @return
     * The railCode
     */
    public String getRailCode() {
        return railCode;
    }

    /**
     *
     * @param railCode
     * The railCode
     */
    public void setRailCode(String railCode) {
        this.railCode = railCode;
    }

    /**
     *
     * @return
     * The divCode
     */
    public String getDivCode() {
        return divCode;
    }

    /**
     *
     * @param divCode
     * The divCode
     */
    public void setDivCode(String divCode) {
        this.divCode = divCode;
    }

    /**
     *
     * @return
     * The sebName
     */
    public String getSebName() {
        return sebName;
    }

    /**
     *
     * @param sebName
     * The sebName
     */
    public void setSebName(String sebName) {
        this.sebName = sebName;
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
     * The date
     */
    public String getDate() {
        return date;
    }

    /**
     *
     * @param date
     * The date
     */
    public void setDate(String date) {
        this.date = date;
    }

}
