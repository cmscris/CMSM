package com.cris.cmsm.models.response;

/**
 *
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ThreeYearData {

    @SerializedName("isSuccess")
    @Expose
    private Boolean isSuccess;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("tractionDetailVOs")
    @Expose
    private List<TractionDetailVO> tractionDetailVOs = new ArrayList<TractionDetailVO>();
    @SerializedName("generalDetailVOs")
    @Expose
    private List<GeneralDetailVO> generalDetailVOs = new ArrayList<GeneralDetailVO>();

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
     * @return The tractionDetailVOs
     */
    public List<TractionDetailVO> getTractionDetailVOs() {
        return tractionDetailVOs;
    }

    /**
     * @param tractionDetailVOs The tractionDetailVOs
     */
    public void setTractionDetailVOs(List<TractionDetailVO> tractionDetailVOs) {
        this.tractionDetailVOs = tractionDetailVOs;
    }

    /**
     * @return The generalDetailVOs
     */
    public List<GeneralDetailVO> getGeneralDetailVOs() {
        return generalDetailVOs;
    }

    /**
     * @param generalDetailVOs The generalDetailVOs
     */
    public void setGeneralDetailVOs(List<GeneralDetailVO> generalDetailVOs) {
        this.generalDetailVOs = generalDetailVOs;
    }

}