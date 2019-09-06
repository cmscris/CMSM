package com.cris.cmsm.models.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by cms on 4/11/18.
 */

public class LICrewMonitoredResponse {
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("liCrewMonitoredResponseVO")
    @Expose
    private List<LICrewMonitoredResponseVO> liCrewMonitoredResponseVO = null;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public List<LICrewMonitoredResponseVO> getLiCrewMonitoredResponseVO() {
        return liCrewMonitoredResponseVO;
    }

    public void setLiCrewMonitoredResponseVO(List<LICrewMonitoredResponseVO> liCrewMonitoredResponseVO) {
        this.liCrewMonitoredResponseVO = liCrewMonitoredResponseVO;
    }
}
