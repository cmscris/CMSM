package com.cris.cmsm.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.cris.cmsm.R;
import com.cris.cmsm.models.response.TransformersVO;

import java.util.List;

/**
 *
 */

public class TransformerAdapter extends BaseAdapter {
    private List<TransformersVO> list;
    private LayoutInflater inflater;

    public TransformerAdapter(Context context, List<TransformersVO> list) {
        this.list = list;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public TransformersVO getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null)
            convertView = inflater.inflate(R.layout.transformer_item, parent, false);
        TextView tv_transformer_name = convertView.findViewById(R.id.tv_transformer_name);
        TextView tv_transformer_type = convertView.findViewById(R.id.tv_transformer_type);
        TextView tv_transformer_capacity = convertView.findViewById(R.id.tv_transformer_capacity);
        tv_transformer_name.setText(getItem(position).getName());
        tv_transformer_type.setText(getItem(position).getType());
        tv_transformer_capacity.setText(getItem(position).getCapacity());
        return convertView;
    }
}
