package com.cris.cmsm.navcontrollers;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.cris.cmsm.DetailSSConsumption;
import com.cris.cmsm.HomeActivity;
import com.cris.cmsm.R;
import com.cris.cmsm.adapter.CrewUtilizationAdapter;
import com.cris.cmsm.adapter.KeyValueAdapter;
import com.cris.cmsm.adapter.LICrewMonitoredAdapter;
import com.cris.cmsm.database.DataHolder;
import com.cris.cmsm.interactor.WebServices;
import com.cris.cmsm.interfaces.OnItemClickListener;
import com.cris.cmsm.models.Division;
import com.cris.cmsm.models.KeyValue;
import com.cris.cmsm.models.Lobby;
import com.cris.cmsm.models.Month;
import com.cris.cmsm.models.Railway;
import com.cris.cmsm.models.ReportHeaderView;
import com.cris.cmsm.models.request.ConSummaryRequest;
import com.cris.cmsm.models.request.GraphAPIRequest;
import com.cris.cmsm.models.request.KeyValueRequest;
import com.cris.cmsm.models.request.LoginRequest;
import com.cris.cmsm.models.response.CrewUtilResponse;
import com.cris.cmsm.models.response.CrewUtilResponseVO;
import com.cris.cmsm.models.response.LICrewMonitoredResponse;
import com.cris.cmsm.models.response.LICrewMonitoredResponseVO;
import com.cris.cmsm.models.response.LoginIfoVO;
import com.cris.cmsm.models.response.SubStationRes;
import com.cris.cmsm.models.response.SubstationResponseVO;
import com.cris.cmsm.prefrences.UserLoginPreferences;
import com.cris.cmsm.presenter.InsertRequestPresenter;
import com.cris.cmsm.presenter.RequestPresenter;
import com.cris.cmsm.presenterview.ResponseView;
import com.cris.cmsm.util.CommonClass;
import com.cris.cmsm.util.Constants;
import com.cris.cmsm.widget.PinchRecyclerView;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;
/**
 * Created by cms on 4/11/18.
 */




public class LICrewMonitoredReportController extends AppCompatActivity implements OnItemClickListener, ResponseView {
    private PinchRecyclerView recyclerView;
    private CommonClass commonClass;
    private InsertRequestPresenter insertRequestPresenter;
    private LICrewMonitoredResponseVO liCrewMonitoredResponseVO;
    private LoginIfoVO loginInfoModel;
    private UserLoginPreferences userLoginPreferences;
    private RequestPresenter requestPresenter;
    TextView tv_crewid;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sub_station_report);
        requestPresenter = new RequestPresenter(LICrewMonitoredReportController.this);
        commonClass = new CommonClass(LICrewMonitoredReportController.this);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        userLoginPreferences = new UserLoginPreferences(LICrewMonitoredReportController.this);
        loginInfoModel = userLoginPreferences.getLoginUser();

        tv_crewid = recyclerView.findViewById(R.id.tv_crewid);

        // CALL WEBSERVICE FOR CREW MONITORED
        callWebService();

    }

    @Override
    public void OnItemClick(Object model) {
        if (model != null) {

            liCrewMonitoredResponseVO = (LICrewMonitoredResponseVO) model;
            DataHolder.setCrewid(liCrewMonitoredResponseVO.getCrewid());
            callWebServiceBiodata();

        }

    }


    // CALL WEBSERVICE FOR CREW MONITORED
    private void callWebService() {
        try {

            DataHolder.setLevel(0);
            LoginRequest request = new LoginRequest();
            request.setUser(loginInfoModel.getLoginid());
            requestPresenter.Request(request, "Getting Crew Monitored", Constants.LI_CREW_MONITORED);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }




    // CALL WEBSERVICE FOR CREW BIODATA
    private void callWebServiceBiodata() {
        try {

            DataHolder.setLevel(1);

           /*
            KeyValueRequest request = new KeyValueRequest();

            request.setUser(liCrewMonitoredResponseVO.getCrewid());
            requestPresenter.Request(request, "Getting Data", Constants.KEY_VALUE);
*/
            CommonClass.goToNextScreen(LICrewMonitoredReportController.this, DetailController.class, true, Constants.LI_CREW_DETAILS);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void ResponseOk(Object object, int position) {
        try {


            switch(position)
            {

                case Constants.LI_CREW_MONITORED :


                    LICrewMonitoredResponse liCrewMonitoredResponse = (LICrewMonitoredResponse) object;

                    //System.out.println("Response is " + new Gson().toJson(liCrewMonitoredResponse));

                    if (liCrewMonitoredResponse != null && liCrewMonitoredResponse.getLiCrewMonitoredResponseVO() != null) {
                        GraphAPIRequest graphAPIRequest = DataHolder.getInstance().getGraphAPIRequest();
                        ReportHeaderView reportHeaderView = new ReportHeaderView();
                        String header_str= "LI : " + loginInfoModel.getLoginid() ;

                        header_str += "\n Crew Monitored";
                        reportHeaderView.setEnergyConsume(header_str );

                        LICrewMonitoredResponseVO cvo = new LICrewMonitoredResponseVO();

                        cvo.setSno("SN");
                        cvo.setCrewid("ID");
                        cvo.setCrewname("Name");
                        cvo.setDesignation("Desig");
                        cvo.setGrade("Grade");
                        cvo.setGradedate("G. Date");
                        cvo.setStatus("Status");
                        cvo.setCounseldate("C. Date");

                        List<LICrewMonitoredResponseVO> list = new ArrayList<>(liCrewMonitoredResponse.getLiCrewMonitoredResponseVO());
                        list.add(0, cvo);
                        recyclerView.setAdapter(new LICrewMonitoredAdapter(LICrewMonitoredReportController.this, reportHeaderView, LICrewMonitoredReportController.this, list));
                    }
                    break;


                case Constants.KEY_VALUE :
                    try {

                        List<KeyValue> list = (ArrayList<KeyValue>)object;

                        //System.out.println("Response is " + new Gson().toJson(liCrewMonitoredResponse));

                        if (list != null ) {

                            ReportHeaderView reportHeaderView = new ReportHeaderView();
                            String header_str= "CREW : " + liCrewMonitoredResponseVO.getCrewid() ;
                            header_str += "\n Crew Biodata";
                            reportHeaderView.setEnergyConsume(header_str );
                            recyclerView.setAdapter(new KeyValueAdapter(LICrewMonitoredReportController.this, reportHeaderView, LICrewMonitoredReportController.this, list));
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                        commonClass.showToast("Unable to get data. Please try again.");
                    }

                    break;

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
