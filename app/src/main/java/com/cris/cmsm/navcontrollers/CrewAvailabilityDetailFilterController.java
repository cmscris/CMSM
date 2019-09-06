package com.cris.cmsm.navcontrollers;

        import android.graphics.Typeface;
        import android.os.Bundle;
        import android.support.annotation.Nullable;
        import android.support.v7.widget.AppCompatSpinner;
        import android.support.v7.widget.LinearLayoutManager;
        import android.text.TextUtils;
        import android.view.View;
        import android.widget.AdapterView;
        import android.widget.Button;
        import android.widget.ImageView;
        import android.widget.TextView;

        import com.cris.cmsm.BaseActivity;
        import com.cris.cmsm.R;
        import com.cris.cmsm.adapter.CrewAvailabilityDetailAdapter;
        import com.cris.cmsm.adapter.DivisonAdapter;
        import com.cris.cmsm.adapter.GeneralKeyValueAdapter;
        import com.cris.cmsm.adapter.LobbyAdapter;
        import com.cris.cmsm.adapter.MonthAdapter;
        import com.cris.cmsm.adapter.RailwayAdapter;
        import com.cris.cmsm.adapter.SpinnerAdapter;
        import com.cris.cmsm.database.DataBaseManager;
        import com.cris.cmsm.database.DataHolder;
        import com.cris.cmsm.interactor.WebServices;
        import com.cris.cmsm.models.Division;
        import com.cris.cmsm.models.KeyValue;
        import com.cris.cmsm.models.Lobby;
        import com.cris.cmsm.models.Month;
        import com.cris.cmsm.models.Railway;
        import com.cris.cmsm.models.ReportHeaderView;
        import com.cris.cmsm.models.request.ConSummaryRequest;
        import com.cris.cmsm.models.request.CrewAvailabilityDetailRequest;
        import com.cris.cmsm.models.request.GraphAPIRequest;
        import com.cris.cmsm.models.response.CrewAvailabilityDetailResponse;
        import com.cris.cmsm.models.response.CrewAvailabilityDetailVO;
        import com.cris.cmsm.models.response.LoginIfoVO;
        import com.cris.cmsm.models.response.VcdStatusResponse;
        import com.cris.cmsm.prefrences.UserLoginPreferences;
        import com.cris.cmsm.presenter.MonthlyRequestPresenter;
        import com.cris.cmsm.presenter.RequestPresenter;
        import com.cris.cmsm.presenterview.ResponseView;
        import com.cris.cmsm.util.CommonClass;
        import com.cris.cmsm.util.Constants;
        import com.cris.cmsm.util.FontFamily;
        import com.cris.cmsm.widget.PinchRecyclerView;
        import com.google.gson.Gson;

        import java.util.ArrayList;
        import java.util.List;

/**
 * Created by rangasanju on 3/16/18.
 */



public class CrewAvailabilityDetailFilterController extends BaseActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener, ResponseView {


