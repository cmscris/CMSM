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
import com.cris.cmsm.models.response.RoadLearningDetailsVO;
import com.cris.cmsm.models.response.RoadLearningDetailsVO;
import com.cris.cmsm.presenter.RequestPresenter;

import java.util.List;



public class RoadLearningAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;
    private static final int TYPE_FOOTER = 2;
    private Context context;
    private List<RoadLearningDetailsVO> list;
    private OnItemClickListener listener;
    private ReportHeaderView reportHeaderView;
    private RequestPresenter requestPresenter;


    public RoadLearningAdapter(Context context, ReportHeaderView reportHeaderView, OnItemClickListener listener, List<RoadLearningDetailsVO> list) {
        this.context = context;
        this.list = list;
        this.reportHeaderView = reportHeaderView;
        this.listener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_ITEM) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.road_learning_report, parent, false);
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
            final RoadLearningDetailsVO model = getItem(pos);


            item.tv_lr_sec.setText(model.getLr_sec());
            item.tv_drv_date.setText(model.getDrv_date());
            item.tv_due_date.setText(model.getDue_date());
            item.tv_day_trip_due.setText(model.getDay_trip_due());
            item.tv_night_trip_due.setText(model.getNight_trip_due());
            


            if (pos == 0) {

                item.tv_lr_sec.setTypeface(null, Typeface.BOLD);
                item.tv_drv_date.setTypeface(null, Typeface.BOLD);
                item.tv_due_date.setTypeface(null, Typeface.BOLD);
                item.tv_day_trip_due.setTypeface(null, Typeface.BOLD);
                item.tv_night_trip_due.setTypeface(null, Typeface.BOLD);

            } else {

                item.tv_lr_sec.setTypeface(null);
                item.tv_drv_date.setTypeface(null);
                item.tv_due_date.setTypeface(null);
                item.tv_day_trip_due.setTypeface(null);
                item.tv_night_trip_due.setTypeface(null);
            }

        } else if (holder instanceof VHHeader) {


            ((VHHeader) holder).tv_railway.setText(reportHeaderView.getEnergyConsume());
            ((VHHeader) holder).tv_zon_div.setVisibility(View.GONE);
            ((VHHeader) holder).tv_month.setVisibility(View.GONE);
            ((VHHeader) holder).tv_energy_consume.setVisibility(View.GONE);
        }
    }


    public void onModelClick(RoadLearningDetailsVO model) {

        listener.OnItemClick(model);
    }



    @Override
    public int getItemCount() {
        return list.size() + 2;
    }

    class VHItem extends RecyclerView.ViewHolder {
        TextView tv_lr_sec, tv_drv_date, tv_due_date, tv_day_trip_due, tv_night_trip_due;

        public VHItem(View itemView) {
            super(itemView);

            tv_lr_sec = itemView.findViewById(R.id.tv_lr_sec);
            tv_drv_date = itemView.findViewById(R.id.tv_drv_date);
            tv_due_date = itemView.findViewById(R.id.tv_due_date);
            tv_day_trip_due = itemView.findViewById(R.id.tv_day_trip_due);
            tv_night_trip_due = itemView.findViewById(R.id.tv_night_trip_due);

        }
    }

    private RoadLearningDetailsVO getItem(int position) {
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
