package com.cris.cmsm.models.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 *
 */

public class SSConsumptionRequest {

    @SerializedName("rlyType")
    @Expose
    private String rlyType;
    @SerializedName("category")
    @Expose
    private String category;
    @SerializedName("division")
    @Expose
    private String division;
    @SerializedName("rlycode")
    @Expose
    private String rlycode;
    @SerializedName("sseincharge")
    @Expose
    private String sseincharge;

    public String getRlyType() {
        return rlyType;
    }

    public void setRlyType(String rlyType) {
        this.rlyType = rlyType;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public String getRlycode() {
        return rlycode;
    }

    public void setRlycode(String rlycode) {
        this.rlycode = rlycode;
    }

    public String getSseincharge() {
        return sseincharge;
    }

    public void setSseincharge(String sseincharge) {
        this.sseincharge = sseincharge;
    }


    public String getDepartmentType() {
        return departmentType;
    }

    public void setDepartmentType(String departmentType) {
        this.departmentType = departmentType;
    }

    String departmentType;

    public String getSseName() {
        return sseName;
    }

    public void setSseName(String sseName) {
        this.sseName = sseName;
    }

    String sseName;
}
