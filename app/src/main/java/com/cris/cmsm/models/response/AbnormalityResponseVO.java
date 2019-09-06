package com.cris.cmsm.models.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by rangasanju on 3/16/18.
 */

public class AbnormalityResponseVO {


    @SerializedName("location")
    @Expose
    private String location;

    @SerializedName("abnormality_head")
    @Expose
    private String abnormality_head;

    @SerializedName("lt_8")
    @Expose
    private String lt_8;

    @SerializedName("bw_8_16")
    @Expose
    private String bw_8_16;

    @SerializedName("bw_16_24")
    @Expose
    private String bw_16_24;

    @SerializedName("bw_24_72")
    @Expose
    private String bw_24_72;

    @SerializedName("bw_72_240")
    @Expose
    private String bw_72_240;

    @SerializedName("gt_240")
    @Expose
    private String gt_240;

    @SerializedName("still_open")
    @Expose
    private String still_open;


    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAbnormality_head() {
        return abnormality_head;
    }

    public void setAbnormality_head(String abnormality_head) {
        this.abnormality_head = abnormality_head;
    }

    public String getLt_8() {
        return lt_8;
    }

    public void setLt_8(String lt_8) {
        this.lt_8 = lt_8;
    }

    public String getBw_8_16() {
        return bw_8_16;
    }

    public void setBw_8_16(String bw_8_16) {
        this.bw_8_16 = bw_8_16;
    }

    public String getBw_16_24() {
        return bw_16_24;
    }

    public void setBw_16_24(String bw_16_24) {
        this.bw_16_24 = bw_16_24;
    }

    public String getBw_24_72() {
        return bw_24_72;
    }

    public void setBw_24_72(String bw_24_72) {
        this.bw_24_72 = bw_24_72;
    }

    public String getBw_72_240() {
        return bw_72_240;
    }

    public void setBw_72_240(String bw_72_240) {
        this.bw_72_240 = bw_72_240;
    }

    public String getGt_240() {
        return gt_240;
    }

    public void setGt_240(String gt_240) {
        this.gt_240 = gt_240;
    }

    public String getStill_open() {
        return still_open;
    }

    public void setStill_open(String still_open) {
        this.still_open = still_open;
    }
}
