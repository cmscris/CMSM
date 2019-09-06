package com.cris.cmsm;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
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
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.Highlight;
import com.cris.cmsm.database.DataHolder;
import com.cris.cmsm.models.response.BillDashboardDevisonWiseVO;
import com.cris.cmsm.models.response.BillDashboardSEBWiseVO;
import com.cris.cmsm.models.response.BillDashboardStateWiseVO;
import com.cris.cmsm.models.response.BillDashboardTotalVO;
import com.cris.cmsm.models.response.BillingResponse;
import com.cris.cmsm.models.response.ConsumptionResponse;
import com.cris.cmsm.models.response.DashboardDevisonWiseVO;
import com.cris.cmsm.models.response.DashboardSEBWiseVO;
import com.cris.cmsm.models.response.DashboardStateWiseVO;
import com.cris.cmsm.models.response.DashboardTotalVO;
import com.cris.cmsm.util.CommonClass;
import com.cris.cmsm.util.Constants;
import com.cris.cmsm.util.FontFamily;
import com.cris.cmsm.util.UnitValueFormatter;
import com.cris.cmsm.widget.VerticalTextView;

import java.util.ArrayList;
import java.util.List;

/**

 */
public class GraphZoomActivity extends Activity {

    private PieChart pieChart;
    private BarChart barChart;
    private VerticalTextView tv_units;
    private Typeface font;
    private TextView tv_graph_title;
    private LinearLayout linear_bar;
    private CommonClass commonClass;
    private List<BarEntry> barEntryList = new ArrayList<>();
    private List<String> titleList = new ArrayList<>();
    private List<Entry> pieList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zoom_graph);
        commonClass = new CommonClass(GraphZoomActivity.this);
        font = new FontFamily(GraphZoomActivity.this).getBold();
        tv_graph_title = findViewById(R.id.tv_graph_title);
        pieChart = findViewById(R.id.pieChart);
        linear_bar = findViewById(R.id.linear_bar);
        barChart = findViewById(R.id.barChart);
        barChart.getAxisRight().setEnabled(false);
        tv_units = findViewById(R.id.tv_units);
        tv_units.setTypeface(font);
        tv_graph_title.setTypeface(font);
        try {
            tv_graph_title.setText(DataHolder.getInstance().getZoomGraphTitle());
            Intent intent = getIntent();
            if (intent.getIntExtra(Constants.ZOOM_INTENT, -1) < 4) {
                int value = intent.getIntExtra(Constants.ZOOM_INTENT, -1);
                if (intent.getBooleanExtra(Constants.ROLE_ID, false))
                    tv_units.setText("Consumption \n" +
                            "(in million)");
                else
                    tv_units.setText("Consumption");
                setBarChartProperty(barChart, "kWh");
                ConsumptionResponse response = DataHolder.getInstance().getConsumptionResponse();
                if (value == Constants.CONS_TOTAL && response.getDashboardTotalVOs().size() > 0) {
                    pieChart.setVisibility(View.VISIBLE);
                    linear_bar.setVisibility(View.GONE);
                    pieList.clear();
                    titleList.clear();
                    List<DashboardTotalVO> totalList = response.getDashboardTotalVOs();
                    for (int i = 0; i < totalList.size(); i++) {
                        pieList.add(new Entry(Float.valueOf(totalList.get(i).getConsumption() == null ? "0" : totalList.get(i).getConsumption()), i));
                        titleList.add(totalList.get(i).getSname() == null ? "" : totalList.get(i).getSname());
                    }
                    showPieChart(pieList, titleList);
                }

                /***************SEB Bar Chart **************/
                if (value == Constants.CONS_SEB_WISE && response.getDashboardSEBWiseVOs().size() > 0) {
                    barEntryList.clear();
                    titleList.clear();
                    List<DashboardSEBWiseVO> sebWiseList = response.getDashboardSEBWiseVOs();
                    for (int i = 0; i < sebWiseList.size(); i++) {
                        barEntryList.add(new BarEntry(Float.valueOf(sebWiseList.get(i).getConsumption() == null ? "0" : sebWiseList.get(i).getConsumption()), i));
                        titleList.add(sebWiseList.get(i).getSeb() == null ? "" : sebWiseList.get(i).getSeb());
                    }

                    showBarChart("SEB", barEntryList, titleList);
                }

                /***************State Bar Chart **************/
                if (value == Constants.CONS_STATE_WISE && response.getDashboardStateWiseVOs().size() > 0) {
                    barEntryList.clear();
                    titleList.clear();
                    List<DashboardStateWiseVO> stateWiseList = response.getDashboardStateWiseVOs();
                    for (int i = 0; i < stateWiseList.size(); i++) {
                        barEntryList.add(new BarEntry(Float.valueOf(stateWiseList.get(i).getConsumption() == null ? "0" : stateWiseList.get(i).getConsumption()), i));
                        titleList.add(stateWiseList.get(i).getState() == null ? "" : stateWiseList.get(i).getState());
                    }

                    showBarChart("State", barEntryList, titleList);
                }
                /***************Division Bar Chart **************/
                if (value == Constants.CONS_DIVISION_WISE && response.getDashboardDevisonWiseVOs().size() > 0) {
                    barEntryList.clear();
                    titleList.clear();
                    List<DashboardDevisonWiseVO> divisionWiseList = response.getDashboardDevisonWiseVOs();
                    for (int i = 0; i < divisionWiseList.size(); i++) {
                        barEntryList.add(new BarEntry(Float.valueOf(divisionWiseList.get(i).getConsumption() == null ? "0" : divisionWiseList.get(i).getConsumption()), i));
                        titleList.add(divisionWiseList.get(i).getDivision() == null ? "" : divisionWiseList.get(i).getDivision());
                    }

                    showBarChart(DataHolder.getInstance().getSebDiv(), barEntryList, titleList);
                }
            } else {
                int value = intent.getIntExtra(Constants.ZOOM_INTENT, -1);
                BillingResponse response = DataHolder.getInstance().getBillingResponse();
                if (intent.getBooleanExtra(Constants.ROLE_ID, false))
                    tv_units.setText("  Billing \n" +
                            "(in million)");
                else
                    tv_units.setText("  Billing");
                setBarChartProperty(barChart, "Rs.");
                if (value == Constants.BILL_TOTAL && response.getDashboardTotalVOs().size() > 0) {
                    pieChart.setVisibility(View.VISIBLE);
                    linear_bar.setVisibility(View.GONE);
                    pieList.clear();
                    titleList.clear();
                    List<BillDashboardTotalVO> totalList = response.getDashboardTotalVOs();
                    for (int i = 0; i < totalList.size(); i++) {
                        pieList.add(new Entry(Float.valueOf(totalList.get(i).getBiliing() == null ? "0" : totalList.get(i).getBiliing()), i));
                        titleList.add(totalList.get(i).getSname() == null ? "" : totalList.get(i).getSname());
                    }

                    showPieChart(pieList, titleList);
                }

                /***************SEB Bar Chart **************/
                if (value == Constants.BILL_SEB_WISE && response.getDashboardSEBWiseVOs().size() > 0) {
                    barEntryList.clear();
                    titleList.clear();
                    List<BillDashboardSEBWiseVO> sebWiseList = response.getDashboardSEBWiseVOs();
                    for (int i = 0; i < sebWiseList.size(); i++) {
                        barEntryList.add(new BarEntry(Float.valueOf(sebWiseList.get(i).getBilling() == null ? "0" : sebWiseList.get(i).getBilling()), i));
                        titleList.add(sebWiseList.get(i).getSeb() == null ? "" : sebWiseList.get(i).getSeb());
                    }
                    showBarChart("SEB", barEntryList, titleList);
                }

                /***************State Bar Chart **************/
                if (value == Constants.BILL_STATE_WISE && response.getDashboardStateWiseVOs().size() > 0) {
                    barEntryList.clear();
                    titleList.clear();
                    List<BillDashboardStateWiseVO> stateWiseList = response.getDashboardStateWiseVOs();
                    for (int i = 0; i < stateWiseList.size(); i++) {
                        barEntryList.add(new BarEntry(Float.valueOf(stateWiseList.get(i).getBilling() == null ? "0" : stateWiseList.get(i).getBilling()), i));
                        titleList.add(stateWiseList.get(i).getState() == null ? "" : stateWiseList.get(i).getState());
                    }

                    showBarChart("State", barEntryList, titleList);
                }
                /***************Division Bar Chart **************/
                if (value == Constants.BILL_DIVISION_WISE && response.getDashboardDevisonWiseVOs().size() > 0) {
                    barEntryList.clear();
                    titleList.clear();
                    List<BillDashboardDevisonWiseVO> divisionWiseList = response.getDashboardDevisonWiseVOs();
                    for (int i = 0; i < divisionWiseList.size(); i++) {
                        barEntryList.add(new BarEntry(Float.valueOf(divisionWiseList.get(i).getBilling() == null ? "0" : divisionWiseList.get(i).getBilling()), i));
                        titleList.add(divisionWiseList.get(i).getDivision() == null ? "" : divisionWiseList.get(i).getDivision());
                    }

                    showBarChart(DataHolder.getInstance().getSebDiv(), barEntryList, titleList);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        barChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {
                if (titleList.size() > 0)
                    commonClass.showToast(titleList.get(e.getXIndex()) + " - " + e.getVal());
            }

            @Override
            public void onNothingSelected() {

            }
        });

        pieChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {
                if (titleList.size() > 0)
                    commonClass.showToast(titleList.get(e.getXIndex()) + " - " + e.getVal());

            }

            @Override
            public void onNothingSelected() {

            }
        });

    }


    private void showPieChart(List<Entry> dataList, List<String> titleList) {
        PieDataSet pieDataSet = new PieDataSet(dataList, "");
        pieDataSet.setValueFormatter(new UnitValueFormatter(""));
        PieData newData = new PieData(titleList, pieDataSet);
        pieDataSet.setColors(CommonClass.COLORFUL_COLORS); //
        pieDataSet.setValueTextSize(12);
        pieChart.setDescription("");
        pieChart.setData(newData);
        pieChart.animateY(2000);
    }

    private void setBarChartProperty(BarChart barChart, String unit) {
        barChart.getAxisRight().setEnabled(false);
        barChart.getAxisLeft().setValueFormatter(new UnitValueFormatter(unit));
        XAxis xl = barChart.getXAxis();
        xl.setPosition(XAxis.XAxisPosition.BOTTOM);
        xl.setDrawAxisLine(true);
    }

    private void showBarChart(String legends, List<BarEntry> dataList, List<String> titleList) {
        BarDataSet barDataSet = new BarDataSet(dataList, legends);
        barDataSet.setValueFormatter(new UnitValueFormatter(""));
        barDataSet.setColors(CommonClass.COLORFUL_COLORS);
        barDataSet.setValueTextSize(12);
        List<BarDataSet> dataSet = new ArrayList<>();
        dataSet.add(barDataSet);
        BarData data = new BarData(titleList, dataSet);
        barChart.setData(data);
        barChart.setDescription("");
        barChart.animateXY(1000, 1000);
        barChart.invalidate();
    }


}
