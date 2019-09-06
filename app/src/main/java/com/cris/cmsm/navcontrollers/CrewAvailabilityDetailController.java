package com.cris.cmsm.navcontrollers;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;

import com.cris.cmsm.R;
import com.cris.cmsm.adapter.CrewAvailabilityDetailAdapter;
import com.cris.cmsm.adapter.KeyValueAdapter;
import com.cris.cmsm.adapter.LICrewMonitoredAdapter;
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
import com.cris.cmsm.models.request.CrewAvailabilityDetailRequest;
import com.cris.cmsm.models.request.KeyValueRequest;
import com.cris.cmsm.models.response.AbnormalityResponse;
import com.cris.cmsm.models.response.CrewAvailabilityDetailResponse;
import com.cris.cmsm.models.response.CrewAvailabilityDetailVO;
import com.cris.cmsm.models.response.CrewDetailsResponse;
import com.cris.cmsm.models.response.CrewMileageDetailsVO;
import com.cris.cmsm.models.response.CrewOvertimeDetailsVO;
import com.cris.cmsm.models.response.LICrewMonitoredResponse;
import com.cris.cmsm.models.response.LICrewMonitoredResponseVO;
import com.cris.cmsm.models.response.LocoCompetencyVO;
import com.cris.cmsm.models.response.LoginIfoVO;
import com.cris.cmsm.models.response.LoginResponse;
import com.cris.cmsm.models.response.RoadLearningDetailsVO;
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
 * Created by cms on 4/18/18.
 */


public class CrewAvailabilityDetailController extends AppCompatActivity implements OnItemClickListener, ResponseView {
    private PinchRecyclerView recyclerView;
    private CommonClass commonClass;
    private InsertRequestPresenter insertRequestPresenter;
    private SubstationResponseVO substationResponseVO;
    private LoginIfoVO loginInfoModel;
    private UserLoginPreferences userLoginPreferences;
    private RequestPresenter requestPresenter;
    //private CrewMileageDetailsVO crewMileageDetailsVO;
    //private CrewOvertimeDetailsVO crewOvertimeDetailsVO;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sub_station_report);
        requestPresenter = new RequestPresenter(CrewAvailabilityDetailController.this);
        commonClass = new CommonClass(CrewAvailabilityDetailController.this);
        recyclerView = (PinchRecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        userLoginPreferences = new UserLoginPreferences(CrewAvailabilityDetailController.this);
        loginInfoModel = userLoginPreferences.getLoginUser();

        CrewAvailabilityDetailResponse crewAvailabilityDetailResponse = DataHolder.getInstance().getCrewAvailabilityDetailResponse();

        ReportHeaderView reportHeaderView = new ReportHeaderView();
        String header_str= "Crew Availability Detail Report";

        header_str += "\nCREW : " + DataHolder.getCrewid() ;
        reportHeaderView.setEnergyConsume(header_str );

        CrewAvailabilityDetailVO cvo = new CrewAvailabilityDetailVO();

        cvo.setCrewId("Crew ID");
        cvo.setCrewName("NAME");
        cvo.setCrewDesignation("ORIG DESIG");
        cvo.setCrewType("OFF.\n" +"DESIG");
        cvo.setTrainNo("TRAIN NO/\n" +"NON RUN\n" +"CODE");
        cvo.setLocoNo("LOCO NO.");
        cvo.setLastDutyHours("LAST DUTY HRS");
        cvo.setSignOffTime("SIGNOFF");
        cvo.setAvailedHours("AVAILABLE");
        cvo.setCrewRest("REST HRS");
        cvo.setMobileNO("MOB NO");
        cvo.setCadre("CDR");

        List<CrewAvailabilityDetailVO> list = new ArrayList<>(crewAvailabilityDetailResponse.getCrewAvailabilityDetailVO());
        list.add(0, cvo);
        recyclerView.setAdapter(new CrewAvailabilityDetailAdapter(CrewAvailabilityDetailController.this, reportHeaderView, CrewAvailabilityDetailController.this, list));



    }

