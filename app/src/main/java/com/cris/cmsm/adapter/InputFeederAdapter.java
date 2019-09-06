package com.cris.cmsm.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cris.cmsm.R;
import com.cris.cmsm.models.request.RequestSSAssets;
import com.cris.cmsm.models.response.InComingFeederVO;

import java.util.List;

public class InputFeederAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int TYPE_ITEM = 1;
    private static final int TYPE_HEADER = 0;
    private static final int TYPE_FOOTER = 2;
    List<InComingFeederVO> list;
    Context context;
    RequestSSAssets requestSSAssets;

    public InputFeederAdapter(Context context, RequestSSAssets requestSSAssets, List<InComingFeederVO> list) {
        this.context = context;
        this.list = list;
        this.requestSSAssets = requestSSAssets;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_ITEM) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.feeder_inflater, parent, false);
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
            InComingFeederVO model = getItem(pos);
            VHItem item = (VHItem) holder;
            if (model != null) {
                item.tv_feeders_name.setText(model.getFeederName());
                item.tv_voltage.setText(model.getVoltageDesc());
                item.tv_type_switch.setText(model.getBreakerDesc());
                item.tv_make.setText(model.getMakeDesc());
                item.tv_rate_in_amp.setText(model.getRating());
                item.tv_break_cap.setText(model.getBreakingCapacity());
                item.tv_year_acqui.setText(model.getCommissionDate());
                item.tv_cost_aqui.setText(model.getAcquisitionCost());
                item.tv_ct_ratio.setText(model.getCtratio());
                if (requestSSAssets.getAssetsName().equalsIgnoreCase("LT PANEL")) {
                    item.linear_feeder.setWeightSum(9);
                    item.view_meter_value.setVisibility(View.GONE);
                    item.tv_meter_available.setVisibility(View.GONE);
                } else if (requestSSAssets.getAssetsName().equalsIgnoreCase("HT PANEL")) {
                    item.linear_feeder.setWeightSum(10);
                    item.view_meter_value.setVisibility(View.VISIBLE);
                    item.tv_meter_available.setVisibility(View.VISIBLE);
                    if (model.getRelayCount() == -100) {
                        item.tv_meter_available.setText("Feeder wise Relay details count");
                    } else
                        item.tv_meter_available.setText("" + model.getRelayCount());
                } else {
                    item.linear_feeder.setWeightSum(10);
                    item.view_meter_value.setVisibility(View.VISIBLE);
                    item.tv_meter_available.setVisibility(View.VISIBLE);
                    item.tv_meter_available.setText(model.getRemarks());
                }
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


    private InComingFeederVO getItem(int position) {
        return list.get(position);
    }

    class VHItem extends RecyclerView.ViewHolder {
        TextView tv_feeders_name, tv_voltage, tv_type_switch, tv_make, tv_rate_in_amp,
                tv_break_cap, tv_year_acqui, tv_cost_aqui, tv_ct_ratio, tv_meter_available;
        LinearLayout linear_feeder;
        View view_meter_value;

        public VHItem(View itemView) {
            super(itemView);
            tv_feeders_name = itemView.findViewById(R.id.tv_feeders_name);
            tv_voltage = itemView.findViewById(R.id.tv_voltage);
            tv_type_switch = itemView.findViewById(R.id.tv_type_switch);
            tv_make = itemView.findViewById(R.id.tv_make);
            tv_rate_in_amp = itemView.findViewById(R.id.tv_rate_in_amp);
            tv_break_cap = itemView.findViewById(R.id.tv_break_cap);
            tv_year_acqui = itemView.findViewById(R.id.tv_year_acqui);
            tv_cost_aqui = itemView.findViewById(R.id.tv_cost_aqui);
            tv_ct_ratio = itemView.findViewById(R.id.tv_ct_ratio);
            tv_meter_available = itemView.findViewById(R.id.tv_meter_available);
            linear_feeder = itemView.findViewById(R.id.linear_feeder);
            view_meter_value = itemView.findViewById(R.id.view_meter_value);
        }
    }


    class VHFooter extends RecyclerView.ViewHolder {
        public VHFooter(View itemView) {
            super(itemView);
        }
    }
}