package com.cris.cmsm.navcontrollers;

import android.app.DatePickerDialog;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatSpinner;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.CheckBox;
import com.cris.cmsm.BaseActivity;
import com.cris.cmsm.R;
import com.cris.cmsm.adapter.DesignationAdapter;
import com.cris.cmsm.adapter.DivisonAdapter;
import com.cris.cmsm.adapter.LobbyAdapter;
import com.cris.cmsm.adapter.MonthAdapter;
import com.cris.cmsm.adapter.RailwayAdapter;
import com.cris.cmsm.adapter.SpinnerAdapter;
import com.cris.cmsm.database.DataBaseManager;
import com.cris.cmsm.database.DataHolder;
import com.cris.cmsm.models.Designation;
import com.cris.cmsm.models.Division;
import com.cris.cmsm.models.Lobby;
import com.cris.cmsm.models.Month;
import com.cris.cmsm.models.Railway;
import com.cris.cmsm.models.request.ConSummaryRequest;
import com.cris.cmsm.models.request.GraphAPIRequest;
import com.cris.cmsm.models.response.CrewUtilResponse;
import com.cris.cmsm.models.response.LoginIfoVO;
import com.cris.cmsm.prefrences.UserLoginPreferences;
import com.cris.cmsm.presenter.MonthlyRequestPresenter;
import com.cris.cmsm.presenter.RequestPresenter;
import com.cris.cmsm.presenterview.ResponseView;
import com.cris.cmsm.util.CommonClass;
import com.cris.cmsm.util.Constants;
import com.cris.cmsm.util.FontFamily;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 *
 */

public class LiInspectionController extends BaseActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener, ResponseView {

