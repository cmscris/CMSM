package com.cris.cmsm.models.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 *
 */
public class SubStation {

    @SerializedName("substationId")
    @Expose
    private String substationId;
    @SerializedName("substationName")
    @Expose
    private String substationName;

    /**
     * @return The substationId
     */
    public String getSubstationId() {
        return substationId;
    }

    /**
     * @param substationId The substationId
     */
    public void setSubstationId(String substationId) {
        this.substationId = substationId;
    }

    /**
     * @return The substationName
     */
    public String getSubstationName() {
        return substationName;
    }

    /**
     * @param substationName The substationName
     */
    public void setSubstationName(String substationName) {
        this.substationName = substationName;
    }
}
