package com.cris.cmsm.models.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 *
 */

public class ResAnnexureRB2 {


    @SerializedName("appsummary")
    @Expose
    private String appsummary;
    @SerializedName("nAppsummary")
    @Expose
    private String nAppsummary;
    @SerializedName("isSuccess")
    @Expose
    private Boolean isSuccess;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("annexureRB2vos")
    @Expose
    private List<AnnexureRB2vo> annexureRB2vos = null;

    /**
     * @return The appsummary
     */
    public String getAppsummary() {
        return appsummary;
    }

    /**
     * @param appsummary The appsummary
     */
    public void setAppsummary(String appsummary) {
        this.appsummary = appsummary;
    }

    /**
     * @return The nAppsummary
     */
    public String getNAppsummary() {
        return nAppsummary;
    }

    /**
     * @param nAppsummary The nAppsummary
     */
    public void setNAppsummary(String nAppsummary) {
        this.nAppsummary = nAppsummary;
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
     * @return The annexureRB2vos
     */
    public List<AnnexureRB2vo> getAnnexureRB2vos() {
        return annexureRB2vos;
    }

    /**
     * @param annexureRB2vos The annexureRB2vos
     */
    public void setAnnexureRB2vos(List<AnnexureRB2vo> annexureRB2vos) {
        this.annexureRB2vos = annexureRB2vos;
    }
}
