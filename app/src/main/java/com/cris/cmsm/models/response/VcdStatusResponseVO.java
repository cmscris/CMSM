package com.cris.cmsm.models.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class VcdStatusResponseVO {


    @SerializedName("location")
    @Expose
    private String location;

    @SerializedName("baseShed")
    @Expose
    private String baseShed;

    @SerializedName("total")
    @Expose
    private String total;

    @SerializedName("working")
    @Expose
    private String working;

    @SerializedName("notWorking")
    @Expose
    private String notWorking;

    @SerializedName("notApplicable")
    @Expose
    private String notApplicable;

    @SerializedName("statusNotAvailable")
    @Expose
    private String statusNotAvailable;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getBaseShed() {
        return baseShed;
    }

    public void setBaseShed(String baseShed) {
        this.baseShed = baseShed;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getWorking() {
        return working;
    }

    public void setWorking(String working) {
        this.working = working;
    }

    public String getNotWorking() {
        return notWorking;
    }

    public void setNotWorking(String notWorking) {
        this.notWorking = notWorking;
    }

    public String getNotApplicable() {
        return notApplicable;
    }

    public void setNotApplicable(String notApplicable) {
        this.notApplicable = notApplicable;
    }

    public String getStatusNotAvailable() {
        return statusNotAvailable;
    }

    public void setStatusNotAvailable(String statusNotAvailable) {
        this.statusNotAvailable = statusNotAvailable;
    }
}
