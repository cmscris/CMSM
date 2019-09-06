package com.cris.cmsm.models.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 *
 */

public class SubstationResponseVO {
    @SerializedName("cbcount")
    @Expose
    private String cbcount;
    @SerializedName("transformercount")
    @Expose
    private String transformercount;
    @SerializedName("meteredss")
    @Expose
    private String meteredss;
    @SerializedName("typeofconn")
    @Expose
    private String typeofconn;
    @SerializedName("assest")
    @Expose
    private String assest;
    @SerializedName("assetname")
    @Expose
    private String assetname;
    @SerializedName("nofeeder")
    @Expose
    private String nofeeder;
    @SerializedName("ivolt")
    @Expose
    private String ivolt;
    @SerializedName("load")
    @Expose
    private String load;
    @SerializedName("ctdemand")
    @Expose
    private String ctdemand;
    @SerializedName("seb")
    @Expose
    private String seb;
    @SerializedName("statecode")
    @Expose
    private String statecode;
    @SerializedName("roleid")
    @Expose
    private String roleid;
    @SerializedName("sname")
    @Expose
    private String sname;
    @SerializedName("hqstncode")
    @Expose
    private String hqstncode;
    @SerializedName("location")
    @Expose
    private String location;
    @SerializedName("commisiondate")
    @Expose
    private String commisiondate;
    @SerializedName("cablelenght")
    @Expose
    private String cablelenght;
    @SerializedName("owner")
    @Expose
    private String owner;
    @SerializedName("divcode")
    @Expose
    private String divcode;
    @SerializedName("layout")
    @Expose
    private String layout;
    @SerializedName("roleName")
    @Expose
    private String roleName;
    @SerializedName("rlycode")
    @Expose
    private String rlycode;

    public String getCbcount() {
        return cbcount;
    }

    public void setCbcount(String cbcount) {
        this.cbcount = cbcount;
    }

    public String getTransformercount() {
        return transformercount;
    }

    public void setTransformercount(String transformercount) {
        this.transformercount = transformercount;
    }

    public String getMeteredss() {
        return meteredss;
    }

    public void setMeteredss(String meteredss) {
        this.meteredss = meteredss;
    }

    public String getTypeofconn() {
        return typeofconn;
    }

    public void setTypeofconn(String typeofconn) {
        this.typeofconn = typeofconn;
    }

    public String getAssest() {
        return assest;
    }

    public void setAssest(String assest) {
        this.assest = assest;
    }

    public String getAssetname() {
        return assetname;
    }

    public void setAssetname(String assetname) {
        this.assetname = assetname;
    }

    public String getNofeeder() {
        return nofeeder;
    }

    public void setNofeeder(String nofeeder) {
        this.nofeeder = nofeeder;
    }

    public String getIvolt() {
        return ivolt;
    }

    public void setIvolt(String ivolt) {
        this.ivolt = ivolt;
    }

    public String getLoad() {
        return load;
    }

    public void setLoad(String load) {
        this.load = load;
    }

    public String getCtdemand() {
        return ctdemand;
    }

    public void setCtdemand(String ctdemand) {
        this.ctdemand = ctdemand;
    }

    public String getSeb() {
        return seb;
    }

    public void setSeb(String seb) {
        this.seb = seb;
    }

    public String getStatecode() {
        return statecode;
    }

    public void setStatecode(String statecode) {
        this.statecode = statecode;
    }

    public String getRoleid() {
        return roleid;
    }

    public void setRoleid(String roleid) {
        this.roleid = roleid;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getHqstncode() {
        return hqstncode;
    }

    public void setHqstncode(String hqstncode) {
        this.hqstncode = hqstncode;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCommisiondate() {
        return commisiondate;
    }

    public void setCommisiondate(String commisiondate) {
        this.commisiondate = commisiondate;
    }

    public String getCablelenght() {
        return cablelenght;
    }

    public void setCablelenght(String cablelenght) {
        this.cablelenght = cablelenght;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getDivcode() {
        return divcode;
    }

    public void setDivcode(String divcode) {
        this.divcode = divcode;
    }

    public String getLayout() {
        return layout;
    }

    public void setLayout(String layout) {
        this.layout = layout;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRlycode() {
        return rlycode;
    }

    public void setRlycode(String rlycode) {
        this.rlycode = rlycode;
    }


    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String details;
}
