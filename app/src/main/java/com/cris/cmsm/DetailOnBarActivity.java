package com.cris.cmsm;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.Highlight;
import com.cris.cmsm.database.DataHolder;
import com.cris.cmsm.models.response.ThreeYearData;
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
public class DetailOnBarActivity extends Activity {

    private BarChart barChart;
    private VerticalTextView tv_units;
    private Typeface font;
    private TextView tv_graph_title;
    private List<String> titleList = new ArrayList<>();
    private List<String> barTitleList = new ArrayList<>();
    private CommonClass commonClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zoom_graph);
        font = new FontFamily(DetailOnBarActivity.this).getBold();
        commonClass = new CommonClass(DetailOnBarActivity.this);
        tv_graph_title = findViewById(R.id.tv_graph_title);
        barChart = findViewById(R.id.barChart);
        barChart.getAxisRight().setEnabled(false);
        tv_units = findViewById(R.id.tv_units);
        tv_units.setTypeface(font);
        tv_graph_title.setTypeface(font);
        try {
            ThreeYearData threeYearData = DataHolder.getInstance().getThreeYearData();
            Intent intent = getIntent();
            if (intent.getIntExtra(Constants.ZOOM_INTENT, -1) == Constants.YEAR_CONS_BAR) {
                if (intent.getBooleanExtra(Constants.UNIT, false)) {
                    tv_graph_title.setText(DataHolder.getInstance().getZoomGraphTitle());
                    tv_units.setText("Consumption \n(in million kWh)");
                } else {
                    tv_graph_title.setText(DataHolder.getInstance().getZoomGraphTitle().replace("(in millions kWh)", ""));
                    tv_units.setText("Consumption \n" +
                            "(in kWh)");
                }
                setBarChartProperty(barChart, "");
                List<BarDataSet> dataSet = new ArrayList<>();
                if (threeYearData.getGeneralDetailVOs() != null && threeYearData.getGeneralDetailVOs().size() > 0) {
                    List<BarEntry> dataList = new ArrayList<>();
                    for (int i = 0; i < threeYearData.getGeneralDetailVOs().size(); i++) {
                        titleList.add(threeYearData.getGeneralDetailVOs().get(i).getXAxixVal());
                        dataList.add(new BarEntry(Float.valueOf(threeYearData.getGeneralDetailVOs().get(i).getConsumption()), i));
                    }

                    BarDataSet barDataSet = new BarDataSet(dataList, "General Service");
                    barDataSet.setValueFormatter(new UnitValueFormatter(""));
                    barDataSet.setColor(Color.rgb(0, 155, 0));
                    barDataSet.setValueTextSize(12);
                    dataSet.add(barDataSet);
                }
                if (threeYearData.getTractionDetailVOs() != null && threeYearData.getTractionDetailVOs().size() > 0) {
                    List<BarEntry> dataList = new ArrayList<>();
                    for (int i = 0; i < threeYearData.getTractionDetailVOs().size(); i++) {
                        barTitleList.add(threeYearData.getTractionDetailVOs().get(i).getXAxixVal());
                        dataList.add(new BarEntry(Float.valueOf(threeYearData.getTractionDetailVOs().get(i).getConsumption()), i));
                    }

                    BarDataSet barDataSet = new BarDataSet(dataList, "Traction");
                    barDataSet.setValueFormatter(new UnitValueFormatter(""));
                    barDataSet.setColor(Color.rgb(155, 0, 0));
                    barDataSet.setValueTextSize(12);
                    dataSet.add(barDataSet);
                }

                BarData data;
                if (titleList.size() > 0)
                    data = new BarData(titleList, dataSet);
                else
                    data = new BarData(barTitleList, dataSet);
                barChart.setData(data);
                barChart.setDescription("");
                barChart.animateXY(1000, 1000);
                barChart.invalidate();
            } else if (intent.getIntExtra(Constants.ZOOM_INTENT, -1) == Constants.YEAR_BILL_BAR) {
                if (intent.getBooleanExtra(Constants.UNIT, false)) {
                    tv_graph_title.setText(DataHolder.getInstance().getZoomGraphTitle());
                    tv_units.setText("  Billing  \n(in million Rs.)");
                } else {
                    tv_graph_title.setText(DataHolder.getInstance().getZoomGraphTitle().replace("(in million Rs.)", ""));
                    tv_units.setText("  Billing  \n(in Rs.)");
                }
                setBarChartProperty(barChart, "");
                List<BarDataSet> dataSet = new ArrayList<>();
                if (threeYearData.getGeneralDetailVOs() != null && threeYearData.getGeneralDetailVOs().size() > 0) {
                    List<BarEntry> dataList = new ArrayList<>();
                    for (int i = 0; i < threeYearData.getGeneralDetailVOs().size(); i++) {
                        titleList.add(threeYearData.getGeneralDetailVOs().get(i).getXAxixVal());
                        dataList.add(new BarEntry(Float.valueOf(threeYearData.getGeneralDetailVOs().get(i).getBill()), i));
                    }

                    BarDataSet barDataSet = new BarDataSet(dataList, "General Service");
                    barDataSet.setValueFormatter(new UnitValueFormatter(""));
                    barDataSet.setColor(Color.rgb(0, 155, 0));
                    barDataSet.setValueTextSize(12);
                    dataSet.add(barDataSet);
                }
                if (threeYearData.getTractionDetailVOs() != null && threeYearData.getTractionDetailVOs().size() > 0) {
                    List<BarEntry> dataList = new ArrayList<>();
                    for (int i = 0; i < threeYearData.getTractionDetailVOs().size(); i++) {
                        barTitleList.add(threeYearData.getTractionDetailVOs().get(i).getXAxixVal());
                        dataList.add(new BarEntry(Float.valueOf(threeYearData.getTractionDetailVOs().get(i).getBill()), i));
                    }

                    BarDataSet barDataSet = new BarDataSet(dataList, "Traction");
                    barDataSet.setValueFormatter(new UnitValueFormatter(""));
                    barDataSet.setColor(Color.rgb(155, 0, 0));
                    barDataSet.setValueTextSize(12);
                    dataSet.add(barDataSet);
                }

                BarData data;
                if (titleList.size() > 0)
                    data = new BarData(titleList, dataSet);
                else
                    data = new BarData(barTitleList, dataSet);
                barChart.setData(data);
                barChart.setDescription("");
                barChart.animateXY(1000, 1000);
                barChart.invalidate();
            } else if (intent.getIntExtra(Constants.ZOOM_INTENT, -1) == Constants.YEAR_CONNECTED_BAR) {
                tv_graph_title.setText(DataHolder.getInstance().getZoomGraphTitle());
                tv_units.setText(" Total Connected Load  \n(MW)");
                setBarChartProperty(barChart, "");
                List<BarDataSet> dataSet = new ArrayList<>();
                if (threeYearData.getGeneralDetailVOs() != null && threeYearData.getGeneralDetailVOs().size() > 0) {
                    List<BarEntry> dataList = new ArrayList<>();
                    for (int i = 0; i < threeYearData.getGeneralDetailVOs().size(); i++) {
                        titleList.add(threeYearData.getGeneralDetailVOs().get(i).getXAxixVal());
                        dataList.add(new BarEntry(Float.valueOf(threeYearData.getGeneralDetailVOs().get(i).getTotalGenConnectedLoad()), i));
                    }

                    BarDataSet barDataSet = new BarDataSet(dataList, "General Service");
                    barDataSet.setValueFormatter(new UnitValueFormatter(""));
                    barDataSet.setColor(Color.rgb(0, 155, 0));
                    barDataSet.setValueTextSize(12);
                    dataSet.add(barDataSet);
                }
                if (threeYearData.getTractionDetailVOs() != null && threeYearData.getTractionDetailVOs().size() > 0) {
                    List<BarEntry> dataList = new ArrayList<>();
                    for (int i = 0; i < threeYearData.getTractionDetailVOs().size(); i++) {
                        barTitleList.add(threeYearData.getTractionDetailVOs().get(i).getXAxixVal());
                        dataList.add(new BarEntry(Float.valueOf(threeYearData.getTractionDetailVOs().get(i).getTotalTrdConnectedLoad()), i));
                    }

                    BarDataSet barDataSet = new BarDataSet(dataList, "Traction");
                    barDataSet.setValueFormatter(new UnitValueFormatter(""));
                    barDataSet.setColor(Color.rgb(155, 0, 0));
                    barDataSet.setValueTextSize(12);
                    dataSet.add(barDataSet);
                }

                BarData data;
                if (titleList.size() > 0)
                    data = new BarData(titleList, dataSet);
                else
                    data = new BarData(barTitleList, dataSet);
                barChart.setData(data);
                barChart.setDescription("");
                barChart.animateXY(1000, 1000);
                barChart.invalidate();
            } else if (intent.getIntExtra(Constants.ZOOM_INTENT, -1) == Constants.YEAR_AVG_BAR) {
                tv_graph_title.setText(DataHolder.getInstance().getZoomGraphTitle());
                tv_units.setText("Average Cost  \n (in Rs/kWh)");
                setBarChartProperty(barChart, "");
                List<BarDataSet> dataSet = new ArrayList<>();
                if (threeYearData.getGeneralDetailVOs() != null && threeYearData.getGeneralDetailVOs().size() > 0) {
                    List<BarEntry> dataList = new ArrayList<>();
                    for (int i = 0; i < threeYearData.getGeneralDetailVOs().size(); i++) {
                        titleList.add(threeYearData.getGeneralDetailVOs().get(i).getXAxixVal());
                        dataList.add(new BarEntry(Float.valueOf(threeYearData.getGeneralDetailVOs().get(i).getTotalGenAvgCost()), i));
                    }

                    BarDataSet barDataSet = new BarDataSet(dataList, "General Service");
                    barDataSet.setValueFormatter(new UnitValueFormatter(""));
                    barDataSet.setColor(Color.rgb(0, 155, 0));
                    barDataSet.setValueTextSize(12);
                    dataSet.add(barDataSet);
                }
                if (threeYearData.getTractionDetailVOs() != null && threeYearData.getTractionDetailVOs().size() > 0) {
                    List<BarEntry> dataList = new ArrayList<>();
                    for (int i = 0; i < threeYearData.getTractionDetailVOs().size(); i++) {
                        barTitleList.add(threeYearData.getTractionDetailVOs().get(i).getXAxixVal());
                        dataList.add(new BarEntry(Float.valueOf(threeYearData.getTractionDetailVOs().get(i).getTotalTrdAvgCost()), i));
                    }

                    BarDataSet barDataSet = new BarDataSet(dataList, "Traction");
                    barDataSet.setValueFormatter(new UnitValueFormatter(""));
                    barDataSet.setColor(Color.rgb(155, 0, 0));
                    barDataSet.setValueTextSize(12);
                    dataSet.add(barDataSet);
                }

                BarData data;
                if (titleList.size() > 0)
                    data = new BarData(titleList, dataSet);
                else
                    data = new BarData(barTitleList, dataSet);
                barChart.setData(data);
                barChart.setDescription("");
                barChart.animateXY(1000, 1000);
                barChart.invalidate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        barChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {
                if (titleList.size() > 0)
                    commonClass.showToast(titleList.get(e.getXIndex()) + " - " + e.getVal());
                else if (barTitleList.size() > 0)
                    commonClass.showToast(barTitleList.get(e.getXIndex()) + " - " + e.getVal());
            }

            @Override
            public void onNothingSelected() {

            }
        });
    }

    private void setBarChartProperty(BarChart barChart, String unit) {
        barChart.getAxisRight().setEnabled(false);
        barChart.getAxisLeft().setValueFormatter(new UnitValueFormatter(unit));
        XAxis xl = barChart.getXAxis();
        xl.setPosition(XAxis.XAxisPosition.BOTTOM);
        xl.setDrawAxisLine(true);
    }
}
