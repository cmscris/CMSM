package com.cris.cmsm.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import com.cris.cmsm.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Dateadapter extends ArrayAdapter {
    Context context;

    private static final String TAG = Dateadapter.class.getSimpleName();
    private LayoutInflater mInflater;
    private List<Date> monthlyDates;
    private Calendar currentDate;


    public Dateadapter(Context context, List<Date> monthlyDates, Calendar currentDate) {
        super(context, R.layout.griditemlayout);
        this.monthlyDates=monthlyDates;
        this.currentDate=currentDate;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return monthlyDates.size();
    }

    @Override
    public Object getItem(int position) {
        return monthlyDates.get(position);
    }

    @Override
    public int getPosition(Object item) {
        return monthlyDates.indexOf(item);
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        Date mDate = monthlyDates.get(i);
        Calendar dateCal = Calendar.getInstance();
        dateCal.setTime(mDate);
        int dayValue = dateCal.get(Calendar.DAY_OF_MONTH);
        int displayMonth = dateCal.get(Calendar.MONTH) + 1;
        int displayYear = dateCal.get(Calendar.YEAR);
        int currentMonth = currentDate.get(Calendar.MONTH) + 1;
        int currentYear = currentDate.get(Calendar.YEAR);
        View view = convertView;
        if(view == null){
            view = mInflater.inflate(R.layout.griditemlayout, parent, false);
        }
        if(displayMonth == currentMonth && displayYear == currentYear){
            view.setBackgroundColor(Color.parseColor("#00B8D4"));
        }else{
            view.setBackgroundColor(Color.parseColor("#E0E0E0"));
        }
        //Add day to calendar
        TextView cellNumber = (TextView)view.findViewById(R.id.icon);
        cellNumber.setText(String.valueOf(dayValue));
        return view;
    }
}
