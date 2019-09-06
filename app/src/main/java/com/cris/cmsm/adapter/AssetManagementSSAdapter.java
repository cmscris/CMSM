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
import com.cris.cmsm.models.response.SubStationAsssetSummaryVO;

import java.util.List;

/**
 *
 */

public class AssetManagementSSAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;
    private static final int TYPE_FOOTER = 2;
    private Context context;
    private List<SubStationAsssetSummaryVO> list;
    private OnItemClickListener listener;
    private ReportHeaderView reportHeaderView;

    public AssetManagementSSAdapter(Context context, ReportHeaderView reportHeaderView, OnItemClickListener listener, List<SubStationAsssetSummaryVO> list) {
        this.context = context;
        this.list = list;
        this.reportHeaderView = reportHeaderView;
        this.listener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_ITEM) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ss_summary, parent, false);
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
            final VHItem item = (VHItem) holder;
            int pos = position - 1;
            final SubStationAsssetSummaryVO model = getItem(pos);
            item.tv_rly.setText(model.getRailway());
            item.tv_div.setText(model.getDivison());
            item.tv_dgset.setText(model.getDgset());
            item.tv_transformer.setText(model.getTransformer());
            item.tv_fireextinguisher.setText(model.getFireExtinguisher());
            item.tv_earthing.setText(model.getEarthing());
            item.tv_apfc.setText(model.getApfcPanel());
            item.tv_htpanel.setText(model.getHtPanel());
            item.tv_ltpanel.setText(model.getLtPanel());
            item.tv_servostabilizer.setText(model.getServostabilizer());
            if (pos != 0) {
                item.tv_dgset.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onModelClick(model, "DGSET", "01");
                    }
                });
                item.tv_transformer.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onModelClick(model, "TRANSFORMER", "02");
                    }
                });
                item.tv_fireextinguisher.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onModelClick(model, "FIRE EXTINGUISHER", "03");
                    }
                });
                item.tv_earthing.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onModelClick(model, "EARTHING", "04");
                    }
                });
                item.tv_apfc.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onModelClick(model, "APFC", "05");
                    }
                });
                item.tv_htpanel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onModelClick(model, "HT PANEL", "06");
                    }
                });
                item.tv_ltpanel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onModelClick(model, "LT PANEL", "07");
                    }
                });
                item.tv_servostabilizer.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onModelClick(model, "SERVO STABILIZER", "19");
                    }
                });
            }
            if (pos == 0) {
                item.tv_rly.setTypeface(null, Typeface.BOLD);
                item.tv_div.setTypeface(null, Typeface.BOLD);
                item.tv_dgset.setTypeface(null, Typeface.BOLD);
                item.tv_transformer.setTypeface(null, Typeface.BOLD);
                item.tv_fireextinguisher.setTypeface(null, Typeface.BOLD);
                item.tv_earthing.setTypeface(null, Typeface.BOLD);
                item.tv_apfc.setTypeface(null, Typeface.BOLD);
                item.tv_htpanel.setTypeface(null, Typeface.BOLD);
                item.tv_ltpanel.setTypeface(null, Typeface.BOLD);
                item.tv_servostabilizer.setTypeface(null, Typeface.BOLD);
            } else {
                item.tv_rly.setTypeface(null);
                item.tv_div.setTypeface(null);
                item.tv_dgset.setTypeface(null);
                item.tv_transformer.setTypeface(null);
                item.tv_fireextinguisher.setTypeface(null);
                item.tv_earthing.setTypeface(null);
                item.tv_apfc.setTypeface(null);
                item.tv_htpanel.setTypeface(null);
                item.tv_ltpanel.setTypeface(null);
                item.tv_servostabilizer.setTypeface(null);
            }
        } else if (holder instanceof VHHeader) {
            ((VHHeader) holder).tv_railway.setText(reportHeaderView.getEnergyConsume());
            ((VHHeader) holder).tv_zon_div.setVisibility(View.GONE);
            ((VHHeader) holder).tv_month.setVisibility(View.GONE);
            ((VHHeader) holder).tv_energy_consume.setVisibility(View.GONE);
        }
    }

    public void onModelClick(SubStationAsssetSummaryVO model, String assetName, String assetType) {
        model.setAssetsName(assetName);
        model.setAssestType(assetType);
        listener.OnItemClick(model);
    }

    @Override
    public int getItemCount() {
        return list.size() + 2;
    }

    class VHItem extends RecyclerView.ViewHolder {
        TextView tv_rly, tv_div, tv_dgset, tv_transformer, tv_fireextinguisher, tv_earthing, tv_apfc, tv_htpanel, tv_ltpanel, tv_servostabilizer;

        public VHItem(View itemView) {
            super(itemView);
            tv_rly = itemView.findViewById(R.id.tv_rly);
            tv_div = itemView.findViewById(R.id.tv_div);
            tv_dgset = itemView.findViewById(R.id.tv_dgset);
            tv_transformer = itemView.findViewById(R.id.tv_transformer);
            tv_fireextinguisher = itemView.findViewById(R.id.tv_fireextinguisher);
            tv_earthing = itemView.findViewById(R.id.tv_earthing);
            tv_apfc = itemView.findViewById(R.id.tv_apfc);
            tv_htpanel = itemView.findViewById(R.id.tv_htpanel);
            tv_ltpanel = itemView.findViewById(R.id.tv_ltpanel);
            tv_servostabilizer = itemView.findViewById(R.id.tv_servostabilizer);
        }
    }

    private SubStationAsssetSummaryVO getItem(int position) {
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
