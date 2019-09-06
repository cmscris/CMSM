package com.cris.cmsm.models.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 *
 */

public class ResponseAssetDetails {
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("liftAssestVOs")
    @Expose
    private List<LiftAssestVO> liftAssestVOs = null;
    @SerializedName("escalatorsAssetVOs")
    @Expose
    private List<EscalatorsAssetVO> escalatorsAssetVOs = null;
    @SerializedName("solarPVPanelBldgVOs")
    @Expose
    private List<SolarPVPanelBldgVO> solarPVPanelBldgVOs = null;
    @SerializedName("solarPVPanelStationAssetVOs")
    @Expose
    private List<SolarPVPanelStationAssetVO> solarPVPanelStationAssetVOs = null;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<LiftAssestVO> getLiftAssestVOs() {
        return liftAssestVOs;
    }

    public void setLiftAssestVOs(List<LiftAssestVO> liftAssestVOs) {
        this.liftAssestVOs = liftAssestVOs;
    }

    public List<EscalatorsAssetVO> getEscalatorsAssetVOs() {
        return escalatorsAssetVOs;
    }

    public void setEscalatorsAssetVOs(List<EscalatorsAssetVO> escalatorsAssetVOs) {
        this.escalatorsAssetVOs = escalatorsAssetVOs;
    }

    public List<SolarPVPanelBldgVO> getSolarPVPanelBldgVOs() {
        return solarPVPanelBldgVOs;
    }

    public void setSolarPVPanelBldgVOs(List<SolarPVPanelBldgVO> solarPVPanelBldgVOs) {
        this.solarPVPanelBldgVOs = solarPVPanelBldgVOs;
    }

    public List<SolarPVPanelStationAssetVO> getSolarPVPanelStationAssetVOs() {
        return solarPVPanelStationAssetVOs;
    }

    public void setSolarPVPanelStationAssetVOs(List<SolarPVPanelStationAssetVO> solarPVPanelStationAssetVOs) {
        this.solarPVPanelStationAssetVOs = solarPVPanelStationAssetVOs;
    }

}
