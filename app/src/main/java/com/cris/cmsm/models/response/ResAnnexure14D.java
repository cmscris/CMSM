package com.cris.cmsm.models.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 *
 */

public class ResAnnexure14D {
    @SerializedName("summary")
    @Expose
    private String summary;
    @SerializedName("isSuccess")
    @Expose
    private Boolean isSuccess;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("annexure14dvos")
    @Expose
    private List<Annexure14dvo> annexure14dvos = null;

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
     * @return The annexure14dvos
     */
    public List<Annexure14dvo> getAnnexure14dvos() {
        return annexure14dvos;
    }

    /**
     * @param annexure14dvos The annexure14dvos
     */
    public void setAnnexure14dvos(List<Annexure14dvo> annexure14dvos) {
        this.annexure14dvos = annexure14dvos;
    }

}
