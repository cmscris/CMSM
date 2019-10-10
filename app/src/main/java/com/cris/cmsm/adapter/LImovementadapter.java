package com.cris.cmsm.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cris.cmsm.R;
import com.cris.cmsm.fragments.CalenderFragment;
import com.cris.cmsm.fragments.LIactivityFragment;
import com.cris.cmsm.interfaces.OnItemClickListener;
import com.cris.cmsm.models.ReportHeaderView;
import com.cris.cmsm.models.response.LICrewMonitoredResponse;

import java.util.ArrayList;
import java.util.List;

public class LImovementadapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context myContext;
    ArrayList<String> list;
    private OnItemClickListener listener;
    public LImovementadapter(Context context,OnItemClickListener listener, ArrayList<String> list) {
        this.myContext = context;
        this.list = list;
        this.listener = listener;
    }




    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.limov_fill_detail, viewGroup, false);
        return new VHItem(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        if(viewHolder instanceof VHItem){
            VHItem item = (VHItem) viewHolder;
            item.tv_fromdt.setText(list.get(i));
            item.tv_todt.setText(list.get(i));
            item.tv_fromsttn.setText(list.get(i));
            item.tv_tosttn.setText(list.get(i));
            item.tv_loco.setText(list.get(i));
            item.tv_train.setText(list.get(i));
            item.tv_remarks.setText(list.get(i));

        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
class VHItem extends RecyclerView.ViewHolder {
    TextView tv_fromdt, tv_todt, tv_frmtime, tv_totime, tv_loco, tv_train, tv_remarks, tv_fromsttn, tv_tosttn;

    public VHItem(View itemView) {

        super(itemView);
        tv_fromdt= (TextView) itemView.findViewById(R.id.tv_fromdt);
        tv_todt = (TextView) itemView.findViewById(R.id.tv_todt);
        tv_frmtime = (TextView) itemView.findViewById(R.id.tv_frmtime);
        tv_totime = (TextView) itemView.findViewById(R.id.tv_totime);
        tv_fromsttn= (TextView) itemView.findViewById(R.id.tv_fromsttn);
        tv_tosttn = (TextView) itemView.findViewById(R.id.tv_tosttn);
        tv_loco = (TextView) itemView.findViewById(R.id.tv_loco);
        tv_train=(TextView)itemView.findViewById(R.id.tv_train);
        tv_remarks=(TextView)itemView.findViewById(R.id.tv_remarks);

    }
}
