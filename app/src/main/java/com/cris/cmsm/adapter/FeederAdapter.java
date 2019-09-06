package com.cris.cmsm.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.cris.cmsm.R;
import com.cris.cmsm.models.response.SsFeedersVO;

import java.util.List;

/**
 *
 */

public class FeederAdapter extends BaseAdapter {
    private List<SsFeedersVO> list;
    private LayoutInflater inflater;

    public FeederAdapter(Context context, List<SsFeedersVO> list) {
        this.list = list;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public SsFeedersVO getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null)
            convertView = inflater.inflate(R.layout.feeder_item, parent, false);
        TextView tv_feeders_name = convertView.findViewById(R.id.tv_feeders_name);
        TextView tv_connected_load = convertView.findViewById(R.id.tv_connected_load);
        tv_feeders_name.setText(getItem(position).getName());
        tv_connected_load.setText(getItem(position).getConnectedLoad());
        return convertView;
    }
}
