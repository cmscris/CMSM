package com.cris.cmsm.tabviews;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatSpinner;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cris.cmsm.R;
import com.cris.cmsm.database.DataHolder;
import com.cris.cmsm.interfaces.IConsumption;
import com.cris.cmsm.interfaces.IOnFragmentVisible;
import com.cris.cmsm.models.Designation;
import com.cris.cmsm.models.Division;
import com.cris.cmsm.models.Lobby;
import com.cris.cmsm.models.Railway;
import com.cris.cmsm.models.response.ConsumptionResponse;
import com.cris.cmsm.models.response.DashboardTotalVO;
import com.cris.cmsm.navcontrollers.CrewAvailabilityController;
import com.cris.cmsm.util.CommonClass;
import com.cris.cmsm.util.Constants;
import com.cris.cmsm.util.FontFamily;
import com.cris.cmsm.util.UnitValueFormatter;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.Highlight;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rangasanju on 05-03-2018.
 */


public class CrewAvailabilityFragment extends Fragment implements IConsumption, IOnFragmentVisible, OnChartValueSelectedListener {
    private HorizontalBarChart horizontal_barChart;
    private List<BarEntry> barEntryList = new ArrayList<>();
    private List<String> titleList = new ArrayList<>();
    private AppCompatSpinner spn_ryCode, spn_divCode, spn_lobbyCode, spn_department, spn_fYear, spn_month, spn_desigCode;
    private TextView tv_title, tv_sub_title;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ((CrewAvailabilityController) getActivity()).initializeConsumption(CrewAvailabilityFragment.this);
        View view = inflater.inflate(R.layout.fragment_railway_wise_data, container, false);


        spn_ryCode = getActivity().findViewById(R.id.spn_ryCode);
        spn_divCode = getActivity().findViewById(R.id.spn_divCode);
        spn_lobbyCode = getActivity().findViewById(R.id.spn_lobbyCode);

        spn_department = getActivity().findViewById(R.id.spn_department);
        spn_fYear = getActivity().findViewById(R.id.spn_fYear);
        spn_month = getActivity().findViewById(R.id.spn_month);
        spn_desigCode = getActivity().findViewById(R.id.spn_desigCode);
        tv_title = view.findViewById(R.id.tv_title);
        tv_sub_title = view.findViewById(R.id.tv_sub_title);




        tv_title.setTypeface(new FontFamily(getActivity()).getBold());
        tv_sub_title.setTypeface(new FontFamily(getActivity()).getBold());

        horizontal_barChart = view.findViewById(R.id.horizontal_barChart);
        horizontal_barChart.getAxisLeft().setEnabled(false);
        horizontal_barChart.getAxisRight().setEnabled(false);
        XAxis xl = horizontal_barChart.getXAxis();
        xl.setPosition(XAxis.XAxisPosition.BOTTOM);
        xl.setDrawAxisLine(true);
        horizontal_barChart.setOnChartValueSelectedListener(this);
        //fragmentBecameVisible();
        return view;
    }

    private void showHorizontalBarChart(String legends, List<BarEntry> dataList, List<String> titleList) {
        BarDataSet barDataSet = new BarDataSet(dataList, legends);
        barDataSet.setColor(Color.rgb(156, 39, 176));
        barDataSet.setValueTextSize(12);
        //barDataSet.setValueFormatter(new UnitValueFormatter("KW"));
        barDataSet.setValueFormatter(new UnitValueFormatter(""));
        List<BarDataSet> dataSet = new ArrayList<>();
        dataSet.add(barDataSet);
        BarData data = new BarData(titleList, dataSet);
        horizontal_barChart.setData(data);
        horizontal_barChart.setDescription("");
        horizontal_barChart.animateXY(1000, 1000);
        horizontal_barChart.invalidate();
    }


    @Override
    public void fragmentBecameVisible() {
        try {
            if (DataHolder.getInstance().getRailWayConsumption() == null) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ((CrewAvailabilityController) getActivity()).callWebService(Constants.CREW_AVAILABILITY);
                    }
                }, 300);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showConsumptionResponse(ConsumptionResponse response) {
        try {


            System.out.println("DataHolder.getLevel() " + DataHolder.getLevel());

            if(DataHolder.getLevel() == 0)
            {
                tv_title.setText("Crew Availability (IR)");
            }else if(DataHolder.getLevel() == 1)
            {
                tv_title.setText("Crew Availability (" + ((Railway) spn_ryCode.getSelectedItem()).getRlycode() + " ZONE)");
            }else if(DataHolder.getLevel() == 2)
            {
                tv_title.setText("Crew Availability (" + ((Division) spn_divCode.getSelectedItem()).getDivcode() + " DIVISION)");
            }
            else
                tv_title.setText("Crew Availability (" + ((Lobby) spn_lobbyCode.getSelectedItem()).getLobbycode() + " LOBBY)");



            DataHolder.getInstance().setRailWayConsumption(response);
            if (response.getDashboardTotalVOs() != null && response.getDashboardTotalVOs().size() > 0) {
                barEntryList.clear();
                titleList.clear();
                List<DashboardTotalVO> totalList = response.getDashboardTotalVOs();
                float Total = 0;
                for (int i = 0; i < totalList.size(); i++) {
                    float value = Float.valueOf(totalList.get(i).getConsumption() == null ? "0" : totalList.get(i).getConsumption());
                    Total = Total + value;
                    barEntryList.add(new BarEntry(value, i));
                    titleList.add(totalList.get(i).getSname() == null ? "" : totalList.get(i).getSname().toUpperCase());
                }
                showHorizontalBarChart("Railway", barEntryList, titleList);
                if (Total > 0)
                    tv_sub_title.setText(((Designation) spn_desigCode.getSelectedItem()).getDesignationName()  + " Availability " + ((long) Total) + "");
                else
                    tv_sub_title.setText("");
            } else {
                if (horizontal_barChart != null)
                    horizontal_barChart.clear();
            }
        } catch (Exception e) {
            e.printStackTrace();
            ((CrewAvailabilityController) getActivity()).retryDialog(getActivity(), "Unable to fetch record. Do you want to retry?");
        }
    }

    @Override
    public void showSpinnerText(Division division) {
        // Blank Block
    }

    @Override
    public void disableControls() {
        // Blank Block
    }

    @Override
    public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {



    }

    @Override
    public void onNothingSelected() {

    }

}
