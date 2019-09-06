package com.cris.cmsm.models.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 *
 */

public class ResTabularReport {

    @SerializedName("tabularReportVO")
    @Expose
    private List<TabularReportVO> tabularReportVO = null;
    @SerializedName("message")
    @Expose
    private String message;

    public List<TabularReportVO> getTabularReportVO() {
        return tabularReportVO;
    }

    public void setTabularReportVO(List<TabularReportVO> tabularReportVO) {
        this.tabularReportVO = tabularReportVO;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
