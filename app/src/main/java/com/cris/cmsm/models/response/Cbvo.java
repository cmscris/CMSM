package com.cris.cmsm.models.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 *
 */

public class Cbvo {

    @SerializedName("cdId")
    @Expose
    private String cdId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("capacity")
    @Expose
    private String capacity;
    @SerializedName("supplier")
    @Expose
    private String supplier;
    @SerializedName("initcost")
    @Expose
    private String initcost;
    @SerializedName("pfold")
    @Expose
    private String pfold;
    @SerializedName("actualCapacity")
    @Expose
    private String actualCapacity;
    @SerializedName("commissiondate")
    @Expose
    private String commissiondate;

    public String getCdId() {
        return cdId;
    }

    public void setCdId(String cdId) {
        this.cdId = cdId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public String getInitcost() {
        return initcost;
    }

    public void setInitcost(String initcost) {
        this.initcost = initcost;
    }

    public String getPfold() {
        return pfold;
    }

    public void setPfold(String pfold) {
        this.pfold = pfold;
    }

    public String getActualCapacity() {
        return actualCapacity;
    }

    public void setActualCapacity(String actualCapacity) {
        this.actualCapacity = actualCapacity;
    }

    public String getCommissiondate() {
        return commissiondate;
    }

    public void setCommissiondate(String commissiondate) {
        this.commissiondate = commissiondate;
    }

}
