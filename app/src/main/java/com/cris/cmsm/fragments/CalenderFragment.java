package com.cris.cmsm.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.GridView;
import android.widget.Toast;

import com.cris.cmsm.LI_activity_detail_page;
import com.cris.cmsm.R;
import com.cris.cmsm.adapter.Dateadapter;

import java.util.ArrayList;

public class CalenderFragment extends Fragment {
    GridView gridview;
    ArrayList<Integer> number;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //View view = inflater.inflate(R.layout.calender_layout, container, false);
        //=view.findViewById(R.id.gridview);

        number=new ArrayList <>();
        for(int i=0;i<=31;i++){
            number.add(i);
        }
        //Dateadapter adapter= new Dateadapter(getContext(), number);
        //gridview.setAdapter(adapter);

return null;
    }


}
