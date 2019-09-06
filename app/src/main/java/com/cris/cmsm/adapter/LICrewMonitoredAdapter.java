package com.cris.cmsm.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cris.cmsm.R;
import com.cris.cmsm.interfaces.OnItemClickListener;
import com.cris.cmsm.models.ReportHeaderView;
import com.cris.cmsm.models.request.KeyValueRequest;
import com.cris.cmsm.models.response.CrewUtilResponseVO;
import com.cris.cmsm.models.response.LICrewMonitoredResponseVO;
import com.cris.cmsm.models.response.SubStationAsssetSummaryVO;
import com.cris.cmsm.presenter.RequestPresenter;
import com.cris.cmsm.util.Constants;
import com.cris.cmsm.widget.PinchRecyclerView;

import java.util.List;

/**
 * Created by cms on 4/11/18.
 */



public class LICrewMonitoredAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;
    private static final int TYPE_FOOTER = 2;
    private Context context;
    private List<LICrewMonitoredResponseVO> list;
    private OnItemClickListener listener;
    private ReportHeaderView reportHeaderView;
    private RequestPresenter requestPresenter;


    public LICrewMonitoredAdapter(Context context, ReportHeaderView reportHeaderView, OnItemClickListener listener, List<LICrewMonitoredResponseVO> list) {
        this.context = context;
        this.list = list;
        this.reportHeaderView = reportHeaderView;
        this.listener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_ITEM) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_li_crew_monitored, parent, false);
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
            final LICrewMonitoredResponseVO model = getItem(pos);


            item.tv_sno.setText(model.getSno());
            item.tv_crewid.setText(model.getCrewid());
            item.tv_crewname.setText(model.getCrewname());
            item.tv_crewdesignation.setText(model.getDesignation());
            item.tv_grade.setText(model.getGrade());
            item.tv_G_Date.setText(model.getGradedate());
            item.tv_status.setText(model.getStatus());
            item.tv_counseldate.setText(model.getCounseldate());

            item.tv_crewid.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onModelClick(model);
                }
            });


            if (pos == 0) {

                item.tv_sno.setTypeface(null, Typeface.BOLD);
                item.tv_crewid.setTypeface(null, Typeface.BOLD);
                item.tv_crewname.setTypeface(null, Typeface.BOLD);
                item.tv_crewdesignation.setTypeface(null, Typeface.BOLD);
                item.tv_grade.setTypeface(null, Typeface.BOLD);
                item.tv_G_Date.setTypeface(null, Typeface.BOLD);
                item.tv_status.setTypeface(null, Typeface.BOLD);
                item.tv_counseldate.setTypeface(null, Typeface.BOLD);

            } else {


                item.tv_sno.setTypeface(null, Typeface.BOLD);
                item.tv_crewid.setTypeface(null);
                item.tv_crewname.setTypeface(null);
                item.tv_crewdesignation.setTypeface(null);
                item.tv_grade.setTypeface(null);
                item.tv_G_Date.setTypeface(null);
                item.tv_status.setTypeface(null);
                item.tv_counseldate.setTypeface(null);

            }
        } else if (holder instanceof VHHeader) {


            ((VHHeader) holder).tv_railway.setText(reportHeaderView.getEnergyConsume());
            ((VHHeader) holder).tv_zon_div.setVisibility(View.GONE);
            ((VHHeader) holder).tv_month.setVisibility(View.GONE);
            ((VHHeader) holder).tv_energy_consume.setVisibility(View.GONE);
        }
    }


    public void onModelClick(LICrewMonitoredResponseVO model) {

        listener.OnItemClick(model);
    }



    @Override
    public int getItemCount() {
        return list.size() + 2;
    }

    class VHItem extends RecyclerView.ViewHolder {
        TextView tv_sno, tv_crewid, tv_crewname, tv_crewdesignation, tv_grade, tv_G_Date, tv_status, tv_counseldate;

        public VHItem(View itemView) {
            super(itemView);

            tv_sno = itemView.findViewById(R.id.tv_sno);
            tv_crewid = itemView.findViewById(R.id.tv_crewid);
            tv_crewname = itemView.findViewById(R.id.tv_crewname);
            tv_crewdesignation = itemView.findViewById(R.id.tv_crewdesignation);
            tv_grade = itemView.findViewById(R.id.tv_grade);
            tv_G_Date = itemView.findViewById(R.id.tv_G_Date);
            tv_status = itemView.findViewById(R.id.tv_status);
            tv_counseldate = itemView.findViewById(R.id.tv_counseldate);





        }
    }

    private LICrewMonitoredResponseVO getItem(int position) {
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
