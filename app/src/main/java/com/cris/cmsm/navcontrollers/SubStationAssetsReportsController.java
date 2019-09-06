package com.cris.cmsm.navcontrollers;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;

import com.cris.cmsm.BaseActivity;
import com.cris.cmsm.R;
import com.cris.cmsm.adapter.AssetsReportAdapter;
import com.cris.cmsm.database.DataHolder;
import com.cris.cmsm.interfaces.OnItemClickListener;
import com.cris.cmsm.models.request.RequestSSAssets;
import com.cris.cmsm.models.response.TabularReportVO;
import com.cris.cmsm.tabviews.HTLTDetailsActivity;
import com.cris.cmsm.util.CommonClass;
import com.cris.cmsm.widget.PinchRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */

public class SubStationAssetsReportsController extends BaseActivity implements OnItemClickListener {

    private PinchRecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sub_station_report);
        try {
            RequestSSAssets requestSSAssets = DataHolder.getInstance().getRequestSSAssets();
            recyclerView = findViewById(R.id.recyclerView);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            TabularReportVO title = new TabularReportVO();
            title.setRailway("RAILWAY");
            title.setDivison("DIVISION");
            title.setSseIncharge("SSE INCHARGE");
            title.setSubstation("SUBSTATION");
            title.setAssestname("ASSET NAME");
            if (requestSSAssets.getAssetsName().equalsIgnoreCase("APFC"))
                title.setRating("RATING(KVAR)");
            else
                title.setRating("RATING(KVA)");
            if (requestSSAssets.getAssetsName().equalsIgnoreCase("EARTHING"))
                title.setMake("Type of Earthing");
            else
                title.setMake("MAKE");
            if (requestSSAssets.getAssetsName().equalsIgnoreCase("SERVO STABILIZER")
                    || requestSSAssets.getAssetsName().equalsIgnoreCase("APFC")
                    || requestSSAssets.getAssetsName().equalsIgnoreCase("EARTHING"))
                title.setDateofmanufacturing("Year of Acquisition");
            else
                title.setDateofmanufacturing("DATE OF MANUFACTURING");
            title.setDetails("Action");
            List<TabularReportVO> list = new ArrayList<>(DataHolder.getInstance().getTabularReport().getTabularReportVO());
            list.add(0, title);
            recyclerView.setAdapter(new AssetsReportAdapter(SubStationAssetsReportsController.this, SubStationAssetsReportsController.this, requestSSAssets, list));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void OnItemClick(Object model) {
        try {
            TabularReportVO item = (TabularReportVO) model;
            DataHolder.getInstance().setTabularReportVO(item);
            CommonClass.goToNextScreen(SubStationAssetsReportsController.this, HTLTDetailsActivity.class, true, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
