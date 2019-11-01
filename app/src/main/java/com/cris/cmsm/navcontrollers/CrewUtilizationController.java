package com.cris.cmsm.navcontrollers;

import android.app.DatePickerDialog;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatSpinner;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.cris.cmsm.BaseActivity;
import com.cris.cmsm.R;
import com.cris.cmsm.adapter.DepartmentAdapter;
import com.cris.cmsm.adapter.DesignationAdapter;
import com.cris.cmsm.adapter.DivisonAdapter;
import com.cris.cmsm.adapter.LobbyAdapter;
import com.cris.cmsm.adapter.MonthAdapter;
import com.cris.cmsm.adapter.RailwayAdapter;
import com.cris.cmsm.adapter.SSEAdapter;
import com.cris.cmsm.adapter.SpinnerAdapter;
import com.cris.cmsm.database.DataBaseManager;
import com.cris.cmsm.database.DataHolder;
import com.cris.cmsm.models.Department;
import com.cris.cmsm.models.Designation;
import com.cris.cmsm.models.Division;
import com.cris.cmsm.models.Lobby;
import com.cris.cmsm.models.Month;
import com.cris.cmsm.models.Railway;
import com.cris.cmsm.models.request.ConSummaryRequest;
import com.cris.cmsm.models.request.GraphAPIRequest;
import com.cris.cmsm.models.request.RequestSSERole;
import com.cris.cmsm.models.request.SSConsumptionRequest;
import com.cris.cmsm.models.response.CrewUtilResponse;
import com.cris.cmsm.models.response.CrewUtilResponseVO;
import com.cris.cmsm.models.response.LoginIfoVO;
import com.cris.cmsm.models.response.ResSSConsumption;
import com.cris.cmsm.models.response.ResponseSSERole;
import com.cris.cmsm.prefrences.UserLoginPreferences;
import com.cris.cmsm.presenter.MonthlyRequestPresenter;
import com.cris.cmsm.presenter.RequestPresenter;
import com.cris.cmsm.presenterview.ResponseView;
import com.cris.cmsm.util.CommonClass;
import com.cris.cmsm.util.Constants;
import com.cris.cmsm.util.FontFamily;
import com.google.gson.Gson;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 *
 */

public class CrewUtilizationController extends BaseActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener, ResponseView {

