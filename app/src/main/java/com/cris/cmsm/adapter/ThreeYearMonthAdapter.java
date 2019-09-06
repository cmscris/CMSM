package com.cris.cmsm.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.cris.cmsm.R;
import com.cris.cmsm.interfaces.ICheckBoxClick;
import com.cris.cmsm.models.Month;
import com.cris.cmsm.util.FontFamily;

import java.util.List;

public class ThreeYearMonthAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private List<Month> list;
    Typeface font;
    private ICheckBoxClick icheckBoxClick;

    public ThreeYearMonthAdapter(Context context, ICheckBoxClick icheckBoxClick, List<Month> list) {
        font = new FontFamily(context).getRegular();
        this.list = list;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.icheckBoxClick = icheckBoxClick;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Month getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.month_spinner_item, viewGroup, false);
        }
        final Month month = getItem(position);
        TextView tv_titles = convertView.findViewById(R.id.tv_titles);
        CheckBox checkbox = convertView.findViewById(R.id.checkbox);
        tv_titles.setText(month.getMonthName());
        tv_titles.setTypeface(font);
        checkbox.setChecked(month.isCheck());
        checkbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (month.isCheck())
                    month.setCheck(false);
                else
                    month.setCheck(true);
                icheckBoxClick.checkBoxClick();
            }
        });
        return convertView;
    }
}


