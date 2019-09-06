package com.cris.cmsm;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatSpinner;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.cris.cmsm.adapter.DivisonAdapter;
import com.cris.cmsm.adapter.RailwayAdapter;
import com.cris.cmsm.adapter.SSEAdapter;
import com.cris.cmsm.adapter.SubStationAdapter;
import com.cris.cmsm.database.DataBaseManager;
import com.cris.cmsm.database.DataHolder;
import com.cris.cmsm.models.Division;
import com.cris.cmsm.models.Railway;
import com.cris.cmsm.models.request.AssetManagementModel;
import com.cris.cmsm.models.request.RequestSSERole;
import com.cris.cmsm.models.request.SubStationRequest;
import com.cris.cmsm.models.response.LoginIfoVO;
import com.cris.cmsm.models.response.ResSubStationSummartVO;
import com.cris.cmsm.models.response.ResponseSSERole;
import com.cris.cmsm.models.response.SubStation;
import com.cris.cmsm.navcontrollers.SummarySSController;
import com.cris.cmsm.prefrences.UserLoginPreferences;
import com.cris.cmsm.presenter.MonthlyRequestPresenter;
import com.cris.cmsm.presenterview.ResponseView;
import com.cris.cmsm.util.CommonClass;
import com.cris.cmsm.util.Constants;
import com.cris.cmsm.util.FontFamily;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */

public class SubStationAssetsActivity extends BaseActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener, ResponseView {

