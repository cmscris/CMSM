package com.cris.cmsm.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.cris.cmsm.R;
import com.cris.cmsm.models.ReportTitleModel;
import com.cris.cmsm.util.FontFamily;

import java.util.List;

/**
 *
 */
public class MISAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater inflater;
    private List<ReportTitleModel> titles;
    private Typeface font;

    public MISAdapter(Context context, List<ReportTitleModel> titles) {
        this.context = context;
        this.titles = titles;
        font = new FontFamily(context).getRegular();
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return titles.size();
    }

    @Override
    public ReportTitleModel getItem(int position) {
        return titles.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null)
            convertView = inflater.inflate(R.layout.mis_collection_item, parent, false);
        TextView tv_annexure_title = convertView.findViewById(R.id.tv_annexure_title);
        tv_annexure_title.setText(getItem(position).getTitle());
        tv_annexure_title.setTypeface(font);
        TextView tv_annexure_subheading = convertView.findViewById(R.id.tv_annexure_subheading);
        if (getItem(position).getSubheading().isEmpty()) {
            tv_annexure_subheading.setVisibility(View.GONE);
        } else {
            tv_annexure_subheading.setVisibility(View.VISIBLE);
            tv_annexure_subheading.setText(getItem(position).getSubheading());
        }
        tv_annexure_subheading.setTypeface(font);
        return convertView;
    }
}
