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
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cris.cmsm.BaseActivity;
import com.cris.cmsm.HomeActivity;
import com.cris.cmsm.R;
import com.cris.cmsm.adapter.DesignationAdapter;
import com.cris.cmsm.database.DataHolder;
import com.cris.cmsm.interactor.WebServices;
import com.cris.cmsm.interfaces.IBilling;
import com.cris.cmsm.interfaces.IConsumption;
import com.cris.cmsm.interfaces.IOnFragmentVisible;
import com.cris.cmsm.models.Designation;
import com.cris.cmsm.models.request.GraphAPIRequest;
import com.cris.cmsm.models.response.BillingResponse;
import com.cris.cmsm.models.response.ConsumptionResponse;
import com.cris.cmsm.models.response.LoginIfoVO;
import com.cris.cmsm.prefrences.UserLoginPreferences;
import com.cris.cmsm.presenter.RequestPresenter;
import com.cris.cmsm.presenterview.ResponseView;
import com.cris.cmsm.tabviews.BoardBillingFragment;
import com.cris.cmsm.tabviews.CrewStrengthFragment;
import com.cris.cmsm.util.CommonClass;
import com.cris.cmsm.util.Constants;
import com.cris.cmsm.util.FontFamily;
import com.cris.cmsm.widget.JitinRLSlidingLayout;
import com.cris.cmsm.widget.NonSwipeViewPager;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class BoardController extends BaseActivity implements ResponseView, View.OnClickListener {

    private TabLayout tabs;
    private NonSwipeViewPager pager;
    private List<String> tabList;
    private JitinRLSlidingLayout slidingPaneLayout;
    private ImageView iv_title_icon, iv_right;
    private TextView action_bar_title, tv_filters;
    private Typeface font;
    private AppCompatSpinner spn_desigCode, spn_ryCode, spn_divCode, spn_department, spn_fYear, spn_month;
    private Button btn_filter;
    private CommonClass commonClass;
    private RequestPresenter requestPresenter;
    private LoginIfoVO loginUser;
    private CreatePageAdapter pageAdapter;
    private LinearLayout showProgress;
    private TextView tv_header_name;
    private IConsumption iConsumption;
    private IBilling iBilling;

    private LoginIfoVO loginInfoModel;
    private UserLoginPreferences userLoginPreferences;


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
        requestPresenter = new RequestPresenter(BoardController.this);
        userLoginPreferences = new UserLoginPreferences(BoardController.this);
        loginInfoModel = userLoginPreferences.getLoginUser();

        loginUser = new UserLoginPreferences(BoardController.this).getLoginUser();
        commonClass = new CommonClass(BoardController.this);

        font = new FontFamily(BoardController.this).getBold();
        showProgress = findViewById(R.id.showProgress);
        spn_desigCode= findViewById(R.id.spn_desigCode);
        spn_ryCode = findViewById(R.id.spn_ryCode);
        spn_divCode = findViewById(R.id.spn_divCode);
        spn_department = findViewById(R.id.spn_department);
        spn_fYear = findViewById(R.id.spn_fYear);
        spn_month = findViewById(R.id.spn_month);
        btn_filter = findViewById(R.id.btn_filter);

        spn_ryCode.setVisibility(View.GONE);
        spn_divCode.setVisibility(View.GONE);
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
        action_bar_title.setText(getResources().getString(R.string.railway_wise_crew_strength));
        tv_filters.setTypeface(font);
        action_bar_title.setTypeface(font);
        tv_header_name.setVisibility(View.VISIBLE);
        tv_header_name.setTypeface(font);

        /********* Spinner Adapters ********/
        spn_desigCode.setAdapter(new DesignationAdapter(BoardController.this, CommonClass.getDesignationList()));


        iv_right.setOnClickListener(this);
        iv_title_icon.setOnClickListener(this);
        btn_filter.setOnClickListener(this);


        tabList = new ArrayList<>();
        tabList.add("Crew Strength");
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
                    fragment = (CrewStrengthFragment) pageAdapter.instantiateItem(pager, position);
                } else if (position == 1) {
                    fragment = (BoardBillingFragment) pageAdapter.instantiateItem(pager, position);
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
            switch(loginInfoModel.getAuthlevel())
            {
                case Constants.AUTH_RAILWAY :
                    DataHolder.setZone(loginInfoModel.getRlycode());
                    DataHolder.setGlobal_level(1);
                    break;
                case Constants.AUTH_DIVISION :
                    DataHolder.setDivision(loginInfoModel.getRlycode());
                    DataHolder.setGlobal_level(2);
                    //commonClass.showToast("Division");
                    break;
                case Constants.AUTH_LOBBY :
                    DataHolder.setLobby(loginInfoModel.getRlycode());
                    DataHolder.setGlobal_level(3);
                    break;

                default :
                    DataHolder.setGlobal_level(0);
                    break;

            }
            DataHolder.setLevel(DataHolder.getGlobal_level());
        }
        callWebService();
    }

    @Override
    public void ResponseOk(Object object, int position) {
        try {


            if (object instanceof ConsumptionResponse) {
                ConsumptionResponse response = (ConsumptionResponse) object;
                iConsumption.showConsumptionResponse(response);
            } else if (object instanceof BillingResponse) {
                BillingResponse response = (BillingResponse) object;
                iBilling.showBillingResponse(response);
            }
        } catch (Exception e) {


            e.printStackTrace();
        }
    }

    @Override
    public void Error() {
        retryDialog(BoardController.this, "Unable to fetch record. Do you want to retry?");
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
                if (CommonClass.checkInternetConnection(BoardController.this)) {
                    if (slidingPaneLayout.isOpen()) {
                        slidingPaneLayout.closePane();
                    }
                    DataHolder.getInstance().setRailWayConsumption(null);
                    DataHolder.getInstance().setRailWayBilling(null);
                    callWebService();
                } else {
                    commonClass.showToast("No internet available.");
                }
                break;
        }
    }


    public void callWebService() {

        GraphAPIRequest request = new GraphAPIRequest();
        request.setRailwayCode("");
        request.setDesignation(((Designation) spn_desigCode.getSelectedItem()).getDesignationName());



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
            request.setRailwayCode("IR");
            request.setDivisionCode(DataHolder.getDivision());
        }

        requestPresenter.Request(request, "Fetching data...", Constants.BOARD_CONSUMPTION_RAILWAY);


    }

    @Override
    public void onBackPressed() {
        if (slidingPaneLayout.isOpen()) {
            slidingPaneLayout.closePane();
        } else {
            //DataHolder.getInstance().setBoardStateModel(null);
            DataHolder.setLevel(DataHolder.getLevel() - 1);

            if(DataHolder.getLevel() < DataHolder.getGlobal_level())
                DataHolder.setLevel(DataHolder.getGlobal_level());

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
                    return new CrewStrengthFragment();
                default:
                    return new BoardBillingFragment();
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
