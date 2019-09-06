package com.cris.cmsm.models.response;

/**
 *
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResAnnexure5 {
    @SerializedName("isSuccess")
    @Expose
    private Boolean isSuccess;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("annexureReports")
    @Expose
    private List<AnnexureReport> annexureReports = null;
    @SerializedName("appsummary")
    @Expose
    private String appsummary;
    @SerializedName("nAppsummary")
    @Expose
    private String nAppsummary;

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

    public List<AnnexureReport> getAnnexureReports() {
        return annexureReports;
    }

    public void setAnnexureReports(List<AnnexureReport> annexureReports) {
        this.annexureReports = annexureReports;
    }

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

}
