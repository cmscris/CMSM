package com.cris.cmsm.models.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 *
 */

public class RequestSSAssets {

    @SerializedName("assestType")
    @Expose
    private String assestType;
    @SerializedName("railCode")
    @Expose
    private String railCode;
    @SerializedName("division")
    @Expose
    private String division;
    @SerializedName("sseIncharge")
    @Expose
    private String sseIncharge;
    @SerializedName("subStation")
    @Expose
    private String subStation;

    @SerializedName("finYear")
    @Expose
    private String finYear;
    @SerializedName("month")
    @Expose
    private String month;
    @SerializedName("authlevel")
    @Expose
    private String authlevel;
    @SerializedName("roleId")
    @Expose
    private String roleId;


    public String getAssestType() {
        return assestType;
    }

    public void setAssestType(String assestType) {
        this.assestType = assestType;
    }

    public String getRailCode() {
        return railCode;
    }

    public void setRailCode(String railCode) {
        this.railCode = railCode;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public String getSseIncharge() {
        return sseIncharge;
    }

    public void setSseIncharge(String sseIncharge) {
        this.sseIncharge = sseIncharge;
    }

    public String getSubStation() {
        return subStation;
    }

    public void setSubStation(String subStation) {
        this.subStation = subStation;
    }


    public String getFinYear() {
        return finYear;
    }

    public void setFinYear(String finYear) {
        this.finYear = finYear;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
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

    public String getAssetsName() {
        return assetsName;
    }

    public void setAssetsName(String assetsName) {
        this.assetsName = assetsName;
    }

    private String assetsName;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    private String msg;
}
