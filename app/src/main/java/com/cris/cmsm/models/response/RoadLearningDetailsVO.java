package com.cris.cmsm.models.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RoadLearningDetailsVO {



    @SerializedName("lr_sec")
    @Expose
    private String lr_sec;
    @SerializedName("drv_date")
    @Expose
    private String drv_date;
    @SerializedName("due_date")
    @Expose
    private String due_date;
    @SerializedName("day_trip_due")
    @Expose
    private String day_trip_due;
    @SerializedName("night_trip_due")
    @Expose
    private String night_trip_due;


    public String getLr_sec() {
        return lr_sec;
    }

    public void setLr_sec(String lr_sec) {
        this.lr_sec = lr_sec;
    }

    public String getDrv_date() {
        return drv_date;
    }

    public void setDrv_date(String drv_date) {
        this.drv_date = drv_date;
    }

    public String getDue_date() {
        return due_date;
    }

    public void setDue_date(String due_date) {
        this.due_date = due_date;
    }

    public String getDay_trip_due() {
        return day_trip_due;
    }

    public void setDay_trip_due(String day_trip_due) {
        this.day_trip_due = day_trip_due;
    }

    public String getNight_trip_due() {
        return night_trip_due;
    }

    public void setNight_trip_due(String night_trip_due) {
        this.night_trip_due = night_trip_due;
    }
}
