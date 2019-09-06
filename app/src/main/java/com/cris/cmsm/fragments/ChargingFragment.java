package com.cris.cmsm.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cris.cmsm.R;
import com.cris.cmsm.adapter.ChargingAdapter;
import com.cris.cmsm.database.DataHolder;
import com.cris.cmsm.models.response.HtPannelChargerVO;
import com.cris.cmsm.widget.PinchRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */

public class ChargingFragment extends Fragment {
    private PinchRecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.sub_station_report, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        List<HtPannelChargerVO> htPannelChargerVOList = DataHolder.getInstance().getTabularReportVO().getHtPannelChargerVOs();
        if (htPannelChargerVOList != null) {
            List<HtPannelChargerVO> list = new ArrayList<>();
            for (HtPannelChargerVO model :
                    htPannelChargerVOList) {
                list.add(model);
            }
            HtPannelChargerVO title = new HtPannelChargerVO();
            title.setCommissionDate("Year of Acquisition \n" +
                    "(Date of Commissioning)");
            title.setAcquisitionCost("Cost of Acquisition\n" +
                    "(in Rs.)");
            title.setMakeDesc("Charger Make");
            title.setChargerCapacity("Capacity of Charger\n" +
                    "(in KVA)");
            title.setVoltageDesc("Voltage of Battery");
            title.setBatteryLugDate("Battery Lug Date");
            title.setBatteryCapacity("Capacity of Battery");
            list.add(0, title);
            recyclerView.setAdapter(new ChargingAdapter(getActivity(), list));
        }
        return view;
    }
}

