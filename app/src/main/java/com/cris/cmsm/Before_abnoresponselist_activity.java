package com.cris.cmsm;

import android.app.Activity;
import android.app.slice.SliceItem;
import android.app.slice.SliceManager;
import android.app.slice.SliceSpec;
import android.arch.lifecycle.ViewModelStore;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
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
import com.cris.cmsm.util.UnitValueFormatter;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.DataSet;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.Highlight;

import java.util.ArrayList;

public class Before_abnoresponselist_activity extends AppCompatActivity implements ResponseView {
    TextView action_bar_title,Rly_code,Role_id,login_id,abnr_count,Role_idh,Rly_codeh,passwordresetstmnt
            ,imageir;
    //Button Password_reset_button;
    //ImageButton resetbutton;
    private ImageView iv_left, iv_middle, iv_right;
    private PieChart piechart;
    CardView cardView;
    AlertDialog.Builder builder;
    int i=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_before_abnoresponselist_activity);
        action_bar_title = (TextView) findViewById(R.id.action_bar_title);
        Rly_code = (TextView) findViewById(R.id.Rly_code);
        Rly_codeh = (TextView) findViewById(R.id.Rly_codeh);
        imageir=(TextView)findViewById(R.id.imageir);
        Role_id = (TextView) findViewById(R.id.Role_id);
        Role_idh = (TextView) findViewById(R.id.Role_idh);
        //passwordresetstmnt=(TextView)findViewById(R.id.passwordResetstmnt);
        //resetbutton=(ImageButton) findViewById(R.id.resetbutton);
        abnr_count=(TextView)findViewById(R.id.abnr_count);
        login_id = (TextView) findViewById(R.id.login_id);
        iv_middle = (ImageView) findViewById(R.id.iv_middle);
        iv_right = (ImageView) findViewById(R.id.iv_right);
        iv_left = (ImageView) findViewById(R.id.iv_left);
        //train_pic = (ImageView) findViewById(R.id.trainpic);
        //Password_reset_button=(Button)findViewById(R.id.password_reset_button);
        piechart=(PieChart)findViewById(R.id.pieChart);
        cardView=(CardView)findViewById(R.id.cardview);
        //lv=(ListView)findViewById(R.id.listview);
        iv_right.setVisibility(View.VISIBLE);
        imageir.setVisibility(View.GONE);
        RequestPresenter requestPresenter = new RequestPresenter(this);
        GraphAPIRequest request = new GraphAPIRequest();
        //blink();
        //Password_reset_button.setVisibility(View.GONE);
        cardView.setVisibility(View.GONE);
        piechart.setVisibility(View.GONE);

        abnr_count.setVisibility(View.GONE);
        Role_id.setVisibility(View.GONE);
        Rly_code.setVisibility(View.GONE);
        login_id.setVisibility(View.GONE);
        //passwordresetstmnt.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);
        /*passwordresetstmnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Before_abnoresponselist_activity.this,Password_reset_activity.class);
                i.putExtra("object",action_bar_title.getText().toString());
                startActivity(i);

            }
        });
*/
        /*resetbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Before_abnoresponselist_activity.this,Password_reset_activity.class);
                i.putExtra("object",action_bar_title.getText().toString());
                startActivity(i);
            }
        });*/
        //train_pic.setVisibility(View.GONE);
        /*Password_reset_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Before_abnoresponselist_activity.this,Password_reset_activity.class);
                i.putExtra("object",action_bar_title.getText().toString());
                startActivity(i);
            }
        });*/

        iv_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.iv_right:
                        showLogoutDialog(Before_abnoresponselist_activity.this, "Do you want to logout?", true);
                        break;
                }
            }
        });

        //lv=(ListView)findViewById(R.id.list_view);
        ArrayList<String> request1=new ArrayList <>();

        ArrayList <String> req=new ArrayList <>() ;

        Bundle extra = getIntent().getExtras();
        if (extra != null) {

            req = extra.getStringArrayList("object");
            request1.add(req.get(0));
            request1.add(req.get(1));
            request.setparamlist(request1);
            requestPresenter.Request(request,"c",Constants.GETABNORMALITYCOUNT);
            //Rly_code.setText("Division - " +(req.get(0)));
            //Role_id.setText("Dept - "+(req.get(1)));

            //login_id.setText("Username - "+(req.get(2)));
            action_bar_title.setText(req.get(1));
           piechart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
                @Override
                public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {
                    e.getXIndex();
                    System.out.println("X Index >>>>>>>>>>>>>>>>>>> " + e.getXIndex());

                    System.out.println(dataSetIndex);

                    if (e.getXIndex()==0) {
                        DataHolder.setAbnrType("Pending");
                        Intent i = new Intent(Before_abnoresponselist_activity.this, abnoresponselist.class);
                        i.putExtra("object", request1);
                        startActivity(i);

                    }
                    if (e.getXIndex()==1) {

                        DataHolder.setAbnrType("Submitted");
                        Intent i = new Intent(Before_abnoresponselist_activity.this, abnoresponselist.class);
                        i.putExtra("object", request1);
                        startActivity(i);
                        ;
                    }
                }

                @Override
                public void onNothingSelected() {

                }
            });

           /* lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView <?> parent, View view, int position, long id) {
                    if (id== 0) {
                        DataHolder.setAbnrType("Pending");
                        Intent i = new Intent(Before_abnoresponselist_activity.this, abnoresponselist.class);
                        i.putExtra("object", request1);
                        startActivity(i);

                    }
                    if(id==1){
                        DataHolder.setAbnrType("Submitted");
                        Intent i=new Intent(Before_abnoresponselist_activity.this,abnoresponselist.class);
                        i.putExtra("object",request1);
                        startActivity(i);;
                    }

                }
            });*/


        }




    }

    private void showLogoutDialog (final Activity context, String msg, final boolean isLogout){
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
        CommonClass.goToNextScreen(Before_abnoresponselist_activity.this, DetailController.class, true, Constants.LOGIN_OPTIONS);


        Intent landing = new Intent(Before_abnoresponselist_activity.this, LandingActivity.class);
        landing.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                | Intent.FLAG_ACTIVITY_CLEAR_TASK
                | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(landing);
        overridePendingTransition(R.animator.slide_in_right, R.animator.slide_out_left);
        finish();
    }


    @Override
    public void ResponseOk(Object object, int position) {
        if (object instanceof Paramresponse) {
            System.out.println("Key sucess>>>>>>>>>>");
            Paramresponse paramresponse = (Paramresponse) object;
            System.out.println("Key sucess>>>>>>>>>>");
            if ((paramresponse.getVosList().isEmpty())) {
                System.out.println("No abnormality found");

            } else {
                Role_id.setText(DataHolder.getRoleid());
                Rly_code.setText(DataHolder.getRlycode());
                login_id.setText(DataHolder.getFname());
                abnr_count.setVisibility(View.VISIBLE);
                System.out.println("Key sucess>>>>>>>>>>" + (paramresponse.getVosList().get(i).getAbnrCountPending()));
               // ArrayList<String>count=new ArrayList <>()
                ArrayList <Entry>count = new ArrayList();
                ArrayList <String> countstatment = new ArrayList<>();



                    //count.add(paramresponse.getVosList().get(i).getAbnrCountPending());
                    // count.add(paramresponse.getVosList().get(i + 1).getAbnrCountSubmitted());
                    count.add(new Entry(Float.valueOf(paramresponse.getVosList().get(i).getAbnrCountPending()), i));
                    //count.add(new Entry(Float.valueOf(0),i+1));
                    count.add(new Entry(Float.valueOf(paramresponse.getVosList().get(i + 1).getAbnrCountSubmitted()), i + 1));
                    System.out.println("AbnrCountPadding>>>>>>>>>>>" + (paramresponse.getVosList().get(i).getAbnrCountPending()));
                    System.out.println("AbnrCountSubmitted>>>>>>>>>>>" + (paramresponse.getVosList().get(i + 1).getAbnrCountSubmitted()));
                /*ArrayAdapter adapter = new ArrayAdapter <String>(this,android.R.layout.simple_list_item_1, count){

                    @Override
                    public View getView(int position, View convertView, ViewGroup parent) {
                      View view =  super.getView(position, convertView, parent);

                        TextView tv = (TextView) view.findViewById(android.R.id.text1);

                        // Set the text size 25 dip for ListView each item
                        tv.setTextSize(TypedValue.COMPLEX_UNIT_DIP,20);
                        return view;
                    }
                };*/
                    countstatment.add("Total Pending Abnormalities");
                    countstatment.add("Total Submitted Abnormalities");
                    PieDataSet pieDataSet = new PieDataSet(count, "");
                    PieData Data = new PieData(countstatment, pieDataSet);

                    piechart.setData(Data);
                    pieDataSet.setValueFormatter(new UnitValueFormatter(""));
                    pieDataSet.setValueTextSize(14);
                    pieDataSet.setColors(ColorTemplate.JOYFUL_COLORS);
                    piechart.animateXY(5000, 5000);

                   // lv.setAdapter(adapter);
                    piechart.setVisibility(View.VISIBLE);
                    cardView.setVisibility(View.VISIBLE);
                    abnr_count.setVisibility(View.VISIBLE);

                    //Password_reset_button.setVisibility(View.VISIBLE);
                   Role_id.setVisibility(View.VISIBLE);
                    Rly_code.setVisibility(View.VISIBLE);
                    login_id.setVisibility(View.VISIBLE);
                    imageir.setVisibility(View.VISIBLE);
                    //train_pic.setVisibility(View.VISIBLE);



            }
        }
    }

    @Override
    public void Error() {

    }

    @Override
    public void dismissProgress() {

    }

    @Override
    public void showProgress(String msg) {

    }
    @Override
    public void onBackPressed() {
        Intent landing = new Intent(Before_abnoresponselist_activity.this, LandingActivity.class);
        startActivity(landing);
        overridePendingTransition(R.animator.slide_in_right, R.animator.slide_out_left);
        finish();
    }

    /*private void blink(){
        final Handler handler = new Handler();
        new Thread(new Runnable() {
            @Override
            public void run() {
                int timeToBlink = 1000;    //in milissegunds
                try{Thread.sleep(timeToBlink);}catch (Exception e) {}
                handler.post(new Runnable() {
                    @Override
                    public void run() {

                        if(password_statement.getVisibility() == View.VISIBLE){
                            password_statement.setVisibility(View.INVISIBLE);
                        }else{
                            password_statement.setVisibility(View.VISIBLE);
                        }
                        blink();
                    }
                });
            }
        }).start();
    }*/
}
