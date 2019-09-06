package com.cris.cmsm.models.response;

/**
 *
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LtConnectionVO {

    @SerializedName("annexureId")
    @Expose
    private String annexureId;
    @SerializedName("roleName")
    @Expose
    private String roleName;
    @SerializedName("ssroleId")
    @Expose
    private String ssroleId;
    @SerializedName("ssseb")
    @Expose
    private String ssseb;
    @SerializedName("consumption")
    @Expose
    private String consumption;
    @SerializedName("billing")
    @Expose
    private String billing;
    @SerializedName("average")
    @Expose
    private String average;
    @SerializedName("nofeeder")
    @Expose
    private String nofeeder;
    @SerializedName("load")
    @Expose
    private String load;
    @SerializedName("remarks")
    @Expose
    private String remarks;
    @SerializedName("sebName")
    @Expose
    private String sebName;

    public String getAnnexureId() {
        return annexureId;
    }

    public void setAnnexureId(String annexureId) {
        this.annexureId = annexureId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getSsroleId() {
        return ssroleId;
    }

    public void setSsroleId(String ssroleId) {
        this.ssroleId = ssroleId;
    }

    public String getSsseb() {
        return ssseb;
    }

    public void setSsseb(String ssseb) {
        this.ssseb = ssseb;
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

    public String getAverage() {
        return average;
    }

    public void setAverage(String average) {
        this.average = average;
    }

    public String getNofeeder() {
        return nofeeder;
    }

    public void setNofeeder(String nofeeder) {
        this.nofeeder = nofeeder;
    }

    public String getLoad() {
        return load;
    }

    public void setLoad(String load) {
        this.load = load;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getSebName() {
        return sebName;
    }

    public void setSebName(String sebName) {
        this.sebName = sebName;
    }

}