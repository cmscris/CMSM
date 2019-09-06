package com.cris.cmsm.navcontrollers;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatSpinner;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cris.cmsm.BaseActivity;
import com.cris.cmsm.R;
import com.cris.cmsm.adapter.DesignationAdapter;
import com.cris.cmsm.adapter.MonthAdapter;
import com.cris.cmsm.adapter.SpinnerAdapter;
import com.cris.cmsm.database.DataHolder;
import com.cris.cmsm.interactor.WebServices;
import com.cris.cmsm.interfaces.IConsumption;
import com.cris.cmsm.interfaces.IOnFragmentVisible;
import com.cris.cmsm.models.Month;
import com.cris.cmsm.models.request.EnergyConsumptionRequest;
import com.cris.cmsm.models.request.GraphAPIRequest;
import com.cris.cmsm.models.response.ConsumptionResponse;
import com.cris.cmsm.models.response.LoginIfoVO;
import com.cris.cmsm.prefrences.UserLoginPreferences;
import com.cris.cmsm.presenter.RequestPresenter;
import com.cris.cmsm.presenterview.ResponseView;
import com.cris.cmsm.tabviews.EnergyConsumptionFragment;
import com.cris.cmsm.util.CommonClass;
import com.cris.cmsm.util.Constants;
import com.cris.cmsm.util.FontFamily;
import com.cris.cmsm.widget.JitinRLSlidingLayout;
import com.cris.cmsm.widget.NonSwipeViewPager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cms on 3/12/18.
 */


public class EnergyConsumptionController extends BaseActivity implements ResponseView, View.OnClickListener {

    private TabLayout tabs;
    private NonSwipeViewPager pager;
    private List<String> tabList;
    private JitinRLSlidingLayout slidingPaneLayout;
    private ImageView iv_title_icon, iv_right;
    private TextView action_bar_title, tv_filters;
    private Typeface font;
    private AppCompatSpinner spn_desigCode, spn_ryCode, spn_divCode, spn_traction, spn_fYear, spn_month;
    private Button btn_filter;
    private CommonClass commonClass;
    private RequestPresenter requestPresenter;
    private LoginIfoVO loginUser;
    private CreatePageAdapter pageAdapter;
    private LinearLayout showProgress;
    private TextView tv_header_name;
    private IConsumption iConsumption;



    public void initializeConsumption(IConsumption iConsumption) {
        this.iConsumption = iConsumption;
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graphs);
        requestPresenter = new RequestPresenter(EnergyConsumptionController.this);
        loginUser = new UserLoginPreferences(EnergyConsumptionController.this).getLoginUser();




        Intent intent = getIntent();
        int posValue = intent.getIntExtra(Constants.KEY, -1);



        commonClass = new CommonClass(EnergyConsumptionController.this);

        font = new FontFamily(EnergyConsumptionController.this).getBold();
        showProgress = findViewById(R.id.showProgress);
        spn_desigCode= findViewById(R.id.spn_desigCode);
        spn_ryCode = findViewById(R.id.spn_ryCode);
        spn_divCode = findViewById(R.id.spn_divCode);
        spn_traction = findViewById(R.id.spn_traction);
        spn_fYear = findViewById(R.id.spn_fYear);
        spn_month = findViewById(R.id.spn_month);
        btn_filter = findViewById(R.id.btn_filter);

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
        action_bar_title.setText(getResources().getString(R.string.energy_consumption));
        tv_filters.setTypeface(font);
        action_bar_title.setTypeface(font);
        tv_header_name.setVisibility(View.VISIBLE);
        tv_header_name.setTypeface(font);

        /********* Spinner Adapters ********/

        if(DataHolder.getLevel() == 0)
        {
            spn_month.setAdapter(new MonthAdapter(EnergyConsumptionController.this, CommonClass.getMonthList()));
            spn_fYear.setAdapter(new SpinnerAdapter(EnergyConsumptionController.this, CommonClass.getYear()));
            spn_traction.setAdapter(new SpinnerAdapter(EnergyConsumptionController.this, CommonClass.getTractionType()));

        }

        iv_right.setOnClickListener(this);
        iv_title_icon.setOnClickListener(this);
        btn_filter.setOnClickListener(this);


