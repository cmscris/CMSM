package com.cris.cmsm.navcontrollers;

        import android.graphics.Typeface;
        import android.os.Bundle;
        import android.support.annotation.Nullable;
        import android.support.v7.widget.AppCompatSpinner;
        import android.text.TextUtils;
        import android.util.Log;
        import android.view.View;
        import android.widget.AdapterView;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.ImageView;
        import android.widget.TextView;

        import com.cris.cmsm.BaseActivity;
        import com.cris.cmsm.R;
        import com.cris.cmsm.adapter.DivisonAdapter;
        import com.cris.cmsm.adapter.LobbyAdapter;
        import com.cris.cmsm.adapter.MonthAdapter;
        import com.cris.cmsm.adapter.RailwayAdapter;
        import com.cris.cmsm.adapter.SpinnerAdapter;
        import com.cris.cmsm.database.DataBaseManager;
        import com.cris.cmsm.database.DataHolder;
        import com.cris.cmsm.models.Division;
        import com.cris.cmsm.models.Lobby;
        import com.cris.cmsm.models.Month;
        import com.cris.cmsm.models.Railway;
        import com.cris.cmsm.models.request.ConSummaryRequest;
        import com.cris.cmsm.models.request.GraphAPIRequest;
        import com.cris.cmsm.models.request.RequestSSERole;
        import com.cris.cmsm.models.response.AbnormalityResponse;
        import com.cris.cmsm.models.response.CrewUtilResponse;
        import com.cris.cmsm.models.response.IrregularCrewResponse;
        import com.cris.cmsm.models.response.LoginIfoVO;
        import com.cris.cmsm.prefrences.UserLoginPreferences;
        import com.cris.cmsm.presenter.MonthlyRequestPresenter;
        import com.cris.cmsm.presenter.RequestPresenter;
        import com.cris.cmsm.presenterview.ResponseView;
        import com.cris.cmsm.util.CommonClass;
        import com.cris.cmsm.util.Constants;
        import com.cris.cmsm.util.FontFamily;

        import java.util.ArrayList;
        import java.util.List;

/**
 * Created by rangasanju on 3/16/18.
 */



public class IrregularCrewController extends BaseActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener, ResponseView {

    private ImageView iv_title_icon;
    private TextView action_bar_title, tv_filters;
    private Typeface font;
    private AppCompatSpinner spn_ryCode,        // Zone
                             spn_divCode,       // Division
                             spn_lobbyCode,     // Lobby
                             spn_fYear,         // Year     ( Not required in this case )
                             spn_month;         // Month    ( Not required in this case )

    private EditText spn_date;
    private Button btn_filter;
    private CommonClass commonClass;
    private DataBaseManager dataBaseManager;
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
        setContentView(R.layout.activity_crew_utilization);
        monthlyRequestPresenter = new MonthlyRequestPresenter(IrregularCrewController.this);
        requestPresenter = new RequestPresenter(IrregularCrewController.this);
        loginUser = new UserLoginPreferences(IrregularCrewController.this).getLoginUser();
        userLoginPreferences = new UserLoginPreferences(IrregularCrewController.this);
        loginInfoModel = userLoginPreferences.getLoginUser();
        commonClass = new CommonClass(IrregularCrewController.this);
        dataBaseManager = new DataBaseManager(IrregularCrewController.this);
        dataBaseManager.createDatabase();

        font = new FontFamily(IrregularCrewController.this).getBold();
        spn_ryCode = findViewById(R.id.spn_ryCode);
        spn_divCode = findViewById(R.id.spn_divCode);
        spn_lobbyCode = findViewById(R.id.spn_lobbyCode);
        spn_fYear = findViewById(R.id.spn_fYear);
        spn_date = findViewById(R.id.spn_date);
        spn_month = findViewById(R.id.spn_month);
        btn_filter = findViewById(R.id.btn_filter);

        tv_filters = findViewById(R.id.tv_filters);
        action_bar_title = findViewById(R.id.action_bar_title);
        iv_title_icon = findViewById(R.id.iv_title_icon);
        iv_title_icon.setImageResource(R.drawable.iv_back);
        action_bar_title.setText(getResources().getString(R.string.abnormality));
        tv_filters.setTypeface(font);
        action_bar_title.setTypeface(font);
        tv_filters.setText("Abnormality");

