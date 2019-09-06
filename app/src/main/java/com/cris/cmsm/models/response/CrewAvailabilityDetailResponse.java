package com.cris.cmsm.models.response;

import com.cris.cmsm.models.KeyValue;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CrewAvailabilityDetailResponse {

    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("crewAvailabilityDetailVO")
    @Expose
    private List<CrewAvailabilityDetailVO> crewAvailabilityDetailVO;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<CrewAvailabilityDetailVO> getCrewAvailabilityDetailVO() {
        return crewAvailabilityDetailVO;
    }

    public void setCrewAvailabilityDetailVO(List<CrewAvailabilityDetailVO> crewAvailabilityDetailVO) {
        this.crewAvailabilityDetailVO = crewAvailabilityDetailVO;
    }
}
