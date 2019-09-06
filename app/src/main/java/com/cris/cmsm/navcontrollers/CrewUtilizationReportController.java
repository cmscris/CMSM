package com.cris.cmsm.navcontrollers;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;

import com.cris.cmsm.DetailSSConsumption;
import com.cris.cmsm.R;
import com.cris.cmsm.adapter.CrewUtilizationAdapter;
import com.cris.cmsm.adapter.SSConsumptionAdapter;
import com.cris.cmsm.database.DataHolder;
import com.cris.cmsm.interfaces.OnItemClickListener;
import com.cris.cmsm.models.ReportHeaderView;
import com.cris.cmsm.models.request.GraphAPIRequest;
import com.cris.cmsm.models.request.SSConsumptionRequest;
import com.cris.cmsm.models.response.CrewUtilResponse;
import com.cris.cmsm.models.response.CrewUtilResponseVO;
import com.cris.cmsm.models.response.ResSSConsumption;
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
 * Created by cms on 3/8/18.
 */



public class CrewUtilizationReportController extends AppCompatActivity implements OnItemClickListener, ResponseView {
    private PinchRecyclerView recyclerView;
    private CommonClass commonClass;
    private InsertRequestPresenter insertRequestPresenter;
    private SubstationResponseVO substationResponseVO;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sub_station_report);
        insertRequestPresenter = new InsertRequestPresenter(CrewUtilizationReportController.this);
        commonClass = new CommonClass(CrewUtilizationReportController.this);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        CrewUtilResponse crewUtilResponse = DataHolder.getInstance().getCrewUtilResponse();
        if (crewUtilResponse != null && crewUtilResponse.getCrewUtilResponseVO() != null) {
            GraphAPIRequest graphAPIRequest = DataHolder.getInstance().getGraphAPIRequest();
            ReportHeaderView reportHeaderView = new ReportHeaderView();
            String header_str="";

            if(!graphAPIRequest.getRailwayCode().isEmpty())
            {
                header_str += "Railway: " +  graphAPIRequest.getRailwayCode();
            }


            if(!graphAPIRequest.getDivisionCode().isEmpty())
            {
                header_str += "Division: " +  graphAPIRequest.getDivisionCode();
            }

            if(!graphAPIRequest.getLobbyCode().isEmpty())
            {
                header_str += "Lobby: " +  graphAPIRequest.getLobbyCode();
            }


            header_str += "\n Crew Utilization";



            reportHeaderView.setEnergyConsume(header_str );

            CrewUtilResponseVO cvo = new CrewUtilResponseVO();
            cvo.setLocation("HQ");
            cvo.setCrewcount("Count");
            cvo.setGt240(">240");
            cvo.setBt208_240("208-240");
            cvo.setBt180_208("180-208");
            cvo.setBt160_180("160-180");
            cvo.setBt140_160("140-160");
            cvo.setLt140("<140");

            List<CrewUtilResponseVO> list = new ArrayList<>(crewUtilResponse.getCrewUtilResponseVO());
            list.add(0, cvo);
            recyclerView.setAdapter(new CrewUtilizationAdapter(CrewUtilizationReportController.this, reportHeaderView, CrewUtilizationReportController.this, list));
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
                        CommonClass.goToNextScreen(CrewUtilizationReportController.this, DetailSSConsumption.class, true, substationResponseVO.getAssetname(), null);
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
