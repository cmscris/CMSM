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
import com.cris.cmsm.models.response.CrewAvailabilityDetailVO;

import java.util.List;


public class CrewAvailabilityDetailAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;
    private static final int TYPE_FOOTER = 2;
    private Context context;
    private List<CrewAvailabilityDetailVO> list;
    private OnItemClickListener listener;
    private ReportHeaderView reportHeaderView;

    public CrewAvailabilityDetailAdapter(Context context, ReportHeaderView reportHeaderView, OnItemClickListener listener, List<CrewAvailabilityDetailVO> list) {
        this.context = context;
        this.list = list;
        this.reportHeaderView = reportHeaderView;
        this.listener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_ITEM) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.crew_availability_detail, parent, false);
            return new VHItem(view);
        } else if (viewType == TYPE_HEADER) {
            View headerView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_header_view, parent, false);
            return new VHHeader(headerView);
        } else if (viewType == TYPE_FOOTER) {
            View footerView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_footer_view, parent, false);
            return new VHFooter(footerView);
        }
        throw new RuntimeException("there is no type that matches the type " + viewType + " + make sure your using types correctly");    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof VHItem) {
            VHItem item = (VHItem) holder;
            int pos = position - 1;
            final CrewAvailabilityDetailVO model = getItem(pos);

            item.tv_sno.setText(""+pos);
            item.tv_crewid.setText(model.getCrewId());
            item.tv_crewname.setText(model.getCrewName());
            item.tv_crewdesignation.setText(model.getCrewDesignation());

            item.tv_crewoffdesig.setText(model.getCrewType());
            item.tv_trainno.setText(model.getTrainNo());
            item.tv_locono.setText(model.getLocoNo());
            item.tv_lastdutyhrs.setText(model.getLastDutyHours());
            item.tv_signofftime.setText(model.getSignOffTime());
            item.tv_availabletime.setText(model.getAvailedHours());
            item.tv_resthrs.setText(model.getCrewRest());
            item.tv_mobileno.setText(model.getMobileNO());
            item.tv_cadre.setText(model.getCadre());


            if (pos == 0) {
                item.tv_sno.setText("SN");
                item.tv_crewid.setTypeface(null, Typeface.BOLD);
                item.tv_crewname.setTypeface(null, Typeface.BOLD);
                item.tv_crewdesignation.setTypeface(null, Typeface.BOLD);
                item.tv_crewoffdesig.setTypeface(null, Typeface.BOLD);
                item.tv_trainno.setTypeface(null, Typeface.BOLD);
                item.tv_locono.setTypeface(null, Typeface.BOLD);
                item.tv_lastdutyhrs.setTypeface(null, Typeface.BOLD);
                item.tv_signofftime.setTypeface(null, Typeface.BOLD);
                item.tv_availabletime.setTypeface(null, Typeface.BOLD);
                item.tv_resthrs.setTypeface(null, Typeface.BOLD);
                item.tv_mobileno.setTypeface(null, Typeface.BOLD);
                item.tv_cadre.setTypeface(null, Typeface.BOLD);

            } else {
                item.tv_crewid.setTypeface(null, Typeface.BOLD);
                item.tv_crewname.setTypeface(null);
                item.tv_crewdesignation.setTypeface(null);
            }
        }else if (holder instanceof CrewAvailabilityDetailAdapter.VHHeader) {
            ((CrewAvailabilityDetailAdapter.VHHeader) holder).tv_railway.setText(reportHeaderView.getEnergyConsume());
            ((CrewAvailabilityDetailAdapter.VHHeader) holder).tv_zon_div.setVisibility(View.GONE);
            ((CrewAvailabilityDetailAdapter.VHHeader) holder).tv_month.setVisibility(View.GONE);
            ((CrewAvailabilityDetailAdapter.VHHeader) holder).tv_energy_consume.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return list.size() + 2;
    }

    class VHItem extends RecyclerView.ViewHolder {
        TextView tv_sno,tv_crewid, tv_crewname, tv_crewdesignation, tv_crewoffdesig,tv_trainno,tv_locono,tv_lastdutyhrs,tv_signofftime,tv_availabletime,tv_resthrs,tv_mobileno,tv_cadre;

        public VHItem(View itemView) {
            super(itemView);
            tv_sno = (TextView) itemView.findViewById(R.id.tv_sno);
            tv_crewid = (TextView) itemView.findViewById(R.id.tv_crewid);
            tv_crewname = (TextView) itemView.findViewById(R.id.tv_crewname);
            tv_crewdesignation = (TextView) itemView.findViewById(R.id.tv_crewdesignation);
            tv_crewoffdesig = (TextView) itemView.findViewById(R.id.tv_crewoffdesig);
            tv_trainno = (TextView) itemView.findViewById(R.id.tv_trainno);
            tv_locono = (TextView) itemView.findViewById(R.id.tv_locono);
            tv_lastdutyhrs = (TextView) itemView.findViewById(R.id.tv_lastdutyhrs);
            tv_signofftime = (TextView) itemView.findViewById(R.id.tv_signofftime);
            tv_availabletime = (TextView) itemView.findViewById(R.id.tv_availabletime);
            tv_resthrs = (TextView) itemView.findViewById(R.id.tv_resthrs);
            tv_mobileno = (TextView) itemView.findViewById(R.id.tv_mobileno);
            tv_cadre = (TextView) itemView.findViewById(R.id.tv_cadre);
        }
    }

    private CrewAvailabilityDetailVO getItem(int position) {
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
            tv_railway = (TextView) itemView.findViewById(R.id.tv_railway);
            tv_month = (TextView) itemView.findViewById(R.id.tv_month);
            tv_zon_div = (TextView) itemView.findViewById(R.id.tv_zon_div);
            tv_energy_consume = (TextView) itemView.findViewById(R.id.tv_energy_consume);
        }
    }


    class VHFooter extends RecyclerView.ViewHolder {
        LinearLayout linear_footer;
        TextView tv_support, tv_date;

        public VHFooter(View itemView) {
            super(itemView);
            linear_footer = (LinearLayout) itemView.findViewById(R.id.linear_footer);
            tv_support = (TextView) itemView.findViewById(R.id.tv_support);
            tv_date = (TextView) itemView.findViewById(R.id.tv_date);
        }
    }

}
