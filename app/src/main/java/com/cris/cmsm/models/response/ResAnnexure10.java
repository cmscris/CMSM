package com.cris.cmsm.models.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 *
 */

public class ResAnnexure10{
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
    @SerializedName("annexure10vos")
    @Expose
    private List<Annexure10vo> annexure10vos = null;

    public String getAppsummary() {
        return appsummary;
    }

    public void setAppsummary(String appsummary) {
        this.appsummary = appsummary;
    }

    public String getNAppsummary() {
        return nAppsummary;
    }

    public void setNAppsummary(String nAppsummary) {
        this.nAppsummary = nAppsummary;
    }

    public Boolean getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(Boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Annexure10vo> getAnnexure10vos() {
        return annexure10vos;
    }

    public void setAnnexure10vos(List<Annexure10vo> annexure10vos) {
        this.annexure10vos = annexure10vos;
    }

}
