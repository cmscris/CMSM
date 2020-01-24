package com.cris.cmsm;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.cris.cmsm.adapter.MenuAdapter;
import com.cris.cmsm.database.DataBaseManager;
import com.cris.cmsm.database.DataHolder;
import com.cris.cmsm.interfaces.RecyclerItemListener;
import com.cris.cmsm.models.Division;
import com.cris.cmsm.models.MenuModel;
import com.cris.cmsm.models.Railway;
import com.cris.cmsm.models.response.DivisionVO;
import com.cris.cmsm.models.response.LoginIfoVO;
import com.cris.cmsm.models.response.MasterData;
import com.cris.cmsm.models.response.RailwayVO;
import com.cris.cmsm.navcontrollers.AbnormalityController;
import com.cris.cmsm.navcontrollers.AssetsReportController;
import com.cris.cmsm.navcontrollers.BoardController;
import com.cris.cmsm.navcontrollers.CompareConsumController;
import com.cris.cmsm.navcontrollers.CrewAvailabilityController;
import com.cris.cmsm.navcontrollers.CrewAvailabilityDetailFilterController;
import com.cris.cmsm.navcontrollers.CrewDetailsController;
import com.cris.cmsm.navcontrollers.CrewPositionController;
import com.cris.cmsm.navcontrollers.DetailController;
import com.cris.cmsm.navcontrollers.IrregularCrewController;
import com.cris.cmsm.navcontrollers.LICrewMonitoredReportController;
import com.cris.cmsm.navcontrollers.LiInspectionController;
import com.cris.cmsm.navcontrollers.MonthGraphController;
import com.cris.cmsm.navcontrollers.SubStationConsController;
import com.cris.cmsm.navcontrollers.VcdStatusController;
import com.cris.cmsm.prefrences.UserLoginPreferences;
import com.cris.cmsm.presenter.RequestPresenter;
import com.cris.cmsm.presenterview.ResponseView;
import com.cris.cmsm.util.CommonClass;
import com.cris.cmsm.util.Constants;
import com.cris.cmsm.util.FontFamily;

import java.util.ArrayList;
import java.util.List;



/**
 * Created by Anand.kumar on 10/24/2016.
 */
public class HomeActivity extends AppCompatActivity implements ResponseView, RecyclerItemListener, View.OnClickListener {

    private RecyclerView recyclerView;
    private TextView tv_copyright, action_bar_title;
    private FontFamily fontFamily;
    private ImageView iv_left, iv_middle, iv_right;
    private Resources res;
    private LoginIfoVO loginInfoModel;
    private UserLoginPreferences userLoginPreferences;
    private DataBaseManager dataBaseManager;
    private RequestPresenter requestPresenter;
    private CommonClass commonClass;
    private String sfoorti_url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        commonClass = new CommonClass(HomeActivity.this);
        requestPresenter = new RequestPresenter(HomeActivity.this);
        userLoginPreferences = new UserLoginPreferences(HomeActivity.this);
        loginInfoModel = userLoginPreferences.getLoginUser();
        dataBaseManager = new DataBaseManager(HomeActivity.this);
        dataBaseManager.createDatabase();


        res = getResources();
        fontFamily = new FontFamily(HomeActivity.this);
        iv_left = (ImageView) findViewById(R.id.iv_left);
        iv_middle = (ImageView) findViewById(R.id.iv_middle);
        iv_right = (ImageView) findViewById(R.id.iv_right);
        action_bar_title = (TextView) findViewById(R.id.action_bar_title);
        tv_copyright = (TextView) findViewById(R.id.tv_copyright);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        GridLayoutManager manager = new GridLayoutManager(HomeActivity.this, 2);
        recyclerView.setLayoutManager(manager);

        recyclerView.setAdapter(new MenuAdapter(HomeActivity.this, HomeActivity.this, getMenuList()));
        recyclerView.setNestedScrollingEnabled(false);


        iv_left.setVisibility(View.GONE);
        iv_middle.setVisibility(View.GONE);
        iv_right.setVisibility(View.VISIBLE);

        action_bar_title.setTypeface(fontFamily.getBold());
        action_bar_title.setText("CMS- " + loginInfoModel.getFname());
        tv_copyright.setTypeface(fontFamily.getRegular());
        iv_right.setOnClickListener(this);





