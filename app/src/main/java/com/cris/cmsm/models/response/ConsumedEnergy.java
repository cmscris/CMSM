package com.cris.cmsm.models.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 *
 */
public class ConsumedEnergy {
    @SerializedName("prevYearConsumedEnergy")
    @Expose
    private String prevYearConsumedEnergy;

    public String getPrevYearConsumedEnergy() {
        return prevYearConsumedEnergy;
    }

    public void setPrevYearConsumedEnergy(String prevYearConsumedEnergy) {
        this.prevYearConsumedEnergy = prevYearConsumedEnergy;
    }
}
