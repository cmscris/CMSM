package com.cris.cmsm.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.cris.cmsm.R;
import com.cris.cmsm.models.response.ResponseSSERole;
import com.cris.cmsm.util.FontFamily;

import java.util.List;

public class SSEAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private List<ResponseSSERole> list;
    Typeface font;

    public SSEAdapter(Context context, List<ResponseSSERole> list) {
        font = new FontFamily(context).getRegular();
        this.list = list;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public ResponseSSERole getItem(int i) {
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
        ResponseSSERole railway = getItem(position);
        TextView tv_titles = convertView.findViewById(R.id.tv_titles);
        tv_titles.setText(railway.getName());
        tv_titles.setTypeface(font);
        return convertView;
    }
}
