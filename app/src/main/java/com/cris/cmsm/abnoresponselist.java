package com.cris.cmsm;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cris.cmsm.database.DataHolder;
import com.cris.cmsm.models.request.GraphAPIRequest;
import com.cris.cmsm.models.response.Paramresponse;
import com.cris.cmsm.navcontrollers.DetailController;
import com.cris.cmsm.presenter.RequestPresenter;
import com.cris.cmsm.presenterview.ResponseView;
import com.cris.cmsm.util.CommonClass;
import com.cris.cmsm.util.Constants;

import java.util.ArrayList;

public class abnoresponselist extends AppCompatActivity implements ResponseView {
    private ArrayList<Paramresponse> ABRES;
    private ArrayList<String> Crew;
LinearLayout linearLayout,linearLayout2,abnrlistheader;
    private RecyclerView recyclerView;
    TextView txt_statemnt,dept;
    TextView action_bar_title;
    private ImageView iv_left, iv_middle, iv_right,iv_title_icon;
    int i=0;
    int count;
    CardViewProductAdapter cardViewProductAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abnoresponselist);
        linearLayout=(LinearLayout) findViewById(R.id.colorlayout);
        linearLayout2=(LinearLayout) findViewById(R.id.color2layout);
        abnrlistheader=(LinearLayout) findViewById(R.id.abnrlistheader);
        txt_statemnt = (TextView) findViewById(R.id.txt_statment);
        dept = (TextView) findViewById(R.id.dept);

        action_bar_title = (TextView) findViewById(R.id.action_bar_title);
        iv_left = (ImageView) findViewById(R.id.iv_left);
       ArrayList<String> back=new ArrayList <>();
        ABRES=new ArrayList <Paramresponse>();
        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        //recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setLayoutManager(layoutManager);


        iv_middle = (ImageView) findViewById(R.id.iv_middle);
        iv_right = (ImageView) findViewById(R.id.iv_right);

        iv_title_icon = findViewById(R.id.iv_title_icon);
        iv_title_icon.setImageResource(R.drawable.iv_back);
        iv_left.setVisibility(View.GONE);
        iv_middle.setVisibility(View.GONE);
        iv_right.setVisibility(View.VISIBLE);
        ArrayList<String>userparam=new ArrayList <>();
        iv_title_icon.setImageResource(R.drawable.iv_back);
        RequestPresenter requestPresenter = new RequestPresenter(this);
        GraphAPIRequest request = new GraphAPIRequest();
        abnrlistheader.setVisibility(View.GONE);
        txt_statemnt.setVisibility(View.GONE);
        iv_title_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(DataHolder.getAbnrType().equals("Submitted")) {
                    finish();
                }
                else {
                    Intent i = new Intent(abnoresponselist.this, Before_abnoresponselist_activity.class);
                    i.putExtra("object", back);
                    startActivity(i);
            }

            }
        });


        iv_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.iv_right:
                        showLogoutDialog(abnoresponselist.this, "Do you want to logout?", true);
                        break;

                }
            }
        });
        Bundle extra = getIntent().getExtras();
        if (extra != null) {
            final ArrayList <String> paralist = extra.getStringArrayList("object");
            back.add(paralist.get(0));
            back.add(paralist.get(1));
            action_bar_title.setText(paralist.get(1));
            request.setparamlist(paralist);
            if (DataHolder.getAbnrType()== "Pending") {
                txt_statemnt.setText("The List of Pending Abnormalities");

                requestPresenter.Request(request, "c", Constants.ABNORMALITYLIST);
            }
            if(DataHolder.getAbnrType()=="Submitted"){
                linearLayout.setVisibility(View.GONE);
                linearLayout2.setVisibility(View.GONE);
                txt_statemnt.setText("The List of Submitted Abnormalities");

                requestPresenter.Request(request, "c", Constants.GETABNORMALITYLISTSUBMITTED);
            }
        }

        //Log.d("length of datalist",""+ABRES.size());




    }

    @Override
    public void ResponseOk(Object object, int position) {

    if(object instanceof Paramresponse){
        System.out.println("Key sucess>>>>>>>>>>");
        Paramresponse paramresponse = (Paramresponse) object;
        System.out.println("Key sucess>>>>>>>>>>");
        if ((paramresponse.getVosList().isEmpty())) {
            System.out.println("No abnormality found");


        } else {
            int counter = 0;
            System.out.println("length" + (paramresponse.getVosList()).size());

            while (i < (paramresponse.getVosList()).size()) {
               System.out.println("key" + (paramresponse.getVosList()).get(i).getabnrNumber());
               System.out.println("key" + (paramresponse.getVosList()).get(i).getCrewId());
               System.out.println("key>>>>>>>>" + (paramresponse.getVosList()).get(i).getAppUserRemarksStts());


                String abnromalityNo = (paramresponse.getVosList()).get(i).getabnrNumber();
                String crewId = (paramresponse.getVosList()).get(i).getCrewId();
                String appRemarks=(paramresponse.getVosList()).get(i).getAppUserRemarksStts();

                count = counter + (i + 1);
                System.out.println("value - "+crewId+" --abnrNo-- "+abnromalityNo);
                Paramresponse pa=new Paramresponse(i+1,crewId,abnromalityNo,appRemarks);
                ABRES.add(pa);
                i++;

            }
i=0;

            txt_statemnt.setVisibility(View.VISIBLE);
            abnrlistheader.setVisibility(View.VISIBLE);

            dept.setText("Total Abnormalities = " + count);
            Log.d("length of data is",""+ABRES.size());
            cardViewProductAdapter = new CardViewProductAdapter(ABRES,this);
            recyclerView.setAdapter(cardViewProductAdapter);



        }



    }}
        private void showLogoutDialog ( final Activity context, String msg,final boolean isLogout){
            new AlertDialog.Builder(context).setCancelable(isLogout)
                    .setTitle(getResources().getString(R.string.cms))
                    .setMessage(msg)
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            if (isLogout) {
                                logOut();
                            }
                        }
                    })
                    .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                            Toast.makeText(getApplicationContext(), "Resume your work",
                                    Toast.LENGTH_SHORT).show();
                        }
                    })
                    .setIcon(R.drawable.icon_logo)
                    .show();
        }

        private void logOut () {
            //userLoginPreferences.clearUser();
            DataHolder.setLevel(0);
            CommonClass.goToNextScreen(abnoresponselist.this, DetailController.class, true, Constants.LOGIN_OPTIONS);


            Intent landing = new Intent(abnoresponselist.this, LandingActivity.class);
            landing.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                    | Intent.FLAG_ACTIVITY_CLEAR_TASK
                    | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(landing);
            overridePendingTransition(R.animator.slide_in_right, R.animator.slide_out_left);
            finish();
        }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) {
            if(resultCode == Activity.RESULT_OK){
                String result=data.getStringExtra("result");
                //cardViewProductAdapter.onActivityResult(1,2,data);
                System.out.println("Result final>>>>>>>>>>" +result);
                ABRES.clear();
                RequestPresenter requestPresenter = new RequestPresenter(this);
                GraphAPIRequest request = new GraphAPIRequest();
                ArrayList<String> para=new ArrayList <>();
                para.add(DataHolder.getRlycode());
                para.add(DataHolder.getRoleid());
                request.setparamlist(para);

                requestPresenter.Request(request,"c", Constants.ABNORMALITYLIST);
                System.out.println("Key sucess>>>>>>>>>>in abnoresponselist");

            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result
            }
        }
    }//onA


    @Override
    public void Error() {

    }

    @Override
    public void dismissProgress() {


    }

    @Override
    public void showProgress(String msg) {
        Toast.makeText(this,"Fetching Response",Toast.LENGTH_SHORT).show();


    }
    @Override
    public void onBackPressed() {


        finish();
    }

}

