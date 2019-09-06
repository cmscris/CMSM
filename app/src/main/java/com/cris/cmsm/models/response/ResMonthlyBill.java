package com.cris.cmsm.models.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 *
 */

public class ResMonthlyBill {

    @SerializedName("isSuccess")
    @Expose
    private Boolean isSuccess;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("graphG2vos")
    @Expose
    private List<GraphG2vo> graphG2vos = null;

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
     * @return The graphG2vos
     */
    public List<GraphG2vo> getGraphG2vos() {
        return graphG2vos;
    }

    /**
     * @param graphG2vos The graphG2vos
     */
    public void setGraphG2vos(List<GraphG2vo> graphG2vos) {
        this.graphG2vos = graphG2vos;
    }
}
