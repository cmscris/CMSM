package com.cris.cmsm.navcontrollers;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;

import com.cris.cmsm.DetailSSConsumption;
import com.cris.cmsm.R;
import com.cris.cmsm.adapter.VcdStatusAdapter;
import com.cris.cmsm.database.DataHolder;
import com.cris.cmsm.interfaces.OnItemClickListener;
import com.cris.cmsm.models.ReportHeaderView;
import com.cris.cmsm.models.request.GraphAPIRequest;
import com.cris.cmsm.models.response.VcdStatusResponse;
import com.cris.cmsm.models.response.VcdStatusResponseVO;
import com.cris.cmsm.models.response.SubStationRes;
import com.cris.cmsm.models.response.SubstationResponseVO;
import com.cris.cmsm.presenter.InsertRequestPresenter;
import com.cris.cmsm.presenterview.ResponseView;
import com.cris.cmsm.util.CommonClass;
import com.cris.cmsm.util.Constants;
import com.cris.cmsm.widget.PinchRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cms on 3/16/18.
 */


public class VcdStatusReportController extends AppCompatActivity implements OnItemClickListener, ResponseView {
    private PinchRecyclerView recyclerView;
    private CommonClass commonClass;
    private InsertRequestPresenter insertRequestPresenter;
    private SubstationResponseVO substationResponseVO;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sub_station_report);
        insertRequestPresenter = new InsertRequestPresenter(VcdStatusReportController.this);
        commonClass = new CommonClass(VcdStatusReportController.this);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        VcdStatusResponse vcdStatusResponse = DataHolder.getInstance().getVcdStatusResponse();
        if (vcdStatusResponse != null && vcdStatusResponse.getVcdStatusResponseVO() != null) {
            GraphAPIRequest graphAPIRequest = DataHolder.getInstance().getGraphAPIRequest();
            ReportHeaderView reportHeaderView = new ReportHeaderView();

            String header_str="";
            String hq_str = "";


            header_str +="VCD Status Report\n";
            if(!graphAPIRequest.getRailwayCode().isEmpty())
            {
                header_str += "Railway: " +  graphAPIRequest.getRailwayCode();
                hq_str = "DIV";
            }
            else
            {
                header_str += "IR" ;
                hq_str = "ZONE";
            }


           /* if(!graphAPIRequest.getDivisionCode().isEmpty())
            {
                header_str += "Division: " +  graphAPIRequest.getDivisionCode();
                hq_str = "LOBBY";
            }

            if(!graphAPIRequest.getLobbyCode().isEmpty())
            {
                header_str += "Lobby: " +  graphAPIRequest.getLobbyCode();
                hq_str = "LOBBY";
            }*/







            reportHeaderView.setEnergyConsume(header_str );
            VcdStatusResponseVO cvo = new VcdStatusResponseVO();
            cvo.setLocation("ZONE");
            cvo.setBaseShed("BASE SHED");
            cvo.setTotal("TOTAL");
            cvo.setWorking("WORKING");
            cvo.setNotWorking("NOT WORKING");
            cvo.setNotApplicable("NOT APPLICABLE");
            cvo.setStatusNotAvailable("STATUS NOT AVAILABLE");

            List<VcdStatusResponseVO> list = new ArrayList<>(vcdStatusResponse.getVcdStatusResponseVO());
            list.add(0, cvo);
            recyclerView.setAdapter(new VcdStatusAdapter(VcdStatusReportController.this, reportHeaderView, VcdStatusReportController.this, list));
        }
    }

    @Override
    public void OnItemClick(Object model) {
        if (model != null) {
            substationResponseVO = (SubstationResponseVO) model;
            insertRequestPresenter.Request(substationResponseVO.getAssest(), "Getting Details...", Constants.SS_DETAILS);
            DataHolder.getInstance().setSubstationResponseVO(substationResponseVO);
        }

    }

    @Override
    public void ResponseOk(Object object, int position) {
        try {
            if (object != null && object instanceof SubStationRes) {
                SubStationRes model = (SubStationRes) object;
                if (!TextUtils.isEmpty(model.getMessage()) && model.getMessage().equalsIgnoreCase("Success")) {
                    if (model.getSubstationResponseVO() != null && model.getSubstationResponseVO().size() > 0) {
                        DataHolder.getInstance().setSubStationRes(model);
                        CommonClass.goToNextScreen(VcdStatusReportController.this, DetailSSConsumption.class, true, substationResponseVO.getAssetname(), null);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            commonClass.showToast("Unable to get data. Please try again.");
        }
    }

    @Override
    public void Error() {
        commonClass.showToast("Unable to get data. Please try again.");
    }

    @Override
    public void dismissProgress() {
        commonClass.dismissDialog();
    }

    @Override
    public void showProgress(String msg) {
        commonClass.showProgressBar(msg);
    }
}
