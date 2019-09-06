package com.cris.cmsm;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.cris.cmsm.database.DataHolder;
import com.cris.cmsm.models.response.GraphG1vo;
import com.cris.cmsm.models.response.GraphG2vo;
import com.cris.cmsm.models.response.ResMonthlyBill;
import com.cris.cmsm.models.response.ResMonthlyCons;
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

public class MonthZoomActivity extends Activity {
    private TextView tv_graph_title;
    private VerticalTextView tv_units, tv_line_cons;
    private BarChart barChart;
    private LineChart lineChart;
    private LinearLayout linear_bar, linear_line;
    private Typeface font;
    private List<Entry> lineCharList = new ArrayList<>();
    private List<BarEntry> graphList = new ArrayList<>();
    private List<String> titleList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_month_graph);
        font = new FontFamily(MonthZoomActivity.this).getBold();
        tv_graph_title = findViewById(R.id.tv_graph_title);
        tv_units = findViewById(R.id.tv_units);
        tv_line_cons = findViewById(R.id.tv_line_cons);
        barChart = findViewById(R.id.barChart);
        lineChart = findViewById(R.id.lineChart);
        linear_bar = findViewById(R.id.linear_bar);
        linear_line = findViewById(R.id.linear_line);

        tv_graph_title.setTypeface(font);
        tv_units.setTypeface(font);
        tv_line_cons.setTypeface(font);


        Intent intent = getIntent();
        switch (intent.getIntExtra(Constants.ZOOM_INTENT, -1)) {
            case Constants.MONTH_BAR_CONS:
                tv_graph_title.setText("Monthly Consumption");
                if (TextUtils.isEmpty(intent.getStringExtra(Constants.ROLE_ID)))
                    tv_units.setText("Consumption \n(in million)");
                else
                    tv_units.setText("Consumption");
                linear_bar.setVisibility(View.VISIBLE);
                setLineChartProperty(lineChart, "kWh");
                setBarChartProperty(barChart, "kWh");
                ResMonthlyCons barResponse = DataHolder.getInstance().getResMonthlyCons();
                if (barResponse != null && barResponse.getIsSuccess()) {
                    List<GraphG1vo> graphG1voList = barResponse.getGraphG1vos();
                    if (graphG1voList != null && graphG1voList.size() > 0) {
                        for (int i = 0; i < graphG1voList.size(); i++) {
                            graphList.add(new BarEntry(Float.valueOf(graphG1voList.get(i).getConValue() == null ? "0" : graphG1voList.get(i).getConValue()), i));
                            titleList.add(graphG1voList.get(i).getMonth() == null ? "" : graphG1voList.get(i).getMonth());
                        }
                    } else if (barChart != null) {
                        barChart.clear();
                    }
                    if (titleList.size() > 0) {
                        showBarChart(barChart, "MONTHS", graphList, titleList);

                    }
                }

                break;
            case Constants.MONTH_LINE_CONS:
                tv_graph_title.setText("Monthly Consumption");
                if (TextUtils.isEmpty(intent.getStringExtra(Constants.ROLE_ID)))
                    tv_line_cons.setText("Consumption \n(in million)");
                else
                    tv_line_cons.setText("Consumption");
                linear_line.setVisibility(View.VISIBLE);
                setLineChartProperty(lineChart, "kWh");
                setBarChartProperty(barChart, "kWh");
                ResMonthlyCons lineResponse = DataHolder.getInstance().getResMonthlyCons();
                if (lineResponse != null && lineResponse.getIsSuccess()) {
                    List<GraphG1vo> graphG1voList = lineResponse.getGraphG1vos();
                    if (graphG1voList != null && graphG1voList.size() > 0) {
                        for (int i = 0; i < graphG1voList.size(); i++) {
                            lineCharList.add(new Entry(Float.valueOf(graphG1voList.get(i).getConValue() == null ? "0" : graphG1voList.get(i).getConValue()), i));
                            titleList.add(graphG1voList.get(i).getMonth() == null ? "" : graphG1voList.get(i).getMonth());
                        }
                    } else if (lineChart != null) {
                        lineChart.clear();
                    }
                    if (titleList.size() > 0) {
                        showLineChart(lineChart, "MONTHS", lineCharList, titleList);
                    }
                }
                break;
            case Constants.MONTH_BAR_BILL:
                tv_graph_title.setText("Monthly Billing");
                if (TextUtils.isEmpty(intent.getStringExtra(Constants.ROLE_ID)))
                    tv_units.setText("Billing \n(in million)");
                else
                    tv_units.setText("Billing");
                linear_bar.setVisibility(View.VISIBLE);
                setLineChartProperty(lineChart, "Rs.");
                setBarChartProperty(barChart, "Rs.");
                ResMonthlyBill monthBarResponse = DataHolder.getInstance().getResMonthlyBill();
                if (monthBarResponse != null && monthBarResponse.getIsSuccess()) {
                    List<GraphG2vo> graphG1voList = monthBarResponse.getGraphG2vos();
                    if (graphG1voList != null && graphG1voList.size() > 0) {
                        for (int i = 0; i < graphG1voList.size(); i++) {
                            graphList.add(new BarEntry(Float.valueOf(graphG1voList.get(i).getBilValue() == null ? "0" : graphG1voList.get(i).getBilValue()), i));
                            titleList.add(graphG1voList.get(i).getMonth() == null ? "" : graphG1voList.get(i).getMonth());
                        }
                    } else if (barChart != null) {
                        barChart.clear();
                    }
                    if (titleList.size() > 0) {
                        showBarChart(barChart, "MONTHS", graphList, titleList);
                    }
                }
                break;
            case Constants.MONTH_LINE_BILL:
                tv_graph_title.setText("Monthly Billing");
                if (TextUtils.isEmpty(intent.getStringExtra(Constants.ROLE_ID)))
                    tv_line_cons.setText("Billing \n(in million)");
                else
                    tv_line_cons.setText("Billing");
                linear_line.setVisibility(View.VISIBLE);
                setLineChartProperty(lineChart, "Rs.");
                setBarChartProperty(barChart, "Rs.");
                ResMonthlyBill monthLineResponse = DataHolder.getInstance().getResMonthlyBill();
                if (monthLineResponse != null && monthLineResponse.getIsSuccess()) {
                    List<GraphG2vo> graphG1voList = monthLineResponse.getGraphG2vos();
                    if (graphG1voList != null && graphG1voList.size() > 0) {
                        for (int i = 0; i < graphG1voList.size(); i++) {
                            lineCharList.add(new Entry(Float.valueOf(graphG1voList.get(i).getBilValue() == null ? "0" : graphG1voList.get(i).getBilValue()), i));
                            titleList.add(graphG1voList.get(i).getMonth() == null ? "" : graphG1voList.get(i).getMonth());
                        }
                    } else if (lineChart != null) {
                        lineChart.clear();
                    }

                    if (titleList.size() > 0) {
                        showLineChart(lineChart, "MONTHS", lineCharList, titleList);
                    }
                }
                break;
            default:
                break;
        }
    }


    private void setLineChartProperty(LineChart lineChartProperty, String unit) {
        lineChartProperty.getAxisRight().setEnabled(false);
        lineChartProperty.getAxisLeft().setValueFormatter(new UnitValueFormatter(unit));
        XAxis xl = lineChartProperty.getXAxis();
        xl.setPosition(XAxis.XAxisPosition.BOTTOM);
        xl.setDrawAxisLine(true);
    }

    private void setBarChartProperty(BarChart barChart, String unit) {
        barChart.getAxisRight().setEnabled(false);
        barChart.getAxisLeft().setValueFormatter(new UnitValueFormatter(unit));
        XAxis xl = barChart.getXAxis();
        xl.setPosition(XAxis.XAxisPosition.BOTTOM);
        xl.setDrawAxisLine(true);
    }

    private void showBarChart(BarChart barChart, String legends, List<BarEntry> dataList, List<String> titleList) {
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

    private void showLineChart(LineChart lineChart, String legends, List<Entry> dataList, List<String> titleList) {
        LineDataSet lineDataSet = new LineDataSet(dataList, legends);
        lineDataSet.setValueFormatter(new UnitValueFormatter(""));
        lineDataSet.setColors(CommonClass.COLORFUL_COLORS);
        lineDataSet.setValueTextSize(12);
        List<LineDataSet> dataSet = new ArrayList<>();
        dataSet.add(lineDataSet);
        LineData data = new LineData(titleList, dataSet);
        lineChart.setData(data);
        lineChart.setDescription("");
        lineChart.animateXY(1000, 1000);
        lineChart.invalidate();
    }
}