    private ImageView iv_title_icon;
    private TextView action_bar_title, tv_filters;
    private Typeface font;
    private AppCompatSpinner spn_ryCode, spn_divCode, spn_lobbyCode, spn_desigCode, spn_fYear, spn_month ;
    private EditText spn_date;
    private Button btn_filter;
    private CommonClass commonClass;
    private DataBaseManager dataBaseManager;
    private LoginIfoVO loginUser;
    private MonthlyRequestPresenter monthlyRequestPresenter;
    private RequestPresenter requestPresenter;
    private LoginIfoVO loginInfoModel;
    private UserLoginPreferences userLoginPreferences;
    List<Division> divisionList = null;
    private List<Lobby> lobby_list;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crew_utilization);
        monthlyRequestPresenter = new MonthlyRequestPresenter(CrewUtilizationController.this);
        requestPresenter = new RequestPresenter(CrewUtilizationController.this);
        userLoginPreferences = new UserLoginPreferences(CrewUtilizationController.this);
        loginInfoModel = userLoginPreferences.getLoginUser();
        loginUser = new UserLoginPreferences(CrewUtilizationController.this).getLoginUser();
        commonClass = new CommonClass(CrewUtilizationController.this);
        dataBaseManager = new DataBaseManager(CrewUtilizationController.this);
        dataBaseManager.createDatabase();

        font = new FontFamily(CrewUtilizationController.this).getBold();
        spn_ryCode = findViewById(R.id.spn_ryCode);
        spn_divCode = findViewById(R.id.spn_divCode);
        spn_lobbyCode = findViewById(R.id.spn_lobbyCode);
        spn_desigCode= findViewById(R.id.spn_desigCode);
        spn_fYear = findViewById(R.id.spn_fYear);
        spn_date = findViewById(R.id.spn_date);
        spn_month = findViewById(R.id.spn_month);
        btn_filter = findViewById(R.id.btn_filter);

        tv_filters = findViewById(R.id.tv_filters);
        action_bar_title = findViewById(R.id.action_bar_title);
        iv_title_icon = findViewById(R.id.iv_title_icon);
        iv_title_icon.setImageResource(R.drawable.iv_back);
        action_bar_title.setText(getResources().getString(R.string.crew_utilization));
        tv_filters.setTypeface(font);
        action_bar_title.setTypeface(font);
        tv_filters.setText("Crew Utilization");

        iv_title_icon.setOnClickListener(this);
        btn_filter.setOnClickListener(this);

        List<Railway> railwayList = dataBaseManager.getRailwayList(true);
        spn_ryCode.setAdapter(new RailwayAdapter(CrewUtilizationController.this, railwayList));
        spn_divCode.setAdapter(new DivisonAdapter(CrewUtilizationController.this, dataBaseManager.getDivisionList("-100")));

        Lobby lobby = new Lobby();
        lobby.setDivcode("");
        lobby.setLobbycode("");
        lobby.setFname("All_Lobby");
        lobby.setSname("All_Lobby");
        lobby.setRlycode("");
        lobby.setId(0L);

        List<Lobby> lobby_list_tmp = new ArrayList<Lobby>();
        lobby_list_tmp.add(lobby);

        spn_desigCode.setAdapter(new DesignationAdapter(CrewUtilizationController.this, CommonClass.getDesignationList()));
        spn_lobbyCode.setAdapter(new LobbyAdapter(CrewUtilizationController.this, lobby_list_tmp));



        if(DataHolder.getType() == 0)
        {
            spn_month.setAdapter(new MonthAdapter(CrewUtilizationController.this, CommonClass.getMonthList()));
            spn_fYear.setAdapter(new SpinnerAdapter(CrewUtilizationController.this, CommonClass.getYear()));
            spn_date.setEnabled(false);
            //spn_date.setVisibility(View.INVISIBLE);
            spn_date.setVisibility(View.GONE);
        }
        else
        {



            spn_date.setFocusable(false);
            spn_date.setClickable(true);
            spn_date.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showDialog().show();
                }
            });


            spn_fYear.setEnabled(false);
            spn_fYear.setVisibility(View.INVISIBLE);
            spn_month.setEnabled(false);
            spn_month.setVisibility(View.INVISIBLE);

        }









        //setSseAdapter(new ArrayList<ResponseSSERole>());

        spn_ryCode.setSelection(commonClass.getUserRailwayIndex(railwayList, loginUser.getRlycode() != null ? loginUser.getRlycode() : ""));
        if (!TextUtils.isEmpty(loginUser.getAuthlevel()) && loginUser.getAuthlevel().equalsIgnoreCase(Constants.AUTH_BOARD)) {
            spn_ryCode.setEnabled(true);
            spn_lobbyCode.setEnabled(true);
        } else {
            spn_ryCode.setEnabled(false);
            spn_lobbyCode.setEnabled(false);
        }

        spn_ryCode.setOnItemSelectedListener(this);
        spn_divCode.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id)
            {
                if(!((Division) spn_divCode.getSelectedItem()).getDivcode().equals(""))
                {
                    callWebService(Constants.LOBBY_LIST);
                }
            }

            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });


        if (!TextUtils.isEmpty(loginUser.getAuthlevel()) && loginUser.getAuthlevel().equals(Constants.AUTH_ZONE))
        {
            spn_ryCode.setEnabled(false);
            spn_ryCode.setVisibility(View.INVISIBLE);
            spn_ryCode.setSelection(commonClass.getUserRailwayIndex(railwayList, loginInfoModel.getRlycode() != null ? loginInfoModel.getRlycode() : ""));

        }



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_title_icon:
                onBackPressed();
                break;
            case R.id.btn_filter:

                if(getIntent().getIntExtra(Constants.KEY, -1) == Constants.CREW_UTILIZATION)
                {
                    if( spn_fYear.getSelectedItem().equals("Select Year"))
                    {
                        commonClass.showToast("Please select a Year");
                    }else if(((Month) spn_month.getSelectedItem()).getMonthCode().equals(""))
                    {
                        commonClass.showToast("Please select a month");
                    }
                    else
                    {
                        if (CommonClass.checkInternetConnection(CrewUtilizationController.this)) {
                            callWebService(Constants.CREW_UTILIZATION);
                        } else {
                            commonClass.showToast("No internet available.");
                        }
                    }

                }
                else
                {
                    if( spn_date.getText().equals("Select Date"))
                    {
                        commonClass.showToast("Please select a fortnight date");
                    }
                    else
                    {
                        if (CommonClass.checkInternetConnection(CrewUtilizationController.this)) {
                            callWebService(Constants.CREW_UTILIZATION_FORTNIGHT);
                        } else {
                            commonClass.showToast("No internet available.");
                        }
                    }
                }



                break;
        }
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Object object = parent.getItemAtPosition(position);
        if (object instanceof Railway) {
            Railway railway = (Railway) object;
            List<Division> list = dataBaseManager.getDivisionList(railway.getRlycode());
            spn_divCode.setAdapter(new DivisonAdapter(CrewUtilizationController.this, list));
            spn_divCode.setSelection(commonClass.getDivisionIndex(list, loginUser.getDivcode() != null ? loginUser.getDivcode() : ""));
            if (!TextUtils.isEmpty(loginUser.getAuthlevel()) && (loginUser.getAuthlevel().equalsIgnoreCase(Constants.AUTH_BOARD)
                    || loginUser.getAuthlevel().equalsIgnoreCase(Constants.AUTH_CRIS) || loginUser.getAuthlevel().equalsIgnoreCase(Constants.AUTH_RAILWAY))) {

                divisionList = dataBaseManager.getDivisionList(railway.getRlycode());
                spn_divCode.setAdapter(new DivisonAdapter(CrewUtilizationController.this, divisionList));
                spn_divCode.setSelection(commonClass.getDivisionIndex(divisionList, loginUser.getDivcode() != null ? loginUser.getDivcode() : ""));
                spn_divCode.setEnabled(true);

                spn_divCode.setEnabled(true);
            }  else if (!TextUtils.isEmpty(loginUser.getAuthlevel()) && loginUser.getAuthlevel().equals(Constants.AUTH_DIVISION))
            {
                spn_ryCode.setEnabled(false);
                spn_ryCode.setVisibility(View.INVISIBLE);
                //spn_divCode.setEnabled(false);
                spn_divCode.setVisibility(View.INVISIBLE);

                divisionList = dataBaseManager.getDivisionListByDivCode(loginInfoModel.getRlycode());
                spn_divCode.setAdapter(new DivisonAdapter(CrewUtilizationController.this, divisionList));
                spn_divCode.setEnabled(true);
                callWebService(Constants.LOBBY_LIST);
            }
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }





    private void callWebService(int cat) {
        try {



            GraphAPIRequest request = new GraphAPIRequest();



            if(cat == Constants.CREW_UTILIZATION)
            {
               //System.out.println("Division Code :>>> " + ((Division) spn_divCode.getSelectedItem()).getDivcode());
               //System.out.println("Division Code Trim :>>> " + ((Division) spn_divCode.getSelectedItem()).getDivcode().trim());

               request.setRailwayCode(((Railway) spn_ryCode.getSelectedItem()).getRlycode().trim());
               request.setDivisionCode(((Division) spn_divCode.getSelectedItem()).getDivcode().trim());
               request.setLobbyCode(((Lobby) spn_lobbyCode.getSelectedItem()).getLobbycode().trim());
               request.setDesignation(((Designation) spn_desigCode.getSelectedItem()).getDesignationCode().trim());
               request.setMonth(((Month) spn_month.getSelectedItem()).getMonthCode().trim());
               request.setFYYear(((String) spn_fYear.getSelectedItem()).trim());
               request.setFlag("M");

               DataHolder.getInstance().setGraphAPIRequest(request);
               monthlyRequestPresenter.Request(request, "Getting Crew Utilization Report", Constants.CREW_UTILIZATION);

            }else  if(cat == Constants.CREW_UTILIZATION_FORTNIGHT)
            {
                //System.out.println("Fort Division Code :>>> " + ((Division) spn_divCode.getSelectedItem()).getDivcode());
                //System.out.println(" Fort Division Code Trim :>>> " + ((Division) spn_divCode.getSelectedItem()).getDivcode().trim());

                request.setRailwayCode(((Railway) spn_ryCode.getSelectedItem()).getRlycode().trim());
                request.setDivisionCode(((Division) spn_divCode.getSelectedItem()).getDivcode().trim());
                request.setLobbyCode(((Lobby) spn_lobbyCode.getSelectedItem()).getLobbycode().trim());
                request.setDesignation(((Designation) spn_desigCode.getSelectedItem()).getDesignationCode().trim());
                request.setMonth("");
                request.setFYYear("");
                request.setProgDate(spn_date.getText().toString().trim());
                request.setFlag("P");
                DataHolder.getInstance().setGraphAPIRequest(request);
                monthlyRequestPresenter.Request(request, "Getting Crew Utilization Report", Constants.CREW_UTILIZATION);

            }
            else
            {
                ConSummaryRequest conrequest = new ConSummaryRequest();
                conrequest.setRailway(((Railway) spn_ryCode.getSelectedItem()).getRlycode());
                conrequest.setDivision(((Division) spn_divCode.getSelectedItem()).getDivcode());

                requestPresenter.Request(conrequest, "Getting Lobby List", Constants.LOBBY_LIST);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }





    @Override
    public void ResponseOk(Object object, int position) {
        switch (position) {

            case Constants.LOBBY_LIST:

                lobby_list = (List<Lobby>) object;
                spn_lobbyCode.setAdapter(new LobbyAdapter(CrewUtilizationController.this, lobby_list));
                spn_lobbyCode.setSelection(commonClass.getLobbyIndex(lobby_list, loginUser.getDivcode() != null ? loginUser.getDivcode() : ""));
                spn_lobbyCode.setEnabled(true);
                break;


            case Constants.CREW_UTILIZATION:
                CrewUtilResponse crewUtilResponse = (CrewUtilResponse) object;
                if (crewUtilResponse != null && crewUtilResponse.getCrewUtilResponseVO() != null) {
                    DataHolder.getInstance().setCrewUtilResponse(crewUtilResponse);
                    CommonClass.goToNextScreen(CrewUtilizationController.this, CrewUtilizationReportController.class, false, false);
                } else
                    commonClass.showToast("No data available.");
                break;

            case Constants.CREW_UTILIZATION_FORTNIGHT:
                CrewUtilResponse crewUtilFortResponse = (CrewUtilResponse) object;
                if (crewUtilFortResponse != null && crewUtilFortResponse.getCrewUtilResponseVO() != null) {
                    DataHolder.getInstance().setCrewUtilResponse(crewUtilFortResponse);
                    CommonClass.goToNextScreen(CrewUtilizationController.this, CrewUtilizationReportController.class, false, false);
                } else
                    commonClass.showToast("No data available.");
                break;
        }
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
        android.app.DatePickerDialog datePickerDialog = new android.app.DatePickerDialog(CrewUtilizationController.this, new android.app.DatePickerDialog.OnDateSetListener() {
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
                      spn_date.setText(year + "-" + monthOfYear + "-" + dayOfMonth);

            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.getDatePicker().setMaxDate(new Date().getTime());

        return datePickerDialog;
    }


}
