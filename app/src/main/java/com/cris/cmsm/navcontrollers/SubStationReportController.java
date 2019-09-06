package com.cris.cmsm.navcontrollers;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;

import com.cris.cmsm.DetailSSConsumption;
import com.cris.cmsm.R;
import com.cris.cmsm.adapter.SSConsumptionAdapter;
import com.cris.cmsm.database.DataHolder;
import com.cris.cmsm.interfaces.OnItemClickListener;
import com.cris.cmsm.models.ReportHeaderView;
import com.cris.cmsm.models.request.SSConsumptionRequest;
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
 *
 */

public class SubStationReportController extends AppCompatActivity implements OnItemClickListener, ResponseView {
    private PinchRecyclerView recyclerView;
    private CommonClass commonClass;
    private InsertRequestPresenter insertRequestPresenter;
    private SubstationResponseVO substationResponseVO;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sub_station_report);
        insertRequestPresenter = new InsertRequestPresenter(SubStationReportController.this);
        commonClass = new CommonClass(SubStationReportController.this);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ResSSConsumption resSSConsumption = DataHolder.getInstance().getResSSConsumption();
        if (resSSConsumption != null && resSSConsumption.getSubstationResponseVO() != null) {
            SSConsumptionRequest ssConsumptionRequest = DataHolder.getInstance().getSsConsumptionRequest();
            ReportHeaderView reportHeaderView = new ReportHeaderView();
            reportHeaderView.setEnergyConsume("Railway: " + (ssConsumptionRequest.getRlycode().isEmpty() ? "ALL" : ssConsumptionRequest.getRlycode()) + ", Division: " + (ssConsumptionRequest.getDivision().isEmpty() ? "ALL" : ssConsumptionRequest.getDivision()) + ", SSE Incharge: " + ssConsumptionRequest.getSseName()
                    + ", Substation Type: " + ssConsumptionRequest.getDepartmentType() + ", Total Sub-stations =" + resSSConsumption.getSubstationResponseVO().size());
            SubstationResponseVO substationResponseVO = new SubstationResponseVO();
            substationResponseVO.setRlycode("Rly.");
            substationResponseVO.setDivcode("Div.");
            substationResponseVO.setAssetname("Sub-Station");
            substationResponseVO.setIvolt("Incoming Voltage \nKV");
            substationResponseVO.setLoad("Connected Load \nKW");
            substationResponseVO.setCtdemand("Contract Demand \nKVA");
            substationResponseVO.setSname("SEB");
            substationResponseVO.setStatecode("State");
            substationResponseVO.setRoleName("Role");
            substationResponseVO.setDetails("Action");
            List<SubstationResponseVO> list = new ArrayList<>(resSSConsumption.getSubstationResponseVO());
            list.add(0, substationResponseVO);
            recyclerView.setAdapter(new SSConsumptionAdapter(SubStationReportController.this, reportHeaderView, SubStationReportController.this, list));
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
                        CommonClass.goToNextScreen(SubStationReportController.this, DetailSSConsumption.class, true, substationResponseVO.getAssetname(), null);
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
