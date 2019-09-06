package com.cris.cmsm.models.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 *
 */
public class MISReportRequest {
    @SerializedName("year")
    @Expose
    private String year;
    @SerializedName("month")
    @Expose
    private String month;
    @SerializedName("railway")
    @Expose
    private String railway;
    @SerializedName("division")
    @Expose
    private String division;
    @SerializedName("authLevel")
    @Expose
    private String authLevel;
    @SerializedName("roleId")
    @Expose
    private String roleId;
    @SerializedName("flag")
    @Expose
    private String flag;
    @SerializedName("annexuretype")
    @Expose
    private String annexuretype;
    @SerializedName("logId")
    @Expose
    private String logId;

    public String getNoofyear() {
        return noofyear;
    }

    public void setNoofyear(String noofyear) {
        this.noofyear = noofyear;
    }

    @SerializedName("noofyear")
    @Expose
    private String noofyear;

    public String getpFinyear() {
        return pFinyear;
    }

    public void setpFinyear(String pFinyear) {
        this.pFinyear = pFinyear;
    }

    @SerializedName("pFinyear")
    @Expose
    private String pFinyear;

    public String getAuthenticationLevel() {
        return authenticationLevel;
    }

    public void setAuthenticationLevel(String authenticationLevel) {
        this.authenticationLevel = authenticationLevel;
    }

    @SerializedName("authlevel")
    @Expose
    private String authenticationLevel;


    public String getAnnexureTypo() {
        return annexureTypo;
    }

    public void setAnnexureTypo(String annexureTypo) {
        this.annexureTypo = annexureTypo;
    }

    @SerializedName("annexureType")
    @Expose
    private String annexureTypo;

    /**
     * @return The year
     */
    public String getYear() {
        return year;
    }

    /**
     * @param year The year
     */
    public void setYear(String year) {
        this.year = year;
    }

    /**
     * @return The month
     */
    public String getMonth() {
        return month;
    }

    /**
     * @param month The month
     */
    public void setMonth(String month) {
        this.month = month;
    }

    /**
     * @return The railway
     */
    public String getRailway() {
        return railway;
    }

    /**
     * @param railway The railway
     */
    public void setRailway(String railway) {
        this.railway = railway;
    }

    /**
     * @return The division
     */
    public String getDivision() {
        return division;
    }

    /**
     * @param division The division
     */
    public void setDivision(String division) {
        this.division = division;
    }

    /**
     * @return The authLevel
     */
    public String getAuthLevel() {
        return authLevel;
    }

    /**
     * @param authLevel The authLevel
     */
    public void setAuthLevel(String authLevel) {
        this.authLevel = authLevel;
    }

    /**
     * @return The roleId
     */
    public String getRoleId() {
        return roleId;
    }

    /**
     * @param roleId The roleId
     */
    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    /**
     * @return The flag
     */
    public String getFlag() {
        return flag;
    }

    /**
     * @param flag The flag
     */
    public void setFlag(String flag) {
        this.flag = flag;
    }

    /**
     * @return The annexuretype
     */
    public String getAnnexuretype() {
        return annexuretype;
    }

    /**
     * @param annexuretype The annexuretype
     */
    public void setAnnexuretype(String annexuretype) {
        this.annexuretype = annexuretype;
    }

    public String getLogId() {
        return logId;
    }

    /**
     * @param logId The logId
     */
    public void setLogId(String logId) {
        this.logId = logId;
    }
}

