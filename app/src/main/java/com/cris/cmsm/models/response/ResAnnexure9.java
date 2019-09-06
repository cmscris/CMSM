package com.cris.cmsm.models.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 *
 */

public class ResAnnexure9 {

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
    @SerializedName("annexure9vos")
    @Expose
    private List<Annexure9vo> annexure9vos = null;

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
     * @return The annexure9vos
     */
    public List<Annexure9vo> getAnnexure9vos() {
        return annexure9vos;
    }

    /**
     * @param annexure9vos The annexure9vos
     */
    public void setAnnexure9vos(List<Annexure9vo> annexure9vos) {
        this.annexure9vos = annexure9vos;
    }

}
