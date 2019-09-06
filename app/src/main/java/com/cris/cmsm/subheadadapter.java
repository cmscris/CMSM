package com.cris.cmsm;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import java.util.ArrayList;

public class subheadadapter extends RecyclerView.Adapter<subheadadapter.CardViewHolder> {
    ArrayList<String> checkboxname;
    String data;
    Context context;

    public subheadadapter(ArrayList<String> checkboxname, Context context) {
        this.checkboxname = checkboxname;
        this.context = context;
    }

    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
       /* System.out.println("--------------- ENTRY - onCreateViewHolder ---------------");
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rootView = layoutInflater.inflate(R.layout.subheaditemlayout,viewGroup,false);
        CardViewHolder cardViewHolder = new CardViewHolder(rootView);
        System.out.println("--------------- EXIT - onCreateViewHolder ---------------");*/
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewHolder subheadViewHolder, int i) {
        System.out.println("--------------- ENTRY - onBindViewHolder ---------------");
        System.out.println("--------------- ENTRY - onBindViewHolder ---------------" +(checkboxname.get(i)));
        subheadViewHolder.checkBox.setText(checkboxname.get(i));
        System.out.println("--------------- EXIT - onBindViewHolder ---------------");
        if(subheadViewHolder.checkBox.isChecked()){
            subheadViewHolder.checkBox.setBackgroundColor(context.getResources().getColor(R.color.colorCardOrange));
            data=subheadViewHolder.checkBox.getText().toString();
        }


    }

    @Override
    public int getItemCount() {
        return checkboxname.size();
    }
    class CardViewHolder extends RecyclerView.ViewHolder {

        CheckBox checkBox;


        public CardViewHolder(@NonNull View itemView) {

            super(itemView);
            System.out.println("--------------- ENTRY - CardViewHolder ---------------");
//            checkBox= (CheckBox) itemView.findViewById(R.id.cb1);
            System.out.println("--------------- EXIT - CardViewHolder ---------------");
        }
    }

}
