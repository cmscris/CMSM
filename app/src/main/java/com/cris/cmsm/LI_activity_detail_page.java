package com.cris.cmsm;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.text.InputFilter;
import android.text.InputType;
import android.text.Spanned;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.TimePicker;

import com.cris.cmsm.adapter.LImovementadapter;
import com.cris.cmsm.database.DataHolder;
import com.cris.cmsm.interfaces.OnItemClickListener;
import com.cris.cmsm.models.request.GraphAPIRequest;
import com.cris.cmsm.models.response.LoginIfoVO;
import com.cris.cmsm.navcontrollers.DetailController;
import com.cris.cmsm.prefrences.UserLoginPreferences;
import com.cris.cmsm.presenter.RequestPresenter;
import com.cris.cmsm.util.CommonClass;
import com.cris.cmsm.util.Constants;
import com.cris.cmsm.widget.PinchRecyclerView;

import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class LI_activity_detail_page extends AppCompatActivity implements
        AdapterView.OnItemSelectedListener {

    /*ArrayList<String>list;
     String id, name, phone;
     TextView ids,phones,names;
     EditText et_id,et_name,et_phone;
     Button btn_clk;*/
    TextView tv_dt, tv_todt, tv_From_sttn, tv_to_sttn, tv_loco, tv_train, tv_remark, et_dt, et_todt;

    private TextView action_bar_title;
    EditText et_From_sttn, et_to_sttn, et_loco, et_train, et_remark,et_km,et_via1,et_via2;
    private ImageView iv_title_icon, iv_right, iv_middle;
    private ArrayList <String> list,transferlist;
    ArrayList <ArrayList <String>> Mainlist;
    private LoginIfoVO loginInfoModel;
    String from_date_time, to_date_time;
    String sn;
    int mHour, mMinute;
    Spinner spn_dutytype;
    NumberFormat format;
    String slectspn,slectspn2;
    private UserLoginPreferences userLoginPreferences;
    String Hour, Minute, id, fromdt, todt, frmsttn, tosttn, loco, train, remrk,frmmonth,tomonth,fromdttime,todttime;
    int pickfrmdt,picktodt,pickfrmyear,j,pickfrmmonth,picktomonth,picktoyear,pickfrmhour,picktohour,pickfrmmin,picktomin;
    Button save, clear,update,btn_del;

    int i = 0;
    PinchRecyclerView pinchview;
    ArrayList <String> Savedatalist;
    TableLayout tableLayout;
    TableRow tableRow, tableRow2;
    CommonClass commonClass;
    private ArrayList <Limovdraftresponse> liresponse,liresponsePrev,titlelist;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_li_detail_page);
        spn_dutytype=(Spinner) findViewById(R.id.spn_dutytype);
        et_dt = (TextView) findViewById(R.id.et_dt);
        et_todt = (TextView) findViewById(R.id.et_todt);
        et_From_sttn = (EditText) findViewById(R.id.et_From_sttn);
        et_to_sttn = (EditText) findViewById(R.id.et_to_sttn);
        et_loco = (EditText) findViewById(R.id.et_loco);
        et_km = (EditText) findViewById(R.id.et_km);
        et_via1=(EditText) findViewById(R.id.et_via1);
        et_via2=(EditText) findViewById(R.id.et_via2);
        et_train = (EditText) findViewById(R.id.et_train);
        et_remark = (EditText) findViewById(R.id.et_remark);
        save = (Button) findViewById(R.id.save);
        clear = (Button) findViewById(R.id.clear);
        update =(Button)findViewById(R.id.btn_updt);
        btn_del=(Button)findViewById(R.id.btn_del);
        iv_title_icon = findViewById(R.id.iv_title_icon);
        iv_right = findViewById(R.id.iv_right);
        iv_middle = findViewById(R.id.iv_middle);
        iv_right.setImageResource(R.drawable.icon_logout);
        iv_right.setVisibility(View.VISIBLE);
        iv_title_icon.setImageResource(R.drawable.iv_back);
        iv_middle.setImageResource(R.mipmap.ic_launcher_list);
        iv_middle.setVisibility(View.VISIBLE);
