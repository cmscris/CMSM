package com.cris.cmsm.tabviews;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
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
import com.cris.cmsm.interfaces.IConsumption;
import com.cris.cmsm.interfaces.IOnFragmentVisible;
import com.cris.cmsm.models.Division;
import com.cris.cmsm.models.Month;
import com.cris.cmsm.models.Railway;
import com.cris.cmsm.models.response.ConsumptionResponse;
import com.cris.cmsm.models.response.DashboardDevisonWiseVO;
import com.cris.cmsm.models.response.DashboardSEBWiseVO;
import com.cris.cmsm.models.response.DashboardStateWiseVO;
import com.cris.cmsm.models.response.DashboardTotalVO;
import com.cris.cmsm.navcontrollers.GraphController;
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
public class ConsumptionGraphFragment extends Fragment implements IConsumption, IOnFragmentVisible, View.OnClickListener {
    private PieChart pieChart;
    private BarChart bc_consumption_se, bc_consumption_state, bc_consumption_division;
    private TextView tv_total, tv_seb, tv_state, tv_division;
    private Typeface font;
    private ImageView iv_zoom1, iv_zoom2, iv_zoom3, iv_zoom4;

    private TextView tv_total_unit;
    private VerticalTextView tv_seb_unit, tv_state_unit, tv_division_unit;
    private AppCompatSpinner spn_ryCode, spn_divCode, spn_month;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ((GraphController) getActivity()).initializeConsumption(ConsumptionGraphFragment.this);
        View view = inflater.inflate(R.layout.fragment_graph, container, false);
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
        bc_consumption_state = view.findViewById(R.id.bc_consumption_state);
        bc_consumption_division = view.findViewById(R.id.bc_consumption_division);
        bc_consumption_se = view.findViewById(R.id.bc_consumption_se);
        iv_zoom1 = view.findViewById(R.id.iv_zoom1);
        iv_zoom2 = view.findViewById(R.id.iv_zoom2);
        iv_zoom3 = view.findViewById(R.id.iv_zoom3);
        iv_zoom4 = view.findViewById(R.id.iv_zoom4);

        setBarChartProperty(bc_consumption_state);
        setBarChartProperty(bc_consumption_division);
        setBarChartProperty(bc_consumption_se);

        tv_seb_unit = view.findViewById(R.id.tv_seb_unit);
        tv_state_unit = view.findViewById(R.id.tv_state_unit);
        tv_division_unit = view.findViewById(R.id.tv_division_unit);

        tv_total.setText("Consumption");
        tv_seb.setText("Consumption-SEB Wise");
        tv_state.setText("Consumption-State Wise");
        tv_total.setTypeface(font);
        tv_seb.setTypeface(font);
        tv_state.setTypeface(font);
        tv_division.setTypeface(font);

        tv_total_unit.setTypeface(font);
        tv_seb_unit.setTypeface(font);
        tv_state_unit.setTypeface(font);
        tv_division_unit.setTypeface(font);

        tv_seb_unit.setText("Consumption");
        tv_state_unit.setText("Consumption");
        tv_division_unit.setText("Consumption");

