package com.cris.cmsm.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cris.cmsm.R;
import com.cris.cmsm.adapter.InputFeederAdapter;
import com.cris.cmsm.database.DataHolder;
import com.cris.cmsm.models.response.FeederResponseVO;
import com.cris.cmsm.models.response.InComingFeederVO;
import com.cris.cmsm.widget.PinchRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */

public class InputFeederFragment extends Fragment {

    private PinchRecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.sub_station_report, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        FeederResponseVO feederResponseVO = DataHolder.getInstance().getTabularReportVO().getFeederResponseVO();
        if (feederResponseVO != null && feederResponseVO.getInComingFeederVOs() != null) {
            List<InComingFeederVO> list = new ArrayList<>();
            for (InComingFeederVO model :
                    feederResponseVO.getInComingFeederVOs()) {
                list.add(model);
            }
            InComingFeederVO title = new InComingFeederVO();
            title.setFeederName("Feeder Name/No.");
            title.setVoltageDesc("Voltage");
            if (DataHolder.getInstance().getRequestSSAssets().getAssetsName().equalsIgnoreCase("LT PANEL"))
                title.setBreakerDesc("Type of Switch");
            else
                title.setBreakerDesc("Type of Breaker");
            title.setMakeDesc("Make");
            title.setRating("Rating (in Amp.)");
            title.setBreakingCapacity("Breaking Capacity(in MVA)");
            title.setCommissionDate("Year of acquisition (Date of Commissioning)");
            title.setAcquisitionCost("Cost of acquisition (in Rs.)");
            title.setCtratio("CT ratio");
            title.setRelayCount(-100);
            title.setRemarks("Meter Availability");
            list.add(0, title);
            recyclerView.setAdapter(new InputFeederAdapter(getActivity(), DataHolder.getInstance().getRequestSSAssets(), list));
        }
        return view;
    }
}
