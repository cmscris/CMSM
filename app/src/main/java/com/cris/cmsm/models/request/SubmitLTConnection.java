package com.cris.cmsm.models.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 *
 */

public class SubmitLTConnection {
    @SerializedName("annexureId")
    @Expose
    private String annexureId;
    @SerializedName("railCode")
    @Expose
    private String railCode;
    @SerializedName("year")
    @Expose
    private String year;
    @SerializedName("month")
    @Expose
    private String month;
    @SerializedName("annexureType")
    @Expose
    private String annexureType;
    @SerializedName("division")
    @Expose
    private String division;
    @SerializedName("authlevel")
    @Expose
    private String authlevel;
    @SerializedName("roleId")
    @Expose
    private String roleId;
    @SerializedName("substationId")
    @Expose
    private String substationId;
    @SerializedName("loginId")
    @Expose
    private String loginId;
    @SerializedName("seb")
    @Expose
    private String seb;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("consumption")
    @Expose
    private String consumption;
    @SerializedName("billing")
    @Expose
    private String billing;
    @SerializedName("remarks")
    @Expose
    private String remarks;

    @SerializedName("ssRolId")
    @Expose
    private String ssRolId;

    public String getAnnexureId() {
        return annexureId;
    }

    public void setAnnexureId(String annexureId) {
        this.annexureId = annexureId;
    }

    public String getRailCode() {
        return railCode;
    }

    public void setRailCode(String railCode) {
        this.railCode = railCode;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getAnnexureType() {
        return annexureType;
    }

    public void setAnnexureType(String annexureType) {
        this.annexureType = annexureType;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public String getAuthlevel() {
        return authlevel;
    }

    public void setAuthlevel(String authlevel) {
        this.authlevel = authlevel;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getSubstationId() {
        return substationId;
    }

    public void setSubstationId(String substationId) {
        this.substationId = substationId;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getSeb() {
        return seb;
    }

    public void setSeb(String seb) {
        this.seb = seb;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getConsumption() {
        return consumption;
    }

    public void setConsumption(String consumption) {
        this.consumption = consumption;
    }

    public String getBilling() {
        return billing;
    }

    public void setBilling(String billing) {
        this.billing = billing;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }




    public String getSsRolId() {
        return ssRolId;
    }

    public void setSsRolId(String ssRolId) {
        this.ssRolId = ssRolId;
    }
}
