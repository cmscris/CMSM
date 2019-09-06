package com.cris.cmsm.models.response;

/**
 *
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ResAnnexure14B {

    @SerializedName("isSuccess")
    @Expose
    private Boolean isSuccess;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("annexure14bvos")
    @Expose
    private List<Annexure14bvo> annexure14bvos = new ArrayList<Annexure14bvo>();
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
     * The annexure14bvos
     */
    public List<Annexure14bvo> getAnnexure14bvos() {
        return annexure14bvos;
    }

    /**
     *
     * @param annexure14bvos
     * The annexure14bvos
     */
    public void setAnnexure14bvos(List<Annexure14bvo> annexure14bvos) {
        this.annexure14bvos = annexure14bvos;
    }

}