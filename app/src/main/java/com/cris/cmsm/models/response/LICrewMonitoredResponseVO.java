package com.cris.cmsm.models.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

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
}
