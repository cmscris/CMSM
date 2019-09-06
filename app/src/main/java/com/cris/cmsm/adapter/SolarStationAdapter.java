package com.cris.cmsm.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cris.cmsm.R;
import com.cris.cmsm.models.ReportHeaderView;
import com.cris.cmsm.models.response.SolarPVPanelStationAssetVO;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class SolarStationAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;
    private static final int TYPE_FOOTER = 2;
    List<SolarPVPanelStationAssetVO> list;
    Context context;
    ReportHeaderView reportHeaderView;
    private String date;

    public SolarStationAdapter(Context context, ReportHeaderView reportHeaderView, List<SolarPVPanelStationAssetVO> list) {
        this.context = context;
        this.reportHeaderView = reportHeaderView;
        this.list = list;
        date = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Calendar.getInstance().getTime());
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_ITEM) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.solar_station_inflater, parent, false);
            return new VHItem(view);
        } else if (viewType == TYPE_HEADER) {
            View headerView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_header_view, parent, false);
            return new VHHeader(headerView);
        } else if (viewType == TYPE_FOOTER) {
            View footerView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_footer_view, parent, false);
            return new VHFooter(footerView);
        }

        throw new RuntimeException("there is no type that matches the type " + viewType + " + make sure your using types correctly");
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof VHItem) {
            int pos = position - 1;
            final SolarPVPanelStationAssetVO model = getItem(pos);
            VHItem item = (VHItem) holder;
            item.tv_name_station.setText(model.getNameOfStation());
            item.tv_connected_load.setText(model.getConnectedLoad());
            item.tv_capacity_solar.setText(model.getCapacity());
            item.tv_type_of_load.setText(model.getTypeOfLoad());
            item.tv_year_of_commissioning.setText(model.getYearOfCommissioning());
            item.tv_mode_maintenance.setText(model.getModeOfMaintenance());
            item.tv_stand_by.setText(model.getStandBy());
            if (pos > 0) {
                if (getItem(pos - 1).getRlyCode().equalsIgnoreCase(model.getRlyCode())) {
                    item.tv_railway.setText("");
                } else {
                    item.tv_railway.setText(model.getRlyCode());
                }

                if (getItem(pos - 1).getDivison().equalsIgnoreCase(model.getDivison())) {
                    item.tv_division.setText("");
                } else {
                    item.tv_division.setText(model.getDivison());
                }

                if (getItem(pos - 1).getState().equalsIgnoreCase(model.getState())) {
                    item.tv_state.setText("");
                } else {
                    item.tv_state.setText(model.getState());
                }
            } else {
                item.tv_railway.setText(model.getRlyCode());
                item.tv_division.setText(model.getDivison());
                item.tv_state.setText(model.getState());
            }
        } else if (holder instanceof VHHeader) {
            ((VHHeader) holder).tv_railway.setText(reportHeaderView.getRailway());
            ((VHHeader) holder).tv_zon_div.setText(reportHeaderView.getZon_div());
            ((VHHeader) holder).tv_month.setText(reportHeaderView.getMonth());
            ((VHHeader) holder).tv_energy_consume.setText(reportHeaderView.getEnergyConsume());

            if (!TextUtils.isEmpty(reportHeaderView.getMsg())) {
                ((VHHeader) holder).tv_detail_msg.setVisibility(View.VISIBLE);
                ((VHHeader) holder).tv_detail_msg.setText(reportHeaderView.getMsg());
            } else {
                ((VHHeader) holder).tv_detail_msg.setVisibility(View.GONE);
                ((VHHeader) holder).tv_detail_msg.setText("");
            }
        } else if (holder instanceof VHFooter) {
            ((VHFooter) holder).tv_support.setText(reportHeaderView.getSummary());
            ((VHFooter) holder).tv_date.setText("\n\n\n As On : " + date);
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

    private SolarPVPanelStationAssetVO getItem(int position) {
        return list.get(position);
    }

    class VHItem extends RecyclerView.ViewHolder {
        TextView tv_railway, tv_division, tv_state, tv_name_station, tv_connected_load, tv_capacity_solar, tv_type_of_load,
                tv_year_of_commissioning, tv_mode_maintenance, tv_stand_by;

        public VHItem(View itemView) {
            super(itemView);
            tv_railway = itemView.findViewById(R.id.tv_railway);
            tv_division = itemView.findViewById(R.id.tv_division);
            tv_state = itemView.findViewById(R.id.tv_state);
            tv_name_station = itemView.findViewById(R.id.tv_name_station);
            tv_connected_load = itemView.findViewById(R.id.tv_connected_load);
            tv_capacity_solar = itemView.findViewById(R.id.tv_capacity_solar);
            tv_type_of_load = itemView.findViewById(R.id.tv_type_of_load);
            tv_year_of_commissioning = itemView.findViewById(R.id.tv_year_of_commissioning);
            tv_mode_maintenance = itemView.findViewById(R.id.tv_mode_maintenance);
            tv_stand_by = itemView.findViewById(R.id.tv_stand_by);
        }
    }

    class VHHeader extends RecyclerView.ViewHolder {
        TextView tv_railway, tv_month, tv_zon_div, tv_energy_consume, tv_detail_msg;

        public VHHeader(View itemView) {
            super(itemView);
            tv_railway = itemView.findViewById(R.id.tv_railway);
            tv_month = itemView.findViewById(R.id.tv_month);
            tv_zon_div = itemView.findViewById(R.id.tv_zon_div);
            tv_energy_consume = itemView.findViewById(R.id.tv_energy_consume);
            tv_detail_msg = itemView.findViewById(R.id.tv_detail_msg);
        }
    }


    class VHFooter extends RecyclerView.ViewHolder {
        LinearLayout linear_footer;
        TextView tv_date, tv_support;

        public VHFooter(View itemView) {
            super(itemView);
            linear_footer = itemView.findViewById(R.id.linear_footer);
            tv_support = itemView.findViewById(R.id.tv_support);
            tv_date = itemView.findViewById(R.id.tv_date);
        }
    }
}