package com.cris.cmsm.models.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class ConsumptionResponse implements Cloneable {
    @SerializedName("dashboardTotalVOs")
    @Expose
    private List<DashboardTotalVO> dashboardTotalVOs = new ArrayList<DashboardTotalVO>();
    @SerializedName("dashboardSEBWiseVOs")
    @Expose
    private List<DashboardSEBWiseVO> dashboardSEBWiseVOs = new ArrayList<DashboardSEBWiseVO>();
    @SerializedName("dashboardStateWiseVOs")
    @Expose
    private List<DashboardStateWiseVO> dashboardStateWiseVOs = new ArrayList<DashboardStateWiseVO>();
    @SerializedName("dashboardDevisonWiseVOs")
    @Expose
    private List<DashboardDevisonWiseVO> dashboardDevisonWiseVOs = new ArrayList<DashboardDevisonWiseVO>();

    /**
     * @return The dashboardTotalVOs
     */
    public List<DashboardTotalVO> getDashboardTotalVOs() {
        return dashboardTotalVOs;
    }

    /**
     * @param dashboardTotalVOs The dashboardTotalVOs
     */
    public void setDashboardTotalVOs(List<DashboardTotalVO> dashboardTotalVOs) {
        this.dashboardTotalVOs = dashboardTotalVOs;
    }

    /**
     * @return The dashboardSEBWiseVOs
     */
    public List<DashboardSEBWiseVO> getDashboardSEBWiseVOs() {
        return dashboardSEBWiseVOs;
    }

    /**
     * @param dashboardSEBWiseVOs The dashboardSEBWiseVOs
     */
    public void setDashboardSEBWiseVOs(List<DashboardSEBWiseVO> dashboardSEBWiseVOs) {
        this.dashboardSEBWiseVOs = dashboardSEBWiseVOs;
    }

    /**
     * @return The dashboardStateWiseVOs
     */
    public List<DashboardStateWiseVO> getDashboardStateWiseVOs() {
        return dashboardStateWiseVOs;
    }

    /**
     * @param dashboardStateWiseVOs The dashboardStateWiseVOs
     */
    public void setDashboardStateWiseVOs(List<DashboardStateWiseVO> dashboardStateWiseVOs) {
        this.dashboardStateWiseVOs = dashboardStateWiseVOs;
    }

    /**
     * @return The dashboardDevisonWiseVOs
     */
    public List<DashboardDevisonWiseVO> getDashboardDevisonWiseVOs() {
        return dashboardDevisonWiseVOs;
    }

    /**
     * @param dashboardDevisonWiseVOs The dashboardDevisonWiseVOs
     */
    public void setDashboardDevisonWiseVOs(List<DashboardDevisonWiseVO> dashboardDevisonWiseVOs) {
        this.dashboardDevisonWiseVOs = dashboardDevisonWiseVOs;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
