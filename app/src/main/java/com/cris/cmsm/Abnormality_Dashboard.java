package com.cris.cmsm;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatSpinner;
import android.util.Base64;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.cris.cmsm.database.DataBaseManager;
import com.cris.cmsm.database.DataHolder;
import com.cris.cmsm.models.Division;
import com.cris.cmsm.models.Lobby;
import com.cris.cmsm.models.Railway;
import com.cris.cmsm.models.request.GraphAPIRequest;
import com.cris.cmsm.models.response.CrewPositionSummaryResponse;
import com.cris.cmsm.models.response.LoginIfoVO;
import com.cris.cmsm.models.response.Paramresponse;
import com.cris.cmsm.models.response.Remarksresponse;
import com.cris.cmsm.models.response.Sectionresponse;
import com.cris.cmsm.models.response.VosList;
import com.cris.cmsm.navcontrollers.CrewAvailabilityController;
import com.cris.cmsm.navcontrollers.CrewPositionController;
import com.cris.cmsm.navcontrollers.DetailController;
import com.cris.cmsm.prefrences.UserLoginPreferences;
import com.cris.cmsm.presenter.RequestPresenter;
import com.cris.cmsm.presenterview.ResponseView;
import com.cris.cmsm.util.CommonClass;
import com.cris.cmsm.util.Constants;
import com.cris.cmsm.util.FontFamily;
import com.cris.cmsm.util.UnitValueFormatter;
import com.cris.cmsm.widget.JitinRLSlidingLayout;
import com.cris.cmsm.widget.TouchImageView;
import com.cris.cmsm.widget.VerticalTextView;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.Highlight;

import org.w3c.dom.Text;

