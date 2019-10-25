package com.cris.cmsm.adapter;

import android.arch.lifecycle.ViewModel;
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

import com.cris.cmsm.LI_activitydraftdetail;
import com.cris.cmsm.Limovdraftresponse;
import com.cris.cmsm.R;
import com.cris.cmsm.database.DataHolder;
import com.cris.cmsm.interfaces.OnItemClickListener;
import com.cris.cmsm.models.ReportHeaderView;
import com.cris.cmsm.models.response.LICrewMonitoredResponse;

import java.util.ArrayList;
import java.util.List;

public class Limovadapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;
    private static final int TYPE_FOOTER = 2;
    private Context context;
    private ArrayList <Limovdraftresponse> list;
    private OnItemClickListener listener;
    int i = 0;
    private Resources res;
    private ReportHeaderView reportHeaderView;
    public static final int MULTI_SELECTION = 2;

    public Limovadapter(Context context, ReportHeaderView reportHeaderView, OnItemClickListener listener, ArrayList <Limovdraftresponse> list) {
        this.context = context;
        this.list = list;
        this.reportHeaderView = reportHeaderView;
        this.listener = listener;


    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if (viewType == TYPE_ITEM) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.limovdraft_layout, parent, false);
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
            System.out.println(">>>>>>>>>>pos value" + pos);

            final Limovdraftresponse model = getItem(pos);


            item.tv_id.setText(""+pos);
            item.tv_frmdttim.setText(model.getFrmdttm());
            item.tv_todttim.setText(model.getTodttm());
            item.tv_fromsttn.setText(model.getFrmsttn());
            item.tv_tosttn.setText(model.getTosttn());
            item.tv_via1.setText(model.getVia1());
            item.tv_via2.setText(model.getVia2());
            item.tv_km.setText(model.getKm());
            item.tv_dutytype.setText(model.getDutytyp());
            item.tv_loc.setText(model.getLoco());
            item.tv_tran.setText(model.getTrain());
            item.tv_rmk.setText(model.getRmk());
            item.tv_edit.setText(model.getEdit());
            //item.tv_del.setText(model.getDel());
            item.tv_edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (v.isSelected() && getItemViewType(v.getId()) == MULTI_SELECTION) {
                       item.tv_id.setBackgroundColor(context.getResources().getColor(R.color.colorBlueGrey));
                       item.tv_frmdttim.setBackgroundColor(context.getResources().getColor(R.color.colorBlueGrey));
                        item.tv_todttim.setBackgroundColor(context.getResources().getColor(R.color.colorBlueGrey));
                        item.tv_fromsttn.setBackgroundColor(context.getResources().getColor(R.color.colorBlueGrey));
                        item.tv_tosttn.setBackgroundColor(context.getResources().getColor(R.color.colorBlueGrey));
                        item.tv_dutytype.setBackgroundColor(context.getResources().getColor(R.color.colorBlueGrey));
                        item.tv_loc.setBackgroundColor(context.getResources().getColor(R.color.colorBlueGrey));
                        item.tv_tran.setBackgroundColor(context.getResources().getColor(R.color.colorBlueGrey));
                        item.tv_rmk.setBackgroundColor(context.getResources().getColor(R.color.colorBlueGrey));
                        item.tv_edit.setBackgroundColor(context.getResources().getColor(R.color.colorBlueGrey));
                    }
                    else{
                        item.tv_id.setBackgroundColor(context.getResources().getColor(R.color.colorBlueGrey));
                        item.tv_frmdttim.setBackgroundColor(context.getResources().getColor(R.color.colorBlueGrey));
                        item.tv_todttim.setBackgroundColor(context.getResources().getColor(R.color.colorBlueGrey));
                        item.tv_fromsttn.setBackgroundColor(context.getResources().getColor(R.color.colorBlueGrey));
                        item.tv_tosttn.setBackgroundColor(context.getResources().getColor(R.color.colorBlueGrey));
                        item.tv_dutytype.setBackgroundColor(context.getResources().getColor(R.color.colorBlueGrey));
                        item.tv_loc.setBackgroundColor(context.getResources().getColor(R.color.colorBlueGrey));
                        item.tv_tran.setBackgroundColor(context.getResources().getColor(R.color.colorBlueGrey));
                        item.tv_rmk.setBackgroundColor(context.getResources().getColor(R.color.colorBlueGrey));
                        item.tv_edit.setBackgroundColor(context.getResources().getColor(R.color.colorBlueGrey));
                    }
                    onModelClick(model);

                }
            });


            if (pos == 0) {
                item.tv_id.setText("SN");
                item.tv_id.setTypeface(null, Typeface.BOLD);
                item.tv_frmdttim.setTypeface(null, Typeface.BOLD);
                item.tv_todttim.setTypeface(null, Typeface.BOLD);
                item.tv_fromsttn.setTypeface(null, Typeface.BOLD);
                item.tv_tosttn.setTypeface(null, Typeface.BOLD);
                item.tv_dutytype.setTypeface(null, Typeface.BOLD);
                item.tv_loc.setTypeface(null, Typeface.BOLD);
                item.tv_tran.setTypeface(null, Typeface.BOLD);
                item.tv_rmk.setTypeface(null, Typeface.BOLD);
                item.tv_edit.setTypeface(null, Typeface.BOLD);
                item.tv_km.setTypeface(null,Typeface.BOLD);
               // item.tv_del.setTypeface(null, Typeface.BOLD);
                item.tv_via1.setTypeface(null, Typeface.BOLD);
                item.tv_via2.setTypeface(null, Typeface.BOLD);


            } else {
                item.tv_id.setTypeface(null, Typeface.BOLD);
                item.tv_frmdttim.setTypeface(null);
                item.tv_todttim.setTypeface(null);
                item.tv_fromsttn.setTypeface(null);
                item.tv_tosttn.setTypeface(null);
                item.tv_dutytype.setTypeface(null);
                item.tv_loc.setTypeface(null);
                item.tv_tran.setTypeface(null);
                item.tv_rmk.setTypeface(null);
                item.tv_edit.setTypeface(null);


            }

        } else if (holder instanceof VHHeader) {
            ((VHHeader) holder).tv_railway.setText(reportHeaderView.getEnergyConsume());
            ((VHHeader) holder).tv_zon_div.setVisibility(View.GONE);
            ((VHHeader) holder).tv_month.setVisibility(View.GONE);
            ((VHHeader) holder).tv_energy_consume.setVisibility(View.GONE);
        }

    }


    private void onModelClick(Limovdraftresponse model) {

        listener.OnItemClick(model);
    }
    @Override
    public int getItemCount() {
        return list.size() + 2;
    }

    class VHItem extends RecyclerView.ViewHolder {
        TextView tv_id, tv_frmdttim, tv_todttim, tv_fromsttn, tv_tosttn, tv_loc, tv_tran, tv_rmk, tv_dutytype, tv_edit,tv_via1,tv_via2,tv_km,tv_del;

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
            tv_edit = (TextView) itemView.findViewById(R.id.tv_edit);
           // tv_del = (TextView) itemView.findViewById(R.id.tv_del);



        }
    }

    private Limovdraftresponse getItem(int position) {
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



