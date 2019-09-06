package com.cris.cmsm.models.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class MasterData {

    @SerializedName("railwayVOs")
    @Expose
    private List<RailwayVO> railwayVOs = new ArrayList<RailwayVO>();
    @SerializedName("divisionVOs")
    @Expose
    private List<DivisionVO> divisionVOs = new ArrayList<DivisionVO>();

    /**
     * @return The railwayVOs
     */
    public List<RailwayVO> getRailwayVOs() {
        return railwayVOs;
    }

    /**
     * @param railwayVOs The railwayVOs
     */
    public void setRailwayVOs(List<RailwayVO> railwayVOs) {
        this.railwayVOs = railwayVOs;
    }

    /**
     * @return The divisionVOs
     */
    public List<DivisionVO> getDivisionVOs() {
        return divisionVOs;
    }

    /**
     * @param divisionVOs The divisionVOs
     */
    public void setDivisionVOs(List<DivisionVO> divisionVOs) {
        this.divisionVOs = divisionVOs;
    }

}
