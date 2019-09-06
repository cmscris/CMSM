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
import com.cris.cmsm.models.response.Rb1vo;
import com.cris.cmsm.util.CommonClass;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class RB1Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;
    private static final int TYPE_FOOTER = 2;
    List<Rb1vo> list;
    Context context;
    ReportHeaderView reportHeaderView;
    private String date;

    public RB1Adapter(Context context, ReportHeaderView reportHeaderView, List<Rb1vo> list) {
        this.context = context;
        this.reportHeaderView = reportHeaderView;
        this.list = list;
        date = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Calendar.getInstance().getTime());
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_ITEM) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rb_mis_list_item, parent, false);
            return new VHItem(view);
        } else if (viewType == TYPE_HEADER) {
            View headerView = LayoutInflater.from(parent.getContext()).inflate(R.layout.rb1_header_view, parent, false);
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
            Rb1vo model = getItem(pos);
            if (model.getRailOrDivCode() != null && model.getRailOrDivCode().equalsIgnoreCase("Z")) {
                try {
                    ((VHItem) holder).tv_rly_div.setText("GRAND TOTAL");
                    ((VHItem) holder).tv_traction.setText(model.getConsumptionTrd());
                    ((VHItem) holder).tv_non_traction.setText(model.getConsumptionGen());
                    ((VHItem) holder).tv_consumed_total.setText(model.getConsumptionTotal());
                    ((VHItem) holder).tv_bill_traction.setText(model.getBillingTrd());
                    ((VHItem) holder).tv_bill_non_traction.setText(model.getBillingGen());
                    ((VHItem) holder).tv_bill_total.setText(model.getBillingTotal());
                    ((VHItem) holder).tv_avg_traction.setText("" + CommonClass.getRoundOff(model.getConsumptionTrd(), model.getBillingTrd()));
                    ((VHItem) holder).tv_avg_nontraction.setText("" + CommonClass.getRoundOff(model.getConsumptionGen(), model.getBillingGen()));
                    ((VHItem) holder).tv_avg_total.setText("" + CommonClass.getRoundOff(model.getConsumptionTotal(), model.getBillingTotal()));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                ((VHItem) holder).tv_rly_div.setText(model.getRailOrDivCode());
                ((VHItem) holder).tv_traction.setText(model.getConsumptionTrd());
                ((VHItem) holder).tv_non_traction.setText(model.getConsumptionGen());
                ((VHItem) holder).tv_consumed_total.setText(model.getConsumptionTotal());
                ((VHItem) holder).tv_bill_traction.setText(model.getBillingTrd());
                ((VHItem) holder).tv_bill_non_traction.setText(model.getBillingGen());
                ((VHItem) holder).tv_bill_total.setText(model.getBillingTotal());
                ((VHItem) holder).tv_avg_traction.setText(model.getAverageTrd());
                ((VHItem) holder).tv_avg_nontraction.setText(model.getAverageGen());
                ((VHItem) holder).tv_avg_total.setText(model.getAverageTotal());
            }

            if (pos == 0) {
                ((VHItem) holder).tv_rly_div.setTypeface(null, Typeface.BOLD);
                ((VHItem) holder).tv_traction.setTypeface(null, Typeface.BOLD);
                ((VHItem) holder).tv_non_traction.setTypeface(null, Typeface.BOLD);
                ((VHItem) holder).tv_consumed_total.setTypeface(null, Typeface.BOLD);
                ((VHItem) holder).tv_bill_traction.setTypeface(null, Typeface.BOLD);
                ((VHItem) holder).tv_bill_non_traction.setTypeface(null, Typeface.BOLD);
                ((VHItem) holder).tv_bill_total.setTypeface(null, Typeface.BOLD);
                ((VHItem) holder).tv_avg_traction.setTypeface(null, Typeface.BOLD);
                ((VHItem) holder).tv_avg_nontraction.setTypeface(null, Typeface.BOLD);
                ((VHItem) holder).tv_avg_total.setTypeface(null, Typeface.BOLD);
            } else {
                ((VHItem) holder).tv_rly_div.setTypeface(null);
                ((VHItem) holder).tv_traction.setTypeface(null);
                ((VHItem) holder).tv_non_traction.setTypeface(null);
                ((VHItem) holder).tv_consumed_total.setTypeface(null);
                ((VHItem) holder).tv_bill_traction.setTypeface(null);
                ((VHItem) holder).tv_bill_non_traction.setTypeface(null);
                ((VHItem) holder).tv_bill_total.setTypeface(null);
                ((VHItem) holder).tv_avg_traction.setTypeface(null);
                ((VHItem) holder).tv_avg_nontraction.setTypeface(null);
                ((VHItem) holder).tv_avg_total.setTypeface(null);
            }
        } else if (holder instanceof VHHeader) {
            ((VHHeader) holder).tv_railway.setText(reportHeaderView.getRailway());
            ((VHHeader) holder).tv_zon_div.setText(reportHeaderView.getZon_div());
            ((VHHeader) holder).tv_month.setText(reportHeaderView.getMonth());
            ((VHHeader) holder).tv_energy_consume.setText(reportHeaderView.getEnergyConsume());

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

    private Rb1vo getItem(int position) {
        return list.get(position);
    }

    class VHItem extends RecyclerView.ViewHolder {
        TextView tv_rly_div, tv_traction, tv_non_traction, tv_consumed_total, tv_bill_traction, tv_bill_non_traction, tv_bill_total, tv_avg_traction, tv_avg_nontraction, tv_avg_total;

        public VHItem(View itemView) {
            super(itemView);
            tv_rly_div = itemView.findViewById(R.id.tv_rly_div);
            tv_traction = itemView.findViewById(R.id.tv_traction);
            tv_non_traction = itemView.findViewById(R.id.tv_non_traction);
            tv_consumed_total = itemView.findViewById(R.id.tv_consumed_total);
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
        TextView tv_date;

        public VHFooter(View itemView) {
            super(itemView);
            linear_footer = itemView.findViewById(R.id.linear_footer);
            tv_date = itemView.findViewById(R.id.tv_date);
        }
    }
}