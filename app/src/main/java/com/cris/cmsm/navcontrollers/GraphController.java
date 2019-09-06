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
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cris.cmsm.BaseActivity;
import com.cris.cmsm.R;
import com.cris.cmsm.adapter.DepartmentAdapter;
import com.cris.cmsm.adapter.DivisonAdapter;
import com.cris.cmsm.adapter.MonthAdapter;
import com.cris.cmsm.adapter.RailwayAdapter;
import com.cris.cmsm.adapter.SpinnerAdapter;
import com.cris.cmsm.database.DataBaseManager;
import com.cris.cmsm.database.DataHolder;
import com.cris.cmsm.interactor.WebServices;
import com.cris.cmsm.interfaces.IBilling;
import com.cris.cmsm.interfaces.IConsumption;
import com.cris.cmsm.interfaces.IOnFragmentVisible;
import com.cris.cmsm.models.BoardStateModel;
import com.cris.cmsm.models.Department;
import com.cris.cmsm.models.Division;
import com.cris.cmsm.models.Month;
import com.cris.cmsm.models.Railway;
import com.cris.cmsm.models.request.GraphAPIRequest;
import com.cris.cmsm.models.response.BillingResponse;
import com.cris.cmsm.models.response.ConsumptionResponse;
import com.cris.cmsm.models.response.LoginIfoVO;
import com.cris.cmsm.prefrences.UserLoginPreferences;
import com.cris.cmsm.presenter.RequestPresenter;
import com.cris.cmsm.presenterview.ResponseView;
import com.cris.cmsm.tabviews.BillingGraphFragment;
import com.cris.cmsm.tabviews.ConsumptionGraphFragment;
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
public class GraphController extends BaseActivity implements ResponseView, View.OnClickListener, AdapterView.OnItemSelectedListener {

    private TabLayout tabs;
    private NonSwipeViewPager pager;
    private List<String> tabList;
    private JitinRLSlidingLayout slidingPaneLayout;
    private ImageView iv_title_icon, iv_right;
    private TextView action_bar_title, tv_filters;
    private Typeface font;
    private AppCompatSpinner spn_ryCode, spn_divCode, spn_department, spn_fYear, spn_month;
    private Button btn_filter;
    private CommonClass commonClass;
    private DataBaseManager dataBaseManager;
    private RequestPresenter requestPresenter;
    private IConsumption iConsumption;
    private IBilling iBilling;
    private LoginIfoVO loginUser;
    private CreatePageAdapter pageAdapter;
    private LinearLayout showProgress;
    private TextView tv_header_name;

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
        requestPresenter = new RequestPresenter(GraphController.this);
        loginUser = new UserLoginPreferences(GraphController.this).getLoginUser();
        commonClass = new CommonClass(GraphController.this);
        dataBaseManager = new DataBaseManager(GraphController.this);
        dataBaseManager.createDatabase();

        font = new FontFamily(GraphController.this).getBold();
        showProgress = findViewById(R.id.showProgress);
        spn_ryCode = findViewById(R.id.spn_ryCode);
        spn_divCode = findViewById(R.id.spn_divCode);
        spn_department = findViewById(R.id.spn_department);
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
        action_bar_title.setText(getResources().getString(R.string.action_title_consumption));
        tv_filters.setTypeface(font);
        action_bar_title.setTypeface(font);
        tv_header_name.setVisibility(View.VISIBLE);
        tv_header_name.setTypeface(font);

        /********* Spinner Adapters ********/
        List<Railway> railwayList = dataBaseManager.getRailwayList(false);
        spn_ryCode.setAdapter(new RailwayAdapter(GraphController.this, railwayList));
        spn_divCode.setAdapter(new DivisonAdapter(GraphController.this, dataBaseManager.getDivisionList("-100")));
        spn_month.setAdapter(new MonthAdapter(GraphController.this, CommonClass.getMonthList()));
        spn_department.setAdapter(new DepartmentAdapter(GraphController.this, CommonClass.getDepartmentList(loginUser.getAuthlevel(), loginUser.getCategory(), false)));
        spn_fYear.setAdapter(new SpinnerAdapter(GraphController.this, CommonClass.getFinancialYear()));
        if (loginUser.getAuthlevel() != null && loginUser.getAuthlevel().equals(Constants.AUTH_BOARD)) {
            BoardStateModel boardStateModel = DataHolder.getInstance().getBoardStateModel();
            if (boardStateModel != null) {
                spn_ryCode.setSelection(commonClass.getUserRailwayIndex(railwayList, boardStateModel.getRlyCode() != null ? boardStateModel.getRlyCode() : ""));
                spn_department.setSelection(boardStateModel.getSelectedDepartment());
                spn_fYear.setSelection(boardStateModel.getSelectYear());
                spn_month.setSelection(boardStateModel.getSelectedMonth());
            }
        } else {
            spn_ryCode.setSelection(commonClass.getUserRailwayIndex(railwayList, loginUser.getRlycode() != null ? loginUser.getRlycode() : ""));
            if (!TextUtils.isEmpty(loginUser.getAuthlevel()) && loginUser.getAuthlevel().equals(Constants.AUTH_BOARD))
                spn_ryCode.setEnabled(true);
            else
                spn_ryCode.setEnabled(false);
        }

