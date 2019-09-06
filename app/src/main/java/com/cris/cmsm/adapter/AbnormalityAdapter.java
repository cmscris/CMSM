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
import com.cris.cmsm.models.response.AbnormalityResponseVO;

import java.util.List;

/**
 * Created by rangasanju on 3/16/18.
 */




public class AbnormalityAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;
    private static final int TYPE_FOOTER = 2;
    private Context context;
    private List<AbnormalityResponseVO> list;
    private OnItemClickListener listener;
    private ReportHeaderView reportHeaderView;

    public AbnormalityAdapter(Context context, ReportHeaderView reportHeaderView, OnItemClickListener listener, List<AbnormalityResponseVO> list) {
        this.context = context;
        this.list = list;
        this.reportHeaderView = reportHeaderView;
        this.listener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_ITEM) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.abnormalityreport, parent, false);
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
            final AbnormalityResponseVO model = getItem(pos);


            item.tv_location.setText(model.getLocation());
            item.tv_lt_8.setText(model.getLt_8());
            item.tv_abnormality_head.setText(model.getAbnormality_head());
            item.tv_bw_8_16.setText(model.getBw_8_16());
            item.tv_bw_16_24.setText(model.getBw_16_24());
            item.tv_bw_24_72.setText(model.getBw_24_72());
            item.tv_bw_72_240.setText(model.getBw_72_240());
            item.tv_gt_240.setText(model.getGt_240());
            item.tv_still_open.setText(model.getStill_open());




            if (pos == 0) {


                item.tv_location.setTypeface(null, Typeface.BOLD);
                item.tv_abnormality_head.setTypeface(null, Typeface.BOLD);
                item.tv_lt_8.setTypeface(null, Typeface.BOLD);
                item.tv_bw_8_16.setTypeface(null, Typeface.BOLD);
                item.tv_bw_16_24.setTypeface(null, Typeface.BOLD);
                item.tv_bw_24_72.setTypeface(null, Typeface.BOLD);
                item.tv_bw_72_240.setTypeface(null, Typeface.BOLD);
                item.tv_gt_240.setTypeface(null, Typeface.BOLD);
                item.tv_still_open.setTypeface(null, Typeface.BOLD);




            } else {

                item.tv_location.setTypeface(null, Typeface.BOLD);
                item.tv_abnormality_head.setTypeface(null);
                item.tv_lt_8.setTypeface(null);
                item.tv_bw_8_16.setTypeface(null);
                item.tv_bw_16_24.setTypeface(null);
                item.tv_bw_24_72.setTypeface(null);
                item.tv_bw_72_240.setTypeface(null);
                item.tv_gt_240.setTypeface(null);
                item.tv_still_open.setTypeface(null);


            }
        }else if (holder instanceof AbnormalityAdapter.VHHeader) {

            ((AbnormalityAdapter.VHHeader) holder).tv_railway.setText(reportHeaderView.getEnergyConsume());
            ((AbnormalityAdapter.VHHeader) holder).tv_zon_div.setVisibility(View.GONE);
            ((AbnormalityAdapter.VHHeader) holder).tv_month.setVisibility(View.GONE);
            ((AbnormalityAdapter.VHHeader) holder).tv_energy_consume.setVisibility(View.GONE);
        }
    }






    @Override
    public int getItemCount() {
        return list.size() + 2;
    }

    class VHItem extends RecyclerView.ViewHolder {
        TextView tv_location, tv_abnormality_head, tv_lt_8, tv_bw_8_16, tv_bw_16_24, tv_bw_24_72, tv_bw_72_240, tv_gt_240, tv_still_open;

        public VHItem(View itemView) {
            super(itemView);

            tv_location = itemView.findViewById(R.id.tv_location);
            tv_abnormality_head = itemView.findViewById(R.id.tv_abnormality_head);
            tv_lt_8 = itemView.findViewById(R.id.tv_lt_8);
            tv_bw_8_16 = itemView.findViewById(R.id.tv_bw_8_16);
            tv_bw_16_24 = itemView.findViewById(R.id.tv_bw_16_24);
            tv_bw_24_72 = itemView.findViewById(R.id.tv_bw_24_72);
            tv_bw_72_240 = itemView.findViewById(R.id.tv_bw_72_240);
            tv_gt_240 = itemView.findViewById(R.id.tv_gt_240);
            tv_still_open = itemView.findViewById(R.id.tv_still_open);






        }
    }

    private AbnormalityResponseVO getItem(int position) {
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
