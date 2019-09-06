package com.cris.cmsm.models.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 *
 */
public class BillDashboardTotalVO {
    @SerializedName("biliing")
    @Expose
    private String biliing;
    @SerializedName("sname")
    @Expose
    private String sname;

    /**
     * @return The biliing
     */
    public String getBiliing() {
        return biliing;
    }

    /**
     * @param biliing The biliing
     */
    public void setBiliing(String biliing) {
        this.biliing = biliing;
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