    @Override
    public void OnItemClick(Object model) {
       /* if (model != null) {

            if (model instanceof CrewMileageDetailsVO) {

                crewMileageDetailsVO = (CrewMileageDetailsVO) model;
                callDetailsWebService(crewMileageDetailsVO.getPay_month(),Constants.SLOT_MILEAGE_DETAILS);

            }
            else if (model instanceof CrewOvertimeDetailsVO)
            {
                crewOvertimeDetailsVO = (CrewOvertimeDetailsVO) model;
                callDetailsWebService(crewOvertimeDetailsVO.getFrom(),Constants.SLOT_OVERTIME_DETAILS);
                Log.d("USER " , crewOvertimeDetailsVO.getFrom() + " - " + crewOvertimeDetailsVO.getTo());
            }

        }*/

    }



    private void callWebService() {
        try {


            DataHolder.setLevel(0);
            CrewAvailabilityDetailRequest request = new CrewAvailabilityDetailRequest();
            //Log.d("USER " , loginInfoModel.getCrewid());
            //Log.d("DataHolder.getType" , "" +       DataHolder.getType());


            String crewid = loginInfoModel.getCrewid();         // IF CREW LOGINS
            request.setHqCode(loginInfoModel.getRlycode());

            System.out.println("Response is >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> " + crewid);
            //Log.d("-->",crewid);
            /*if(!DataHolder.getCrewid().equals(""))
                crewid = DataHolder.getCrewid();                // DRILL DOWN CREWID FOR LI LOGIN
            else
                DataHolder.setCrewid(crewid);

            Log.d("---->",DataHolder.getCrewid());*/

            //request.setUser(crewid);

            switch(DataHolder.getType())
            {
               case Constants.CREW_AVAILABILITY_DETAIL :
                    //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                    requestPresenter.Request(request, "Getting Data", Constants.CREW_AVAILABILITY_DETAIL);
                    break;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }




    /*private void callDetailsWebService(String pay_month, int TYPE) {
        try {

            DataHolder.setLevel(1);

            KeyValueRequest request = new KeyValueRequest();
            Log.d("USER " , loginInfoModel.getLoginid());
            Log.d("USER " , DataHolder.getCrewid());

            request.setUser(DataHolder.getCrewid());
            request.setPay_month(pay_month);

            if(TYPE==Constants.SLOT_MILEAGE_DETAILS)
            {
                //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                requestPresenter.Request(request, "Getting Data", Constants.SLOT_MILEAGE_DETAILS);
            }
            else if(TYPE==Constants.SLOT_OVERTIME_DETAILS)
            {
                //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                requestPresenter.Request(request, "Getting Data", Constants.SLOT_OVERTIME_DETAILS);
            }




        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/



    @Override
    public void ResponseOk(Object object, int position) {
        try {

            CrewAvailabilityDetailResponse crewDetailsResponse = (CrewAvailabilityDetailResponse) object;
            System.out.println("Response is >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> " + new Gson().toJson(crewDetailsResponse));
            loginInfoModel = userLoginPreferences.getLoginUser();


            switch(position)
            {

                case Constants.CREW_AVAILABILITY_DETAIL :
                    if (crewDetailsResponse != null && crewDetailsResponse.getCrewAvailabilityDetailVO() != null) {

                        ReportHeaderView reportHeaderView = new ReportHeaderView();
                        String header_str= "Crew Availability Detail Report";

                        header_str += "\nCREW : " + DataHolder.getCrewid() ;
                        reportHeaderView.setEnergyConsume(header_str );

                        CrewAvailabilityDetailVO cvo = new CrewAvailabilityDetailVO();

                        cvo.setCrewId("Crew ID");
                        cvo.setCrewName("NAME");
                        cvo.setCrewDesignation("ORIG DESIG");
                        cvo.setCrewType("OFF.\n" +"DESIG");
                        cvo.setTrainNo("TRAIN NO/\n" +"NON RUN\n" +"CODE");
                        cvo.setLocoNo("LOCO NO.");
                        cvo.setLastDutyHours("LAST DUTY HRS");
                        cvo.setSignOffTime("SIGNOFF");
                        cvo.setAvailedHours("AVAILABLE");
                        cvo.setCrewRest("REST HRS");
                        cvo.setMobileNO("MOB NO");
                        cvo.setCadre("CDR");

                        List<CrewAvailabilityDetailVO> list = new ArrayList<>(crewDetailsResponse.getCrewAvailabilityDetailVO());
                        list.add(0, cvo);
                        recyclerView.setAdapter(new CrewAvailabilityDetailAdapter(CrewAvailabilityDetailController.this, reportHeaderView, CrewAvailabilityDetailController.this, list));
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
