package com.cris.cmsm.models.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 *
 */
public class FeedbackResponse {
    @SerializedName("flag")
    @Expose
    private Boolean flag;
    @SerializedName("message")
    @Expose
    private String message;

    /**
     * @return The flag
     */
    public Boolean getFlag() {
        return flag;
    }

    /**
     * @param flag The flag
     */
    public void setFlag(Boolean flag) {
        this.flag = flag;
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

}
