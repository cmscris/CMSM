package com.cris.cmsm.models.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 *
 */

public class SubStationAsssetSummaryVO {
    @SerializedName("railway")
    @Expose
    private String railway;
    @SerializedName("divison")
    @Expose
    private String divison;
    @SerializedName("dgset")
    @Expose
    private String dgset;
    @SerializedName("transformer")
    @Expose
    private String transformer;
    @SerializedName("fireExtinguisher")
    @Expose
    private String fireExtinguisher;
    @SerializedName("earthing")
    @Expose
    private String earthing;
    @SerializedName("apfcPanel")
    @Expose
    private String apfcPanel;
    @SerializedName("htPanel")
    @Expose
    private String htPanel;
    @SerializedName("ltPanel")
    @Expose
    private String ltPanel;
    @SerializedName("servostabilizer")
    @Expose
    private String servostabilizer;

    @SerializedName("assestType")
    @Expose
    private String assestType;
    public String getAssestType() {
        return assestType;
    }

    public void setAssestType(String assestType) {
        this.assestType = assestType;
    }

    public String getRailway() {
        return railway;
    }

    public void setRailway(String railway) {
        this.railway = railway;
    }

    public String getDivison() {
        return divison;
    }

    public void setDivison(String divison) {
        this.divison = divison;
    }

    public String getDgset() {
        return dgset;
    }

    public void setDgset(String dgset) {
        this.dgset = dgset;
    }

    public String getTransformer() {
        return transformer;
    }

    public void setTransformer(String transformer) {
        this.transformer = transformer;
    }

    public String getFireExtinguisher() {
        return fireExtinguisher;
    }

    public void setFireExtinguisher(String fireExtinguisher) {
        this.fireExtinguisher = fireExtinguisher;
    }

    public String getEarthing() {
        return earthing;
    }

    public void setEarthing(String earthing) {
        this.earthing = earthing;
    }

    public String getApfcPanel() {
        return apfcPanel;
    }

    public void setApfcPanel(String apfcPanel) {
        this.apfcPanel = apfcPanel;
    }

    public String getHtPanel() {
        return htPanel;
    }

    public void setHtPanel(String htPanel) {
        this.htPanel = htPanel;
    }

    public String getLtPanel() {
        return ltPanel;
    }

    public void setLtPanel(String ltPanel) {
        this.ltPanel = ltPanel;
    }

    public String getServostabilizer() {
        return servostabilizer;
    }

    public void setServostabilizer(String servostabilizer) {
        this.servostabilizer = servostabilizer;
    }

    public String getAssetsName() {
        return assetsName;
    }

    public void setAssetsName(String assetsName) {
        this.assetsName = assetsName;
    }

    private String assetsName;
}
