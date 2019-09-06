package com.cris.cmsm.models.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 *
 */
public class DivisionVO {
    @SerializedName("divcode")
    @Expose
    private String divcode;
    @SerializedName("sname")
    @Expose
    private String sname;
    @SerializedName("fname")
    @Expose
    private String fname;
    @SerializedName("rlycode")
    @Expose
    private String rlycode;
    @SerializedName("divid")
    @Expose
    private String divid;
    @SerializedName("electrifiedflag")
    @Expose
    private String  electrifiedflag;
    @SerializedName("divflag")
    @Expose
    private String divflag;

    /**
     * @return The divcode
     */
    public String getDivcode() {
        return divcode;
    }

    /**
     * @param divcode The divcode
     */
    public void setDivcode(String divcode) {
        this.divcode = divcode;
    }

    /**
     * @return The sname
     */
    public String getSname() {
        return sname;
    }

    /**
     * @param sname The sname
     */
    public void setSname(String sname) {
        this.sname = sname;
    }

    /**
     * @return The fname
     */
    public String getFname() {
        return fname;
    }

    /**
     * @param fname The fname
     */
    public void setFname(String fname) {
        this.fname = fname;
    }

    /**
     * @return The rlycode
     */
    public String getRlycode() {
        return rlycode;
    }

    /**
     * @param rlycode The rlycode
     */
    public void setRlycode(String rlycode) {
        this.rlycode = rlycode;
    }

    /**
     * @return The divid
     */
    public String getDivid() {
        return divid;
    }

    /**
     * @param divid The divid
     */
    public void setDivid(String divid) {
        this.divid = divid;
    }

    /**
     * @return The electrifiedflag
     */
    public String getElectrifiedflag() {
        return electrifiedflag;
    }

    /**
     * @param electrifiedflag The electrifiedflag
     */
    public void setElectrifiedflag(String electrifiedflag) {
        this.electrifiedflag = electrifiedflag;
    }

    /**
     * @return The divflag
     */
    public String getDivflag() {
        return divflag;
    }

    /**
     * @param divflag The divflag
     */
    public void setDivflag(String divflag) {
        this.divflag = divflag;
    }
}
