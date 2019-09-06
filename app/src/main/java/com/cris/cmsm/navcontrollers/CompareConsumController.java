package com.cris.cmsm.navcontrollers;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatSpinner;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.Highlight;
import com.cris.cmsm.BaseActivity;
import com.cris.cmsm.DetailOnBarActivity;
import com.cris.cmsm.R;
import com.cris.cmsm.ZoomCompareGraph;
import com.cris.cmsm.adapter.DepartmentAdapter;
import com.cris.cmsm.adapter.DivisonAdapter;
import com.cris.cmsm.adapter.RailwayAdapter;
import com.cris.cmsm.adapter.SpinnerAdapter;
import com.cris.cmsm.adapter.ThreeYearMonthAdapter;
import com.cris.cmsm.database.DataBaseManager;
import com.cris.cmsm.database.DataHolder;
import com.cris.cmsm.interactor.WebServices;
import com.cris.cmsm.interfaces.ICheckBoxClick;
import com.cris.cmsm.models.Department;
import com.cris.cmsm.models.Division;
import com.cris.cmsm.models.Month;
import com.cris.cmsm.models.Railway;
import com.cris.cmsm.models.request.ConSummaryRequest;
import com.cris.cmsm.models.response.ConSummaryResponse;
import com.cris.cmsm.models.response.LoginIfoVO;
import com.cris.cmsm.models.response.ThreeYearData;
import com.cris.cmsm.prefrences.UserLoginPreferences;
import com.cris.cmsm.presenter.RequestPresenter;
import com.cris.cmsm.presenterview.ResponseView;
import com.cris.cmsm.util.CommonClass;
import com.cris.cmsm.util.Constants;
import com.cris.cmsm.util.FontFamily;
import com.cris.cmsm.util.UnitValueFormatter;
import com.cris.cmsm.widget.JitinRLSlidingLayout;
import com.cris.cmsm.widget.NonScrollListView;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class CompareConsumController extends BaseActivity implements ResponseView, View.OnClickListener, AdapterView.OnItemSelectedListener, ICheckBoxClick {

    private JitinRLSlidingLayout slidingPaneLayout;
    private ImageView iv_title_icon, iv_right, iv_zoom_cons, iv_zoom_bill, iv_connected_load, iv_avg_cost;
    private TextView action_bar_title, tv_filters, tv_connected_load, tv_avg_cost;
    private Typeface font;
    private AppCompatSpinner spn_ryCode, spn_divCode, spn_department, spn_fYear, spn_no_of_year;
    private NonScrollListView listView;
    private Button btn_filter;
    private CommonClass commonClass;
    private DataBaseManager dataBaseManager;
    private RequestPresenter requestPresenter;
    private LoginIfoVO loginUser;
    private PieChart pieChart, bill_pieChart, connected_load_pieChart, avg_pieChart;
    private TextView tv_consumption, tv_billing;
    private LinearLayout showProgress;
    private List<ConSummaryResponse> list;
    private int detailVar = 0;
    private List<Month> monthList;
    private ScrollView scrollView;
    private ThreeYearMonthAdapter threeYearMonthAdapter;
    private TextView tv_titles;
    private CheckBox checkbox;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_three_year);
        loginUser = new UserLoginPreferences(CompareConsumController.this).getLoginUser();
        requestPresenter = new RequestPresenter(CompareConsumController.this);
        commonClass = new CommonClass(CompareConsumController.this);
        dataBaseManager = new DataBaseManager(CompareConsumController.this);
        dataBaseManager.createDatabase();

        scrollView = findViewById(R.id.scrollView);
        tv_titles = findViewById(R.id.tv_titles);
        checkbox = findViewById(R.id.checkbox);
        showProgress = findViewById(R.id.showProgress);
        font = new FontFamily(CompareConsumController.this).getBold();
        spn_ryCode = findViewById(R.id.spn_ryCode);
        spn_divCode = findViewById(R.id.spn_divCode);
        spn_department = findViewById(R.id.spn_department);
        spn_fYear = findViewById(R.id.spn_fYear);
        spn_no_of_year = findViewById(R.id.spn_no_of_year);
        listView = findViewById(R.id.listView);
        tv_filters = findViewById(R.id.tv_filters);
        btn_filter = findViewById(R.id.btn_filter);

        action_bar_title = findViewById(R.id.action_bar_title);
        iv_title_icon = findViewById(R.id.iv_title_icon);
        iv_right = findViewById(R.id.iv_right);

        slidingPaneLayout = findViewById(R.id.slidingPaneLayout);
        slidingPaneLayout.setParallaxDistance(200);
        slidingPaneLayout.setShadowDrawableLeft(getResources().getDrawable(R.drawable.nav_bar_shadow));
        slidingPaneLayout.setSliderFadeColor(getResources().getColor(android.R.color.transparent));

        iv_zoom_cons = findViewById(R.id.iv_zoom_cons);
        iv_zoom_bill = findViewById(R.id.iv_zoom_bill);
        iv_connected_load = findViewById(R.id.iv_connected_load);
        tv_consumption = findViewById(R.id.tv_consumption);
        tv_billing = findViewById(R.id.tv_billing);
        tv_connected_load = findViewById(R.id.tv_connected_load);
        tv_avg_cost = findViewById(R.id.tv_avg_cost);
        pieChart = findViewById(R.id.pieChart);
        bill_pieChart = findViewById(R.id.bill_pieChart);
        connected_load_pieChart = findViewById(R.id.connected_load_pieChart);

        tv_consumption.setTypeface(font);
        tv_billing.setTypeface(font);
        tv_connected_load.setTypeface(font);
        tv_avg_cost.setTypeface(font);
        tv_filters.setTypeface(font);
        tv_titles.setTypeface(new FontFamily(CompareConsumController.this).getRegular());
        tv_titles.setText("All Months");


        iv_right.setImageResource(R.drawable.filter);
        iv_right.setVisibility(View.VISIBLE);
        iv_title_icon.setImageResource(R.drawable.iv_back);
        action_bar_title.setText(getResources().getString(R.string.consumption_analytics));
        action_bar_title.setTypeface(font);

        /********* Spinner Adapters ********/
        List<Railway> railwayList = null;
        if (loginUser.getAuthlevel().equals(Constants.AUTH_BOARD)) {
            railwayList = dataBaseManager.getRailwayList(true);
        } else {
            railwayList = dataBaseManager.getRailwayList(false);
        }
        spn_ryCode.setAdapter(new RailwayAdapter(CompareConsumController.this, railwayList));
        spn_divCode.setAdapter(new DivisonAdapter(CompareConsumController.this, dataBaseManager.getDivisionList(loginUser.getRlycode())));
        spn_department.setAdapter(new DepartmentAdapter(CompareConsumController.this, CommonClass.getDepartmentList(loginUser.getAuthlevel(), loginUser.getCategory(), true)));

        monthList = CommonClass.getMonthList();
        monthList.remove(0);
        threeYearMonthAdapter = new ThreeYearMonthAdapter(CompareConsumController.this, CompareConsumController.this, monthList);
        listView.setAdapter(threeYearMonthAdapter);
        spn_fYear.setAdapter(new SpinnerAdapter(CompareConsumController.this, CommonClass.getFinancialYear()));
        spn_no_of_year.setAdapter(new SpinnerAdapter(CompareConsumController.this, CommonClass.getComparisonYear()));
        spn_no_of_year.setSelection(3);
        spn_ryCode.setSelection(commonClass.getUserRailwayIndex(railwayList, loginUser.getRlycode() != null ? loginUser.getRlycode() : ""));
        if (!TextUtils.isEmpty(loginUser.getAuthlevel()) && loginUser.getAuthlevel().equals(Constants.AUTH_BOARD))
            spn_ryCode.setEnabled(true);
        else
            spn_ryCode.setEnabled(false);

        spn_ryCode.setOnItemSelectedListener(this);
        btn_filter.setOnClickListener(this);

        iv_right.setOnClickListener(this);
        iv_title_icon.setOnClickListener(this);
        iv_zoom_cons.setOnClickListener(this);
        iv_zoom_bill.setOnClickListener(this);
        iv_connected_load.setOnClickListener(this);
        iv_avg_cost.setOnClickListener(this);

        pieChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {
                if (DataHolder.getInstance().getConSummaryResponses() != null) {
                    detailVar = 0;
                    ConSummaryRequest request = DataHolder.getInstance().getConSummaryRequest();
                    request.setFinancialYear(DataHolder.getInstance().getConSummaryResponses().get(e.getXIndex()).getYear());
                    requestPresenter.Request(request, "Fetching records...", Constants.THREE_YEAR_DETAIL);
                }

            }

            @Override
            public void onNothingSelected() {

            }
        });

        bill_pieChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {
                if (DataHolder.getInstance().getConSummaryResponses() != null) {
                    detailVar = 1;
                    ConSummaryRequest request = DataHolder.getInstance().getConSummaryRequest();
                    request.setFinancialYear(DataHolder.getInstance().getConSummaryResponses().get(e.getXIndex()).getYear());
                    requestPresenter.Request(request, "Fetching records...", Constants.THREE_YEAR_DETAIL);
                }
            }

            @Override
            public void onNothingSelected() {

            }
        });

        connected_load_pieChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {
                if (DataHolder.getInstance().getConSummaryResponses() != null) {
                    detailVar = 2;
                    ConSummaryRequest request = DataHolder.getInstance().getConSummaryRequest();
                    request.setFinancialYear(DataHolder.getInstance().getConSummaryResponses().get(e.getXIndex()).getYear());
                    requestPresenter.Request(request, "Fetching records...", Constants.THREE_YEAR_DETAIL);
                }
            }

            @Override
            public void onNothingSelected() {

            }
        });


        avg_pieChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {
                if (DataHolder.getInstance().getConSummaryResponses() != null) {
                    detailVar = 3;
                    ConSummaryRequest request = DataHolder.getInstance().getConSummaryRequest();
                    request.setFinancialYear(DataHolder.getInstance().getConSummaryResponses().get(e.getXIndex()).getYear());
                    requestPresenter.Request(request, "Fetching records...", Constants.THREE_YEAR_DETAIL);
                }
            }

            @Override
            public void onNothingSelected() {

            }
        });


        scrollView
                .setDescendantFocusability(ViewGroup.FOCUS_BEFORE_DESCENDANTS);
        scrollView.setFocusable(true);
        scrollView.setFocusableInTouchMode(true);
        scrollView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                v.requestFocusFromTouch();
                return false;
            }
        });

        checkbox.setChecked(true);
        checkbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkbox.isChecked())
                    checkUncheck(true);
                else
                    checkUncheck(false);
            }
        });

        handler.postDelayed(r, 300);
    }


    Runnable r = new Runnable() {
        @Override
        public void run() {
            callWebService();
        }
    };


    private void checkUncheck(boolean isCheckAll) {
        for (Month month : monthList)
            month.setCheck(isCheckAll);
        threeYearMonthAdapter.notifyDataSetChanged();
    }

    @Override
    public void ResponseOk(Object object, int position) {
        try {
            StringBuilder sb = new StringBuilder();
            List<Month> checkedList = new ArrayList<>();
            for (Month month : monthList) {
                if (month.isCheck())
                    checkedList.add(month);
            }
            if (checkedList.size() == 12) {
                sb.append("All Months");
            } else {
                for (Month month :
                        checkedList) {
                    sb.append(month.getMonthName() + ",");
                }
            }
            String year = "\n " + "Fin. Year : " + spn_fYear.getSelectedItem() + " & " + "Month : " + sb.toString();
            if (object instanceof ThreeYearData) {
                ThreeYearData threeYearData = (ThreeYearData) object;
                DataHolder.getInstance().setThreeYearData(threeYearData);
                if (detailVar == 0) {
                    DataHolder.getInstance().setZoomGraphTitle(setTitle("Year wise Energy Data(in millions kWh) \nComparison  of ").toString() + year);
                    zoomScreen(Constants.YEAR_CONS_BAR, true);
                } else if (detailVar == 1) {
                    DataHolder.getInstance().setZoomGraphTitle(setTitle("Year wise Billing(in million Rs.) \nComparison of ").toString() + year);
                    zoomScreen(Constants.YEAR_BILL_BAR, true);
                } else if (detailVar == 2) {
                    DataHolder.getInstance().setZoomGraphTitle(setTitle("Year wise Total Connected Load \nComparison of ").toString() + "(MW)" + year);
                    zoomScreen(Constants.YEAR_CONNECTED_BAR, true);
                } else if (detailVar == 3) {
                    DataHolder.getInstance().setZoomGraphTitle(setTitle("Year wise Total Average Cost  \nComparison of ").toString() + "(in Rs./kWh)" + year);
                    zoomScreen(Constants.YEAR_AVG_BAR, true);
                }
            } else {
                list = (List<ConSummaryResponse>) object;
                if (list != null && list.size() > 0) {
                    DataHolder.getInstance().setConSummaryResponses(list);
                    showPieChart(list);
                    tv_consumption.setText(setTitle("Year wise Energy Data(in millions kWh) \nComparison  of ").toString());
                    tv_billing.setText(setTitle("Year wise Billing(in million Rs.) \nComparison of ").toString());
                    tv_connected_load.setText(setTitle("Year wise Total Connected Load \nComparison  of ").toString() + "(MW)");
                    tv_avg_cost.setText(setTitle("Year wise Total Average Cost \nComparison of ").toString() + "(in Rs./kWh)");
                }
            }
        } catch (Exception e) {
            retryDialog(CompareConsumController.this, "Unable to fetch record. Do you want to retry?");
            e.printStackTrace();
        }
    }


    private StringBuilder setTitle(String title) {
        StringBuilder sb = new StringBuilder(title);
        if (((Division) spn_divCode.getSelectedItem()).getDivcode().isEmpty()) {
            if (((Railway) spn_ryCode.getSelectedItem()).getRlycode().isEmpty())
                sb.append("Indian Railways");
            else
                sb.append(((Railway) spn_ryCode.getSelectedItem()).getFname());
        } else {
            sb.append(FirstLetterCaptial(((Division) spn_divCode.getSelectedItem()).getFname()));
        }
        sb.append(" for ");
        if (((Department) spn_department.getSelectedItem()).getDepartmentCode().isEmpty()) {
            if ("R".equalsIgnoreCase(((Railway) spn_ryCode.getSelectedItem()).getFlag()) || TextUtils.isEmpty(((Railway) spn_ryCode.getSelectedItem()).getRlycode()))
                sb.append("\n Gen & Trd Services");
            else
                sb.append("\n Gen Services");
        } else {
            sb.append(FirstLetterCaptial(((Department) spn_department.getSelectedItem()).getDepartmentName()));
        }
        return sb;
    }


    private String FirstLetterCaptial(String myString) {
        String upperString = myString.substring(0, 1).toUpperCase() + myString.substring(1).toLowerCase();
        return upperString;
    }


    private void showPieChart(List<ConSummaryResponse> list) {
        if (list != null) {
            List<Entry> dataList = new ArrayList<>();
            List<String> titleList = new ArrayList<>();
            for (int i = 0; i < list.size(); i++) {
                dataList.add(new Entry(Float.valueOf(list.get(i).getTotalConsumption()), i));
                titleList.add(list.get(i).getYear());
            }
            showPieChart(pieChart, dataList, titleList);


            List<Entry> billList = new ArrayList<>();
            List<String> billTitleList = new ArrayList<>();
            for (int i = 0; i < list.size(); i++) {
                billList.add(new Entry(Float.valueOf(list.get(i).getTotalBill()), i));
                billTitleList.add(list.get(i).getYear());
            }
            showPieChart(bill_pieChart, billList, billTitleList);

            List<Entry> connectedList = new ArrayList<>();
            List<String> connectedTitleList = new ArrayList<>();
            for (int i = 0; i < list.size(); i++) {
                connectedList.add(new Entry(Float.valueOf(list.get(i).getTotalConnectedLoad()), i));
                connectedTitleList.add(list.get(i).getYear());
            }
            showPieChart(connected_load_pieChart, connectedList, connectedTitleList);


            List<Entry> avgList = new ArrayList<>();
            List<String> avgTitleList = new ArrayList<>();
            for (int i = 0; i < list.size(); i++) {
                avgList.add(new Entry(Float.valueOf(list.get(i).getTotalAvgCost()), i));
                avgTitleList.add(list.get(i).getYear());
            }
            showPieChart(avg_pieChart, avgList, avgTitleList);
        }


    }

    private void showPieChart(PieChart pieChart, List<Entry> dataList, List<String> titleList) {
        PieDataSet pieDataSet = new PieDataSet(dataList, "");
        pieDataSet.setValueFormatter(new UnitValueFormatter(""));
        PieData newData = new PieData(titleList, pieDataSet);
        pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        pieDataSet.setValueTextSize(12);//
        pieChart.setDescription("");
        pieChart.setData(newData);
        pieChart.animateY(2000);
    }

    private void callWebService() {
        try {
            ConSummaryRequest request = new ConSummaryRequest();
            request.setRailway(((Railway) spn_ryCode.getSelectedItem()).getRlycode());
            request.setDivision(((Division) spn_divCode.getSelectedItem()).getDivcode());
            request.setFinancialYear((String) spn_fYear.getSelectedItem());
            StringBuilder sb = new StringBuilder();
            List<Month> checkedList = new ArrayList<>();
            for (Month month : monthList) {
                if (month.isCheck())
                    checkedList.add(month);
            }
            if (checkedList.size() == 12) {
            } else {
                for (Month month :
                        checkedList) {
                    sb.append(month.getMonthCode() + ",");
                }
            }
            request.setMonth(sb.toString());
            if (spn_department.getAdapter().getCount() > 0) {
                request.setDepartment(((Department) spn_department.getSelectedItem()).getDepartmentCode());
            }
            request.setNoOfyear((String) spn_no_of_year.getSelectedItem());
            DataHolder.getInstance().setConSummaryRequest(request);
            requestPresenter.Request(request, "Getting Data For Comparison", Constants.THREE_YEAR_CONSUMPTION);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void Error() {
        retryDialog(CompareConsumController.this, "Unable to fetch record. Do you want to retry?");
    }

    @Override
    public void dismissProgress() {
        iv_right.setEnabled(true);
        iv_zoom_cons.setEnabled(true);
        iv_zoom_bill.setEnabled(true);
        iv_connected_load.setEnabled(true);
        iv_avg_cost.setEnabled(true);
        showProgress.setVisibility(View.GONE);
    }

    @Override
    public void showProgress(String msg) {
        if (!showProgress.isShown()) {
            iv_right.setEnabled(false);
            iv_zoom_cons.setEnabled(false);
            iv_zoom_bill.setEnabled(false);
            iv_connected_load.setEnabled(false);
            iv_avg_cost.setEnabled(false);
            showProgress.setVisibility(View.VISIBLE);

        }
    }

    @Override
    public void onClick(View v) {
        StringBuilder sb = new StringBuilder();
        List<Month> checkedList = new ArrayList<>();
        for (Month month : monthList) {
            if (month.isCheck())
                checkedList.add(month);
        }
        if (checkedList.size() == 12) {
            sb.append("All Months");
        } else {
            for (Month month :
                    checkedList) {
                sb.append(month.getMonthName() + ",");
            }
        }
        String year = "\n " + "Fin. Year : " + spn_fYear.getSelectedItem() + " & " + "Month : " + sb.toString();
        switch (v.getId()) {
            case R.id.iv_zoom_cons:
                DataHolder.getInstance().setZoomThreeYearTitle(tv_consumption.getText().toString().trim() + year);
                zoomScreen(Constants.THREE_YEAR_CONS, false);
                break;
            case R.id.iv_zoom_bill:
                DataHolder.getInstance().setZoomThreeYearTitle(tv_billing.getText().toString().trim() + year);
                zoomScreen(Constants.THREE_YEAR_BILL, false);
                break;
            case R.id.iv_connected_load:
                DataHolder.getInstance().setZoomThreeYearTitle(tv_connected_load.getText().toString().trim() + year);
                zoomScreen(Constants.THREE_YEAR_CONNECTED, false);
                break;

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
                if (isValid()) {
                    if (CommonClass.checkInternetConnection(CompareConsumController.this)) {
                        if (slidingPaneLayout.isOpen()) {
                            slidingPaneLayout.closePane();
                        }
                        DataHolder.getInstance().setConSummaryResponses(null);
                        callWebService();
                    } else {
                        commonClass.showToast("No internet available.");
                    }
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
            spn_divCode.setAdapter(new DivisonAdapter(CompareConsumController.this, list));
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

    private boolean isValid() {
        List<Month> checkedList = new ArrayList<>();
        for (Month month : monthList) {
            if (month.isCheck())
                checkedList.add(month);
        }
        if (((String) spn_no_of_year.getSelectedItem()).equalsIgnoreCase("No Of Year.")) {
            commonClass.showToast("Please select no of year");
            return false;
        } else if (checkedList.size() == 0) {
            commonClass.showToast("Please select at least one month.");
            return false;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        if (slidingPaneLayout.isOpen()) {
            slidingPaneLayout.closePane();
        } else {
            WebServices.getInstance().cancelAllRequest();
            if (handler != null && r != null)
                handler.removeCallbacks(r);
            super.onBackPressed();
        }

    }


    private void retryDialog(final Activity activity, String msg) {
        try {
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void zoomScreen(int value, boolean isBarActivity) {
        Intent intent = null;
        if (isBarActivity) {
            intent = new Intent(CompareConsumController.this, DetailOnBarActivity.class);
            if (((Division) spn_divCode.getSelectedItem()).getDivcode().trim().isEmpty())
                intent.putExtra(Constants.UNIT, true);
            else
                intent.putExtra(Constants.UNIT, false);
        } else
            intent = new Intent(CompareConsumController.this, ZoomCompareGraph.class);
        intent.putExtra(Constants.ZOOM_INTENT, value);
        startActivity(intent);
    }


    @Override
    public void checkBoxClick() {
        List<Month> checkedList = new ArrayList<>();
        for (Month month : monthList) {
            if (month.isCheck())
                checkedList.add(month);
        }
        if (checkedList.size() == monthList.size())
            checkbox.setChecked(true);
        else
            checkbox.setChecked(false);
    }


}
