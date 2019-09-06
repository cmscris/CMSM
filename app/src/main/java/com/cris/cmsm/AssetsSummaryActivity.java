package com.cris.cmsm;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;

import com.cris.cmsm.adapter.AssetsSummaryAdapter;
import com.cris.cmsm.database.DataBaseManager;
import com.cris.cmsm.database.DataHolder;
import com.cris.cmsm.interfaces.OnItemClickListener;
import com.cris.cmsm.models.Division;
import com.cris.cmsm.models.Railway;
import com.cris.cmsm.models.ReportHeaderView;
import com.cris.cmsm.models.request.RequestSSAssets;
import com.cris.cmsm.models.response.AssestReportSummaryVO;
import com.cris.cmsm.models.response.LoginIfoVO;
import com.cris.cmsm.models.response.ResAssetReport;
import com.cris.cmsm.models.response.ResponseAssetDetails;
import com.cris.cmsm.prefrences.UserLoginPreferences;
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

public class AssetsSummaryActivity extends BaseActivity implements OnItemClickListener, ResponseView {

    private PinchRecyclerView recyclerView;
    private DataBaseManager manager;
    private CommonClass commonClass;
    private RequestSSAssets requestSSAssets;
    private LoginIfoVO loginUser;
    private MonthlyRequestPresenter monthlyRequestPresenter;
    private ResAssetReport resAssetReport;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sub_station_report);
        try {
            loginUser = new UserLoginPreferences(AssetsSummaryActivity.this).getLoginUser();
            monthlyRequestPresenter = new MonthlyRequestPresenter(AssetsSummaryActivity.this);
            commonClass = new CommonClass(AssetsSummaryActivity.this);
            manager = new DataBaseManager(AssetsSummaryActivity.this);
            manager.createDatabase();
            recyclerView = findViewById(R.id.recyclerView);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));

            requestSSAssets = DataHolder.getInstance().getRequestSSAssets();
            resAssetReport = DataHolder.getInstance().getResAssetReport();
            List<AssestReportSummaryVO> list = new ArrayList<>(resAssetReport.getAssestReportSummaryVO());

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
            headerView.setMsg(TextUtils.isEmpty(resAssetReport.getLatestDataMsg()) ? ""
                    : resAssetReport.getLatestDataMsg());
            if (list != null && requestSSAssets != null && list.size() > 0) {
                AssestReportSummaryVO assestReportSummaryVO = new AssestReportSummaryVO();
                assestReportSummaryVO.setRailCode("Railway");
                assestReportSummaryVO.setDivision("Division");
                String type = "";
                if (requestSSAssets.getAssestType().equalsIgnoreCase("A21")) {// escalator
                    type = "Escalators";
                } else if (requestSSAssets.getAssestType().equalsIgnoreCase("A22")) { //lift
                    type = "Lift";
                } else if (requestSSAssets.getAssestType().equalsIgnoreCase("A26")) {//office building
                    type = "Solar Panel at Office Building";
                } else if (requestSSAssets.getAssestType().equalsIgnoreCase("A31")) {//street station
                    type = "Solar Panel at Street Station";
                }

                assestReportSummaryVO.setUptoCurrentFinYearNO("No. of " + type + "  installed up to\n 31-Mar-" + splitFinYear(requestSSAssets.getFinYear(), 0));
                assestReportSummaryVO.setInCurrentFinYearNo("No. of " + type + "  installed during current year\n(" + requestSSAssets.getFinYear() + ")");
                assestReportSummaryVO.setTotal("Total " + type + " as on\n(" + CommonClass.currentMonth(requestSSAssets.getMonth()) + "-" + (Integer.valueOf(requestSSAssets.getMonth()) < 4 ? splitFinYear(requestSSAssets.getFinYear(), 1) : splitFinYear(requestSSAssets.getFinYear(), 0)) + ")");
                list.add(0, assestReportSummaryVO);

                headerView.setEnergyConsume("Position of " + type);
                headerView.setMonth("Month : " + CommonClass.currentMonth(requestSSAssets.getMonth()) + "-" + (Integer.valueOf(requestSSAssets.getMonth()) < 4 ? splitFinYear(requestSSAssets.getFinYear(), 1) : splitFinYear(requestSSAssets.getFinYear(), 0)));
                headerView.setSummary("");
                recyclerView.setAdapter(new AssetsSummaryAdapter(AssetsSummaryActivity.this, AssetsSummaryActivity.this, headerView, list));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void OnItemClick(Object model) {
        AssestReportSummaryVO item = (AssestReportSummaryVO) model;
        RequestSSAssets request = new RequestSSAssets();
        request.setRailCode(item.getRailCode());
        request.setDivision(item.getDivision());
        request.setAssestType(requestSSAssets.getAssestType());
        if (TextUtils.isEmpty(resAssetReport.getLatestDataMsg())) {
            request.setFinYear(requestSSAssets.getFinYear());
            request.setMonth(requestSSAssets.getMonth());
        } else {
            request.setFinYear(resAssetReport.getLatestFinyear());
            request.setMonth(resAssetReport.getLatestMonth());
        }
        request.setAuthlevel(loginUser.getAuthlevel());
        request.setRoleId(loginUser.getRoleid());
        monthlyRequestPresenter.Request(request, "Getting Asset Details", Constants.ASSETS_REPORT_DETAILS);
        request.setMsg(resAssetReport.getLatestDataMsg());
        DataHolder.getInstance().setSummaryRequest(request);
    }

    @Override
    public void ResponseOk(Object object, int position) {
        try {
            ResponseAssetDetails details = (ResponseAssetDetails) object;
            DataHolder.getInstance().setSummaryResponse(details);
            CommonClass.goToNextScreen(AssetsSummaryActivity.this, AssetsDetailsActivity.class, true, false);
        } catch (Exception e) {
            e.printStackTrace();
            commonClass.showToast("Unable to fetch record. Please try again.");
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


    private String splitFinYear(String year, int pos) {
        String[] arr = year.split("-");
        return arr[pos];
    }
}
