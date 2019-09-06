package com.cris.cmsm.models.response;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CrewOnAvailabilityResponse {

    @SerializedName("crewId")
    @Expose
    private String crewId;
    @SerializedName("lastSignOnSttn")
    @Expose
    private String lastSignOnSttn;
    @SerializedName("crewName")
    @Expose
    private String crewName;
    @SerializedName("crewDesignation")
    @Expose
    private String crewDesignation;
    @SerializedName("crewType")
    @Expose
    private String crewType;
    @SerializedName("signOffTime")
    @Expose
    private String signOffTime;
    @SerializedName("availedHours")
    @Expose
    private String availedHours;
    @SerializedName("lastDutyHours")
    @Expose
    private String lastDutyHours;
    @SerializedName("crewProgressive")
    @Expose
    private String crewProgressive;
    @SerializedName("crewRest")
    @Expose
    private String crewRest;
    @SerializedName("trainNo")
    @Expose
    private String trainNo;
    @SerializedName("locoNo")
    @Expose
    private String locoNo;
    @SerializedName("idcount")
    @Expose
    private Long idcount;
    @SerializedName("mobileNO")
    @Expose
    private String mobileNO;
    @SerializedName("underRest")
    @Expose
    private String underRest;
    @SerializedName("advanceNonrun")
    @Expose
    private String advanceNonrun;
    @SerializedName("crewOnLr")
    @Expose
    private String crewOnLr;
    @SerializedName("cadre")
    @Expose
    private String cadre;
    @SerializedName("progHrs")
    @Expose
    private String progHrs;
    @SerializedName("nightHrs")
    @Expose
    private String nightHrs;
    @SerializedName("availSttn")
    @Expose
    private String availSttn;
    @SerializedName("linkNo")
    @Expose
    private String linkNo;

    public String getCrewId() {
        return crewId;
    }

    public void setCrewId(String crewId) {
        this.crewId = crewId;
    }

    public String getLastSignOnSttn() {
        return lastSignOnSttn;
    }

    public void setLastSignOnSttn(String lastSignOnSttn) {
        this.lastSignOnSttn = lastSignOnSttn;
    }

    public String getCrewName() {
        return crewName;
    }

    public void setCrewName(String crewName) {
        this.crewName = crewName;
    }

    public String getCrewDesignation() {
        return crewDesignation;
    }

    public void setCrewDesignation(String crewDesignation) {
        this.crewDesignation = crewDesignation;
    }

    public String getCrewType() {
        return crewType;
    }

    public void setCrewType(String crewType) {
        this.crewType = crewType;
    }

    public String getSignOffTime() {
        return signOffTime;
    }

    public void setSignOffTime(String signOffTime) {
        this.signOffTime = signOffTime;
    }

    public String getAvailedHours() {
        return availedHours;
    }

    public void setAvailedHours(String availedHours) {
        this.availedHours = availedHours;
    }

    public String getLastDutyHours() {
        return lastDutyHours;
    }

    public void setLastDutyHours(String lastDutyHours) {
        this.lastDutyHours = lastDutyHours;
    }

    public String getCrewProgressive() {
        return crewProgressive;
    }

    public void setCrewProgressive(String crewProgressive) {
        this.crewProgressive = crewProgressive;
    }

    public String getCrewRest() {
        return crewRest;
    }

    public void setCrewRest(String crewRest) {
        this.crewRest = crewRest;
    }

    public String getTrainNo() {
        return trainNo;
    }

    public void setTrainNo(String trainNo) {
        this.trainNo = trainNo;
    }

    public String getLocoNo() {
        return locoNo;
    }

    public void setLocoNo(String locoNo) {
        this.locoNo = locoNo;
    }

    public Long getIdcount() {
        return idcount;
    }

    public void setIdcount(Long idcount) {
        this.idcount = idcount;
    }

    public String getMobileNO() {
        return mobileNO;
    }

    public void setMobileNO(String mobileNO) {
        this.mobileNO = mobileNO;
    }

    public String getUnderRest() {
        return underRest;
    }

    public void setUnderRest(String underRest) {
        this.underRest = underRest;
    }

    public String getAdvanceNonrun() {
        return advanceNonrun;
    }

    public void setAdvanceNonrun(String advanceNonrun) {
        this.advanceNonrun = advanceNonrun;
    }

    public String getCrewOnLr() {
        return crewOnLr;
    }

    public void setCrewOnLr(String crewOnLr) {
        this.crewOnLr = crewOnLr;
    }

    public String getCadre() {
        return cadre;
    }

    public void setCadre(String cadre) {
        this.cadre = cadre;
    }

    public String getProgHrs() {
        return progHrs;
    }

    public void setProgHrs(String progHrs) {
        this.progHrs = progHrs;
    }

    public String getNightHrs() {
        return nightHrs;
    }

    public void setNightHrs(String nightHrs) {
        this.nightHrs = nightHrs;
    }

    public String getAvailSttn() {
        return availSttn;
    }

    public void setAvailSttn(String availSttn) {
        this.availSttn = availSttn;
    }

    public String getLinkNo() {
        return linkNo;
    }

    public void setLinkNo(String linkNo) {
        this.linkNo = linkNo;
    }

}