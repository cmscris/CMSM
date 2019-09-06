package com.cris.cmsm.models.response;

/**
 *
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;


public class ResAnnexure6 {

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
    @SerializedName("annexureReports")
    @Expose
    private List<Annexure6Report> annexureReports = new ArrayList<Annexure6Report>();

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
     * @return The annexureReports
     */
    public List<Annexure6Report> getAnnexureReports() {
        return annexureReports;
    }

    /**
     * @param annexureReports The annexureReports
     */
    public void setAnnexureReports(List<Annexure6Report> annexureReports) {
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