    private TextView tv_filters, action_bar_title;
    private ImageView iv_title_icon;
    private Button btn_filter;
    private Typeface font;
    private AppCompatSpinner spn_ryCode, spn_divCode, spn_sse, spn_sub_station;
    private LoginIfoVO loginUser;
    private CommonClass commonClass;
    private DataBaseManager dataBaseManager;
    private MonthlyRequestPresenter monthlyRequestPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_substation_assets);
        monthlyRequestPresenter = new MonthlyRequestPresenter(SubStationAssetsActivity.this);
        dataBaseManager = new DataBaseManager(SubStationAssetsActivity.this);
        dataBaseManager.createDatabase();
        commonClass = new CommonClass(SubStationAssetsActivity.this);
        loginUser = new UserLoginPreferences(SubStationAssetsActivity.this).getLoginUser();
        font = new FontFamily(SubStationAssetsActivity.this).getBold();
        iv_title_icon = findViewById(R.id.iv_title_icon);
        tv_filters = findViewById(R.id.tv_filters);
        btn_filter = findViewById(R.id.btn_filter);
        action_bar_title = findViewById(R.id.action_bar_title);

        spn_ryCode = findViewById(R.id.spn_ryCode);
        spn_divCode = findViewById(R.id.spn_divCode);
        spn_sse = findViewById(R.id.spn_sse);
        spn_sub_station = findViewById(R.id.spn_sub_station);

        action_bar_title.setTypeface(font);
        tv_filters.setTypeface(font);
        iv_title_icon.setImageResource(R.drawable.iv_back);


        action_bar_title.setText(getResources().getString(R.string.sub_station_assets));

        List<Railway> railwayList = dataBaseManager.getRailwayList(true);
        spn_ryCode.setAdapter(new RailwayAdapter(SubStationAssetsActivity.this, railwayList));
        spn_divCode.setAdapter(new DivisonAdapter(SubStationAssetsActivity.this, dataBaseManager.getDivisionList("-100")));
        setSseAdapter(new ArrayList<ResponseSSERole>());
        spn_ryCode.setSelection(commonClass.getUserRailwayIndex(railwayList, loginUser.getRlycode() != null ? loginUser.getRlycode() : ""));
        if (!TextUtils.isEmpty(loginUser.getAuthlevel()) && loginUser.getAuthlevel().equalsIgnoreCase(Constants.AUTH_BOARD)) {
            spn_ryCode.setEnabled(true);
        } else {
            spn_ryCode.setEnabled(false);
        }

        iv_title_icon.setOnClickListener(this);
        btn_filter.setOnClickListener(this);

        spn_ryCode.setOnItemSelectedListener(this);
        spn_divCode.setOnItemSelectedListener(this);
        spn_sse.setOnItemSelectedListener(this);
        spn_sub_station.setOnItemSelectedListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_title_icon:
                onBackPressed();
                break;
            case R.id.btn_filter:
                if (CommonClass.checkInternetConnection(SubStationAssetsActivity.this))
                    callTabularReport();
                else
                    commonClass.showToast("No internet available.");
                break;
        }
    }


    private void setSseAdapter(List<ResponseSSERole> list) {
        ResponseSSERole hintModel = new ResponseSSERole();
        hintModel.setName("ALL SSE");
        hintModel.setRoleid("");
        list.add(0, hintModel);
        spn_sse.setAdapter(new SSEAdapter(SubStationAssetsActivity.this, list));
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Object object = parent.getItemAtPosition(position);
        if (object instanceof Railway) {
            Railway railway = (Railway) object;
            List<Division> list = dataBaseManager.getDivisionList(railway.getRlycode());
            spn_divCode.setAdapter(new DivisonAdapter(SubStationAssetsActivity.this, list));
            spn_divCode.setSelection(commonClass.getDivisionIndex(list, loginUser.getDivcode() != null ? loginUser.getDivcode() : ""));
            if (!TextUtils.isEmpty(loginUser.getAuthlevel()) && (loginUser.getAuthlevel().equalsIgnoreCase(Constants.AUTH_BOARD)
                    || loginUser.getAuthlevel().equalsIgnoreCase(Constants.AUTH_RAILWAY))) {
                spn_divCode.setEnabled(true);
            } else {
                spn_divCode.setEnabled(false);
            }
        } else if (object instanceof Division) {
            if (spn_divCode.getSelectedItemPosition() != 0) {
                callWebService();
            } else {
                setSseAdapter(new ArrayList<ResponseSSERole>());
            }
        } else if (object instanceof ResponseSSERole) {
            if (spn_sse.getSelectedItemPosition() != 0) {
                SubStationRequest subStationRequest = new SubStationRequest();
                subStationRequest.setRoleId(((ResponseSSERole) spn_sse.getSelectedItem()).getRoleid());
                monthlyRequestPresenter.Request(subStationRequest, "Getting SubStation", Constants.GET_SUBSTATION);
            } else {
                setSubStationAdapter(new ArrayList<SubStation>());
            }
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


    private void callWebService() {
        RequestSSERole requestSSERole = new RequestSSERole();
        requestSSERole.setAuthLevel(Integer.valueOf(loginUser.getAuthlevel()));
        if (loginUser.getAuthlevel().equalsIgnoreCase(Constants.AUTH_DIVISION) && loginUser.getCategory().equalsIgnoreCase(Constants.CATEGORY_WORKSHOP))
            requestSSERole.setCategory(loginUser.getCategory());
        else
            requestSSERole.setCategory(Constants.CATEGORY_GENERAL_SERVICE);
        requestSSERole.setLoginid(loginUser.getLoginid());
        requestSSERole.setDivcode(((Division) spn_divCode.getSelectedItem()).getDivcode());
        if (((Railway) spn_ryCode.getSelectedItem()).getFlag().equalsIgnoreCase("R"))
            requestSSERole.setHqstnCode("");
        else
            requestSSERole.setHqstnCode(((Railway) spn_ryCode.getSelectedItem()).getRlycode());
        monthlyRequestPresenter.Request(requestSSERole, "Getting SSE Role...", Constants.GET_SSE);

    }

    private void setSubStationAdapter(List<SubStation> list) {
        SubStation subStation = new SubStation();
        subStation.setSubstationId("");
        subStation.setSubstationName("ALL Sub-Station");
        list.add(0, subStation);
        spn_sub_station.setAdapter(new SubStationAdapter(SubStationAssetsActivity.this, list));
    }

    @Override
    public void ResponseOk(Object object, int position) {
        try {
            switch (position) {
                case Constants.GET_SSE:
                    List<ResponseSSERole> list = (List<ResponseSSERole>) object;
                    if (list == null) {
                        commonClass.showToast("No Data Available.");
                        list = new ArrayList<>();
                    }
                    ResponseSSERole hintModel = new ResponseSSERole();
                    hintModel.setName("ALL SSE");
                    hintModel.setRoleid("");
                    list.add(0, hintModel);
                    spn_sse.setAdapter(new SSEAdapter(SubStationAssetsActivity.this, list));
                    if (!TextUtils.isEmpty(loginUser.getRoleid())) {
                        spn_sse.setSelection(commonClass.getSSEIndex(list, loginUser.getRoleid()));
                        spn_sse.setEnabled(false);
                    }
                    break;

                case Constants.GET_SUBSTATION:
                    List<SubStation> subStationList = (List<SubStation>) object;
                    if (subStationList == null) {
                        commonClass.showToast("No Data Available.");
                        subStationList = new ArrayList<>();
                    }
                    SubStation subStation = new SubStation();
                    subStation.setSubstationId("");
                    subStation.setSubstationName("ALL Sub-Station");
                    subStationList.add(0, subStation);
                    spn_sub_station.setAdapter(new SubStationAdapter(SubStationAssetsActivity.this, subStationList));
                    break;
                case Constants.TABULAR_SUMMARY_REPORT:
                    ResSubStationSummartVO tabularReport = (ResSubStationSummartVO) object;
                    if (!TextUtils.isEmpty(tabularReport.getMessage()) && tabularReport.getMessage().equalsIgnoreCase("SUCCESS")) {
                        DataHolder.getInstance().setResSubStationSummartVO(tabularReport);
                        CommonClass.goToNextScreen(SubStationAssetsActivity.this, SummarySSController.class, true, false);
                    } else
                        commonClass.showToast("No data available.");
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
            commonClass.showToast("Unable to fetch data. Please try again.");
        }
    }

    @Override
    public void Error() {
        commonClass.showToast("Unable to fetch data. Please try again.");
    }

    @Override
    public void dismissProgress() {
        commonClass.dismissDialog();
    }

    @Override
    public void showProgress(String msg) {
        commonClass.showProgressBar(msg);
    }


    private void callTabularReport() {
        AssetManagementModel requestSSAssets = new AssetManagementModel();
        requestSSAssets.setRailCode(((Railway) spn_ryCode.getSelectedItem()).getRlycode());
        requestSSAssets.setDivision(((Division) spn_divCode.getSelectedItem()).getDivcode());
        requestSSAssets.setSseIncharge(((ResponseSSERole) spn_sse.getSelectedItem()).getRoleid());
        requestSSAssets.setSubStation(((SubStation) spn_sub_station.getSelectedItem()).getSubstationId());
        monthlyRequestPresenter.Request(requestSSAssets, "Getting Sub Station Assets Details...", Constants.TABULAR_SUMMARY_REPORT);
        requestSSAssets.setSseName(((ResponseSSERole) spn_sse.getSelectedItem()).getName());
        requestSSAssets.setSubstationName(((SubStation) spn_sub_station.getSelectedItem()).getSubstationName());
        DataHolder.getInstance().setAssetManagementModel(requestSSAssets);
    }

}

