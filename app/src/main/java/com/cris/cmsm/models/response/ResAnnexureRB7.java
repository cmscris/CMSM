package com.cris.cmsm.models.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 *
 */

public class ResAnnexureRB7 {

    @SerializedName("isSuccess")
    @Expose
    private Boolean isSuccess;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("annexureRB7vos")
    @Expose
    private List<AnnexureRB7vo> annexureRB7vos = null;

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
     * @return The annexureRB7vos
     */
    public List<AnnexureRB7vo> getAnnexureRB7vos() {
        return annexureRB7vos;
    }

    /**
     * @param annexureRB7vos The annexureRB7vos
     */
    public void setAnnexureRB7vos(List<AnnexureRB7vo> annexureRB7vos) {
        this.annexureRB7vos = annexureRB7vos;
    }
}