import java.sql.Array;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Abnormality_Dashboard<total> extends BaseActivity  implements AdapterView.OnItemSelectedListener,ResponseView{
    private JitinRLSlidingLayout slidingPaneLayout;
    private ImageView iv_title_icon, iv_right,iv_middle;
    private TextView action_bar_title, tv_filters,last_Pending_Date,tv_closed,heading,tv_bar;
    private Typeface font;
    private Spinner  spn_department,spn_sectionlist,spn_subhead;
    private Button btn_filter;
    private CommonClass commonClass;
    private RequestPresenter requestPresenter;
    private LoginIfoVO loginUser;
    private DataBaseManager dataBaseManager;
    private List<Lobby> lobby_list;
    private LoginIfoVO loginInfoModel;
    private UserLoginPreferences userLoginPreferences;
    TextView tv_header_name;
    private BarChart barChart;
    NumberFormat format;
    int counts=0;
    LinearLayout deptlayout,pendingdatelayout,monthlayout;
    private PieChart piechart;
    private PieChart  pieChart_dept;
    String selected_spinner,selected_spinnersubhead;

    List<Railway> railwayList = null;
    List<Division> divisionList = null;
    ArrayList<String>abnrdeptlist;
    String pendingcount,closedcount,pendingdate;
    GraphAPIRequest request;
    ArrayList<String> request1;

    ArrayList<String>subheadlist;

    ArrayList<String>sectionlist;
    String divisionname,send,send2,selected_spinnersection;
    ArrayList<String> dashboardlist;

    int i;
    ArrayList <Entry>count;
    ArrayList <String> deptstatment;
    ArrayList <String> countstatment;
    ArrayList <Entry>countdept;
    ArrayList<BarEntry> barEntryList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //DOJ2=(DatePicker)findViewById(R.id.DOJ2);

        setContentView(R.layout.activity_abnormality__dashboard);
        requestPresenter = new RequestPresenter(Abnormality_Dashboard.this);
        loginUser = new UserLoginPreferences(Abnormality_Dashboard.this).getLoginUser();
        commonClass = new CommonClass(Abnormality_Dashboard.this);
        last_Pending_Date=(TextView)findViewById(R.id.last_Pending_Date);
        tv_closed=(TextView)findViewById(R.id.tv_closed);
        tv_bar=(TextView)findViewById(R.id.tv_bar);
        heading=(TextView)findViewById(R.id.heading);
        pieChart_dept=(PieChart)findViewById(R.id. pieChart_dept);
        pendingdatelayout=(LinearLayout)findViewById(R.id.pendingdatelayout);
        deptlayout=(LinearLayout)findViewById(R.id.deptlayout);
        monthlayout=(LinearLayout)findViewById(R.id.monthlayout);
        piechart=(PieChart)findViewById(R.id. pieChart_dashboard);
        format=new DecimalFormat("00");
        barChart=(BarChart)findViewById(R.id.month_chart);

        dataBaseManager = new DataBaseManager(Abnormality_Dashboard.this);
        dataBaseManager.createDatabase();
        userLoginPreferences = new UserLoginPreferences(Abnormality_Dashboard.this);
        loginInfoModel = userLoginPreferences.getLoginUser();
        font = new FontFamily(Abnormality_Dashboard.this).getBold();
        spn_subhead=findViewById(R.id.spn_subhead);
        spn_department = findViewById(R.id.spn_department);
        spn_sectionlist=findViewById(R.id.spn_sectionlist);
        btn_filter = findViewById(R.id.btn_filter);
        request = new GraphAPIRequest();
        abnrdeptlist=new ArrayList <>();
        deptstatment=new ArrayList <>();
        count = new ArrayList();
        countstatment = new ArrayList<>();
        countdept = new ArrayList();
        barEntryList=new ArrayList();
    monthlayout.setVisibility(View.GONE);

        tv_header_name = findViewById(R.id.tv_header_name);
        tv_filters = findViewById(R.id.tv_filters);
        action_bar_title = findViewById(R.id.action_bar_title);
        iv_title_icon = findViewById(R.id.iv_title_icon);
        iv_right = findViewById(R.id.iv_right);
        iv_middle = findViewById(R.id.iv_middle);

        slidingPaneLayout = findViewById(R.id.slidingPaneLayout);
        slidingPaneLayout.setParallaxDistance(100);
        slidingPaneLayout.setShadowDrawableLeft(getResources().getDrawable(R.drawable.nav_bar_shadow));
        slidingPaneLayout.setSliderFadeColor(getResources().getColor(android.R.color.transparent));
        iv_right.setImageResource(R.drawable.icon_logout);
        iv_right.setVisibility(View.VISIBLE);
        iv_title_icon.setImageResource(R.drawable.iv_back);
        action_bar_title.setText("Abnormality Dashboard");
        iv_middle.setVisibility(View.VISIBLE);
        iv_middle.setImageResource(R.drawable.filter);
        tv_filters.setTypeface(font);
        action_bar_title.setTypeface(font);
        subheadlist=new ArrayList <>();
        sectionlist=new ArrayList <>();

        abnrdeptlist.add("SELECT DEPT");
        abnrdeptlist.add("OHE");
        abnrdeptlist.add("ENGG");
        abnrdeptlist.add("ST");
        abnrdeptlist.add("CW");
        abnrdeptlist.add("LOCO");
        abnrdeptlist.add("EMU");
        abnrdeptlist.add("DMU");
        abnrdeptlist.add("SECURITY");
        abnrdeptlist.add("TRAFFIC");
        abnrdeptlist.add("COMMERCIAL");
        spn_department.setOnItemSelectedListener(this);
        ArrayAdapter dept = new ArrayAdapter(this, android.R.layout.simple_spinner_item, abnrdeptlist);
        dept.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spn_department.setAdapter(dept);
        request1=new ArrayList<>();
        Bundle extra = getIntent().getExtras();
        if (extra != null) {
            divisionname= extra.getString("object");
            request1.add(divisionname);

            request.setparamlist(request1);
            requestPresenter.Request(request, "Loading Data", Constants.ABNORMALITYANALYSISCOUNT);

        }

iv_right.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_right:
                showLogoutDialog(Abnormality_Dashboard.this, "Do you want to logout?", true);
                break;
        }
    }
});

        iv_title_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (slidingPaneLayout.isOpen()) {
                    slidingPaneLayout.closePane();
                } else {
                    Intent intent = getIntent();
                    finish();
                    startActivity(intent);

                }
            }
        });
        iv_middle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (slidingPaneLayout.isOpen()) {
                    slidingPaneLayout.closePane();
                } else {
                    requestPresenter.Request(request,"Fetching Sections",Constants.GET_SECTIONS);
                    slidingPaneLayout.openPane();
                }
            }
        });
        piechart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {
                System.out.println( e.getXIndex());
            }

            @Override
            public void onNothingSelected() {

            }
        });
        pieChart_dept.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {
                System.out.println(+dataSetIndex);
                System.out.println(e.getData() + "-" + e.getXIndex());
                System.out.println("countstatementlist>>>>>>>>>>>>>>>"+countstatment);
                System.out.println(e.getVal());
                System.out.println(e);
            }

            @Override
            public void onNothingSelected() {

            }
        });

        btn_filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dashboardlist=new ArrayList <>();

                System.out.println("11111-" +send2);
                System.out.println("33333-" +selected_spinner);
                System.out.println("22222-" +send);


                dashboardlist.clear();
                dashboardlist.add(selected_spinner);
                dashboardlist.add(divisionname);
                dashboardlist.add(send2);
                dashboardlist.add(send);
                if(send2.equals("")&& send.equals("")) {
                    tv_bar.setText("Chart for ABNR HEAD  - " + dashboardlist.get(0));
                }
                else if(send2.equals("")&& !send.equals("")) {
                    tv_bar.setText("Chart for ABNR HEAD  - " + dashboardlist.get(0) +"\n"+"SECTION - "+dashboardlist.get(3));
                }
                else if(!send2.equals("")&& !send.equals("")){
                    tv_bar.setText("Chart for ABNR HEAD  - " + dashboardlist.get(0) + " \n" + " SUBHEAD - " + dashboardlist.get(2)+"\n"+"SECTION - "+dashboardlist.get(3));
                }
                else if(!send2.equals("")&& send.equals("")){
                    tv_bar.setText("Chart for ABNR HEAD  - " + dashboardlist.get(0) + " \n" + " SUBHEAD - " + dashboardlist.get(2));
                }



                if (slidingPaneLayout.isOpen()) {
                    slidingPaneLayout.closePane();
                } else {
                    slidingPaneLayout.openPane();
                }
                deptlayout.setVisibility(View.GONE);
                pendingdatelayout.setVisibility(View.GONE);
                monthlayout.setVisibility(View.VISIBLE);
                System.out.println("Request Data for Dashboradlist>>>>>>>>>>>>>>>" +dashboardlist);
                request.setparamlist(dashboardlist);
                requestPresenter.Request(request,"Loading Data",Constants.ABNORMALITYANALYSISCOUNTWITHPARAM);

            }
        });

    }

    @Override
    public void ResponseOk(Object object, int position) {
        System.out.println("Success>>>>>>>>>>>>>>>>>>>>>>");
        int i = 0;
        if (object instanceof Paramresponse) {
            System.out.println("Key sucess>>>>>>>>>>");
            Paramresponse paramresponse = (Paramresponse) object;

            System.out.println("Key sucess>>>>>>>>>>");


            while(i<paramresponse.getVosList().size()) {

if(paramresponse.getVosList().get(i).getAbnrType().equals("LOCO")) {

    if (paramresponse.getVosList().get(i).getAbnrCount().equals("0")) {
        System.out.println(paramresponse.getVosList().get(i).getAbnrCount()+" - "+i);
    } else {
        System.out.println("LOCO - "+paramresponse.getVosList().get(i).getAbnrCount()+" - "+i);
        countstatment.add("LOCO");
        count.add(new Entry(Float.valueOf(paramresponse.getVosList().get(i).getAbnrCount()), i));
    }
}
else if(paramresponse.getVosList().get(i).getAbnrType().equals("PN")) {
    countdept.add(new Entry(Float.valueOf(paramresponse.getVosList().get(i).getAbnrCount()), i));
    deptstatment.add("Pending Abnormality");
    pendingcount=paramresponse.getVosList().get(i).getAbnrCount();
    pendingdate=paramresponse.getVosList().get(i).getAbnrLastPendingDate();
   }
else if(paramresponse.getVosList().get(i).getAbnrType().equals("ENGG")) {
    if (paramresponse.getVosList().get(i).getAbnrCount().equals("0")) {
        System.out.println("ENGG - "+paramresponse.getVosList().get(i).getAbnrCount()+" - "+i);
    } else {
        System.out.println("ENGG - "+paramresponse.getVosList().get(i).getAbnrCount()+" - "+i);
        countstatment.add("ENGG");
        count.add(new Entry(Float.valueOf(paramresponse.getVosList().get(i).getAbnrCount()),  i));
    }
}
else if(paramresponse.getVosList().get(i).getAbnrType().equals("ST")) {
    if (paramresponse.getVosList().get(i).getAbnrCount().equals("0")) {
        System.out.println(paramresponse.getVosList().get(i).getAbnrCount()+" - "+i);
    } else {
        System.out.println("ST - "+paramresponse.getVosList().get(i).getAbnrCount()+" - "+i);
        countstatment.add("ST");
        count.add(new Entry(Float.valueOf(paramresponse.getVosList().get(i).getAbnrCount()),  i));
    }
}
else if(paramresponse.getVosList().get(i).getAbnrType().equals("EMU")) {

    if (paramresponse.getVosList().get(i).getAbnrCount().equals("0")) {
        System.out.println(paramresponse.getVosList().get(i).getAbnrCount()+" - "+i);
    } else {
        System.out.println("EMU - "+paramresponse.getVosList().get(i).getAbnrCount()+" - "+i);
        countstatment.add("EMU");
        count.add(new Entry(Float.valueOf(paramresponse.getVosList().get(i).getAbnrCount()),  i));
    }
}
else if(paramresponse.getVosList().get(i).getAbnrType().equals("MISC")) {

    if (paramresponse.getVosList().get(i).getAbnrCount().equals("0")) {
        System.out.println(paramresponse.getVosList().get(i).getAbnrCount()+" - "+i);
    } else {
        System.out.println("MISC - "+paramresponse.getVosList().get(i).getAbnrCount()+" - "+i);

        countstatment.add("MISC");
        count.add(new Entry(Float.valueOf(paramresponse.getVosList().get(i).getAbnrCount()),  i));
    }
}
else if(paramresponse.getVosList().get(i).getAbnrType().equals("SECURITY")) {

    if (paramresponse.getVosList().get(i).getAbnrCount().equals("0")) {
        System.out.println(paramresponse.getVosList().get(i).getAbnrCount()+" - "+i);
    } else {
        System.out.println(" Security -"+paramresponse.getVosList().get(i).getAbnrCount()+" - "+i);
        countstatment.add("SECURITY");
        count.add(new Entry(Float.valueOf(paramresponse.getVosList().get(i).getAbnrCount()),  i));
    }
}
else if(paramresponse.getVosList().get(i).getAbnrType().equals("COMMERCIAL")) {

    if (paramresponse.getVosList().get(i).getAbnrCount().equals("0")) {
        System.out.println(paramresponse.getVosList().get(i).getAbnrCount()+" - "+i);
    } else {
        countstatment.add("COMMERCIAL");
        System.out.println("Commercial - "+paramresponse.getVosList().get(i).getAbnrCount()+" - "+i);
        count.add(new Entry(Float.valueOf(paramresponse.getVosList().get(i).getAbnrCount()),  i));
    }
}
else if(paramresponse.getVosList().get(i).getAbnrType().equals("CW")) {

    if (paramresponse.getVosList().get(i).getAbnrCount().equals("0")) {
        System.out.println(paramresponse.getVosList().get(i).getAbnrCount()+" - "+i);
    } else {
        System.out.println("CW"+paramresponse.getVosList().get(i).getAbnrCount()+" - "+i);
        countstatment.add("CW");
        count.add(new Entry(Float.valueOf(paramresponse.getVosList().get(i).getAbnrCount()), i));
    }
}
else if(paramresponse.getVosList().get(i).getAbnrType().equals("DMU")) {
    if (paramresponse.getVosList().get(i).getAbnrCount().equals("0")) {
        System.out.println(paramresponse.getVosList().get(i).getAbnrCount()+" - "+i);
    } else {
        System.out.println("DMU - "+paramresponse.getVosList().get(i).getAbnrCount()+" - "+i);
        countstatment.add("DMU");
        count.add(new Entry(Float.valueOf(paramresponse.getVosList().get(i).getAbnrCount()),  i));
    }
}
else if(paramresponse.getVosList().get(i).getAbnrType().equals("OHE")) {

    if (paramresponse.getVosList().get(i).getAbnrCount().equals("0")) {
        System.out.println(">>>>>>>>>>>OHE"+paramresponse.getVosList().get(i).getAbnrCount()+" - "+i);
    } else {
        System.out.println("OHE -"+paramresponse.getVosList().get(i).getAbnrCount()+" - "+i);
        countstatment.add("OHE");
        count.add(new Entry(Float.valueOf(paramresponse.getVosList().get(i).getAbnrCount()),  i));
    }
}
else if(paramresponse.getVosList().get(i).getAbnrType().equals("TRAFFIC")) {
    if (paramresponse.getVosList().get(i).getAbnrCount().equals("0")) {
        System.out.println(paramresponse.getVosList().get(i).getAbnrCount()+" - "+i);
    } else {
        System.out.println("Traffic - "+paramresponse.getVosList().get(i).getAbnrCount()+" - "+i);
        countstatment.add("TRAFFIC");
        count.add(new Entry(Float.valueOf(paramresponse.getVosList().get(i).getAbnrCount()),  i));
    }
}
else if(paramresponse.getVosList().get(i).getAbnrType().equals("YS")) {
    countdept.add(new Entry(Float.valueOf(paramresponse.getVosList().get(i).getAbnrCount()), i));
    deptstatment.add("Closed Abnormality");
    closedcount=paramresponse.getVosList().get(i).getAbnrCount();
    System.out.println(">>>>>>>>>>>>>>closed number" + paramresponse.getVosList().get(i).getAbnrCount());
}
i++;

if(!countdept.isEmpty()){
    showPieChart(piechart,countdept,deptstatment);
}
if(!count.isEmpty()){
    showPieChart(pieChart_dept,count,countstatment);
}

} }
        int pn=Integer.parseInt(pendingcount);
        int cs=Integer.parseInt(closedcount);
        counts=pn+cs;
        last_Pending_Date.setText("Total Reported Abnormality Count - " +counts +"\n"+"Last Pending Date - "+pendingdate);
        tv_closed.setText("Department wise Pending Abnormality Count");
        Calendar cal=Calendar.getInstance();
        SimpleDateFormat month_date = new SimpleDateFormat("MMMM");
        String month_name = month_date.format(cal.getTime());
        heading.setText("Abnormality Chart for Month - "+month_name);
        heading.setTextColor(getResources().getColor(R.color.colorBlack));
        heading.setTextSize(16);
        heading.setTypeface(Typeface.SERIF);


            if (object instanceof Sectionresponse) {
                System.out.println("Key sucess>>>>>>>>>>");
                Sectionresponse sectionresponse = (Sectionresponse) object;
                System.out.println("Key sucess>>>>>>>>>>");
                if ((sectionresponse.getVosList().isEmpty())) {
                    System.out.println("No subhead found");
                }
                if (sectionresponse.getVosList().get(i).equals("SUBHEAD")) {
                    while (i + 1 < sectionresponse.getVosList().size()) {

                        sectionresponse.getVosList().get(i + 1);
                        subheadlist.add(sectionresponse.getVosList().get(i + 1));
                        System.out.println("subheadlist" + (sectionresponse.getVosList().get(i + 1)));
                        i++;
                    }
                    i=0;
                    spn_subhead.setOnItemSelectedListener(this);
                    ArrayAdapter subhead = new ArrayAdapter(this, android.R.layout.simple_spinner_item, subheadlist);
                    subhead.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    //Setting the ArrayAdapter data on the Spinner
                    spn_subhead.setAdapter(subhead);

                    }
                    else{
                        while(i<sectionresponse.getVosList().size()-1){

                            System.out.println("sectionlist" + (sectionresponse.getVosList().get(i)));

                            if(!sectionresponse.getVosList().get(i).equals("")){
                                sectionlist.add(sectionresponse.getVosList().get(i));
                            }
                        i++;
                        }

                        spn_sectionlist.setOnItemSelectedListener(this);
                    ArrayAdapter section = new ArrayAdapter(this, android.R.layout.simple_spinner_item, sectionlist);
                    section.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spn_sectionlist.setAdapter(section);

                }
            }

            if (object instanceof Remarksresponse) {
                heading.setText("Reported Pending Abnormality Chart");
                heading.setTextColor(getResources().getColor(R.color.colorBlack));
                heading.setTextSize(16);
                heading.setTypeface(Typeface.SERIF);
                System.out.println("Key sucess>>>>>>>>>> in Remarksresponse ");
                Remarksresponse remarksresponse = (Remarksresponse) object;
                System.out.println("Key sucess>>>>>>>>>> in Remarksresponse");
                ArrayList <String> monthwise = new ArrayList <>();
                barEntryList.clear();
                monthwise.clear();
                while (i < remarksresponse.getVosList().size()) {
                    System.out.println(remarksresponse.getVosList().get(i).getAbnrCount());
                    System.out.println(remarksresponse.getVosList().get(i).getAbnrMonthWise());
                    barEntryList.add(new BarEntry(Float.valueOf(remarksresponse.getVosList().get(i).getAbnrCount()), i));

                    if (remarksresponse.getVosList().get(i).getAbnrMonthWise().equals("1")) {
                        monthwise.add("January");
                    } else if (remarksresponse.getVosList().get(i).getAbnrMonthWise().equals("2")) {
                        monthwise.add("February");
                    } else if (remarksresponse.getVosList().get(i).getAbnrMonthWise().equals("3")) {
                        monthwise.add("March");
                    } else if (remarksresponse.getVosList().get(i).getAbnrMonthWise().equals("4")) {
                        monthwise.add("April");
                    } else if (remarksresponse.getVosList().get(i).getAbnrMonthWise().equals("5")) {
                        monthwise.add("May");
                    } else if (remarksresponse.getVosList().get(i).getAbnrMonthWise().equals("6")) {
                        monthwise.add("June");
                    } else if (remarksresponse.getVosList().get(i).getAbnrMonthWise().equals("7")) {
                        monthwise.add("July");
                    } else if (remarksresponse.getVosList().get(i).getAbnrMonthWise().equals("8")) {
                        monthwise.add("August");
                    } else if (remarksresponse.getVosList().get(i).getAbnrMonthWise().equals("9")) {
                        monthwise.add("September");
                    } else if (remarksresponse.getVosList().get(i).getAbnrMonthWise().equals("10")) {
                        monthwise.add("October");
                    } else if (remarksresponse.getVosList().get(i).getAbnrMonthWise().equals("11")) {
                        monthwise.add("November");
                    } else if (remarksresponse.getVosList().get(i).getAbnrMonthWise().equals("12")) {
                        monthwise.add("December");
                    }
                    i++;

                }
                showBarChart("Count of Reported Pending Abnormality", barEntryList, monthwise);
                setBarChartProperty(barChart, "");
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

    private void showBarChart(String legends, List<BarEntry> dataList, List<String> titleList) {
        BarDataSet barDataSet = new BarDataSet(dataList, legends);
        barDataSet.setValueFormatter(new UnitValueFormatter(""));
        barDataSet.setColors(CommonClass.COLORFUL_COLORS);
        barDataSet.setValueTextSize(12);
        List <BarDataSet> dataSet = new ArrayList <>();
        dataSet.add(barDataSet);
        BarData data = new BarData(titleList, dataSet);
        barChart.setData(data);
        barChart.setDescription("");
        barChart.animateXY(1000, 1000);
        barChart.invalidate();
    }
    private void setBarChartProperty(BarChart barChart, String unit) {
        barChart.getAxisRight().setEnabled(false);
        barChart.getAxisLeft().setValueFormatter(new UnitValueFormatter(unit));
        XAxis xl = barChart.getXAxis();
        xl.setPosition(XAxis.XAxisPosition.BOTTOM);
        xl.setDrawAxisLine(true);


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
    private void showLogoutDialog (final Activity context, String msg, final boolean isLogout){
        new AlertDialog.Builder(context).setCancelable(isLogout)
                .setTitle(getResources().getString(R.string.cms))
                .setMessage(msg)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        if (isLogout) {
                            logOut();
                        }
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        Toast.makeText(getApplicationContext(), "Resume your work",
                                Toast.LENGTH_SHORT).show();

                    }
                })
                .setIcon(R.drawable.icon_logo)
                .show();
    }

    private void logOut () {
        //userLoginPreferences.clearUser();
        DataHolder.setLevel(0);
        CommonClass.goToNextScreen(Abnormality_Dashboard.this, DetailController.class, true, Constants.LOGIN_OPTIONS);


        Intent landing = new Intent(Abnormality_Dashboard.this, LandingActivity.class);
        landing.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                | Intent.FLAG_ACTIVITY_CLEAR_TASK
                | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(landing);
        overridePendingTransition(R.animator.slide_in_right, R.animator.slide_out_left);
        finish();
    }

    @Override
    public void onItemSelected(AdapterView <?> parent, View view, int position, long id) {
        //selected_spinner = parent.getItemAtPosition(position).toString();
        Spinner spn_department = (Spinner) parent;
        Spinner spn_sections = (AppCompatSpinner) parent;
        Spinner spn_subhead = (Spinner) parent;
        if (spn_department.getId() == R.id.spn_department) {

            ArrayList <String> deptlist = new ArrayList <>();
            selected_spinner = parent.getItemAtPosition(position).toString();

            System.out.println(">>>>>>>Inside onItemSelecetd1>>>>>>>>>" +selected_spinner);
            if (!selected_spinner.equals("SELECT DEPT")) {
                selected_spinner = parent.getItemAtPosition(position).toString();
                System.out.println(">>>>>>>Inside onItemSelecetddept>>>>>>>>>" +selected_spinner);
                deptlist.clear();
                deptlist.add(selected_spinner);
                subheadlist.clear();
                request.setparamlist(deptlist);
                requestPresenter.Request(request, "fetching subheads", Constants.SUBHEADLIST);

            }
            if (selected_spinner.equals("SELECT DEPT")) {
                //Toast.makeText(getApplicationContext(), "Please select Department", Toast.LENGTH_SHORT).show();

            }
            System.out.println(">>>>>>>Inside onItemSelecetd>>>>>>>>>" + position);
        }

        if (spn_subhead.getId() == R.id.spn_subhead) {
            selected_spinnersubhead = parent.getItemAtPosition(position).toString();
            System.out.println(selected_spinnersubhead);
            if (!selected_spinnersubhead.equals("Select")) {
                send2 = selected_spinnersubhead;
                System.out.println(">>>>>>>>>>>>>" + send2);
                System.out.println(">>>>>>>Inside onItemSelecetd>>>>>>>>>" + position);
            }
            if (selected_spinnersubhead.equals("Select")) {
                    send2 = "";
            }
            System.out.println(">>>>>>>Inside onItemSelecetd>>>>>>>>>" + R.id.spn_sectionlist);
            }
        if(spn_sections.getId()==R.id.spn_sectionlist){
            selected_spinnersection = parent.getItemAtPosition(position).toString();
            System.out.println(selected_spinnersection);
            if(selected_spinnersection.equals("SELECT")){
                send="";
            }
            else{
                send=selected_spinnersection;

            }

        }
    }
    @Override
    public void onNothingSelected(AdapterView <?> parent) {

    }
}
