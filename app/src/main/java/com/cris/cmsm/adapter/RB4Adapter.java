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
import com.cris.cmsm.models.ReportHeaderView;
import com.cris.cmsm.models.response.AnnexureRB4vo;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class RB4Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;
    private static final int TYPE_FOOTER = 2;
    List<AnnexureRB4vo> list;
    Context context;
    ReportHeaderView reportHeaderView;
    private String date;

    public RB4Adapter(Context context, ReportHeaderView reportHeaderView, List<AnnexureRB4vo> list) {
        this.context = context;
        this.reportHeaderView = reportHeaderView;
        this.list = list;
        date = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Calendar.getInstance().getTime());
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_ITEM) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rb_mis_list_four, parent, false);
            return new VHItem(view);
        } else if (viewType == TYPE_HEADER) {
            View headerView = LayoutInflater.from(parent.getContext()).inflate(R.layout.rb1_header_four, parent, false);
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
            AnnexureRB4vo model = getItem(pos);
            if (model.getSebs() != null) {
                try {
                    ((VHItem) holder).tv_sebname.setText(model.getSebs());
                    ((VHItem) holder).tv_bill_traction.setText(model.getConsumption1());
                    ((VHItem) holder).tv_bill_non_traction.setText(model.getBilling1());
                    ((VHItem) holder).tv_bill_total.setText(model.getAverage1());
                    ((VHItem) holder).tv_avg_traction.setText(model.getConsumption());
                    ((VHItem) holder).tv_avg_nontraction.setText(model.getBilling());
                    ((VHItem) holder).tv_avg_total.setText(model.getAverage());
                    if (pos == 0) {
                        ((VHItem) holder).tv_sebname.setTypeface(null, Typeface.BOLD);
                        ((VHItem) holder).tv_bill_traction.setTypeface(null, Typeface.BOLD);
                        ((VHItem) holder).tv_bill_non_traction.setTypeface(null, Typeface.BOLD);
                        ((VHItem) holder).tv_bill_total.setTypeface(null, Typeface.BOLD);
                        ((VHItem) holder).tv_avg_traction.setTypeface(null, Typeface.BOLD);
                        ((VHItem) holder).tv_avg_nontraction.setTypeface(null, Typeface.BOLD);
                        ((VHItem) holder).tv_avg_total.setTypeface(null, Typeface.BOLD);
                    } else {
                        ((VHItem) holder).tv_sebname.setTypeface(null);
                        ((VHItem) holder).tv_bill_traction.setTypeface(null);
                        ((VHItem) holder).tv_bill_non_traction.setTypeface(null);
                        ((VHItem) holder).tv_bill_total.setTypeface(null);
                        ((VHItem) holder).tv_avg_traction.setTypeface(null);
                        ((VHItem) holder).tv_avg_nontraction.setTypeface(null);
                        ((VHItem) holder).tv_avg_total.setTypeface(null);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else if (holder instanceof VHHeader) {
            ((VHHeader) holder).tv_railway.setText(reportHeaderView.getRailway());
            ((VHHeader) holder).tv_zon_div.setText(reportHeaderView.getZon_div());
            ((VHHeader) holder).tv_month.setText(reportHeaderView.getMonth());
            ((VHHeader) holder).tv_energy_consume.setText(reportHeaderView.getEnergyConsume());
            ((VHHeader) holder).tv_total_consump.setText("");
            ((VHHeader) holder).tv_bill_paid.setText(reportHeaderView.getTitleTwo());
            ((VHHeader) holder).tv_avg_cost.setText(reportHeaderView.getTitleOne());

        } else if (holder instanceof VHFooter) {
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

    private AnnexureRB4vo getItem(int position) {
        return list.get(position);
    }

    class VHItem extends RecyclerView.ViewHolder {
        TextView tv_sebname, tv_bill_traction, tv_bill_non_traction, tv_bill_total, tv_avg_traction, tv_avg_nontraction, tv_avg_total;

        public VHItem(View itemView) {
            super(itemView);
            tv_sebname = itemView.findViewById(R.id.tv_sebname);
            tv_bill_traction = itemView.findViewById(R.id.tv_bill_traction);
            tv_bill_non_traction = itemView.findViewById(R.id.tv_bill_non_traction);
            tv_bill_total = itemView.findViewById(R.id.tv_bill_total);
            tv_avg_traction = itemView.findViewById(R.id.tv_avg_traction);
            tv_avg_nontraction = itemView.findViewById(R.id.tv_avg_nontraction);
            tv_avg_total = itemView.findViewById(R.id.tv_avg_total);
        }
    }

    class VHHeader extends RecyclerView.ViewHolder {
        TextView tv_railway, tv_month, tv_zon_div, tv_energy_consume;
        TextView tv_total_consump, tv_bill_paid, tv_avg_cost;

        public VHHeader(View itemView) {
            super(itemView);
            tv_railway = itemView.findViewById(R.id.tv_railway);
            tv_month = itemView.findViewById(R.id.tv_month);
            tv_zon_div = itemView.findViewById(R.id.tv_zon_div);
            tv_energy_consume = itemView.findViewById(R.id.tv_energy_consume);
            tv_total_consump = itemView.findViewById(R.id.tv_total_consump);
            tv_bill_paid = itemView.findViewById(R.id.tv_bill_paid);
            tv_avg_cost = itemView.findViewById(R.id.tv_avg_cost);
        }
    }


    class VHFooter extends RecyclerView.ViewHolder {
        LinearLayout linear_footer;
        TextView tv_date;

        public VHFooter(View itemView) {
            super(itemView);
            linear_footer = itemView.findViewById(R.id.linear_footer);
            tv_date = itemView.findViewById(R.id.tv_date);
        }
    }
}