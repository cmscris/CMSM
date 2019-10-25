package com.cris.cmsm.models.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Limovementresponse {
    @SerializedName("liMovementVOsResponse")
    @Expose
    private List <LiMovementVOsResponse> liMovementVOsResponse = null;

    public List<LiMovementVOsResponse> getLiMovementVOsResponse() {
        return liMovementVOsResponse;
    }

    public void setLiMovementVOsResponse(List<LiMovementVOsResponse> liMovementVOsResponse) {
        this.liMovementVOsResponse = liMovementVOsResponse;
    }


}
