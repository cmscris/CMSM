package com.cris.cmsm.navcontrollers;

import android.app.Activity;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatSpinner;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cris.cmsm.BaseActivity;
import com.cris.cmsm.R;
import com.cris.cmsm.adapter.DesignationAdapter;
import com.cris.cmsm.adapter.DivisonAdapter;
import com.cris.cmsm.adapter.LobbyAdapter;
import com.cris.cmsm.adapter.RailwayAdapter;
import com.cris.cmsm.database.DataBaseManager;
import com.cris.cmsm.database.DataHolder;
import com.cris.cmsm.interactor.WebServices;
import com.cris.cmsm.interfaces.IBilling;
import com.cris.cmsm.interfaces.IConsumption;
import com.cris.cmsm.interfaces.IOnFragmentVisible;
import com.cris.cmsm.models.Designation;
import com.cris.cmsm.models.Division;
import com.cris.cmsm.models.Lobby;
import com.cris.cmsm.models.Month;
import com.cris.cmsm.models.Railway;
import com.cris.cmsm.models.request.ConSummaryRequest;
import com.cris.cmsm.models.request.GraphAPIRequest;
import com.cris.cmsm.models.response.BillingResponse;
import com.cris.cmsm.models.response.ConsumptionResponse;
import com.cris.cmsm.models.response.CrewPositionSummaryResponse;
import com.cris.cmsm.models.response.LoginIfoVO;
import com.cris.cmsm.prefrences.UserLoginPreferences;
import com.cris.cmsm.presenter.RequestPresenter;
import com.cris.cmsm.presenterview.ResponseView;
import com.cris.cmsm.tabviews.CrewAvailabilityFragment;
import com.cris.cmsm.util.CommonClass;
import com.cris.cmsm.util.Constants;
import com.cris.cmsm.util.FontFamily;
import com.cris.cmsm.widget.JitinRLSlidingLayout;
import com.cris.cmsm.widget.NonSwipeViewPager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rangasanju on 05-03-2018.
 */


public class CrewAvailabilityController extends BaseActivity implements ResponseView, View.OnClickListener {

    private TabLayout tabs;
    private NonSwipeViewPager pager;
    private List<String> tabList;
    private JitinRLSlidingLayout slidingPaneLayout;
    private ImageView iv_title_icon, iv_right;
    private TextView action_bar_title, tv_filters;
    private Typeface font;
    private AppCompatSpinner spn_desigCode, spn_ryCode, spn_divCode, spn_lobbyCode,  spn_department, spn_fYear, spn_month;
    private Button btn_filter;
    private CommonClass commonClass;
    private RequestPresenter requestPresenter;
    private LoginIfoVO loginUser;
    private CreatePageAdapter pageAdapter;
    private LinearLayout showProgress;
    private TextView tv_header_name;
    private IConsumption iConsumption;
    private IBilling iBilling;
    private DataBaseManager dataBaseManager;
    private List<Lobby> lobby_list;
    private LoginIfoVO loginInfoModel;
    private UserLoginPreferences userLoginPreferences;
    List<Railway> railwayList = null;
    List<Division> divisionList = null;



    public void initializeConsumption(IConsumption iConsumption) {
        this.iConsumption = iConsumption;
    }

    public void initializeBilling(IBilling iBilling) {
        this.iBilling = iBilling;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graphs);
        requestPresenter = new RequestPresenter(CrewAvailabilityController.this);
        loginUser = new UserLoginPreferences(CrewAvailabilityController.this).getLoginUser();
        commonClass = new CommonClass(CrewAvailabilityController.this);

        dataBaseManager = new DataBaseManager(CrewAvailabilityController.this);
        dataBaseManager.createDatabase();
        userLoginPreferences = new UserLoginPreferences(CrewAvailabilityController.this);
        loginInfoModel = userLoginPreferences.getLoginUser();
        font = new FontFamily(CrewAvailabilityController.this).getBold();
        showProgress = findViewById(R.id.showProgress);
        spn_desigCode= findViewById(R.id.spn_desigCode);
        spn_ryCode = findViewById(R.id.spn_ryCode);
        spn_divCode = findViewById(R.id.spn_divCode);
        spn_lobbyCode = findViewById(R.id.spn_lobbyCode);
        spn_department = findViewById(R.id.spn_department);
        spn_fYear = findViewById(R.id.spn_fYear);
        spn_month = findViewById(R.id.spn_month);
        btn_filter = findViewById(R.id.btn_filter);


        spn_month.setVisibility(View.GONE);
        spn_fYear.setVisibility(View.GONE);


        tv_header_name = findViewById(R.id.tv_header_name);
        tv_filters = findViewById(R.id.tv_filters);
        action_bar_title = findViewById(R.id.action_bar_title);
        iv_title_icon = findViewById(R.id.iv_title_icon);
        iv_right = findViewById(R.id.iv_right);

        slidingPaneLayout = findViewById(R.id.slidingPaneLayout);
        slidingPaneLayout.setParallaxDistance(200);
        slidingPaneLayout.setShadowDrawableLeft(getResources().getDrawable(R.drawable.nav_bar_shadow));
        slidingPaneLayout.setSliderFadeColor(getResources().getColor(android.R.color.transparent));


