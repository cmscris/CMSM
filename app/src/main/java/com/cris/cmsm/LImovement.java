package com.cris.cmsm;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.applandeo.materialcalendarview.EventDay;
import com.applandeo.materialcalendarview.listeners.OnDayClickListener;
import com.cris.cmsm.adapter.Dateadapter;
import com.cris.cmsm.adapter.LImovementadapter;
import com.cris.cmsm.database.DataHolder;
import com.cris.cmsm.models.request.GraphAPIRequest;
import com.cris.cmsm.models.response.Limovementresponse;
import com.cris.cmsm.models.response.LoginIfoVO;
import com.cris.cmsm.models.response.Paramresponse;
import com.cris.cmsm.navcontrollers.BoardController;
import com.cris.cmsm.navcontrollers.DetailController;
import com.cris.cmsm.navcontrollers.LiArrivalController;
import com.cris.cmsm.navcontrollers.LiDepartureController;
import com.cris.cmsm.prefrences.UserLoginPreferences;
import com.cris.cmsm.presenter.RequestPresenter;
import com.cris.cmsm.presenterview.ResponseView;
import com.cris.cmsm.util.CommonClass;
import com.cris.cmsm.util.Constants;

import java.lang.reflect.Array;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class LImovement extends AppCompatActivity implements ResponseView {
    GridView gridview;
    TextView  display_current_date;
    private ArrayList<Limovdraftresponse> limovstatuslist;
    ImageView previous_month,next_month;
    private ImageView iv_right,iv_title_icon;
    Button btn_curr_mn_actvty;
    private SimpleDateFormat formatter = new SimpleDateFormat("MMMM yyyy", Locale.ENGLISH);
    private static final int MAX_CALENDAR_COLUMN = 42;
    int i=0;
    private RequestPresenter requestPresenter;
    GraphAPIRequest request;
    private Dateadapter mAdapter;
    ArrayList<String> calendarinput;
    private UserLoginPreferences userLoginPreferences;
    private LoginIfoVO loginInfoModel;
    CommonClass commonClass;
    String flagdate,flagstatus;
    StringBuilder sb =new StringBuilder();
    private Calendar cal = Calendar.getInstance(Locale.ENGLISH);
    ArrayList monthparam;
    Map<String, String> dataFilledStatus;
    String cur;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_limovement);
        previous_month=(ImageView) findViewById(R.id.previous_month);
        next_month=(ImageView) findViewById(R.id.next_month);
        iv_right=(ImageView) findViewById(R.id.iv_right);
        btn_curr_mn_actvty=(Button)findViewById(R.id.btn_curr_mn_actvty);
        display_current_date=(TextView)findViewById(R.id.display_current_date);
        gridview = (GridView) findViewById(R.id.calendar_grid);
        userLoginPreferences = new UserLoginPreferences(LImovement.this);
        iv_title_icon = findViewById(R.id.iv_title_icon);
        iv_title_icon.setImageResource(R.drawable.iv_back);
        loginInfoModel = userLoginPreferences.getLoginUser();
        iv_right.setImageResource(R.drawable.icon_logout);
        iv_right.setVisibility(View.VISIBLE);
        commonClass=new CommonClass(LImovement.this);
        calendarinput=new ArrayList <>();
        Calendar cal= Calendar.getInstance();
        int currentmonth= cal.get(Calendar.MONTH)+1 ;

        System.out.println("Current Month"+ currentmonth);
        System.out.println("Current Year"+ cal.get(Calendar.YEAR));
        setPreviousButtonClickEvent();
        setNextButtonClickEvent();
        monthparam=new ArrayList();
        requestPresenter=new RequestPresenter(LImovement.this);
        request=new GraphAPIRequest();
        monthparam.add(loginInfoModel.getLoginid());
        monthparam.add(currentmonth);
        monthparam.add(cal.get(Calendar.YEAR));
        request.setparamlist(monthparam);


        /* GETS THE DATES ALONG WITH THE STATUS OF DATA FILLED ON THAT OR NOT
        *
        * e.g.
        *
        * {"body":{"liMovementVOsResponse":[
          {"date":"01-12-2019","status":"N"},
          {"date":"02-12-2019","status":"N"},
          {"date":"03-12-2019","status":"N"},
        *
        *
        * */
        requestPresenter.Request(request,"Loading Data",Constants.LIMOVEMENT_DETAIL_MONTHLY);

        iv_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.iv_right:
                        showLogoutDialog(LImovement.this, "Do you want to logout?", true);
                        break;
                }
            }
        });
        iv_title_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }
        });
        btn_curr_mn_actvty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendarinput.clear();
                calendarinput.add(loginInfoModel.getLoginid());
                calendarinput.add(display_current_date.getText().toString());
                Intent i=new Intent(LImovement.this,SubmitReportLiMovement.class);
                i.putExtra("date",calendarinput);
                startActivity(i);
            }
        });

       gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView <?> parent, View view, int position, long id) {
               System.out.println("data at position>>>"+parent.getItemAtPosition(position));
               String selectedDate = new SimpleDateFormat("dd-MM-yyyy").format(parent.getItemAtPosition(position));
               System.out.println("selectedDate>>>"+selectedDate);

               i=0;

               /*
               * MODIFIED BY SANJAY RANGA ON 10/12/2019
               *
               * CHANGED dataFilledStatus FROM ARRYLIST TO MAP TO INCLUDE THE STATUS
               * */
               // IF dataFilledStatus CONTAINS THE DATE CLICKED THAT MEANS IT IS ALREADY FILLED





                   if (dataFilledStatus.containsKey(selectedDate)) {


                       // IF LI MOVEMENT IS COMPLETELY FILLED (DEPARTURE + ARRIVAL )
                       if(dataFilledStatus.get(selectedDate).equals("Y"))
                       {


                               System.out.println("Go To Submit ReportLi Movement>>>");
                               calendarinput.clear();
                               calendarinput.add(loginInfoModel.getLoginid());
                               calendarinput.add(selectedDate);
                               Intent i = new Intent(LImovement.this, SubmitReportLiMovement.class);
                               i.putExtra("date", calendarinput);
                               startActivity(i);


                       }// IF ONLY DEPARTURE IS FILLED THEN FORWARD TO FILL ARRIVAL
                       else
                       {
                           CommonClass.goToNextScreen(LImovement.this, LiArrivalController.class, true, selectedDate);
                       }


                   }// IF NO DATA IS FILLED
                    else {   // THE DATA HAS NOT BE FILEED - TAKE THE USER TO THE DEPARTURE FILL FORM
                       System.out.println("Go To LI_activity_detail_page>>>");
                       /*Intent i = new Intent(LImovement.this, LI_activity_detail_page.class);
                       i.putExtra("frmdate", selectedDate);
                       startActivity(i);*/


                       /* COMMENTED BY SANJAY RANGA ON 03/12/2019
                       *  DIRECTING TO DEPARTURE FORM NOW AS PER THE NEW REQUIREMENTS
                       *
                       *
                       Intent i = new Intent(LImovement.this, LiRecordMovement.class);
                       i.putExtra("frmdate", selectedDate);
                       startActivity(i);
                       */


                       // IF ANY PREVIOUS ARRIVAL IS PENDING THAN USER CANNOT FILL A DEPARTURE
                       if (dataFilledStatus.containsValue("P")) {
                           commonClass.showToast("One arrival is pending");

                       }
                       else   // ELSE GO TO LiDepartureController - PASS FROM DATE AS PARAMETER
                       {
                           CommonClass.goToNextScreen(LImovement.this, LiDepartureController.class, true, selectedDate);
                       }



               }
           }
       });
    }








    @Override
    public void ResponseOk(Object object, int position) {
        if(object instanceof Limovementresponse){


            System.out.println(">>>>>>>>>>>>>>>>>>>> ResponseOk");
            Limovementresponse limovementresponse=(Limovementresponse)object;
            limovstatuslist=new ArrayList <>();
            dataFilledStatus=new HashMap<String,String>();
            i=0;
            while(i<limovementresponse.getLiMovementVOsResponse().size()) {

                System.out.println(">>>> Date" + limovementresponse.getLiMovementVOsResponse().get(i).getDate());

                flagdate=limovementresponse.getLiMovementVOsResponse().get(i).getDate();
                flagstatus=limovementresponse.getLiMovementVOsResponse().get(i).getStatus();

                // INSERT DATE INTO dataFilledStatus WHERE FILLED FLAG == Y || FLAG == P
                if(!flagstatus.equals("N")){
                    if(dataFilledStatus.containsKey(flagdate))
                    {
                        flagstatus= "M";
                        System.out.println(">>>> sETTING" + flagstatus);
                        if(dataFilledStatus.get(flagdate).equals('Y'))
                        {
                            dataFilledStatus.remove(flagdate);
                        }
                    }
                    else
                    {
                        dataFilledStatus.put(flagdate,flagstatus);
                    }
                }
                Limovdraftresponse limovdraftresponse=new Limovdraftresponse();
                limovdraftresponse.setDates(flagdate);
                limovdraftresponse.setStatus(flagstatus);
                System.out.println(">>>> Status" + flagstatus);
                limovstatuslist.add(limovdraftresponse);
                i++;
            }

            for (Map.Entry<String, String> entry : dataFilledStatus.entrySet()) {
                System.out.println("Key : " + entry.getKey() + " ---- Value : " + entry.getValue().toString());
            }
            setUpCalendarAdapter();

            DataHolder.setLimovstatuslist(limovstatuslist);
        }
    }


    // POPULATE THE CALENDAR COMPONENT
    private void setUpCalendarAdapter() {

        List <Date> dayValueInCells = new ArrayList<Date>();

        Calendar mCal = (Calendar)cal.clone();
        mCal.set(Calendar.DAY_OF_MONTH, 1);
        int firstDayOfTheMonth = mCal.get(Calendar.DAY_OF_WEEK) - 1;
        mCal.add(Calendar.DAY_OF_MONTH, -firstDayOfTheMonth);
        while(dayValueInCells.size() < MAX_CALENDAR_COLUMN){
            dayValueInCells.add(mCal.getTime());
            mCal.add(Calendar.DAY_OF_MONTH, 1);
        }
        Log.d("TAG", "Number of date " + dayValueInCells.size());
        String sDate = formatter.format(cal.getTime());
        System.out.println("checkdate"+cal.getTime());
        display_current_date.setText(sDate);


        // SET THE COLORS IN CALANDER
        mAdapter = new Dateadapter(LImovement.this, dayValueInCells, cal);
        gridview.setAdapter(mAdapter);
    }




    private void setPreviousButtonClickEvent(){
        previous_month.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Inside previous month","Month before");
                cal.add(Calendar.MONTH, -1);
                int cuurent= cal.get(Calendar.MONTH)+1;
                monthparam.clear();
                monthparam.add(loginInfoModel.getLoginid());
                monthparam.add( cuurent);
                monthparam.add(cal.get(Calendar.YEAR));
                request.setparamlist(monthparam);
                requestPresenter.Request(request,"Loading Data",Constants.LIMOVEMENT_DETAIL_MONTHLY);

            }
        });
    }
    private void setNextButtonClickEvent(){
        next_month.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Inside next_month","Month after");
                cal.add(Calendar.MONTH, 1);
                int cuurent= cal.get(Calendar.MONTH)+1;

                System.out.println("Next month>>>>>>>>"+cuurent);
                monthparam.clear();

                monthparam.add(loginInfoModel.getLoginid());
                monthparam.add(cuurent);
                request.setparamlist(monthparam);
                monthparam.add(cal.get(Calendar.YEAR));
                requestPresenter.Request(request,"Loading Data",Constants.LIMOVEMENT_DETAIL_MONTHLY);
            }
        });
    }





    private void showLogoutDialog (final Activity context, String msg, final boolean isLogout){
        new AlertDialog.Builder(context).setCancelable(isLogout)
                .setTitle(getResources().getString(R.string.cms))
                .setMessage(msg)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        if (isLogout) {
                            logOut();
                        }
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        Toast.makeText(getApplicationContext(), "Resume your work",
                                Toast.LENGTH_SHORT).show();

                    }
                })
                .setIcon(R.drawable.icon_logo)
                .show();
    }

    private void logOut () {
        //userLoginPreferences.clearUser();
        DataHolder.setLevel(0);
        CommonClass.goToNextScreen(LImovement.this, DetailController.class, true, Constants.LOGIN_OPTIONS);


        Intent landing = new Intent(LImovement.this, LandingActivity.class);
        landing.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                | Intent.FLAG_ACTIVITY_CLEAR_TASK
                | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(landing);
        overridePendingTransition(R.animator.slide_in_right, R.animator.slide_out_left);
        finish();
    }



    @Override
    public void Error() {
        commonClass.showToast("Error in Response");

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




