package com.cris.cmsm.models.response;

/**
 *
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;


public class ResAnnexure14A {

    @SerializedName("isSuccess")
    @Expose
    private Boolean isSuccess;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("annexure14AReports")
    @Expose
    private List<Annexure14AReport> annexure14AReports = new ArrayList<Annexure14AReport>();

    @SerializedName("summary")
    @Expose
    private String summary;

    /**
     * @return The summary
     */
    public String getSummary() {
        return summary;
    }

    /**
     * @param summary The summary
     */
    public void setSummary(String summary) {
        this.summary = summary;
    }

    /**
     *
     * @return
     * The isSuccess
     */
    public Boolean getIsSuccess() {
        return isSuccess;
    }

    /**
     *
     * @param isSuccess
     * The isSuccess
     */
    public void setIsSuccess(Boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    /**
     *
     * @return
     * The message
     */
    public String getMessage() {
        return message;
    }

    /**
     *
     * @param message
     * The message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     *
     * @return
     * The annexure14AReports
     */
    public List<Annexure14AReport> getAnnexure14AReports() {
        return annexure14AReports;
    }

    /**
     *
     * @param annexure14AReports
     * The annexure14AReports
     */
    public void setAnnexure14AReports(List<Annexure14AReport> annexure14AReports) {
        this.annexure14AReports = annexure14AReports;
    }

}
