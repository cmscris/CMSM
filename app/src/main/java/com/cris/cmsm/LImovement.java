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
    TextView  display_current_date,icon;
    ImageView previous_month,next_month;
    ArrayList <Integer> number;
    DateFormat dateFormat;
    String monthname="";
    private SimpleDateFormat formatter = new SimpleDateFormat("MMMM yyyy", Locale.ENGLISH);
    private static final int MAX_CALENDAR_COLUMN = 42;
    Date date;
    private Context context;
    private Dateadapter mAdapter;
    public static String[] days;
    public static int[] months = {31, 0, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    int today, beginOfMonth;
    String month, year;
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
       /* dateFormat = new SimpleDateFormat("yyyy");
        date = new Date();
        months[1] = Feb(Integer.parseInt(dateFormat.format(date))); // Find the amount of days in Feb
        dateFormat = new SimpleDateFormat("MM");
        int numDays = months[Integer.parseInt(dateFormat.format(date))-1] + 6; // Number of days in the month as well as making sure not to override the day names
        // Check which day of the month the month started on. Eg: April 1st 2016 is a Friday
        dateFormat = new SimpleDateFormat("MM");
        month = dateFormat.format(date);
        dateFormat = new SimpleDateFormat("yyyy");
        year = dateFormat.format(date);
        try {
            beginOfMonth = (Day("01"+month+year))-1; // Get the beginning of the month (-1 because Android recognizes Sunday as the first day)
        } catch (ParseException pe) {
            Toast.makeText(getApplicationContext(), pe.getMessage(), Toast.LENGTH_LONG).show();
        }
        if (beginOfMonth == 0) {
            beginOfMonth = 7;
        }
        days = new String[numDays+beginOfMonth];
        days[0] = "Mon";
        days[1] = "Tue";
        days[2] = "Wed";
        days[3] = "Thu";
        days[4] = "Fri";
        days[5] = "Sat";
        days[6] = "Sun";
        dateFormat = new SimpleDateFormat("dd");
        String temp = dateFormat.format(date);
        today = Integer.parseInt(temp);

        if(beginOfMonth != 0) {
            for (int i = 7; i <= (5 + beginOfMonth); i++) {
                days[i] = "";
            }
        }
        for (int i = (6 + beginOfMonth); i <= (days.length-1); i++) {
            days[i] = Integer.toString(i-beginOfMonth-5);
        }
        if(month.equals("1")){
            monthname="January";
        }
        else if(month.equals("2")){
            monthname="February";
        }
        else if(month.equals("3")){
            monthname="March";
        }
        else if(month.equals("4")){
            monthname="April";
        }
        else if(month.equals("5")){
            monthname="May";
        }
        else if(month.equals("6")){
            monthname="June";
        }
        else if(month.equals("7")){
            monthname="July";
        }
        else if(month.equals("8")){
            monthname="August";
        }
        else if(month.equals("9")){
            monthname="September";
        }
        else if(month.equals("10")){
            monthname="October";
        } else if(month.equals("11")){
            monthname="November";
        }
        else if(month.equals("12")){
            monthname="December";
        }
        Dateadapter adapter=new Dateadapter(this,days);
        gridview.setAdapter(adapter);
        display_current_date.setText(monthname+" "+year);


    }


    public int Feb(int year) {
        int temp;
        try {
            temp = year / 4;
        } catch (Exception e) {
            return 28;
        }
        return 29;
    }

    public int Day(String day) throws ParseException {
        DateFormat df = new SimpleDateFormat("ddMMyyyy");
        try {
            Date d = df.parse(String.valueOf(day));
            Calendar c = Calendar.getInstance();
            c.setTime(d);
            return c.get(Calendar.DAY_OF_WEEK);
        } catch (Exception e) {
            ParseException pe = new ParseException("There was a problem getting the date.", 0);
            throw pe;
        }
    }*/



}