        tabList = new ArrayList<>();
        tabList.add("Energy");
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
                    fragment = (EnergyConsumptionFragment) pageAdapter.instantiateItem(pager, position);
                }
                if (fragment != null) {
                    fragment.fragmentBecameVisible();
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });






        if(DataHolder.getLevel() == 0)
        {

            // OPEN THE FILETR PANE ON LOAD
            slidingPaneLayout.openPane();
            if(DataHolder.getType() == 1)
            {
                spn_traction.setVisibility(View.GONE);
            }


            switch(loginUser.getAuthlevel())
            {
                case Constants.AUTH_RAILWAY :
                    DataHolder.setZone(loginUser.getRlycode());
                    DataHolder.setGlobal_level(1);
                    break;
                case Constants.AUTH_DIVISION :
                    DataHolder.setDivision(loginUser.getRlycode());
                    DataHolder.setGlobal_level(2);
                    //commonClass.showToast("Division");
                    break;
                case Constants.AUTH_LOBBY :
                    DataHolder.setLobby(loginUser.getRlycode());
                    DataHolder.setGlobal_level(3);
                    break;

                default :
                    DataHolder.setGlobal_level(0);
                    break;

            }
            DataHolder.setLevel(DataHolder.getGlobal_level());

        }
        else
        {
            DataHolder.getInstance().setRailWayConsumption(null);
            DataHolder.getInstance().setRailWayBilling(null);
            callWebService();
        }

    }

    @Override
    public void ResponseOk(Object object, int position) {
        try {


            if (object instanceof ConsumptionResponse) {
                ConsumptionResponse response = (ConsumptionResponse) object;
                iConsumption.showConsumptionResponse(response);
            }
        } catch (Exception e) {


            e.printStackTrace();
        }
    }

    @Override
    public void Error() {
        retryDialog(EnergyConsumptionController.this, "Unable to fetch record. Do you want to retry?");
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
                if (CommonClass.checkInternetConnection(EnergyConsumptionController.this)) {

                    if( spn_fYear.getSelectedItem().equals("Select Year"))
                    {
                        commonClass.showToast("Please select a Year");
                    }else if(((Month) spn_month.getSelectedItem()).getMonthCode().equals(""))
                    {
                        commonClass.showToast("Please select a month");
                    }else
                    {
                        if (slidingPaneLayout.isOpen()) {
                            slidingPaneLayout.closePane();
                        }
                        DataHolder.getInstance().setRailWayConsumption(null);
                        DataHolder.getInstance().setRailWayBilling(null);
                        callWebService();
                    }


                } else {
                    commonClass.showToast("No internet available.");
                }
                break;
        }
    }


    public void callWebService() {



        EnergyConsumptionRequest request = new EnergyConsumptionRequest();


        if(DataHolder.getLevel()==0)
        {
            request.setRailwayCode("");
            request.setTraction((String) spn_traction.getSelectedItem());
            DataHolder.setTraction((String) spn_traction.getSelectedItem());
            request.setMonth(((Month) spn_month.getSelectedItem()).getMonthCode());
            request.setfYYear((String) spn_fYear.getSelectedItem());

        }
        else
        {
            request = DataHolder.getInstance().getEnergyConsumptionRequest();
        }


        DataHolder.getInstance().setEnergyConsumptionRequest(request);


        if(DataHolder.getLevel() == 0)
        {
            request.setRailwayCode("");
            request.setDivisionCode("");
        }else if(DataHolder.getLevel() == 1)
        {
            request.setRailwayCode(DataHolder.getZone());
            request.setDivisionCode("");
        }else if(DataHolder.getLevel() == 2)
        {
            request.setRailwayCode(DataHolder.getZone());
            request.setDivisionCode(DataHolder.getDivision());
        }

        if(DataHolder.getType() == 0)
            requestPresenter.Request(request, "Fetching data...", Constants.ENERGY_CONSUMPTION);
        else  if(DataHolder.getType() == 1)
        {
            request.setTraction("ELEC");
            requestPresenter.Request(request, "Fetching data...", Constants.ENERGY_REGENERATION);
        }
        else  if(DataHolder.getType() == 2)
            requestPresenter.Request(request, "Fetching data...", Constants.SEC_SFC);

        tv_header_name.setText(request.getfYYear() + "/" + request.getMonth() + "/" + request.getTraction());


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
            switch (position) {
                case 0:
                    return new EnergyConsumptionFragment();
                default:
                    return null;
            }
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
                            callWebService();
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
