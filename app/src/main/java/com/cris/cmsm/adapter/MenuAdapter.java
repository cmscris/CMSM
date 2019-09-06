package com.cris.cmsm.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cris.cmsm.R;
import com.cris.cmsm.interfaces.RecyclerItemListener;
import com.cris.cmsm.models.MenuModel;
import com.cris.cmsm.util.FontFamily;

import java.util.List;

/**
 * Created by Anand.kumar on 10/24/2016.
 */
public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.ViewHolder> {
    List<MenuModel> list;
    Context context;
    RecyclerItemListener listener;
    Typeface font;

    public MenuAdapter(Context context, RecyclerItemListener listener, List<MenuModel> list) {
        this.context = context;
        font = new FontFamily(context).getRegular();
        this.list = list;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final MenuModel model = list.get(position);
        holder.linear_card.setBackgroundColor(context.getResources().getColor(model.getCardColor()));
        holder.img_card.setImageResource(model.getIcon());
        holder.tv_card.setText(model.getMenuTitle());
        holder.tv_card.setTypeface(font);
        holder.linear_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClickListener(model);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout linear_card;
        ImageView img_card;
        TextView tv_card;

        public ViewHolder(View itemView) {
            super(itemView);
            linear_card = itemView.findViewById(R.id.linear_card);
            img_card = itemView.findViewById(R.id.img_card);
            tv_card = itemView.findViewById(R.id.tv_card);

        }
    }

}
