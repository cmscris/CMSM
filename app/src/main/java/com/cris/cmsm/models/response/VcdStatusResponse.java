package com.cris.cmsm.models.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by cms on 3/16/18.
 */


public class VcdStatusResponse {

    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("vcdStatusResponseVO")
    @Expose
    private List<VcdStatusResponseVO> vcdStatusResponseVO = null;




    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public List<VcdStatusResponseVO> getVcdStatusResponseVO() {
        return vcdStatusResponseVO;
    }

    public void setVcdStatusResponseVO(List<VcdStatusResponseVO> vcdStatusResponseVO) {
        this.vcdStatusResponseVO = vcdStatusResponseVO;
    }
}
