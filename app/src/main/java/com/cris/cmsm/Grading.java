package com.cris.cmsm;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TableLayout;
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
import com.cris.cmsm.widget.JitinRLSlidingLayout;

import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import static com.cris.cmsm.util.Constants.CrewCounselling;

public class Grading extends AppCompatActivity implements TextWatcher, AdapterView.OnItemSelectedListener, ResponseView {
    TextView tv_crewTotal, tv_crew_name, tv_duedatehead, tv_crew_id, tv_ondate1, tv_Date_Monitored,tv_crewgrade ,tv_maxmark,
            tv_DrivingOperatingTechnique, tv_marks, tv_Knowledge, tv_marks2, tv_Technical, tv_marks3, tv_Personal, tv_marks4,
            tv_Accident, tv_marks5, tv_total, tv_score6,tv_constype;
    TextInputLayout Remark;
    LinearLayout gradingformlayout;
    TableLayout formtablelayout,formtotallayout,consformlayout;
    TableLayout gradingdetail_layout;
    private TextView action_bar_title;
    Spinner spn_CrewName,spn_constype;
    DatePickerDialog picker;
    private ImageView iv_left, iv_right, iv_title_icon;
    private LoginIfoVO loginInfoModel;
    int a1, a2, a3, a4, a5,total, pickdate;
    NumberFormat format;
    String strEnteredVal, pickmonth,selected_spinner,Crewid,crewnameselected,Crewgrade,Crewlastgr_dt,Crewlasttotal,counselling_type;
    String grade="";
    private ScrollView scrollView;
    private UserLoginPreferences userLoginPreferences;
    private CommonClass commonClass;
    EditText et_score, et_score2, et_score3, et_score4, et_score5, et_score8;
    Button bt_clear, bt_save;
    ArrayList <String> Crewname,getCrewlistdata,crewid,crewgrade,crewlastgradedate,crewlasttotal,savelist,constype,lastcounseldt,lastftpltdt;
    RequestPresenter requestPresenter;
    GraphAPIRequest request;
    int i=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grading);
        action_bar_title = (TextView) findViewById(R.id.action_bar_title);
        iv_title_icon = findViewById(R.id.iv_title_icon);
        gradingdetail_layout=(TableLayout)findViewById(R.id.gradingdetail_layout);
        gradingformlayout=(LinearLayout)findViewById(R.id.gradingformlayout);
        gradingdetail_layout.setVisibility(View.GONE);
        iv_title_icon.setImageResource(R.drawable.iv_back);
        scrollView = findViewById(R.id.scrollView);
        iv_left = (ImageView) findViewById(R.id.iv_left);
        iv_right = (ImageView) findViewById(R.id.iv_right);
        tv_crewTotal=(TextView)findViewById(R.id.crewTotal);
        formtablelayout=(TableLayout)findViewById(R.id.formtablelayout);
        formtotallayout=(TableLayout)findViewById(R.id.formtotallayout);
        consformlayout=(TableLayout)findViewById(R.id.consformlayout);
        //tv_grading = (TextView) findViewById(R.id.grading);
        tv_crew_id=(TextView)findViewById(R.id.crew_id);
        tv_constype=(TextView)findViewById(R.id.constype);
        tv_crewgrade=(TextView)findViewById(R.id.crewgrade);
        spn_constype=(Spinner) findViewById(R.id.spn_constype);
        spn_CrewName = (Spinner) findViewById(R.id.spn_CrewName);
        tv_crew_name = (TextView) findViewById(R.id.crew_name);
        tv_duedatehead = (TextView) findViewById(R.id.duedatehead);
        tv_Date_Monitored = (TextView) findViewById(R.id.Date_Monitored);
        tv_DrivingOperatingTechnique = (TextView) findViewById(R.id.DrivingOperatingTechnique);
        tv_marks = (TextView) findViewById(R.id.marks);
        tv_maxmark = (TextView)findViewById(R.id.maxmark);
        tv_Knowledge = (TextView) findViewById(R.id.Knowledge);
        tv_marks2 = (TextView) findViewById(R.id.marks2);
        tv_Technical = (TextView) findViewById(R.id.Technical);
        tv_marks3 = (TextView) findViewById(R.id.marks3);
        tv_Personal = (TextView) findViewById(R.id.Personal);
        tv_marks4 = (TextView) findViewById(R.id.marks4);
        tv_Accident = (TextView) findViewById(R.id.Accident);
        tv_marks5 = (TextView) findViewById(R.id.marks5);
        tv_total = (TextView) findViewById(R.id.total);
        Remark = (TextInputLayout) findViewById(R.id.Remark);
        tv_ondate1 = (TextView) findViewById(R.id.ondate1);
        et_score = (EditText) findViewById(R.id.score);
        et_score2 = (EditText) findViewById(R.id.score2);
        et_score3 = (EditText) findViewById(R.id.score3);
        et_score4 = (EditText) findViewById(R.id.score4);
        et_score5 = (EditText) findViewById(R.id.score5);
        tv_score6 = (TextView) findViewById(R.id.score6);
        et_score8 = (EditText) findViewById(R.id.score8);
        bt_clear = (Button) findViewById(R.id.clear);
        bt_save = (Button) findViewById(R.id.save);
        userLoginPreferences = new UserLoginPreferences(Grading.this);
        loginInfoModel = userLoginPreferences.getLoginUser();
        tv_score6.setText("");
        gradingformlayout.setVisibility(View.VISIBLE);
        format = new DecimalFormat("00");
        et_score2.addTextChangedListener(this);
        et_score3.addTextChangedListener(this);
        et_score4.addTextChangedListener(this);
        et_score5.addTextChangedListener(this);
        et_score.addTextChangedListener(this);
        requestPresenter = new RequestPresenter(this);
        request = new GraphAPIRequest();
        crewid=new ArrayList <>();
        crewgrade=new ArrayList <>();
        savelist=new ArrayList <>();
        crewlasttotal=new ArrayList <>();
        constype=new ArrayList <>();
        commonClass=new CommonClass(Grading.this);
        et_score.setFocusable(false);
        et_score2.setFocusable(false);
        et_score3.setFocusable(false);
        et_score4.setFocusable(false);
        et_score5.setFocusable(false);
        et_score8.setFocusable(false);
        bt_save.setVisibility(View.GONE);
        bt_clear.setVisibility(View.GONE);
        Crewname=new ArrayList <>();
        lastcounseldt = new ArrayList <>();
        lastftpltdt = new ArrayList <>();
        Crewname.add("SELECT");
        getCrewlistdata=new ArrayList<>();
        getCrewlistdata.add(loginInfoModel.getLoginid());
       // request.setparamlist(getCrewlistdata);
        //requestPresenter.Request(request,"Please wait",Constants.GET_CREWLIST);
        if(DataHolder.getType()==Constants.CrewCounselling){
            System.out.println(">>>>>>>>>>insideCounsellinglayout");
            tv_duedatehead.setText("Last Counselling date -");
            tv_Date_Monitored.setText("Select Date");
            tv_crewTotal.setVisibility(View.GONE);
            consformlayout.setVisibility(View.VISIBLE);
            tv_maxmark.setVisibility(View.GONE);
            formtablelayout.setVisibility(View.GONE);
            formtotallayout.setVisibility((View.GONE));
            constype.add("SELECT");
            constype.add("Counselling");
            constype.add("FP/Monitoring");
            spn_constype.setOnItemSelectedListener(this);
            ArrayAdapter cons_type = new ArrayAdapter(this, android.R.layout.simple_spinner_item, constype);
            cons_type.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            //Setting the ArrayAdapter data on the Spinner
            spn_constype.setAdapter(cons_type);
            request.setparamlist(getCrewlistdata);
            requestPresenter.Request(request,"Please wait",Constants.GET_CREWLIST_LI_COUNSELLING);

        }
        else if(DataHolder.getType()==Constants.Grading){
            consformlayout.setVisibility(View.GONE);
            System.out.println(">>>>>>>>>>insideGradinglayout");
            request.setparamlist(getCrewlistdata);
            requestPresenter.Request(request,"Please wait",Constants.GET_CREWLIST_LI_GRADING);
        }
        if(!CommonClass.checkInternetConnection(Grading.this)) {
            commonClass.showToast("No internet available.");
        }
        tv_ondate1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int years = cldr.get(Calendar.YEAR);
                Calendar weekBackDate=Calendar.getInstance();
                weekBackDate.add(Calendar.DAY_OF_MONTH,-7);
                Date minDate = weekBackDate.getTime();

                // date picker dialog
                picker = new DatePickerDialog(Grading.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                System.out.println(">>>>>>>>>>>>>>>spinner select"+selected_spinner);
                                if (selected_spinner.equals("SELECT")) {
                                    tv_ondate1.setText("dd-mm-yyyy");
                                    System.out.println(">>>>>>>>>>>>>>>spinner select"+selected_spinner);
                                    commonClass.showToast("Please select Crew first");
                                } else {
                                    pickdate = dayOfMonth;
                                    String tvdate = format.format(dayOfMonth);
                                    pickmonth = format.format(monthOfYear + 1);
                                    tv_ondate1.setText(tvdate + "-" + (pickmonth) + "-" + year);

                                }
                            }
                        }, years, month, day);
                picker.getDatePicker().setMinDate(minDate.getTime());
                picker.getDatePicker().setMaxDate(System.currentTimeMillis());
                //picker.setCanceledOnTouchOutside(true);
                picker.show();

            }
        });

        bt_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_score8.setText("");
            }
        });






        action_bar_title.setText("CMS- " + loginInfoModel.getFname());

        bt_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(DataHolder.getType()==Constants.Grading) {
                    if (et_score.getText().toString().equals("") || et_score2.getText().toString().equals("") || et_score3.getText().toString().equals("") || et_score4.getText().toString().equals("") || et_score5.getText().toString().equals("")) {
                        commonClass.showToast("Please enter Score");
                    } else if (tv_score6.getText().toString().equals("")) {
                        commonClass.showToast("Please GET Total for saving data");
                    } else if (et_score8.getText().toString().equals("")) {
                        commonClass.showToast(("Please enter Remarks"));
                    } else if (tv_ondate1.getText().toString().equals("dd-mm-yyyy")) {
                        commonClass.showToast(("Please enter Date Monitored"));
                    } else {
                        total = Integer.parseInt(tv_score6.getText().toString());
                        savelist.add(loginInfoModel.getLoginid());
                        savelist.add(Crewid);
                        savelist.add(tv_ondate1.getText().toString());
                        savelist.add(et_score.getText().toString());
                        savelist.add(et_score2.getText().toString());
                        savelist.add(et_score3.getText().toString());
                        savelist.add(et_score4.getText().toString());
                        savelist.add(et_score5.getText().toString());
                        savelist.add(et_score8.getText().toString());
                        if ((total >= 80)) {
                            grade = "A";
                        } else if ((total < 80) && (total >= 60)) {
                            grade = "B";
                        } else if ((total < 60) && (total >= 50)) {
                            grade = "C";
                        } else {
                            grade = "D";
                        }
                    }

                    request.setparamlist(savelist);
                    requestPresenter.Request(request, "Saving Data", Constants.SAVE_LI_GRADING);
                }
                else if(DataHolder.getType()==Constants.CrewCounselling){
                    if (et_score8.getText().toString().equals("")) {
                        commonClass.showToast(("Please enter Remarks"));
                    } else if (tv_ondate1.getText().toString().equals("dd-mm-yyyy")) {
                        commonClass.showToast(("Please enter Date Monitored"));
                    }
                    savelist.add(loginInfoModel.getLoginid());
                    savelist.add(Crewid);
                    savelist.add(tv_ondate1.getText().toString());
                    savelist.add(counselling_type);
                    savelist.add(et_score8.getText().toString());
                    System.out.println("Counselling detail"+savelist);
                    request.setparamlist(savelist);
                    requestPresenter.Request(request, "Saving Data", Constants.SAVE_LI_COUNSELLING);
                }


            }
        });
        iv_title_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    finish();
            }
        });
        iv_right.setVisibility(View.VISIBLE);
        iv_left.setVisibility(View.GONE);
        iv_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.iv_right:
                        showLogoutDialog(Grading.this, "Do you want to logout?", true);
                        break;
                }
            }
        });
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
        CommonClass.goToNextScreen(Grading.this, DetailController.class, true, Constants.LOGIN_OPTIONS);


        Intent landing = new Intent(Grading.this, LandingActivity.class);
        landing.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                | Intent.FLAG_ACTIVITY_CLEAR_TASK
                | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(landing);
        overridePendingTransition(R.animator.slide_in_right, R.animator.slide_out_left);
        finish();
    }


    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (et_score.getText().hashCode() == s.hashCode()) {
            strEnteredVal = et_score.getText().toString();
            tv_score6.setText(addnumber());
            if (!strEnteredVal.equals("")) {
                int num = Integer.parseInt(strEnteredVal);
                if (num <= Integer.parseInt(tv_marks.getText().toString())) {
                    System.out.println(">>>>>>>>>>>>>>>>>>insideifscore");
                    a1 = num;
                } else {
                    et_score.setText("");
                }
            }

        }
        if (et_score2.getText().hashCode() == s.hashCode()) {
            strEnteredVal = et_score2.getText().toString();
            tv_score6.setText(addnumber());
            if (!strEnteredVal.equals("")) {
                int num = Integer.parseInt(strEnteredVal);
                if (num <= Integer.parseInt(tv_marks2.getText().toString())) {
                    System.out.println(">>>>>>>>>>>>>>>>>>insideifscore2");
                    a2 = num;
                    //score.setFocusable(false);
                } else {
                    et_score2.setText("");
                }
            }
        }
        if (et_score3.getText().hashCode() == s.hashCode()) {
            strEnteredVal = et_score3.getText().toString();
            tv_score6.setText(addnumber());
            if (!strEnteredVal.equals("")) {
                int num = Integer.parseInt(strEnteredVal);
                if (num <= Integer.parseInt(tv_marks3.getText().toString())) {
                    System.out.println(">>>>>>>>>>>>>>>>>>insideifscore3");
                    a3 = num;
                } else {
                    et_score3.setText("");
                }
            }
        }
        if (et_score4.getText().hashCode() == s.hashCode()) {
            strEnteredVal = et_score4.getText().toString();
            tv_score6.setText(addnumber());
            if (!strEnteredVal.equals("")) {
                int num = Integer.parseInt(strEnteredVal);
                if (num <= Integer.parseInt(tv_marks4.getText().toString())) {
                    System.out.println(">>>>>>>>>>>>>>>>>>insideifscore4");
                    a4 = num;
                } else {
                    et_score4.setText("");
                }
            }
        }

        if (et_score5.getText().hashCode() == s.hashCode()) {
            strEnteredVal = et_score5.getText().toString();
            tv_score6.setText(addnumber());
            if (!strEnteredVal.equals("")) {
                int num = Integer.parseInt(strEnteredVal);
                if (num <= Integer.parseInt(tv_marks5.getText().toString())) {
                    System.out.println(">>>>>>>>>>>>>>>>>>insideifscore5");
                    a5 = num;
                } else {
                    et_score5.setText("");
                }
            }
        }
    }

    private String addnumber() {
        if (!et_score.getText().toString().equals("")&& et_score.getText().toString().length()>0) {
            int num = Integer.parseInt(et_score.getText().toString());
            if (num <= Integer.parseInt(tv_marks.getText().toString())) {
                System.out.println(">>>>>>>>>>>>>>>>>>insideifscore");
                a1 = num;
            } else {
                a1=0;
            }

        }
        if (!et_score2.getText().toString().equals("")&&et_score2.getText().toString().length()>0) {
            int num = Integer.parseInt(et_score2.getText().toString());
            if (num <= Integer.parseInt(tv_marks2.getText().toString())) {
                System.out.println(">>>>>>>>>>>>>>>>>>insideifscore2");
                a2 = num;
                //score.setFocusable(false);
            } else {
               a2=0;
            }
        }
        if (!et_score3.getText().toString().equals("")&&et_score3.getText().toString().length()>0) {
            int num = Integer.parseInt(et_score3.getText().toString());
            if (num <= Integer.parseInt(tv_marks3.getText().toString())) {
                System.out.println(">>>>>>>>>>>>>>>>>>insideifscore2");
                a3 = num;
                //score.setFocusable(false);
            } else {
                a3=0;
            }
        }
        if (!et_score4.getText().toString().equals("")&&et_score4.getText().toString().length()>0) {
            int num = Integer.parseInt(et_score4.getText().toString());
            if (num <= Integer.parseInt(tv_marks4.getText().toString())) {
                System.out.println(">>>>>>>>>>>>>>>>>>insideifscore2");
                a4 = num;
                //score.setFocusable(false);
            } else {
                a4=0;
            }
        }
        if (!et_score5.getText().toString().equals("")&&et_score5.getText().toString().length()>0) {
            int num = Integer.parseInt(et_score5.getText().toString());
            if (num <= Integer.parseInt(tv_marks5.getText().toString())) {
                System.out.println(">>>>>>>>>>>>>>>>>>insideifscore2");
                a5 = num;
                //score.setFocusable(false);
            } else {
                a5=0;
            }
        }
        return Integer.toString(a1+a2+a3+a4+a5);
    }


    @Override
    public void afterTextChanged(Editable s) {

    }

    @Override
    public void onItemSelected(AdapterView <?> parent, View view, int position, long id) {
        Spinner spn_CrewName=(Spinner)parent;
        Spinner spn_constype=(Spinner)parent;
        if(spn_CrewName.getId()==R.id.spn_CrewName){
            selected_spinner = parent.getItemAtPosition(position).toString();
            if(!selected_spinner.equals("SELECT")){
                crewgrade.get(position-1);
                crewlastgradedate.get(position-1);
               Crewid= crewid.get(position-1);
               crewnameselected=parent.getItemAtPosition(position).toString();
               Crewgrade=crewgrade.get(position-1);
               Crewlastgr_dt= crewlastgradedate.get(position-1);
                Crewlasttotal=crewlasttotal.get(position-1);
                gradingdetail_layout.setVisibility(View.VISIBLE);
                et_score.setFocusableInTouchMode(true);
                et_score2.setFocusableInTouchMode(true);
                et_score3.setFocusableInTouchMode(true);
                et_score4.setFocusableInTouchMode(true);
                et_score5.setFocusableInTouchMode(true);
                et_score8.setFocusableInTouchMode(true);
                bt_clear.setVisibility(View.VISIBLE);
                bt_save.setVisibility(View.VISIBLE);
                et_score.setText("");
                    et_score2.setText("");
                    et_score3.setText("");
                    et_score4.setText("");
                    et_score5.setText("");
                    tv_score6.setText("");

                    if(DataHolder.getType()==Constants.CrewCounselling){
                        tv_crew_name.setText("Crew Name - "+crewnameselected );
                        tv_crew_id.setText("Crew ID - "+Crewid);
                        tv_crewgrade.setVisibility(View.GONE);
                        tv_crewTotal.setVisibility(View.GONE);
                        tv_duedatehead.setText("Last Counselling date - "+lastcounseldt.get(position-1)+"\n"+"Last FootPlate date - "+lastftpltdt.get(position-1));
                        System.out.println(">>>>>>>>>>last counselling date");
                    }
                    else if(DataHolder.getType()==Constants.Grading) {
                        tv_crew_name.setText("Crew Name - "+crewnameselected );
                        tv_crew_id.setText("Crew ID - "+Crewid);
                        tv_crewTotal.setText(" Last Total - "+Crewlasttotal);
                        if (Crewgrade.equals("NA") || Crewgrade.equals("-") || Crewgrade.equals("")) {
                            tv_crewgrade.setText("");
                        } else {
                            System.out.println(">>>>>>>>>>Details"+  crewgrade.get(position-1));
                            tv_crewgrade.setText("Grade - " + Crewgrade);
                        }
                        if (Crewlastgr_dt.equals("-") || Crewlastgr_dt.equals("")) {

                            tv_duedatehead.setText("");
                        } else {
                            System.out.println(">>>>>>>>>>Details"+  crewlastgradedate.get(position-1));
                            tv_duedatehead.setText("Last Grading date - " + Crewlastgr_dt);
                        }
                    }


                System.out.println(">>>>>>>>>>Details"+  crewlastgradedate.get(position-1));
                System.out.println(">>>>>>>>>>Details"+   crewid.get(position-1));
                System.out.println(">>>>>>>>>>Details"+   crewnameselected);
                System.out.println(">>>>>>>>>>Details"+   Crewlasttotal);
            }

            else{
                gradingdetail_layout.setVisibility(View.GONE);
                et_score.setText("");
                et_score2.setText("");
                et_score3.setText("");
                et_score4.setText("");
                et_score5.setText("");
                tv_score6.setText("");
                tv_crew_name.setText("" );
                tv_crew_id.setText("");
                tv_crewTotal.setText("");
                tv_crewgrade.setText("");
                tv_ondate1.setText("dd-mm-yyyy");
                tv_duedatehead.setText("");
commonClass.showToast("Please select Crew..");
            }
        }
        else if(spn_constype.getId()== R.id.spn_constype) {
            selected_spinner = parent.getItemAtPosition(position).toString();
            if (!selected_spinner.equals("SELECT")) {
                counselling_type=selected_spinner;
                if(counselling_type.trim().equalsIgnoreCase("CONS")) {
                    counselling_type = "CONS";
                }else{
                    counselling_type = "FP/M";
                }

            }
        }

    }

    @Override
    public void onNothingSelected(AdapterView <?> parent) {

    }

    @Override
    public void ResponseOk(Object object, int position) {
        if(object instanceof Paramresponse){
            Paramresponse paramresponse=(Paramresponse)object;


            crewlastgradedate=new ArrayList <>();

            while(i<paramresponse.getVosList().size()) {
                Crewname.add(paramresponse.getVosList().get(i).getCrewName());
                crewid.add(paramresponse.getVosList().get(i).getCrewId());
                crewgrade.add(paramresponse.getVosList().get(i).getGrade());
                crewlastgradedate.add(paramresponse.getVosList().get(i).getLastgradingdate());
                crewlasttotal.add(paramresponse.getVosList().get(i).getTotal());
                lastcounseldt.add(paramresponse.getVosList().get(i).getLastCounselDate());
                lastftpltdt.add(paramresponse.getVosList().get(i).getLastFootPlateDate());
                System.out.println(">>>>>>>>>>>>>>>>>>CREW ID>>>>>>>>>>" + paramresponse.getVosList().get(i).getCrewId());
                System.out.println(">>>>>>>>>>>>>>>>>>CREW Name>>>>>>>>>>" + paramresponse.getVosList().get(i).getCrewName());
                System.out.println(">>>>>>>>>>>>>>>>>>CREW Grade>>>>>>>>>>" + paramresponse.getVosList().get(i).getGrade());
                System.out.println(">>>>>>>>>>>>>>>>>>CREW lastgradingdate>>>>>>>>>>" + paramresponse.getVosList().get(i).getLastgradingdate());
                System.out.println(">>>>>>>>>>>>>>>>>>CREW TOTAL>>>>>>>>>>" + paramresponse.getVosList().get(i).getTotal());
                System.out.println(">>>>>>>>>>>>>>>>>>CREW TOTAL>>>>>>>>>>" + paramresponse.getVosList().get(i).getLastCounselDate());
                System.out.println(">>>>>>>>>>>>>>>>>>CREW TOTAL>>>>>>>>>>" + paramresponse.getVosList().get(i).getLastFootPlateDate());
                i++;
            }
            spn_CrewName.setOnItemSelectedListener(this);
            ArrayAdapter crew_name = new ArrayAdapter(this, android.R.layout.simple_spinner_item, Crewname);
            crew_name.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            //Setting the ArrayAdapter data on the Spinner
            spn_CrewName.setAdapter(crew_name);
        }
        i=0;
            if(object instanceof Remarksresponse){
                Remarksresponse remarksresponse=(Remarksresponse)object;
                System.out.println(">>>>>>>>>>>>>>>>>>REMARKS>>>>>>>>>>" +remarksresponse.getVosList().get(i).getRemarks());

                remarksresponse.getVosList().get(i).getappUserRemarks();
                if( remarksresponse.getVosList().get(i).getRemarks().equals("DATASAVED")|| remarksresponse.getVosList().get(i).getRemarks().equals("DATA SAVED")) {
                    System.out.println(">>>>>>>>>>>>>>>>>>REMARKS>>>>>>>>>>" + remarksresponse.getVosList().get(i).getRemarks());
                    et_score.setText("");
                    et_score2.setText("");
                    et_score3.setText("");
                    et_score4.setText("");
                    et_score5.setText("");
                    tv_score6.setText("");
                    et_score8.setText("");
                    tv_ondate1.setText("");
                    if (grade.equals("")) {
                        commonClass.showToast("Record Successfully Updated");
                    } else {
                        commonClass.showToast("Record Successfully Updated" + " " + "Grade is - " + grade);
                        savelist.clear();
                        et_score.setFocusable(false);
                        et_score2.setFocusable(false);
                        et_score3.setFocusable(false);
                        et_score4.setFocusable(false);
                        et_score5.setFocusable(false);
                        et_score8.setFocusable(false);
                        gradingdetail_layout.setVisibility(View.GONE);
                    }
                }
                else if(remarksresponse.getVosList().get(i).getRemarks().equals("DATANOTSAVED")||remarksresponse.getVosList().get(i).getRemarks().equals("DATA NOT SAVED")){
                    commonClass.showToast("Record not Saved.Please try again");
                    savelist.clear();
                   }
                else if(remarksresponse.getVosList().get(i).getRemarks().equals("ERROR")){
                   commonClass.showToast("Error");
                   savelist.clear();
                    }
            }


    }

    @Override
    public void Error() {
    }

    @Override
    public void dismissProgress() {
        commonClass.dismissDialog();
    }

    @Override
    public void showProgress(String msg) {
        commonClass.showProgressBar(msg);
    }
}