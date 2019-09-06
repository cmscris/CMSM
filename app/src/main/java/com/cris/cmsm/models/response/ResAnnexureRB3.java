package com.cris.cmsm.models.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 *
 */

public class ResAnnexureRB3 {

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
    @SerializedName("annexureRB3vos")
    @Expose
    private List<AnnexureRB3vo> annexureRB3vos = null;

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
     * @return The annexureRB3vos
     */
    public List<AnnexureRB3vo> getAnnexureRB3vos() {
        return annexureRB3vos;
    }

    /**
     * @param annexureRB3vos The annexureRB3vos
     */
    public void setAnnexureRB3vos(List<AnnexureRB3vo> annexureRB3vos) {
        this.annexureRB3vos = annexureRB3vos;
    }
}
