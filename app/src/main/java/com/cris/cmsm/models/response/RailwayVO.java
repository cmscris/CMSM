package com.cris.cmsm.models.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 *
 */
public class RailwayVO {
    @SerializedName("rlycode")
    @Expose
    private String rlycode;
    @SerializedName("sname")
    @Expose
    private String sname;
    @SerializedName("fname")
    @Expose
    private String fname;
    @SerializedName("flag")
    @Expose
    private String flag;

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
     * @return The flag
     */
    public String getFlag() {
        return flag;
    }

    /**
     * @param flag The flag
     */
    public void setFlag(String flag) {
        this.flag = flag;
    }
}
