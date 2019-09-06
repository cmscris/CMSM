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
import com.cris.cmsm.adapter.Annexure10_2Adapter;
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

public class TenPartTwoFragment extends Fragment {

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
                titleModel.setPlowpf(misRequest.getpFinyear());
                titleModel.setLowpf(misRequest.getYear());
                titleModel.setPexcessSMD(misRequest.getpFinyear());
                titleModel.setExcessSMD(misRequest.getYear());
                titleModel.setPexcessConsumption(misRequest.getpFinyear());
                titleModel.setExcessConsumption(misRequest.getYear());
                titleModel.setPexcessFCA(misRequest.getpFinyear());
                titleModel.setExcessFCA(misRequest.getYear());
                titleModel.setPdelayedPayment(misRequest.getpFinyear());
                titleModel.setDelayedPayment(misRequest.getYear());
                titleModel.setPsurcharge(misRequest.getpFinyear());
                titleModel.setSurcharge(misRequest.getYear());
                List<Annexure10vo> list = new ArrayList<>();
                double pPf = 0, pf = 0, pMd = 0, md = 0, pCons = 0, cons = 0, pFca = 0, fca = 0, pDelay = 0, delay = 0, psCharge = 0, sCharge = 0;
                for (Annexure10vo model :
                        reportResponse.getAnnexure10vos()) {
                    pPf = pPf + convertStringToDouble(model.getPlowpf());
                    pf = pf + convertStringToDouble(model.getLowpf());
                    pMd = pMd + convertStringToDouble(model.getPexcessSMD());
                    md = md + convertStringToDouble(model.getExcessSMD());
                    pCons = pCons + convertStringToDouble(model.getPexcessConsumption());
                    cons = cons + convertStringToDouble(model.getExcessConsumption());
                    pFca = pFca + convertStringToDouble(model.getPexcessFCA());
                    fca = fca + convertStringToDouble(model.getExcessFCA());
                    pDelay = pDelay + convertStringToDouble(model.getPdelayedPayment());
                    delay = delay + convertStringToDouble(model.getDelayedPayment());
                    psCharge = psCharge + convertStringToDouble(model.getPsurcharge());
                    sCharge = sCharge + convertStringToDouble(model.getSurcharge());
                    list.add(model);
                }
                list.add(0, titleModel);

                Annexure10vo totalModel = new Annexure10vo();
                totalModel.setRlycode("Z");
                totalModel.setPlowpf("" + CommonClass.getRoundOff(pPf));
                totalModel.setLowpf("" + CommonClass.getRoundOff(pf));
                totalModel.setPexcessSMD("" + CommonClass.getRoundOff(pMd));
                totalModel.setExcessSMD("" + CommonClass.getRoundOff(md));
                totalModel.setPexcessConsumption("" + CommonClass.getRoundOff(pCons));
                totalModel.setExcessConsumption("" + CommonClass.getRoundOff(cons));
                totalModel.setPexcessFCA("" + CommonClass.getRoundOff(pFca));
                totalModel.setExcessFCA("" + CommonClass.getRoundOff(fca));
                totalModel.setPdelayedPayment("" + CommonClass.getRoundOff(pDelay));
                totalModel.setDelayedPayment("" + CommonClass.getRoundOff(delay));
                totalModel.setPsurcharge("" + CommonClass.getRoundOff(psCharge));
                totalModel.setSurcharge("" + CommonClass.getRoundOff(sCharge));
                list.add(totalModel);
                recyclerView.setAdapter(new Annexure10_2Adapter(getActivity(), headerView, list));
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
