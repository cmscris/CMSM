package com.cris.cmsm;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Spannable;
import android.text.method.MovementMethod;
import android.text.method.ScrollingMovementMethod;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.cris.cmsm.database.DataHolder;
import com.cris.cmsm.models.request.GraphAPIRequest;
import com.cris.cmsm.models.response.LoginIfoVO;
import com.cris.cmsm.models.response.Paramresponse;
import com.cris.cmsm.models.response.Remarksresponse;
import com.cris.cmsm.navcontrollers.DetailController;
import com.cris.cmsm.prefrences.UserLoginPreferences;
import com.cris.cmsm.presenter.RequestPresenter;
import com.cris.cmsm.presenterview.ResponseView;
import com.cris.cmsm.util.CommonClass;
import com.cris.cmsm.util.Constants;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.StringTokenizer;

public class Responsedetail extends AppCompatActivity implements ResponseView {
    TextView action_bar_title, abnr, crewid, abdetails, crewname, fr_sttn, to_sttn, km_frm, KM_to, abdes,
            locono, traino, time, section, frwrdloco, frwrdtime, frwrdremark,appremarks,remark_statement;
    EditText remarks;
    private ImageView iv_left, iv_middle, iv_right,iv_title_icon;
    Button submit, reset;
    int i = 0;
    private LoginIfoVO loginIfoVO;
    private UserLoginPreferences userLoginPreferences;
    AlertDialog.Builder builder;
    StringBuilder sb =new StringBuilder();
    ScrollView scrollViewremarks;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_responsedetail);
        action_bar_title = (TextView) findViewById(R.id.action_bar_title);
        builder = new AlertDialog.Builder(this);
        abnr = (TextView) findViewById(R.id.abnr);
        remark_statement=(TextView)findViewById(R.id.remark_stmt);
        time = (TextView) findViewById(R.id.Time);
        crewid = (TextView) findViewById(R.id.crewid);
        remarks = (EditText) findViewById(R.id.remarks);
        abdes = (TextView) findViewById(R.id.abdes);
        section = (TextView) findViewById(R.id.section);
        frwrdloco = (TextView) findViewById(R.id.frwrdloco);
        frwrdremark = (TextView) findViewById(R.id.frwrdremark);
        frwrdtime = (TextView) findViewById(R.id.frwrdtime);
        abdetails = (TextView) findViewById(R.id.abdetails);
        locono = (TextView) findViewById(R.id.locono);
        traino = (TextView) findViewById(R.id.Traino);
        crewname = (TextView) findViewById(R.id.crewname);
        fr_sttn = (TextView) findViewById(R.id.frm_sttn);
        to_sttn = (TextView) findViewById(R.id.to_sttn);
        km_frm = (TextView) findViewById(R.id.KM_from);
        KM_to = (TextView) findViewById(R.id.KM_to);
        submit = (Button) findViewById(R.id.submit);
        reset = (Button) findViewById(R.id.Reset);
        appremarks=(TextView)findViewById(R.id.appremark);
        ArrayList <String> see = new ArrayList <>();
        RequestPresenter requestPresenter = new RequestPresenter(this);
        GraphAPIRequest request = new GraphAPIRequest();
        ArrayList <String> req=new ArrayList <>();




        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                remarks.setText("");
            }
        });

        iv_left = (ImageView) findViewById(R.id.iv_left);
        userLoginPreferences = new UserLoginPreferences(this);
        loginIfoVO = userLoginPreferences.getLoginUser();
        action_bar_title.setText(loginIfoVO.getRoleid());
        abresponse abresponse = new abresponse();

        iv_middle = (ImageView) findViewById(R.id.iv_middle);
        iv_right = (ImageView) findViewById(R.id.iv_right);

        iv_title_icon = findViewById(R.id.iv_title_icon);
        iv_title_icon.setImageResource(R.drawable.iv_back);
        iv_left.setVisibility(View.GONE);
        iv_middle.setVisibility(View.GONE);
        iv_right.setVisibility(View.VISIBLE);
        iv_title_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>");
                Toast.makeText(Responsedetail.this, "Go Back", Toast.LENGTH_SHORT).show();
                finish();
                //DataHolder.getInstance().getParamresponse().toString();
                System.out.println("%%%%%%%%%%%%%%%%%%%");

            }
        });
        if(DataHolder.getAbnrType().equals("Submitted")){
    remark_statement.setVisibility(View.GONE);
    remarks.setVisibility(View.GONE);
    reset.setVisibility(View.GONE);
    submit.setVisibility(View.GONE);
}

        Bundle extra = getIntent().getExtras();
        if (extra != null) {

            req = extra.getStringArrayList("object");
            System.out.println("Request is>>>of" + req);


String f=req.get(0);
if(f!=null){

           see.add(req.get(0));
           see.add(req.get(1));
}
           else{
    throw new IllegalArgumentException("The argument cannot be null");
           }
            see.add(DataHolder.getUserid());
            System.out.println("Request is>>>" + req);
            request.setparamlist(req);
            requestPresenter.Request(request, "c", Constants.ABNORMALITY_DETAILS);


        }



            submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String remark = remarks.getText().toString();
                    if (remark.equals("")) {
                        Toast.makeText(getApplicationContext(), "Please enter Remarks",
                                Toast.LENGTH_SHORT).show();
                    } else {

                        builder.setMessage(R.string.dialog_message).setTitle(R.string.dialog_title);

                        //Setting message manually and performing action on button click
                        builder.setMessage("Do you want to submit ?")
                                .setCancelable(false)
                                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {

                                        remarks.setFocusable(false);


                                        //if(remarks.isFocused()){
                                        see.add(remark);
                                        request.setparamlist(see);

                                        System.out.println("Request is>>>see" + see);
                                        requestPresenter.Request(request, "c", Constants.GETREMARKS);
                                    }


                                })
                                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        //  Action for 'NO' Button
                                        dialog.cancel();
                                        Toast.makeText(getApplicationContext(), "Resume your work",
                                                Toast.LENGTH_SHORT).show();
                                    }
                                });
                        //Creating dialog box
                        AlertDialog alert = builder.create();
                        //Setting the title manually
                        alert.setTitle(getResources().getString(R.string.cms));
                        alert.show();
                        alert.setIcon(R.drawable.icon_logo);
                    }
                }
            });




        iv_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.iv_right:
                        showLogoutDialog(Responsedetail.this, "Do you want to logout?", true);
                        break;
                }
            }

            private void showLogoutDialog(final Activity context, String msg, final boolean isLogout) {
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

            private void logOut() {
                //userLoginPreferences.clearUser();
                DataHolder.setLevel(0);
                CommonClass.goToNextScreen(Responsedetail.this, DetailController.class, true, Constants.LOGIN_OPTIONS);


                Intent landing = new Intent(Responsedetail.this, LandingActivity.class);
                landing.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                        | Intent.FLAG_ACTIVITY_CLEAR_TASK
                        | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(landing);
                overridePendingTransition(R.animator.slide_in_right, R.animator.slide_out_left);
                finish();
            }
        });

    }

    @Override
    public void ResponseOk(Object object, int position) {
        Paramresponse paramresponse;
        if (object instanceof Paramresponse) {
            System.out.println("Key sucess>>>>>>>>>>");
            paramresponse = (Paramresponse) object;
            if ((paramresponse.getVosList().isEmpty())) {
                    System.out.println("No abnormality found");

                } else {

                    System.out.println("Response is here" + (paramresponse.getVosList().get(i).getCrewName()));
                    crewname.setText("Crew Name-" + (paramresponse.getVosList().get(i).getCrewName()));
                    crewid.setText("Crew ID-" + (paramresponse.getVosList().get(i).getCrewId()));
                    abnr.setText("Abnr Number-" + (paramresponse.getVosList().get(i).getabnrNumber()));
                   //if(paramresponse.getVosList().get(i).getappUserRemarks().isEmpty()) {
                        abdes.setText(paramresponse.getVosList().get(i).getAbnrDescription());
                    //}
                String z= paramresponse.getVosList().get(i).getappUserRemarks();
                if(z.isEmpty()) {
                    appremarks.setVisibility(View.GONE);
                    appremarks.setText("");

                }
                else{

                System.out.println("Response is here" + (paramresponse.getVosList().get(i).getappUserRemarks()));
                String[]appremark=z.split("@");
                for(String apremark:appremark){
                    sb.append(apremark +"\n");
                    appremarks.setText("Remarks-" +sb);
                }
                appremarks.setMovementMethod(new ScrollingMovementMethod());
                }
              // String[] arrOfStr = z.split("@", 5);

                //for (String a : arrOfStr){
                    //sb.append(a);
                   //System.out.println(a);
                   // appremark.setText(sb);
                 //}


            fr_sttn.setText("From Sttn-" + (paramresponse.getVosList().get(i).getAbnrFromSttn()));
                    to_sttn.setText("To Sttn-" + (paramresponse.getVosList().get(i).getAbnrToSttn()));
                    km_frm.setText("Km Start-" + (paramresponse.getVosList().get(i).getKmStart()));
                    KM_to.setText("Km End-" + (paramresponse.getVosList().get(i).getKmEnd()));
                    section.setText("Section-" + (paramresponse.getVosList().get(i).getAbnrSection()));
                    traino.setText("Train No.-" + (paramresponse.getVosList().get(i).getAbnrTrainNo()));
                    locono.setText("Loco No.-" + (paramresponse.getVosList().get(i).getAbnrLocoNo()));
                    time.setText("Time:" + (paramresponse.getVosList().get(i).getAbnrTime()));
                    if ((paramresponse.getVosList().get(i).getAbnrFrwdLoc()).isEmpty() && (paramresponse.getVosList().get(i).getAbnrFrwdRemarks()).isEmpty() && (paramresponse.getVosList().get(i).getAbnrFrwdTime()).isEmpty()) {
                        frwrdtime.setText("");
                        frwrdloco.setText("");

                        frwrdremark.setText("");
                    } else {
                        frwrdtime.setText("FrwrdTime-" + (paramresponse.getVosList().get(0).getAbnrFrwdTime()));
                        frwrdremark.setText("FrwrdRemarks-" + (paramresponse.getVosList().get(0).getAbnrFrwdRemarks()));
                        frwrdloco.setText("FrwrdBy-" + (paramresponse.getVosList().get(0).getAbnrFrwdLoc()));

                    }



                }


            }
            if(object instanceof Remarksresponse){
                Remarksresponse remarksresponse=(Remarksresponse)object;
                String g=remarksresponse.getVosList().get(0).getResponseMessage();
                if(g.contains("RECORD SUCCESSFULLY UPDATED")){
                System.out.println("Key sucess response remarks is>>>>>>>>>>   "+g);
                Toast.makeText(getApplicationContext(), "Data is finally saved",
                        Toast.LENGTH_SHORT).show();
                Intent returnIntent = new Intent();
                returnIntent.putExtra("result",g);
                setResult(Activity.RESULT_OK,returnIntent);
                finish();
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
}
