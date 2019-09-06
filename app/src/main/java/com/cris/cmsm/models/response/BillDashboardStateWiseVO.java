package com.cris.cmsm.models.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 *
 */
public class BillDashboardStateWiseVO {

    @SerializedName("billing")
    @Expose
    private String billing;
    @SerializedName("state")
    @Expose
    private String state;

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
     * @return The state
     */
    public String getState() {
        return state;
    }

    /**
     * @param state The state
     */
    public void setState(String state) {
        this.state = state;
    }
}