        iv_right.setImageResource(R.drawable.filter);
        iv_right.setVisibility(View.VISIBLE);
        iv_title_icon.setImageResource(R.drawable.iv_back);
        action_bar_title.setText(getResources().getString(R.string.railway_wise_crew_availability));
        tv_filters.setTypeface(font);
        action_bar_title.setTypeface(font);
        tv_header_name.setVisibility(View.VISIBLE);
        tv_header_name.setTypeface(font);

        /********* Spinner Adapters ********/
        List<Railway> railwayList = null;
        if (loginUser.getAuthlevel().equals(Constants.AUTH_BOARD)) {
            railwayList = dataBaseManager.getRailwayList(true);
        } else {
            railwayList = dataBaseManager.getRailwayList(false);
        }
        spn_ryCode.setAdapter(new RailwayAdapter(CrewAvailabilityController.this, railwayList));





        /********* Spinner Adapters ********/
        spn_desigCode.setAdapter(new DesignationAdapter(CrewAvailabilityController.this, CommonClass.getDesignationList()));


        iv_right.setOnClickListener(this);
        iv_title_icon.setOnClickListener(this);
        btn_filter.setOnClickListener(this);


        tabList = new ArrayList<>();
        tabList.add("Crew Availability");
        //tabList.add("Future");
        tabs = findViewById(R.id.tabs);
        pager = findViewById(R.id.pager);
        pageAdapter = new CreatePageAdapter(getSupportFragmentManager());
        pager.setAdapter(pageAdapter);
        tabs.setupWithViewPager(pager);
        pager.setOffscreenPageLimit(1);
        pager.setPagingEnabled(false);
        pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                IOnFragmentVisible fragment = null;
                if (position == 0) {
                    fragment = (CrewAvailabilityFragment) pageAdapter.instantiateItem(pager, position);
                }

