package com.cris.cmsm.models.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 *
 */

public class ResAnnexureRB4 {

    @SerializedName("isSuccess")
    @Expose
    private Boolean isSuccess;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("annexureRB4vos")
    @Expose
    private List<AnnexureRB4vo> annexureRB4vos = null;

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
     * @return The annexureRB4vos
     */
    public List<AnnexureRB4vo> getAnnexureRB4vos() {
        return annexureRB4vos;
    }

    /**
     * @param annexureRB4vos The annexureRB4vos
     */
    public void setAnnexureRB4vos(List<AnnexureRB4vo> annexureRB4vos) {
        this.annexureRB4vos = annexureRB4vos;
    }

}
