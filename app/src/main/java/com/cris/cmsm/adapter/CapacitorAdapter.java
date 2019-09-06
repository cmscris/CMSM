package com.cris.cmsm.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.cris.cmsm.R;
import com.cris.cmsm.models.response.Cbvo;

import java.util.List;

/**
 *
 */

public class CapacitorAdapter extends BaseAdapter {
    private List<Cbvo> list;
    private LayoutInflater inflater;

    public CapacitorAdapter(Context context, List<Cbvo> list) {
        this.list = list;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Cbvo getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null)
            convertView = inflater.inflate(R.layout.capacitor_item, parent, false);
        TextView tv_cbname = convertView.findViewById(R.id.tv_cbname);
        TextView tv_type = convertView.findViewById(R.id.tv_type);
        TextView tv_capacity = convertView.findViewById(R.id.tv_capacity);
        TextView tv_act_capacity = convertView.findViewById(R.id.tv_act_capacity);
        TextView tv_date = convertView.findViewById(R.id.tv_date);
        TextView tv_supplier = convertView.findViewById(R.id.tv_supplier);
        TextView tv_init_cost = convertView.findViewById(R.id.tv_init_cost);
        TextView tv_pf_before_commission = convertView.findViewById(R.id.tv_pf_before_commission);
        Cbvo model = getItem(position);
        tv_cbname.setText(model.getName());
        tv_type.setText(model.getType());
        tv_capacity.setText(model.getCapacity());
        tv_act_capacity.setText(model.getActualCapacity());
        tv_date.setText(model.getCommissiondate());
        tv_supplier.setText(model.getSupplier());
        tv_init_cost.setText(model.getInitcost());
        tv_pf_before_commission.setText(model.getPfold());
        return convertView;
    }
}
