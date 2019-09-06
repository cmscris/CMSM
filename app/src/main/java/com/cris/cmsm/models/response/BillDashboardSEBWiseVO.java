package com.cris.cmsm.models.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 *
 */
public class BillDashboardSEBWiseVO {
    @SerializedName("billing")
    @Expose
    private String billing;
    @SerializedName("seb")
    @Expose
    private String seb;

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
     * The seb
     */
    public String getSeb() {
        return seb;
    }

    /**
     *
     * @param seb
     * The seb
     */
    public void setSeb(String seb) {
        this.seb = seb;
    }
}
