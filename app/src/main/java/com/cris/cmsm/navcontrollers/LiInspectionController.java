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
import android.widget.TextView;

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
    private TextView action_bar_title, tv_filters;
    private Typeface font;
    private AppCompatSpinner spn_ref_no, spn_cab, spn_train_type;
    private EditText et_lp_id,et_alp_id;
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
        spn_train_type = findViewById(R.id.spn_train_type);
        spn_cab = findViewById(R.id.spn_cab);

        btn_filter = findViewById(R.id.btn_filter);

        tv_filters = findViewById(R.id.tv_filters);
        action_bar_title = findViewById(R.id.action_bar_title);
        iv_title_icon = findViewById(R.id.iv_title_icon);
        iv_title_icon.setImageResource(R.drawable.iv_back);
        action_bar_title.setText(getResources().getString(R.string.inspection_record_title));
        tv_filters.setTypeface(font);
        action_bar_title.setTypeface(font);
        tv_filters.setText("Crew Utilization");

        iv_title_icon.setOnClickListener(this);
        btn_filter.setOnClickListener(this);

        List<Railway> railwayList = dataBaseManager.getRailwayList(true);
        spn_ref_no.setAdapter(new SpinnerAdapter(LiInspectionController.this, CommonClass.getDemoValues("Select Ref. No")));
        spn_cab.setAdapter(new SpinnerAdapter(LiInspectionController.this, CommonClass.getDemoValues("Select Cab")));
        spn_train_type.setAdapter(new SpinnerAdapter(LiInspectionController.this, CommonClass.getDemoValues("Select Train Type")));



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
