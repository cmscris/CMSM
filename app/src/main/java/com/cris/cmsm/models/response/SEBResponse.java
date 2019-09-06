package com.cris.cmsm.models.response;

/**
 *
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class SEBResponse {

    @SerializedName("sebMasters")
    @Expose
    private List<SebMaster> sebMasters = new ArrayList<SebMaster>();
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("isSuccess")
    @Expose
    private Boolean isSuccess;

    /**
     * @return The sebMasters
     */
    public List<SebMaster> getSebMasters() {
        return sebMasters;
    }

    /**
     * @param sebMasters The sebMasters
     */
    public void setSebMasters(List<SebMaster> sebMasters) {
        this.sebMasters = sebMasters;
    }

    /**
     * @return The message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message The message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @return The isSuccess
     */
    public Boolean getIsSuccess() {
        return isSuccess;
    }

    /**
     * @param isSuccess The isSuccess
     */
    public void setIsSuccess(Boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

}