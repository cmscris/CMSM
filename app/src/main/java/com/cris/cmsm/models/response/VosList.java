
package com.cris.cmsm.models.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VosList {

    @SerializedName("crewId")
    @Expose
    private String crewId;
    @SerializedName("abnrNumber")
    @Expose
    private String abnrNumber;
    @SerializedName("appUserRemarksStts")
    @Expose
    private String appUserRemarksStts;
    @SerializedName("appUserRemarks")
    @Expose
    private String appUserRemarks;
    @SerializedName("abnrCountPending")
    @Expose
    private String abnrCountPending;
    @SerializedName("abnrCountSubmitted")
    @Expose
    private String abnrCountSubmitted;
    @SerializedName("abnrDescription")
    @Expose
    private String abnrDescription;
    @SerializedName("kmStart")
    @Expose
    private String kmStart;
    @SerializedName("kmEnd")
    @Expose
    private String kmEnd;
    @SerializedName("abnrFromSttn")
    @Expose
    private String abnrFromSttn;
    @SerializedName("abnrToSttn")
    @Expose
    private String abnrToSttn;
    @SerializedName("abnrTime")
    @Expose
    private String abnrTime;
    @SerializedName("abnrLocoNo")
    @Expose
    private String abnrLocoNo;
    @SerializedName("abnrTrainNo")
    @Expose
    private String abnrTrainNo;
    @SerializedName("abnrFrwdLoc")
    @Expose
    private String abnrFrwdLoc;
    @SerializedName("abnrFrwdTime")
    @Expose
    private String abnrFrwdTime;
    @SerializedName("abnrFrwdRemarks")
    @Expose
    private String abnrFrwdRemarks;
    @SerializedName("abnrSection")
    @Expose
    private String abnrSection;
    @SerializedName("crewName")
    @Expose
    private String crewName;
    @SerializedName("remarks")
    @Expose
    private String remarks;
    @SerializedName("responseMessage")
    @Expose
    private String responseMessage;
    @SerializedName("imageString")
    @Expose
    private String imageString;
    @SerializedName("abnrCount")
    @Expose
    private String abnrCount;
    @SerializedName("abnrType")
    @Expose
    private String abnrType;
    @SerializedName("abnrLastPendingDate")
    @Expose
    private String abnrLastPendingDate;
    @SerializedName("abnrSubHead")
    @Expose
    private String abnrSubHead;
    @SerializedName("imageFlag")
    @Expose
    private String imageFlag;
    @SerializedName("abnrMonthWise")
    @Expose
    private String abnrMonthWise;
    @SerializedName("grade")
    @Expose
    private String grade;
    @SerializedName("lastgradingdate")
    @Expose
    private String lastgradingdate;
    @SerializedName("total")
    @Expose
    private String total;
    @SerializedName("lastCounselDate")
    @Expose
    private String lastCounselDate;
    @SerializedName("lastFootPlateDate")
    @Expose
    private String lastFootPlateDate;

    public String getLastCounselDate() {
        return lastCounselDate;
    }

    public void setLastCounselDate(String lastCounselDate) {this.lastCounselDate = lastCounselDate;}
    public String getLastFootPlateDate() {
        return lastFootPlateDate;
    }
    public void setLastFootPlateDate(String lastFootPlateDate) {this.lastFootPlateDate = lastFootPlateDate;}
    public String getRemarks() { return remarks;}
    public void setRemarks(String remarks) {this.remarks = remarks;}
    public String getTotal() {
        return total;
    }
    public void setTotal(String total) {
        this.total = total;
    }
    public String getAbnrMonthWise() {
        return abnrMonthWise;
    }
    public void setAbnrMonthWise(String abnrMonthWise) {
        this.abnrMonthWise = abnrMonthWise;
    }
    public String getAbnrCount() {
        return abnrCount;
    }
    public void setAbnrCount(String abnrCount) {
        this.abnrCount = abnrCount;
    }
    public String getAbnrType() {
        return abnrType;
    }
    public void setAbnrType(String abnrType) {
        this.abnrType = abnrType;
    }
    public String getAbnrLastPendingDate() {
        return abnrLastPendingDate;
    }
    public void setAbnrLastPendingDate(String abnrLastPendingDate) {this.abnrLastPendingDate = abnrLastPendingDate;}
    public String getImageString() {
        return imageString;
    }
    public void setImageString(String imageString) {
        this.imageString = imageString;
    }
    public String getAbnrSubHead() {
        return abnrSubHead;
    }
    public void setAbnrSubHead(String abnrSubHead) {
        this.abnrSubHead = abnrSubHead;
    }
    public String getImageFlag() {
        return imageFlag;
    }
    public void setImageFlag(String imageFlag) {
        this.imageFlag = imageFlag;
    }
    public String getAbnrCountPending() {
        return abnrCountPending;
    }
    public void setAbnrCountPending(String abnrCountPending) { this.abnrCountPending = abnrCountPending; }
    public String getAbnrCountSubmitted() {
        return abnrCountSubmitted;
    }
    public void setAbnrCountSubmitted(String abnrCountSubmitted) {this.abnrCountSubmitted = abnrCountSubmitted;}
    public String getAppUserRemarksStts() {
        return appUserRemarksStts;
    }
    public void setAppUserRemarksStts(String appUserRemarksStts){this.appUserRemarksStts=appUserRemarksStts;}
    public String getappUserRemarks() {
        return appUserRemarks;
    }
    public void setappUserRemarks(String appUserRemarks){this.appUserRemarks=appUserRemarks;}
    public String getCrewId() {
        return crewId;
    }
    public void setCrewId(String crewId) {
        this.crewId = crewId;
    }
    public String getabnrNumber() {
        return abnrNumber;
    }
    public void setabnrNumber(String abnrNumber) {
        this.abnrNumber = abnrNumber;
    }
    public String getAbnrDescription() {
        return abnrDescription;
    }
    public void setAbnrDescription(String abnrDescription) {this.abnrDescription = abnrDescription; }
    public String getKmStart() {
        return kmStart;
    }
    public void setKmStart(String kmStart) {
        this.kmStart = kmStart;
    }
    public String getKmEnd() {
        return kmEnd;
    }
    public void setKmEnd(String kmEnd) {
        this.kmEnd = kmEnd;
    }
    public String getAbnrFromSttn() {
        return abnrFromSttn;
    }
    public void setAbnrFromSttn(String abnrFromSttn) {
        this.abnrFromSttn = abnrFromSttn;
    }
    public String getAbnrToSttn() {
        return abnrToSttn;
    }
    public void setAbnrToSttn(String abnrToSttn) {
        this.abnrToSttn = abnrToSttn;
    }
    public String getAbnrTime() {
        return abnrTime;
    }
    public void setAbnrTime(String abnrTime) {
        this.abnrTime = abnrTime;
    }
    public String getAbnrLocoNo() {
        return abnrLocoNo;
    }
    public void setAbnrLocoNo(String abnrLocoNo) {
        this.abnrLocoNo = abnrLocoNo;
    }
    public String getAbnrTrainNo() {
        return abnrTrainNo;
    }
    public void setAbnrTrainNo(String abnrTrainNo) {
        this.abnrTrainNo = abnrTrainNo;
    }
    public String getAbnrFrwdLoc() {
        return abnrFrwdLoc;
    }
    public void setAbnrFrwdLoc(String abnrFrwdLoc) {
        this.abnrFrwdLoc = abnrFrwdLoc;
    }
    public String getAbnrFrwdTime() {
        return abnrFrwdTime;
    }
    public void setAbnrFrwdTime(String abnrFrwdTime) {
        this.abnrFrwdTime = abnrFrwdTime;
    }
    public String getAbnrFrwdRemarks() {
        return abnrFrwdRemarks;
    }
    public void setAbnrFrwdRemarks(String abnrFrwdRemarks) {this.abnrFrwdRemarks = abnrFrwdRemarks;}
    public String getAbnrSection() {
        return abnrSection;
    }
    public void setAbnrSection(String abnrSection) {this.abnrSection = abnrSection;}
    public String getCrewName() {
        return crewName;
    }
    public void setCrewName(String crewName) {
        this.crewName = crewName;
    }
    public String getResponseMessage() {
        return responseMessage;
    }
    public void setResponseMessage(String responseMessage) {this.responseMessage = responseMessage;}
    public String getGrade() {
        return grade;
    }
    public void setGrade(String grade) {
        this.grade = grade;
    }
    public String getLastgradingdate() {return lastgradingdate;}
    public void setLastgradingdate(String lastgradingdate) {this.lastgradingdate = lastgradingdate;}
}
