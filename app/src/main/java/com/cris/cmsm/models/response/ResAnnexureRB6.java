package com.cris.cmsm.models.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 *
 */

public class ResAnnexureRB6 {

    @SerializedName("isSuccess")
    @Expose
    private Boolean isSuccess;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("annexureRB6vos")
    @Expose
    private List<AnnexureRB6vo> annexureRB6vos = null;

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
     * @return The annexureRB6vos
     */
    public List<AnnexureRB6vo> getAnnexureRB6vos() {
        return annexureRB6vos;
    }

    /**
     * @param annexureRB6vos The annexureRB6vos
     */
    public void setAnnexureRB6vos(List<AnnexureRB6vo> annexureRB6vos) {
        this.annexureRB6vos = annexureRB6vos;
    }
}
