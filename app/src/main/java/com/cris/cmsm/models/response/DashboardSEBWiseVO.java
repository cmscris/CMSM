package com.cris.cmsm.models.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 *
 */
public class DashboardSEBWiseVO {

    @SerializedName("consumption")
    @Expose
    private String consumption;
    @SerializedName("seb")
    @Expose
    private String seb;

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
     * @return The seb
     */
    public String getSeb() {
        return seb;
    }

    /**
     * @param seb The seb
     */
    public void setSeb(String seb) {
        this.seb = seb;
    }
}
