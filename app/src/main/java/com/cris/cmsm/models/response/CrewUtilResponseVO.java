package com.cris.cmsm.models.response;

import android.widget.TextView;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by cms on 3/8/18.
 */


public class CrewUtilResponseVO {

    @SerializedName("location")
    @Expose
    private String location;
    @SerializedName("crewcount")
    @Expose
    private String crewcount;
    @SerializedName("gt240")
    @Expose
    private String gt240;
    @SerializedName("bt208_240")
    @Expose
    private String bt208_240;
    @SerializedName("bt180_208")
    @Expose
    private String bt180_208;
    @SerializedName("bt160_180")
    @Expose
    private String bt160_180;
    @SerializedName("bt140_160")
    @Expose
    private String bt140_160;
    @SerializedName("lt140")
    @Expose
    private String lt140;


    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCrewcount() {
        return crewcount;
    }

    public void setCrewcount(String crewcount) {
        this.crewcount = crewcount;
    }

    public String getGt240() {
        return gt240;
    }

    public void setGt240(String gt240) {
        this.gt240 = gt240;
    }

    public String getBt208_240() {
        return bt208_240;
    }

    public void setBt208_240(String bt208_240) {
        this.bt208_240 = bt208_240;
    }

    public String getBt180_208() {
        return bt180_208;
    }

    public void setBt180_208(String bt180_208) {
        this.bt180_208 = bt180_208;
    }

    public String getBt160_180() {
        return bt160_180;
    }

    public void setBt160_180(String bt160_180) {
        this.bt160_180 = bt160_180;
    }

    public String getBt140_160() {
        return bt140_160;
    }

    public void setBt140_160(String bt140_160) {
        this.bt140_160 = bt140_160;
    }

    public String getLt140() {
        return lt140;
    }

    public void setLt140(String lt140) {
        this.lt140 = lt140;
    }
}
