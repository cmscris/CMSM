package com.cris.cmsm.models.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by cms on 3/12/18.
 */

public class CrewAvailabilityDetailRequest {

   /* {
        "hqCode":"GZB",
            "crewRequiredFor":"",
            "traction":"ALL",
            "cadre":"M",
            "slot":"without Slot Data",
            "crewAvailibilityType":"",
            "endDate":"",
            "officiating":"",
            "crewType":"'LPG'"
    }*/


    @SerializedName("hqCode")
    @Expose
    private String hqCode="";

    @SerializedName("crewRequiredFor")
    @Expose
    private String crewRequiredFor="";

    @SerializedName("traction")
    @Expose
    private String traction="";

    @SerializedName("cadre")
    @Expose
    private String cadre="";

    @SerializedName("slot")
    @Expose
    private String slot="";

    @SerializedName("crewAvailibilityType")
    @Expose
    private String crewAvailibilityType="";

    @SerializedName("endDate")
    @Expose
    private String endDate="";

    @SerializedName("officiating")
    @Expose
    private String officiating="";

    @SerializedName("crewType")
    @Expose
    private String crewType="";







    public String getHqCode() {
        return hqCode;
    }

    public void setHqCode(String hqCode) {
        this.hqCode = hqCode;
    }

    public String getCrewRequiredFor() {
        return crewRequiredFor;
    }

    public void setCrewRequiredFor(String crewRequiredFor) {
        this.crewRequiredFor = crewRequiredFor;
    }

    public String getTraction() {
        return traction;
    }

    public void setTraction(String traction) {
        this.traction = traction;
    }

    public String getCadre() {
        return cadre;
    }

    public void setCadre(String cadre) {
        this.cadre = cadre;
    }

    public String getSlot() {
        return slot;
    }

    public void setSlot(String slot) {
        this.slot = slot;
    }

    public String getCrewAvailibilityType() {
        return crewAvailibilityType;
    }

    public void setCrewAvailibilityType(String crewAvailibilityType) {
        this.crewAvailibilityType = crewAvailibilityType;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getOfficiating() {
        return officiating;
    }

    public void setOfficiating(String officiating) {
        this.officiating = officiating;
    }

    public String getCrewType() {
        return crewType;
    }

    public void setCrewType(String crewType) {
        this.crewType = crewType;
    }
}
