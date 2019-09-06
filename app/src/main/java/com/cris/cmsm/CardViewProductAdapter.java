package com.cris.cmsm;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.cris.cmsm.database.DataHolder;
import com.cris.cmsm.models.request.GraphAPIRequest;
import com.cris.cmsm.models.response.Paramresponse;
import com.cris.cmsm.presenter.RequestPresenter;
import com.cris.cmsm.util.Constants;

import org.w3c.dom.Text;

import java.util.ArrayList;

import static android.media.CamcorderProfile.get;

public class CardViewProductAdapter extends RecyclerView.Adapter<CardViewProductAdapter.CardViewHolder> {
    final ArrayList<String> crew =new ArrayList <>();


    private ArrayList<Paramresponse> cardViewProductsList;
    private Context context;

    public CardViewProductAdapter(ArrayList<Paramresponse> cardViewProductsList,Context context) {
        this.cardViewProductsList = cardViewProductsList;
        this.context = context;
    }

    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        System.out.println("--------------- ENTRY - onCreateViewHolder ---------------");
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rootView = layoutInflater.inflate(R.layout.itemlayout,viewGroup,false);
        CardViewHolder cardViewHolder = new CardViewHolder(rootView);
        System.out.println("--------------- EXIT - onCreateViewHolder ---------------");
        return cardViewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull final CardViewHolder viewHolder, int i) {
        System.out.println("--------------- ENTRY - onBindViewHolder ---------------");
        Paramresponse product = cardViewProductsList.get(i);
        System.out.println("-sequence - " + product.getSequence());
        viewHolder.SN.setText("" + (product.getSequence()));

        // viewHolder.Crewid.setText("crew id");
        System.out.println("-crew id - " + product.getCrewId());
        viewHolder.Crewid.setText(product.getCrewId());
        System.out.println("-abnr no - " + product.getAbnormalityNo());
        viewHolder.Abnrno.setText(product.getAbnormalityNo());
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (DataHolder.getAbnrType().equals("Pending")) {

                    Intent intent = new Intent(context, Responsedetail.class);
                    crew.clear();
                    crew.add(viewHolder.Crewid.getText().toString());
                    crew.add(viewHolder.Abnrno.getText().toString());

                    intent.putExtra("object", crew);
                    //abnoresponselist origin = (abnoresponselist) context;
                    //origin.startActivityForResult(new Intent(context, Responsedetail.class), 1);
                    ((abnoresponselist) context).startActivityForResult(intent, 1);
                } if(DataHolder.getAbnrType().equals("Submitted")) {
                    crew.clear();
                    Intent intent = new Intent(context, Responsedetail.class);
                    crew.add(viewHolder.Crewid.getText().toString());
                    crew.add(viewHolder.Abnrno.getText().toString());

                    intent.putExtra("object", crew);
                    //abnoresponselist origin = (abnoresponselist) context;
                    //origin.startActivityForResult(new Intent(context, Responsedetail.class), 1);
                    ((abnoresponselist) context).startActivityForResult(intent, 1);
                }
            }

        });
        crew.clear();




            if(product.getAppRemarks().equals("R")){
               viewHolder.itemView.setBackgroundColor(context.getResources().getColor(R.color.colorCardGreen));
            }if(!product.getAppRemarks().equals("R")&& !product.getAppRemarks().equals("Y")) {
               viewHolder.itemView.setBackgroundColor(context.getResources().getColor(R.color.colorBlueGrey));

            }
        System.out.println("--------------- EXIT - onBindViewHolder ---------------");
    }
    public void onActivityResult(int requestCode,int resultcode,Intent data){
        Log.d("MyCardviewAdapter","on ActivityResult");

    }



    @Override
    public int getItemCount() {
        System.out.println("--------------- ENTRY - getItemCount ---------------");
        return cardViewProductsList.size();
    }

    class CardViewHolder extends RecyclerView.ViewHolder {

        TextView SN;
        TextView Crewid;
        TextView Abnrno;


        public CardViewHolder(@NonNull View itemView) {

            super(itemView);
            System.out.println("--------------- ENTRY - CardViewHolder ---------------");
            SN = (TextView)itemView.findViewById(R.id.SN);
            Crewid = (TextView)itemView.findViewById(R.id.Crewid);
            Abnrno = (TextView)itemView.findViewById(R.id.Abnrno);
            System.out.println("--------------- EXIT - CardViewHolder ---------------");
        }
    }

}
