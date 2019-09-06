package com.cris.cmsm.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cris.cmsm.R;
import com.cris.cmsm.interfaces.OnItemClickListener;
import com.cris.cmsm.models.ReportHeaderView;
import com.cris.cmsm.models.response.AssestReportSummaryVO;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class AssetsSummaryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;
    private static final int TYPE_FOOTER = 2;
    List<AssestReportSummaryVO> list;
    Context context;
    ReportHeaderView reportHeaderView;
    private String date;
    private OnItemClickListener listener;

    public AssetsSummaryAdapter(Context context, OnItemClickListener listener, ReportHeaderView reportHeaderView, List<AssestReportSummaryVO> list) {
        this.context = context;
        this.reportHeaderView = reportHeaderView;
        this.list = list;
        this.listener = listener;
        date = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Calendar.getInstance().getTime());
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_ITEM) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.mis_list_item, parent, false);
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
            final AssestReportSummaryVO model = getItem(pos);
            VHItem item = (VHItem) holder;
            item.tv_railway.setText(model.getRailCode());
            item.tv_connected_load.setText(model.getDivision());
            item.tv_consumed_energy.setText(model.getUptoCurrentFinYearNO());
            item.tv_bill_paid.setText(model.getInCurrentFinYearNo());
            item.tv_avg_cost.setText(model.getTotal());


            if (pos == 0) {
                item.tv_railway.setTypeface(null, Typeface.BOLD);
                item.tv_connected_load.setTypeface(null, Typeface.BOLD);
                item.tv_consumed_energy.setTypeface(null, Typeface.BOLD);
                item.tv_bill_paid.setTypeface(null, Typeface.BOLD);
                item.tv_avg_cost.setTypeface(null, Typeface.BOLD);
                item.linear_item.setOnClickListener(null);
            } else {
                item.tv_railway.setTypeface(null);
                item.tv_connected_load.setTypeface(null);
                item.tv_consumed_energy.setTypeface(null);
                item.tv_bill_paid.setTypeface(null);
                item.tv_avg_cost.setTypeface(null);
                item.linear_item.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        listener.OnItemClick(model);
                    }
                });
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

    private AssestReportSummaryVO getItem(int position) {
        return list.get(position);
    }

    class VHItem extends RecyclerView.ViewHolder {
        TextView tv_railway, tv_connected_load, tv_consumed_energy, tv_bill_paid, tv_avg_cost;
        LinearLayout linear_item;

        public VHItem(View itemView) {
            super(itemView);
            tv_railway = itemView.findViewById(R.id.tv_railway);
            tv_connected_load = itemView.findViewById(R.id.tv_connected_load);
            tv_consumed_energy = itemView.findViewById(R.id.tv_consumed_energy);
            tv_bill_paid = itemView.findViewById(R.id.tv_bill_paid);
            tv_avg_cost = itemView.findViewById(R.id.tv_avg_cost);
            linear_item = itemView.findViewById(R.id.linear_item);
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