package com.cris.cmsm.models.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 *
 */

public class ResMonthlyCons {
    @SerializedName("isSuccess")
    @Expose
    private Boolean isSuccess;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("graphG1vos")
    @Expose
    private List<GraphG1vo> graphG1vos = null;

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
     * @return The graphG1vos
     */
    public List<GraphG1vo> getGraphG1vos() {
        return graphG1vos;
    }

    /**
     * @param graphG1vos The graphG1vos
     */
    public void setGraphG1vos(List<GraphG1vo> graphG1vos) {
        this.graphG1vos = graphG1vos;
    }
}
