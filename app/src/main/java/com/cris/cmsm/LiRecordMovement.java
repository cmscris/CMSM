package com.cris.cmsm;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatRadioButton;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class LiRecordMovement extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    AppCompatRadioButton radio_departure,radio_arrival;
    RadioGroup radioGroup;
    EditText et_gps_sttn, et_frm_sttn,et_via_1,et_via_2,et_loco,et_train,et_remarks;
    TextView dttime,refrencenum;
    Spinner spn_authority,spn_dutytype,spn_purpose,spn_serviceTyp;

    String from_date_time, to_date_time;
    String Hour="" ;
    String Minute="";
    String formateDate="";
    CardView formlayout;
    LinearLayout spinner_layout,spn_Textview_Layout;
    ArrayList authority,dutytype,purpose,service;
    NumberFormat format;
    String  id, fromdt, todt,frmmonth,tomonth,fromdttime,todttime;
    int mHour, mMinute;
    int pickfrmdt,picktodt,pickfrmyear,j,pickfrmmonth,picktomonth,picktoyear,pickfrmhour,picktohour,pickfrmmin,picktomin;
    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_li_record_movement);
        radioGroup=(RadioGroup)findViewById(R.id.radioGroup);
        formlayout=(CardView) findViewById(R.id.formlayout);
        spinner_layout=(LinearLayout)findViewById(R.id.spinner_layout);
        spn_Textview_Layout=(LinearLayout)findViewById(R.id.spn_Textview_Layout);
        radio_departure = (AppCompatRadioButton) findViewById(R.id.radio_departure);
        radio_arrival = (AppCompatRadioButton) findViewById(R.id.radio_arrival);
        dttime = (TextView) findViewById(R.id.dttime);
        refrencenum=(TextView)findViewById(R.id.refrencenum);
        et_frm_sttn = (EditText) findViewById(R.id.et_frm_sttn);
        et_gps_sttn = (EditText) findViewById(R.id.et_gps_sttn);
        et_via_1 = (EditText) findViewById(R.id.et_via_1);
        et_via_2 = (EditText) findViewById(R.id.et_via_2);
        et_loco = (EditText) findViewById(R.id.et_loco);
        et_train = (EditText) findViewById(R.id.et_train);
        et_remarks = (EditText) findViewById(R.id.et_remarks);
        spn_authority = (Spinner) findViewById(R.id.spn_authority);
        spn_dutytype = (Spinner) findViewById(R.id.spn_dutytype);
        spn_purpose = (Spinner) findViewById(R.id.spn_purpose);
        spn_serviceTyp=(Spinner)findViewById(R.id.spn_serviceTyp);
        format = new DecimalFormat("00");
        authority=new ArrayList();
        formlayout.setVisibility(View.GONE);
        spn_Textview_Layout.setVisibility(View.GONE);
        authority.add("Select the Movement Authority");
        authority.add("By TLC");
        authority.add("By Self");
        ArrayAdapter auth = new ArrayAdapter(this, android.R.layout.simple_spinner_item, authority);
        auth.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //Setting the ArrayAdapter data on the Spinner
        spn_authority.setAdapter(auth);
        spn_authority.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);
        dutytype=new ArrayList();
        dutytype.add("Please Select Duty Type");
        dutytype.add("SAFETY DRIVE ");
        dutytype.add("AMBUSH CHECK ");
        dutytype.add("VIP MOVEMENT ");
        dutytype.add("LRD CREW ");
        dutytype.add("CT CREW ");
        dutytype.add("HANDLING CREW ");
        dutytype.add("PRACTICAL TRG");
        dutytype.add("INSPECTION");
        dutytype.add("LOCO SCH. CHECK");
        dutytype.add("SPCL DUTY");
        dutytype.add("FOOTPLATE");
        dutytype.add("COUNSELLING");
        dutytype.add("STATIONARY DUTIES");
        dutytype.add("PROTOCOL DUTY");
        ArrayAdapter duty = new ArrayAdapter(this, android.R.layout.simple_spinner_item, dutytype);
        duty.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //Setting the ArrayAdapter data on the Spinner
        spn_dutytype.setAdapter(duty);
        spn_dutytype.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);
        purpose=new ArrayList();
        purpose.add("Please Select Purpose of Duty");
        purpose.add("Footplate with Alloted Staff");
        purpose.add("Accompany with Officers");
        purpose.add("Ambush Check");
        purpose.add("Footplate with Non Alloted Staff");
        purpose.add("Spare Movement");
        ArrayAdapter purps = new ArrayAdapter(this, android.R.layout.simple_spinner_item, purpose);
        purps.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //Setting the ArrayAdapter data on the Spinner
        spn_purpose.setAdapter(purps);
        spn_purpose.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);
        service=new ArrayList();

        service.add("Please Select Type of Service");
        service.add("Coaching");
        service.add("Goods");
        ArrayAdapter servc = new ArrayAdapter(this, android.R.layout.simple_spinner_item, service);
        servc.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        //Setting the ArrayAdapter data on the Spinner
        spn_serviceTyp.setAdapter(servc);
        spn_serviceTyp.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);


        Bundle extra = getIntent().getExtras();
        if (extra != null) {
            String temp = extra.getString("frmdate");
            formateDate=temp;
            String currenttime= sdf.format(Calendar.getInstance().getTime());
            dttime.setText(temp+currenttime);

        }
        dttime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePicker();
            }
        });
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId==radio_arrival.getId()){
                    refrencenum.setVisibility(View.VISIBLE);
                    et_frm_sttn.setFocusable(false);
                    et_loco.setFocusable(false);
                    et_train.setFocusable(false);
                    et_via_1.setFocusable(false);
                    et_via_2.setFocusable(false);
                    formlayout.setVisibility(View.VISIBLE);
                    spinner_layout.setVisibility(View.GONE);
                    spn_Textview_Layout.setVisibility(View.VISIBLE);
                }
                if(checkedId==radio_departure.getId()){
                    refrencenum.setVisibility(View.GONE);
                    et_frm_sttn.setFocusable(true);
                    et_loco.setFocusable(true);
                    et_train.setFocusable(true);
                    et_via_1.setFocusable(true);
                    et_via_2.setFocusable(true);
                    formlayout.setVisibility(View.VISIBLE);
                    spinner_layout.setVisibility(View.VISIBLE);
                    spn_Textview_Layout.setVisibility(View.GONE);
                }
            }
        });

    }
    private void datePicker(){

        // Get Current Date
        final Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int  mDay = c.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        pickfrmdt=dayOfMonth;
                        pickfrmyear=year;
                        pickfrmmonth=monthOfYear + 1;
                        fromdt=format.format(dayOfMonth);
                        frmmonth=format.format(monthOfYear + 1);

                        from_date_time = fromdt + "-" + (frmmonth) + "-" + year;

                        //*************Call Time Picker Here ********************
                        tiemPicker();
                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
        datePickerDialog.show();
    }
    private void tiemPicker(){
        // Get Current Time
        final Calendar c = Calendar.getInstance();
        mHour = c.get(Calendar.HOUR_OF_DAY);
        mMinute = c.get(Calendar.MINUTE);

        // Launch Time Picker Dialog
        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        pickfrmhour=hourOfDay;
                        pickfrmmin=minute;
                        Hour=format.format(hourOfDay);
                        Minute=format.format(minute);
                        mHour = hourOfDay;
                        mMinute = minute;
                        fromdttime= formateDate +" "+Hour + ":" + Minute;
                        dttime.setText(fromdttime);

                    }
                }, mHour, mMinute, false);
        timePickerDialog.show();
    }

    @Override
    public void onItemSelected(AdapterView <?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView <?> parent) {

    }
}
