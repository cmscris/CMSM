package com.cris.cmsm.models.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 *
 */

public class AssestReportSummaryVO {

    @SerializedName("railCode")
    @Expose
    private String railCode;
    @SerializedName("division")
    @Expose
    private String division;
    @SerializedName("inCurrentFinYearNo")
    @Expose
    private String inCurrentFinYearNo;
    @SerializedName("uptoCurrentFinYearNO")
    @Expose
    private String uptoCurrentFinYearNO;
    @SerializedName("total")
    @Expose
    private String total;

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

    public String getInCurrentFinYearNo() {
        return inCurrentFinYearNo;
    }

    public void setInCurrentFinYearNo(String inCurrentFinYearNo) {
        this.inCurrentFinYearNo = inCurrentFinYearNo;
    }

    public String getUptoCurrentFinYearNO() {
        return uptoCurrentFinYearNO;
    }

    public void setUptoCurrentFinYearNO(String uptoCurrentFinYearNO) {
        this.uptoCurrentFinYearNO = uptoCurrentFinYearNO;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }


}
