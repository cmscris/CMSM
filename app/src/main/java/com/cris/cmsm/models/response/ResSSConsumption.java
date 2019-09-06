package com.cris.cmsm.models.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 *
 */

public class ResSSConsumption {
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("substationResponseVO")
    @Expose
    private List<SubstationResponseVO> substationResponseVO = null;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<SubstationResponseVO> getSubstationResponseVO() {
        return substationResponseVO;
    }

    public void setSubstationResponseVO(List<SubstationResponseVO> substationResponseVO) {
        this.substationResponseVO = substationResponseVO;
    }
}
