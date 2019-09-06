package com.cris.cmsm;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class abnormality_depart_adapter extends BaseAdapter {
    private Context context;
    private String[]Abnormality={"OHE", "ENGG", "S&T", "C&W", "LOCO", "EMU", "DMU", "SECURITY", "TRAFFIC", "COMMERCIAL"};

    public abnormality_depart_adapter(Context context, String[] Abnormality) {
        this.context = context;
        this.Abnormality = Abnormality;
    }
    @Override
    public int getCount() {
        return Abnormality.length;
    }

    @Override
    public Object getItem(int position) {
        return Abnormality[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView textView;
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;

        if (convertView == null) {

            gridView = new View(context);

            // get layout from mobile.xml
            gridView = inflater.inflate(R.layout.gridviewitemlayoutabnr, null);

            // set value into textview
            textView = (TextView) gridView
                    .findViewById(R.id.item_gridviewtext);
            textView.setText(Abnormality[position]);

            if(Abnormality.length%2==0){
                textView.setBackgroundColor(context.getResources().getColor(R.color.colorWhite));
            }
        } else {
            gridView = (View) convertView;
        }

        return gridView;
    }



}
