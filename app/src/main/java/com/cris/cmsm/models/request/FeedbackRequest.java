package com.cris.cmsm.models.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 *
 */
public class FeedbackRequest {

    @SerializedName("loginId")
    @Expose
    private String loginId;
    @SerializedName("form")
    @Expose
    private String form;
    @SerializedName("feedback")
    @Expose
    private String feedback;

    /**
     * @return The loginId
     */
    public String getLoginId() {
        return loginId;
    }

    /**
     * @param loginId The loginId
     */
    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    /**
     * @return The form
     */
    public String getForm() {
        return form;
    }

    /**
     * @param form The form
     */
    public void setForm(String form) {
        this.form = form;
    }

    /**
     * @return The feedback
     */
    public String getFeedback() {
        return feedback;
    }

    /**
     * @param feedback The feedback
     */
    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
}
