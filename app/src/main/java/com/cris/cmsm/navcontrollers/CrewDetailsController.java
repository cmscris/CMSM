package com.cris.cmsm.navcontrollers;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;

import com.cris.cmsm.R;
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
import com.cris.cmsm.models.request.GraphAPIRequest;
import com.cris.cmsm.models.request.KeyValueRequest;
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


public class CrewDetailsController extends AppCompatActivity implements OnItemClickListener, ResponseView {
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
        requestPresenter = new RequestPresenter(CrewDetailsController.this);
        commonClass = new CommonClass(CrewDetailsController.this);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        userLoginPreferences = new UserLoginPreferences(CrewDetailsController.this);
        loginInfoModel = userLoginPreferences.getLoginUser();


        callWebService();

    }

    @Override
    public void OnItemClick(Object model) {
        if (model != null) {

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

        }

    }



    private void callWebService() {
        try {


            DataHolder.setLevel(0);
            KeyValueRequest request = new KeyValueRequest();
            //Log.d("USER " , loginInfoModel.getCrewid());
            //Log.d("DataHolder.getType" , "" +       DataHolder.getType());


            String crewid = loginInfoModel.getCrewid();         // IF CREW LOGINS


            System.out.println("Response is >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> " + crewid);
            //Log.d("-->",crewid);
            if(!DataHolder.getCrewid().equals(""))
                crewid = DataHolder.getCrewid();                // DRILL DOWN CREWID FOR LI LOGIN
            else
                DataHolder.setCrewid(crewid);

            Log.d("---->",DataHolder.getCrewid());

            request.setUser(crewid);

            switch(DataHolder.getType())
            {
                case Constants.CREW_BIODATA :
                    requestPresenter.Request(request, "Getting Data", Constants.CREW_BIODATA);
                    break;
                case Constants.TRAINING_DETAILS :
                    requestPresenter.Request(request, "Getting Data", Constants.TRAINING_DETAILS);
                    break;
                case Constants.LOCO_COMPETENCY_DETAILS :
                    requestPresenter.Request(request, "Getting Data", Constants.LOCO_COMPETENCY_DETAILS);
                    break;
                case Constants.ROAD_LEARNING_DETAILS :
                    requestPresenter.Request(request, "Getting Data", Constants.ROAD_LEARNING_DETAILS);
                    break;
                case Constants.MILEAGE_DETAILS :
                    //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                    requestPresenter.Request(request, "Getting Data", Constants.MILEAGE_DETAILS);
                    break;
                case Constants.OVERTIME :
                    //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                    requestPresenter.Request(request, "Getting Data", Constants.OVERTIME);
                    break;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }




    private void callDetailsWebService(String pay_month, int TYPE) {
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
    }



    @Override
    public void ResponseOk(Object object, int position) {
        try {

            CrewDetailsResponse crewDetailsResponse = (CrewDetailsResponse) object;
            System.out.println("Response is >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> " + new Gson().toJson(crewDetailsResponse));
            loginInfoModel = userLoginPreferences.getLoginUser();


            switch(position)
            {

                case Constants.CREW_BIODATA :

                    try {


                        //System.out.println("Response is " + new Gson().toJson(liCrewMonitoredResponse));

                        if (crewDetailsResponse != null && crewDetailsResponse.getKeyval() != null) {

                            ReportHeaderView reportHeaderView = new ReportHeaderView();
                            String header_str= "Crew Biodata";


                            header_str += "\nCREW : " + DataHolder.getCrewid() ;
                            reportHeaderView.setEnergyConsume(header_str );
                            List<KeyValue> list = new ArrayList<>(crewDetailsResponse.getKeyval());

                            recyclerView.setAdapter(new KeyValueAdapter(CrewDetailsController.this, reportHeaderView, CrewDetailsController.this, list));
                        }



                    } catch (Exception e) {
                        e.printStackTrace();
                        commonClass.showToast("Unable to get data. Please try again.");
                    }
                    break;


                case Constants.TRAINING_DETAILS :

                    if (crewDetailsResponse != null && crewDetailsResponse.getTrainingDetailsVO() != null) {

                        ReportHeaderView reportHeaderView = new ReportHeaderView();


                        String header_str = " Training Details";
                        header_str += "\nCREW : " + DataHolder.getCrewid() ;

                        reportHeaderView.setEnergyConsume(header_str );

                        TrainingDetailsVO cvo = new TrainingDetailsVO();

                        cvo.setCode("Code");
                        cvo.setDone_date("Done Date");
                        cvo.setDue_date("Due Date");

                        List<TrainingDetailsVO> list = new ArrayList<>(crewDetailsResponse.getTrainingDetailsVO());
                        list.add(0, cvo);
                        recyclerView.setAdapter(new TrainingDetailsAdapter(CrewDetailsController.this, reportHeaderView, CrewDetailsController.this, list));
                    }
                    break;


                case Constants.LOCO_COMPETENCY_DETAILS :
                    if (crewDetailsResponse != null && crewDetailsResponse.getLocoCompetencyVO() != null) {

                        ReportHeaderView reportHeaderView = new ReportHeaderView();
                        String header_str = "Loco Competency Report";
                        header_str +=  "\nCREW : " + DataHolder.getCrewid();

                        reportHeaderView.setEnergyConsume(header_str );

                        LocoCompetencyVO cvo = new LocoCompetencyVO();
                        cvo.setType("Type");
                        cvo.setLast_drive_date("Lst Drv Date");
                        cvo.setDue_date("Due Date");

                        List<LocoCompetencyVO> list = new ArrayList<>(crewDetailsResponse.getLocoCompetencyVO());
                        list.add(0, cvo);
                        recyclerView.setAdapter(new LocoCompetencyAdapter(CrewDetailsController.this, reportHeaderView, CrewDetailsController.this, list));
                    }
                    break;


                case Constants.ROAD_LEARNING_DETAILS :
                    if (crewDetailsResponse != null && crewDetailsResponse.getRoadLearningDetails() != null) {

                        ReportHeaderView reportHeaderView = new ReportHeaderView();
                        String header_str= "Road Learning Report";

                        header_str += "\nCREW : " + DataHolder.getCrewid();
                        reportHeaderView.setEnergyConsume(header_str );

                        RoadLearningDetailsVO cvo = new RoadLearningDetailsVO();

                        cvo.setLr_sec("LR SEC");
                        cvo.setDrv_date("Drv Date");
                        cvo.setDue_date("Due Date");
                        cvo.setDay_trip_due("Day Trip Due");
                        cvo.setNight_trip_due("Night Trip Due");

                        List<RoadLearningDetailsVO> list = new ArrayList<>(crewDetailsResponse.getRoadLearningDetails());
                        list.add(0, cvo);
                        recyclerView.setAdapter(new RoadLearningAdapter(CrewDetailsController.this, reportHeaderView, CrewDetailsController.this, list));
                    }
                    break;


                case Constants.MILEAGE_DETAILS :
                    if (crewDetailsResponse != null && crewDetailsResponse.getCrewMileageDetailsVO() != null) {

                        ReportHeaderView reportHeaderView = new ReportHeaderView();
                        String header_str= "Mileage Report";

                        header_str += "\nCREW : " + DataHolder.getCrewid() ;
                        reportHeaderView.setEnergyConsume(header_str );

                        CrewMileageDetailsVO cvo = new CrewMileageDetailsVO();

                        cvo.setDuty_hrs("Duty Hrs");
                        cvo.setAlkm_non_leave("ALKM Non Leave");
                        cvo.setAlkm_leave("ALKM Leave");
                        cvo.setTotal_kms("Total KMS");
                        cvo.setPay_month("Pay Month");

                        List<CrewMileageDetailsVO> list = new ArrayList<>(crewDetailsResponse.getCrewMileageDetailsVO());
                        list.add(0, cvo);
                        recyclerView.setAdapter(new MileageDetailsAdapter(CrewDetailsController.this, reportHeaderView, CrewDetailsController.this, list));
                    }
                    break;


                case Constants.SLOT_MILEAGE_DETAILS :

                    try {


                        //System.out.println("Response is " + new Gson().toJson(liCrewMonitoredResponse));

                        if (crewDetailsResponse != null && crewDetailsResponse.getKeyval() != null) {

                            ReportHeaderView reportHeaderView = new ReportHeaderView();
                            String header_str= "Mileage Details";


                            header_str += "\nCREW : " + DataHolder.getCrewid() ;
                            reportHeaderView.setEnergyConsume(header_str );
                            List<KeyValue> list = new ArrayList<>(crewDetailsResponse.getKeyval());

                            recyclerView.setAdapter(new KeyValueAdapter(CrewDetailsController.this, reportHeaderView, CrewDetailsController.this, list));
                        }



                    } catch (Exception e) {
                        e.printStackTrace();
                        commonClass.showToast("Unable to get data. Please try again.");
                    }
                    break;


                case Constants.OVERTIME :
                    if (crewDetailsResponse != null && crewDetailsResponse.getCrewOvertimeDetailsVO() != null) {

                        ReportHeaderView reportHeaderView = new ReportHeaderView();
                        String header_str= "Overtime Report";

                        header_str += "\nCREW : " + DataHolder.getCrewid() ;
                        reportHeaderView.setEnergyConsume(header_str );

                        CrewOvertimeDetailsVO cvo = new CrewOvertimeDetailsVO();

                        cvo.setRunning_days("Run Days");
                        cvo.setNon_run_days("Nonrun Days");
                        cvo.setLeaves("Leaves");
                        cvo.setAbsents("Absents");
                        cvo.setTotal_duty("T.Duty");
                        cvo.setFrom("From");
                        cvo.setTo("To");

                        List<CrewOvertimeDetailsVO> list = new ArrayList<>(crewDetailsResponse.getCrewOvertimeDetailsVO());
                        list.add(0, cvo);
                        recyclerView.setAdapter(new OvertimeDetailsAdapter(CrewDetailsController.this, reportHeaderView, CrewDetailsController.this, list));
                    }
                    break;



                case Constants.SLOT_OVERTIME_DETAILS :

                    try {


                        //System.out.println("Response is " + new Gson().toJson(liCrewMonitoredResponse));

                        if (crewDetailsResponse != null && crewDetailsResponse.getKeyval() != null) {

                            ReportHeaderView reportHeaderView = new ReportHeaderView();
                            String header_str= "Overtime Details";


                            header_str += "\nCREW : " + DataHolder.getCrewid() ;
                            reportHeaderView.setEnergyConsume(header_str );
                            List<KeyValue> list = new ArrayList<>(crewDetailsResponse.getKeyval());

                            recyclerView.setAdapter(new KeyValueAdapter(CrewDetailsController.this, reportHeaderView, CrewDetailsController.this, list));
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
