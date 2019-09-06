package com.cris.cmsm.models.response;

/**
 *
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class RB1Response {

    @SerializedName("isSuccess")
    @Expose
    private Boolean isSuccess;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("rb1vos")
    @Expose
    private List<Rb1vo> rb1vos = new ArrayList<Rb1vo>();

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
     * @return The rb1vos
     */
    public List<Rb1vo> getRb1vos() {
        return rb1vos;
    }

    /**
     * @param rb1vos The rb1vos
     */
    public void setRb1vos(List<Rb1vo> rb1vos) {
        this.rb1vos = rb1vos;
    }

}
