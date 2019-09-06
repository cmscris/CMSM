package com.cris.cmsm.navcontrollers;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;

import com.cris.cmsm.BaseActivity;
import com.cris.cmsm.R;
import com.cris.cmsm.adapter.AssetManagementSSAdapter;
import com.cris.cmsm.database.DataHolder;
import com.cris.cmsm.interfaces.OnItemClickListener;
import com.cris.cmsm.models.ReportHeaderView;
import com.cris.cmsm.models.request.AssetManagementModel;
import com.cris.cmsm.models.request.RequestSSAssets;
import com.cris.cmsm.models.response.ResSubStationSummartVO;
import com.cris.cmsm.models.response.ResTabularReport;
import com.cris.cmsm.models.response.SubStationAsssetSummaryVO;
import com.cris.cmsm.presenter.MonthlyRequestPresenter;
import com.cris.cmsm.presenterview.ResponseView;
import com.cris.cmsm.util.CommonClass;
import com.cris.cmsm.util.Constants;
import com.cris.cmsm.widget.PinchRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */

public class SummarySSController extends BaseActivity implements OnItemClickListener, ResponseView {

    private PinchRecyclerView recyclerView;
    private MonthlyRequestPresenter monthlyRequestPresenter;
    private AssetManagementModel ssConsumptionRequest;
    private CommonClass commonClass;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sub_station_report);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        commonClass = new CommonClass(SummarySSController.this);
        monthlyRequestPresenter = new MonthlyRequestPresenter(SummarySSController.this);
        ResSubStationSummartVO resSSConsumption = DataHolder.getInstance().getResSubStationSummartVO();
        if (resSSConsumption != null && resSSConsumption.getSubStationAsssetSummaryVOs() != null) {
            ssConsumptionRequest = DataHolder.getInstance().getAssetManagementModel();
            ReportHeaderView reportHeaderView = new ReportHeaderView();
            reportHeaderView.setEnergyConsume("Railway: " + (ssConsumptionRequest.getRailCode().isEmpty() ? "ALL" : ssConsumptionRequest.getRailCode()) + ", Division: " + (ssConsumptionRequest.getDivision().isEmpty() ? "ALL" : ssConsumptionRequest.getDivision()) + ", SSE Incharge: " + (ssConsumptionRequest.getSseIncharge().isEmpty() ? "ALL" : ssConsumptionRequest.getSseName()) + ",\n Sub-Station: " + (ssConsumptionRequest.getSubStation().isEmpty() ? "ALL" : ssConsumptionRequest.getSubstationName()));
            SubStationAsssetSummaryVO substationResponseVO = new SubStationAsssetSummaryVO();
            substationResponseVO.setRailway("Railway");
            substationResponseVO.setDivison("Division");
            substationResponseVO.setDgset("DGSET");
            substationResponseVO.setTransformer("TRANSFORMER");
            substationResponseVO.setFireExtinguisher("FIRE EXTINGUISHER");
            substationResponseVO.setEarthing("EARTHING");
            substationResponseVO.setApfcPanel("APFC");
            substationResponseVO.setLtPanel("LT PANEL");
            substationResponseVO.setHtPanel("HT PANEL");
            substationResponseVO.setServostabilizer("SERVO STABILIZER");
            List<SubStationAsssetSummaryVO> list = new ArrayList<>(resSSConsumption.getSubStationAsssetSummaryVOs());
            list.add(0, substationResponseVO);
            recyclerView.setAdapter(new AssetManagementSSAdapter(SummarySSController.this, reportHeaderView, SummarySSController.this, list));
        }
    }

    @Override
    public void OnItemClick(Object model) {
        try {
            if (CommonClass.checkInternetConnection(SummarySSController.this)) {
                SubStationAsssetSummaryVO selectedModel = (SubStationAsssetSummaryVO) model;
                RequestSSAssets requestSSAssets = new RequestSSAssets();
                requestSSAssets.setRailCode(selectedModel.getRailway());
                requestSSAssets.setDivision(selectedModel.getDivison());
                requestSSAssets.setAssestType(selectedModel.getAssestType());
                requestSSAssets.setSseIncharge(ssConsumptionRequest.getSseIncharge());
                requestSSAssets.setSubStation(ssConsumptionRequest.getSubStation());
                monthlyRequestPresenter.Request(requestSSAssets, "Getting details...", Constants.TABULAR_REPORT);
                requestSSAssets.setAssetsName(selectedModel.getAssetsName());
                DataHolder.getInstance().setRequestSSAssets(requestSSAssets);
            } else
                commonClass.showToast("No internet available.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void ResponseOk(Object object, int position) {
        switch (position) {
            case Constants.TABULAR_REPORT:
                ResTabularReport resTabularReport = (ResTabularReport) object;
                DataHolder.getInstance().setTabularReport(resTabularReport);
                CommonClass.goToNextScreen(SummarySSController.this, SubStationAssetsReportsController.class, true, false);
                break;
        }
    }

    @Override
    public void Error() {
        commonClass.showToast("Unable to fetch record. Please try again.");
    }

    @Override
    public void dismissProgress() {
        commonClass.dismissDialog();
    }

    @Override
    public void showProgress(String msg) {
        commonClass.showProgressBar(msg);
    }
}
