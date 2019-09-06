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
import com.cris.cmsm.models.response.IrregularCrew;

import java.util.List;


/**
 * Created by rangasanju on 22/07/2019.
 */




public class IrregularCrewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;
    private static final int TYPE_FOOTER = 2;
    private Context context;
    private List<IrregularCrew> list;
    private OnItemClickListener listener;
    private ReportHeaderView reportHeaderView;

    public IrregularCrewAdapter(Context context, ReportHeaderView reportHeaderView, OnItemClickListener listener, List<IrregularCrew> list) {
        this.context = context;
        this.list = list;
        this.reportHeaderView = reportHeaderView;
        this.listener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_ITEM) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.irregular_crew_report, parent, false);
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
            final IrregularCrew model = getItem(pos);

            item.tv_location.setText(model.getLobby());
            item.tv_notAckGt24.setText(model.getNotAckGt24());
            item.tv_notSignonGt24.setText(model.getNotSignonGt24());
            item.tv_osRestGt72.setText(model.getOsRestGt72());
            item.tv_onTrainGt24.setText(model.getOnTrainGt24());
            item.tv_sysNonRun.setText(model.getSysNonRun());
            item.tv_nonRunGt7Days.setText(model.getNonRunGt7Days());
            item.tv_crewInRestGt36.setText(model.getCrewInRestGt36());
            item.tv_unplanned.setText(model.getUnplanned());
            item.tv_approvalPendOnOff.setText(model.getApprovalPendOnOff());
            item.tv_missedDutyCoachingLink48.setText(model.getMissedDutyCoachingLink48());
            item.tv_missedDutyShuntingLink48.setText(model.getMissedDutyShuntingLink48());




            if (pos == 0) {


                item.tv_location.setTypeface(null, Typeface.BOLD);
                item.tv_notAckGt24.setTypeface(null, Typeface.BOLD);
                item.tv_notSignonGt24.setTypeface(null, Typeface.BOLD);
                item.tv_osRestGt72.setTypeface(null, Typeface.BOLD);
                item.tv_onTrainGt24.setTypeface(null, Typeface.BOLD);
                item.tv_sysNonRun.setTypeface(null, Typeface.BOLD);
                item.tv_nonRunGt7Days.setTypeface(null, Typeface.BOLD);
                item.tv_crewInRestGt36.setTypeface(null, Typeface.BOLD);
                item.tv_unplanned.setTypeface(null, Typeface.BOLD);
                item.tv_approvalPendOnOff.setTypeface(null, Typeface.BOLD);
                item.tv_missedDutyCoachingLink48.setTypeface(null, Typeface.BOLD);
                item.tv_missedDutyShuntingLink48.setTypeface(null, Typeface.BOLD);



            } else {

                item.tv_location.setTypeface(null, Typeface.BOLD);
                item.tv_notAckGt24.setTypeface(null);
                item.tv_notSignonGt24.setTypeface(null);
                item.tv_osRestGt72.setTypeface(null);
                item.tv_onTrainGt24.setTypeface(null);
                item.tv_sysNonRun.setTypeface(null);
                item.tv_nonRunGt7Days.setTypeface(null);
                item.tv_crewInRestGt36.setTypeface(null);
                item.tv_unplanned.setTypeface(null);
                item.tv_approvalPendOnOff.setTypeface(null);
                item.tv_missedDutyCoachingLink48.setTypeface(null);
                item.tv_missedDutyShuntingLink48.setTypeface(null);


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
        TextView tv_location,tv_notAckGt24,tv_notSignonGt24,tv_osRestGt72,tv_onTrainGt24,tv_sysNonRun,tv_nonRunGt7Days,tv_crewInRestGt36,tv_unplanned,
                 tv_approvalPendOnOff,tv_missedDutyCoachingLink48,tv_missedDutyShuntingLink48;

        public VHItem(View itemView) {
            super(itemView);

            tv_location = itemView.findViewById(R.id.tv_location);
            tv_notAckGt24 = itemView.findViewById(R.id.tv_notAckGt24);
            tv_notSignonGt24 = itemView.findViewById(R.id.tv_notSignonGt24);
            tv_osRestGt72 = itemView.findViewById(R.id.tv_osRestGt72);
            tv_onTrainGt24 = itemView.findViewById(R.id.tv_onTrainGt24);
            tv_sysNonRun = itemView.findViewById(R.id.tv_sysNonRun);
            tv_nonRunGt7Days = itemView.findViewById(R.id.tv_nonRunGt7Days);
            tv_crewInRestGt36 = itemView.findViewById(R.id.tv_crewInRestGt36);
            tv_unplanned = itemView.findViewById(R.id.tv_unplanned);
            tv_approvalPendOnOff = itemView.findViewById(R.id.tv_approvalPendOnOff);
            tv_missedDutyCoachingLink48 = itemView.findViewById(R.id.tv_missedDutyCoachingLink48);
            tv_missedDutyShuntingLink48 = itemView.findViewById(R.id.tv_missedDutyShuntingLink48);






        }
    }

    private IrregularCrew getItem(int position) {
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
