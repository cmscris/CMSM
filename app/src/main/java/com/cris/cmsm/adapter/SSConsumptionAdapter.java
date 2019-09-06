package com.cris.cmsm.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cris.cmsm.R;
import com.cris.cmsm.interfaces.OnItemClickListener;
import com.cris.cmsm.models.ReportHeaderView;
import com.cris.cmsm.models.response.SubstationResponseVO;

import java.util.List;

/**
 *
 */

public class SSConsumptionAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;
    private static final int TYPE_FOOTER = 2;
    private Context context;
    private List<SubstationResponseVO> list;
    private OnItemClickListener listener;
    private ReportHeaderView reportHeaderView;

    public SSConsumptionAdapter(Context context, ReportHeaderView reportHeaderView, OnItemClickListener listener, List<SubstationResponseVO> list) {
        this.context = context;
        this.list = list;
        this.reportHeaderView = reportHeaderView;
        this.listener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_ITEM) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sub_station, parent, false);
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
            VHItem item = (VHItem) holder;
            int pos = position - 1;
            final SubstationResponseVO model = getItem(pos);
            item.tv_rly.setText(model.getRlycode());
            item.tv_div.setText(model.getDivcode());
            item.tv_seb.setText(model.getSname());
            item.tv_sub_station.setText(model.getAssetname());
            item.tv_iv.setText(model.getIvolt());
            item.tv_connected_load.setText(model.getLoad());
            item.tv_contract_demand.setText(model.getCtdemand());
            item.tv_state.setText(model.getStatecode());
            item.tv_role.setText(model.getRoleName());
            if (model.getDetails() != null) {
                item.tv_details.setText(model.getDetails());
                item.tv_details.setTextColor(context.getResources().getColor(R.color.colorDarkBlack));
                item.tv_details.setBackgroundColor(context.getResources().getColor(R.color.colorWhite));
                item.tv_details.setTypeface(null);
                item.tv_details.setOnClickListener(null);
            } else {
                item.tv_details.setText("Details");
                item.tv_details.setTextColor(context.getResources().getColor(R.color.colorWhite));
                item.tv_details.setBackgroundColor(context.getResources().getColor(R.color.colorBlueText));
                item.tv_details.setTypeface(item.tv_details.getTypeface(), Typeface.BOLD);
                item.tv_details.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        listener.OnItemClick(model);
                    }
                });
            }

            if (pos == 0) {
                item.tv_rly.setTypeface(null, Typeface.BOLD);
                item.tv_div.setTypeface(null, Typeface.BOLD);
                item.tv_seb.setTypeface(null, Typeface.BOLD);
                item.tv_sub_station.setTypeface(null, Typeface.BOLD);
                item.tv_iv.setTypeface(null, Typeface.BOLD);
                item.tv_connected_load.setTypeface(null, Typeface.BOLD);
                item.tv_contract_demand.setTypeface(null, Typeface.BOLD);
                item.tv_state.setTypeface(null, Typeface.BOLD);
                item.tv_role.setTypeface(null, Typeface.BOLD);
                item.tv_details.setTypeface(null, Typeface.BOLD);

            } else {
                item.tv_rly.setTypeface(null);
                item.tv_div.setTypeface(null);
                item.tv_seb.setTypeface(null);
                item.tv_sub_station.setTypeface(null);
                item.tv_iv.setTypeface(null);
                item.tv_connected_load.setTypeface(null);
                item.tv_contract_demand.setTypeface(null);
                item.tv_state.setTypeface(null);
                item.tv_role.setTypeface(null);
                item.tv_details.setTypeface(null);
            }
        } else if (holder instanceof VHHeader) {
            ((VHHeader) holder).tv_railway.setText(reportHeaderView.getEnergyConsume());
            ((VHHeader) holder).tv_zon_div.setVisibility(View.GONE);
            ((VHHeader) holder).tv_month.setVisibility(View.GONE);
            ((VHHeader) holder).tv_energy_consume.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return list.size() + 2;
    }

    class VHItem extends RecyclerView.ViewHolder {
        TextView tv_rly, tv_div, tv_sub_station, tv_iv, tv_connected_load, tv_contract_demand, tv_seb, tv_state, tv_role, tv_details;

        public VHItem(View itemView) {
            super(itemView);
            tv_rly = itemView.findViewById(R.id.tv_rly);
            tv_div = itemView.findViewById(R.id.tv_div);
            tv_sub_station = itemView.findViewById(R.id.tv_sub_station);
            tv_iv = itemView.findViewById(R.id.tv_iv);
            tv_connected_load = itemView.findViewById(R.id.tv_connected_load);
            tv_contract_demand = itemView.findViewById(R.id.tv_contract_demand);
            tv_seb = itemView.findViewById(R.id.tv_seb);
            tv_state = itemView.findViewById(R.id.tv_state);
            tv_role = itemView.findViewById(R.id.tv_role);
            tv_details = itemView.findViewById(R.id.tv_details);
        }
    }

    private SubstationResponseVO getItem(int position) {
        return list.get(position);
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


    class VHHeader extends RecyclerView.ViewHolder {
        TextView tv_railway, tv_month, tv_zon_div, tv_energy_consume;

        public VHHeader(View itemView) {
            super(itemView);
            tv_railway = itemView.findViewById(R.id.tv_railway);
            tv_month = itemView.findViewById(R.id.tv_month);
            tv_zon_div = itemView.findViewById(R.id.tv_zon_div);
            tv_energy_consume = itemView.findViewById(R.id.tv_energy_consume);
        }
    }


    class VHFooter extends RecyclerView.ViewHolder {
        LinearLayout linear_footer;
        TextView tv_support, tv_date;

        public VHFooter(View itemView) {
            super(itemView);
            linear_footer = itemView.findViewById(R.id.linear_footer);
            tv_support = itemView.findViewById(R.id.tv_support);
            tv_date = itemView.findViewById(R.id.tv_date);
        }
    }

}
