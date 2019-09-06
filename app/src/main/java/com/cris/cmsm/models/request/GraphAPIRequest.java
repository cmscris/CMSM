package com.cris.cmsm.models.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 *
 */
public class GraphAPIRequest {

    @SerializedName("railwayCode")
    @Expose
    private String railwayCode;
    @SerializedName("divisionCode")
    @Expose
    private String divisionCode;
    @SerializedName("lobbyCode")
    @Expose
    private String lobbyCode;
    @SerializedName("serviceType")
    @Expose
    private String serviceType;
    @SerializedName("fYYear")
    @Expose
    private String fYYear;
    @SerializedName("month")
    @Expose
    private String month;
    @SerializedName("flag")
    @Expose
    private String flag;
    @SerializedName("designation")
    @Expose
    private String designation;


  @SerializedName("paramList")
    @Expose
    private List<String> paramList;






    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    /**
     *
     * @return
     * The railwayCode
     */
    public String getRailwayCode() {
        return railwayCode;
    }

    /**
     *
     * @param railwayCode
     * The railwayCode
     */
    public void setRailwayCode(String railwayCode) {
        this.railwayCode = railwayCode;
    }

    /**
     *
     * @return
     * The divisionCode
     */
    public String getDivisionCode() {
        return divisionCode;
    }

    /**
     *
     * @param divisionCode
     * The divisionCode
     */
    public void setDivisionCode(String divisionCode) {
        this.divisionCode = divisionCode;
    }


    public String getLobbyCode() {
        return lobbyCode;
    }

    public void setLobbyCode(String lobbyCode) {
        this.lobbyCode = lobbyCode;
    }

    /**
     *
     * @return
     * The serviceType
     */
    public String getServiceType() {
        return serviceType;
    }

    /**
     *
     * @param serviceType
     * The serviceType
     */
    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    /**
     *
     * @return
     * The fYYear
     */
    public String getFYYear() {
        return fYYear;
    }

    /**
     *
     * @param fYYear
     * The fYYear
     */
    public void setFYYear(String fYYear) {
        this.fYYear = fYYear;
    }

    /**
     *
     * @return
     * The month
     */
    public String getMonth() {
        return month;
    }

    /**
     *
     * @param month
     * The month
     */
    public void setMonth(String month) {
        this.month = month;
    }

    /**
     *
     * @return
     * The flag
     */
    public String getFlag() {
        return flag;
    }

    /**
     *
     * @param flag
     * The flag
     */
    public void setFlag(String flag) {
        this.flag = flag;
    }
    public void setparamlist(List<String> paramList) {
        this.paramList = paramList;
    }


}
