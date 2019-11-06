package com.cris.cmsm;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.icu.text.UnicodeFilter;
import android.os.Build;
import android.os.SystemClock;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.Spanned;
import android.text.TextWatcher;
import android.util.Base64;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.cris.cmsm.database.DataHolder;
import com.cris.cmsm.models.request.GraphAPIRequest;
import com.cris.cmsm.models.response.LoginIfoVO;
import com.cris.cmsm.models.response.Paramresponse;
import com.cris.cmsm.models.response.Sectionresponse;
import com.cris.cmsm.navcontrollers.DetailController;
import com.cris.cmsm.prefrences.UserLoginPreferences;
import com.cris.cmsm.presenter.RequestPresenter;
import com.cris.cmsm.presenterview.ResponseView;
import com.cris.cmsm.util.CommonClass;
import com.cris.cmsm.util.Constants;
import com.cris.cmsm.util.StringFormatter;
import com.cris.cmsm.widget.TouchImageView;

import java.io.ByteArrayOutputStream;
import java.net.URLEncoder;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Abnormality_fill_activity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, ResponseView {
    TextView crewid1, designation, DOJ,crewiddata, designationdata,abnrdept,abnrsubhead,Loco,cameratv,Trainno,DOJ1,Timedata;
    TouchImageView touchimageview;
    EditText et_From_sttn, et_to_sttn, et_to_km, et_From_km,Locodata,abnrfill,trainnum;
    Spinner spinner_section,subhead_spin,dept_spin;
    Button getsection_bttn, SaveNext,Reset2;
    Bitmap photo;
    int currenthour;
    CommonClass commonClass;
    private TextView action_bar_title;
    private ImageView iv_left, iv_middle, iv_right,iv_title_icon;
    private Resources res;
    private LoginIfoVO loginInfoModel;
    private UserLoginPreferences userLoginPreferences;
    String selected_spinner;
    ArrayList <String> sectionlist;
    ArrayList <String> subheadlist;
    DatePickerDialog picker;
    TimePickerDialog timepicker;
    ArrayList <String> abnrdeptlist;
    int pickdate;
    RequestPresenter requestPresenter;
    GraphAPIRequest request;
    NumberFormat format;
    String Hour,Minute,pickmonth,deptstring,subhead,time,date,etfrom,etto,section,image;
    String bitmap="";
    ArrayList <String> filleddata,datafilllist;
    private static final int CAMERA_REQUEST = 1888;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abnormality_fill_activity);
        getsection_bttn = (Button) findViewById(R.id.getsection_bttn);
        touchimageview=(TouchImageView)findViewById(R.id.touchimageview);
        cameratv=(TextView)findViewById(R.id.cameratv);
        abnrfill=(EditText)findViewById(R.id.abnrfill);
        Trainno=(TextView) findViewById(R.id.Trainno);
        trainnum=(EditText)findViewById(R.id.trainnum);
        iv_title_icon = findViewById(R.id.iv_title_icon);
        iv_title_icon.setImageResource(R.drawable.iv_back);
        crewid1 = (TextView) findViewById(R.id.crewid1);
        abnrdept = (TextView) findViewById(R.id.abnrdept);
        abnrsubhead = (TextView) findViewById(R.id.abnrsubhead);
        crewiddata = (TextView) findViewById(R.id.crewiddata);
        Loco = (TextView) findViewById(R.id.Loco);
        Locodata = (EditText) findViewById(R.id.locodata);
        designation = (TextView) findViewById(R.id.designation);
        designationdata = (TextView) findViewById(R.id.designationdata);
        DOJ = (TextView) findViewById(R.id.DOJ);
        action_bar_title = (TextView) findViewById(R.id.action_bar_title);
        DOJ1 = (TextView) findViewById(R.id.DOJ1);
        Reset2=(Button)findViewById(R.id.Reset2);
        SaveNext = (Button) findViewById(R.id.SaveNext);
        Timedata = (TextView) findViewById(R.id.Timedata);
        et_From_sttn = (EditText) findViewById(R.id.et_From_sttn);
        et_From_km = (EditText) findViewById(R.id.et_From_km);
        et_to_km = (EditText) findViewById(R.id.et_to_km);
        et_to_sttn = (EditText) findViewById(R.id.et_to_sttn);
        spinner_section = (Spinner) findViewById(R.id.section_spin);
        subhead_spin = (Spinner) findViewById(R.id.subhead_spin);
        dept_spin = (Spinner) findViewById(R.id.dept_spin);
        iv_left = (ImageView) findViewById(R.id.iv_left);
        iv_middle = (ImageView) findViewById(R.id.iv_middle);
        iv_right = (ImageView) findViewById(R.id.iv_right);
        filleddata = new ArrayList <>(14);
        datafilllist=new ArrayList <>(8);
        subheadlist=new ArrayList <>();
        requestPresenter = new RequestPresenter(this);
        request = new GraphAPIRequest();
        abnrdeptlist=new ArrayList <>();
        sectionlist = new ArrayList <>();
        format=new DecimalFormat("00");
        filleddata.clear();


        cameratv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                touchimageview.setVisibility(View.VISIBLE);
            }
        });
        iv_title_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Reset2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                retryDialog(Abnormality_fill_activity.this, "Do you want to reset data?");

            }
        });
        iv_right.setVisibility(View.VISIBLE);
        iv_left.setVisibility(View.GONE);
        abnrdeptlist.add("Select");
        abnrdeptlist.add("OHE");
        abnrdeptlist.add("ENGG");
        abnrdeptlist.add("ST");
        abnrdeptlist.add("CW");
        abnrdeptlist.add("LOCO");
        abnrdeptlist.add("EMU");
        abnrdeptlist.add("DMU");
        abnrdeptlist.add("SECURITY");
        abnrdeptlist.add("TRAFFIC");
        abnrdeptlist.add("COMMERCIAL");
        dept_spin.setOnItemSelectedListener(this);
        ArrayAdapter dept = new ArrayAdapter(this, android.R.layout.simple_spinner_item, abnrdeptlist);
        dept.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        dept_spin.setAdapter(dept);
        touchimageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
                touchimageview.resetZoom();
            }
        });

        Timedata.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                final int date=c.get(Calendar.DATE);



                System.out.println( "Date>>>>>>>>>>>>>"+ date);
                //System.out.println( "currentmonth>>>>>>>>>>>>>"+ ((c.get(Calendar.MONTH)+1)));
                System.out.println( "Date>>>>>>>>>>>>>"+ c.get(Calendar.YEAR));
                System.out.println( new Timestamp(System.currentTimeMillis()) );
                final  int mHour = c.get(Calendar.HOUR);
                final  int mMinute = c.get(Calendar.MINUTE);

                // int mSeconds = c.get(Calendar.SECOND);
                timepicker=new TimePickerDialog(Abnormality_fill_activity.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {


                                if (pickdate == date ) {
                                    System.out.println("PICKDATE>=DATE");
                                    System.out.println("Current Time is" + (mHour + ":" + mMinute));

                                    if (hourOfDay <= mHour && minute <= mMinute) {
                                        Hour=format.format(hourOfDay);
                                        Minute=format.format(minute);
                                        System.out.println("Current Time is" + (mHour + ":" + mMinute));

                                        Timedata.setText(Hour + ":" + Minute);
                                        time=Timedata.getText().toString();
                                        //datafilllist.add(Timedata.getText().toString());

                                    } if (mHour >= 0) {
                                        System.out.println(">>>>>>>>>>>>>>>INSIDE>>>>>>>>>mHour>=0>>>>>>>>>>");

                                        currenthour= mHour + 12;
                                        Minute=format.format(minute);
                                        Timedata.setText(currenthour + ":" + Minute);
                                        if (hourOfDay <= currenthour) {
                                            System.out.println(">>>>>>>>>>>>>>>INSIDE>>>>>>>>>hourOfDay>=currenthour>>>>>>>>>>");

                                            Hour=format.format(hourOfDay);
                                            Timedata.setText(Hour + ":" + Minute);
                                            time=Timedata.getText().toString();
                                            //datafilllist.add(Timedata.getText().toString());

                                        }
                                        else{
                                            System.out.println("Invalid Time");
                                            Timedata.setText("Invalid Time");
                                        }


                                    }

                                    else{
                                        System.out.println("Invalid Time");
                                        Timedata.setText("Invalid Time");

                                    }
                                } else {
                                    Hour = format.format(hourOfDay);
                                    Minute = format.format(minute);
                                    System.out.println(">>>>>>>>>>>>>>>INSIDE>>>>>>>>>final else case>>>>>>>>>>");
                                    Timedata.setText(Hour + ":" + Minute);
                                    time=Timedata.getText().toString();
                                    //Timedata.setText(hourOfDay + ":" + minute);
                                    //datafilllist.add(Timedata.getText().toString());
                                }

                            }


                        }, mHour,mMinute ,true);

                //timepicker.setMaxTime(c.get(Calendar.HOUR_OF_DAY),c.get(Calendar.MINUTE),c.get(Calendar.SECOND));
                timepicker.show();
            }
        });
        DOJ1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int years = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(Abnormality_fill_activity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                pickdate = dayOfMonth;
                                String tvdate=format.format(dayOfMonth);
                                pickmonth=format.format(monthOfYear+1);



                                DOJ1.setText(tvdate + "-" + (pickmonth) + "-" + year);
                                date=DOJ1.getText().toString();

                                //datafilllist.add(DOJ1.getText().toString());
                            }
                        }, years, month, day);
                picker.getDatePicker().setMaxDate(System.currentTimeMillis());
                picker.show();
            }
        });


        et_From_sttn.setFilters(new InputFilter[]{new InputFilter.AllCaps()});
        //et_From_sttn.setFilters(new InputFilter[]{new InputFilter.LengthFilter(4)});
        abnrfill.setFilters(new InputFilter[]{new InputFilter.LengthFilter(500)});

        // abnrfill.setImeOptions(EditorInfo.IME_FLAG_FORCE_ASCII);
        trainnum.setFilters(new InputFilter[]{new InputFilter.LengthFilter(10)});
        trainnum.setInputType(InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS |InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);

        et_to_sttn.setFilters(new InputFilter[]{new InputFilter.AllCaps()});
        //et_to_sttn.setFilters(new InputFilter[]{new InputFilter.LengthFilter(4)});
        et_From_km.setFilters(new InputFilter[]{new InputFilter.LengthFilter(8)});
        et_to_km.setFilters(new InputFilter[]{new InputFilter.LengthFilter(8)});
        Locodata.setFilters(new InputFilter[]{new InputFilter.LengthFilter(6)});
        iv_middle.setVisibility(View.GONE);
        userLoginPreferences = new UserLoginPreferences(Abnormality_fill_activity.this);
        loginInfoModel = userLoginPreferences.getLoginUser();
        iv_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.iv_right:
                        showLogoutDialog(Abnormality_fill_activity.this, "Do you want to logout?", true);
                        break;
                }
            }
        });
        //filleddata.add(loginInfoModel.getFname());
        //datafilllist.add(DataHolder.getLoginid());
        System.out.println(" >>>>Loginid" + DataHolder.getLoginid());

        //filleddata.add(loginInfoModel.getDesignation());

        crewiddata.setText(loginInfoModel.getLoginid());

        action_bar_title.setText("CMS- "+loginInfoModel.getFname());

        System.out.println(" >>>>designation" + loginInfoModel.getDesignation());
        designationdata.setText(loginInfoModel.getDesignation());


        SaveNext.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                System.out.println(" <>>>>>>inside onclick save");
                System.out.println(" <>>>>>>inside onclick save" +datafilllist);
                /*Intent i = new Intent(Abnormality_fill_activity.this, Abnormality_department.class);
                i.putExtra("object", bitmap);
                startActivity(i);*/
                filleddata.add(DataHolder.getLoginid());
                filleddata.add(date);
                filleddata.add(time);
                filleddata.add(etfrom);
                filleddata.add(etto);
                filleddata.add(section);
                filleddata.add(deptstring);
                filleddata.add(subhead);
                //filleddata.add(0,crewiddata.getText().toString());
                if(!et_From_km.getText().toString().equals("")&& !et_to_km.getText().toString().equals("")){
                    filleddata.add(et_From_km.getText().toString());
                    filleddata.add(et_to_km.getText().toString());

                }
                if(DataHolder.getLoginid().equals("")){
                    System.out.println("Please login again");
                    Toast.makeText(getApplicationContext(),"Your session expires,Please login again",Toast.LENGTH_LONG).show();
                    logOut();
                }
                filleddata.add(Locodata.getText().toString());
                filleddata.add(trainnum.getText().toString());
                String utf= StringFormatter.convertStringToUTF8(abnrfill.getText().toString());
                String decode=StringFormatter.convertUTF8ToString(utf);
                System.out.println(">>>>>>>>>>>hindi string"+decode);
                filleddata.add(utf);

                filleddata.add(bitmap);

                System.out.println(" <>>>>>>inside onclick save" +(filleddata.size()));
                System.out.println(" <>>>>>>inside onclick save" +(filleddata));
                if (DOJ1.getText().toString().equals("") || Timedata.getText().toString().equals("") ||
                        et_From_sttn.getText().toString().equals("")|| et_to_sttn.getText().toString().equals("") ||
                        et_From_km.getText().toString().equals("") || et_to_km.getText().toString().equals("") ||
                        Locodata.getText().toString().equals("") || trainnum.getText().toString().equals("")|| abnrfill.getText().toString().equals("")|| selected_spinner.equals("Select")) {
                    Toast.makeText(getApplicationContext(), "Please fill complete detail", Toast.LENGTH_SHORT).show();
                    filleddata.clear();

                } else {

                    request.setparamlist(filleddata);
                    requestPresenter.Request(request,"c",Constants.SAVEABNORMALITY);

                    //callwebservice.
                    /*Intent i = new Intent(Abnormality_fill_activity.this, Abnormality_department.class);
                    i.putExtra("object", filleddata);
                    startActivity(i);*/

                }
            }

        });



        getsection_bttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fromsttn = et_From_sttn.getText().toString();
                String tosttn = et_to_sttn.getText().toString();
                if(!fromsttn.equals("") && !tosttn.equals("")){
                    etfrom=et_From_sttn.getText().toString();
                    etto=et_to_sttn.getText().toString();
                    //datafilllist.add(et_From_sttn.getText().toString());
                    //datafilllist.add(et_to_sttn.getText().toString());
                    ArrayList <String> sttnlist = new ArrayList <>();
                    sttnlist.clear();

                    sttnlist.add(fromsttn);
                    sttnlist.add(tosttn);
                    request.setparamlist(sttnlist);
                    requestPresenter.Request(request, "c", Constants.SECTION);

                }

                if (fromsttn.equals("") && tosttn.equals("")) {
                    Toast.makeText(Abnormality_fill_activity.this, "Please enter From and To Station", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    @Override
    public void onItemSelected(AdapterView <?> parent, View view, int position, long id) {
        dept_spin.setEnabled(true);
        subhead_spin.setEnabled(true);
        Spinner spinner_section=(Spinner)parent;
        Spinner dept_spin =(Spinner)parent;
        Spinner subhead_spin=(Spinner)parent;
        if(spinner_section.getId()==(R.id.section_spin)){
            selected_spinner = parent.getItemAtPosition(position).toString();
            if(!selected_spinner.equals("Select")){
                section=parent.getItemAtPosition(position).toString();
                //datafilllist.add(selected_spinner);
            }


            if (selected_spinner.equals("Select")) {
                Toast.makeText(getApplicationContext(), "Please select section", Toast.LENGTH_LONG).show();

            }
            //Toast.makeText(getApplicationContext(),selected_spinner, Toast.LENGTH_LONG).show();
            System.out.println(">>>>>>>Inside onItemSelecetd>>>>>>>>>" + position);
        }


        if(dept_spin.getId()==R.id.dept_spin){
            ArrayList <String> deptlist = new ArrayList <>();
            selected_spinner = parent.getItemAtPosition(position).toString();
            if(!selected_spinner.equals("Select")){
                deptstring= parent.getItemAtPosition(position).toString();
                selected_spinner = parent.getItemAtPosition(position).toString();
                //datafilllist.add(selected_spinner);
                deptlist.add(deptstring);
                subheadlist.clear();
                request.setparamlist(deptlist);
                requestPresenter.Request(request,"fetching subheads",Constants.SUBHEADLIST);

            }
            if (selected_spinner.equals("Select")) {
                //Toast.makeText(getApplicationContext(), "Please select abnormality head", Toast.LENGTH_SHORT).show();

            }


            //Toast.makeText(getApplicationContext(),selected_spinner, Toast.LENGTH_LONG).show();
            System.out.println(">>>>>>>Inside onItemSelecetd>>>>>>>>>" + position);

        }
        if(subhead_spin.getId()==R.id.subhead_spin) {
            selected_spinner = parent.getItemAtPosition(position).toString();
            if (!selected_spinner.equals("Select")) {
                subhead=parent.getItemAtPosition(position).toString();
                //datafilllist.add(selected_spinner);


            }
            if (selected_spinner.equals("Select")) {
                Toast.makeText(getApplicationContext(), "Please select abnormality subhead", Toast.LENGTH_SHORT).show();

            }
            System.out.println(">>>>>>>Inside onItemSelecetd>>>>>>>>>" + position);
        }




    }


    @Override
    public void onNothingSelected(AdapterView <?> parent) {


    }

    @Override
    public void ResponseOk(Object object, int position) {
        int i = 0;
        if (object instanceof Sectionresponse) {
            System.out.println("Key sucess>>>>>>>>>>");
            Sectionresponse sectionresponse = (Sectionresponse) object;
            System.out.println("Key sucess>>>>>>>>>>");
            if ((sectionresponse.getVosList().isEmpty())) {
                System.out.println("No sections found");
            } if (sectionresponse.getVosList().get(i).equals("SECTION")) {
                System.out.println("Else if-------");
                System.out.println("Else if- size------"+sectionresponse.getVosList().size());
                sectionlist.clear();
                while (i+1 < sectionresponse.getVosList().size()) {

                    sectionresponse.getVosList().get(i+1);
                    sectionlist.add(sectionresponse.getVosList().get(i+1));
                    System.out.println("sectionlist" + (sectionresponse.getVosList().get(i+1)));
                    i++;
                }
                i=0;
                spinner_section.setOnItemSelectedListener(this);
                ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, sectionlist);
                aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                //Setting the ArrayAdapter data on the Spinner
                spinner_section.setAdapter(aa);
            }
            if (sectionresponse.getVosList().get(i).equals("SUBHEAD")) {
                while (i+1 < sectionresponse.getVosList().size()) {

                    sectionresponse.getVosList().get(i+1);
                    subheadlist.add(sectionresponse.getVosList().get(i+1));
                    System.out.println("subheadlist" + (sectionresponse.getVosList().get(i+1)));
                    i++;
                }
                i=0;
                subhead_spin.setOnItemSelectedListener(this);
                ArrayAdapter subhead = new ArrayAdapter(this, android.R.layout.simple_spinner_item, subheadlist);
                subhead.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                //Setting the ArrayAdapter data on the Spinner
                subhead_spin.setAdapter(subhead);

            }
        }
        if(object instanceof Paramresponse){
            System.out.println("Key sucess>>>>>>>>>>");
            Paramresponse paramresponse = (Paramresponse) object;
            System.out.println("Key sucess>>>>>>>>>>");
            if(paramresponse.getVosList().get(i).getResponseMessage().equalsIgnoreCase("RECORD SUCCESSFULLY UPDATED.")){
                Toast.makeText(getApplicationContext(), "RECORD SUCCESSFULLY UPDATED.", Toast.LENGTH_LONG).show();
                Intent intent = getIntent();
                finish();
                startActivity(intent);
                filleddata.clear();
                datafilllist.clear();
                trainnum.setText("");
                DOJ1.setText("");
                Timedata.setText("");
                et_From_sttn.setText("");
                et_to_sttn.setText("");
                et_to_km.setText("");
                et_From_km.setText("");
                Locodata.setText("");
                abnrfill.setText("");
                touchimageview.setImageResource(R.drawable.camera);



            }
            if(paramresponse.getVosList().get(i).getResponseMessage().equalsIgnoreCase("LOCO NUMBER DOES NOT EXIST !!")) {
                Toast.makeText(getApplicationContext(), "LOCO NUMBER DOES NOT EXIST !!", Toast.LENGTH_LONG).show();
                filleddata.clear();
                //filleddata.set(10,Locodata.getText().toString());
                //filleddata.set(11,trainnum.getText().toString());
                //filleddata.set(12,abnrfill.getText().toString());

                /*if (Locodata.hasSelection()) {
                    filleddata.set(10, Locodata.getText().toString());
                }*/
            }

        }

    }

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
                        dialog.dismiss();
                        if (isLogout) {
                            logOut();
                        } else {
                            context.finish();
                        }
                    }
                })
                .setIcon(R.drawable.icon_logo)
                .show();
    }

    private void logOut () {
        //userLoginPreferences.clearUser();
        DataHolder.setLevel(0);
        CommonClass.goToNextScreen(Abnormality_fill_activity.this, DetailController.class, true, Constants.LOGIN_OPTIONS);


        Intent landing = new Intent(Abnormality_fill_activity.this, LandingActivity.class);
        landing.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                | Intent.FLAG_ACTIVITY_CLEAR_TASK
                | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(landing);
        overridePendingTransition(R.animator.slide_in_right, R.animator.slide_out_left);
        finish();
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_REQUEST && resultCode==RESULT_OK && data!=null) {

            photo = (Bitmap) data.getExtras().get("data");
            touchimageview.setImageBitmap(photo);

            ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
            photo.compress(Bitmap.CompressFormat.JPEG,80,byteArrayOutputStream);
            photo.setDensity(160);
            byte[] imageByte=byteArrayOutputStream.toByteArray();

            bitmap= Base64.encodeToString(imageByte,Base64.DEFAULT);
            System.out.println(bitmap);


            System.out.println(" Photo string>>>>>>>>>"+bitmap.length());


            touchimageview.setMaxZoom(20);
            //filleddata.add(bitmap);

        }

    }
    private void retryDialog(final Activity activity, String msg) {
        new AlertDialog.Builder(activity)
                .setTitle(getResources().getString(R.string.cms))
                .setMessage(msg)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        abnrfill.setText("");
                        DOJ1.setText("");
                        Timedata.setText("");
                        et_From_sttn.setText("");
                        et_to_sttn.setText("");
                        et_From_km.setText("");
                        et_to_km.setText("");
                        sectionlist.clear();
                        Locodata.setText("");
                        trainnum.setText(" ");
                        touchimageview.setImageResource(R.drawable.camera);

                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        // finish();
                    }
                })
                .setIcon(R.drawable.icon_logo)
                .show();
    }
    @Override
    public void Error () {

    }

    @Override
    public void dismissProgress () {

    }

    @Override
    public void showProgress (String msg){

    }

}
