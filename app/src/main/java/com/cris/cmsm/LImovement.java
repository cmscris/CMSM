package com.cris.cmsm;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CalendarView;
import android.widget.GridView;

import com.cris.cmsm.adapter.Dateadapter;
import com.cris.cmsm.adapter.LImovementadapter;
import com.cris.cmsm.util.CommonClass;

import java.util.ArrayList;

public class LImovement extends AppCompatActivity {
    //GridView gridview;
   // ArrayList<Integer> number;
    CalendarView cv;
Context ctx;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_limovement);
        cv=(CalendarView)findViewById(R.id.cv);
        cv.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
               int date = (int) cv.getDate();
                CommonClass.goToNextScreen(LImovement.this,LI_activity_detail_page.class,true,date);
            }
        });
       /* cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });*/
//        gridview=(GridView)findViewById(R.id.gridview);

        /*number=new ArrayList <>();
        for(int i=0;i<=31;i++){
            number.add(i);
        }
        Dateadapter adapter= new Dateadapter(getApplicationContext(),number);
        gridview.setAdapter(adapter);*/



    }
}