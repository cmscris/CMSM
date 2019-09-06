package com.cris.cmsm.navcontrollers;

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

import com.cris.cmsm.BaseActivity;
import com.cris.cmsm.R;
import com.cris.cmsm.adapter.DepartmentAdapter;
import com.cris.cmsm.adapter.DivisonAdapter;
import com.cris.cmsm.adapter.RailwayAdapter;
import com.cris.cmsm.adapter.SSEAdapter;
import com.cris.cmsm.database.DataBaseManager;
import com.cris.cmsm.database.DataHolder;
import com.cris.cmsm.models.Department;
import com.cris.cmsm.models.Division;
import com.cris.cmsm.models.Railway;
import com.cris.cmsm.models.request.RequestSSERole;
import com.cris.cmsm.models.request.SSConsumptionRequest;
import com.cris.cmsm.models.response.LoginIfoVO;
import com.cris.cmsm.models.response.ResSSConsumption;
import com.cris.cmsm.models.response.ResponseSSERole;
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

public class SubStationConsController extends BaseActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener, ResponseView {

    private ImageView iv_title_icon;
    private TextView action_bar_title, tv_filters;
    private Typeface font;
    private AppCompatSpinner spn_ryCode, spn_divCode, spn_sse, spn_department;
    private Button btn_filter;
    private CommonClass commonClass;
    private DataBaseManager dataBaseManager;
    private LoginIfoVO loginUser;
    private MonthlyRequestPresenter monthlyRequestPresenter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_station);
        monthlyRequestPresenter = new MonthlyRequestPresenter(SubStationConsController.this);
        loginUser = new UserLoginPreferences(SubStationConsController.this).getLoginUser();
        commonClass = new CommonClass(SubStationConsController.this);
        dataBaseManager = new DataBaseManager(SubStationConsController.this);
        dataBaseManager.createDatabase();

        font = new FontFamily(SubStationConsController.this).getBold();
        spn_ryCode = findViewById(R.id.spn_ryCode);
        spn_divCode = findViewById(R.id.spn_divCode);
        spn_sse = findViewById(R.id.spn_sse);
        spn_department = findViewById(R.id.spn_department);
        btn_filter = findViewById(R.id.btn_filter);

        tv_filters = findViewById(R.id.tv_filters);
        action_bar_title = findViewById(R.id.action_bar_title);
        iv_title_icon = findViewById(R.id.iv_title_icon);
        iv_title_icon.setImageResource(R.drawable.iv_back);
        action_bar_title.setText(getResources().getString(R.string.sub_station_cons));
        tv_filters.setTypeface(font);
        action_bar_title.setTypeface(font);
        tv_filters.setText("Sub-Stations");

        iv_title_icon.setOnClickListener(this);
        btn_filter.setOnClickListener(this);

        List<Railway> railwayList = dataBaseManager.getRailwayList(true);
        spn_ryCode.setAdapter(new RailwayAdapter(SubStationConsController.this, railwayList));
        spn_divCode.setAdapter(new DivisonAdapter(SubStationConsController.this, dataBaseManager.getDivisionList("-100")));
        setSseAdapter(new ArrayList<ResponseSSERole>());
        spn_department.setAdapter(new DepartmentAdapter(SubStationConsController.this, CommonClass.getDepartmentList(loginUser.getAuthlevel(), loginUser.getCategory(), false)));
        spn_ryCode.setSelection(commonClass.getUserRailwayIndex(railwayList, loginUser.getRlycode() != null ? loginUser.getRlycode() : ""));
        if (!TextUtils.isEmpty(loginUser.getAuthlevel()) && loginUser.getAuthlevel().equalsIgnoreCase(Constants.AUTH_BOARD)) {
            spn_ryCode.setEnabled(true);
        } else {
            spn_ryCode.setEnabled(false);
        }

        spn_ryCode.setOnItemSelectedListener(this);
        spn_divCode.setOnItemSelectedListener(this);
        spn_department.setOnItemSelectedListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_title_icon:
                onBackPressed();
                break;
            case R.id.btn_filter:
                if (CommonClass.checkInternetConnection(SubStationConsController.this)) {
                    callWebService();
                } else {
                    commonClass.showToast("No internet available.");
                }
                break;
        }
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Object object = parent.getItemAtPosition(position);
        if (object instanceof Railway) {
            Railway railway = (Railway) object;
            List<Division> list = dataBaseManager.getDivisionList(railway.getRlycode());
            spn_divCode.setAdapter(new DivisonAdapter(SubStationConsController.this, list));
            spn_divCode.setSelection(commonClass.getDivisionIndex(list, loginUser.getDivcode() != null ? loginUser.getDivcode() : ""));
            if (!TextUtils.isEmpty(loginUser.getAuthlevel()) && (loginUser.getAuthlevel().equalsIgnoreCase(Constants.AUTH_BOARD)
                    || loginUser.getAuthlevel().equalsIgnoreCase(Constants.AUTH_RAILWAY))) {
                spn_divCode.setEnabled(true);
            } else {
                spn_divCode.setEnabled(false);
            }
        } else if (object instanceof Division) {
            if (spn_divCode.getSelectedItemPosition() != 0) {
                getSSEs();
            } else {
                setSseAdapter(new ArrayList<ResponseSSERole>());
            }
        } else if (object instanceof Department) {
            if (loginUser.getAuthlevel().equalsIgnoreCase(Constants.AUTH_BOARD)
                    || (loginUser.getAuthlevel().equalsIgnoreCase(Constants.AUTH_RAILWAY)
                    && "R".equalsIgnoreCase(loginUser.getRlytype()))) {
                spn_divCode.setSelection(0);
            }
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private void getSSEs() {
        RequestSSERole requestSSERole = new RequestSSERole();
        requestSSERole.setAuthLevel(Integer.valueOf(loginUser.getAuthlevel()));
        if (loginUser.getAuthlevel().equalsIgnoreCase(Constants.AUTH_DIVISION) && loginUser.getCategory().equalsIgnoreCase(Constants.CATEGORY_WORKSHOP)) {
            requestSSERole.setCategory(loginUser.getCategory());
        } else {
            if (((Department) spn_department.getSelectedItem()).getDepartmentCode().equals("TRD"))
                requestSSERole.setCategory(Constants.CATEGORY_TRANSACTION);
            else
                requestSSERole.setCategory(Constants.CATEGORY_GENERAL_SERVICE);
        }
        requestSSERole.setLoginid(loginUser.getLoginid());
        requestSSERole.setDivcode(((Division) spn_divCode.getSelectedItem()).getDivcode());
        if (((Railway) spn_ryCode.getSelectedItem()).getFlag().equalsIgnoreCase("R"))
            requestSSERole.setHqstnCode("");
        else
            requestSSERole.setHqstnCode(((Railway) spn_ryCode.getSelectedItem()).getRlycode());
        monthlyRequestPresenter.Request(requestSSERole, "Getting SSE Role...", Constants.GET_SSE);
    }


    private void callWebService() {
        SSConsumptionRequest ssConsumptionRequest = new SSConsumptionRequest();
        ssConsumptionRequest.setRlycode(((Railway) spn_ryCode.getSelectedItem()).getRlycode());
        ssConsumptionRequest.setRlyType(((Railway) spn_ryCode.getSelectedItem()).getFlag());
        ssConsumptionRequest.setDivision(((Division) spn_divCode.getSelectedItem()).getDivcode());
        ssConsumptionRequest.setSseincharge(((ResponseSSERole) spn_sse.getSelectedItem()).getRoleid());
        if (((Department) spn_department.getSelectedItem()).getDepartmentCode().equals("GEN"))
            ssConsumptionRequest.setCategory(Constants.CATEGORY_GENERAL_SERVICE);
        else if (((Department) spn_department.getSelectedItem()).getDepartmentCode().equals("TRD"))
            ssConsumptionRequest.setCategory(Constants.CATEGORY_TRANSACTION);
        else
            ssConsumptionRequest.setCategory("");
        monthlyRequestPresenter.Request(ssConsumptionRequest, "Getting Sub Station Report", Constants.SS_CONSUMPTION);
        ssConsumptionRequest.setDepartmentType(((Department) spn_department.getSelectedItem()).getDepartmentName());
        ssConsumptionRequest.setSseName(((ResponseSSERole) spn_sse.getSelectedItem()).getName());
        DataHolder.getInstance().setSsConsumptionRequest(ssConsumptionRequest);
    }

    private void setSseAdapter(List<ResponseSSERole> list) {
        ResponseSSERole hintModel = new ResponseSSERole();
        hintModel.setName("ALL SSE");
        hintModel.setRoleid("");
        list.add(0, hintModel);
        spn_sse.setAdapter(new SSEAdapter(SubStationConsController.this, list));
    }

    @Override
    public void ResponseOk(Object object, int position) {
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
                spn_sse.setAdapter(new SSEAdapter(SubStationConsController.this, list));
                if (!TextUtils.isEmpty(loginUser.getRoleid())) {
                    spn_sse.setSelection(commonClass.getSSEIndex(list, loginUser.getRoleid()));
                    spn_sse.setEnabled(false);
                }
                break;
            case Constants.SS_CONSUMPTION:
                ResSSConsumption resSSConsumption = (ResSSConsumption) object;
                if (resSSConsumption != null && resSSConsumption.getSubstationResponseVO() != null) {
                    DataHolder.getInstance().setResSSConsumption(resSSConsumption);
                    CommonClass.goToNextScreen(SubStationConsController.this, SubStationReportController.class, false, false);
                } else
                    commonClass.showToast("No data available.");
                break;
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
}
