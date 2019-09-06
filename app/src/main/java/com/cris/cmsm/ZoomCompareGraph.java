package com.cris.cmsm;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;

import com.cris.cmsm.models.response.CrewPositionSummaryResponse;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.Highlight;
import com.cris.cmsm.database.DataHolder;
import com.cris.cmsm.models.response.ConSummaryResponse;
import com.cris.cmsm.util.CommonClass;
import com.cris.cmsm.util.Constants;
import com.cris.cmsm.util.FontFamily;
import com.cris.cmsm.util.UnitValueFormatter;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class ZoomCompareGraph extends Activity {

    private PieChart pieChart;
    private TextView tv_graph_title;
    private Typeface font;
    private List<Entry> dataList = new ArrayList<>();
    private List<String> titleList = new ArrayList<>();
    private CommonClass commonClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compare_graph);
        commonClass = new CommonClass(ZoomCompareGraph.this);
        font = new FontFamily(ZoomCompareGraph.this).getBold();
        tv_graph_title = findViewById(R.id.tv_graph_title);
        tv_graph_title.setTypeface(font);
        pieChart = findViewById(R.id.pieChart);
        tv_graph_title.setText(DataHolder.getInstance().getZoomThreeYearTitle());
        Intent intent = getIntent();
        if (intent.getIntExtra(Constants.ZOOM_INTENT, -1) == Constants.THREE_YEAR_CONS) {
            showPieChart(0, DataHolder.getInstance().getCrewPositionSummaryResponses());
        } else if (intent.getIntExtra(Constants.ZOOM_INTENT, -1) == Constants.THREE_YEAR_BILL) {
            showPieChart(1, DataHolder.getInstance().getCrewPositionSummaryResponses());
        } else if (intent.getIntExtra(Constants.ZOOM_INTENT, -1) == Constants.THREE_YEAR_CONNECTED) {
            showPieChart(2, DataHolder.getInstance().getCrewPositionSummaryResponses());
        } else if (intent.getIntExtra(Constants.ZOOM_INTENT, -1) == Constants.THREE_YEAR_AVG) {
            showPieChart(3, DataHolder.getInstance().getCrewPositionSummaryResponses());
        }
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

    private void showPieChart(int pos, List<CrewPositionSummaryResponse> list) {
        dataList.clear();
        titleList.clear();

        int count = 0;
        if (pos == 0) {
            for (int i = 0; i < list.size(); i++) {
                if(list.get(i).getTitle().equals("NONRUN_TOTAL") || list.get(i).getTitle().equals("RUNNING"))
                {

                    dataList.add(new Entry(Float.valueOf(list.get(i).getTotal()), count));
                    titleList.add(list.get(i).getTitle());
                    count++;
                }

            }
        } else if (pos == 1) {
            int runcounter=0;
            for (int i = 0; i < list.size(); i++) {
                if(list.get(i).getTitle().equals("NONRUN_TOTAL"))
                {
                    runcounter = i+1;
                    break;
                }
                dataList.add(new Entry(Float.valueOf(list.get(i).getTotal()), i));
                titleList.add(list.get(i).getTitle());
            }
        } else if (pos == 2) {

            int runcounter=0;
            for (int i = 0; i < list.size(); i++) {
                if(list.get(i).getTitle().equals("NONRUN_TOTAL"))
                {
                    runcounter = i+1;
                    break;
                }
            }

            count=0;
            for (int z = runcounter; z < list.size(); z++) {
                if(list.get(z).getTitle().equals("RUNNING"))
                {
                    break;
                }
                dataList.add(new Entry(Float.valueOf(list.get(z).getTotal()), count));
                titleList.add(list.get(z).getTitle());
                count++;
            }

        } else if (pos == 3) {
            for (int i = 0; i < list.size(); i++) {
                //dataList.add(new Entry(Float.valueOf(list.get(i).getTotalAvgCost()), i));
                //titleList.add(list.get(i).getYear());
            }

        }
        showPieChart(pieChart, dataList, titleList);
    }


    private void showPieChart(PieChart pieChart, List<Entry> dataList, List<String> titleList) {
        PieDataSet pieDataSet = new PieDataSet(dataList, "");
        pieDataSet.setValueFormatter(new UnitValueFormatter(""));
        PieData newData = new PieData(titleList, pieDataSet);
        pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS); //
        pieDataSet.setValueTextSize(12);
        pieChart.setDescription("");
        pieChart.setData(newData);
        pieChart.animateY(2000);
    }


}
