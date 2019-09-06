package com.cris.cmsm.models.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 *
 */

public class Annexure14CRes {

    @SerializedName("summary")
    @Expose
    private String summary;
    @SerializedName("isSuccess")
    @Expose
    private Boolean isSuccess;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("annexure14cvos")
    @Expose
    private List<Annexure14cvo> annexure14cvos = null;

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
     * @return The annexure14cvos
     */
    public List<Annexure14cvo> getAnnexure14cvos() {
        return annexure14cvos;
    }

    /**
     * @param annexure14cvos The annexure14cvos
     */
    public void setAnnexure14cvos(List<Annexure14cvo> annexure14cvos) {
        this.annexure14cvos = annexure14cvos;
    }

}
