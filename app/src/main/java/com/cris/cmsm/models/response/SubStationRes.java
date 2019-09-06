package com.cris.cmsm.models.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 *
 */

public class SubStationRes {

    @SerializedName("substationResponseVO")
    @Expose
    private List<SubstationResponseVO> substationResponseVO = null;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("ssFeedersVOs")
    @Expose
    private List<SsFeedersVO> ssFeedersVOs = null;
    @SerializedName("transformersVOs")
    @Expose
    private List<TransformersVO> transformersVOs = null;
    @SerializedName("cbvos")
    @Expose
    private List<Cbvo> cbvos = null;

    public List<SubstationResponseVO> getSubstationResponseVO() {
        return substationResponseVO;
    }

    public void setSubstationResponseVO(List<SubstationResponseVO> substationResponseVO) {
        this.substationResponseVO = substationResponseVO;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<SsFeedersVO> getSsFeedersVOs() {
        return ssFeedersVOs;
    }

    public void setSsFeedersVOs(List<SsFeedersVO> ssFeedersVOs) {
        this.ssFeedersVOs = ssFeedersVOs;
    }

    public List<TransformersVO> getTransformersVOs() {
        return transformersVOs;
    }

    public void setTransformersVOs(List<TransformersVO> transformersVOs) {
        this.transformersVOs = transformersVOs;
    }

    public List<Cbvo> getCbvos() {
        return cbvos;
    }

    public void setCbvos(List<Cbvo> cbvos) {
        this.cbvos = cbvos;
    }
}
