package com.cris.cmsm.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.cris.cmsm.R;
import com.cris.cmsm.models.Railway;
import com.cris.cmsm.util.FontFamily;

import java.util.List;

public class RailwayAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private List<Railway> list;
    Typeface font;

    public RailwayAdapter(Context context, List<Railway> list) {
        font = new FontFamily(context).getRegular();
        this.list = list;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Railway getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.spinner_item, viewGroup, false);
        }
        Railway railway = getItem(position);
        TextView tv_titles = convertView.findViewById(R.id.tv_titles);
        tv_titles.setSingleLine(true);
        tv_titles.setText(railway.getFname());
        tv_titles.setTypeface(font);
        return convertView;
    }
}
