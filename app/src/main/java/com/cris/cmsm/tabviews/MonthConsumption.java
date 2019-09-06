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
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.cris.cmsm.MonthZoomActivity;
import com.cris.cmsm.R;
import com.cris.cmsm.database.DataHolder;
import com.cris.cmsm.interfaces.IMonthConsumption;
import com.cris.cmsm.interfaces.IOnFragmentVisible;
import com.cris.cmsm.models.response.GraphG1vo;
import com.cris.cmsm.models.response.LoginIfoVO;
import com.cris.cmsm.models.response.ResMonthlyCons;
import com.cris.cmsm.models.response.ResponseSSERole;
import com.cris.cmsm.navcontrollers.MonthGraphController;
import com.cris.cmsm.prefrences.UserLoginPreferences;
import com.cris.cmsm.util.CommonClass;
import com.cris.cmsm.util.Constants;
import com.cris.cmsm.util.FontFamily;
import com.cris.cmsm.util.UnitValueFormatter;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */

public class MonthConsumption extends Fragment implements IOnFragmentVisible, IMonthConsumption, View.OnClickListener {
    private TextView tv_bar, tv_line, tv_seb, tv_total;
    private ImageView iv_zoom1, iv_zoom2;
    private LineChart lineChart;
    private BarChart barChart;
    private Typeface bold;
    private List<Entry> lineCharList = new ArrayList<>();
    private List<BarEntry> graphList = new ArrayList<>();
    private List<String> titleList = new ArrayList<>();
    private AppCompatSpinner spn_sse;
    private LoginIfoVO loginUser;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ((MonthGraphController) getActivity()).initializeConsumption(MonthConsumption.this);
        View view = inflater.inflate(R.layout.fragment_month_graph, container, false);
        loginUser = new UserLoginPreferences(getActivity()).getLoginUser();
        spn_sse = getActivity().findViewById(R.id.spn_sse);
        bold = new FontFamily(getActivity()).getBold();
        tv_bar = view.findViewById(R.id.tv_bar);
        tv_line = view.findViewById(R.id.tv_line);
        tv_seb = view.findViewById(R.id.tv_seb);
        tv_total = view.findViewById(R.id.tv_total);
        iv_zoom1 = view.findViewById(R.id.iv_zoom1);
        iv_zoom2 = view.findViewById(R.id.iv_zoom2);
        lineChart = view.findViewById(R.id.lineChart);
        barChart = view.findViewById(R.id.barChart);

        tv_bar.setTypeface(bold);
        tv_line.setTypeface(bold);
        tv_seb.setTypeface(bold);
        tv_total.setTypeface(bold);

        tv_bar.setText("Consumption");
        tv_line.setText("Consumption");
        tv_total.setText("Monthly Consumption");
        tv_seb.setText("Monthly Consumption");

        setBarChartProperty(barChart);
        setLineChartProperty(lineChart);
        iv_zoom1.setOnClickListener(this);
        iv_zoom2.setOnClickListener(this);
        fragmentBecameVisible();
        return view;
    }

    @Override
    public void fragmentBecameVisible() {
        try {
            if (DataHolder.getInstance().getResMonthlyCons() != null) {
                showConsumptionResponse(DataHolder.getInstance().getResMonthlyCons());
            } else {
                if (!TextUtils.isEmpty(loginUser.getRoleid())) {
                    ((MonthGraphController) getActivity()).getMonthlyData();
                } else {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            ((MonthGraphController) getActivity()).getMonthlyData();
                        }
                    }, 300);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void showConsumptionResponse(ResMonthlyCons response) {
        try {
            graphList.clear();
            titleList.clear();
            lineCharList.clear();
            if (response != null && response.getIsSuccess()) {
                enableControls();
                List<GraphG1vo> graphG1voList = response.getGraphG1vos();
                if (graphG1voList != null && graphG1voList.size() > 0) {
                    for (int i = 0; i < graphG1voList.size(); i++) {
                        graphList.add(new BarEntry(Float.valueOf(graphG1voList.get(i).getConValue() == null ? "0" : graphG1voList.get(i).getConValue()), i));
                        lineCharList.add(new Entry(Float.valueOf(graphG1voList.get(i).getConValue() == null ? "0" : graphG1voList.get(i).getConValue()), i));
                        titleList.add(graphG1voList.get(i).getMonth() == null ? "" : graphG1voList.get(i).getMonth());
                    }
                } else if (barChart != null && lineChart != null) {
                    barChart.clear();
                    lineChart.clear();
                }

                if (graphList.size() > 0 && titleList.size() > 0) {
                    showBarChart(barChart, "MONTHS", graphList, titleList);
                    showLineChart(lineChart, "MONTHS", lineCharList, titleList);
                    if (TextUtils.isEmpty(((ResponseSSERole) spn_sse.getSelectedItem()).getRoleid())) {
                        tv_bar.setText("Consumption \n(in million)");
                        tv_line.setText("Consumption \n (in million)");
                    } else {
                        tv_bar.setText("Consumption");
                        tv_line.setText("Billing");
                    }
                } else {
                    tv_bar.setText("Consumption");
                    tv_line.setText("Consumption");
                }
            } else {
                barChart.clear();
                lineChart.clear();
                tv_bar.setText("Consumption");
                tv_line.setText("Consumption");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void disableControls() {
        iv_zoom1.setEnabled(false);
        iv_zoom2.setEnabled(false);
    }


    private void enableControls() {
        iv_zoom1.setEnabled(true);
        iv_zoom2.setEnabled(true);
    }

    private void setLineChartProperty(LineChart lineChartProperty) {
        lineChartProperty.getAxisRight().setEnabled(false);
        lineChartProperty.getAxisLeft().setValueFormatter(new UnitValueFormatter("kWh"));
        XAxis xl = lineChartProperty.getXAxis();
        xl.setPosition(XAxis.XAxisPosition.BOTTOM);
        xl.setDrawAxisLine(true);
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

    private void showLineChart(LineChart lineChart, String legends, List<Entry> dataList, List<String> titleList) {
        LineDataSet lineDataSet = new LineDataSet(dataList, legends);
        lineDataSet.setValueFormatter(new UnitValueFormatter(""));
        lineDataSet.setColors(CommonClass.COLORFUL_COLORS);
        List<LineDataSet> dataSet = new ArrayList<>();
        dataSet.add(lineDataSet);
        LineData data = new LineData(titleList, dataSet);
        lineChart.setData(data);
        lineChart.setDescription("");
        lineChart.animateXY(1000, 1000);
        lineChart.invalidate();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_zoom1:
                zoomScreen(Constants.MONTH_BAR_CONS, ((ResponseSSERole) spn_sse.getSelectedItem()).getRoleid());
                break;
            case R.id.iv_zoom2:
                zoomScreen(Constants.MONTH_LINE_CONS, ((ResponseSSERole) spn_sse.getSelectedItem()).getRoleid());
                break;
        }
    }


    private void zoomScreen(int value, String roleId) {
        Intent intent = new Intent(getActivity(), MonthZoomActivity.class);
        intent.putExtra(Constants.ZOOM_INTENT, value);
        intent.putExtra(Constants.ROLE_ID, roleId);
        getActivity().startActivity(intent);
    }
}
