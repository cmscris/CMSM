package com.cris.cmsm.models.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 *
 */

public class LTConnectionRequest {

    @SerializedName("substationId")
    @Expose
    private String substationId;
    @SerializedName("annexurefinyear")
    @Expose
    private String annexurefinyear;
    @SerializedName("annexuremonth")
    @Expose
    private String annexuremonth;
    @SerializedName("annexuretype")
    @Expose
    private String annexuretype;
    @SerializedName("divcode")
    @Expose
    private String divcode;
    @SerializedName("rlycode")
    @Expose
    private String rlycode;
    @SerializedName("authlevel")
    @Expose
    private String authlevel;
    @SerializedName("roleid")
    @Expose
    private String roleid;
    @SerializedName("parameter")
    @Expose
    private String parameter;
    @SerializedName("pauthlevel")
    @Expose
    private String pauthlevel;
    @SerializedName("loginid")
    @Expose
    private String loginid;

    public String getSseInCharge() {
        return sseInCharge;
    }

    public void setSseInCharge(String sseInCharge) {
        this.sseInCharge = sseInCharge;
    }

    private String sseInCharge;

    public String getSebName() {
        return sebName;
    }

    public void setSebName(String sebName) {
        this.sebName = sebName;
    }

    private String sebName;

    public String getSubstationId() {
        return substationId;
    }

    public void setSubstationId(String substationId) {
        this.substationId = substationId;
    }

    public String getAnnexurefinyear() {
        return annexurefinyear;
    }

    public void setAnnexurefinyear(String annexurefinyear) {
        this.annexurefinyear = annexurefinyear;
    }

    public String getAnnexuremonth() {
        return annexuremonth;
    }

    public void setAnnexuremonth(String annexuremonth) {
        this.annexuremonth = annexuremonth;
    }

    public String getAnnexuretype() {
        return annexuretype;
    }

    public void setAnnexuretype(String annexuretype) {
        this.annexuretype = annexuretype;
    }

    public String getDivcode() {
        return divcode;
    }

    public void setDivcode(String divcode) {
        this.divcode = divcode;
    }

    public String getRlycode() {
        return rlycode;
    }

    public void setRlycode(String rlycode) {
        this.rlycode = rlycode;
    }

    public String getAuthlevel() {
        return authlevel;
    }

    public void setAuthlevel(String authlevel) {
        this.authlevel = authlevel;
    }

    public String getRoleid() {
        return roleid;
    }

    public void setRoleid(String roleid) {
        this.roleid = roleid;
    }

    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }

    public String getPauthlevel() {
        return pauthlevel;
    }

    public void setPauthlevel(String pauthlevel) {
        this.pauthlevel = pauthlevel;
    }

    public String getLoginid() {
        return loginid;
    }

    public void setLoginid(String loginid) {
        this.loginid = loginid;
    }

}
