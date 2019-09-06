package com.cris.cmsm.models.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CrewAvailabilityDetailVO {

    @SerializedName("lobby")
    @Expose
    private String lobby;
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
    @SerializedName("linkNo")
    @Expose
    private String linkNo;
    @SerializedName("linkTrain")
    @Expose

    private String linkTrain ;
    @SerializedName("linkTo")
    @Expose
    private String linkTo ;
    @SerializedName("linkFrom")
    @Expose
    private String linkFrom ;
    @SerializedName("linkSignOn")
    @Expose
    private String linkSignOn ;
    @SerializedName("linkCallserve")
    @Expose
    private String linkCallserve;
    @SerializedName("linkCallserveDate")
    @Expose
    private String linkCallserveDate;
    @SerializedName("change_flag")
    @Expose
    private String change_flag;
    @SerializedName("idcount")
    @Expose
    private int idcount;
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


    public String getLobby() {
        return lobby;
    }

    public void setLobby(String lobby) {
        this.lobby = lobby;
    }

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

    public String getLinkNo() {
        return linkNo;
    }

    public void setLinkNo(String linkNo) {
        this.linkNo = linkNo;
    }

    public String getLinkTrain() {
        return linkTrain;
    }

    public void setLinkTrain(String linkTrain) {
        this.linkTrain = linkTrain;
    }

    public String getLinkTo() {
        return linkTo;
    }

    public void setLinkTo(String linkTo) {
        this.linkTo = linkTo;
    }

    public String getLinkFrom() {
        return linkFrom;
    }

    public void setLinkFrom(String linkFrom) {
        this.linkFrom = linkFrom;
    }

    public String getLinkSignOn() {
        return linkSignOn;
    }

    public void setLinkSignOn(String linkSignOn) {
        this.linkSignOn = linkSignOn;
    }

    public String getLinkCallserve() {
        return linkCallserve;
    }

    public void setLinkCallserve(String linkCallserve) {
        this.linkCallserve = linkCallserve;
    }

    public String getLinkCallserveDate() {
        return linkCallserveDate;
    }

    public void setLinkCallserveDate(String linkCallserveDate) {
        this.linkCallserveDate = linkCallserveDate;
    }

    public String getChange_flag() {
        return change_flag;
    }

    public void setChange_flag(String change_flag) {
        this.change_flag = change_flag;
    }

    public int getIdcount() {
        return idcount;
    }

    public void setIdcount(int idcount) {
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
}