        callWebService();
        DataHolder.setType(0);
    }

    private void callWebService() {
        if (dataBaseManager.getRailwayList(false).size() == 0) {
            requestPresenter.Request(null, "Retrieving Data...", Constants.MASTERS);
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        DataHolder.getInstance().AssignNull();
    }

    // RENDER THE HOME PAGE MENU ITEMS
    private List<MenuModel> getMenuList() {
        List<MenuModel> list = new ArrayList<>();


        if(loginInfoModel.getRoleid().equals("IR"))
        {
            list.add(new MenuModel(R.color.colorCardOrange, R.drawable.crew_position, res.getString(R.string.crew_strength)));
            list.add(new MenuModel(R.color.colorCardFive, R.drawable.crew_strength, res.getString(R.string.crew_position)));
            list.add(new MenuModel(R.color.colorCardFive, R.drawable.consumption_analytics, res.getString(R.string.crew_utilization)));
            list.add(new MenuModel(R.color.colorCardOrange, R.drawable.availability, res.getString(R.string.crew_availability)));
            list.add(new MenuModel(R.color.colorCardOrange, R.drawable.mis_reporting, res.getString(R.string.energy_consumption)));
            list.add(new MenuModel(R.color.colorCardFive, R.drawable.abnormality, res.getString(R.string.abnormality)));
            list.add(new MenuModel(R.color.colorCardFive, R.drawable.vcd_status, res.getString(R.string.vcd_status)));
            list.add(new MenuModel(R.color.colorCardOrange, R.drawable.irregular, res.getString(R.string.irregular_crew)));
            //list.add(new MenuModel(R.color.colorCardOrange, R.drawable.ir, res.getString(R.string.sfoorti)));
            list.add(new MenuModel(R.color.colorCardSix, R.drawable.photo_gallery, res.getString(R.string.photo_gallery)));
            //list.add(new MenuModel(R.color.colorTeal, R.drawable.feedback, res.getString(R.string.feedback)));
        }
        else if(loginInfoModel.getRoleid().equals("LI"))
        {

            if(loginInfoModel.getCrewid() == null)
            {
                list.add(new MenuModel(R.color.colorCardOrange, R.drawable.crew_position, res.getString(R.string.crew_monitored)));
                //list.add(new MenuModel(R.color.colorCardFive, R.drawable.consumption_analytics, res.getString(R.string.crew_availability_detail)));
                list.add(new MenuModel(R.color.colorCardFive, R.drawable.abnormality,res.getString(R.string.abnormality_report)));
                list.add(new MenuModel(R.color.colorCardFive,R.drawable.consumption_analytics,res.getString(R.string.grading)));
                list.add(new MenuModel(R.color.colorCardOrange,R.drawable.crew_position,res.getString(R.string.crew_counselling)));
                list.add(new MenuModel(R.color.colorCardOrange,R.drawable.crew_position,"Crew Current Status"));
                list.add(new MenuModel(R.color.colorCardFive, R.drawable.consumption_analytics,"LI Movement"));
                list.add(new MenuModel(R.color.colorCardFive, R.drawable.availability, res.getString(R.string.inspection_record)));
                //list.add(new MenuModel(R.color.colorTeal, R.drawable.feedback, res.getString(R.string.feedback)));
            }
            else
            {
                list.add(new MenuModel(R.color.colorCardOrange, R.drawable.crew_position, res.getString(R.string.biodata)));
                list.add(new MenuModel(R.color.colorCardFive, R.drawable.crew_strength, res.getString(R.string.training_details)));
                list.add(new MenuModel(R.color.colorCardFive, R.drawable.consumption_analytics, res.getString(R.string.loco_competency)));
                list.add(new MenuModel(R.color.colorCardOrange, R.drawable.availability, res.getString(R.string.road_learnings)));
                list.add(new MenuModel(R.color.colorCardFive, R.drawable.consumption_analytics, res.getString(R.string.crew_availability_detail)));
                //list.add(new MenuModel(R.color.colorTeal, R.drawable.feedback, res.getString(R.string.feedback)));
            }

        }
        else if(loginInfoModel.getRoleid().equals("CREW"))
        {
            list.add(new MenuModel(R.color.colorCardOrange, R.drawable.crew_position, res.getString(R.string.biodata)));
            list.add(new MenuModel(R.color.colorCardFive, R.drawable.crew_strength, res.getString(R.string.training_details)));
            list.add(new MenuModel(R.color.colorCardFive, R.drawable.consumption_analytics, res.getString(R.string.loco_competency)));
            list.add(new MenuModel(R.color.colorCardOrange, R.drawable.availability, res.getString(R.string.road_learnings)));
            list.add(new MenuModel(R.color.colorCardOrange, R.drawable.availability, res.getString(R.string.crew_mileage)));
            list.add(new MenuModel(R.color.colorCardFive, R.drawable.consumption_analytics, res.getString(R.string.overtime)));
            list.add(new MenuModel(R.color.colorCardFive, R.drawable.consumption_analytics, res.getString(R.string.crew_availability_detail)));
            list.add(new MenuModel(R.color.colorCardOrange, R.drawable.abnormality,res.getString(R.string.abnormality_report)));
            //list.add(new MenuModel(R.color.colorTeal, R.drawable.feedback, res.getString(R.string.feedback)));
        }


        list.add(new MenuModel(R.color.colorCardOrange, R.drawable.icon_websites,res.getString(R.string.train_enquiry_freight)));
        return list;

    }

    @Override
    public void onItemClickListener(MenuModel model) {

        System.out.println(">>>>>>>>>>>>>> Menu Title >>>>>>>>>>>>>>>>>>>>>>> " + model.getMenuTitle());
        if (model.getMenuTitle().equals(res.getString(R.string.crew_strength))) {
            CommonClass.goToNextScreen(HomeActivity.this, BoardController.class, true, false);
        } else if (model.getMenuTitle().equals(res.getString(R.string.crew_utilization))) {
            CommonClass.goToNextScreen(HomeActivity.this, DetailController.class, true, Constants.CREW_UTILIZATION);
            //CommonClass.goToNextScreen(HomeActivity.this, CrewUtilizationController.class, true, false);
        } else if (model.getMenuTitle().equals(res.getString(R.string.crew_availability))) {
            DataHolder.setType(Constants.CREW_AVAILABILITY);
            CommonClass.goToNextScreen(HomeActivity.this, CrewAvailabilityController.class, true, false);
        } else if (model.getMenuTitle().equals(res.getString(R.string.crew_position))) {
            CommonClass.goToNextScreen(HomeActivity.this, CrewPositionController.class, true, false);
        }else if (model.getMenuTitle().equals(res.getString(R.string.energy_consumption))) {
            CommonClass.goToNextScreen(HomeActivity.this, DetailController.class, true, Constants.ENERGY_CONSUMPTION);
        }else if (model.getMenuTitle().equals(res.getString(R.string.abnormality))) {
            CommonClass.goToNextScreen(HomeActivity.this, AbnormalityController.class, true, false);
        }else if (model.getMenuTitle().equals(res.getString(R.string.irregular_crew))) {
            CommonClass.goToNextScreen(HomeActivity.this, IrregularCrewController.class, true, false);
        }else if (model.getMenuTitle().equals(res.getString(R.string.vcd_status))) {
            CommonClass.goToNextScreen(HomeActivity.this, VcdStatusController.class, true, false);
        }else if (model.getMenuTitle().equals(res.getString(R.string.crew_monitored))) {
            CommonClass.goToNextScreen(HomeActivity.this, LICrewMonitoredReportController.class, true, false);
        }else if (model.getMenuTitle().equals(res.getString(R.string.biodata))) {
            DataHolder.setType(Constants.CREW_BIODATA);
            CommonClass.goToNextScreen(HomeActivity.this, CrewDetailsController.class, true, false);
        }else if (model.getMenuTitle().equals(res.getString(R.string.training_details))) {
            DataHolder.setType(Constants.TRAINING_DETAILS);
            CommonClass.goToNextScreen(HomeActivity.this, CrewDetailsController.class, true, false);
        }else if (model.getMenuTitle().equals(res.getString(R.string.loco_competency))) {
            DataHolder.setType(Constants.LOCO_COMPETENCY_DETAILS);
            CommonClass.goToNextScreen(HomeActivity.this, CrewDetailsController.class, true, false);
        }else if (model.getMenuTitle().equals(res.getString(R.string.road_learnings))) {
            DataHolder.setType(Constants.ROAD_LEARNING_DETAILS);
            CommonClass.goToNextScreen(HomeActivity.this, CrewDetailsController.class, true, false);
        }else if (model.getMenuTitle().equals(res.getString(R.string.crew_mileage))) {
            DataHolder.setType(Constants.MILEAGE_DETAILS);
            CommonClass.goToNextScreen(HomeActivity.this, CrewDetailsController.class, true, false);
        }else if (model.getMenuTitle().equals(res.getString(R.string.overtime))) {
            DataHolder.setType(Constants.OVERTIME);
            CommonClass.goToNextScreen(HomeActivity.this, CrewDetailsController.class, true, false);
        }else if (model.getMenuTitle().equals(res.getString(R.string.crew_availability_detail))) {
            DataHolder.setType(Constants.CREW_AVAILABILITY_DETAIL);
            //CommonClass.goToNextScreen(HomeActivity.this, CrewAvailabilityDetailController.class, true, false);
            CommonClass.goToNextScreen(HomeActivity.this, CrewAvailabilityDetailFilterController.class, true, false);
        }else if(model.getMenuTitle().equals(res.getString(R.string.abnormality_report))){
            DataHolder.setType(Constants.Report_Abnormality);
            CommonClass.goToNextScreen(HomeActivity.this, Abnormality_fill_activity.class, true, false);
        }
        else if(model.getMenuTitle().equals(res.getString(R.string.train_enquiry_freight))){
            CommonClass.goToNextScreen(HomeActivity.this, WebsitesLink.class, true, res.getString(R.string.train_enquiry_freight), Constants.TRAINENQUIRYFREIGHT);
        }
        else if(model.getMenuTitle().equals("Grading")){
            DataHolder.setType(Constants.Grading);
            System.out.println(">>>>>>>>>>>>>>step1 for crew grading>>>>>>>>>>>>>>>>>>>>>>>> ");
            CommonClass.goToNextScreen(HomeActivity.this, Grading.class, true, false);
        }
        else if(model.getMenuTitle().equals("Crew Counselling")){
            DataHolder.setType(Constants.CrewCounselling);
            System.out.println(">>>>>>>>>>>>>>step1 for crew Counselling>>>>>>>>>>>>>>>>>>>>>>>> ");
            CommonClass.goToNextScreen(HomeActivity.this, Grading.class, true, false);
        }
        else if(model.getMenuTitle().equals("Crew Current Status")){
            DataHolder.setType(Constants.CREWCURRENTSTATUS);
            System.out.println(">>>>>>>>>>>>>>step1 for crew current status>>>>>>>>>>>>>>>>>>>>>>>> ");
            CommonClass.goToNextScreen(HomeActivity.this, Crew_Current_Status.class, true, false);
            // CommonClass.goToNextScreen(HomeActivity.this, LICrewsttstable.class, true, false);
        }
        else if(model.getMenuTitle().equals("LI Movement")){
            DataHolder.setType(Constants.LI_MOVEMENT);
            System.out.println(">>>>>>>>>>>>>>step1 for LI_MOVEMENT>>>>>>>>>>>>>>>>>>>>>>> ");
            Intent i=new Intent(HomeActivity.this,LImovement.class);
            startActivity(i);
            //CommonClass.goToNextScreen(HomeActivity.this,CalendarCustomView.class, true, false);
            // CommonClass.goToNextScreen(HomeActivity.this, LICrewsttstable.class, true, false);
        }
        else if(model.getMenuTitle().equals(res.getString(R.string.inspection_record))){
            DataHolder.setType(Constants.INSPECTION_RECORD);
            System.out.println(">>>>>>>>>>>>>> Inspection Record for LI >>>>>>>>>>>>>>>>>>>>>>> ");
            Intent i=new Intent(HomeActivity.this, LiInspectionController.class);
            startActivity(i);
            //CommonClass.goToNextScreen(HomeActivity.this,CalendarCustomView.class, true, false);
            // CommonClass.goToNextScreen(HomeActivity.this, LICrewsttstable.class, true, false);
        }

        else if (model.getMenuTitle().equals(res.getString(R.string.sfoorti))) {

            if(loginInfoModel.getAuthlevel().equals("IR"))
                sfoorti_url = Constants.SFOORTI + "&LOCN=&LOCNFLAG=I";
            else if(loginInfoModel.getAuthlevel().equals("ZONE"))
                sfoorti_url = Constants.SFOORTI + "&LOCN=" + loginInfoModel.getRlycode() + "&LOCNFLAG=Z";
            else if(loginInfoModel.getAuthlevel().equals("DIVISION"))
                sfoorti_url = Constants.SFOORTI + "&LOCN=" + loginInfoModel.getRlycode() + "&LOCNFLAG=D";


            Log.d("sfforti_url"," : " + sfoorti_url);
            CommonClass.goToNextScreen(HomeActivity.this, WebsitesLink.class, true, res.getString(R.string.ir_greenri), sfoorti_url);
        }    else if (model.getMenuTitle().equals(res.getString(R.string.mis_report))) {
            CommonClass.goToNextScreen(HomeActivity.this, DetailController.class, true, Constants.ANNEXURE_14);
        } else if (model.getMenuTitle().equals(res.getString(R.string.photo_gallery))) {
            CommonClass.goToNextScreen(HomeActivity.this, DetailController.class, true, Constants.PHOTO_GALLERY);
        } else if (model.getMenuTitle().equals(res.getString(R.string.feedback))) {
            CommonClass.goToNextScreen(HomeActivity.this, DetailController.class, true, Constants.FEEDBACK);
        } else if (model.getMenuTitle().equals(res.getString(R.string.consumption_analytics))) {
            CommonClass.goToNextScreen(HomeActivity.this, CompareConsumController.class, true, false);
        } else if (model.getMenuTitle().equals(res.getString(R.string.monthly_consumption))) {
            CommonClass.goToNextScreen(HomeActivity.this, MonthGraphController.class, true, false);
        } else if (model.getMenuTitle().equals(res.getString(R.string.sub_station_cons))) {
            CommonClass.goToNextScreen(HomeActivity.this, SubStationConsController.class, true, false);
        } else if (model.getMenuTitle().equalsIgnoreCase(res.getString(R.string.sub_station_assets))) {
            CommonClass.goToNextScreen(HomeActivity.this, SubStationAssetsActivity.class, true, false);
        } else if (model.getMenuTitle().equalsIgnoreCase(res.getString(R.string.assets_report))) {
            CommonClass.goToNextScreen(HomeActivity.this, AssetsReportController.class, true, false);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_right:
                showLogoutDialog(HomeActivity.this, "Do you want to logout?", true);
                break;
        }
    }

    private void showLogoutDialog(final Activity context, String msg, final boolean isLogout) {
        new AlertDialog.Builder(context).setCancelable(isLogout)
                .setTitle(getResources().getString(R.string.cms))
                .setMessage(msg)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        if (isLogout) {
                            logOut();
                        } else {
                            callWebService();
                        }
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        if (isLogout) {
                            // Logout
                        } else {
                            context.finish();
                        }
                    }
                })
                .setIcon(R.drawable.icon_logo)
                .show();
    }

    private void logOut() {
        userLoginPreferences.clearUser();
        DataHolder.setLevel(0);
        //CommonClass.goToNextScreen(HomeActivity.this, DetailController.class, true, Constants.LOGIN_OPTIONS);


        Intent landing = new Intent(HomeActivity.this, LandingActivity.class);
        landing.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                | Intent.FLAG_ACTIVITY_CLEAR_TASK
                | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(landing);
        overridePendingTransition(R.animator.slide_in_right, R.animator.slide_out_left);
        finish();


        /*
        Intent logout = new Intent(HomeActivity.this, LoginActivity.class);
        logout.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                | Intent.FLAG_ACTIVITY_CLEAR_TASK
                | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(logout);

        */
    }

    @Override
    public void ResponseOk(Object object, int position) {
        try {
            if (object instanceof MasterData) {
                MasterData masterData = (MasterData) object;
                if (masterData.getRailwayVOs().size() > 0) {
                    dataBaseManager.deleteRailwayALL();
                    for (RailwayVO railway :
                            masterData.getRailwayVOs()) {
                        Railway zone = new Railway();
                        zone.setRlycode(railway.getRlycode());
                        zone.setFname(railway.getFname());
                        zone.setSname(railway.getSname());
                        zone.setFlag(railway.getFlag());
                        dataBaseManager.insertRailway(zone);
                    }
                }
                if (masterData.getDivisionVOs().size() > 0) {
                    dataBaseManager.deleteDivisionALL();
                    for (DivisionVO divisionVO :
                            masterData.getDivisionVOs()) {
                        Division division = new Division();
                        division.setDivid(divisionVO.getDivid());
                        division.setDivcode(divisionVO.getDivcode());
                        division.setDivflag(divisionVO.getDivflag());
                        division.setFname(divisionVO.getFname());
                        division.setSname(divisionVO.getSname());
                        division.setRlycode(divisionVO.getRlycode());
                        division.setDivflag(divisionVO.getDivflag());
                        division.setElectrifiedflag(divisionVO.getElectrifiedflag() == null ? "" : divisionVO.getElectrifiedflag());
                        dataBaseManager.insertDivision(division);
                    }
                }
            } else
                showLogoutDialog(HomeActivity.this, "Unable to retrieve data. Do you want to retry?", false);

        } catch (Exception e) {
            e.printStackTrace();
            showLogoutDialog(HomeActivity.this, "Unable to retrieve data. Do you want to retry?", false);
        }

    }

    @Override
    public void Error() {
        showLogoutDialog(HomeActivity.this, "Unable to retrieve data. Do you want to retry?", false);
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
