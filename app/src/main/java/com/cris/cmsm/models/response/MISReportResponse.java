package com.cris.cmsm.models.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class MISReportResponse {

    @SerializedName("annexureReportVOs")
    @Expose
    private List<AnnexureReportVO> annexureReportVOs = new ArrayList<AnnexureReportVO>();
    @SerializedName("summary")
    @Expose
    private String summary;

    /**
     * @return The summary
     */
    public String getSummary() {
        return summary;
    }

    /**
     * @param summary The summary
     */
    public void setSummary(String summary) {
        this.summary = summary;
    }

    /**
     * @return The annexureReportVOs
     */
    public List<AnnexureReportVO> getAnnexureReportVOs() {
        return annexureReportVOs;
    }

    /**
     * @param annexureReportVOs The annexureReportVOs
     */
    public void setAnnexureReportVOs(List<AnnexureReportVO> annexureReportVOs) {
        this.annexureReportVOs = annexureReportVOs;
    }
}
