package com.cris.cmsm.models.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 *
 */

public class AssetManagementModel {

    @SerializedName("division")
    @Expose
    private String division;
    @SerializedName("railCode")
    @Expose
    private String railCode;
    @SerializedName("sseIncharge")
    @Expose
    private String sseIncharge;
    @SerializedName("subStation")
    @Expose
    private String subStation;

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public String getRailCode() {
        return railCode;
    }

    public void setRailCode(String railCode) {
        this.railCode = railCode;
    }

    public String getSseIncharge() {
        return sseIncharge;
    }

    public void setSseIncharge(String sseIncharge) {
        this.sseIncharge = sseIncharge;
    }

    public String getSubStation() {
        return subStation;
    }

    public void setSubStation(String subStation) {
        this.subStation = subStation;
    }


    public String getSubstationName() {
        return substationName;
    }

    public void setSubstationName(String substationName) {
        this.substationName = substationName;
    }

    public String getSseName() {
        return sseName;
    }

    public void setSseName(String sseName) {
        this.sseName = sseName;
    }

    private String sseName;
    private String substationName;
}
