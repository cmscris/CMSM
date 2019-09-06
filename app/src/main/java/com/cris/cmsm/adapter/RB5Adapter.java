package com.cris.cmsm.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cris.cmsm.R;
import com.cris.cmsm.models.ReportHeaderView;
import com.cris.cmsm.models.response.AnnexureRB5vo;
import com.cris.cmsm.util.CommonClass;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class RB5Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;
    private static final int TYPE_FOOTER = 2;
    List<AnnexureRB5vo> list;
    Context context;
    ReportHeaderView reportHeaderView;
    private String date;

    public RB5Adapter(Context context, ReportHeaderView reportHeaderView, List<AnnexureRB5vo> list) {
        this.context = context;
        this.reportHeaderView = reportHeaderView;
        this.list = list;
        date = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Calendar.getInstance().getTime());
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_ITEM) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rb_mis_list_five, parent, false);
            return new VHItem(view);
        } else if (viewType == TYPE_HEADER) {
            View headerView = LayoutInflater.from(parent.getContext()).inflate(R.layout.rb1_header_five, parent, false);
            return new VHHeader(headerView);
        } else if (viewType == TYPE_FOOTER) {
            View footerView = LayoutInflater.from(parent.getContext()).inflate(R.layout.footer_five, parent, false);
            return new VHFooter(footerView);
        }

        throw new RuntimeException("there is no type that matches the type " + viewType + " + make sure your using types correctly");
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof VHItem) {
            int pos = position - 1;
            AnnexureRB5vo model = getItem(pos);
            if (model != null) {
                try {
                    ((VHItem) holder).tv_rly_div.setText(model.getRlycode());
                    ((VHItem) holder).tv_ec1.setText(model.getEngconsumed4());
                    ((VHItem) holder).tv_ec2.setText(model.getEngconsumed3());
                    ((VHItem) holder).tv_ec3.setText(model.getEngconsumed2());
                    ((VHItem) holder).tv_ec4.setText(model.getEngconsumed1());
                    ((VHItem) holder).tv_ec5.setText(model.getEngconsumed());
                    ((VHItem) holder).tv_eb1.setText(model.getBillpaid4());
                    ((VHItem) holder).tv_eb2.setText(model.getBillpaid3());
                    ((VHItem) holder).tv_eb3.setText(model.getBillpaid2());
                    ((VHItem) holder).tv_eb4.setText(model.getBillpaid1());
                    ((VHItem) holder).tv_eb5.setText(model.getBillpaid());
                    ((VHItem) holder).tv_avg_total.setText(model.getAvgcost());

                    if (pos == 0) {
                        ((VHItem) holder).tv_rly_div.setTypeface(null, Typeface.BOLD);
                        ((VHItem) holder).tv_ec1.setTypeface(null, Typeface.BOLD);
                        ((VHItem) holder).tv_ec2.setTypeface(null, Typeface.BOLD);
                        ((VHItem) holder).tv_ec3.setTypeface(null, Typeface.BOLD);
                        ((VHItem) holder).tv_ec4.setTypeface(null, Typeface.BOLD);
                        ((VHItem) holder).tv_ec5.setTypeface(null, Typeface.BOLD);
                        ((VHItem) holder).tv_eb1.setTypeface(null, Typeface.BOLD);
                        ((VHItem) holder).tv_eb2.setTypeface(null, Typeface.BOLD);
                        ((VHItem) holder).tv_eb3.setTypeface(null, Typeface.BOLD);
                        ((VHItem) holder).tv_eb4.setTypeface(null, Typeface.BOLD);
                        ((VHItem) holder).tv_eb5.setTypeface(null, Typeface.BOLD);
                        ((VHItem) holder).tv_avg_total.setTypeface(null, Typeface.BOLD);
                    } else {
                        ((VHItem) holder).tv_rly_div.setTypeface(null);
                        ((VHItem) holder).tv_ec1.setTypeface(null);
                        ((VHItem) holder).tv_ec2.setTypeface(null);
                        ((VHItem) holder).tv_ec3.setTypeface(null);
                        ((VHItem) holder).tv_ec4.setTypeface(null);
                        ((VHItem) holder).tv_ec5.setTypeface(null);
                        ((VHItem) holder).tv_eb1.setTypeface(null);
                        ((VHItem) holder).tv_eb2.setTypeface(null);
                        ((VHItem) holder).tv_eb3.setTypeface(null);
                        ((VHItem) holder).tv_eb4.setTypeface(null);
                        ((VHItem) holder).tv_eb5.setTypeface(null);
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
            ((VHHeader) holder).tv_year5.setText(reportHeaderView.getTitleFive());
            ((VHHeader) holder).tv_year4.setText(reportHeaderView.getTitleFour());
            ((VHHeader) holder).tv_year3.setText(reportHeaderView.getTitleThree());
            ((VHHeader) holder).tv_year2.setText(reportHeaderView.getTitleTwo());
            ((VHHeader) holder).tv_year1.setText(reportHeaderView.getTitleOne());
        } else if (holder instanceof VHFooter) {
            if (list != null && list.size() > 0) {
                AnnexureRB5vo footer = list.get(list.size() - 1);
                if (footer != null) {
                    ((VHFooter) holder).tv_avg_cost.setText("Average Cost");
                    ((VHFooter) holder).tv_year5.setText("" +
                            CommonClass.getRoundOff(footer.getEngconsumed4(), footer.getBillpaid4()));
                    ((VHFooter) holder).tv_year4.setText("" +
                            CommonClass.getRoundOff(footer.getEngconsumed3(), footer.getBillpaid3()));
                    ((VHFooter) holder).tv_year3.setText("" +
                            CommonClass.getRoundOff(footer.getEngconsumed2(), footer.getBillpaid2()));
                    ((VHFooter) holder).tv_year2.setText("" +
                            CommonClass.getRoundOff(footer.getEngconsumed1(), footer.getBillpaid1()));
                    ((VHFooter) holder).tv_year1.setText("" +
                            CommonClass.getRoundOff(footer.getEngconsumed(), footer.getBillpaid()));
                    ((VHFooter) holder).tv_date.setText("\n\n\n As On : " + date);
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

    private AnnexureRB5vo getItem(int position) {
        return list.get(position);
    }

    class VHItem extends RecyclerView.ViewHolder {
        TextView tv_rly_div, tv_avg_total;
        TextView tv_ec1, tv_ec2, tv_ec3, tv_ec4, tv_ec5;
        TextView tv_eb1, tv_eb2, tv_eb3, tv_eb4, tv_eb5;

        public VHItem(View itemView) {
            super(itemView);
            tv_rly_div = itemView.findViewById(R.id.tv_rly_div);
            tv_ec1 = itemView.findViewById(R.id.tv_ec1);
            tv_ec2 = itemView.findViewById(R.id.tv_ec2);
            tv_ec3 = itemView.findViewById(R.id.tv_ec3);
            tv_ec4 = itemView.findViewById(R.id.tv_ec4);
            tv_ec5 = itemView.findViewById(R.id.tv_ec5);
            tv_eb1 = itemView.findViewById(R.id.tv_eb1);
            tv_eb2 = itemView.findViewById(R.id.tv_eb2);
            tv_eb3 = itemView.findViewById(R.id.tv_eb3);
            tv_eb4 = itemView.findViewById(R.id.tv_eb4);
            tv_eb5 = itemView.findViewById(R.id.tv_eb5);
            tv_avg_total = itemView.findViewById(R.id.tv_avg_total);
        }
    }

    class VHHeader extends RecyclerView.ViewHolder {
        TextView tv_railway, tv_zon_div, tv_month, tv_energy_consume;
        TextView tv_year5, tv_year4, tv_year3, tv_year2, tv_year1;

        public VHHeader(View itemView) {
            super(itemView);
            tv_railway = itemView.findViewById(R.id.tv_railway);
            tv_month = itemView.findViewById(R.id.tv_month);
            tv_zon_div = itemView.findViewById(R.id.tv_zon_div);
            tv_energy_consume = itemView.findViewById(R.id.tv_energy_consume);
            tv_year5 = itemView.findViewById(R.id.tv_year5);
            tv_year4 = itemView.findViewById(R.id.tv_year4);
            tv_year3 = itemView.findViewById(R.id.tv_year3);
            tv_year2 = itemView.findViewById(R.id.tv_year2);
            tv_year1 = itemView.findViewById(R.id.tv_year1);
        }
    }

    class VHFooter extends RecyclerView.ViewHolder {
        TextView tv_avg_cost, tv_year5, tv_year4, tv_year3, tv_year2, tv_year1, tv_date;

        public VHFooter(View itemView) {
            super(itemView);
            tv_avg_cost = itemView.findViewById(R.id.tv_avg_cost);
            tv_year5 = itemView.findViewById(R.id.tv_year5);
            tv_year4 = itemView.findViewById(R.id.tv_year4);
            tv_year3 = itemView.findViewById(R.id.tv_year3);
            tv_year2 = itemView.findViewById(R.id.tv_year2);
            tv_year1 = itemView.findViewById(R.id.tv_year1);
            tv_date = itemView.findViewById(R.id.tv_date);
        }
    }
}