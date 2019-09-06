package com.cris.cmsm;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;

import com.cris.cmsm.adapter.BuildingAdapter;
import com.cris.cmsm.adapter.EscalatorAdapter;
import com.cris.cmsm.adapter.LiftAdapter;
import com.cris.cmsm.adapter.SolarStationAdapter;
import com.cris.cmsm.database.DataBaseManager;
import com.cris.cmsm.database.DataHolder;
import com.cris.cmsm.models.Division;
import com.cris.cmsm.models.Railway;
import com.cris.cmsm.models.ReportHeaderView;
import com.cris.cmsm.models.request.RequestSSAssets;
import com.cris.cmsm.models.response.EscalatorsAssetVO;
import com.cris.cmsm.models.response.LiftAssestVO;
import com.cris.cmsm.models.response.ResponseAssetDetails;
import com.cris.cmsm.models.response.SolarPVPanelBldgVO;
import com.cris.cmsm.models.response.SolarPVPanelStationAssetVO;
import com.cris.cmsm.util.CommonClass;
import com.cris.cmsm.widget.PinchRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */

public class AssetsDetailsActivity extends BaseActivity {
    private PinchRecyclerView recyclerView;
    private DataBaseManager manager;
    private RequestSSAssets requestSSAssets;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sub_station_report);
        try {
            manager = new DataBaseManager(AssetsDetailsActivity.this);
            manager.createDatabase();
            recyclerView = findViewById(R.id.recyclerView);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            ResponseAssetDetails responseAssetDetails = DataHolder.getInstance().getSummaryResponse();
            requestSSAssets = DataHolder.getInstance().getSummaryRequest();
            ReportHeaderView headerView = new ReportHeaderView();
            List<Railway> railwayList = manager.getRailwayListByCode(requestSSAssets.getRailCode());
            List<Division> divisionList = manager.getDivisionListByDivCode(requestSSAssets.getDivision());
            if (railwayList.size() > 0)
                headerView.setRailway(railwayList.get(0).getFname());
            else
                headerView.setRailway(getResources().getString(R.string.indian_railway));
            if (railwayList.size() == 0)
                headerView.setZon_div("");
            else if (railwayList.size() > 0 && divisionList.size() > 0)
                headerView.setZon_div(divisionList.get(0).getFname().toUpperCase() + " DIVISION");
            else
                headerView.setZon_div("ALL DIVISIONS");
            headerView.setMonth("Month : " + CommonClass.currentMonth(requestSSAssets.getMonth()) + "-" + (Integer.valueOf(requestSSAssets.getMonth()) < 4 ? splitFinYear(requestSSAssets.getFinYear(), 1) : splitFinYear(requestSSAssets.getFinYear(), 0)));
            headerView.setSummary("");
            headerView.setMsg(TextUtils.isEmpty(requestSSAssets.getMsg()) ? ""
                    : requestSSAssets.getMsg());
            if (responseAssetDetails.getEscalatorsAssetVOs() != null && responseAssetDetails.getEscalatorsAssetVOs().size() > 0) {
                headerView.setEnergyConsume("Position of Escalators");
                EscalatorsAssetVO title = new EscalatorsAssetVO();
                title.setRlyCode("Railway");
                title.setDivison("Division");
                title.setLocation("Location");
                title.setMake("Make");
                title.setYearOfInstallation("Year of Installation");
                title.setModeOfMaintenance("Mode of Maintenance");
                List<EscalatorsAssetVO> list = new ArrayList<>(responseAssetDetails.getEscalatorsAssetVOs());
                list.add(0, title);
                recyclerView.setAdapter(new EscalatorAdapter(AssetsDetailsActivity.this, headerView, list));
            } else if (responseAssetDetails.getLiftAssestVOs() != null && responseAssetDetails.getLiftAssestVOs().size() > 0) {
                headerView.setEnergyConsume("Position of Lift");
                LiftAssestVO title = new LiftAssestVO();
                title.setRlyCode("Railway");
                title.setDivison("Division");
                title.setLocationType("Location Type");
                title.setLocation("Location");
                title.setMake("Make");
                title.setYearOfInstallation("Year of Installation");
                title.setModeOfMaintenance("Mode of Maintenance");
                List<LiftAssestVO> list = new ArrayList<>(responseAssetDetails.getLiftAssestVOs());
                list.add(0, title);
                recyclerView.setAdapter(new LiftAdapter(AssetsDetailsActivity.this, headerView, list));
            } else if (responseAssetDetails.getSolarPVPanelBldgVOs() != null && responseAssetDetails.getSolarPVPanelBldgVOs().size() > 0) {
                headerView.setEnergyConsume("Position of Solar PV panel provided at office building");
                SolarPVPanelBldgVO title = new SolarPVPanelBldgVO();
                title.setRlyCode("Railway");
                title.setDivison("Division");
                title.setState("State");
                title.setNameOfBuilding("Name of Buildings");
                title.setConnectedLoad("Connected load of the building in\n" +
                        "KW");
                title.setCapacity("Capacity of solar PV modules installed in\n" +
                        "WP/KW");
                title.setTypeOfLoad("Type of load and total load connected wth Solar PV module in\n KW.");
                title.setYearOfCommissioning("Year of commissioning");
                title.setAverageCost("Average cost of of solar PV module");
                title.setModeOfMaintenance("Mode of maintenance Departmentally AMC");
                title.setStandBy("Stand by arrangement DG set/Invertor with capacity");
                List<SolarPVPanelBldgVO> list = new ArrayList<>(responseAssetDetails.getSolarPVPanelBldgVOs());
                list.add(0, title);
                recyclerView.setAdapter(new BuildingAdapter(AssetsDetailsActivity.this, headerView, list));
            } else if (responseAssetDetails.getSolarPVPanelStationAssetVOs() != null && responseAssetDetails.getSolarPVPanelStationAssetVOs().size() > 0) {
                headerView.setEnergyConsume("Position of Solar PV Panel Provided at Station");
                SolarPVPanelStationAssetVO title = new SolarPVPanelStationAssetVO();
                title.setRlyCode("Railway");
                title.setDivison("Division");
                title.setState("State");
                title.setNameOfStation("Station");
                title.setConnectedLoad("Connected load of the building in\n" +
                        "KW");
                title.setCapacity("Capacity of solar PV modules installed in\n" +
                        "WP/KW");
                title.setTypeOfLoad("Type of load and total load connected wth Solar PV module in\n KW.");
                title.setYearOfCommissioning("Year of commissioning");
                title.setModeOfMaintenance("Mode of maintenance Departmentally AMC");
                title.setStandBy("Stand by arrangement DG set/Invertor with capacity");
                List<SolarPVPanelStationAssetVO> list = new ArrayList<>(responseAssetDetails.getSolarPVPanelStationAssetVOs());
                list.add(0, title);
                recyclerView.setAdapter(new SolarStationAdapter(AssetsDetailsActivity.this, headerView, list));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    private String splitFinYear(String year, int pos) {
        String[] arr = year.split("-");
        return arr[pos];
    }
}