                if (fragment != null) {
                    fragment.fragmentBecameVisible();
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });



        spn_ryCode.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id)
            {

                if(!((Railway) spn_ryCode.getSelectedItem()).getRlycode().equals(""))
                {
                    Railway railway = (Railway) spn_ryCode.getSelectedItem();

                    List<Division> list = dataBaseManager.getDivisionList(railway.getRlycode());
                    spn_divCode.setAdapter(new DivisonAdapter(CrewAvailabilityController.this, list));
                    spn_divCode.setSelection(commonClass.getDivisionIndex(list, loginUser.getDivcode() != null ? loginUser.getDivcode() : ""));
                    if (!TextUtils.isEmpty(loginUser.getAuthlevel()) && (loginUser.getAuthlevel().equalsIgnoreCase(Constants.AUTH_BOARD)
                            || loginUser.getAuthlevel().equalsIgnoreCase(Constants.AUTH_RAILWAY))) {


                        divisionList = dataBaseManager.getDivisionList(railway.getRlycode());
                        spn_divCode.setAdapter(new DivisonAdapter(CrewAvailabilityController.this, divisionList));
                        spn_divCode.setSelection(commonClass.getDivisionIndex(divisionList, loginUser.getDivcode() != null ? loginUser.getDivcode() : ""));
                        spn_divCode.setEnabled(true);


                    }  else if (!TextUtils.isEmpty(loginUser.getAuthlevel()) && loginUser.getAuthlevel().equals(Constants.AUTH_DIVISION))
                    {
                        spn_ryCode.setEnabled(false);
                        spn_ryCode.setVisibility(View.INVISIBLE);
                        spn_divCode.setEnabled(false);
                        spn_divCode.setVisibility(View.INVISIBLE);

                        divisionList = dataBaseManager.getDivisionListByDivCode(loginInfoModel.getRlycode());
                        spn_divCode.setAdapter(new DivisonAdapter(CrewAvailabilityController.this, divisionList));
                        spn_divCode.setEnabled(true);
                        callWebService(Constants.LOBBY_LIST);
                    }
                }

            }

            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });


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



        DataHolder.setLevel(0);



        // HIDE RAILWAY CODE IF AUTH LEVEL IS NOT BOARD
        if (!TextUtils.isEmpty(loginUser.getAuthlevel()) && loginUser.getAuthlevel().equals(Constants.AUTH_ZONE))
        {
            spn_ryCode.setEnabled(false);
            spn_ryCode.setVisibility(View.INVISIBLE);
            spn_ryCode.setSelection(commonClass.getUserRailwayIndex(railwayList, loginInfoModel.getRlycode() != null ? loginInfoModel.getRlycode() : ""));

        }


        // OPEN THE FILETR PANE ON LOAD
        slidingPaneLayout.openPane();
        //callWebService(Constants.CREW_AVAILABILITY);
    }

    @Override
    public void ResponseOk(Object object, int position) {
        try {


            if(position == Constants.CREW_AVAILABILITY)
            {
                ConsumptionResponse response = (ConsumptionResponse) object;
                iConsumption.showConsumptionResponse(response);
            }
            else
            {

                lobby_list = (List<Lobby>) object;
                spn_lobbyCode.setAdapter(new LobbyAdapter(CrewAvailabilityController.this, lobby_list));
                spn_lobbyCode.setSelection(commonClass.getLobbyIndex(lobby_list, loginUser.getDivcode() != null ? loginUser.getDivcode() : ""));
                spn_lobbyCode.setEnabled(true);

            }


        } catch (Exception e) {


            e.printStackTrace();
        }
    }

    @Override
    public void Error() {
        retryDialog(CrewAvailabilityController.this, "Unable to fetch record. Do you want to retry?");
    }

    @Override
    public void dismissProgress() {
        iv_right.setEnabled(true);
        showProgress.setVisibility(View.GONE);

    }

    @Override
    public void showProgress(String msg) {
        if (!showProgress.isShown()) {
            iv_right.setEnabled(false);
            showProgress.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_title_icon:
                if (slidingPaneLayout.isOpen()) {
                    slidingPaneLayout.closePane();
                } else {
                    onBackPressed();
                }
                break;
            case R.id.iv_right:
                if (slidingPaneLayout.isOpen()) {
                    slidingPaneLayout.closePane();
                } else {
                    slidingPaneLayout.openPane();
                }
                break;
            case R.id.btn_filter:
                if (CommonClass.checkInternetConnection(CrewAvailabilityController.this)) {
                    if (slidingPaneLayout.isOpen()) {
                        slidingPaneLayout.closePane();
                    }
                    DataHolder.getInstance().setRailWayConsumption(null);
                    DataHolder.getInstance().setRailWayBilling(null);
                    callWebService(Constants.CREW_AVAILABILITY);
                } else {
                    commonClass.showToast("No internet available.");
                }
                break;
        }
    }


    public void callWebService(int cat) {
        try {
            ConSummaryRequest request = new ConSummaryRequest();


            request.setRailway(((Railway) spn_ryCode.getSelectedItem()).getRlycode());
            if(spn_divCode.getSelectedItem() != null)
                request.setDivision(((Division) spn_divCode.getSelectedItem()).getDivcode());

            request.setDesignation(((Designation) spn_desigCode.getSelectedItem()).getDesignationCode());


            if(cat == Constants.CREW_AVAILABILITY)
            {

                if(spn_lobbyCode.getSelectedItem() != null)
                    request.setLobby(((Lobby) spn_lobbyCode.getSelectedItem()).getLobbycode());

                if(((Railway) spn_ryCode.getSelectedItem()).getRlycode().trim().equals(""))
                    DataHolder.setLevel(0);
                else if(spn_divCode.getSelectedItem() == null || ((Division) spn_divCode.getSelectedItem()).getDivcode().trim().equals(""))
                    DataHolder.setLevel(1);
                else if(spn_lobbyCode.getSelectedItem() == null || ((Lobby) spn_lobbyCode.getSelectedItem()).getLobbycode().trim().equals(""))
                    DataHolder.setLevel(2);
                else
                    DataHolder.setLevel(3);


                //commonClass.showToast("Level  " + DataHolder.getLevel());

                //commonClass.showToast("Zone " + ((Railway) spn_ryCode.getSelectedItem()).getRlycode());
                //commonClass.showToast("Division " + ((Division) spn_divCode.getSelectedItem()).getDivcode());
                //commonClass.showToast("Lobby " + ((Lobby) spn_lobbyCode.getSelectedItem()).getLobbycode());
                requestPresenter.Request(request, "Getting Data For CrewPosition", Constants.CREW_AVAILABILITY);

            }
            else
            {
                Log.d(">>>>>>>>",((Division) spn_divCode.getSelectedItem()).getDivcode());
                request.setDivision(((Division) spn_divCode.getSelectedItem()).getDivcode());
                requestPresenter.Request(request, "Getting Lobby List", Constants.LOBBY_LIST);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }




    @Override
    public void onBackPressed() {
        if (slidingPaneLayout.isOpen()) {
            slidingPaneLayout.closePane();
        } else {
            //DataHolder.getInstance().setBoardStateModel(null);
            DataHolder.setLevel(DataHolder.getLevel() - 1);
            if(DataHolder.getLevel() < 0)
                DataHolder.setLevel(0);

            WebServices.getInstance().cancelAllRequest();
            super.onBackPressed();
        }

    }


    private class CreatePageAdapter extends FragmentStatePagerAdapter {

        public CreatePageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            return new CrewAvailabilityFragment();

        }


        @Override
        public int getCount() {
            return tabList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabList.get(position);
        }

        @Override
        public void notifyDataSetChanged() {
            super.notifyDataSetChanged();
        }

    }


    public void retryDialog(final Activity activity, String msg) {
        try {
            new AlertDialog.Builder(activity).setCancelable(false)
                    .setTitle(getResources().getString(R.string.cms))
                    .setMessage(msg)
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            callWebService(Constants.CREW_AVAILABILITY);
                        }
                    })
                    .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            activity.finish();
                        }
                    })
                    .setIcon(R.drawable.icon_logo)
                    .show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
