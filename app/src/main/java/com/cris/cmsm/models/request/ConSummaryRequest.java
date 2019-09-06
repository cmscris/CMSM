package com.cris.cmsm.models.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 *
 */
public class ConSummaryRequest {
    @SerializedName("railway")
    @Expose
    private String railway;
    @SerializedName("division")
    @Expose
    private String division;
    @SerializedName("lobby")
    @Expose
    private String lobby;
    @SerializedName("financialYear")
    @Expose
    private String financialYear;
    @SerializedName("month")
    @Expose
    private String month;
    @SerializedName("department")
    @Expose
    private String department;
    @SerializedName("noOfyear")
    @Expose
    private String noOfyear;
    @SerializedName("designation")
    @Expose
    private String designation;


    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
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


    public String getLobby() {
        return lobby;
    }

    public void setLobby(String lobby) {
        this.lobby = lobby;
    }

    /**
     * @return The financialYear
     */
    public String getFinancialYear() {
        return financialYear;
    }

    /**
     * @param financialYear The financialYear
     */
    public void setFinancialYear(String financialYear) {
        this.financialYear = financialYear;
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
     * @return The department
     */
    public String getDepartment() {
        return department;
    }

    /**
     * @param department The department
     */
    public void setDepartment(String department) {
        this.department = department;
    }

    public String getNoOfyear() {
        return noOfyear;
    }

    public void setNoOfyear(String noOfyear) {
        this.noOfyear = noOfyear;
    }
}
