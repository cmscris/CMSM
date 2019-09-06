package com.cris.cmsm.models.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CrewOvertimeDetailsVO {


    @SerializedName("running_days")
    @Expose
    private String running_days;
    @SerializedName("non_run_days")
    @Expose
    private String non_run_days;
    @SerializedName("leaves")
    @Expose
    private String leaves;
    @SerializedName("absents")
    @Expose
    private String absents;
    @SerializedName("total_duty")
    @Expose
    private String total_duty;
    @SerializedName("from")
    @Expose
    private String from;
    @SerializedName("to")
    @Expose
    private String to;


    public String getRunning_days() {
        return running_days;
    }

    public void setRunning_days(String running_days) {
        this.running_days = running_days;
    }

    public String getNon_run_days() {
        return non_run_days;
    }

    public void setNon_run_days(String non_run_days) {
        this.non_run_days = non_run_days;
    }

    public String getLeaves() {
        return leaves;
    }

    public void setLeaves(String leaves) {
        this.leaves = leaves;
    }

    public String getAbsents() {
        return absents;
    }

    public void setAbsents(String absents) {
        this.absents = absents;
    }

    public String getTotal_duty() {
        return total_duty;
    }

    public void setTotal_duty(String total_duty) {
        this.total_duty = total_duty;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }
}
