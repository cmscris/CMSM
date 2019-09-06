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
import com.cris.cmsm.models.response.Annexure10vo;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class Annexure10_2Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;
    private static final int TYPE_FOOTER = 2;
    List<Annexure10vo> list;
    Context context;
    ReportHeaderView reportHeaderView;
    private String date;

    public Annexure10_2Adapter(Context context, ReportHeaderView reportHeaderView, List<Annexure10vo> list) {
        this.context = context;
        this.reportHeaderView = reportHeaderView;
        this.list = list;
        date = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Calendar.getInstance().getTime());
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_ITEM) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.annexure10_list_item2, parent, false);
            return new VHItem(view);
        } else if (viewType == TYPE_HEADER) {
            View headerView = LayoutInflater.from(parent.getContext()).inflate(R.layout.annexure_header_view2, parent, false);
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
            Annexure10vo model = getItem(pos);
            if (model.getRlycode() != null && model.getRlycode().equalsIgnoreCase("Z")) {
                try {
                    ((VHItem) holder).tv_rly_div.setText("TOTAL");
                    ((VHItem) holder).tv_ppf.setText(model.getPlowpf());
                    ((VHItem) holder).tv_pf.setText(model.getLowpf());
                    ((VHItem) holder).tv_pmd.setText(model.getPexcessSMD());
                    ((VHItem) holder).tv_md.setText(model.getExcessSMD());
                    ((VHItem) holder).tv_penergy.setText(model.getPexcessConsumption());
                    ((VHItem) holder).tv_energy.setText(model.getExcessConsumption());
                    ((VHItem) holder).tv_pfca.setText(model.getPexcessFCA());
                    ((VHItem) holder).tv_fca.setText(model.getExcessFCA());
                    ((VHItem) holder).tv_pdelay.setText(model.getPdelayedPayment());
                    ((VHItem) holder).tv_delay.setText(model.getDelayedPayment());
                    ((VHItem) holder).tv_psurcharge.setText(model.getPsurcharge());
                    ((VHItem) holder).tv_surcharge.setText(model.getSurcharge());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                ((VHItem) holder).tv_rly_div.setText(model.getRlycode());
                ((VHItem) holder).tv_ppf.setText(model.getPlowpf());
                ((VHItem) holder).tv_pf.setText(model.getLowpf());
                ((VHItem) holder).tv_pmd.setText(model.getPexcessSMD());
                ((VHItem) holder).tv_md.setText(model.getExcessSMD());
                ((VHItem) holder).tv_penergy.setText(model.getPexcessConsumption());
                ((VHItem) holder).tv_energy.setText(model.getExcessConsumption());
                ((VHItem) holder).tv_pfca.setText(model.getPexcessFCA());
                ((VHItem) holder).tv_fca.setText(model.getExcessFCA());
                ((VHItem) holder).tv_pdelay.setText(model.getPdelayedPayment());
                ((VHItem) holder).tv_delay.setText(model.getDelayedPayment());
                ((VHItem) holder).tv_psurcharge.setText(model.getPsurcharge());
                ((VHItem) holder).tv_surcharge.setText(model.getSurcharge());
            }

            if (pos == 0) {
                ((VHItem) holder).tv_rly_div.setTypeface(null, Typeface.BOLD);
                ((VHItem) holder).tv_ppf.setTypeface(null, Typeface.BOLD);
                ((VHItem) holder).tv_pf.setTypeface(null, Typeface.BOLD);
                ((VHItem) holder).tv_pmd.setTypeface(null, Typeface.BOLD);
                ((VHItem) holder).tv_md.setTypeface(null, Typeface.BOLD);
                ((VHItem) holder).tv_penergy.setTypeface(null, Typeface.BOLD);
                ((VHItem) holder).tv_energy.setTypeface(null, Typeface.BOLD);
                ((VHItem) holder).tv_pfca.setTypeface(null, Typeface.BOLD);
                ((VHItem) holder).tv_fca.setTypeface(null, Typeface.BOLD);
                ((VHItem) holder).tv_pdelay.setTypeface(null, Typeface.BOLD);
                ((VHItem) holder).tv_delay.setTypeface(null, Typeface.BOLD);
                ((VHItem) holder).tv_psurcharge.setTypeface(null, Typeface.BOLD);
                ((VHItem) holder).tv_surcharge.setTypeface(null, Typeface.BOLD);
            } else {
                ((VHItem) holder).tv_rly_div.setTypeface(null);
                ((VHItem) holder).tv_ppf.setTypeface(null);
                ((VHItem) holder).tv_pf.setTypeface(null);
                ((VHItem) holder).tv_pmd.setTypeface(null);
                ((VHItem) holder).tv_md.setTypeface(null);
                ((VHItem) holder).tv_penergy.setTypeface(null);
                ((VHItem) holder).tv_energy.setTypeface(null);
                ((VHItem) holder).tv_pfca.setTypeface(null);
                ((VHItem) holder).tv_fca.setTypeface(null);
                ((VHItem) holder).tv_pdelay.setTypeface(null);
                ((VHItem) holder).tv_delay.setTypeface(null);
                ((VHItem) holder).tv_psurcharge.setTypeface(null);
                ((VHItem) holder).tv_surcharge.setTypeface(null);
            }
        } else if (holder instanceof VHHeader) {
            System.out.println("Month is :" + reportHeaderView.getMonth());
            ((VHHeader) holder).tv_railway.setText(reportHeaderView.getRailway());
            ((VHHeader) holder).tv_zon_div.setText(reportHeaderView.getZon_div());
            ((VHHeader) holder).tv_month.setText(reportHeaderView.getMonth());
            ((VHHeader) holder).tv_energy_consume.setText(reportHeaderView.getEnergyConsume());

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

    private Annexure10vo getItem(int position) {
        return list.get(position);
    }

    class VHItem extends RecyclerView.ViewHolder {
        TextView tv_rly_div, tv_ppf, tv_pf, tv_pmd, tv_md, tv_penergy, tv_energy, tv_pfca, tv_fca, tv_pdelay, tv_delay, tv_psurcharge, tv_surcharge;

        public VHItem(View itemView) {
            super(itemView);
            tv_rly_div = itemView.findViewById(R.id.tv_rly_div);
            tv_ppf = itemView.findViewById(R.id.tv_ppf);
            tv_pf = itemView.findViewById(R.id.tv_pf);
            tv_pmd = itemView.findViewById(R.id.tv_pmd);
            tv_md = itemView.findViewById(R.id.tv_md);
            tv_penergy = itemView.findViewById(R.id.tv_penergy);
            tv_energy = itemView.findViewById(R.id.tv_energy);
            tv_pfca = itemView.findViewById(R.id.tv_pfca);
            tv_fca = itemView.findViewById(R.id.tv_fca);
            tv_pdelay = itemView.findViewById(R.id.tv_pdelay);
            tv_delay = itemView.findViewById(R.id.tv_delay);
            tv_psurcharge = itemView.findViewById(R.id.tv_psurcharge);
            tv_surcharge = itemView.findViewById(R.id.tv_surcharge);
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
        TextView tv_date, tv_support;

        public VHFooter(View itemView) {
            super(itemView);
            linear_footer = itemView.findViewById(R.id.linear_footer);
            tv_date = itemView.findViewById(R.id.tv_date);
            tv_support = itemView.findViewById(R.id.tv_support);
        }
    }
}