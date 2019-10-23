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
import com.cris.cmsm.models.response.LiMovementVOsResponse;
import com.cris.cmsm.presenter.RequestPresenter;

import java.util.List;

public class LiMovementDetailAdapter  extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;
    private static final int TYPE_FOOTER = 2;
    private Context context;
    private List <LiMovementVOsResponse> list;
    private OnItemClickListener listener;
    private ReportHeaderView reportHeaderView;
    private RequestPresenter requestPresenter;


    public LiMovementDetailAdapter(Context context, ReportHeaderView reportHeaderView, OnItemClickListener listener, List<LiMovementVOsResponse> list) {
        this.context = context;
        this.list = list;
        this.reportHeaderView = reportHeaderView;
        this.listener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_ITEM) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.li_movement_detail_layout, parent, false);
            return new LiMovementDetailAdapter.VHItem(view);
        } else if (viewType == TYPE_HEADER) {
            View headerView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_header_view, parent, false);
            return new LiMovementDetailAdapter.VHHeader(headerView);
        } else if (viewType == TYPE_FOOTER) {
            View footerView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_footer_view, parent, false);
            return new LiMovementDetailAdapter.VHFooter(footerView);
        }
        throw new RuntimeException("there is no type that matches the type " + viewType + " + make sure your using types correctly");
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof LiMovementDetailAdapter.VHItem) {
            LiMovementDetailAdapter.VHItem item = (LiMovementDetailAdapter.VHItem) holder;
            int pos = position - 1;
            final LiMovementVOsResponse model = getItem(pos);
            item.tv_id.setText(model.getSno());
            item.tv_frmdttim.setText(model.getFromDtTime());
            item.tv_todttim.setText(model.getToDtTime());
            item.tv_dutytype.setText(model.getDutyType());
            item.tv_fromsttn.setText(model.getFromSttn());
            item.tv_tosttn.setText(model.getToSttn());
            item.tv_via1.setText(model.getVia1());
            item.tv_via2.setText(model.getVia2());
            item.tv_km.setText(model.getKms());
            item.tv_loc.setText(model.getLocoNo());
            item.tv_tran.setText(model.getTrainNo());
            item.tv_rmk.setText(model.getRemrk());





            if (pos == 0) {

                item.tv_id.setTypeface(null, Typeface.BOLD);
                item.tv_frmdttim.setTypeface(null, Typeface.BOLD);
                item.tv_todttim.setTypeface(null, Typeface.BOLD);
                item.tv_dutytype.setTypeface(null, Typeface.BOLD);
                item.tv_fromsttn.setTypeface(null, Typeface.BOLD);
                item.tv_tosttn.setTypeface(null, Typeface.BOLD);
                item.tv_via1.setTypeface(null, Typeface.BOLD);
                item.tv_via2.setTypeface(null, Typeface.BOLD);
                item.tv_km.setTypeface(null, Typeface.BOLD);
                item.tv_loc.setTypeface(null, Typeface.BOLD);
                item.tv_tran.setTypeface(null, Typeface.BOLD);
                item.tv_rmk.setTypeface(null, Typeface.BOLD);

            } else {
                item.tv_id.setTypeface(null);
                item.tv_frmdttim.setTypeface(null);
                item.tv_todttim.setTypeface(null);
                item.tv_dutytype.setTypeface(null);
                item.tv_fromsttn.setTypeface(null);
                item.tv_tosttn.setTypeface(null);
                item.tv_via1.setTypeface(null);
                item.tv_via2.setTypeface(null);
                item.tv_km.setTypeface(null);
                item.tv_loc.setTypeface(null);
                item.tv_tran.setTypeface(null);
                item.tv_rmk.setTypeface(null);
            }

        } else if (holder instanceof LiMovementDetailAdapter.VHHeader) {


            ((LiMovementDetailAdapter.VHHeader) holder).tv_railway.setText(reportHeaderView.getEnergyConsume());
            ((LiMovementDetailAdapter.VHHeader) holder).tv_zon_div.setVisibility(View.GONE);
            ((LiMovementDetailAdapter.VHHeader) holder).tv_month.setVisibility(View.GONE);
            ((LiMovementDetailAdapter.VHHeader) holder).tv_energy_consume.setVisibility(View.GONE);
        }
    }


    public void onModelClick(LiMovementVOsResponse model) {

        listener.OnItemClick(model);
    }



    @Override
    public int getItemCount() {
        return list.size() + 2;
    }

    class VHItem extends RecyclerView.ViewHolder {
        TextView tv_id, tv_frmdttim, tv_todttim, tv_fromsttn, tv_tosttn, tv_loc, tv_tran, tv_rmk, tv_dutytype,tv_via1,tv_via2,tv_km;

        public VHItem(View itemView) {
            super(itemView);

            tv_id = (TextView) itemView.findViewById(R.id.tv_id);
            tv_frmdttim = (TextView) itemView.findViewById(R.id.tv_frmdttim);
            tv_todttim = (TextView) itemView.findViewById(R.id.tv_todttim);
            tv_dutytype = (TextView) itemView.findViewById(R.id.tv_dutytype);
            tv_fromsttn = (TextView) itemView.findViewById(R.id.tv_fromsttn);
            tv_tosttn = (TextView) itemView.findViewById(R.id.tv_tosttn);
            tv_loc = (TextView) itemView.findViewById(R.id.tv_loc);
            tv_via1=(TextView) itemView.findViewById(R.id.tv_via1);
            tv_via2=(TextView) itemView.findViewById(R.id.tv_via2);
            tv_km=(TextView) itemView.findViewById(R.id.tv_km);
            tv_tran = (TextView) itemView.findViewById(R.id.tv_tran);
            tv_rmk = (TextView) itemView.findViewById(R.id.tv_rmk);
        }
    }

    private LiMovementVOsResponse getItem(int position) {
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

