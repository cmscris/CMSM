package com.cris.cmsm.models.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by cms on 4/11/18.
 */

public class LICrewMonitoredResponseVO {


    @SerializedName("sno")
    @Expose
    private String sno;
    @SerializedName("crewid")
    @Expose
    private String crewid;
    @SerializedName("crewname")
    @Expose
    private String crewname;
    @SerializedName("designation")
    @Expose
    private String designation;
    @SerializedName("grade")
    @Expose
    private String grade;
    @SerializedName("gradedate")
    @Expose
    private String gradedate;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("counseldate")
    @Expose
    private String counseldate;
    @SerializedName("availablesttn")
    @Expose
    private String availablesttn;
    @SerializedName("fromsttn")
    @Expose
    private String fromsttn;
    @SerializedName("tosttn")
    @Expose
    private String tosttn;
    @SerializedName("calltime")
    @Expose
    private String calltime;
    @SerializedName("booktime")
    @Expose
    private String booktime;
    @SerializedName("lastsignofftime")
    @Expose
    private String lastsignofftime;
    @SerializedName("availabletime")
    @Expose
    private String availabletime;


    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }

    public String getCrewid() {
        return crewid;
    }

    public void setCrewid(String crewid) {
        this.crewid = crewid;
    }

    public String getCrewname() {
        return crewname;
    }

    public void setCrewname(String crewname) {
        this.crewname = crewname;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getGradedate() {
        return gradedate;
    }

    public void setGradedate(String gradedate) {
        this.gradedate = gradedate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCounseldate() {
        return counseldate;
    }

    public void setCounseldate(String counseldate) {
        this.counseldate = counseldate;
    }
    public String getAvailablesttn() {
        return availablesttn;
    }

    public void setAvailablesttn(String availablesttn) {
        this.availablesttn = availablesttn;
    }

    public String getFromsttn() {
        return fromsttn;
    }

    public void setFromsttn(String fromsttn) {
        this.fromsttn = fromsttn;
    }

    public String getTosttn() {
        return tosttn;
    }

    public void setTosttn(String tosttn) {
        this.tosttn = tosttn;
    }

    public String getCalltime() {
        return calltime;
    }

    public void setCalltime(String calltime) {
        this.calltime = calltime;
    }

    public String getBooktime() {
        return booktime;
    }

    public void setBooktime(String booktime) {
        this.booktime = booktime;
    }

    public String getLastsignofftime() {
        return lastsignofftime;
    }

    public void setLastsignofftime(String lastsignofftime) {
        this.lastsignofftime = lastsignofftime;
    }

    public String getAvailabletime() {
        return availabletime;
    }

    public void setAvailabletime(String availabletime) {
        this.availabletime = availabletime;
    }
}
