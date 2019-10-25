package com.cris.cmsm.models.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LiMovementVOsResponse {
    @SerializedName("sno")
    @Expose
    private String sno;
    @SerializedName("fromSttn")
    @Expose
    private String fromSttn;
    @SerializedName("toSttn")
    @Expose
    private String toSttn;
    @SerializedName("fromDtTime")
    @Expose
    private String fromDtTime;
    @SerializedName("toDtTime")
    @Expose
    private String toDtTime;
    @SerializedName("via1")
    @Expose
    private String via1;
    @SerializedName("via2")
    @Expose
    private String via2;
    @SerializedName("dutyType")
    @Expose
    private String dutyType;
    @SerializedName("locoNo")
    @Expose
    private String locoNo;
    @SerializedName("trainNo")
    @Expose
    private String trainNo;
    @SerializedName("kms")
    @Expose
    private String kms;
    @SerializedName("remrk")
    @Expose
    private String remrk;

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }

    public String getFromSttn() {
        return fromSttn;
    }

    public void setFromSttn(String fromSttn) {
        this.fromSttn = fromSttn;
    }

    public String getToSttn() {
        return toSttn;
    }

    public void setToSttn(String toSttn) {
        this.toSttn = toSttn;
    }

    public String getFromDtTime() {
        return fromDtTime;
    }

    public void setFromDtTime(String fromDtTime) {
        this.fromDtTime = fromDtTime;
    }

    public String getToDtTime() {
        return toDtTime;
    }

    public void setToDtTime(String toDtTime) {
        this.toDtTime = toDtTime;
    }

    public String getVia1() {
        return via1;
    }

    public void setVia1(String via1) {
        this.via1 = via1;
    }

    public String getVia2() {
        return via2;
    }

    public void setVia2(String via2) {
        this.via2 = via2;
    }

    public String getDutyType() {
        return dutyType;
    }

    public void setDutyType(String dutyType) {
        this.dutyType = dutyType;
    }

    public String getLocoNo() {
        return locoNo;
    }

    public void setLocoNo(String locoNo) {
        this.locoNo = locoNo;
    }

    public String getTrainNo() {
        return trainNo;
    }

    public void setTrainNo(String trainNo) {
        this.trainNo = trainNo;
    }

    public String getKms() {
        return kms;
    }

    public void setKms(String kms) {
        this.kms = kms;
    }

    public String getRemrk() {
        return remrk;
    }

    public void setRemrk(String remrk) {
        this.remrk = remrk;
    }

}
