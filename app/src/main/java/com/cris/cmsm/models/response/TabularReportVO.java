package com.cris.cmsm.models.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 *
 */

public class TabularReportVO {

    @SerializedName("railway")
    @Expose
    private String railway;
    @SerializedName("divison")
    @Expose
    private String divison;
    @SerializedName("sse_incharge")
    @Expose
    private String sseIncharge;
    @SerializedName("substation")
    @Expose
    private String substation;
    @SerializedName("assestname")
    @Expose
    private String assestname;
    @SerializedName("rating")
    @Expose
    private String rating;
    @SerializedName("make")
    @Expose
    private String make;
    @SerializedName("capacity")
    @Expose
    private String capacity;
    @SerializedName("dateofmanufacturing")
    @Expose
    private String dateofmanufacturing;
    @SerializedName("location")
    @Expose
    private String location;
    @SerializedName("htPannelChargerVOs")
    @Expose
    private List<HtPannelChargerVO> htPannelChargerVOs = null;
    @SerializedName("feederResponseVO")
    @Expose
    private FeederResponseVO feederResponseVO;
    @SerializedName("costOfFaquis")
    @Expose
    private String costOfFaquis;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("inpoutVoltageFrom")
    @Expose
    private String inpoutVoltageFrom;
    @SerializedName("inputVoltageTo")
    @Expose
    private String inputVoltageTo;
    @SerializedName("outputVoltageFrom")
    @Expose
    private String outputVoltageFrom;
    @SerializedName("outPutVoltageTo")
    @Expose
    private String outPutVoltageTo;

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

    public String getSseIncharge() {
        return sseIncharge;
    }

    public void setSseIncharge(String sseIncharge) {
        this.sseIncharge = sseIncharge;
    }

    public String getSubstation() {
        return substation;
    }

    public void setSubstation(String substation) {
        this.substation = substation;
    }

    public String getAssestname() {
        return assestname;
    }

    public void setAssestname(String assestname) {
        this.assestname = assestname;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public String getDateofmanufacturing() {
        return dateofmanufacturing;
    }

    public void setDateofmanufacturing(String dateofmanufacturing) {
        this.dateofmanufacturing = dateofmanufacturing;
    }

    public Object getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<HtPannelChargerVO> getHtPannelChargerVOs() {
        return htPannelChargerVOs;
    }

    public void setHtPannelChargerVOs(List<HtPannelChargerVO> htPannelChargerVOs) {
        this.htPannelChargerVOs = htPannelChargerVOs;
    }

    public FeederResponseVO getFeederResponseVO() {
        return feederResponseVO;
    }

    public void setFeederResponseVO(FeederResponseVO feederResponseVO) {
        this.feederResponseVO = feederResponseVO;
    }

    public String getCostOfFaquis() {
        return costOfFaquis;
    }

    public void setCostOfFaquis(String costOfFaquis) {
        this.costOfFaquis = costOfFaquis;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Object getInpoutVoltageFrom() {
        return inpoutVoltageFrom;
    }

    public void setInpoutVoltageFrom(String inpoutVoltageFrom) {
        this.inpoutVoltageFrom = inpoutVoltageFrom;
    }

    public String getInputVoltageTo() {
        return inputVoltageTo;
    }

    public void setInputVoltageTo(String inputVoltageTo) {
        this.inputVoltageTo = inputVoltageTo;
    }

    public String getOutputVoltageFrom() {
        return outputVoltageFrom;
    }

    public void setOutputVoltageFrom(String outputVoltageFrom) {
        this.outputVoltageFrom = outputVoltageFrom;
    }

    public Object getOutPutVoltageTo() {
        return outPutVoltageTo;
    }

    public void setOutPutVoltageTo(String outPutVoltageTo) {
        this.outPutVoltageTo = outPutVoltageTo;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    private String details;

}
