package com.cris.cmsm.models.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 *
 */
public class SubStationResponse {

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("isSuccess")
    @Expose
    private Boolean isSuccess;
    @SerializedName("substationInfos")
    @Expose
    private SubstationInfos substationInfos;
    @SerializedName("consumedEnergy")
    @Expose
    private ConsumedEnergy consumedEnergy;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(Boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public SubstationInfos getSubstationInfos() {
        return substationInfos;
    }

    public void setSubstationInfos(SubstationInfos substationInfos) {
        this.substationInfos = substationInfos;
    }

    public ConsumedEnergy getConsumedEnergy() {
        return consumedEnergy;
    }

    public void setConsumedEnergy(ConsumedEnergy consumedEnergy) {
        this.consumedEnergy = consumedEnergy;
    }
}
