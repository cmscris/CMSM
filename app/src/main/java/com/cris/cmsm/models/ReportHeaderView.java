package com.cris.cmsm.models;

/**
 *
 */
public class ReportHeaderView {
    public String getRailway() {
        return railway;
    }

    public void setRailway(String railway) {
        this.railway = railway;
    }

    public String getZon_div() {
        return zon_div;
    }

    public void setZon_div(String zon_div) {
        this.zon_div = zon_div;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getEnergyConsume() {
        return energyConsume;
    }

    public void setEnergyConsume(String energyConsume) {
        this.energyConsume = energyConsume;
    }

    String railway;
    String zon_div;
    String month;
    String energyConsume;

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    String summary = "";

    public String getTitleOne() {
        return titleOne;
    }

    public void setTitleOne(String titleOne) {
        this.titleOne = titleOne;
    }

    public String getTitleTwo() {
        return titleTwo;
    }

    public void setTitleTwo(String titleTwo) {
        this.titleTwo = titleTwo;
    }

    public String getTitleThree() {
        return titleThree;
    }

    public void setTitleThree(String titleThree) {
        this.titleThree = titleThree;
    }

    String titleOne;
    String titleTwo;
    String titleThree;

    public String getTitleFour() {
        return titleFour;
    }

    public void setTitleFour(String titleFour) {
        this.titleFour = titleFour;
    }

    String titleFour;

    public String getTitleFive() {
        return titleFive;
    }

    public void setTitleFive(String titleFive) {
        this.titleFive = titleFive;
    }

    String titleFive;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    String msg;

}