        iv_zoom1.setOnClickListener(this);
        iv_zoom2.setOnClickListener(this);
        iv_zoom3.setOnClickListener(this);
        iv_zoom4.setOnClickListener(this);
        showSpinnerText((Division) spn_divCode.getSelectedItem());
        fragmentBecameVisible();
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
        barChart.getAxisLeft().setValueFormatter(new UnitValueFormatter("kWh"));
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
    public void showConsumptionResponse(ConsumptionResponse response) {
        try {
            if (response != null) {
                enableControls();
                DataHolder.getInstance().setConsumptionResponse(response);
                /***************Pie Chart **************/
                double TotalEnergy = 0;
                if (response.getDashboardTotalVOs() != null && response.getDashboardTotalVOs().size() > 0) {
                    List<Entry> pieList = new ArrayList<>();
                    List<String> titleList = new ArrayList<>();
                    List<DashboardTotalVO> totalList = response.getDashboardTotalVOs();
                    for (int i = 0; i < totalList.size(); i++) {
                        TotalEnergy = TotalEnergy + (Float.valueOf(totalList.get(i).getConsumption() == null ? "0" : totalList.get(i).getConsumption()));
                        pieList.add(new Entry(Float.valueOf(totalList.get(i).getConsumption() == null ? "0" : totalList.get(i).getConsumption()), i));
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
                    List<DashboardSEBWiseVO> sebWiseList = response.getDashboardSEBWiseVOs();
                    for (int i = 0; i < sebWiseList.size(); i++) {
                        sebList.add(new BarEntry(Float.valueOf(sebWiseList.get(i).getConsumption() == null ? "0" : sebWiseList.get(i).getConsumption()), i));
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
                    List<DashboardStateWiseVO> stateWiseList = response.getDashboardStateWiseVOs();
                    for (int i = 0; i < stateWiseList.size(); i++) {
                        stateList.add(new BarEntry(Float.valueOf(stateWiseList.get(i).getConsumption() == null ? "0" : stateWiseList.get(i).getConsumption()), i));
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
                    List<DashboardDevisonWiseVO> divisionWiseList = response.getDashboardDevisonWiseVOs();
                    for (int i = 0; i < divisionWiseList.size(); i++) {
                        divisionList.add(new BarEntry(Float.valueOf(divisionWiseList.get(i).getConsumption() == null ? "0" : divisionWiseList.get(i).getConsumption()), i));
                        titleList.add(divisionWiseList.get(i).getDivision() == null ? "" : divisionWiseList.get(i).getDivision());
                    }

                    if (((Railway) spn_ryCode.getSelectedItem()).getRlycode().equals("")) {
                        tv_division.setText("Consumption-Division Wise");
                        DataHolder.getInstance().setSebDiv("Division");
                        showBarChart(bc_consumption_division, "Division", divisionList, titleList);
                    } else if (((Division) spn_divCode.getSelectedItem()).getDivcode().equals("")) {
                        tv_division.setText("Consumption-DEE/ADEE Wise");
                        DataHolder.getInstance().setSebDiv("DEE/ADEE");
                        showBarChart(bc_consumption_division, "DEE/ADEE", divisionList, titleList);
                    } else {
                        tv_division.setText("Consumption-SSE Wise");
                        DataHolder.getInstance().setSebDiv("SSE");
                        showBarChart(bc_consumption_division, "SSE", divisionList, titleList);
                    }
                } else {
                    if (((Railway) spn_ryCode.getSelectedItem()).getRlycode().equals("")) {
                        tv_division.setText("Consumption-Division Wise");
                        DataHolder.getInstance().setSebDiv("Division");
                    } else if (((Division) spn_divCode.getSelectedItem()).getDivcode().equals("")) {
                        tv_division.setText("Consumption-DEE/ADEE Wise");
                        DataHolder.getInstance().setSebDiv("DEE/ADEE");
                    } else {
                        tv_division.setText("Consumption-SSE Wise");
                        DataHolder.getInstance().setSebDiv("SSE");

                    }

                    if (bc_consumption_division != null)
                        bc_consumption_division.clear();
                }
                if (!TextUtils.isEmpty(((Division) spn_divCode.getSelectedItem()).getDivcode()) && !TextUtils.isEmpty(((Month) spn_month.getSelectedItem()).getMonthCode())) {
                    tv_seb_unit.setText("Consumption");
                    tv_state_unit.setText("Consumption");
                    tv_division_unit.setText("Consumption");
                    tv_total_unit.setText(TotalEnergy > 0 ? "Total Energy Consumption is " + CommonClass.getRoundOff(Double.valueOf(CommonClass.getRoundOff("" + TotalEnergy)) / 1000000) + " million units(kWh)" : "");
                } else {
                    tv_seb_unit.setText("Consumption \n" +
                            "(in million)");
                    tv_state_unit.setText("Consumption \n" +
                            "(in million)");
                    tv_division_unit.setText("Consumption \n" +
                            "(in million)");
                    tv_total_unit.setText(TotalEnergy > 0 ? "Total Energy Consumption is " + CommonClass.getRoundOff(Double.valueOf(CommonClass.getRoundOff("" + TotalEnergy))) + " million units(kWh)" : "");
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
            tv_division.setText("Consumption-Division Wise");
        } else if (division != null) {
            tv_division.setText("Consumption-SSE Wise");
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
        try {
            if (DataHolder.getInstance().getConsumptionResponse() != null) {
                showConsumptionResponse(DataHolder.getInstance().getConsumptionResponse());
            } else {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ((GraphController) getActivity()).callWebService();
                    }
                }, 300);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_zoom1:
                DataHolder.getInstance().setZoomGraphTitle(tv_total.getText().toString().trim() + "\n" + tv_total_unit.getText().toString().trim());
                zoomScreen(Constants.CONS_TOTAL);
                break;
            case R.id.iv_zoom2:
                DataHolder.getInstance().setZoomGraphTitle(tv_seb.getText().toString().trim());
                zoomScreen(Constants.CONS_SEB_WISE);
                break;
            case R.id.iv_zoom3:
                DataHolder.getInstance().setZoomGraphTitle(tv_state.getText().toString().trim());
                zoomScreen(Constants.CONS_STATE_WISE);
                break;
            case R.id.iv_zoom4:
                DataHolder.getInstance().setZoomGraphTitle(tv_division.getText().toString().trim());
                zoomScreen(Constants.CONS_DIVISION_WISE);
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