        iv_right.setOnClickListener(this);
        iv_title_icon.setOnClickListener(this);
        btn_filter.setOnClickListener(this);
        spn_ryCode.setOnItemSelectedListener(this);

        tabList = new ArrayList<>();
        tabList.add("Consumption");
        tabList.add("Billing");
        tabs = findViewById(R.id.tabs);
        pager = findViewById(R.id.pager);
        pageAdapter = new CreatePageAdapter(getSupportFragmentManager());
        pager.setAdapter(pageAdapter);
        tabs.setupWithViewPager(pager);
        pager.setOffscreenPageLimit(1);
        pager.setPagingEnabled(false);
        if (getIntent().getIntExtra(Constants.KEY, -1) != -1) {
            int value = getIntent().getIntExtra(Constants.KEY, -1);
            pager.setCurrentItem(value);
        }
        pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                IOnFragmentVisible fragment = null;
                if (position == 0) {
                    fragment = (ConsumptionGraphFragment) pageAdapter.instantiateItem(pager, position);
                } else if (position == 1) {
                    fragment = (BillingGraphFragment) pageAdapter.instantiateItem(pager, position);
                }
                if (fragment != null) {
                    fragment.fragmentBecameVisible();
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    @Override
    public void ResponseOk(Object object, int position) {
        try {
            if (object instanceof ConsumptionResponse) {
                ConsumptionResponse response = (ConsumptionResponse) object;
                iConsumption.showConsumptionResponse((ConsumptionResponse) response.clone());
            } else if (object instanceof BillingResponse) {
                BillingResponse response = (BillingResponse) object;
                iBilling.showBillingResponse((BillingResponse) response.clone());
            }
        } catch (Exception e) {
            retryDialog(GraphController.this, "Unable to fetch record. Do you want to retry?");
            e.printStackTrace();
        }
    }

    @Override
    public void Error() {
        retryDialog(GraphController.this, "Unable to fetch record. Do you want to retry?");
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
            if (pager.getCurrentItem() == 0)
                iConsumption.disableControls();
            if (pager.getCurrentItem() == 1)
                iBilling.disableControls();

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
                if (CommonClass.checkInternetConnection(GraphController.this)) {
                    if (slidingPaneLayout.isOpen()) {
                        slidingPaneLayout.closePane();
                    }
                    DataHolder.getInstance().setBillingResponse(null);
                    DataHolder.getInstance().setConsumptionResponse(null);
                    callWebService();
                } else {
                    commonClass.showToast("No internet available.");
                }
                break;
        }
    }


    public void callWebService() {
        GraphAPIRequest request = new GraphAPIRequest();
        request.setRailwayCode(((Railway) spn_ryCode.getSelectedItem()).getRlycode());
        request.setDivisionCode(((Division) spn_divCode.getSelectedItem()).getDivcode());
        request.setMonth(((Month) spn_month.getSelectedItem()).getMonthCode());
        request.setFYYear((String) spn_fYear.getSelectedItem());
        request.setFlag(((Railway) spn_ryCode.getSelectedItem()).getFlag());
        if (spn_department.getAdapter().getCount() > 0) {
            request.setServiceType(((Department) spn_department.getSelectedItem()).getDepartmentCode());
        }
        if (pager.getCurrentItem() == 0) {
            requestPresenter.Request(request, "Fetching consumption record...", Constants.FILTER_CONSUMPTION);
        } else if (pager.getCurrentItem() == 1) {
            requestPresenter.Request(request, "Fetching billing record...", Constants.DASH_BILLING);
        }
        tv_header_name.setText(((Railway) spn_ryCode.getSelectedItem()).getRlycode() + "/" + ((Division) spn_divCode.getSelectedItem()).getFname() + "/" + spn_fYear.getSelectedItem() + "/" + ((Month) spn_month.getSelectedItem()).getMonthName());
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Object object = parent.getItemAtPosition(position);
        if (object instanceof Railway) {
            Railway railway = (Railway) object;
            List<Division> list = dataBaseManager.getDivisionList(railway.getRlycode());
            spn_divCode.setAdapter(new DivisonAdapter(GraphController.this, list));
            spn_divCode.setSelection(commonClass.getDivisionIndex(list, loginUser.getDivcode() != null ? loginUser.getDivcode() : ""));
            if (!TextUtils.isEmpty(loginUser.getAuthlevel()) && (loginUser.getAuthlevel().equalsIgnoreCase(Constants.AUTH_BOARD)
                    || loginUser.getAuthlevel().equalsIgnoreCase(Constants.AUTH_RAILWAY))) {
                spn_divCode.setEnabled(true);
            } else {
                spn_divCode.setEnabled(false);
            }
            if (iConsumption != null && pager.getCurrentItem() == 0) {
                iConsumption.showSpinnerText((Division) spn_divCode.getSelectedItem());
            } else if (iConsumption != null && pager.getCurrentItem() == 0) {
                iConsumption.showSpinnerText((Division) spn_divCode.getSelectedItem());
            }
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onBackPressed() {
        if (slidingPaneLayout.isOpen()) {
            slidingPaneLayout.closePane();
        } else {
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
                    return new ConsumptionGraphFragment();
                default:
                    return new BillingGraphFragment();
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
