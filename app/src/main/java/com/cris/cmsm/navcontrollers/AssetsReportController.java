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

import com.cris.cmsm.AssetsSummaryActivity;
import com.cris.cmsm.BaseActivity;
import com.cris.cmsm.R;
import com.cris.cmsm.adapter.DepartmentAdapter;
import com.cris.cmsm.adapter.DivisonAdapter;
import com.cris.cmsm.adapter.MonthAdapter;
import com.cris.cmsm.adapter.RailwayAdapter;
import com.cris.cmsm.adapter.SpinnerAdapter;
import com.cris.cmsm.database.DataBaseManager;
import com.cris.cmsm.database.DataHolder;
import com.cris.cmsm.models.Department;
import com.cris.cmsm.models.Division;
import com.cris.cmsm.models.Month;
import com.cris.cmsm.models.Railway;
import com.cris.cmsm.models.request.RequestSSAssets;
import com.cris.cmsm.models.response.LoginIfoVO;
import com.cris.cmsm.models.response.ResAssetReport;
import com.cris.cmsm.prefrences.UserLoginPreferences;
import com.cris.cmsm.presenter.MonthlyRequestPresenter;
import com.cris.cmsm.presenterview.ResponseView;
import com.cris.cmsm.util.CommonClass;
import com.cris.cmsm.util.Constants;
import com.cris.cmsm.util.FontFamily;

import java.util.List;

/**
 *
 */

public class AssetsReportController extends BaseActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener, ResponseView {

    private TextView tv_filters, action_bar_title;
    private ImageView iv_title_icon;
    private AppCompatSpinner spn_ryCode, spn_divCode, spn_finY, spn_month, spn_asset_type;
    private Button btn_filter;
    private DataBaseManager dataBaseManager;
    private LoginIfoVO loginUser;
    private CommonClass commonClass;
    private Typeface fontBold;
    private MonthlyRequestPresenter monthlyRequestPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_substation_assets);
        FontFamily fontFamily = new FontFamily(AssetsReportController.this);
        fontBold = fontFamily.getBold();
        monthlyRequestPresenter = new MonthlyRequestPresenter(AssetsReportController.this);
        commonClass = new CommonClass(AssetsReportController.this);
        loginUser = new UserLoginPreferences(AssetsReportController.this).getLoginUser();
        dataBaseManager = new DataBaseManager(AssetsReportController.this);
        dataBaseManager.createDatabase();

        iv_title_icon = findViewById(R.id.iv_title_icon);
        action_bar_title = findViewById(R.id.action_bar_title);
        tv_filters = findViewById(R.id.tv_filters);

        spn_ryCode = findViewById(R.id.spn_ryCode);
        spn_divCode = findViewById(R.id.spn_divCode);
        spn_finY = findViewById(R.id.spn_sse);
        spn_month = findViewById(R.id.spn_sub_station);
        spn_asset_type = findViewById(R.id.spn_asset_type);
        spn_asset_type.setVisibility(View.VISIBLE);

        btn_filter = findViewById(R.id.btn_filter);
        iv_title_icon.setImageResource(R.drawable.iv_back);


        List<Railway> railwayList = dataBaseManager.getRailwayList(true);
        spn_ryCode.setAdapter(new RailwayAdapter(AssetsReportController.this, railwayList));
        spn_finY.setAdapter(new SpinnerAdapter(AssetsReportController.this, CommonClass.getFinancialYear()));
        spn_month.setAdapter(new MonthAdapter(AssetsReportController.this, CommonClass.getMonthsList()));
        spn_divCode.setAdapter(new DivisonAdapter(AssetsReportController.this, dataBaseManager.getDivisionList("-100")));
        spn_asset_type.setAdapter(new DepartmentAdapter(AssetsReportController.this, CommonClass.getAssetsReportList()));


        spn_ryCode.setSelection(commonClass.getUserRailwayIndex(railwayList, loginUser.getRlycode() != null ? loginUser.getRlycode() : ""));
        if (!TextUtils.isEmpty(loginUser.getAuthlevel()) && loginUser.getAuthlevel().equals(Constants.AUTH_BOARD))
            spn_ryCode.setEnabled(true);
        else
            spn_ryCode.setEnabled(false);

        spn_ryCode.setOnItemSelectedListener(this);

        action_bar_title.setText(getResources().getString(R.string.assets_report));
        tv_filters.setText(getResources().getString(R.string.assets_report));
        action_bar_title.setTypeface(fontBold);
        tv_filters.setTypeface(fontBold);
        btn_filter.setTypeface(fontBold);
        iv_title_icon.setOnClickListener(this);
        btn_filter.setOnClickListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Object object = parent.getItemAtPosition(position);
        if (object instanceof Railway) {
            Railway railway = (Railway) object;
            List<Division> list = dataBaseManager.getDivisionList(railway.getRlycode());
            spn_divCode.setAdapter(new DivisonAdapter(AssetsReportController.this, list));
            spn_divCode.setSelection(commonClass.getDivisionIndex(list, loginUser.getDivcode() != null ? loginUser.getDivcode() : ""));
            if (!TextUtils.isEmpty(loginUser.getAuthlevel()) && (loginUser.getAuthlevel().equalsIgnoreCase(Constants.AUTH_BOARD)
                    || loginUser.getAuthlevel().equalsIgnoreCase(Constants.AUTH_RAILWAY))) {
                spn_divCode.setEnabled(true);
            } else {
                spn_divCode.setEnabled(false);
            }
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_title_icon:
                onBackPressed();
                break;
            case R.id.btn_filter:
                if (((Department) spn_asset_type.getSelectedItem()).getDepartmentCode().isEmpty()) {
                    commonClass.showToast("Please select asset type.");
                } else {
                    RequestSSAssets assets = new RequestSSAssets();
                    assets.setAssestType(((Department) spn_asset_type.getSelectedItem()).getDepartmentCode());
                    assets.setRailCode(((Railway) spn_ryCode.getSelectedItem()).getRlycode());
                    assets.setDivision(((Division) spn_divCode.getSelectedItem()).getDivcode());
                    assets.setFinYear((String) spn_finY.getSelectedItem());
                    assets.setMonth(((Month) spn_month.getSelectedItem()).getMonthCode());
                    assets.setAuthlevel(loginUser.getAuthlevel());
                    assets.setRoleId(loginUser.getRoleid());
                    monthlyRequestPresenter.Request(assets, "Getting Asset Summary", Constants.ASSETS_REPORT_SUMMARY);
                    DataHolder.getInstance().setRequestSSAssets(assets);
                }
                break;
        }
    }


    @Override
    public void ResponseOk(Object object, int position) {
        if (object instanceof ResAssetReport) {
            ResAssetReport resAssetReport = (ResAssetReport) object;
            if (resAssetReport.getAssestReportSummaryVO() != null && resAssetReport.getAssestReportSummaryVO().size() > 0) {
                DataHolder.getInstance().setResAssetReport(resAssetReport);
                CommonClass.goToNextScreen(AssetsReportController.this, AssetsSummaryActivity.class, true, false);
            } else
                commonClass.showToast("No data available.");
        } else
            commonClass.showToast("Unable to fetch data. Please try again.");
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
