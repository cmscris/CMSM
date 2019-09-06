package com.cris.cmsm.models.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 *
 */

public class GraphG1vo {

    @SerializedName("conValue")
    @Expose
    private String conValue;
    @SerializedName("month")
    @Expose
    private String month;

    /**
     * @return The conValue
     */
    public String getConValue() {
        return conValue;
    }

    /**
     * @param conValue The conValue
     */
    public void setConValue(String conValue) {
        this.conValue = conValue;
    }

    /**
     * @return The month
     */
    public String getMonth() {
        return month;
    }

    /**
     * @param month The month
     */
    public void setMonth(String month) {
        this.month = month;
    }

}
