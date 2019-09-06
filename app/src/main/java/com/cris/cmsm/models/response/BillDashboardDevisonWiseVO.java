package com.cris.cmsm.models.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 *
 */
public class BillDashboardDevisonWiseVO {
    @SerializedName("billing")
    @Expose
    private String billing;
    @SerializedName("division")
    @Expose
    private String division;

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
     * The division
     */
    public String getDivision() {
        return division;
    }

    /**
     *
     * @param division
     * The division
     */
    public void setDivision(String division) {
        this.division = division;
    }
}