        iv_title_icon.setOnClickListener(this);
        btn_filter.setOnClickListener(this);


        // Not required in this case so hiding
        spn_date.setVisibility(View.GONE);
        spn_fYear.setVisibility(View.GONE);
        spn_month.setVisibility(View.GONE);


        // Load the Spinners with data
        List<Railway> railwayList = dataBaseManager.getRailwayList(true);
        spn_ryCode.setAdapter(new RailwayAdapter(IrregularCrewController.this, railwayList));
        spn_divCode.setAdapter(new DivisonAdapter(IrregularCrewController.this, dataBaseManager.getDivisionList("-100")));

        Lobby lobby = new Lobby();
        lobby.setDivcode("");
        lobby.setLobbycode("");
        lobby.setFname("All Lobby");
        lobby.setSname("All Lobby");
        lobby.setRlycode("");
        lobby.setId(0L);

        List<Lobby> lobby_list_tmp = new ArrayList<Lobby>();
        lobby_list_tmp.add(lobby);

        spn_lobbyCode.setAdapter(new LobbyAdapter(IrregularCrewController.this, lobby_list_tmp));



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



        // HIDE RAILWAY CODE IF AUTH LEVEL IS NOT BOARD
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


                if( spn_lobbyCode.getSelectedItem().equals("All Lobby"))
                {
                    commonClass.showToast("Please select a Lobby");
                }
                else
                {
                    if (CommonClass.checkInternetConnection(IrregularCrewController.this)) {
                        callWebService(Constants.IRREGULAR_CREW);
                    } else {
                        commonClass.showToast("No internet available.");
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
            spn_divCode.setAdapter(new DivisonAdapter(IrregularCrewController.this, list));
            spn_divCode.setSelection(commonClass.getDivisionIndex(list, loginUser.getDivcode() != null ? loginUser.getDivcode() : ""));
            if (!TextUtils.isEmpty(loginUser.getAuthlevel()) && (loginUser.getAuthlevel().equalsIgnoreCase(Constants.AUTH_BOARD)
                    || loginUser.getAuthlevel().equalsIgnoreCase(Constants.AUTH_RAILWAY))) {
                divisionList = dataBaseManager.getDivisionList(railway.getRlycode());
                spn_divCode.setAdapter(new DivisonAdapter(IrregularCrewController.this, divisionList));
                spn_divCode.setSelection(commonClass.getDivisionIndex(divisionList, loginUser.getDivcode() != null ? loginUser.getDivcode() : ""));
                spn_divCode.setEnabled(true);


            }  else if (!TextUtils.isEmpty(loginUser.getAuthlevel()) && loginUser.getAuthlevel().equals(Constants.AUTH_DIVISION))
            {
                spn_ryCode.setEnabled(false);
                spn_ryCode.setVisibility(View.INVISIBLE);
                spn_divCode.setEnabled(false);
                spn_divCode.setVisibility(View.INVISIBLE);

                divisionList = dataBaseManager.getDivisionListByDivCode(loginInfoModel.getRlycode());
                spn_divCode.setAdapter(new DivisonAdapter(IrregularCrewController.this, divisionList));
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

            if(cat == Constants.IRREGULAR_CREW)
            {
                // GET THE LOBBY CODE
                request.setLobbyCode(((Lobby) spn_lobbyCode.getSelectedItem()).getLobbycode());

                DataHolder.getInstance().setGraphAPIRequest(request);
                monthlyRequestPresenter.Request(request, "Getting Abnormality Report", Constants.IRREGULAR_CREW);

            }
            else    // GET THE LIST OF LOBBIES
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
                spn_lobbyCode.setAdapter(new LobbyAdapter(IrregularCrewController.this, lobby_list));
                spn_lobbyCode.setSelection(commonClass.getLobbyIndex(lobby_list, loginUser.getDivcode() != null ? loginUser.getDivcode() : ""));
                spn_lobbyCode.setEnabled(true);

                break;


            case Constants.IRREGULAR_CREW:
                IrregularCrewResponse irregularCrewResponse = (IrregularCrewResponse) object;
                if (irregularCrewResponse != null && irregularCrewResponse.getIrregularCrewList() != null) {
                    DataHolder.getInstance().setIrregularCrewResponse(irregularCrewResponse);
                    CommonClass.goToNextScreen(IrregularCrewController.this, AbnormalityReportController.class, false, false);
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
}
