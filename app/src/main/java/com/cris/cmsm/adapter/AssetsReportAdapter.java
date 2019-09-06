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
import com.cris.cmsm.models.request.RequestSSAssets;
import com.cris.cmsm.models.response.TabularReportVO;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class AssetsReportAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;
    private static final int TYPE_FOOTER = 2;
    private List<TabularReportVO> list;
    private Context context;
    private RequestSSAssets reportHeaderView;
    private String date;
    private OnItemClickListener listener;

    public AssetsReportAdapter(Context context, OnItemClickListener listener, RequestSSAssets reportHeaderView, List<TabularReportVO> list) {
        this.context = context;
        this.listener = listener;
        this.reportHeaderView = reportHeaderView;
        this.list = list;
        date = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Calendar.getInstance().getTime());
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_ITEM) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.assets_list_item, parent, false);
            return new VHItem(view);
        } else if (viewType == TYPE_HEADER) {
            View headerView = LayoutInflater.from(parent.getContext()).inflate(R.layout.assets_header, parent, false);
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
            int pos = position - 1;
            final TabularReportVO model = getItem(pos);
            VHItem item = (VHItem) holder;
            item.tv_rly.setText(model.getRailway());
            item.tv_div.setText(model.getDivison());
            item.tv_sse_incharge.setText(model.getSseIncharge());
            item.tv_sub_station.setText(model.getSubstation());
            item.tv_assets_name.setText(model.getAssestname());
            item.tv_rating.setText(model.getRating());
            item.tv_make.setText(model.getMake());
            item.tv_date.setText(model.getDateofmanufacturing());

            if (reportHeaderView.getAssetsName().equalsIgnoreCase("FIRE EXTINGUISHER")
                    || (reportHeaderView.getAssetsName().equalsIgnoreCase("EARTHING"))
                    || (reportHeaderView.getAssetsName().equalsIgnoreCase("SERVO STABILIZER"))) {
                item.linear_item.setWeightSum(7);
                item.linear_details.setVisibility(View.GONE);
                item.tv_rating.setVisibility(View.GONE);
                item.view_rating.setVisibility(View.GONE);
            } else if (reportHeaderView.getAssetsName().equalsIgnoreCase("LT PANEL")
                    || reportHeaderView.getAssetsName().equalsIgnoreCase("HT PANEL")) {
                item.linear_item.setWeightSum(6);
                item.linear_details.setVisibility(View.VISIBLE);
                item.tv_date.setVisibility(View.GONE);
                item.tv_rating.setVisibility(View.GONE);
                item.view_rating.setVisibility(View.GONE);
                item.tv_make.setVisibility(View.GONE);
                item.view_make.setVisibility(View.GONE);
            } else {
                item.linear_item.setWeightSum(8);
                item.linear_details.setVisibility(View.GONE);
                item.tv_date.setVisibility(View.VISIBLE);
                item.tv_rating.setVisibility(View.VISIBLE);
                item.view_rating.setVisibility(View.VISIBLE);
                item.tv_make.setVisibility(View.VISIBLE);
                item.view_make.setVisibility(View.VISIBLE);
            }
            if (model.getDetails() != null) {
                item.tv_details.setText(model.getDetails());
                item.tv_details.setTextColor(context.getResources().getColor(R.color.colorDarkBlack));
                item.tv_details.setBackgroundColor(context.getResources().getColor(R.color.colorWhite));
                item.tv_details.setTypeface(null);
                item.tv_details.setOnClickListener(null);
            } else {
                item.tv_details.setText("Details");
                item.tv_details.setTextColor(context.getResources().getColor(R.color.colorWhite));
                item.tv_details.setBackgroundColor(context.getResources().getColor(R.color.colorBlueText));
                item.tv_details.setTypeface(item.tv_details.getTypeface(), Typeface.BOLD);
                item.tv_details.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        listener.OnItemClick(model);
                    }
                });
            }
        } else if (holder instanceof VHHeader) {
            ((VHHeader) holder).tv_assets_type.setText(reportHeaderView.getAssetsName());
            ((VHHeader) holder).tv_report_date.setText(date);
            ((VHHeader) holder).tv_railway.setText(reportHeaderView.getRailCode());
            ((VHHeader) holder).tv_division.setText(reportHeaderView.getDivision());

        } else if (holder instanceof VHFooter) {
            ((VHFooter) holder).tv_date.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return list.size() + 2;
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

    private TabularReportVO getItem(int position) {
        return list.get(position);
    }

    class VHItem extends RecyclerView.ViewHolder {
        TextView tv_rly, tv_div, tv_sse_incharge, tv_sub_station, tv_assets_name, tv_rating, tv_make, tv_date, tv_details;
        LinearLayout linear_item, linear_details;
        View view_make, view_rating;

        public VHItem(View itemView) {
            super(itemView);
            tv_rly = itemView.findViewById(R.id.tv_rly);
            tv_div = itemView.findViewById(R.id.tv_div);
            tv_sse_incharge = itemView.findViewById(R.id.tv_sse_incharge);
            tv_sub_station = itemView.findViewById(R.id.tv_sub_station);
            tv_assets_name = itemView.findViewById(R.id.tv_assets_name);
            tv_rating = itemView.findViewById(R.id.tv_rating);
            tv_make = itemView.findViewById(R.id.tv_make);
            tv_date = itemView.findViewById(R.id.tv_date);
            tv_details = itemView.findViewById(R.id.tv_details);
            linear_item = itemView.findViewById(R.id.linear_item);
            linear_details = itemView.findViewById(R.id.linear_details);
            view_make = itemView.findViewById(R.id.view_make);
            view_rating = itemView.findViewById(R.id.view_rating);
        }
    }

    class VHHeader extends RecyclerView.ViewHolder {
        TextView tv_assets_type, tv_report_date, tv_railway, tv_division;

        public VHHeader(View itemView) {
            super(itemView);
            tv_assets_type = itemView.findViewById(R.id.tv_assets_type);
            tv_report_date = itemView.findViewById(R.id.tv_report_date);
            tv_railway = itemView.findViewById(R.id.tv_railway);
            tv_division = itemView.findViewById(R.id.tv_division);
        }
    }


    class VHFooter extends RecyclerView.ViewHolder {
        LinearLayout linear_footer;
        TextView tv_date;

        public VHFooter(View itemView) {
            super(itemView);
            linear_footer = itemView.findViewById(R.id.linear_footer);
            tv_date = itemView.findViewById(R.id.tv_date);
        }
    }
}