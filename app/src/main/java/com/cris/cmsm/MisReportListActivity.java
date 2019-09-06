package com.cris.cmsm;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatSpinner;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.cris.cmsm.adapter.DivisonAdapter;
import com.cris.cmsm.adapter.KeyValueAdapter;
import com.cris.cmsm.adapter.MISAdapter;
import com.cris.cmsm.adapter.MonthAdapter;
import com.cris.cmsm.adapter.RailwayAdapter;
import com.cris.cmsm.adapter.SpinnerAdapter;
import com.cris.cmsm.database.DataBaseManager;
import com.cris.cmsm.database.DataHolder;
import com.cris.cmsm.models.Division;
import com.cris.cmsm.models.KeyValue;
import com.cris.cmsm.models.Month;
import com.cris.cmsm.models.Railway;
import com.cris.cmsm.models.ReportTitleModel;
import com.cris.cmsm.models.request.MISReportRequest;
import com.cris.cmsm.models.response.Annexure14CRes;
import com.cris.cmsm.models.response.LoginIfoVO;
import com.cris.cmsm.models.response.MISReportResponse;
import com.cris.cmsm.models.response.RB1Response;
import com.cris.cmsm.models.response.ResAnnexure1;
import com.cris.cmsm.models.response.ResAnnexure10;
import com.cris.cmsm.models.response.ResAnnexure11;
import com.cris.cmsm.models.response.ResAnnexure14A;
import com.cris.cmsm.models.response.ResAnnexure14B;
import com.cris.cmsm.models.response.ResAnnexure14D;
import com.cris.cmsm.models.response.ResAnnexure3;
import com.cris.cmsm.models.response.ResAnnexure5;
import com.cris.cmsm.models.response.ResAnnexure6;
import com.cris.cmsm.models.response.ResAnnexure7;
import com.cris.cmsm.models.response.ResAnnexure8;
import com.cris.cmsm.models.response.ResAnnexure9;
import com.cris.cmsm.models.response.ResAnnexureRB2;
import com.cris.cmsm.models.response.ResAnnexureRB3;
import com.cris.cmsm.models.response.ResAnnexureRB4;
import com.cris.cmsm.models.response.ResAnnexureRB5;
import com.cris.cmsm.models.response.ResAnnexureRB6;
import com.cris.cmsm.models.response.ResAnnexureRB7;
import com.cris.cmsm.prefrences.UserLoginPreferences;
import com.cris.cmsm.presenter.RequestPresenter;
import com.cris.cmsm.presenterview.ResponseView;
import com.cris.cmsm.util.CommonClass;
import com.cris.cmsm.util.Constants;
import com.cris.cmsm.util.FontFamily;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class MisReportListActivity extends BaseActivity implements ResponseView, View.OnClickListener, AdapterView.OnItemSelectedListener {

    private AppCompatSpinner spn_ryCode, spn_divCode, spn_fYear, spn_month, spn_no_of_year;
    private RequestPresenter requestPresenter;
    private CommonClass commonClass;
    private Button btn_get_report;
    private DataBaseManager dataBaseManager;
    private TextView tv_annexure, tv_annexure_subheading;
    private Typeface font;
    private LoginIfoVO loginUser;
    private ListView lv_mis_reports;
    private Dialog dview = null;
    private int pos = 0;
    private ImageView iv_title_icon;
    private TextView action_bar_title;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_mis_report);
        loginUser = new UserLoginPreferences(MisReportListActivity.this).getLoginUser();
        requestPresenter = new RequestPresenter(MisReportListActivity.this);
        commonClass = new CommonClass(MisReportListActivity.this);
        dataBaseManager = new DataBaseManager(MisReportListActivity.this);
        dataBaseManager.createDatabase();
        font = new FontFamily(MisReportListActivity.this).getBold();

        iv_title_icon = findViewById(R.id.iv_title_icon);
        action_bar_title = findViewById(R.id.action_bar_title);
        lv_mis_reports = findViewById(R.id.lv_mis_reports);

        action_bar_title.setTypeface(font);
        iv_title_icon.setImageResource(R.drawable.iv_back);
        iv_title_icon.setOnClickListener(this);

        Intent intent = getIntent();
        int posValue = intent.getIntExtra(Constants.KEY, -1);
        lv_mis_reports.setAdapter(new MISAdapter(MisReportListActivity.this, addMisMenuList(posValue)));
        lv_mis_reports.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ReportTitleModel title = (ReportTitleModel) parent.getItemAtPosition(position);
                pos = title.getPosition();
                showDialog(title.getTitle(), title.getSubheading());
            }
        });
    }

    private List<ReportTitleModel> addMisMenuList(int itemPos) {
        List<ReportTitleModel> list = new ArrayList<>();
        Resources resources = getResources();
        if (itemPos == Constants.TRACTION) {
            action_bar_title.setText(resources.getString(R.string.mis_traction));
            if (TextUtils.isEmpty(loginUser.getRoleid())) {
                list.add(new ReportTitleModel("(Average Cost/Unit of Traction Energy for various SEBs/Power Supply authorities)", "", 8));
                list.add(new ReportTitleModel("(Energy charges alongwith surcharge paid Raillway wise)", "", 9));
                list.add(new ReportTitleModel("(Energy Consumed Rly. wise)", "", 0));
                list.add(new ReportTitleModel("(Energy Charges Railway-SEB wise)", "", 1));
                list.add(new ReportTitleModel("(Energy Charges alongwith surcharge paid SEB wise)", "", 13));
                list.add(new ReportTitleModel("(Energy charges alongwith surcharge paid Railway-SEB wise)", "", 10));
                list.add(new ReportTitleModel("(Energy charges alongwith surcharge paid State-Railway wise)", "", 11));
                list.add(new ReportTitleModel("(Railway wise details of Energy Charges)", "", 20));
                list.add(new ReportTitleModel("(Details Of Energy Charges Paid By Railways)", "", 12));
            }
        } else if (itemPos == Constants.NONTRACTION) {
            action_bar_title.setText(resources.getString(R.string.mis_nontraction));
            if (TextUtils.isEmpty(loginUser.getRoleid())) {
                list.add(new ReportTitleModel("(Energy Consumed Rly. wise)", "", 2));
                list.add(new ReportTitleModel("(Energy Consumed SEBs wise)", "", 3));
                if (loginUser.getAuthlevel().equalsIgnoreCase(Constants.AUTH_PU) &&
                        !TextUtils.isEmpty(loginUser.getRlytype()) &&
                        loginUser.getRlytype().equalsIgnoreCase("P")) {
                    // blank for pu
                } else
                    list.add(new ReportTitleModel("(Energy Consumed Division & SEBs wise)", "", 4));
                list.add(new ReportTitleModel("(Energy Consumed Year wise)", "", 5));
                list.add(new ReportTitleModel("(Yearly Average Cost Comparison- SEB wise)", "", 6));
            }
        } else if (itemPos == Constants.PLANNING) {
            action_bar_title.setText(resources.getString(R.string.mis_planning));
            if (TextUtils.isEmpty(loginUser.getRoleid())) {
                list.add(new ReportTitleModel("Traction/Non-Traction Energy Consumption", "", 7));
                list.add(new ReportTitleModel("Railway wise (Traction/Non-Traction) Electrical Energy charges & unit rate in Last 2 Years", "", 14));
                list.add(new ReportTitleModel("Summary of Electricity Consumption Traction and Non-Traction", "", 15));
                list.add(new ReportTitleModel("Energy Charges paid SEB wise for Traction", "", 18));
                list.add(new ReportTitleModel("Railway wise (Traction) Energy Consumption in Last 5 Years", "", 16));
                list.add(new ReportTitleModel("Railway wise (Non-Traction) Energy Consumption in Last 5 Years", "", 17));
                list.add(new ReportTitleModel("Division wise (Traction/Non-Traction) Energy Consumption", "", 19));
            }
        }
        return list;
    }


    private void showDialog(String annex_value, String annex_sub) {
        if (Build.VERSION.SDK_INT >= 21) {
            dview = new Dialog(MisReportListActivity.this, android.R.style.Theme_Material_Light_Dialog_NoActionBar);
        } else {
            dview = new Dialog(MisReportListActivity.this);
        }
        dview.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dview.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dview.setCanceledOnTouchOutside(false);
        dview.setContentView(R.layout.dialog_mis_report);

        tv_annexure = dview.findViewById(R.id.tv_annexure);
        tv_annexure_subheading = dview.findViewById(R.id.tv_annexure_subheading);
        tv_annexure_subheading.setVisibility(View.VISIBLE);
        spn_ryCode = dview.findViewById(R.id.spn_ryCode);
        spn_divCode = dview.findViewById(R.id.spn_divCode);
        spn_fYear = dview.findViewById(R.id.spn_fYear);
        spn_month = dview.findViewById(R.id.spn_month);
        spn_no_of_year = dview.findViewById(R.id.spn_no_of_year);
        btn_get_report = dview.findViewById(R.id.btn_get_report);

        /********* Spinner Adapters ********/
        List<Railway> railwayList = null;
        if (loginUser.getAuthlevel().equals(Constants.AUTH_BOARD)) {
            railwayList = dataBaseManager.getRailwayList(true);
        } else {
            railwayList = dataBaseManager.getRailwayList(false);
        }
        spn_ryCode.setAdapter(new RailwayAdapter(MisReportListActivity.this, railwayList));
        spn_divCode.setAdapter(new DivisonAdapter(MisReportListActivity.this, dataBaseManager.getDivisionList(loginUser.getRlycode())));
        spn_month.setAdapter(new MonthAdapter(MisReportListActivity.this, CommonClass.getMonthList()));
        spn_fYear.setAdapter(new SpinnerAdapter(MisReportListActivity.this, CommonClass.getFinancialYear()));
        //spn_no_of_year.setAdapter(new KeyValueAdapter(MisReportListActivity.this, CommonClass.getNoOfYear()));
        spn_ryCode.setSelection(commonClass.getUserRailwayIndex(railwayList, loginUser.getRlycode() != null ? loginUser.getRlycode() : ""));
        if (!TextUtils.isEmpty(loginUser.getAuthlevel()) && loginUser.getAuthlevel().equals(Constants.AUTH_BOARD))
            spn_ryCode.setEnabled(true);
        else
            spn_ryCode.setEnabled(false);

        if (pos == 15)
            spn_no_of_year.setVisibility(View.VISIBLE);
        else
            spn_no_of_year.setVisibility(View.GONE);

        tv_annexure.setTypeface(font);
        tv_annexure_subheading.setTypeface(new FontFamily(MisReportListActivity.this).getRegular());
        tv_annexure.setText(annex_value);
        tv_annexure_subheading.setText(annex_sub);
        btn_get_report.setTypeface(font);
        btn_get_report.setOnClickListener(this);
        spn_ryCode.setOnItemSelectedListener(this);
        dview.show();
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Object object = parent.getItemAtPosition(position);
        if (object instanceof Railway) {
            Railway railway = (Railway) object;
            List<Division> list = dataBaseManager.getDivisionList(railway.getRlycode());
            spn_divCode.setAdapter(new DivisonAdapter(MisReportListActivity.this, list));
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
    public void ResponseOk(Object object, int position) {
        try {
            if (object instanceof MISReportResponse) {
                MISReportResponse misResponse = (MISReportResponse) object;
                if (misResponse != null && misResponse.getAnnexureReportVOs().size() > 0) {
                    DataHolder.getInstance().setSelectedMistYear((String) spn_fYear.getSelectedItem());
                    DataHolder.getInstance().setMisResponse(misResponse);
                    CommonClass.goToNextScreen(MisReportListActivity.this, DetailMISReport.class, false, false);
                } else
                    commonClass.showToast("No Data Available.");
            } else if (object instanceof RB1Response) {
                RB1Response misResponse = (RB1Response) object;
                if (misResponse != null && misResponse.getIsSuccess()) {
                    DataHolder.getInstance().setSelectedMistYear((String) spn_fYear.getSelectedItem());
                    DataHolder.getInstance().setMisResponse(misResponse);
                    CommonClass.goToNextScreen(MisReportListActivity.this, DetailMISReport.class, false, false);
                } else
                    commonClass.showToast("No Data Available.");
            } else if (object instanceof ResAnnexure14A) {
                ResAnnexure14A misResponse = (ResAnnexure14A) object;
                if (misResponse != null && misResponse.getIsSuccess()) {
                    DataHolder.getInstance().setSelectedMistYear((String) spn_fYear.getSelectedItem());
                    DataHolder.getInstance().setMisResponse(misResponse);
                    CommonClass.goToNextScreen(MisReportListActivity.this, DetailMISReport.class, false, false);
                } else
                    commonClass.showToast("No Data Available.");
            } else if (object instanceof ResAnnexure14B) {
                ResAnnexure14B misResponse = (ResAnnexure14B) object;
                if (misResponse != null && misResponse.getIsSuccess()) {
                    DataHolder.getInstance().setSelectedMistYear((String) spn_fYear.getSelectedItem());
                    DataHolder.getInstance().setMisResponse(misResponse);
                    CommonClass.goToNextScreen(MisReportListActivity.this, DetailMISReport.class, false, false);
                } else
                    commonClass.showToast("No Data Available.");
            } else if (object instanceof Annexure14CRes) {
                Annexure14CRes misResponse = (Annexure14CRes) object;
                if (misResponse != null && misResponse.getIsSuccess()) {
                    DataHolder.getInstance().setSelectedMistYear((String) spn_fYear.getSelectedItem());
                    DataHolder.getInstance().setMisResponse(misResponse);
                    CommonClass.goToNextScreen(MisReportListActivity.this, DetailMISReport.class, false, false);
                } else
                    commonClass.showToast("No Data Available.");
            } else if (object instanceof ResAnnexure14D) {
                ResAnnexure14D misResponse = (ResAnnexure14D) object;
                if (misResponse != null && misResponse.getIsSuccess()) {
                    DataHolder.getInstance().setSelectedMistYear((String) spn_fYear.getSelectedItem());
                    DataHolder.getInstance().setMisResponse(misResponse);
                    CommonClass.goToNextScreen(MisReportListActivity.this, DetailMISReport.class, false, false);
                } else
                    commonClass.showToast("No Data Available.");
            } else if (object instanceof ResAnnexure5) {
                ResAnnexure5 misResponse = (ResAnnexure5) object;
                if (misResponse != null && misResponse.getIsSuccess()) {
                    DataHolder.getInstance().setSelectedMistYear((String) spn_fYear.getSelectedItem());
                    DataHolder.getInstance().setMisResponse(misResponse);
                    CommonClass.goToNextScreen(MisReportListActivity.this, DetailMISReport.class, false, false);
                } else
                    commonClass.showToast("No Data Available.");
            } else if (object instanceof ResAnnexure6) {
                ResAnnexure6 misResponse = (ResAnnexure6) object;
                if (misResponse != null && misResponse.getIsSuccess()) {
                    DataHolder.getInstance().setSelectedMistYear((String) spn_fYear.getSelectedItem());
                    DataHolder.getInstance().setMisResponse(misResponse);
                    CommonClass.goToNextScreen(MisReportListActivity.this, DetailMISReport.class, false, false);
                } else
                    commonClass.showToast("No Data Available.");
            } else if (object instanceof ResAnnexure1) {
                ResAnnexure1 misResponse = (ResAnnexure1) object;
                if (misResponse != null && misResponse.getIsSuccess()) {
                    DataHolder.getInstance().setSelectedMistYear((String) spn_fYear.getSelectedItem());
                    DataHolder.getInstance().setMisResponse(misResponse);
                    CommonClass.goToNextScreen(MisReportListActivity.this, DetailMISReport.class, false, false);
                } else
                    commonClass.showToast("No Data Available.");
            } else if (object instanceof ResAnnexure3) {
                ResAnnexure3 misResponse = (ResAnnexure3) object;
                if (misResponse != null && misResponse.getIsSuccess()) {
                    DataHolder.getInstance().setSelectedMistYear((String) spn_fYear.getSelectedItem());
                    DataHolder.getInstance().setMisResponse(misResponse);
                    CommonClass.goToNextScreen(MisReportListActivity.this, DetailMISReport.class, false, false);
                } else
                    commonClass.showToast("No Data Available.");
            } else if (object instanceof ResAnnexure8) {
                ResAnnexure8 misResponse = (ResAnnexure8) object;
                if (misResponse != null && misResponse.getIsSuccess()) {
                    DataHolder.getInstance().setSelectedMistYear((String) spn_fYear.getSelectedItem());
                    DataHolder.getInstance().setMisResponse(misResponse);
                    CommonClass.goToNextScreen(MisReportListActivity.this, DetailMISReport.class, false, false);
                } else
                    commonClass.showToast("No Data Available.");
            } else if (object instanceof ResAnnexure9) {
                ResAnnexure9 misResponse = (ResAnnexure9) object;
                if (misResponse != null && misResponse.getIsSuccess()) {
                    DataHolder.getInstance().setSelectedMistYear((String) spn_fYear.getSelectedItem());
                    DataHolder.getInstance().setMisResponse(misResponse);
                    CommonClass.goToNextScreen(MisReportListActivity.this, DetailMISReport.class, false, false);
                } else
                    commonClass.showToast("No Data Available.");
            } else if (object instanceof ResAnnexure11) {
                ResAnnexure11 misResponse = (ResAnnexure11) object;
                if (misResponse != null && misResponse.getIsSuccess()) {
                    DataHolder.getInstance().setSelectedMistYear((String) spn_fYear.getSelectedItem());
                    DataHolder.getInstance().setMisResponse(misResponse);
                    CommonClass.goToNextScreen(MisReportListActivity.this, DetailMISReport.class, false, false);
                } else
                    commonClass.showToast("No Data Available.");
            } else if (object instanceof ResAnnexure7) {
                ResAnnexure7 misResponse = (ResAnnexure7) object;
                if (misResponse != null && misResponse.getIsSuccess()) {
                    DataHolder.getInstance().setSelectedMistYear((String) spn_fYear.getSelectedItem());
                    DataHolder.getInstance().setMisResponse(misResponse);
                    CommonClass.goToNextScreen(MisReportListActivity.this, DetailMISReport.class, false, false);
                } else
                    commonClass.showToast("No Data Available.");
            } else if (object instanceof ResAnnexureRB2) {
                ResAnnexureRB2 misResponse = (ResAnnexureRB2) object;
                if (misResponse != null && misResponse.getIsSuccess()) {
                    DataHolder.getInstance().setSelectedMistYear((String) spn_fYear.getSelectedItem());
                    DataHolder.getInstance().setMisResponse(misResponse);
                    CommonClass.goToNextScreen(MisReportListActivity.this, DetailMISReport.class, false, false);
                } else
                    commonClass.showToast("No Data Available.");
            } else if (object instanceof ResAnnexureRB3) {
                ResAnnexureRB3 misResponse = (ResAnnexureRB3) object;
                if (misResponse != null && misResponse.getIsSuccess()) {
                    DataHolder.getInstance().setSelectedMistYear((String) spn_fYear.getSelectedItem());
                    DataHolder.getInstance().setMisResponse(misResponse);
                    CommonClass.goToNextScreen(MisReportListActivity.this, DetailMISReport.class, false, false);
                } else
                    commonClass.showToast("No Data Available.");
            } else if (object instanceof ResAnnexureRB5) {
                ResAnnexureRB5 misResponse = (ResAnnexureRB5) object;
                if (misResponse != null && misResponse.getIsSuccess()) {
                    DataHolder.getInstance().setSelectedMistYear((String) spn_fYear.getSelectedItem());
                    DataHolder.getInstance().setMisResponse(misResponse);
                    CommonClass.goToNextScreen(MisReportListActivity.this, DetailMISReport.class, false, false);
                } else
                    commonClass.showToast("No Data Available.");
            } else if (object instanceof ResAnnexureRB6) {
                ResAnnexureRB6 misResponse = (ResAnnexureRB6) object;
                if (misResponse != null && misResponse.getIsSuccess()) {
                    DataHolder.getInstance().setSelectedMistYear((String) spn_fYear.getSelectedItem());
                    DataHolder.getInstance().setMisResponse(misResponse);
                    CommonClass.goToNextScreen(MisReportListActivity.this, DetailMISReport.class, false, false);
                } else
                    commonClass.showToast("No Data Available.");
            } else if (object instanceof ResAnnexureRB4) {
                ResAnnexureRB4 misResponse = (ResAnnexureRB4) object;
                if (misResponse != null && misResponse.getIsSuccess()) {
                    DataHolder.getInstance().setSelectedMistYear((String) spn_fYear.getSelectedItem());
                    DataHolder.getInstance().setMisResponse(misResponse);
                    CommonClass.goToNextScreen(MisReportListActivity.this, DetailMISReport.class, false, false);
                } else
                    commonClass.showToast("No Data Available.");
            } else if (object instanceof ResAnnexureRB7) {
                ResAnnexureRB7 misResponse = (ResAnnexureRB7) object;
                if (misResponse != null && misResponse.getIsSuccess()) {
                    DataHolder.getInstance().setSelectedMistYear((String) spn_fYear.getSelectedItem());
                    DataHolder.getInstance().setMisResponse(misResponse);
                    CommonClass.goToNextScreen(MisReportListActivity.this, DetailMISReport.class, false, false);
                } else
                    commonClass.showToast("No Data Available.");
            } else if (object instanceof ResAnnexure10) {
                ResAnnexure10 misResponse = (ResAnnexure10) object;
                if (misResponse != null && misResponse.getIsSuccess()) {
                    DataHolder.getInstance().setSelectedMistYear((String) spn_fYear.getSelectedItem());
                    DataHolder.getInstance().setMisResponse(misResponse);
                    CommonClass.goToNextScreen(MisReportListActivity.this, AnnexureTen.class, false, false);
                } else
                    commonClass.showToast("No Data Available.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            retryDialog(MisReportListActivity.this, "Unable to fetch data. Do you want to retry?");
        }
    }

    @Override
    public void Error() {
        retryDialog(MisReportListActivity.this, "Unable to fetch data. Do you want to retry?");
    }

    @Override
    public void dismissProgress() {
        commonClass.dismissDialog();
    }

    @Override
    public void showProgress(String msg) {
        commonClass.showProgressBar(msg);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_get_report:
                if (CommonClass.checkInternetConnection(MisReportListActivity.this)) {
                    if (dview != null && dview.isShowing()) {
                        if (callWebService())
                            dview.dismiss();

                    }
                } else
                    commonClass.showToast("No internet available.");
                break;
            case R.id.iv_title_icon:
                onBackPressed();
                break;
        }
    }

    private boolean callWebService() {
        MISReportRequest misReportRequest = new MISReportRequest();
        misReportRequest.setRailway(((Railway) spn_ryCode.getSelectedItem()).getRlycode());
        misReportRequest.setDivision(((Division) spn_divCode.getSelectedItem()).getDivcode());
        misReportRequest.setYear((String) spn_fYear.getSelectedItem());
        misReportRequest.setMonth(((Month) spn_month.getSelectedItem()).getMonthCode());
        switch (pos) {
            case 0:
                misReportRequest.setAuthLevel(loginUser.getAuthlevel());
                misReportRequest.setRoleId("");
                misReportRequest.setLogId("");
                misReportRequest.setAnnexureTypo("");
                DataHolder.getInstance().setMisReportRequest(misReportRequest);
                requestPresenter.Request(misReportRequest, "Getting MIS report...", Constants.ANNEXURE_5);
                break;
            case 1:
                misReportRequest.setAuthLevel(loginUser.getAuthlevel());
                misReportRequest.setRoleId("");
                misReportRequest.setLogId("");
                misReportRequest.setAnnexureTypo("");
                DataHolder.getInstance().setMisReportRequest(misReportRequest);
                requestPresenter.Request(misReportRequest, "Getting MIS report...", Constants.ANNEXURE_6);
                break;
            case 2:
                misReportRequest.setAuthenticationLevel(loginUser.getAuthlevel());
                misReportRequest.setRoleId("");
                misReportRequest.setFlag(TextUtils.isEmpty(((Railway) spn_ryCode.getSelectedItem()).getFlag()) ? "R" : ((Railway) spn_ryCode.getSelectedItem()).getFlag());
                DataHolder.getInstance().setMisReportRequest(misReportRequest);
                requestPresenter.Request(misReportRequest, "Getting MIS report...", Constants.ANNEXURE_14);
                break;
            case 3:
                misReportRequest.setAuthenticationLevel(loginUser.getAuthlevel());
                misReportRequest.setRoleId("");
                misReportRequest.setFlag(TextUtils.isEmpty(((Railway) spn_ryCode.getSelectedItem()).getFlag()) ? "R" : ((Railway) spn_ryCode.getSelectedItem()).getFlag());
                DataHolder.getInstance().setMisReportRequest(misReportRequest);
                requestPresenter.Request(misReportRequest, "Getting MIS report...", Constants.ANNEXURE_14A);
                break;
            case 4:
                misReportRequest.setAuthenticationLevel(loginUser.getAuthlevel());
                misReportRequest.setRoleId("");
                misReportRequest.setFlag(TextUtils.isEmpty(((Railway) spn_ryCode.getSelectedItem()).getFlag()) ? "R" : ((Railway) spn_ryCode.getSelectedItem()).getFlag());
                DataHolder.getInstance().setMisReportRequest(misReportRequest);
                requestPresenter.Request(misReportRequest, "Getting MIS report...", Constants.ANNEXURE_14B);
                break;
            case 5:
                misReportRequest.setAuthenticationLevel(loginUser.getAuthlevel());
                misReportRequest.setRoleId("");
                misReportRequest.setFlag(TextUtils.isEmpty(((Railway) spn_ryCode.getSelectedItem()).getFlag()) ? "R" : ((Railway) spn_ryCode.getSelectedItem()).getFlag());
                DataHolder.getInstance().setMisReportRequest(misReportRequest);
                requestPresenter.Request(misReportRequest, "Getting MIS report...", Constants.ANNEXURE_14C);
                break;
            case 6:
                misReportRequest.setAuthenticationLevel(loginUser.getAuthlevel());
                misReportRequest.setRoleId("");
                misReportRequest.setFlag(TextUtils.isEmpty(((Railway) spn_ryCode.getSelectedItem()).getFlag()) ? "R" : ((Railway) spn_ryCode.getSelectedItem()).getFlag());
                DataHolder.getInstance().setMisReportRequest(misReportRequest);
                requestPresenter.Request(misReportRequest, "Getting MIS report...", Constants.ANNEXURE_14D);
                break;
            case 7:
                misReportRequest.setAuthenticationLevel(loginUser.getAuthlevel());
                misReportRequest.setRoleId("");
                misReportRequest.setFlag(TextUtils.isEmpty(((Railway) spn_ryCode.getSelectedItem()).getFlag()) ? "R" : ((Railway) spn_ryCode.getSelectedItem()).getFlag());
                DataHolder.getInstance().setMisReportRequest(misReportRequest);
                requestPresenter.Request(misReportRequest, "Getting MIS report...", Constants.ANNEXURE_RB1);
                break;

            case 8:
                misReportRequest.setRoleId("");
                DataHolder.getInstance().setMisReportRequest(misReportRequest);
                requestPresenter.Request(misReportRequest, "Getting MIS report...", Constants.ANNEXURE_1);
                break;
            case 9:
                misReportRequest.setAuthLevel(loginUser.getAuthlevel());
                misReportRequest.setRoleId("");
                misReportRequest.setLogId("");
                misReportRequest.setAnnexureTypo("AT1");
                DataHolder.getInstance().setMisReportRequest(misReportRequest);
                requestPresenter.Request(misReportRequest, "Getting MIS report...", Constants.ANNEXURE_3);
                break;

            case 10:
                misReportRequest.setAuthLevel(loginUser.getAuthlevel());
                misReportRequest.setRoleId("");
                misReportRequest.setLogId("");
                misReportRequest.setAnnexureTypo("AT1");
                DataHolder.getInstance().setMisReportRequest(misReportRequest);
                requestPresenter.Request(misReportRequest, "Getting MIS report...", Constants.ANNEXURE_8);
                break;

            case 11:
                misReportRequest.setAuthLevel(loginUser.getAuthlevel());
                misReportRequest.setRoleId("");
                misReportRequest.setLogId("");
                misReportRequest.setAnnexureTypo("AT1");
                DataHolder.getInstance().setMisReportRequest(misReportRequest);
                requestPresenter.Request(misReportRequest, "Getting MIS report...", Constants.ANNEXURE_9);
                break;
            case 12:
                misReportRequest.setAuthLevel(loginUser.getAuthlevel());
                misReportRequest.setRoleId("");
                misReportRequest.setLogId("");
                misReportRequest.setAnnexureTypo("AT1");
                DataHolder.getInstance().setMisReportRequest(misReportRequest);
                requestPresenter.Request(misReportRequest, "Getting MIS report...", Constants.ANNEXURE_11);
                break;
            case 13:
                misReportRequest.setAuthLevel(loginUser.getAuthlevel());
                misReportRequest.setRoleId("");
                misReportRequest.setLogId("");
                misReportRequest.setAnnexureTypo("AT1");
                DataHolder.getInstance().setMisReportRequest(misReportRequest);
                requestPresenter.Request(misReportRequest, "Getting MIS report...", Constants.ANNEXURE_7);
                break;
            case 14:
                misReportRequest.setAuthLevel(loginUser.getAuthlevel());
                misReportRequest.setRoleId("");
                misReportRequest.setAnnexureTypo("AT1");
                DataHolder.getInstance().setMisReportRequest(misReportRequest);
                requestPresenter.Request(misReportRequest, "Getting MIS report...", Constants.ANNEXURE_RB2);
                break;
            case 15:
                if (spn_no_of_year.isShown() && ((KeyValue) spn_no_of_year.getSelectedItem()).getValue().isEmpty()) {
                    commonClass.showToast("Please select no of year.");
                    return false;
                } else {
                    misReportRequest.setAuthLevel(loginUser.getAuthlevel());
                    misReportRequest.setRoleId("");
                    misReportRequest.setNoofyear(((KeyValue) spn_no_of_year.getSelectedItem()).getValue());
                    misReportRequest.setAnnexureTypo("AT1");
                    DataHolder.getInstance().setMisReportRequest(misReportRequest);
                    requestPresenter.Request(misReportRequest, "Getting MIS report...", Constants.ANNEXURE_RB3);
                }
                break;

            case 16:
                misReportRequest.setAuthLevel(loginUser.getAuthlevel());
                misReportRequest.setRoleId("");
                DataHolder.getInstance().setMisReportRequest(misReportRequest);
                requestPresenter.Request(misReportRequest, "Getting MIS report...", Constants.ANNEXURE_RB5);
                break;

            case 17:
                misReportRequest.setAuthLevel(loginUser.getAuthlevel());
                misReportRequest.setRoleId("");
                DataHolder.getInstance().setMisReportRequest(misReportRequest);
                requestPresenter.Request(misReportRequest, "Getting MIS report...", Constants.ANNEXURE_RB6);
                break;
            case 18:
                misReportRequest.setAuthLevel(loginUser.getAuthlevel());
                misReportRequest.setRoleId("");
                DataHolder.getInstance().setMisReportRequest(misReportRequest);
                requestPresenter.Request(misReportRequest, "Getting MIS report...", Constants.ANNEXURE_RB4);
                break;
            case 19:
                misReportRequest.setAuthLevel(loginUser.getAuthlevel());
                misReportRequest.setRoleId("");
                DataHolder.getInstance().setMisReportRequest(misReportRequest);
                requestPresenter.Request(misReportRequest, "Getting MIS report...", Constants.ANNEXURE_RB7);
                break;
            case 20:
                String selectedYear = (String) spn_fYear.getSelectedItem();
                misReportRequest.setAuthLevel(loginUser.getAuthlevel());
                misReportRequest.setRoleId("");
                misReportRequest.setLogId("");
                if (selectedYear == null)
                    misReportRequest.setpFinyear("");
                else {
                    String[] year = selectedYear.split("-");
                    int secYear = Integer.valueOf(year[1]);
                    misReportRequest.setpFinyear(secYear - 2 + "-" + (secYear - 1));
                }
                misReportRequest.setAnnexureTypo("AT1");
                DataHolder.getInstance().setMisReportRequest(misReportRequest);
                requestPresenter.Request(misReportRequest, "Getting MIS report...", Constants.ANNEXURE_10);
                break;
        }
        return true;
    }


    private void retryDialog(final Activity activity, String msg) {
        new AlertDialog.Builder(activity).setCancelable(false)
                .setTitle(getResources().getString(R.string.cms))
                .setMessage(msg)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        callWebService();
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        activity.finish();
                    }
                })
                .setIcon(R.drawable.icon_logo)
                .show();
    }
}
