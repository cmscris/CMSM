package com.cris.cmsm.models.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 *
 */
public class DashboardTotalVO {
    @SerializedName("consumption")
    @Expose
    private String consumption;
    @SerializedName("sname")
    @Expose
    private String sname;

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
     * @return The sname
     */
    public String getSname() {
        return sname;
    }

    /**
     * @param sname The sname
     */
    public void setSname(String sname) {
        this.sname = sname;
    }
}
