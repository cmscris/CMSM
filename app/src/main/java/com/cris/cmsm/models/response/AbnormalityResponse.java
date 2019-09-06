package com.cris.cmsm.models.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by cms on 3/16/18.
 */


public class AbnormalityResponse {

    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("abnormalityResponseVO")
    @Expose
    private List<AbnormalityResponseVO> abnormalityResponseVO = null;




    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public List<AbnormalityResponseVO> getAbnormalityResponseVO() {
        return abnormalityResponseVO;
    }

    public void setAbnormalityResponseVO(List<AbnormalityResponseVO> abnormalityResponseVO) {
        this.abnormalityResponseVO = abnormalityResponseVO;
    }
}
