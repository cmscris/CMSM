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
import com.cris.cmsm.models.response.CrewOvertimeDetailsVO;
import com.cris.cmsm.models.response.CrewOvertimeDetailsVO;
import com.cris.cmsm.presenter.RequestPresenter;

import java.util.List;



public class OvertimeDetailsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;
    private static final int TYPE_FOOTER = 2;
    private Context context;
    private List<CrewOvertimeDetailsVO> list;
    private OnItemClickListener listener;
    private ReportHeaderView reportHeaderView;
    private RequestPresenter requestPresenter;


    public OvertimeDetailsAdapter(Context context, ReportHeaderView reportHeaderView, OnItemClickListener listener, List<CrewOvertimeDetailsVO> list) {
        this.context = context;
        this.list = list;
        this.reportHeaderView = reportHeaderView;
        this.listener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_ITEM) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.overtime_details_report, parent, false);
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
            final CrewOvertimeDetailsVO model = getItem(pos);


            item.tv_running_days.setText(model.getRunning_days());
            item.tv_non_run_days.setText(model.getNon_run_days());
            item.tv_leaves.setText(model.getLeaves());
            item.tv_absents.setText(model.getAbsents());
            item.tv_total_duty.setText(model.getTotal_duty());
            item.tv_from.setText(model.getFrom());
            item.tv_to.setText(model.getTo());


            item.tv_from.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onModelClick(model);
                }
            });


            if (pos == 0) {


                item.tv_running_days.setTypeface(null, Typeface.BOLD);
                item.tv_non_run_days.setTypeface(null, Typeface.BOLD);
                item.tv_leaves.setTypeface(null, Typeface.BOLD);
                item.tv_absents.setTypeface(null, Typeface.BOLD);
                item.tv_total_duty.setTypeface(null, Typeface.BOLD);
                item.tv_from.setTypeface(null, Typeface.BOLD);
                item.tv_to.setTypeface(null, Typeface.BOLD);



            } else {

                item.tv_running_days.setTypeface(null);
                item.tv_non_run_days.setTypeface(null);
                item.tv_leaves.setTypeface(null);
                item.tv_absents.setTypeface(null);
                item.tv_total_duty.setTypeface(null);
                item.tv_from.setTypeface(null);
                item.tv_to.setTypeface(null);
            }

        } else if (holder instanceof VHHeader) {


            ((VHHeader) holder).tv_railway.setText(reportHeaderView.getEnergyConsume());
            ((VHHeader) holder).tv_zon_div.setVisibility(View.GONE);
            ((VHHeader) holder).tv_month.setVisibility(View.GONE);
            ((VHHeader) holder).tv_energy_consume.setVisibility(View.GONE);
        }
    }


    public void onModelClick(CrewOvertimeDetailsVO model) {

        listener.OnItemClick(model);
    }



    @Override
    public int getItemCount() {
        return list.size() + 2;
    }

    class VHItem extends RecyclerView.ViewHolder {
        TextView tv_running_days, tv_non_run_days, tv_leaves, tv_absents, tv_total_duty, tv_from, tv_to;

        public VHItem(View itemView) {
            super(itemView);

            tv_running_days = itemView.findViewById(R.id.tv_running_days);
            tv_non_run_days = itemView.findViewById(R.id.tv_non_run_days);
            tv_leaves = itemView.findViewById(R.id.tv_leaves);
            tv_absents = itemView.findViewById(R.id.tv_absents);
            tv_total_duty = itemView.findViewById(R.id.tv_total_duty);
            tv_from = itemView.findViewById(R.id.tv_from);
            tv_to = itemView.findViewById(R.id.tv_to);
           
        }
    }

    private CrewOvertimeDetailsVO getItem(int position) {
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

