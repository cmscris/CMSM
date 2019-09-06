package com.cris.cmsm.navcontrollers;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatSpinner;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.cris.cmsm.adapter.LobbyAdapter;
import com.cris.cmsm.models.Lobby;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.Highlight;
import com.cris.cmsm.BaseActivity;
import com.cris.cmsm.DetailOnBarActivity;
import com.cris.cmsm.R;
import com.cris.cmsm.ZoomCompareGraph;
import com.cris.cmsm.adapter.DepartmentAdapter;
import com.cris.cmsm.adapter.DivisonAdapter;
import com.cris.cmsm.adapter.RailwayAdapter;
import com.cris.cmsm.adapter.SpinnerAdapter;
import com.cris.cmsm.adapter.ThreeYearMonthAdapter;
import com.cris.cmsm.database.DataBaseManager;
import com.cris.cmsm.database.DataHolder;
import com.cris.cmsm.interactor.WebServices;
import com.cris.cmsm.interfaces.ICheckBoxClick;
import com.cris.cmsm.models.Department;
import com.cris.cmsm.models.Division;
import com.cris.cmsm.models.Month;
import com.cris.cmsm.models.Railway;
import com.cris.cmsm.models.request.ConSummaryRequest;
import com.cris.cmsm.models.response.ConSummaryResponse;
import com.cris.cmsm.models.response.CrewPositionSummaryResponse;
import com.cris.cmsm.models.response.LoginIfoVO;
import com.cris.cmsm.models.response.ThreeYearData;
import com.cris.cmsm.prefrences.UserLoginPreferences;
import com.cris.cmsm.presenter.RequestPresenter;
import com.cris.cmsm.presenterview.ResponseView;
import com.cris.cmsm.util.CommonClass;
import com.cris.cmsm.util.Constants;
import com.cris.cmsm.util.FontFamily;
import com.cris.cmsm.util.UnitValueFormatter;
import com.cris.cmsm.widget.JitinRLSlidingLayout;
import com.cris.cmsm.widget.NonScrollListView;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class CrewPositionController extends BaseActivity implements ResponseView, View.OnClickListener, AdapterView.OnItemSelectedListener, ICheckBoxClick {

    private JitinRLSlidingLayout slidingPaneLayout;
    private ImageView iv_title_icon, iv_right, iv_zoom_cons, iv_zoom_bill, iv_connected_load;
    private TextView action_bar_title, tv_filters, tv_connected_load, tv_avg_cost;
    private Typeface font;
    private AppCompatSpinner spn_ryCode, spn_divCode, spn_lobbyCode, spn_department, spn_fYear, spn_no_of_year;
    private NonScrollListView listView;
    private Button btn_filter;
    private CommonClass commonClass;
    private DataBaseManager dataBaseManager;
    private RequestPresenter requestPresenter;
    private LoginIfoVO loginUser;
    private PieChart pieChart, bill_pieChart, connected_load_pieChart;
    private TextView tv_consumption, tv_billing;
    private LinearLayout showProgress;
    private List<CrewPositionSummaryResponse> list;
    private int detailVar = 0;
    private List<Month> monthList;
    private ScrollView scrollView;
    private ThreeYearMonthAdapter threeYearMonthAdapter;
    private TextView tv_titles;
    private CheckBox checkbox;
    private Handler handler = new Handler();
    private LoginIfoVO loginInfoModel;
    private UserLoginPreferences userLoginPreferences;


    List<Railway> railwayList = null;
    List<Division> divisionList = null;
    private List<Lobby> lobby_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_three_year);
        loginUser = new UserLoginPreferences(CrewPositionController.this).getLoginUser();
        userLoginPreferences = new UserLoginPreferences(CrewPositionController.this);
        loginInfoModel = userLoginPreferences.getLoginUser();
        requestPresenter = new RequestPresenter(CrewPositionController.this);
        commonClass = new CommonClass(CrewPositionController.this);
        dataBaseManager = new DataBaseManager(CrewPositionController.this);
        dataBaseManager.createDatabase();

        scrollView = findViewById(R.id.scrollView);
        tv_titles = findViewById(R.id.tv_titles);
        checkbox = findViewById(R.id.checkbox);
        showProgress = findViewById(R.id.showProgress);
        font = new FontFamily(CrewPositionController.this).getBold();
        spn_ryCode = findViewById(R.id.spn_ryCode);
        spn_divCode = findViewById(R.id.spn_divCode);
        spn_lobbyCode = findViewById(R.id.spn_lobbyCode);
        spn_department = findViewById(R.id.spn_department);
        spn_fYear = findViewById(R.id.spn_fYear);
        spn_no_of_year = findViewById(R.id.spn_no_of_year);
        listView = findViewById(R.id.listView);
        tv_filters = findViewById(R.id.tv_filters);
        btn_filter = findViewById(R.id.btn_filter);

        action_bar_title = findViewById(R.id.action_bar_title);
        iv_title_icon = findViewById(R.id.iv_title_icon);
        iv_right = findViewById(R.id.iv_right);

        slidingPaneLayout = findViewById(R.id.slidingPaneLayout);
        slidingPaneLayout.setParallaxDistance(200);
        slidingPaneLayout.setShadowDrawableLeft(getResources().getDrawable(R.drawable.nav_bar_shadow));
        slidingPaneLayout.setSliderFadeColor(getResources().getColor(android.R.color.transparent));

        iv_zoom_cons = findViewById(R.id.iv_zoom_cons);
        iv_zoom_bill = findViewById(R.id.iv_zoom_bill);
        iv_connected_load = findViewById(R.id.iv_connected_load);
        tv_consumption = findViewById(R.id.tv_consumption);
        tv_billing = findViewById(R.id.tv_billing);
        tv_connected_load = findViewById(R.id.tv_connected_load);
        tv_avg_cost = findViewById(R.id.tv_avg_cost);
        pieChart = findViewById(R.id.pieChart);
        bill_pieChart = findViewById(R.id.bill_pieChart);
        connected_load_pieChart = findViewById(R.id.connected_load_pieChart);


        tv_consumption.setTypeface(font);
        tv_filters.setTypeface(font);


        spn_department.setVisibility(View.GONE);
        spn_fYear.setVisibility(View.GONE);
        spn_no_of_year.setVisibility(View.GONE);
        listView.setVisibility(View.GONE);

        iv_right.setImageResource(R.drawable.filter);
        iv_right.setVisibility(View.VISIBLE);
        iv_title_icon.setImageResource(R.drawable.iv_back);
        action_bar_title.setText(getResources().getString(R.string.crew_position));
        action_bar_title.setTypeface(font);

        /********* Spinner Adapters ********/

        if (loginUser.getAuthlevel().equals(Constants.AUTH_BOARD)) {
            railwayList = dataBaseManager.getRailwayList(true);
        } else {
            railwayList = dataBaseManager.getRailwayList(false);
        }
        spn_ryCode.setAdapter(new RailwayAdapter(CrewPositionController.this, railwayList));

        monthList = CommonClass.getMonthList();
        monthList.remove(0);
        threeYearMonthAdapter = new ThreeYearMonthAdapter(CrewPositionController.this, CrewPositionController.this, monthList);
        listView.setAdapter(threeYearMonthAdapter);
        spn_fYear.setAdapter(new SpinnerAdapter(CrewPositionController.this, CommonClass.getFinancialYear()));
        spn_no_of_year.setAdapter(new SpinnerAdapter(CrewPositionController.this, CommonClass.getComparisonYear()));
        spn_no_of_year.setSelection(3);


        //Log.d("Logging : AUTH LEVEL " , loginUser.getAuthlevel());


        spn_ryCode.setEnabled(true);
        spn_lobbyCode.setEnabled(true);

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








        btn_filter.setOnClickListener(this);

        iv_right.setOnClickListener(this);
        iv_title_icon.setOnClickListener(this);
        iv_zoom_cons.setOnClickListener(this);
        iv_zoom_bill.setOnClickListener(this);
        iv_connected_load.setOnClickListener(this);

        pieChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {
                if (DataHolder.getInstance().getCrewPositionSummaryResponses() != null) {

                }

            }

            @Override
            public void onNothingSelected() {

            }
        });


        scrollView.setDescendantFocusability(ViewGroup.FOCUS_BEFORE_DESCENDANTS);
        scrollView.setFocusable(true);
        scrollView.setFocusableInTouchMode(true);
        scrollView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                v.requestFocusFromTouch();
                return false;
            }
        });


        // OPEN THE FILETR PANE ON LOAD
        slidingPaneLayout.openPane();

        handler.postDelayed(r, 300);
    }


    Runnable r = new Runnable() {
        @Override
        public void run() {
            //callWebService();
        }
    };


    private void checkUncheck(boolean isCheckAll) {
        for (Month month : monthList)
            month.setCheck(isCheckAll);
        threeYearMonthAdapter.notifyDataSetChanged();
    }

    @Override
    public void ResponseOk(Object object, int position) {
        try {
            StringBuilder sb = new StringBuilder();
            List<Month> checkedList = new ArrayList<>();
            for (Month month : monthList) {
                if (month.isCheck())
                    checkedList.add(month);
            }
            if (checkedList.size() == 12) {
                sb.append("All Months");
            } else {
                for (Month month :
                        checkedList) {
                    sb.append(month.getMonthName() + ",");
                }
            }
            String year = "\n " + "Fin. Year : " + spn_fYear.getSelectedItem() + " & " + "Month : " + sb.toString();

            list = (List) object;
            if (list != null && list.size() > 0)
            {
                if(list.get(0) instanceof CrewPositionSummaryResponse)
                {

                    list = (List<CrewPositionSummaryResponse>) object;
                    if (list != null && list.size() > 0) {
                        DataHolder.getInstance().setCrewPositionSummaryResponses(list);
                        showPieChart(list);
                        tv_consumption.setText(setTitle("Crew Position of ").toString());
                        tv_billing.setText(setTitle("Non Run ").toString());
                        tv_connected_load.setText(setTitle("Running ").toString());

                    }
                }
                else
                {

                    lobby_list = (List<Lobby>) object;
                    spn_lobbyCode.setAdapter(new LobbyAdapter(CrewPositionController.this, lobby_list));
                    spn_lobbyCode.setSelection(commonClass.getLobbyIndex(lobby_list, loginUser.getDivcode() != null ? loginUser.getDivcode() : ""));
                    spn_lobbyCode.setEnabled(true);

                }
            }



        } catch (Exception e) {
            retryDialog(CrewPositionController.this, "Unable to fetch record. Do you want to retry?");
            e.printStackTrace();
        }
    }



    private StringBuilder setTitle(String title) {
        StringBuilder sb = new StringBuilder(title);

        if (spn_lobbyCode.getSelectedItem() == null) {
            if (((Division) spn_divCode.getSelectedItem()).getDivcode().isEmpty()) {
                if (((Railway) spn_ryCode.getSelectedItem()).getRlycode().isEmpty())
                    sb.append("Indian Railways");
                else
                {
                    sb.append(((Railway) spn_ryCode.getSelectedItem()).getRlycode());
                    sb.append(" Zone ");
                }

            } else {
                sb.append(FirstLetterCaptial(((Division) spn_divCode.getSelectedItem()).getDivcode()));
                sb.append(" Division ");
            }
        }
        else
        {
            if(((Lobby) spn_lobbyCode.getSelectedItem()).getLobbycode().equals(""))
            {
                sb.append(FirstLetterCaptial(((Division) spn_divCode.getSelectedItem()).getDivcode()));
                sb.append(" Division ");
            }
            else
            {
                sb.append(FirstLetterCaptial(((Lobby) spn_lobbyCode.getSelectedItem()).getLobbycode()));
                sb.append(" Lobby ");
            }

        }




        return sb;
    }


    private String FirstLetterCaptial(String myString) {
        String upperString = myString.substring(0, 1).toUpperCase() + myString.substring(1).toLowerCase();
        return upperString;
    }


    private void showPieChart(List<CrewPositionSummaryResponse> list) {
        if (list != null) {

            List<Entry> dataList = new ArrayList<>();
            List<String> titleList = new ArrayList<>();
            for (int i = 0; i < list.size(); i++) {
                if(list.get(i).getTitle().equals("NONRUN_TOTAL") || list.get(i).getTitle().equals("RUNNING"))
                {
                    dataList.add(new Entry(Float.valueOf(list.get(i).getTotal()), i));
                    titleList.add(list.get(i).getTitle());
                }

            }
            showPieChart(pieChart, dataList, titleList);



            int runcounter=0;

            List<Entry> nonrunList = new ArrayList<>();
            List<String> nonruntitleList = new ArrayList<>();
            for (int i = 0; i < list.size(); i++) {

                if(list.get(i).getTitle().equals("NONRUN_TOTAL"))
                {
                    runcounter = i+1;
                    break;
                }
                nonrunList.add(new Entry(Float.valueOf(list.get(i).getTotal()), i));
                nonruntitleList.add(list.get(i).getTitle());



            }

            showPieChart(bill_pieChart, nonrunList, nonruntitleList);

            List<Entry> runningList = new ArrayList<>();
            List<String> runningtitleList = new ArrayList<>();
            for (int z = runcounter; z < list.size(); z++) {

                if(list.get(z).getTitle().equals("RUNNING"))
                {
                    break;
                }
                runningList.add(new Entry(Float.valueOf(list.get(z).getTotal()), z));
                runningtitleList.add(list.get(z).getTitle());

            }


            showPieChart(connected_load_pieChart, runningList, runningtitleList);




        }


    }

    private void showPieChart(PieChart pieChart, List<Entry> dataList, List<String> titleList) {
        PieDataSet pieDataSet = new PieDataSet(dataList, "");
        pieDataSet.setValueFormatter(new UnitValueFormatter(""));
        PieData newData = new PieData(titleList, pieDataSet);
        pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        pieDataSet.setValueTextSize(12);//
        pieChart.setDescription("");
        pieChart.setData(newData);
        pieChart.animateY(2000);
    }

    private void callWebService(int cat) {
        try {
            ConSummaryRequest request = new ConSummaryRequest();

            StringBuilder sb = new StringBuilder();
            List<Month> checkedList = new ArrayList<>();
            for (Month month : monthList) {
                if (month.isCheck())
                    checkedList.add(month);
            }
            if (checkedList.size() == 12) {
            } else {
                for (Month month :
                        checkedList) {
                    sb.append(month.getMonthCode() + ",");
                }
            }



            if(cat == Constants.CREW_POSITION)
            {

                request.setRailway((spn_ryCode.getSelectedItem() == null ) ? "" : ((Railway) spn_ryCode.getSelectedItem()).getRlycode());
                request.setDivision((spn_divCode.getSelectedItem() == null ) ? "" : ((Division) spn_divCode.getSelectedItem()).getDivcode());

                if(spn_lobbyCode.getSelectedItem() != null)
                   request.setLobby(((Lobby) spn_lobbyCode.getSelectedItem()).getLobbycode());
                requestPresenter.Request(request, "Getting Data For CrewPosition", Constants.CREW_POSITION);
            }
            else
            {
                //commonClass.showToast("Logging : AUTH LEVEL " + loginInfoModel.getRlycode() );
                request.setDivision(((Division) spn_divCode.getSelectedItem()).getDivcode());
                //request.setDivision(loginInfoModel.getRlycode());
                requestPresenter.Request(request, "Getting Lobby List", Constants.LOBBY_LIST);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void Error() {
        retryDialog(CrewPositionController.this, "Unable to fetch record. Do you want to retry?");
    }

    @Override
    public void dismissProgress() {
        iv_right.setEnabled(true);
        iv_zoom_cons.setEnabled(true);
        iv_zoom_bill.setEnabled(true);
        iv_connected_load.setEnabled(true);
        showProgress.setVisibility(View.GONE);
    }

    @Override
    public void showProgress(String msg) {
        if (!showProgress.isShown()) {
            iv_right.setEnabled(false);
            iv_zoom_cons.setEnabled(false);
            iv_zoom_bill.setEnabled(false);
            iv_connected_load.setEnabled(false);
            showProgress.setVisibility(View.VISIBLE);

        }
    }

    @Override
    public void onClick(View v) {
        StringBuilder sb = new StringBuilder();
        List<Month> checkedList = new ArrayList<>();
        for (Month month : monthList) {
            if (month.isCheck())
                checkedList.add(month);
        }
        if (checkedList.size() == 12) {
            sb.append("All Months");
        } else {
            for (Month month :
                    checkedList) {
                sb.append(month.getMonthName() + ",");
            }
        }
        String year = "\n " + "Fin. Year : " + spn_fYear.getSelectedItem() + " & " + "Month : " + sb.toString();
        switch (v.getId()) {
            case R.id.iv_zoom_cons:
                //DataHolder.getInstance().setZoomThreeYearTitle(tv_consumption.getText().toString().trim() + year);
                zoomScreen(Constants.THREE_YEAR_CONS, false);
                break;
            case R.id.iv_zoom_bill:
                //DataHolder.getInstance().setZoomThreeYearTitle(tv_billing.getText().toString().trim() + year);
                zoomScreen(Constants.THREE_YEAR_BILL, false);
                break;
            case R.id.iv_connected_load:
                //DataHolder.getInstance().setZoomThreeYearTitle(tv_connected_load.getText().toString().trim() + year);
                zoomScreen(Constants.THREE_YEAR_CONNECTED, false);
                break;
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


                    if (CommonClass.checkInternetConnection(CrewPositionController.this)) {
                        if (slidingPaneLayout.isOpen()) {
                            slidingPaneLayout.closePane();
                        }
                        DataHolder.getInstance().setCrewPositionSummaryResponses(null);
                        callWebService(Constants.CREW_POSITION);
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

            if (!TextUtils.isEmpty(loginUser.getAuthlevel()) && (loginUser.getAuthlevel().equalsIgnoreCase(Constants.AUTH_BOARD)
                    || loginUser.getAuthlevel().equalsIgnoreCase(Constants.AUTH_CRIS) || loginUser.getAuthlevel().equalsIgnoreCase(Constants.AUTH_RAILWAY))) {

                Railway railway = (Railway) object;
                divisionList = dataBaseManager.getDivisionList(railway.getRlycode());
                spn_divCode.setAdapter(new DivisonAdapter(CrewPositionController.this, divisionList));
                spn_divCode.setSelection(commonClass.getDivisionIndex(divisionList, loginUser.getDivcode() != null ? loginUser.getDivcode() : ""));
                spn_divCode.setEnabled(true);

            }
            else if (!TextUtils.isEmpty(loginUser.getAuthlevel()) && loginUser.getAuthlevel().equals(Constants.AUTH_DIVISION))
            {
                spn_ryCode.setEnabled(false);
                spn_ryCode.setVisibility(View.INVISIBLE);
                spn_divCode.setEnabled(false);
                spn_divCode.setVisibility(View.INVISIBLE);

                divisionList = dataBaseManager.getDivisionListByDivCode(loginInfoModel.getRlycode());
                spn_divCode.setAdapter(new DivisonAdapter(CrewPositionController.this, divisionList));
                spn_divCode.setEnabled(true);
                callWebService(Constants.LOBBY_LIST);
            }






        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private boolean isValid() {
        List<Month> checkedList = new ArrayList<>();
        for (Month month : monthList) {
            if (month.isCheck())
                checkedList.add(month);
        }
        if (((String) spn_no_of_year.getSelectedItem()).equalsIgnoreCase("No Of Year.")) {
            commonClass.showToast("Please select no of year");
            return false;
        } else if (checkedList.size() == 0) {
            commonClass.showToast("Please select at least one month.");
            return false;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        if (slidingPaneLayout.isOpen()) {
            slidingPaneLayout.closePane();
        } else {
            WebServices.getInstance().cancelAllRequest();
            if (handler != null && r != null)
                handler.removeCallbacks(r);
            super.onBackPressed();
        }

    }


    private void retryDialog(final Activity activity, String msg) {
        try {
            new AlertDialog.Builder(activity).setCancelable(false)
                    .setTitle(getResources().getString(R.string.cms))
                    .setMessage(msg)
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            callWebService(Constants.CREW_POSITION);
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


    private void zoomScreen(int value, boolean isBarActivity) {
        Intent intent = null;
        if (isBarActivity) {
            intent = new Intent(CrewPositionController.this, DetailOnBarActivity.class);
            if (((Division) spn_divCode.getSelectedItem()).getDivcode().trim().isEmpty())
                intent.putExtra(Constants.UNIT, true);
            else
                intent.putExtra(Constants.UNIT, false);
        } else
            intent = new Intent(CrewPositionController.this, ZoomCompareGraph.class);
        intent.putExtra(Constants.ZOOM_INTENT, value);
        startActivity(intent);
    }


    @Override
    public void checkBoxClick() {
        List<Month> checkedList = new ArrayList<>();
        for (Month month : monthList) {
            if (month.isCheck())
                checkedList.add(month);
        }
        if (checkedList.size() == monthList.size())
            checkbox.setChecked(true);
        else
            checkbox.setChecked(false);
    }


}
