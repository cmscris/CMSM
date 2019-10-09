package com.cris.cmsm.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cris.cmsm.R;
import com.cris.cmsm.interfaces.OnItemClickListener;
import com.cris.cmsm.models.ReportHeaderView;
import com.cris.cmsm.models.response.LICrewMonitoredResponse;
import com.cris.cmsm.models.response.LICrewMonitoredResponseVO;

import java.util.List;

import static com.cris.cmsm.R.color.colorCardOrange;

public class Licrewstatusadapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;
    private static final int TYPE_FOOTER = 2;
    private Context context;
    private List <LICrewMonitoredResponse> list;
    private OnItemClickListener listener;
    int i=0;
    int j=0;
    int k=0;
    private Resources res;
    private ReportHeaderView reportHeaderView;

    public Licrewstatusadapter(Context context, ReportHeaderView reportHeaderView, OnItemClickListener listener, List <LICrewMonitoredResponse> list) {
        this.context = context;
        this.list = list;
        this.reportHeaderView = reportHeaderView;
        this.listener = listener;


    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if (viewType == TYPE_ITEM) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.li_crewstatus_detail, parent, false);
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
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof VHItem) {
            VHItem item = (VHItem) holder;
            int pos = position - 1;

            final LICrewMonitoredResponse model = getItem(pos);
            item.tv_sno.setText("" +model.getSn());
            item.tv_crewid.setText(model.getCrewid());
            item.tv_crewname.setText(model.getCrewname());
            item.tv_crewdesignation.setText(model.getDesig());
            item.tv_status.setText(model.getStatus());
            item.tv_availsttn.setText(model.getAvlsttn());
            item.tv_fromsttn.setText(model.getFrmsttn());
            item.tv_tosttn.setText(model.getTosttn());
            item.tv_calltime.setText(model.getCalltime());
            item.tv_booktime.setText(model.getBooktime());
            item.tv_lastsignofftime.setText(model.getLastsignofftym());
            item.tv_availtime.setText(model.getAvailtym());

            if (pos== 0) {
                                item.tv_sno.setText("SN");
                item.tv_crewid.setTypeface(null, Typeface.BOLD);
                item.tv_crewname.setTypeface(null, Typeface.BOLD);
                item.tv_crewdesignation.setTypeface(null, Typeface.BOLD);
                item.tv_status.setTypeface(null, Typeface.BOLD);
                item.tv_availtime.setTypeface(null, Typeface.BOLD);
                item.tv_availsttn.setTypeface(null, Typeface.BOLD);
                item.tv_fromsttn.setTypeface(null, Typeface.BOLD);
                item.tv_tosttn.setTypeface(null, Typeface.BOLD);
                item.tv_calltime.setTypeface(null, Typeface.BOLD);
                item.tv_booktime.setTypeface(null, Typeface.BOLD);
                item.tv_lastsignofftime.setTypeface(null, Typeface.BOLD);

            }
            if(pos%2==0) {
                if (item.tv_sno.getText().toString().equals("SN")) {
                    item.tv_sno.setBackgroundColor(context.getResources().getColor(R.color.colorBlueText));
                    item.tv_crewid.setBackgroundColor(context.getResources().getColor(R.color.colorBlueText));
                    item.tv_crewname.setBackgroundColor(context.getResources().getColor(R.color.colorBlueText));
                    item.tv_crewdesignation.setBackgroundColor(context.getResources().getColor(R.color.colorBlueText));
                    item.tv_status.setBackgroundColor(context.getResources().getColor(R.color.colorBlueText));
                    item.tv_availtime.setBackgroundColor(context.getResources().getColor(R.color.colorBlueText));
                    item.tv_availsttn.setBackgroundColor(context.getResources().getColor(R.color.colorBlueText));
                    item.tv_fromsttn.setBackgroundColor(context.getResources().getColor(R.color.colorBlueText));
                    item.tv_tosttn.setBackgroundColor(context.getResources().getColor(R.color.colorBlueText));
                    item.tv_calltime.setBackgroundColor(context.getResources().getColor(R.color.colorBlueText));
                    item.tv_booktime.setBackgroundColor(context.getResources().getColor(R.color.colorBlueText));
                    item.tv_lastsignofftime.setBackgroundColor(context.getResources().getColor(R.color.colorBlueText));
                    item.tv_sno.setTextColor(context.getResources().getColor(R.color.colorWhite));
                    item.tv_crewid.setTextColor(context.getResources().getColor(R.color.colorWhite));
                    item.tv_crewname.setTextColor(context.getResources().getColor(R.color.colorWhite));
                    item.tv_crewdesignation.setTextColor(context.getResources().getColor(R.color.colorWhite));
                    item.tv_status.setTextColor(context.getResources().getColor(R.color.colorWhite));
                    item.tv_availtime.setTextColor(context.getResources().getColor(R.color.colorWhite));
                    item.tv_availsttn.setTextColor(context.getResources().getColor(R.color.colorWhite));
                    item.tv_fromsttn.setTextColor(context.getResources().getColor(R.color.colorWhite));
                    item.tv_tosttn.setTextColor(context.getResources().getColor(R.color.colorWhite));
                    item.tv_calltime.setTextColor(context.getResources().getColor(R.color.colorWhite));
                    item.tv_booktime.setTextColor(context.getResources().getColor(R.color.colorWhite));
                    item.tv_lastsignofftime.setTextColor(context.getResources().getColor(R.color.colorWhite));
                    item.tv_crewid.setTypeface(null, Typeface.BOLD);
                    item.tv_crewname.setTypeface(null, Typeface.BOLD);
                    item.tv_crewdesignation.setTypeface(null, Typeface.BOLD);
                    item.tv_status.setTypeface(null, Typeface.BOLD);
                    item.tv_availtime.setTypeface(null, Typeface.BOLD);
                    item.tv_availsttn.setTypeface(null, Typeface.BOLD);
                    item.tv_fromsttn.setTypeface(null, Typeface.BOLD);
                    item.tv_tosttn.setTypeface(null, Typeface.BOLD);
                    item.tv_calltime.setTypeface(null, Typeface.BOLD);
                    item.tv_booktime.setTypeface(null, Typeface.BOLD);
                    item.tv_lastsignofftime.setTypeface(null, Typeface.BOLD);

                } else {
                    item.tv_sno.setBackgroundColor(context.getResources().getColor(R.color.colorBlueGrey));
                    item.tv_crewid.setBackgroundColor(context.getResources().getColor(R.color.colorBlueGrey));
                    item.tv_crewname.setBackgroundColor(context.getResources().getColor(R.color.colorBlueGrey));
                    item.tv_crewdesignation.setBackgroundColor(context.getResources().getColor(R.color.colorBlueGrey));
                    item.tv_status.setBackgroundColor(context.getResources().getColor(R.color.colorBlueGrey));
                    item.tv_availtime.setBackgroundColor(context.getResources().getColor(R.color.colorBlueGrey));
                    item.tv_availsttn.setBackgroundColor(context.getResources().getColor(R.color.colorBlueGrey));
                    item.tv_fromsttn.setBackgroundColor(context.getResources().getColor(R.color.colorBlueGrey));
                    item.tv_tosttn.setBackgroundColor(context.getResources().getColor(R.color.colorBlueGrey));
                    item.tv_calltime.setBackgroundColor(context.getResources().getColor(R.color.colorBlueGrey));
                    item.tv_booktime.setBackgroundColor(context.getResources().getColor(R.color.colorBlueGrey));
                    item.tv_lastsignofftime.setBackgroundColor(context.getResources().getColor(R.color.colorBlueGrey));
                    item.tv_sno.setTextColor(context.getResources().getColor(R.color.colorWhite));
                    item.tv_crewid.setTextColor(context.getResources().getColor(R.color.colorWhite));
                    item.tv_crewname.setTextColor(context.getResources().getColor(R.color.colorWhite));
                    item.tv_crewdesignation.setTextColor(context.getResources().getColor(R.color.colorWhite));
                    item.tv_status.setTextColor(context.getResources().getColor(R.color.colorWhite));
                    item.tv_availtime.setTextColor(context.getResources().getColor(R.color.colorWhite));
                    item.tv_availsttn.setTextColor(context.getResources().getColor(R.color.colorWhite));
                    item.tv_fromsttn.setTextColor(context.getResources().getColor(R.color.colorWhite));
                    item.tv_tosttn.setTextColor(context.getResources().getColor(R.color.colorWhite));
                    item.tv_calltime.setTextColor(context.getResources().getColor(R.color.colorWhite));
                    item.tv_booktime.setTextColor(context.getResources().getColor(R.color.colorWhite));
                    item.tv_lastsignofftime.setTextColor(context.getResources().getColor(R.color.colorWhite));

                }
            }
            else {
                if (item.tv_sno.getText().toString().equals("SN")) {
                    item.tv_sno.setBackgroundColor(context.getResources().getColor(R.color.colorBlueText));
                    item.tv_crewid.setBackgroundColor(context.getResources().getColor(R.color.colorBlueText));
                    item.tv_crewname.setBackgroundColor(context.getResources().getColor(R.color.colorBlueText));
                    item.tv_crewdesignation.setBackgroundColor(context.getResources().getColor(R.color.colorBlueText));
                    item.tv_status.setBackgroundColor(context.getResources().getColor(R.color.colorBlueText));
                    item.tv_availtime.setBackgroundColor(context.getResources().getColor(R.color.colorBlueText));
                    item.tv_availsttn.setBackgroundColor(context.getResources().getColor(R.color.colorBlueText));
                    item.tv_fromsttn.setBackgroundColor(context.getResources().getColor(R.color.colorBlueText));
                    item.tv_tosttn.setBackgroundColor(context.getResources().getColor(R.color.colorBlueText));
                    item.tv_calltime.setBackgroundColor(context.getResources().getColor(R.color.colorBlueText));
                    item.tv_booktime.setBackgroundColor(context.getResources().getColor(R.color.colorBlueText));
                    item.tv_lastsignofftime.setBackgroundColor(context.getResources().getColor(R.color.colorBlueText));
                    item.tv_sno.setTextColor(context.getResources().getColor(R.color.colorWhite));
                    item.tv_crewid.setTextColor(context.getResources().getColor(R.color.colorWhite));
                    item.tv_crewname.setTextColor(context.getResources().getColor(R.color.colorWhite));
                    item.tv_crewdesignation.setTextColor(context.getResources().getColor(R.color.colorWhite));
                    item.tv_status.setTextColor(context.getResources().getColor(R.color.colorWhite));
                    item.tv_availtime.setTextColor(context.getResources().getColor(R.color.colorWhite));
                    item.tv_availsttn.setTextColor(context.getResources().getColor(R.color.colorWhite));
                    item.tv_fromsttn.setTextColor(context.getResources().getColor(R.color.colorWhite));
                    item.tv_tosttn.setTextColor(context.getResources().getColor(R.color.colorWhite));
                    item.tv_calltime.setTextColor(context.getResources().getColor(R.color.colorWhite));
                    item.tv_booktime.setTextColor(context.getResources().getColor(R.color.colorWhite));
                    item.tv_lastsignofftime.setTextColor(context.getResources().getColor(R.color.colorWhite));

                } else {
                    item.tv_sno.setBackgroundColor(context.getResources().getColor(R.color.colorBlueGrey));
                    item.tv_crewid.setBackgroundColor(context.getResources().getColor(R.color.colorBlueGrey));
                    item.tv_crewname.setBackgroundColor(context.getResources().getColor(R.color.colorBlueGrey));
                    item.tv_crewdesignation.setBackgroundColor(context.getResources().getColor(R.color.colorBlueGrey));
                    item.tv_status.setBackgroundColor(context.getResources().getColor(R.color.colorBlueGrey));
                    item.tv_availtime.setBackgroundColor(context.getResources().getColor(R.color.colorBlueGrey));
                    item.tv_availsttn.setBackgroundColor(context.getResources().getColor(R.color.colorBlueGrey));
                    item.tv_fromsttn.setBackgroundColor(context.getResources().getColor(R.color.colorBlueGrey));
                    item.tv_tosttn.setBackgroundColor(context.getResources().getColor(R.color.colorBlueGrey));
                    item.tv_calltime.setBackgroundColor(context.getResources().getColor(R.color.colorBlueGrey));
                    item.tv_booktime.setBackgroundColor(context.getResources().getColor(R.color.colorBlueGrey));
                    item.tv_lastsignofftime.setBackgroundColor(context.getResources().getColor(R.color.colorBlueGrey));
                    item.tv_sno.setTextColor(context.getResources().getColor(R.color.colorWhite));
                    item.tv_crewid.setTextColor(context.getResources().getColor(R.color.colorWhite));
                    item.tv_crewname.setTextColor(context.getResources().getColor(R.color.colorWhite));
                    item.tv_crewdesignation.setTextColor(context.getResources().getColor(R.color.colorWhite));
                    item.tv_status.setTextColor(context.getResources().getColor(R.color.colorWhite));
                    item.tv_availtime.setTextColor(context.getResources().getColor(R.color.colorWhite));
                    item.tv_availsttn.setTextColor(context.getResources().getColor(R.color.colorWhite));
                    item.tv_fromsttn.setTextColor(context.getResources().getColor(R.color.colorWhite));
                    item.tv_tosttn.setTextColor(context.getResources().getColor(R.color.colorWhite));
                    item.tv_calltime.setTextColor(context.getResources().getColor(R.color.colorWhite));
                    item.tv_booktime.setTextColor(context.getResources().getColor(R.color.colorWhite));
                    item.tv_lastsignofftime.setTextColor(context.getResources().getColor(R.color.colorWhite));

                }
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
        TextView tv_sno, tv_crewid, tv_crewname, tv_crewdesignation, tv_status, tv_availtime, tv_availsttn, tv_fromsttn, tv_tosttn,tv_calltime,tv_booktime,tv_lastsignofftime;

        public VHItem(View itemView) {

            super(itemView);
            tv_sno = (TextView) itemView.findViewById(R.id.tv_sno);
            tv_crewid = (TextView) itemView.findViewById(R.id.tv_crewid);
            tv_crewname = (TextView) itemView.findViewById(R.id.tv_crewname);
            tv_crewdesignation = (TextView) itemView.findViewById(R.id.tv_crewdesignation);
            tv_status = (TextView) itemView.findViewById(R.id.tv_crewstatus);
            tv_availtime= (TextView) itemView.findViewById(R.id.tv_availtime);
            tv_availsttn= (TextView) itemView.findViewById(R.id.tv_avail_sttn);
            tv_fromsttn= (TextView) itemView.findViewById(R.id.tv_fromsttn);
            tv_tosttn = (TextView) itemView.findViewById(R.id.tv_tosttn);
            tv_calltime=(TextView)itemView.findViewById(R.id.tv_calltime);
            tv_booktime=(TextView)itemView.findViewById(R.id.tv_booktime);
            tv_lastsignofftime=(TextView)itemView.findViewById(R.id.tv_lastsignofftime);
        }
    }

    private LICrewMonitoredResponse getItem(int position) {
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