    private ImageView iv_title_icon;
    private TextView action_bar_title, tv_filters;
    private Typeface font;
    private AppCompatSpinner spn_crewavailtype,spn_cadre,spn_traction;//, spn_divCode, spn_lobbyCode, spn_fYear, spn_month;
    private Button btn_filter;
    private CommonClass commonClass;
    private LoginIfoVO loginUser;
    private MonthlyRequestPresenter monthlyRequestPresenter;
    private RequestPresenter requestPresenter;
    private LoginIfoVO loginInfoModel;
    private UserLoginPreferences userLoginPreferences;
    List<Railway> railwayList = null;
    List<Division> divisionList = null;
    private List<Lobby> lobby_list;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crew_availibility_detail);
        monthlyRequestPresenter = new MonthlyRequestPresenter(CrewAvailabilityDetailFilterController.this);
        requestPresenter = new RequestPresenter(CrewAvailabilityDetailFilterController.this);
        loginUser = new UserLoginPreferences(CrewAvailabilityDetailFilterController.this).getLoginUser();
        userLoginPreferences = new UserLoginPreferences(CrewAvailabilityDetailFilterController.this);
        loginInfoModel = userLoginPreferences.getLoginUser();
        commonClass = new CommonClass(CrewAvailabilityDetailFilterController.this);


        font = new FontFamily(CrewAvailabilityDetailFilterController.this).getBold();
        spn_crewavailtype = findViewById(R.id.spn_crewavailtype);
        spn_cadre = findViewById(R.id.spn_cadre);
        spn_traction = findViewById(R.id.spn_traction);

        btn_filter = findViewById(R.id.btn_filter);

        tv_filters = findViewById(R.id.tv_filters);
        action_bar_title = findViewById(R.id.action_bar_title);
        iv_title_icon = findViewById(R.id.iv_title_icon);
        iv_title_icon.setImageResource(R.drawable.iv_back);
        action_bar_title.setText(getResources().getString(R.string.vcd_status));
        tv_filters.setTypeface(font);
        action_bar_title.setTypeface(font);
        tv_filters.setText("Crew Availability Detail");

        iv_title_icon.setOnClickListener(this);
        btn_filter.setOnClickListener(this);


        spn_crewavailtype.setAdapter(new GeneralKeyValueAdapter(CrewAvailabilityDetailFilterController.this, CommonClass.getCrewAvailabilityType()));
        spn_cadre.setAdapter(new GeneralKeyValueAdapter(CrewAvailabilityDetailFilterController.this, CommonClass.getCadre()));
        spn_traction.setAdapter(new GeneralKeyValueAdapter(CrewAvailabilityDetailFilterController.this, CommonClass.getTraction()));

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_title_icon:
                onBackPressed();
                break;
            case R.id.btn_filter:

                DataHolder.setLevel(0);

                if (CommonClass.checkInternetConnection(CrewAvailabilityDetailFilterController.this)) {
                    callWebService(Constants.CREW_AVAILABILITY_DETAIL);

                } else {
                    commonClass.showToast("No internet available.");
                }


                break;
        }
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Object object = parent.getItemAtPosition(position);
        if (object instanceof Railway) {
            Railway railway = (Railway) object;

                callWebService(Constants.LOBBY_LIST);

        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }




    private void callWebService(int cat) {
        try {

            CrewAvailabilityDetailRequest request = new CrewAvailabilityDetailRequest();


            if(cat == Constants.CREW_AVAILABILITY_DETAIL)
            {
                request.setCrewAvailibilityType(((KeyValue) spn_crewavailtype.getSelectedItem()).getValue());
                request.setCadre(((KeyValue) spn_cadre.getSelectedItem()).getValue());
                request.setTraction(((KeyValue) spn_traction.getSelectedItem()).getValue());
                //request.setCrewType(loginInfoModel.getDesignation());
                request.setCrewType("'LPG'");
                request.setSlot("Slot Data");
                request.setOfficiating("OFFICIATING");
                request.setHqCode(loginInfoModel.getRlycode());
                requestPresenter.Request(request, "Getting Data", Constants.CREW_AVAILABILITY_DETAIL);

            }
            else
            {
                ConSummaryRequest conrequest = new ConSummaryRequest();
                //conrequest.setRailway(((Railway) spn_ryCode.getSelectedItem()).getRlycode());
                /*conrequest.setDivision(((Division) spn_divCode.getSelectedItem()).getDivcode());

                requestPresenter.Request(conrequest, "Getting Lobby List", Constants.LOBBY_LIST);*/
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }




    @Override
    public void ResponseOk(Object object, int position) {
        try {

            CrewAvailabilityDetailResponse crewDetailsResponse = (CrewAvailabilityDetailResponse) object;
            System.out.println("Response is >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> " + new Gson().toJson(crewDetailsResponse));
            loginInfoModel = userLoginPreferences.getLoginUser();


            switch(position)
            {

                case Constants.CREW_AVAILABILITY_DETAIL :
                    if (crewDetailsResponse != null && crewDetailsResponse.getCrewAvailabilityDetailVO() != null) {

                        DataHolder.setCrewid(loginInfoModel.getCrewid());

                        DataHolder.getInstance().setCrewAvailabilityDetailResponse(crewDetailsResponse);
                        CommonClass.goToNextScreen(CrewAvailabilityDetailFilterController.this, CrewAvailabilityDetailController.class, false, false);
                    }
                    break;
            }



        } catch (Exception e) {
            e.printStackTrace();
            commonClass.showToast("Unable to get data. Please try again.");
        }
    }


    @Override
    public void onBackPressed() {

        WebServices.getInstance().cancelAllRequest();
        if(DataHolder.getLevel() == 1)
        {
            //callWebService();           // CALL WEBSERVICE FOR CREW BIODATA
        }
        else
        {
            super.onBackPressed();
        }

    }



    @Override
    public void Error() {
        commonClass.showToast("Unable to get data. Please try again.");
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
