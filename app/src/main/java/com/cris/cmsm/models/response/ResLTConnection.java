package com.cris.cmsm.models.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 *
 */

public class ResLTConnection {

    @SerializedName("isSuccess")
    @Expose
    private Boolean isSuccess;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("ltConnectionVOs")
    @Expose
    private List<LtConnectionVO> ltConnectionVOs = null;

    public Boolean getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(Boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<LtConnectionVO> getLtConnectionVOs() {
        return ltConnectionVOs;
    }

    public void setLtConnectionVOs(List<LtConnectionVO> ltConnectionVOs) {
        this.ltConnectionVOs = ltConnectionVOs;
    }

}
