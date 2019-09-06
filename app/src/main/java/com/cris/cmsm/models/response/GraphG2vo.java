package com.cris.cmsm.models.response;

/**
 *
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GraphG2vo {

    @SerializedName("bilValue")
    @Expose
    private String bilValue;
    @SerializedName("month")
    @Expose
    private String month;

    /**
     *
     * @return
     * The bilValue
     */
    public String getBilValue() {
        return bilValue;
    }

    /**
     *
     * @param bilValue
     * The bilValue
     */
    public void setBilValue(String bilValue) {
        this.bilValue = bilValue;
    }

    /**
     *
     * @return
     * The month
     */
    public String getMonth() {
        return month;
    }

    /**
     *
     * @param month
     * The month
     */
    public void setMonth(String month) {
        this.month = month;
    }

}