update.setVisibility(View.GONE);
        save.setVisibility(View.VISIBLE);
        action_bar_title = findViewById(R.id.action_bar_title);
        userLoginPreferences = new UserLoginPreferences(LI_activity_detail_page.this);
        loginInfoModel = userLoginPreferences.getLoginUser();
        commonClass=new CommonClass(LI_activity_detail_page.this);
        action_bar_title.setText("CMS- " + loginInfoModel.getFname());
        list=new ArrayList <>();
        titlelist=new ArrayList <>();
        Mainlist = new ArrayList <>();
        transferlist=new ArrayList <>();
        format=new DecimalFormat("00");
        et_From_sttn.setFilters(new InputFilter[]{new InputFilter.AllCaps()});
        et_to_sttn.setFilters(new InputFilter[]{new InputFilter.AllCaps()});
        et_train.setFilters(new InputFilter[]{new InputFilter.AllCaps()});
        et_via1.setFilters(new InputFilter[]{new InputFilter.AllCaps()});
        et_via2.setFilters(new InputFilter[]{new InputFilter.AllCaps()});
        et_loco.setFilters(new InputFilter[]{new InputFilter.LengthFilter(5)});
        et_remark.setFilters(new InputFilter[]{new InputFilter.LengthFilter(20)});

        et_train.setFilters(new InputFilter[]{new InputFilter.LengthFilter(10)});
        et_train.setInputType(InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS |InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
       Savedatalist=new ArrayList <>();
        Savedatalist.add("Select");
        Savedatalist.add("NOMINATED LP ");
        Savedatalist.add("SAFETY DRIVE ");
        Savedatalist.add("AMBUSH CHECK ");
        Savedatalist.add("VIP MOVEMENT ");
        Savedatalist.add("AS PER SR. DEETRO");
        Savedatalist.add("LIRD CREW ");
        Savedatalist.add("CT CREW ");
        Savedatalist.add("HANDLING CREW ");
        Savedatalist.add("PRACTICAL TRG");
        Savedatalist.add("INSPECTION");
        Savedatalist.add("LOCO SCH. CHECK");
        ArrayAdapter dept = new ArrayAdapter(this, android.R.layout.simple_spinner_item, Savedatalist);
        dept.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            //Setting the ArrayAdapter data on the Spinner
        spn_dutytype.setAdapter(dept);
     spn_dutytype.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);

        Limovdraftresponse lis=new Limovdraftresponse();
        lis.setId("SN");
        lis.setFrmdttm("FROM  DT-TM");
        lis.setTodttm("To DT-TM");
        lis.setFrmsttn("FROM STTN");
        lis.setTosttn("TO STTN");
        lis.setVia1("VIA-1 STTN");
        lis.setVia2("VIA-2 STTN");
        lis.setDutytyp("DUTY TYPE");
        lis.setLoco("LOCO No.");
        lis.setTrain("TRAIN NO.");
        lis.setKm("KMS");
        lis.setRmk("REMARK");
        lis.setEdit("EDIT");
        //lis.setDel("DEL");
        titlelist.add(lis);
        btn_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int j=Integer.parseInt(sn);
                DataHolder.getLimovmainlist().remove(j);
                update.setVisibility(View.GONE);
                save.setVisibility(View.VISIBLE);
                et_dt.setText("");
                et_todt.setText("");
                et_From_sttn.setText("");
                et_to_sttn.setText("");
                et_via1.setText("");
                et_via2.setText("");
                et_train.setText("");
                et_loco.setText("");
                et_remark.setText("");
                et_km.setText("");
                commonClass.showToast("Data Deleted Sucessfully");


            }
        });
        iv_title_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    finish();

            }
        });
        iv_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.iv_right:
                        showLogoutDialog(LI_activity_detail_page.this, "Do you want to logout?", true);
                        break;
                }
            }
        });
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                retryDialog(LI_activity_detail_page.this, "Do you want to clear data?");

            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int j=Integer.parseInt(sn);
               DataHolder.getLimovmainlist().get(j).setFrmdttm(et_dt.getText().toString());
                DataHolder.getLimovmainlist().get(j).setTodttm(et_todt.getText().toString());
                DataHolder.getLimovmainlist().get(j).setFrmsttn(et_From_sttn.getText().toString());
                DataHolder.getLimovmainlist().get(j).setTosttn(et_to_sttn.getText().toString());
                DataHolder.getLimovmainlist().get(j).setVia1(et_via1.getText().toString());
                DataHolder.getLimovmainlist().get(j).setVia2(et_via2.getText().toString());
                DataHolder.getLimovmainlist().get(j).setDutytyp(slectspn2);
                DataHolder.getLimovmainlist().get(j).setLoco(et_loco.getText().toString());
                DataHolder.getLimovmainlist().get(j).setTrain(et_train.getText().toString());
                DataHolder.getLimovmainlist().get(j).setKm(et_km.getText().toString());
                DataHolder.getLimovmainlist().get(j).setRmk(et_remark.getText().toString());


             commonClass.showToast("Data Updated");
             et_dt.setText("");
             et_todt.setText("");
             et_From_sttn.setText("");
             et_to_sttn.setText("");
             et_via1.setText("");
             et_via2.setText("");
             et_train.setText("");
             et_loco.setText("");
             et_remark.setText("");
             et_km.setText("");
                save.setVisibility(View.VISIBLE);
                update.setVisibility(View.GONE);

        }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                j = i + 1;
                id=""+j;





                i++;
               list = new ArrayList < >();
               /* list.add(id);

                list.add(et_dt.getText().toString());
                list.add(et_todt.getText().toString());
                list.add(et_From_sttn.getText().toString());
                list.add(et_to_sttn.getText().toString());
                list.add(slectspn2);
                list.add(et_loco.getText().toString());
                list.add(et_train.getText().toString());
                list.add(et_remark.getText().toString());
                list.add("-");*/
               if(et_dt.getText().toString().equals("")||et_todt.getText().toString().equals("")||et_From_sttn.getText().toString().equals("") || et_to_sttn.getText().toString().equals("")||et_loco.getText().toString().equals("")||
                       et_train.getText().toString().equals("")||et_km.getText().toString().equals("")){
                   commonClass.showToast("Please fill complete detail");

                }
               else if(et_remark.getText().toString().equals("")){
                   commonClass.showToast("Please enter Remarks");
               }
               else {
                   liresponse = new ArrayList <>();
                   Limovdraftresponse limovdraftresponse = new Limovdraftresponse();
                   limovdraftresponse.setId(id);
                   limovdraftresponse.setFrmdttm(et_dt.getText().toString());
                   limovdraftresponse.setTodttm(et_todt.getText().toString());
                   limovdraftresponse.setFrmsttn(et_From_sttn.getText().toString());
                   limovdraftresponse.setTosttn(et_to_sttn.getText().toString());
                   limovdraftresponse.setVia1(et_via1.getText().toString());
                   limovdraftresponse.setVia2(et_via2.getText().toString());
                   limovdraftresponse.setDutytyp(slectspn2);
                   limovdraftresponse.setLoco(et_loco.getText().toString());
                   limovdraftresponse.setTrain(et_train.getText().toString());
                   limovdraftresponse.setKm(et_km.getText().toString());
                   limovdraftresponse.setRmk(et_remark.getText().toString());
                   limovdraftresponse.setEdit("EDIT");
                   //limovdraftresponse.setDel("DEL");

                   liresponsePrev = (ArrayList) DataHolder.getLimovmainlist();
                   System.out.println("size of liresponse list before " + liresponse.size());
                   if (liresponsePrev == null) {
                       liresponse.add(0, lis);
                       liresponse.add(limovdraftresponse);
                       DataHolder.setLimovmainlist(liresponse);
                       System.out.println("inside null");
                       System.out.println("size of liresponse list " + DataHolder.getLimovmainlist());
                       System.out.println("size of liresponse list before " + liresponse.size());
                   } else {
                       liresponsePrev.add(limovdraftresponse);
                       DataHolder.setLimovmainlist(liresponsePrev);
                       System.out.println("inside not  null");
                       System.out.println("size of liresponse list after" + liresponsePrev.size());
                       System.out.println("size of liresponse list " + DataHolder.getLimovmainlist());
                   }
                   commonClass.showToast("Data Saved as a Draft");

                   //DataHolder.setLimovlist(list);
                   // DataHolder.setLevel(1);

                /*if (DataHolder.getLevel() == 1) {
                    System.out.println("LIST DATA>>>>>>" + DataHolder.getLimovlist());
                    Mainlist.add(list);
                    DataHolder.setLimovmainlist(Mainlist);
                    System.out.println("LIST DATA>>>>>>" + Mainlist);
                    DataHolder.setLevel(0);
                } else {
                    list.clear();
                }*/


                   et_dt.setText("");
                   et_todt.setText("");
                   et_From_sttn.setText("");
                   et_to_sttn.setText("");
                   et_via1.setText("");
                   et_via2.setText("");
                   et_train.setText("");
                   et_loco.setText("");
                   et_remark.setText("");
                   et_km.setText("");
               }
            }
        });

        et_dt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePicker();
            }
        });
        et_todt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePicker2();
            }
        });
        iv_middle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println(">>>>>>>>>>>>>>LIMain" + DataHolder.getLimovmainlist());
                if (DataHolder.getLimovmainlist() == null) {
                    commonClass.showToast("Please fill LI Movement");
                } else {
                    Intent i = new Intent(LI_activity_detail_page.this, LI_activitydraftdetail.class);
                    startActivityForResult(i, 1);
                }
            }
        });


    }

    private void showLogoutDialog(final Activity context, String msg,final boolean isLogout) {
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
        CommonClass.goToNextScreen(LI_activity_detail_page.this, DetailController.class, true, Constants.LOGIN_OPTIONS);


        Intent landing = new Intent(LI_activity_detail_page.this, LandingActivity.class);
        landing.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                | Intent.FLAG_ACTIVITY_CLEAR_TASK
                | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(landing);
        overridePendingTransition(R.animator.slide_in_right, R.animator.slide_out_left);
        finish();
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
                    public void onDateSet(DatePicker view, int year,int monthOfYear, int dayOfMonth) {
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
                    public void onTimeSet(TimePicker view, int hourOfDay,int minute) {
                        pickfrmhour=hourOfDay;
                        pickfrmmin=minute;
                        Hour=format.format(hourOfDay);
                        Minute=format.format(minute);
                        mHour = hourOfDay;
                        mMinute = minute;
                        fromdttime=from_date_time+" "+Hour + ":" + Minute;
                        et_dt.setText(fromdttime);

                    }
                }, mHour, mMinute, false);
        timePickerDialog.show();
    }
    private void datePicker2(){

        // Get Current Date
        final Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int  mDay = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,int monthOfYear, int dayOfMonth) {
                        picktodt=dayOfMonth;
                        picktomonth=monthOfYear+1;
                        picktoyear=year;
                        todt=format.format(dayOfMonth);
                        tomonth=format.format(monthOfYear + 1);
                        to_date_time = todt + "-" + (tomonth) + "-" + year;
                        if(to_date_time.equals(from_date_time)){
                            tiemPicker2();
                                }
                        else if(pickfrmmonth<=picktomonth && pickfrmdt<=picktodt){
                            tiemPicker2();

                        }
                        else if(pickfrmmonth<=picktomonth){
                            tiemPicker2();
                        }



                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
        datePickerDialog.show();
    }
    private void tiemPicker2(){
        // Get Current Time
        final Calendar c = Calendar.getInstance();
        mHour = c.get(Calendar.HOUR_OF_DAY);
        mMinute = c.get(Calendar.MINUTE);

        // Launch Time Picker Dialog
        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay,int minute) {
                        picktohour=hourOfDay;
                        picktomin=minute;
                        Hour=format.format(hourOfDay);
                        Minute=format.format(minute);
                        mHour = hourOfDay;
                        mMinute = minute;

                        if(pickfrmdt>=picktodt && pickfrmyear>=picktoyear &&pickfrmmonth>=picktomonth){
                            if(pickfrmhour>=picktohour&&pickfrmmin>=picktomin){
                                et_todt.setText("Invalid Time");
                            }
                            else{
                                todttime=to_date_time+" "+Hour + ":" + Minute;
                                et_todt.setText(to_date_time+" "+Hour + ":" + Minute);
                            }

                        }
                        else{
                            todttime=to_date_time+" "+Hour + ":" + Minute;
                            et_todt.setText(to_date_time+" "+Hour + ":" + Minute);

                        }
                    }
                }, mHour, mMinute, false);
        timePickerDialog.show();
    }


    @Override
    public void onItemSelected(AdapterView <?> parent, View view, int position, long id) {
       slectspn=parent.getItemAtPosition(position).toString();
        if(!slectspn.equals("Select")){
            slectspn2=slectspn;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if(resultCode == Activity.RESULT_OK) {
                ArrayList <String> datalist = data.getStringArrayListExtra("keyName");
                //cardViewProductAdapter.onActivityResult(1,2,data);
                System.out.println("Result final>>>>>>>>>>" + datalist);
                save.setVisibility(View.GONE);
                update.setVisibility(View.VISIBLE);
                sn=datalist.get(0);
                int k= 1;
                while (k < datalist.size()){
                    if (k == 1) {
                        et_dt.setText(datalist.get(k));
                    } else if (k == 2) {
                        et_todt.setText(datalist.get(k));
                    } else if (k == 3) {
                        et_From_sttn.setText(datalist.get(k));
                    } else if (k == 4) {
                        et_to_sttn.setText(datalist.get(k));
                    } else if (k == 5) {
                        slectspn2 = datalist.get(k);
                    } else if (k == 6) {
                        et_loco.setText(datalist.get(k));
                    } else if (k == 7) {
                        et_train.setText(datalist.get(k));
                    } else if (k == 8) {
                        et_remark.setText(datalist.get(k));
                    }
                    else if(k==9){
                        et_via1.setText(datalist.get(k));
                    }
                    else if(k==10){
                        et_via2.setText(datalist.get(k));
                    }
                    else if(k==11){
                        et_km.setText(datalist.get(k));
                    }
                    k++;
            }



            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result
            }
        }

    }
    private void retryDialog(final Activity activity, String msg) {
        new AlertDialog.Builder(activity)
                .setTitle(getResources().getString(R.string.cms))
                .setMessage(msg)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        et_dt.setText("");
                        et_todt.setText("");
                        et_From_sttn.setText("");
                        et_to_sttn.setText("");
                        et_via1.setText("");
                        et_via2.setText("");
                        et_train.setText("");
                        et_loco.setText("");
                        et_remark.setText("");
                        et_km.setText("");
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
    public void onNothingSelected(AdapterView <?> parent) {

    }
}