    private ImageView iv_title_icon;
    private TextView action_bar_title, tv_filters , tv_ambush;
    private Typeface font;
    private AppCompatSpinner spn_ref_no, spn_cab, spn_train_type,spn_bpc_valid_invalid,spn_type_of_coches,spn_speedometer,spn_vcd,spn_horns,spn_sander;
    private EditText et_lp_id, et_alp_id ,et_lead_loco_no, et_train_no,et_bpc_no,et_bpc_percentage,et_bpc_issue_station,et_load,et_no_of_coaches,et_comoddity,
            et_guard_id,et_energy_meter_start,et_energy_meter_end,et_regen_start,et_regen_end,et_trail_loco_no,et_weak_area_lp,et_weak_area_alp;
    private CheckBox ch_gate,ch_ba,ch_runningroom, ch_auto,ch_cug,ch_speedgun,ch_bookinglooby,ch_nrt,ch_yard,ch_shunting,ch_cont;
    private Button btn_filter;
    private CommonClass commonClass;
    private DataBaseManager dataBaseManager;
    private LoginIfoVO loginUser;
    private MonthlyRequestPresenter monthlyRequestPresenter;
    private RequestPresenter requestPresenter;
    private LoginIfoVO loginInfoModel;
    private UserLoginPreferences userLoginPreferences;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_li_inspection_record);
        monthlyRequestPresenter = new MonthlyRequestPresenter(LiInspectionController.this);
        requestPresenter = new RequestPresenter(LiInspectionController.this);
        userLoginPreferences = new UserLoginPreferences(LiInspectionController.this);
        loginInfoModel = userLoginPreferences.getLoginUser();
        loginUser = new UserLoginPreferences(LiInspectionController.this).getLoginUser();
        commonClass = new CommonClass(LiInspectionController.this);
        dataBaseManager = new DataBaseManager(LiInspectionController.this);
        dataBaseManager.createDatabase();

        font = new FontFamily(LiInspectionController.this).getBold();
        spn_ref_no = findViewById(R.id.spn_ref_no);
        et_lp_id = findViewById(R.id.et_lp_id);
        et_alp_id = findViewById(R.id.et_alp_id);
        et_lead_loco_no= findViewById(R.id.et_lead_loco_no);
        spn_cab = findViewById(R.id.spn_cab);
        et_train_no = findViewById(R.id.et_train_no);
        spn_train_type = findViewById(R.id.spn_train_type);
        et_bpc_no= findViewById(R.id.et_bpc_no);
        et_bpc_percentage= findViewById(R.id.et_bpc_no);
        et_bpc_issue_station= findViewById(R.id.et_bpc_issue_station);
        spn_bpc_valid_invalid = findViewById(R.id.spn_bpc_valid_invalid);
        et_load= findViewById(R.id.et_load);
        et_no_of_coaches= findViewById(R.id.et_no_of_coaches);
        et_comoddity = findViewById(R.id.et_comoddity);



        spn_type_of_coches = findViewById(R.id.spn_type_of_coches);
        spn_speedometer = findViewById(R.id.spn_speedometer);
        spn_vcd = findViewById(R.id.spn_vcd);
        spn_horns = findViewById(R.id.spn_horns);
        spn_sander  = findViewById(R.id.spn_sander);

        ch_ba   = findViewById(R.id.ch_ba);
        ch_gate  = findViewById(R.id.ch_gate);
        ch_auto  = findViewById(R.id.ch_auto);
        ch_bookinglooby  = findViewById(R.id.ch_bookinglooby);
        ch_cont  = findViewById(R.id.ch_cont);
        ch_cug  = findViewById(R.id.ch_cug);
        ch_nrt  = findViewById(R.id.ch_nrt);
        ch_runningroom  = findViewById(R.id.ch_runningroom);
        ch_shunting  = findViewById(R.id.ch_shunting);
        ch_speedgun  = findViewById(R.id.ch_speedgun);
        ch_yard  = findViewById(R.id.ch_yard);


        et_weak_area_lp= findViewById(R.id.et_weak_area_lp);
        btn_filter = findViewById(R.id.btn_filter);

        tv_filters = findViewById(R.id.tv_filters);
        tv_ambush = findViewById(R.id.tv_ambush);
        action_bar_title = findViewById(R.id.action_bar_title);
        iv_title_icon = findViewById(R.id.iv_title_icon);
        iv_title_icon.setImageResource(R.drawable.iv_back);
        action_bar_title.setText(getResources().getString(R.string.inspection_record_title));
        tv_filters.setTypeface(font);
        action_bar_title.setTypeface(font);
        tv_filters.setText(getResources().getString(R.string.inspection_record_title));

        iv_title_icon.setOnClickListener(this);
        btn_filter.setOnClickListener(this);

        List<Railway> railwayList = dataBaseManager.getRailwayList(true);
        spn_ref_no.setAdapter(new SpinnerAdapter(LiInspectionController.this, CommonClass.getDemoValues("Select Ref. No")));
        spn_cab.setAdapter(new SpinnerAdapter(LiInspectionController.this, CommonClass.getDemoValues("Select Cab")));
        spn_train_type.setAdapter(new SpinnerAdapter(LiInspectionController.this, CommonClass.getDemoValues("Select Train Type")));
        spn_bpc_valid_invalid.setAdapter(new SpinnerAdapter(LiInspectionController.this, CommonClass.getDemoValid("Select BPC")));
        spn_type_of_coches.setAdapter(new SpinnerAdapter(LiInspectionController.this, CommonClass.getDemoValues("Select Coach Type")));
        spn_speedometer.setAdapter(new SpinnerAdapter(LiInspectionController.this, CommonClass.getDemoWorking("Select Speedometer Status")));
        spn_vcd.setAdapter(new SpinnerAdapter(LiInspectionController.this, CommonClass.getDemoWorking("Select VCD Status")));
        spn_horns.setAdapter(new SpinnerAdapter(LiInspectionController.this, CommonClass.getDemoWorking("Select Horns Status")));
        spn_sander.setAdapter(new SpinnerAdapter(LiInspectionController.this, CommonClass.getDemoWorking("Select Sander Status")));

    }

    @Override
    public void onClick(View v) {

    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }





    private void callWebService(int cat) {

    }





    @Override
    public void ResponseOk(Object object, int position) {

    }

    @Override
    public void Error() {
        commonClass.showToast("Unable to fetch data. Please try again.");
    }

    @Override
    public void dismissProgress() {
        commonClass.dismissDialog();

    }

    @Override
    public void showProgress(String msg) {
        commonClass.showProgressBar(msg);
    }



    public DatePickerDialog showDialog() {
        final Calendar calendar = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(LiInspectionController.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                String dayOfMonth;
                String monthOfYear;
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, day);
                if (day < 10) {
                    dayOfMonth = "0" + day;
                } else {
                    dayOfMonth = "" + day;
                }
                if (month + 1 < 10) {
                    monthOfYear = "0" + (month + 1);
                } else {
                    monthOfYear = "" + (month + 1);
                }

                //spn_date.setText(dayOfMonth + "/" + monthOfYear + "/" + year);
                //spn_date.setText(year + "-" + monthOfYear + "-" + dayOfMonth);

            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.getDatePicker().setMaxDate(new Date().getTime());

        return datePickerDialog;
    }


}
