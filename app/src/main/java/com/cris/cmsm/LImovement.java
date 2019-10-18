package com.cris.cmsm;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.applandeo.materialcalendarview.EventDay;
import com.applandeo.materialcalendarview.listeners.OnDayClickListener;
import com.cris.cmsm.adapter.Dateadapter;
import com.cris.cmsm.adapter.LImovementadapter;
import com.cris.cmsm.util.CommonClass;

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
    private SimpleDateFormat formatter = new SimpleDateFormat("MMMM yyyy", Locale.ENGLISH);
    private static final int MAX_CALENDAR_COLUMN = 42;
    private Dateadapter mAdapter;

    CommonClass commonClass;
    private Calendar cal = Calendar.getInstance(Locale.ENGLISH);



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_limovement);
        previous_month=(ImageView) findViewById(R.id.previous_month);
        next_month=(ImageView) findViewById(R.id.next_month);
        display_current_date=(TextView)findViewById(R.id.display_current_date);
        gridview = (GridView) findViewById(R.id.calendar_grid);
        setUpCalendarAdapter();
        setPreviousButtonClickEvent();
        setNextButtonClickEvent();

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

}




