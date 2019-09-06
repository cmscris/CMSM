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
import com.cris.cmsm.models.response.CrewMileageDetailsVO;
import com.cris.cmsm.presenter.RequestPresenter;

import java.util.List;


public class MileageDetailsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;
    private static final int TYPE_FOOTER = 2;
    private Context context;
    private List<CrewMileageDetailsVO> list;
    private OnItemClickListener listener;
    private ReportHeaderView reportHeaderView;
    private RequestPresenter requestPresenter;


    public MileageDetailsAdapter(Context context, ReportHeaderView reportHeaderView, OnItemClickListener listener, List<CrewMileageDetailsVO> list) {
        this.context = context;
        this.list = list;
        this.reportHeaderView = reportHeaderView;
        this.listener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_ITEM) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.mileage_details_report, parent, false);
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
            final CrewMileageDetailsVO model = getItem(pos);


            item.tv_duty_hrs.setText(model.getDuty_hrs());
            item.tv_alkm_non_leave.setText(model.getAlkm_non_leave());
            item.tv_alkm_leave.setText(model.getAlkm_leave());
            item.tv_total_kms.setText(model.getTotal_kms());
            item.tv_pay_month.setText(model.getPay_month());


            item.tv_pay_month.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onModelClick(model);
                }
            });


            if (pos == 0) {


                item.tv_duty_hrs.setTypeface(null, Typeface.BOLD);
                item.tv_alkm_non_leave.setTypeface(null, Typeface.BOLD);
                item.tv_alkm_leave.setTypeface(null, Typeface.BOLD);
                item.tv_total_kms.setTypeface(null, Typeface.BOLD);
                item.tv_pay_month.setTypeface(null, Typeface.BOLD);


            } else {

                item.tv_duty_hrs.setTypeface(null);
                item.tv_alkm_non_leave.setTypeface(null);
                item.tv_alkm_leave.setTypeface(null);
                item.tv_total_kms.setTypeface(null);
                item.tv_pay_month.setTypeface(null);
            }

        } else if (holder instanceof VHHeader) {


            ((VHHeader) holder).tv_railway.setText(reportHeaderView.getEnergyConsume());
            ((VHHeader) holder).tv_zon_div.setVisibility(View.GONE);
            ((VHHeader) holder).tv_month.setVisibility(View.GONE);
            ((VHHeader) holder).tv_energy_consume.setVisibility(View.GONE);
        }
    }


    public void onModelClick(CrewMileageDetailsVO model) {

        listener.OnItemClick(model);
    }



    @Override
    public int getItemCount() {
        return list.size() + 2;
    }

    class VHItem extends RecyclerView.ViewHolder {
        TextView tv_duty_hrs, tv_alkm_non_leave, tv_alkm_leave, tv_total_kms, tv_pay_month;

        public VHItem(View itemView) {
            super(itemView);

            tv_duty_hrs = itemView.findViewById(R.id.tv_duty_hrs);
            tv_alkm_non_leave = itemView.findViewById(R.id.tv_alkm_non_leave);
            tv_alkm_leave = itemView.findViewById(R.id.tv_alkm_leave);
            tv_total_kms = itemView.findViewById(R.id.tv_total_kms);
            tv_pay_month = itemView.findViewById(R.id.tv_pay_month);

        }
    }

    private CrewMileageDetailsVO getItem(int position) {
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

