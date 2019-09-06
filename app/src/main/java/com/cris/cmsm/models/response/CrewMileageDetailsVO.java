package com.cris.cmsm.models.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CrewMileageDetailsVO {



    @SerializedName("duty_hrs")
    @Expose
    private String duty_hrs;
    @SerializedName("alkm_non_leave")
    @Expose
    private String alkm_non_leave;
    @SerializedName("alkm_leave")
    @Expose
    private String alkm_leave;
    @SerializedName("total_kms")
    @Expose
    private String total_kms;
    @SerializedName("pay_month")
    @Expose
    private String pay_month;


    public String getDuty_hrs() {
        return duty_hrs;
    }

    public void setDuty_hrs(String duty_hrs) {
        this.duty_hrs = duty_hrs;
    }

    public String getAlkm_non_leave() {
        return alkm_non_leave;
    }

    public void setAlkm_non_leave(String alkm_non_leave) {
        this.alkm_non_leave = alkm_non_leave;
    }

    public String getAlkm_leave() {
        return alkm_leave;
    }

    public void setAlkm_leave(String alkm_leave) {
        this.alkm_leave = alkm_leave;
    }

    public String getTotal_kms() {
        return total_kms;
    }

    public void setTotal_kms(String total_kms) {
        this.total_kms = total_kms;
    }

    public String getPay_month() {
        return pay_month;
    }

    public void setPay_month(String pay_month) {
        this.pay_month = pay_month;
    }
}
