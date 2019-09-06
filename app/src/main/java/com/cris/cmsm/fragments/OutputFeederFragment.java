package com.cris.cmsm.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cris.cmsm.R;
import com.cris.cmsm.adapter.OutGoingAdpater;
import com.cris.cmsm.database.DataHolder;
import com.cris.cmsm.models.response.FeederResponseVO;
import com.cris.cmsm.models.response.OutGoingFeederVo;
import com.cris.cmsm.widget.PinchRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */

public class OutputFeederFragment extends Fragment {

    private PinchRecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.sub_station_report, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        FeederResponseVO feederResponseVO = DataHolder.getInstance().getTabularReportVO().getFeederResponseVO();
        if (feederResponseVO != null && feederResponseVO.getOutGoingFeederVos() != null) {
            List<OutGoingFeederVo> list = new ArrayList<>();
            for (OutGoingFeederVo model :
                    feederResponseVO.getOutGoingFeederVos()) {
                list.add(model);
            }
            OutGoingFeederVo title = new OutGoingFeederVo();
            title.setFeederName("Feeder Name/No.");
            title.setVoltageDesc("Voltage");
            title.setBreakerDesc("Type of Switch");
            title.setMakeDesc("Make");
            title.setRating("Rating (in Amp.)");
            title.setBreakingCapacity("Breaking Capacity(in MVA)");
            title.setCommissionDate("Year of acquisition (Date of Commissioning)");
            title.setAcquisitionCost("Cost of acquisition (in Rs.)");
            title.setCtratio("CT ratio");
            title.setRelayCount(-100);
            title.setRemarks("Meter Availability");
            list.add(0, title);
            recyclerView.setAdapter(new OutGoingAdpater(getActivity(), DataHolder.getInstance().getRequestSSAssets(), list));
        }
        return view;
    }
}
