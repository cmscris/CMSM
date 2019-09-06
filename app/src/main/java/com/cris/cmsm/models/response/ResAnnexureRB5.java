package com.cris.cmsm.models.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 *
 */

public class ResAnnexureRB5 {

    @SerializedName("isSuccess")
    @Expose
    private Boolean isSuccess;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("annexureRB5vos")
    @Expose
    private List<AnnexureRB5vo> annexureRB5vos = null;

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
     * @return The annexureRB5vos
     */
    public List<AnnexureRB5vo> getAnnexureRB5vos() {
        return annexureRB5vos;
    }

    /**
     * @param annexureRB5vos The annexureRB5vos
     */
    public void setAnnexureRB5vos(List<AnnexureRB5vo> annexureRB5vos) {
        this.annexureRB5vos = annexureRB5vos;
    }

}
