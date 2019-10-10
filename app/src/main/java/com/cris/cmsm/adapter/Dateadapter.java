package com.cris.cmsm.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.cris.cmsm.R;

import java.util.ArrayList;

public class Dateadapter extends BaseAdapter {
    Context context;
    ArrayList <Integer> date;
    LayoutInflater inflater;

    public Dateadapter(Context applicationContext, ArrayList <Integer> dates) {
        this.context = applicationContext;
        this.date = dates;
        inflater = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return date.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.griditemlayout, null); // inflate the layout
        TextView icon = (TextView) view.findViewById(R.id.icon); // get the reference of ImageView
      icon.setText(""+(i+1));
        return view;
    }
}
