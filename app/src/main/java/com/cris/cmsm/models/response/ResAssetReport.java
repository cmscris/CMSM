package com.cris.cmsm.models.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 *
 */

public class ResAssetReport {

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("assestReportSummaryVO")
    @Expose
    private List<AssestReportSummaryVO> assestReportSummaryVO = null;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<AssestReportSummaryVO> getAssestReportSummaryVO() {
        return assestReportSummaryVO;
    }

    public void setAssestReportSummaryVO(List<AssestReportSummaryVO> assestReportSummaryVO) {
        this.assestReportSummaryVO = assestReportSummaryVO;
    }

    @SerializedName("latestFinyear")
    @Expose
    private String latestFinyear;
    @SerializedName("latestMonth")
    @Expose
    private String latestMonth;
    @SerializedName("latestDataMsg")
    @Expose
    private String latestDataMsg;

    public String getLatestFinyear() {
        return latestFinyear;
    }

    public void setLatestFinyear(String latestFinyear) {
        this.latestFinyear = latestFinyear;
    }

    public String getLatestMonth() {
        return latestMonth;
    }

    public void setLatestMonth(String latestMonth) {
        this.latestMonth = latestMonth;
    }

    public String getLatestDataMsg() {
        return latestDataMsg;
    }

    public void setLatestDataMsg(String latestDataMsg) {
        this.latestDataMsg = latestDataMsg;
    }

}
