package com.cris.cmsm.models.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TrainingDetailsVO {


    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("done_date")
    @Expose
    private String done_date;
    @SerializedName("due_date")
    @Expose
    private String due_date;


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDone_date() {
        return done_date;
    }

    public void setDone_date(String done_date) {
        this.done_date = done_date;
    }

    public String getDue_date() {
        return due_date;
    }

    public void setDue_date(String due_date) {
        this.due_date = due_date;
    }
}
