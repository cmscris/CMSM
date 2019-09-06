package com.cris.cmsm.navcontrollers;

import android.os.Bundle;
       import android.support.annotation.Nullable;
        import android.support.v7.app.AppCompatActivity;
        import android.support.v7.widget.LinearLayoutManager;
        import android.text.TextUtils;

        import com.cris.cmsm.DetailSSConsumption;
        import com.cris.cmsm.R;
        import com.cris.cmsm.adapter.AbnormalityAdapter;
        import com.cris.cmsm.adapter.CrewUtilizationAdapter;
        import com.cris.cmsm.database.DataHolder;
        import com.cris.cmsm.interfaces.OnItemClickListener;
        import com.cris.cmsm.models.ReportHeaderView;
        import com.cris.cmsm.models.request.GraphAPIRequest;
        import com.cris.cmsm.models.response.AbnormalityResponse;
        import com.cris.cmsm.models.response.AbnormalityResponseVO;
        import com.cris.cmsm.models.response.CrewUtilResponse;
        import com.cris.cmsm.models.response.CrewUtilResponseVO;
import com.cris.cmsm.models.response.IrregularCrew;
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


public class IrregularCrewReportController extends AppCompatActivity implements OnItemClickListener, ResponseView {
    private PinchRecyclerView recyclerView;
    private CommonClass commonClass;
    private InsertRequestPresenter insertRequestPresenter;
    private SubstationResponseVO substationResponseVO;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sub_station_report);
        insertRequestPresenter = new InsertRequestPresenter(IrregularCrewReportController.this);
        commonClass = new CommonClass(IrregularCrewReportController.this);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        AbnormalityResponse abnormalityResponse = DataHolder.getInstance().getAbnormalityResponse();
        if (abnormalityResponse != null && abnormalityResponse.getAbnormalityResponseVO() != null) {
            GraphAPIRequest graphAPIRequest = DataHolder.getInstance().getGraphAPIRequest();
            ReportHeaderView reportHeaderView = new ReportHeaderView();

            String header_str="";
            String hq_str = "";


            header_str +="Irregular Crew Report\n";
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


            if(!graphAPIRequest.getDivisionCode().isEmpty())
            {
                header_str += "Division: " +  graphAPIRequest.getDivisionCode();
                hq_str = "LOBBY";
            }

            if(!graphAPIRequest.getLobbyCode().isEmpty())
            {
                header_str += "Lobby: " +  graphAPIRequest.getLobbyCode();
                hq_str = "LOBBY";
            }







            reportHeaderView.setEnergyConsume(header_str );
            IrregularCrew cvo = new IrregularCrew();
            cvo.setLobby(hq_str);
            cvo.setAbnormality_head("AH");
            cvo.setLt_8("<8");
            cvo.setBw_8_16("8-16");
            cvo.setBw_16_24("16-24");
            cvo.setBw_24_72("24-72");
            cvo.setBw_72_240("72-240");
            cvo.setGt_240(">240");
            cvo.setStill_open("PN");

            List<AbnormalityResponseVO> list = new ArrayList<>(abnormalityResponse.getAbnormalityResponseVO());
            list.add(0, cvo);
            recyclerView.setAdapter(new AbnormalityAdapter(IrregularCrewReportController.this, reportHeaderView, IrregularCrewReportController.this, list));
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
                        CommonClass.goToNextScreen(IrregularCrewReportController.this, DetailSSConsumption.class, true, substationResponseVO.getAssetname(), null);
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
