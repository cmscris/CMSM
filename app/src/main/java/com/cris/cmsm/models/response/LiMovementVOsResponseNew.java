package com.cris.cmsm.models.response;

import com.cris.cmsm.models.request.LiMovementRequest;

import java.util.List;



public class LiMovementVOsResponseNew {
    private String message;
    private List<LiMovementdetail> liMovementVOsResponse;
    private List<LiMovementRequest> liMovementDetailsResponse;

    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public List<LiMovementdetail> getLiMovementVOsResponse() {
        return liMovementVOsResponse;
    }
    public void setLiMovementVOsResponse(List<LiMovementdetail> vo_list) {
        this.liMovementVOsResponse = vo_list;
    }
    public List<LiMovementRequest> getLiMovementDetailsResponse() {
        return liMovementDetailsResponse;
    }
    public void setLiMovementDetailsResponse(List<LiMovementRequest> liMovementDetailsResponse) {
        this.liMovementDetailsResponse = liMovementDetailsResponse;
    }

}
