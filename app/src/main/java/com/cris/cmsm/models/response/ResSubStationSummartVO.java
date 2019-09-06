package com.cris.cmsm.models.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 *
 */

public class ResSubStationSummartVO {

    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("subStationAsssetSummaryVOs")
    @Expose
    private List<SubStationAsssetSummaryVO> subStationAsssetSummaryVOs = null;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<SubStationAsssetSummaryVO> getSubStationAsssetSummaryVOs() {
        return subStationAsssetSummaryVOs;
    }

    public void setSubStationAsssetSummaryVOs(List<SubStationAsssetSummaryVO> subStationAsssetSummaryVOs) {
        this.subStationAsssetSummaryVOs = subStationAsssetSummaryVOs;
    }

}
