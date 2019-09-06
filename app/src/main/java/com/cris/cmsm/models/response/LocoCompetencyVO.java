package com.cris.cmsm.models.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LocoCompetencyVO {


    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("last_drive_date")
    @Expose
    private String last_drive_date;
    @SerializedName("due_date")
    @Expose
    private String due_date;


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLast_drive_date() {
        return last_drive_date;
    }

    public void setLast_drive_date(String last_drive_date) {
        this.last_drive_date = last_drive_date;
    }

    public String getDue_date() {
        return due_date;
    }

    public void setDue_date(String due_date) {
        this.due_date = due_date;
    }
}
