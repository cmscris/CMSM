package com.cris.cmsm.tabviews;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatSpinner;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.cris.cmsm.GraphZoomActivity;
import com.cris.cmsm.R;
import com.cris.cmsm.database.DataHolder;
import com.cris.cmsm.interfaces.IBilling;
import com.cris.cmsm.interfaces.IOnFragmentVisible;
import com.cris.cmsm.models.Division;
import com.cris.cmsm.models.Month;
import com.cris.cmsm.models.Railway;
import com.cris.cmsm.models.response.BillDashboardDevisonWiseVO;
import com.cris.cmsm.models.response.BillDashboardSEBWiseVO;
import com.cris.cmsm.models.response.BillDashboardStateWiseVO;
import com.cris.cmsm.models.response.BillDashboardTotalVO;
import com.cris.cmsm.models.response.BillingResponse;
import com.cris.cmsm.models.response.LoginIfoVO;
import com.cris.cmsm.navcontrollers.GraphController;
import com.cris.cmsm.prefrences.UserLoginPreferences;
import com.cris.cmsm.util.CommonClass;
import com.cris.cmsm.util.Constants;
import com.cris.cmsm.util.FontFamily;
import com.cris.cmsm.util.UnitValueFormatter;
import com.cris.cmsm.widget.VerticalTextView;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class BillingGraphFragment extends Fragment implements IBilling, IOnFragmentVisible, View.OnClickListener {
    private PieChart pieChart;
    private BarChart bc_consumption_se, bc_consumption_state, bc_consumption_division;
    private TextView tv_total, tv_seb, tv_state, tv_division;
    private Typeface font;
    private ImageView iv_zoom1, iv_zoom2, iv_zoom3, iv_zoom4;
    private TextView tv_total_unit;
    private AppCompatSpinner spn_ryCode, spn_divCode, spn_month;
    private VerticalTextView tv_seb_unit, tv_state_unit, tv_division_unit;
    private LoginIfoVO loginInfoModel;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ((GraphController) getActivity()).initializeBilling(BillingGraphFragment.this);
        View view = inflater.inflate(R.layout.fragment_graph, container, false);
        loginInfoModel = new UserLoginPreferences(getActivity()).getLoginUser();
        font = new FontFamily(getActivity()).getBold();
        spn_ryCode = getActivity().findViewById(R.id.spn_ryCode);
        spn_divCode = getActivity().findViewById(R.id.spn_divCode);
        spn_month = getActivity().findViewById(R.id.spn_month);
        pieChart = view.findViewById(R.id.pieChart);

        tv_total_unit = view.findViewById(R.id.tv_total_unit);

        tv_total = view.findViewById(R.id.tv_total);
        tv_seb = view.findViewById(R.id.tv_seb);
        tv_state = view.findViewById(R.id.tv_state);
        tv_division = view.findViewById(R.id.tv_division);

        iv_zoom1 = view.findViewById(R.id.iv_zoom1);
        iv_zoom2 = view.findViewById(R.id.iv_zoom2);
        iv_zoom3 = view.findViewById(R.id.iv_zoom3);
        iv_zoom4 = view.findViewById(R.id.iv_zoom4);

        tv_seb_unit = view.findViewById(R.id.tv_seb_unit);
        tv_state_unit = view.findViewById(R.id.tv_state_unit);
        tv_division_unit = view.findViewById(R.id.tv_division_unit);

        tv_total.setText("Billing");
        tv_seb.setText("Billing-SEB Wise");
        tv_state.setText("Billing-State Wise");


        tv_total.setTypeface(font);
        tv_seb.setTypeface(font);
        tv_state.setTypeface(font);
        tv_division.setTypeface(font);

        tv_total_unit.setTypeface(font);
        tv_seb_unit.setTypeface(font);
        tv_state_unit.setTypeface(font);
        tv_division_unit.setTypeface(font);

        tv_seb_unit.setText("  Billing");
        tv_state_unit.setText("  Billing");
        tv_division_unit.setText("  Billing");

        iv_zoom1.setOnClickListener(this);
        iv_zoom2.setOnClickListener(this);
        iv_zoom3.setOnClickListener(this);
        iv_zoom4.setOnClickListener(this);

        bc_consumption_state = view.findViewById(R.id.bc_consumption_state);
        bc_consumption_division = view.findViewById(R.id.bc_consumption_division);
        bc_consumption_se = view.findViewById(R.id.bc_consumption_se);

        setBarChartProperty(bc_consumption_state);
        setBarChartProperty(bc_consumption_division);
        setBarChartProperty(bc_consumption_se);
        showSpinnerText((Division) spn_divCode.getSelectedItem());
        if (loginInfoModel.getAuthlevel().equalsIgnoreCase(Constants.AUTH_BOARD) && DataHolder.getInstance().getBillingResponse() != null) {
            fragmentBecameVisible();
        }
        return view;
    }

    private void showPieChart(List<Entry> dataList, List<String> titleList) {
        PieDataSet pieDataSet = new PieDataSet(dataList, "");
        pieDataSet.setValueFormatter(new UnitValueFormatter(""));
        PieData newData = new PieData(titleList, pieDataSet);
        pieDataSet.setColors(CommonClass.COLORFUL_COLORS);
        pieChart.setDescription("");
        pieChart.setData(newData);
        pieChart.animateY(2000);
    }

    private void setBarChartProperty(BarChart barChart) {
        barChart.getAxisRight().setEnabled(false);
        barChart.getAxisLeft().setValueFormatter(new UnitValueFormatter("Rs."));
        XAxis xl = barChart.getXAxis();
        xl.setPosition(XAxis.XAxisPosition.BOTTOM);
        xl.setDrawAxisLine(true);
    }

    private void showBarChart(BarChart barChart, String legends, List<BarEntry> dataList, List<String> titleList) {
        BarDataSet barDataSet = new BarDataSet(dataList, legends);
        barDataSet.setValueFormatter(new UnitValueFormatter(""));
        barDataSet.setColors(CommonClass.COLORFUL_COLORS);
        List<BarDataSet> dataSet = new ArrayList<>();
        dataSet.add(barDataSet);
        BarData data = new BarData(titleList, dataSet);
        barChart.setData(data);
        barChart.setDescription("");
        barChart.animateXY(1000, 1000);
        barChart.invalidate();

    }

    @Override
    public void showBillingResponse(BillingResponse response) {
        try {
            if (response != null) {
                /***************Pie Chart **************/
                double TotalBilling = 0;
                DataHolder.getInstance().setBillingResponse(response);
                enableControls();
                if (response.getDashboardTotalVOs() != null && response.getDashboardTotalVOs().size() > 0) {
                    List<Entry> pieList = new ArrayList<>();
                    List<String> titleList = new ArrayList<>();
                    List<BillDashboardTotalVO> totalList = response.getDashboardTotalVOs();
                    for (int i = 0; i < totalList.size(); i++) {
                        TotalBilling = TotalBilling + (Float.valueOf(totalList.get(i).getBiliing() == null ? "0" : totalList.get(i).getBiliing()));
                        pieList.add(new Entry(Float.valueOf(totalList.get(i).getBiliing() == null ? "0" : totalList.get(i).getBiliing()), i));
                        titleList.add(totalList.get(i).getSname() == null ? "" : totalList.get(i).getSname());
                    }
                    showPieChart(pieList, titleList);

                } else {
                    if (pieChart != null)
                        pieChart.clear();
                }


                /***************SEB Bar Chart **************/
                if (response.getDashboardSEBWiseVOs() != null && response.getDashboardSEBWiseVOs().size() > 0) {
                    List<BarEntry> sebList = new ArrayList<>();
                    List<String> titleList = new ArrayList<>();
                    List<BillDashboardSEBWiseVO> sebWiseList = response.getDashboardSEBWiseVOs();
                    for (int i = 0; i < sebWiseList.size(); i++) {
                        sebList.add(new BarEntry(Float.valueOf(sebWiseList.get(i).getBilling() == null ? "0" : sebWiseList.get(i).getBilling()), i));
                        titleList.add(sebWiseList.get(i).getSeb() == null ? "" : sebWiseList.get(i).getSeb());
                    }
                    showBarChart(bc_consumption_se, "SEB", sebList, titleList);
                } else {
                    if (bc_consumption_se != null)
                        bc_consumption_se.clear();
                }

                /***************State Bar Chart **************/
                if (response.getDashboardStateWiseVOs() != null && response.getDashboardStateWiseVOs().size() > 0) {
                    List<BarEntry> stateList = new ArrayList<>();
                    List<String> titleList = new ArrayList<>();
                    List<BillDashboardStateWiseVO> stateWiseList = response.getDashboardStateWiseVOs();
                    for (int i = 0; i < stateWiseList.size(); i++) {
                        stateList.add(new BarEntry(Float.valueOf(stateWiseList.get(i).getBilling() == null ? "0" : stateWiseList.get(i).getBilling()), i));
                        titleList.add(stateWiseList.get(i).getState() == null ? "" : stateWiseList.get(i).getState());
                    }

                    showBarChart(bc_consumption_state, "State", stateList, titleList);
                } else {
                    if (bc_consumption_state != null)
                        bc_consumption_state.clear();
                }
                /***************Division Bar Chart **************/
                if (response.getDashboardDevisonWiseVOs() != null && response.getDashboardDevisonWiseVOs().size() > 0) {
                    List<BarEntry> divisionList = new ArrayList<>();
                    List<String> titleList = new ArrayList<>();
                    List<BillDashboardDevisonWiseVO> divisionWiseList = response.getDashboardDevisonWiseVOs();
                    for (int i = 0; i < divisionWiseList.size(); i++) {
                        divisionList.add(new BarEntry(Float.valueOf(divisionWiseList.get(i).getBilling() == null ? "0" : divisionWiseList.get(i).getBilling()), i));
                        titleList.add(divisionWiseList.get(i).getDivision() == null ? "" : divisionWiseList.get(i).getDivision());
                    }

                    if (((Railway) spn_ryCode.getSelectedItem()).getRlycode().equals("")) {
                        tv_division.setText("Billing-Division Wise");
                        showBarChart(bc_consumption_division, "Division", divisionList, titleList);
                    }else if (((Division) spn_divCode.getSelectedItem()).getDivcode().equals("")) {
                        tv_division.setText("Billing-DEE/ADEE Wise");
                        showBarChart(bc_consumption_division, "DEE/ADEE", divisionList, titleList);
                    } else {
                        tv_division.setText("Billing-SSE Wise");
                        showBarChart(bc_consumption_division, "SSE", divisionList, titleList);
                    }
                } else {
                    if (((Railway) spn_ryCode.getSelectedItem()).getRlycode().equals("")) {
                        tv_division.setText("Billing-Division Wise");
                    }else if (((Division) spn_divCode.getSelectedItem()).getDivcode().equals("")) {
                        tv_division.setText("Billing-DEE/ADEE Wise");
                    } else {
                        tv_division.setText("Billing-SSE Wise");
                    }
                    if (bc_consumption_division != null)
                        bc_consumption_division.clear();
                }

                if (!TextUtils.isEmpty(((Division) spn_divCode.getSelectedItem()).getDivcode()) && !TextUtils.isEmpty(((Month) spn_month.getSelectedItem()).getMonthCode())) {
                    tv_seb_unit.setText("  Billing");
                    tv_state_unit.setText("  Billing");
                    tv_division_unit.setText("  Billing");
                    tv_total_unit.setText(TotalBilling > 0 ? "Total Bill Paid is " + CommonClass.getRoundOff(Double.valueOf(CommonClass.getRoundOff("" + TotalBilling)) / 1000000) + " million Rs." : "");
                } else {
                    tv_seb_unit.setText("  Billing \n" +
                            "(in million)");
                    tv_state_unit.setText("  Billing \n" +
                            "(in million)");
                    tv_division_unit.setText("  Billing \n" +
                            "(in million)");
                    tv_total_unit.setText(TotalBilling > 0 ? "Total Bill Paid is " + CommonClass.getRoundOff(Double.valueOf(CommonClass.getRoundOff("" + TotalBilling))) + " million Rs." : "");

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            ((GraphController) getActivity()).retryDialog(getActivity(), "Unable to fetch record. Do you want to retry?");
        }
    }

    @Override
    public void showSpinnerText(Division division) {
        if (division != null && division.getDivcode().equals("")) {
            tv_division.setText("Billing-Division Wise");
        } else if (division != null) {
            tv_division.setText("Billing-SSE Wise");
        }
    }

    @Override
    public void disableControls() {
        iv_zoom1.setEnabled(false);
        iv_zoom2.setEnabled(false);
        iv_zoom3.setEnabled(false);
        iv_zoom4.setEnabled(false);
    }


    private void enableControls() {
        iv_zoom1.setEnabled(true);
        iv_zoom2.setEnabled(true);
        iv_zoom3.setEnabled(true);
        iv_zoom4.setEnabled(true);
    }


    @Override
    public void fragmentBecameVisible() {
        if (DataHolder.getInstance().getBillingResponse() != null) {
            showBillingResponse(DataHolder.getInstance().getBillingResponse());
        } else {
            ((GraphController) getActivity()).callWebService();
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_zoom1:
                DataHolder.getInstance().setZoomGraphTitle(tv_total.getText().toString().trim() + "\n" + tv_total_unit.getText().toString().trim());
                zoomScreen(Constants.BILL_TOTAL);
                break;
            case R.id.iv_zoom2:
                DataHolder.getInstance().setZoomGraphTitle(tv_seb.getText().toString().trim());
                zoomScreen(Constants.BILL_SEB_WISE);
                break;
            case R.id.iv_zoom3:
                DataHolder.getInstance().setZoomGraphTitle(tv_state.getText().toString().trim());
                zoomScreen(Constants.BILL_STATE_WISE);
                break;
            case R.id.iv_zoom4:
                DataHolder.getInstance().setZoomGraphTitle(tv_division.getText().toString().trim());
                zoomScreen(Constants.BILL_DIVISION_WISE);
                break;

        }
    }


    private void zoomScreen(int value) {
        Intent intent = new Intent(getActivity(), GraphZoomActivity.class);
        intent.putExtra(Constants.ZOOM_INTENT, value);
        if (!TextUtils.isEmpty(((Division) spn_divCode.getSelectedItem()).getDivcode()) && !TextUtils.isEmpty(((Month) spn_month.getSelectedItem()).getMonthCode()))
            intent.putExtra(Constants.ROLE_ID, false);
        else
            intent.putExtra(Constants.ROLE_ID, true);
        getActivity().startActivity(intent);
    }

}
