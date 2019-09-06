package com.cris.cmsm.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cris.cmsm.R;
import com.cris.cmsm.adapter.Annexure10_1Adapter;
import com.cris.cmsm.database.DataBaseManager;
import com.cris.cmsm.database.DataHolder;
import com.cris.cmsm.models.Division;
import com.cris.cmsm.models.Railway;
import com.cris.cmsm.models.ReportHeaderView;
import com.cris.cmsm.models.request.MISReportRequest;
import com.cris.cmsm.models.response.Annexure10vo;
import com.cris.cmsm.models.response.ResAnnexure10;
import com.cris.cmsm.util.CommonClass;
import com.cris.cmsm.util.Constants;
import com.cris.cmsm.widget.PinchRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */

public class TenPartOneFragment extends Fragment {
    private PinchRecyclerView recyclerView;
    private DataBaseManager manager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_mis_report, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        manager = new DataBaseManager(getActivity());
        manager.createDatabase();
        Object object = DataHolder.getInstance().getMisResponse();
        MISReportRequest misRequest = DataHolder.getInstance().getMisReportRequest();
        ReportHeaderView headerView = new ReportHeaderView();
        List<Railway> railwayList = manager.getRailwayListByCode(misRequest.getRailway());
        List<Division> divisionList = manager.getDivisionListByDivCode(misRequest.getDivision());
        if (railwayList.size() > 0)
            headerView.setRailway(railwayList.get(0).getFname());
        else
            headerView.setRailway(getResources().getString(R.string.indian_railway));
        if (railwayList.size() == 0)
            headerView.setZon_div("");
        else if (railwayList.size() > 0 && divisionList.size() > 0)
            headerView.setZon_div(divisionList.get(0).getFname().toUpperCase() + " DIVISION");
        else
            headerView.setZon_div("ALL DIVISIONS");

        if (object instanceof ResAnnexure10) {
            ResAnnexure10 reportResponse = (ResAnnexure10) object;
            if (reportResponse.getIsSuccess()) {
                StringBuilder stringBuilder = new StringBuilder();
                if (!TextUtils.isEmpty(reportResponse.getAppsummary())) {
                    stringBuilder.append(reportResponse.getAppsummary());
                }
                if (!TextUtils.isEmpty(reportResponse.getNAppsummary())) {
                    stringBuilder.append(reportResponse.getNAppsummary());
                }

                if (TextUtils.isEmpty(stringBuilder.toString())) {
                    headerView.setSummary("");
                } else if (stringBuilder.toString().trim().equalsIgnoreCase(Constants.note)) {
                    headerView.setSummary(stringBuilder.toString());
                } else {
                    headerView.setSummary("Note: Above figure does not include data of :\n\n" + stringBuilder.toString());
                }
                headerView.setMonth("Month : " + (CommonClass.currentMonth(misRequest.getMonth()).isEmpty() ? "All months" : CommonClass.currentMonth(misRequest.getMonth())));
                headerView.setEnergyConsume("Railway wise details of Energy Charges");
                Annexure10vo titleModel = new Annexure10vo();
                titleModel.setRlycode("");
                titleModel.setPconsumption(misRequest.getpFinyear());
                titleModel.setConsumption(misRequest.getYear());
                titleModel.setPbilling(misRequest.getpFinyear());
                titleModel.setBilling(misRequest.getYear());
                titleModel.setPaverage(misRequest.getpFinyear());
                titleModel.setAverage(misRequest.getYear());
                titleModel.setPpercentage(misRequest.getpFinyear());
                titleModel.setPercentage(misRequest.getYear());

                double pCons = 0, cons = 0, pBilling = 0, billing = 0, pAvg = 0, avg = 0, psCharge = 0, sCharge = 0;
                List<Annexure10vo> list = new ArrayList<>();
                for (Annexure10vo model :
                        reportResponse.getAnnexure10vos()) {
                    pCons = pCons + convertStringToDouble(model.getPconsumption());
                    cons = cons + convertStringToDouble(model.getConsumption());
                    pBilling = pBilling + convertStringToDouble(model.getPbilling());
                    billing = billing + convertStringToDouble(model.getBilling());
                    psCharge = psCharge + convertStringToDouble(model.getPsurcharge());
                    sCharge = sCharge + convertStringToDouble(model.getSurcharge());
                    list.add(model);
                }
                list.add(0, titleModel);

                Annexure10vo totalModel = new Annexure10vo();
                totalModel.setRlycode("Z");
                totalModel.setPconsumption("" + CommonClass.getRoundOff(pCons));
                totalModel.setConsumption("" + CommonClass.getRoundOff(cons));
                totalModel.setPbilling("" + CommonClass.getRoundOff(pBilling));
                totalModel.setBilling("" + CommonClass.getRoundOff(billing));
                totalModel.setPaverage("" + CommonClass.getRoundOff(pBilling / pCons));
                totalModel.setAverage("" + CommonClass.getRoundOff(billing / cons));
                totalModel.setPpercentage("" + CommonClass.getRoundOff((psCharge / pBilling) * 100));
                totalModel.setPercentage("" + CommonClass.getRoundOff((sCharge / billing) * 100));
                list.add(totalModel);

                recyclerView.setAdapter(new Annexure10_1Adapter(getActivity(), headerView, list));
            }
        }
        return view;
    }


    private double convertStringToDouble(String value) {
        if (TextUtils.isEmpty(value)) {
            value = "0";
        }
        return Double.valueOf(value);
    }
}
