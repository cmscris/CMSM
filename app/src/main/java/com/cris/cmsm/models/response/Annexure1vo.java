package com.cris.cmsm.models.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 *
 */

public class Annexure1vo {
    @SerializedName("sebname")
    @Expose
    private String sebname;
    @SerializedName("year9")
    @Expose
    private String year9;
    @SerializedName("year10")
    @Expose
    private String year10;
    @SerializedName("year11")
    @Expose
    private String year11;
    @SerializedName("year12")
    @Expose
    private String year12;

    /**
     * @return The sebname
     */
    public String getSebname() {
        return sebname;
    }

    /**
     * @param sebname The sebname
     */
    public void setSebname(String sebname) {
        this.sebname = sebname;
    }

    /**
     * @return The year9
     */
    public String getYear9() {
        return year9;
    }

    /**
     * @param year9 The year9
     */
    public void setYear9(String year9) {
        this.year9 = year9;
    }

    /**
     * @return The year10
     */
    public String getYear10() {
        return year10;
    }

    /**
     * @param year10 The year10
     */
    public void setYear10(String year10) {
        this.year10 = year10;
    }

    /**
     * @return The year11
     */
    public String getYear11() {
        return year11;
    }

    /**
     * @param year11 The year11
     */
    public void setYear11(String year11) {
        this.year11 = year11;
    }

    /**
     * @return The year12
     */
    public String getYear12() {
        return year12;
    }

    /**
     * @param year12 The year12
     */
    public void setYear12(String year12) {
        this.year12 = year12;
    }
}
