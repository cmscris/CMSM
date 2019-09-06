package com.cris.cmsm.navcontrollers;

import android.app.Activity;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatSpinner;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cris.cmsm.BaseActivity;
import com.cris.cmsm.R;
import com.cris.cmsm.adapter.DepartmentAdapter;
import com.cris.cmsm.adapter.DivisonAdapter;
import com.cris.cmsm.adapter.RailwayAdapter;
import com.cris.cmsm.adapter.SSEAdapter;
import com.cris.cmsm.adapter.SpinnerAdapter;
import com.cris.cmsm.adapter.SubStationAdapter;
import com.cris.cmsm.database.DataBaseManager;
import com.cris.cmsm.database.DataHolder;
import com.cris.cmsm.interactor.WebServices;
import com.cris.cmsm.interfaces.IMonthBilling;
import com.cris.cmsm.interfaces.IMonthConsumption;
import com.cris.cmsm.interfaces.IOnFragmentVisible;
import com.cris.cmsm.models.Department;
import com.cris.cmsm.models.Division;
import com.cris.cmsm.models.Railway;
import com.cris.cmsm.models.request.MonthlyConsModel;
import com.cris.cmsm.models.request.RequestSSERole;
import com.cris.cmsm.models.request.SubStationRequest;
import com.cris.cmsm.models.response.LoginIfoVO;
import com.cris.cmsm.models.response.ResMonthlyBill;
import com.cris.cmsm.models.response.ResMonthlyCons;
import com.cris.cmsm.models.response.ResponseSSERole;
import com.cris.cmsm.models.response.SubStation;
import com.cris.cmsm.prefrences.UserLoginPreferences;
import com.cris.cmsm.presenter.MonthlyRequestPresenter;
import com.cris.cmsm.presenterview.MonthResponseView;
import com.cris.cmsm.tabviews.MonthBilling;
import com.cris.cmsm.tabviews.MonthConsumption;
import com.cris.cmsm.util.CommonClass;
import com.cris.cmsm.util.Constants;
import com.cris.cmsm.util.FontFamily;
import com.cris.cmsm.widget.JitinRLSlidingLayout;
import com.cris.cmsm.widget.NonSwipeViewPager;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class MonthGraphController extends BaseActivity implements MonthResponseView, View.OnClickListener, AdapterView.OnItemSelectedListener, ViewPager.OnPageChangeListener {

    private TabLayout tabs;
    private NonSwipeViewPager pager;
    private List<String> tabList;
    private JitinRLSlidingLayout slidingPaneLayout;
    private ImageView iv_title_icon, iv_right;
    private TextView action_bar_title, tv_filters;
    private Typeface font;
    private AppCompatSpinner spn_ryCode, spn_divCode, spn_sse, spn_sub_station, spn_department, spn_fYear, spn_month;
    private Button btn_filter;
    private CommonClass commonClass;
    private DataBaseManager dataBaseManager;
    private IMonthConsumption iConsumption;
    private IMonthBilling iBilling;
    private LoginIfoVO loginUser;
    private CreatePageAdapter pageAdapter;
    private LinearLayout showProgress;
    private TextView tv_header_name;
    private MonthlyRequestPresenter monthlyRequestPresenter;

    public void initializeConsumption(IMonthConsumption iConsumption) {
        this.iConsumption = iConsumption;
    }

    public void initializeBilling(IMonthBilling iBilling) {
        this.iBilling = iBilling;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graphs);
        monthlyRequestPresenter = new MonthlyRequestPresenter(MonthGraphController.this);
        loginUser = new UserLoginPreferences(MonthGraphController.this).getLoginUser();
        commonClass = new CommonClass(MonthGraphController.this);
        dataBaseManager = new DataBaseManager(MonthGraphController.this);
        dataBaseManager.createDatabase();

        font = new FontFamily(MonthGraphController.this).getBold();
        showProgress = findViewById(R.id.showProgress);
        spn_ryCode = findViewById(R.id.spn_ryCode);
        spn_divCode = findViewById(R.id.spn_divCode);
        spn_sse = findViewById(R.id.spn_sse);
        spn_sub_station = findViewById(R.id.spn_sub_station);
        spn_department = findViewById(R.id.spn_department);
        spn_fYear = findViewById(R.id.spn_fYear);
        spn_month = findViewById(R.id.spn_month);
        btn_filter = findViewById(R.id.btn_filter);

        spn_sse.setVisibility(View.VISIBLE);
        spn_sub_station.setVisibility(View.VISIBLE);
        spn_month.setVisibility(View.GONE);

        tv_header_name = findViewById(R.id.tv_header_name);
        tv_filters = findViewById(R.id.tv_filters);
        action_bar_title = findViewById(R.id.action_bar_title);
        iv_title_icon = findViewById(R.id.iv_title_icon);
        iv_right = findViewById(R.id.iv_right);

        slidingPaneLayout = findViewById(R.id.slidingPaneLayout);
        slidingPaneLayout.setParallaxDistance(200);
        slidingPaneLayout.setShadowDrawableLeft(getResources().getDrawable(R.drawable.nav_bar_shadow));
        slidingPaneLayout.setSliderFadeColor(getResources().getColor(android.R.color.transparent));


        iv_right.setImageResource(R.drawable.filter);
        iv_right.setVisibility(View.VISIBLE);
        iv_title_icon.setImageResource(R.drawable.iv_back);
        action_bar_title.setText(getResources().getString(R.string.monthly_consumption));
        tv_filters.setTypeface(font);
        action_bar_title.setTypeface(font);
        tv_header_name.setVisibility(View.VISIBLE);
        tv_header_name.setTypeface(font);

        /********* Spinner Adapters ********/
        List<Railway> railwayList = dataBaseManager.getRailwayList(true);
        spn_ryCode.setAdapter(new RailwayAdapter(MonthGraphController.this, railwayList));
        spn_ryCode.setSelection(commonClass.getUserRailwayIndex(railwayList, loginUser.getRlycode() != null ? loginUser.getRlycode() : ""));
        if (!TextUtils.isEmpty(loginUser.getAuthlevel()) && loginUser.getAuthlevel().equalsIgnoreCase(Constants.AUTH_BOARD)) {
            spn_ryCode.setEnabled(true);
        } else {
            spn_ryCode.setEnabled(false);
        }
        spn_divCode.setAdapter(new DivisonAdapter(MonthGraphController.this, dataBaseManager.getDivisionList("-100")));
        setSseAdapter(new ArrayList<ResponseSSERole>());
        setSubStationAdapter(new ArrayList<SubStation>());
        spn_department.setAdapter(new DepartmentAdapter(MonthGraphController.this, CommonClass.getDepartmentList(loginUser.getAuthlevel(), loginUser.getCategory(), false)));
        spn_fYear.setAdapter(new SpinnerAdapter(MonthGraphController.this, CommonClass.getFinancialYear()));

        iv_right.setOnClickListener(this);
        iv_title_icon.setOnClickListener(this);
        btn_filter.setOnClickListener(this);
        spn_ryCode.setOnItemSelectedListener(this);
        spn_divCode.setOnItemSelectedListener(this);
        spn_sse.setOnItemSelectedListener(this);
        spn_sub_station.setOnItemSelectedListener(this);
        spn_department.setOnItemSelectedListener(this);
        spn_fYear.setOnItemSelectedListener(this);

        tabList = new ArrayList<>();
        tabList.add("Consumption");
        tabList.add("Billing");
        tabs = findViewById(R.id.tabs);
        pager = findViewById(R.id.pager);
        if (!TextUtils.isEmpty(loginUser.getRoleid())) {
            //set after sse thread
        } else {
            setPagerView();
        }

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_title_icon:
                if (slidingPaneLayout.isOpen()) {
                    slidingPaneLayout.closePane();
                } else {
                    onBackPressed();
                }
                break;
            case R.id.iv_right:
                if (slidingPaneLayout.isOpen()) {
                    slidingPaneLayout.closePane();
                } else {
                    slidingPaneLayout.openPane();
                }
                break;
            case R.id.btn_filter:
                if (CommonClass.checkInternetConnection(MonthGraphController.this)) {
                    if (slidingPaneLayout.isOpen()) {
                        slidingPaneLayout.closePane();
                    }
                    DataHolder.getInstance().setResMonthlyCons(null);
                    DataHolder.getInstance().setResMonthlyBill(null);
                    getMonthlyData();
                } else {
                    commonClass.showToast("No internet available.");
                }
                break;
        }
    }


    @Override
    public void onBackPressed() {
        if (slidingPaneLayout.isOpen()) {
            slidingPaneLayout.closePane();
        } else {
            WebServices.getInstance().cancelAllRequest();
            super.onBackPressed();
        }

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
                    spn_sse.setAdapter(new SSEAdapter(MonthGraphController.this, list));
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
                    spn_sub_station.setAdapter(new SubStationAdapter(MonthGraphController.this, subStationList));
                    if (!TextUtils.isEmpty(loginUser.getRoleid())) {
                        setPagerView();
                    }
                    break;

                case Constants.MONTHLY_CONSUMPTION:
                    try {
                        ResMonthlyCons resMonthlyCons = (ResMonthlyCons) object;
                        DataHolder.getInstance().setResMonthlyCons(resMonthlyCons);
                        iConsumption.showConsumptionResponse(resMonthlyCons);
                    } catch (Exception e) {
                        retryDialog(MonthGraphController.this, "Unable to fetch record. Do you want to retry?");
                    }
                    break;

                case Constants.MONTHLY_BILLING:
                    try {
                        ResMonthlyBill resMonthlyBill = (ResMonthlyBill) object;
                        DataHolder.getInstance().setResMonthlyBill(resMonthlyBill);
                        iBilling.showBillingResponse(resMonthlyBill);
                    } catch (Exception e) {
                        retryDialog(MonthGraphController.this, "Unable to fetch record. Do you want to retry?");
                    }
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
            commonClass.showToast("Unable to fetch record. Please try again.");
        }
    }

    @Override
    public void Error() {
        retryDialog(MonthGraphController.this, "Unable to fetch record. Do you want to retry?");
    }

    @Override
    public void dismissProgress() {
        if (commonClass.checkProgressState())
            commonClass.dismissDialog();
    }


    @Override
    public void showMonthProgress() {
        iv_right.setEnabled(false);
        showProgress.setVisibility(View.VISIBLE);
        if (pager.getCurrentItem() == 0)
            iConsumption.disableControls();
        if (pager.getCurrentItem() == 1)
            iBilling.disableControls();
    }

    @Override
    public void dismissMonthReq() {
        iv_right.setEnabled(true);
        showProgress.setVisibility(View.GONE);
    }


    @Override
    public void showProgress(String msg) {
        commonClass.showProgressBar(msg);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Object object = parent.getItemAtPosition(position);
        if (object instanceof Railway) {
            Railway railway = (Railway) object;
            List<Division> list = dataBaseManager.getDivisionList(railway.getRlycode());
            spn_divCode.setAdapter(new DivisonAdapter(MonthGraphController.this, list));
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
                subStationRequest.setCategory(((Department) spn_department.getSelectedItem()).getDepartmentCode());
                monthlyRequestPresenter.Request(subStationRequest, "Getting SubStation", Constants.GET_SUBSTATION);
            } else {
                setSubStationAdapter(new ArrayList<SubStation>());
            }
        } else if (object instanceof Department) {
            if (loginUser.getAuthlevel().equalsIgnoreCase(Constants.AUTH_BOARD) || (loginUser.getAuthlevel().equalsIgnoreCase(Constants.AUTH_RAILWAY) && "R".equalsIgnoreCase(loginUser.getRlytype()))) {
                spn_divCode.setSelection(0);
            }
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }


    public void getMonthlyData() {
        MonthlyConsModel request = new MonthlyConsModel();
        request.setYear((String) spn_fYear.getSelectedItem());
        request.setRailway(((Railway) spn_ryCode.getSelectedItem()).getRlycode());
        request.setDivision(((Division) spn_divCode.getSelectedItem()).getDivcode());
        request.setAuthLevel(loginUser.getAuthlevel());
        if (((Department) spn_department.getSelectedItem()).getDepartmentCode().equalsIgnoreCase("TRD"))
            request.setCategory(Constants.CATEGORY_TRANSACTION);
        else
            request.setCategory(Constants.CATEGORY_GENERAL_SERVICE);
        request.setSseIncharge(((ResponseSSERole) spn_sse.getSelectedItem()).getRoleid());
        request.setSubsttn(((SubStation) spn_sub_station.getSelectedItem()).getSubstationId());
        if (pager.getCurrentItem() == 0)
            monthlyRequestPresenter.Request(request, "", Constants.MONTHLY_CONSUMPTION);
        else
            monthlyRequestPresenter.Request(request, "", Constants.MONTHLY_BILLING);
        StringBuilder sb = new StringBuilder();
        if (((Railway) spn_ryCode.getSelectedItem()).getRlycode().isEmpty())
            sb.append("ALL Rly.");
        else
            sb.append(((Railway) spn_ryCode.getSelectedItem()).getRlycode());
        if (((Division) spn_divCode.getSelectedItem()).getDivcode().isEmpty())
            sb.append(" / ALL Div.");
        else
            sb.append(" / " + ((Division) spn_divCode.getSelectedItem()).getDivcode());
        if (((ResponseSSERole) spn_sse.getSelectedItem()).getRoleid().isEmpty())
            sb.append(" / ALL SSE");
        else
            sb.append(" / " + ((ResponseSSERole) spn_sse.getSelectedItem()).getName());
        if (((SubStation) spn_sub_station.getSelectedItem()).getSubstationId().isEmpty())
            sb.append(" / ALL Sub-Station");
        else
            sb.append(" / " + ((SubStation) spn_sub_station.getSelectedItem()).getSubstationName());
        tv_header_name.setText(sb.toString());
    }

    private void callWebService() {
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

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        IOnFragmentVisible fragment = null;
        if (position == 0) {
            fragment = (MonthConsumption) pageAdapter.instantiateItem(pager, position);
        } else if (position == 1) {
            fragment = (MonthBilling) pageAdapter.instantiateItem(pager, position);
        }
        if (fragment != null) {
            fragment.fragmentBecameVisible();
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }


    private class CreatePageAdapter extends FragmentStatePagerAdapter {

        public CreatePageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new MonthConsumption();
                default:
                    return new MonthBilling();
            }
        }


        @Override
        public int getCount() {
            return tabList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabList.get(position);
        }

        @Override
        public void notifyDataSetChanged() {
            super.notifyDataSetChanged();
        }

    }


    public void retryDialog(final Activity activity, String msg) {
        try {
            new AlertDialog.Builder(activity).setCancelable(false)
                    .setTitle(getResources().getString(R.string.cms))
                    .setMessage(msg)
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            getMonthlyData();
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
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void setSseAdapter(List<ResponseSSERole> list) {
        ResponseSSERole hintModel = new ResponseSSERole();
        hintModel.setName("ALL SSE");
        hintModel.setRoleid("");
        list.add(0, hintModel);
        spn_sse.setAdapter(new SSEAdapter(MonthGraphController.this, list));
    }

    private void setSubStationAdapter(List<SubStation> list) {
        SubStation subStation = new SubStation();
        subStation.setSubstationId("");
        subStation.setSubstationName("ALL Sub-Station");
        list.add(0, subStation);
        spn_sub_station.setAdapter(new SubStationAdapter(MonthGraphController.this, list));
    }

    private void setPagerView() {
        pageAdapter = new CreatePageAdapter(getSupportFragmentManager());
        pager.setAdapter(pageAdapter);
        tabs.setupWithViewPager(pager);
        pager.setOffscreenPageLimit(1);
        pager.setPagingEnabled(false);
        if (getIntent().getIntExtra(Constants.KEY, -1) != -1) {
            int value = getIntent().getIntExtra(Constants.KEY, -1);
            pager.setCurrentItem(value);
        }
        pager.setOnPageChangeListener(this);
    }

}
