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
import com.cris.cmsm.models.response.LoginIfoVO;
import com.cris.cmsm.navcontrollers.DetailController;
import com.cris.cmsm.prefrences.UserLoginPreferences;
import com.cris.cmsm.util.CommonClass;
import com.cris.cmsm.util.Constants;

import java.lang.reflect.Array;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class LImovement extends AppCompatActivity {
    GridView gridview;
    TextView  display_current_date;
    ImageView previous_month,next_month;
    private ImageView iv_title_icon, iv_right,iv_middle;
    Button btn_curr_mn_actvty;
    private SimpleDateFormat formatter = new SimpleDateFormat("MMMM yyyy", Locale.ENGLISH);
    private static final int MAX_CALENDAR_COLUMN = 42;
    private Dateadapter mAdapter;
    ArrayList<String> calendarinput;
    private UserLoginPreferences userLoginPreferences;
    private LoginIfoVO loginInfoModel;
    CommonClass commonClass;
    StringBuilder sb =new StringBuilder();
    private Calendar cal = Calendar.getInstance(Locale.ENGLISH);



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
        loginInfoModel = userLoginPreferences.getLoginUser();
        iv_right.setImageResource(R.drawable.icon_logout);
        iv_right.setVisibility(View.VISIBLE);
        calendarinput=new ArrayList <>();
        setUpCalendarAdapter();
        setPreviousButtonClickEvent();
        setNextButtonClickEvent();
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
               Intent i=new Intent(LImovement.this,LI_activity_detail_page.class);
               startActivity(i);
           }
       });
    }
    private void setPreviousButtonClickEvent(){
        previous_month.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Inside previous month","Month before");
                cal.add(Calendar.MONTH, -1);
                setUpCalendarAdapter();
            }
        });
    }
    private void setNextButtonClickEvent(){
        next_month.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Inside next_month","Month after");
                cal.add(Calendar.MONTH, 1);
                setUpCalendarAdapter();
            }
        });
    }

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
        display_current_date.setText(sDate);
        mAdapter = new Dateadapter(LImovement.this, dayValueInCells, cal);
        gridview.setAdapter(mAdapter);
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


}




