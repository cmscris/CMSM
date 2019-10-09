package com.cris.cmsm.navcontrollers;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.util.Log;
import com.cris.cmsm.R;
import com.cris.cmsm.adapter.AbnormalityAdapter;
import com.cris.cmsm.adapter.CrewUtilizationAdapter;
import com.cris.cmsm.adapter.KeyValueAdapter;
import com.cris.cmsm.adapter.LocoCompetencyAdapter;
import com.cris.cmsm.adapter.MileageDetailsAdapter;
import com.cris.cmsm.adapter.OvertimeDetailsAdapter;
import com.cris.cmsm.adapter.RoadLearningAdapter;
import com.cris.cmsm.adapter.TrainingDetailsAdapter;
import com.cris.cmsm.database.DataHolder;
import com.cris.cmsm.interactor.WebServices;
import com.cris.cmsm.interfaces.OnItemClickListener;
import com.cris.cmsm.models.KeyValue;
import com.cris.cmsm.models.ReportHeaderView;
import com.cris.cmsm.models.request.GraphAPIRequest;
import com.cris.cmsm.models.request.KeyValueRequest;
import com.cris.cmsm.models.response.AbnormalityResponse;
import com.cris.cmsm.models.response.AbnormalityResponseVO;
import com.cris.cmsm.models.response.CrewDetailsResponse;
import com.cris.cmsm.models.response.CrewMileageDetailsVO;
import com.cris.cmsm.models.response.CrewOvertimeDetailsVO;
import com.cris.cmsm.models.response.CrewUtilResponse;
import com.cris.cmsm.models.response.CrewUtilResponseVO;
import com.cris.cmsm.models.response.IrregularCrew;
import com.cris.cmsm.models.response.KeyValueResponse;
import com.cris.cmsm.models.response.LocoCompetencyVO;
import com.cris.cmsm.models.response.LoginIfoVO;
import com.cris.cmsm.models.response.RoadLearningDetailsVO;
import com.cris.cmsm.models.response.SubStationRes;
import com.cris.cmsm.models.response.SubstationResponseVO;
import com.cris.cmsm.models.response.TrainingDetailsVO;
import com.cris.cmsm.prefrences.UserLoginPreferences;
import com.cris.cmsm.presenter.InsertRequestPresenter;
import com.cris.cmsm.presenter.RequestPresenter;
import com.cris.cmsm.presenterview.ResponseView;
import com.cris.cmsm.util.CommonClass;
import com.cris.cmsm.util.Constants;
import com.cris.cmsm.widget.PinchRecyclerView;
import com.google.gson.Gson;

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
    private LoginIfoVO loginInfoModel;
    private UserLoginPreferences userLoginPreferences;
    private RequestPresenter requestPresenter;
    private CrewMileageDetailsVO crewMileageDetailsVO;
    private CrewOvertimeDetailsVO crewOvertimeDetailsVO;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sub_station_report);
        requestPresenter = new RequestPresenter(com.cris.cmsm.navcontrollers.IrregularCrewReportController.this);
        commonClass = new CommonClass(com.cris.cmsm.navcontrollers.IrregularCrewReportController.this);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        userLoginPreferences = new UserLoginPreferences(com.cris.cmsm.navcontrollers.IrregularCrewReportController.this);
        loginInfoModel = userLoginPreferences.getLoginUser();

        callWebService();
    }

    @Override
    public void OnItemClick(Object model) {
        if (model != null) {



        }

    }



    private void callWebService() {
        try {


           // DataHolder.setLevel(0);
            KeyValue request = DataHolder.getInstance().getKeyValue();

            requestPresenter.Request(request, "Getting Data", Constants.IRREGULAR_CREW);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }







    @Override
    public void ResponseOk(Object object, int position) {
        try {

            KeyValueResponse keyValueResponse = (KeyValueResponse) object;
            System.out.println("Response is >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> " + new Gson().toJson(keyValueResponse));
            loginInfoModel = userLoginPreferences.getLoginUser();


                    try {

                        if (keyValueResponse != null && keyValueResponse.getKeyValueList() != null) {

                            ReportHeaderView reportHeaderView = new ReportHeaderView();
                            String header_str= "Irregular Crew";


                            header_str += "\n" + DataHolder.getInstance().getKeyValue().getKey() + " - " + DataHolder.getInstance().getKeyValue().getValue();
                            reportHeaderView.setEnergyConsume(header_str );
                            List<KeyValue> list = new ArrayList<>(keyValueResponse.getKeyValueList());

                            recyclerView.setAdapter(new KeyValueAdapter(com.cris.cmsm.navcontrollers.IrregularCrewReportController.this, reportHeaderView, com.cris.cmsm.navcontrollers.IrregularCrewReportController.this, list));
                        }



                    } catch (Exception e) {
                        e.printStackTrace();
                        commonClass.showToast("Unable to get data. Please try again.");
                    }

        } catch (Exception e) {
            e.printStackTrace();
            commonClass.showToast("Unable to get data. Please try again.");
        }
    }


    @Override
    public void onBackPressed() {

        WebServices.getInstance().cancelAllRequest();
        if(DataHolder.getLevel() == 1)
        {
            callWebService();           // CALL WEBSERVICE FOR CREW BIODATA
        }
        else
        {
            super.onBackPressed();
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
