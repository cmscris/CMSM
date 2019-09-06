package com.cris.cmsm.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cris.cmsm.R;
import com.cris.cmsm.models.response.HtPannelChargerVO;

import java.util.List;

public class ChargingAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int TYPE_ITEM = 1;
    private static final int TYPE_HEADER = 0;
    private static final int TYPE_FOOTER = 2;
    List<HtPannelChargerVO> list;
    Context context;

    public ChargingAdapter(Context context, List<HtPannelChargerVO> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_ITEM) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.charging_inflater, parent, false);
            return new VHItem(view);
        } else if (viewType == TYPE_HEADER) {
            View headerView = LayoutInflater.from(parent.getContext()).inflate(R.layout.feeder_header_view, parent, false);
            return new VHFooter(headerView);
        } else if (viewType == TYPE_FOOTER) {
            View footerView = LayoutInflater.from(parent.getContext()).inflate(R.layout.feeder_footer_view, parent, false);
            return new VHFooter(footerView);
        }
        throw new RuntimeException("there is no type that matches the type " + viewType + " + make sure your using types correctly");
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof VHItem) {
            int pos = position - 1;
            HtPannelChargerVO model = getItem(pos);
            VHItem item = (VHItem) holder;
            if (model != null) {
                item.tv_year_acqui.setText(model.getCommissionDate());
                item.tv_cost_aqui.setText(model.getAcquisitionCost());
                item.tv_make.setText(model.getMakeDesc());
                item.tv_capacity_charger.setText(model.getChargerCapacity());
                item.tv_voltage.setText(model.getVoltageDesc());
                item.tv_cap_battery.setText(model.getBatteryCapacity());
                item.tv_battery_lug.setText(model.getBatteryLugDate());
            }
        }
    }

    @Override
    public int getItemCount() {
        return list.size() + 2;
    }

    @Override
    public int getItemViewType(int position) {
        if (isPositionHeader(position))
            return TYPE_HEADER;

        else if (isPositionFooter(position))
            return TYPE_FOOTER;

        return TYPE_ITEM;
    }


    private boolean isPositionFooter(int position) {
        return position == getItemCount() - 1;
    }

    private boolean isPositionHeader(int position) {
        return position == 0;
    }


    private HtPannelChargerVO getItem(int position) {
        return list.get(position);
    }

    class VHItem extends RecyclerView.ViewHolder {
        TextView tv_year_acqui, tv_cost_aqui, tv_make, tv_capacity_charger, tv_voltage,
                tv_cap_battery, tv_battery_lug;

        public VHItem(View itemView) {
            super(itemView);
            tv_year_acqui = itemView.findViewById(R.id.tv_year_acqui);
            tv_cost_aqui = itemView.findViewById(R.id.tv_cost_aqui);
            tv_make = itemView.findViewById(R.id.tv_make);
            tv_capacity_charger = itemView.findViewById(R.id.tv_capacity_charger);
            tv_voltage = itemView.findViewById(R.id.tv_voltage);
            tv_cap_battery = itemView.findViewById(R.id.tv_cap_battery);
            tv_battery_lug = itemView.findViewById(R.id.tv_battery_lug);
        }
    }


    class VHFooter extends RecyclerView.ViewHolder {
        public VHFooter(View itemView) {
            super(itemView);
        }
    }
}