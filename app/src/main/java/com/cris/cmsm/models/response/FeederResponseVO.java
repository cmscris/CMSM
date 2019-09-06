package com.cris.cmsm.models.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 *
 */

public class FeederResponseVO {


    @SerializedName("inComingFeederVOs")
    @Expose
    private List<InComingFeederVO> inComingFeederVOs = null;
    @SerializedName("outGoingFeederVos")
    @Expose
    private List<OutGoingFeederVo> outGoingFeederVos = null;

    public List<InComingFeederVO> getInComingFeederVOs() {
        return inComingFeederVOs;
    }

    public void setInComingFeederVOs(List<InComingFeederVO> inComingFeederVOs) {
        this.inComingFeederVOs = inComingFeederVOs;
    }

    public List<OutGoingFeederVo> getOutGoingFeederVos() {
        return outGoingFeederVos;
    }

    public void setOutGoingFeederVos(List<OutGoingFeederVo> outGoingFeederVos) {
        this.outGoingFeederVos = outGoingFeederVos;
    }
}
