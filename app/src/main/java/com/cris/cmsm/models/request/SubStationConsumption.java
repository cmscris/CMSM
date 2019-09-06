package com.cris.cmsm.models.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 *
 */
public class SubStationConsumption {
    @SerializedName("substationId")
    @Expose
    private String substationId;
    @SerializedName("fyYear")
    @Expose
    private String fyYear;
    @SerializedName("month")
    @Expose
    private String month;
    @SerializedName("annextureType")
    @Expose
    private String annextureType;
    @SerializedName("authLevel")
    @Expose
    private String authLevel;
    @SerializedName("railCode")
    @Expose
    private String railCode;
    @SerializedName("divCode")
    @Expose
    private String divCode;
    @SerializedName("roleId")
    @Expose
    private String roleId;
    @SerializedName("parameter")
    @Expose
    private String parameter;
    @SerializedName("pauthlevel")
    @Expose
    private String pauthlevel;
    @SerializedName("loginid")
    @Expose
    private String loginid;

    public String getSubstationId() {
        return substationId;
    }

    public void setSubstationId(String substationId) {
        this.substationId = substationId;
    }

    public String getFyYear() {
        return fyYear;
    }

    public void setFyYear(String fyYear) {
        this.fyYear = fyYear;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getAnnextureType() {
        return annextureType;
    }

    public void setAnnextureType(String annextureType) {
        this.annextureType = annextureType;
    }

    public String getAuthLevel() {
        return authLevel;
    }

    public void setAuthLevel(String authLevel) {
        this.authLevel = authLevel;
    }

    public String getRailCode() {
        return railCode;
    }

    public void setRailCode(String railCode) {
        this.railCode = railCode;
    }

    public String getDivCode() {
        return divCode;
    }

    public void setDivCode(String divCode) {
        this.divCode = divCode;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }

    public String getPauthlevel() {
        return pauthlevel;
    }

    public void setPauthlevel(String pauthlevel) {
        this.pauthlevel = pauthlevel;
    }

    public String getLoginid() {
        return loginid;
    }

    public void setLoginid(String loginid) {
        this.loginid = loginid;
    }


}
