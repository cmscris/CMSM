package com.cris.cmsm.models.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class BillingResponse implements Cloneable {
    @SerializedName("dashboardTotalVOs")
    @Expose
    private List<BillDashboardTotalVO> dashboardTotalVOs = new ArrayList<BillDashboardTotalVO>();
    @SerializedName("dashboardSEBWiseVOs")
    @Expose
    private List<BillDashboardSEBWiseVO> dashboardSEBWiseVOs = new ArrayList<BillDashboardSEBWiseVO>();
    @SerializedName("dashboardStateWiseVOs")
    @Expose
    private List<BillDashboardStateWiseVO> dashboardStateWiseVOs = new ArrayList<BillDashboardStateWiseVO>();
    @SerializedName("dashboardDevisonWiseVOs")
    @Expose
    private List<BillDashboardDevisonWiseVO> dashboardDevisonWiseVOs = new ArrayList<BillDashboardDevisonWiseVO>();

    /**
     * @return The dashboardTotalVOs
     */
    public List<BillDashboardTotalVO> getDashboardTotalVOs() {
        return dashboardTotalVOs;
    }

    /**
     * @param dashboardTotalVOs The dashboardTotalVOs
     */
    public void setDashboardTotalVOs(List<BillDashboardTotalVO> dashboardTotalVOs) {
        this.dashboardTotalVOs = dashboardTotalVOs;
    }

    /**
     * @return The dashboardSEBWiseVOs
     */
    public List<BillDashboardSEBWiseVO> getDashboardSEBWiseVOs() {
        return dashboardSEBWiseVOs;
    }

    /**
     * @param dashboardSEBWiseVOs The dashboardSEBWiseVOs
     */
    public void setDashboardSEBWiseVOs(List<BillDashboardSEBWiseVO> dashboardSEBWiseVOs) {
        this.dashboardSEBWiseVOs = dashboardSEBWiseVOs;
    }

    /**
     * @return The dashboardStateWiseVOs
     */
    public List<BillDashboardStateWiseVO> getDashboardStateWiseVOs() {
        return dashboardStateWiseVOs;
    }

    /**
     * @param dashboardStateWiseVOs The dashboardStateWiseVOs
     */
    public void setDashboardStateWiseVOs(List<BillDashboardStateWiseVO> dashboardStateWiseVOs) {
        this.dashboardStateWiseVOs = dashboardStateWiseVOs;
    }

    /**
     * @return The dashboardDevisonWiseVOs
     */
    public List<BillDashboardDevisonWiseVO> getDashboardDevisonWiseVOs() {
        return dashboardDevisonWiseVOs;
    }

    /**
     * @param dashboardDevisonWiseVOs The dashboardDevisonWiseVOs
     */
    public void setDashboardDevisonWiseVOs(List<BillDashboardDevisonWiseVO> dashboardDevisonWiseVOs) {
        this.dashboardDevisonWiseVOs = dashboardDevisonWiseVOs;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
