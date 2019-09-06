package com.cris.cmsm.models.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 *
 */
public class GetLTConnection {

    @SerializedName("railCode")
    @Expose
    private String railCode;
    @SerializedName("division")
    @Expose
    private String division;
    @SerializedName("year")
    @Expose
    private String year;
    @SerializedName("month")
    @Expose
    private String month;
    @SerializedName("roleId")
    @Expose
    private String roleId;
    @SerializedName("substationId")
    @Expose
    private String substationId;

    /**
     * @return The railCode
     */
    public String getRailCode() {
        return railCode;
    }

    /**
     * @param railCode The railCode
     */
    public void setRailCode(String railCode) {
        this.railCode = railCode;
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
     * @return The substationId
     */
    public String getSubstationId() {
        return substationId;
    }

    /**
     * @param substationId The substationId
     */
    public void setSubstationId(String substationId) {
        this.substationId = substationId;
    }
}
