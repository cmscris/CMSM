package com.cris.cmsm.models.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 *
 */

public class MonthlyConsModel {

    @SerializedName("year")
    @Expose
    private String year;
    @SerializedName("railway")
    @Expose
    private String railway;
    @SerializedName("division")
    @Expose
    private String division;
    @SerializedName("authLevel")
    @Expose
    private String authLevel;
    @SerializedName("category")
    @Expose
    private String category;
    @SerializedName("sseIncharge")
    @Expose
    private String sseIncharge;
    @SerializedName("substtn")
    @Expose
    private String substtn;

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
     * @return The category
     */
    public String getCategory() {
        return category;
    }

    /**
     * @param category The category
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * @return The sseIncharge
     */
    public String getSseIncharge() {
        return sseIncharge;
    }

    /**
     * @param sseIncharge The sseIncharge
     */
    public void setSseIncharge(String sseIncharge) {
        this.sseIncharge = sseIncharge;
    }

    /**
     * @return The substtn
     */
    public String getSubsttn() {
        return substtn;
    }

    /**
     * @param substtn The substtn
     */
    public void setSubsttn(String substtn) {
        this.substtn = substtn;
    }

}
