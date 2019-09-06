package com.cris.cmsm.models.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by cms on 3/12/18.
 */

public class EnergyConsumptionRequest {


    @SerializedName("railwayCode")
    @Expose
    private String railwayCode;
    @SerializedName("divisionCode")
    @Expose
    private String divisionCode;
    @SerializedName("lobbyCode")
    @Expose
    private String lobbyCode;
    @SerializedName("traction")
    @Expose
    private String traction;
    @SerializedName("fYYear")
    @Expose
    private String fYYear;
    @SerializedName("month")
    @Expose
    private String month;


    public String getRailwayCode() {
        return railwayCode;
    }

    public void setRailwayCode(String railwayCode) {
        this.railwayCode = railwayCode;
    }

    public String getDivisionCode() {
        return divisionCode;
    }

    public void setDivisionCode(String divisionCode) {
        this.divisionCode = divisionCode;
    }

    public String getLobbyCode() {
        return lobbyCode;
    }

    public void setLobbyCode(String lobbyCode) {
        this.lobbyCode = lobbyCode;
    }

    public String getTraction() {
        return traction;
    }

    public void setTraction(String traction) {
        this.traction = traction;
    }

    public String getfYYear() {
        return fYYear;
    }

    public void setfYYear(String fYYear) {
        this.fYYear = fYYear;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }
}
