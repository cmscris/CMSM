package com.cris.cmsm;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.cris.cmsm.database.DataHolder;
import com.cris.cmsm.models.request.GraphAPIRequest;
import com.cris.cmsm.models.response.Paramresponse;
import com.cris.cmsm.models.response.TabularReportVO;
import com.cris.cmsm.navcontrollers.DetailController;
import com.cris.cmsm.presenter.RequestPresenter;
import com.cris.cmsm.presenterview.ResponseView;
import com.cris.cmsm.util.CommonClass;
import com.cris.cmsm.util.Constants;

import java.util.ArrayList;

import de.codecrafters.tableview.TableDataAdapter;
import de.codecrafters.tableview.TableView;
import de.codecrafters.tableview.colorizers.TableDataRowColorizer;
import de.codecrafters.tableview.listeners.SwipeToRefreshListener;
import de.codecrafters.tableview.listeners.TableDataClickListener;
import de.codecrafters.tableview.model.TableColumnModel;
import de.codecrafters.tableview.model.TableColumnWeightModel;
import de.codecrafters.tableview.providers.TableDataRowBackgroundProvider;
import de.codecrafters.tableview.toolkit.SimpleTableDataAdapter;
import de.codecrafters.tableview.toolkit.SimpleTableHeaderAdapter;
import de.codecrafters.tableview.toolkit.TableDataRowBackgroundProviders;
import de.codecrafters.tableview.toolkit.TableDataRowColorizers;

import static com.cris.cmsm.R.layout.activity_abnorresponse_activity;

public class abnorresponse_activity extends AppCompatActivity implements ResponseView{


    TextView txt_statemnt,dept;
    TextView action_bar_title;
    private ImageView iv_left, iv_middle, iv_right;

    final ArrayList <String> Crewab = new ArrayList <>();

    String[] tableheader = {"SN", "CrewID", "Abnormality No."};
    String[][] abresponse;
    Context context;
    StringBuilder sb=new StringBuilder();

    int i = 0;
int count;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_abnorresponse_activity);
        txt_statemnt = (TextView) findViewById(R.id.txt_statment);
        dept = (TextView) findViewById(R.id.dept);
        action_bar_title = (TextView) findViewById(R.id.action_bar_title);
        iv_left = (ImageView) findViewById(R.id.iv_left);

        iv_middle = (ImageView) findViewById(R.id.iv_middle);
        iv_right = (ImageView) findViewById(R.id.iv_right);
        iv_left.setVisibility(View.GONE);
        iv_middle.setVisibility(View.GONE);
        iv_right.setVisibility(View.VISIBLE);
        RequestPresenter requestPresenter = new RequestPresenter(this);
        GraphAPIRequest request = new GraphAPIRequest();
        iv_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                case R.id.iv_right:
                    showLogoutDialog(abnorresponse_activity.this, "Do you want to logout?", true);
                    break;
            }
            }
        });
        Bundle extra = getIntent().getExtras();
        if (extra != null) {
            final ArrayList<String> paralist = extra.getStringArrayList("object");
            action_bar_title.setText(paralist.get(1));
            request.setparamlist(paralist);
            requestPresenter.Request(request, "c", Constants.ABNORMALITYLIST);


        }

    }

    @Override
    public void ResponseOk(Object object, int position) {
        final TableView tableView = (TableView) findViewById(R.id.tableview);
        tableView.setColumnCount(3);
        TableColumnWeightModel columnModel = new TableColumnWeightModel(3);
        columnModel.setColumnWeight(0, 1);
        columnModel.setColumnWeight(1, 2);
        columnModel.setColumnWeight(2, 3);

        //TableColumnDpWidthModel widthModel = new TableColumnDpWidthModel(this,3,100);
        //widthModel.setColumnWidth();

        tableView.setColumnModel(columnModel);
        tableView.setHeaderAdapter(new SimpleTableHeaderAdapter(this, tableheader));
       
        //tableView.setDataRowColorizer(TableDataRowColorizers.alternatingRows(R.color.colorWhite, R.color.colorBlack));
        //tableView.setSwipeToRefreshListener(SwipeToRefreshListener.RefreshIndicator::isVisible);
        //tableView.setSwipeToRefreshEnabled(true);
       /* if (tableView.isSwipeToRefreshEnabled()) {
            RequestPresenter requestPresenter = new RequestPresenter(this);
            GraphAPIRequest request = new GraphAPIRequest();
            ArrayList <String> para = new ArrayList <>();
            para.add(DataHolder.getRlycode());
            para.add(DataHolder.getRoleid());
            request.setparamlist(para);
            requestPresenter.Request(request, "c", Constants.ABNORMALITYLIST);
            tableView.setSwipeToRefreshEnabled(false);
            System.out.println("Key sucess>>>>>>>>>>isisiisisResponse");
        }*/


            if (object instanceof Paramresponse) {
                System.out.println("Key sucess>>>>>>>>>>");
                Paramresponse paramresponse = (Paramresponse) object;
                System.out.println("Key sucess>>>>>>>>>>");
                if ((paramresponse.getVosList().isEmpty())) {
                    System.out.println("No abnormality found");


                } else {
                    int counter = 0;
                    final ArrayList <abresponse> ABRES = new ArrayList <>();
                    System.out.println("length" + (paramresponse.getVosList()).size());

                    while (i < (paramresponse.getVosList()).size()) {
                        System.out.println("key" + (paramresponse.getVosList()).get(i).getabnrNumber());
                        System.out.println("key" + (paramresponse.getVosList()).get(i).getCrewId());
                        System.out.println("key>>>>>>>>" + (paramresponse.getVosList()).get(i).getAppUserRemarksStts());


                  //String a = (paramresponse.getVosList()).get(i).getAbnormalityNumber();
                  //String b = (paramresponse.getVosList()).get(i).getCrewId();
                        count = counter + (i + 1);

                        abresponse abresponse1 = new abresponse();
                        abresponse1.setSNO("" + (i + 1));
                        abresponse1.setCREW_ID((paramresponse.getVosList()).get(i).getCrewId());
                        abresponse1.setABNORMALITY_NO((paramresponse.getVosList()).get(i).getabnrNumber());
                        //abresponse1.setApp_Remarks((paramresponse.getVosList()).get(i).getappUserRemarks());
                        ABRES.add(abresponse1);
                        i++;
                    }
                    dept.setText("Total Abnormalities = " + count);


                    abresponse = new String[ABRES.size()][3];
                    for (int i = 0; i < ABRES.size(); i++) {
                        abresponse abres = ABRES.get(i);
                        abresponse[i][0] = abres.getSNO();
                        abresponse[i][1] = abres.getCREW_ID();
                        abresponse[i][2] = abres.getABNORMALITY_NO();
                    }
                    /*tableView.setDataRowColorizer(new TableDataRowColorizer() {
                        @Override
                        public int getRowColor(int rowIndex, Object rowData) {
                            if (rowData.toString().contains("R")) {
                                return R.color.colorCardMagenta;
                            }
                            return R.color.colorWhite;
                        }

                    });*/

                    //tableView.setSwipeToRefreshEnabled(false);
                    //if(tableView.isSwipeToRefreshEnabled()){
                    //RequestPresenter requestPresenter = new RequestPresenter(this);
                    // GraphAPIRequest request = new GraphAPIRequest();
                    //ArrayList<String> para=new ArrayList <>();
                    //para.add(DataHolder.getRlycode());
                    //para.add(DataHolder.getRoleid());
                    //request.setparamlist(para);
                    //requestPresenter.Request(request,"c",Constants.ABNORMALITYLIST);
                    //System.out.println("Key sucess>>>>>>>>>>isisiisisResponse");


                }

                tableView.setDataAdapter(new SimpleTableDataAdapter(this, abresponse));

                tableView.addDataClickListener(new TableDataClickListener() {

                    public void onDataClicked(int rowIndex, Object clickedData) {
                        System.out.println("" + ((String[]) clickedData)[1]);
                        System.out.println("" + ((String[]) clickedData)[2]);
                        String a = ((String[]) clickedData)[1];


                        String b = ((String[]) clickedData)[2];
                        Crewab.add(a);
                        Crewab.add(b);
                        Intent i = new Intent(abnorresponse_activity.this, Responsedetail.class);
                        i.putExtra("object", Crewab);

                        startActivityForResult(i, 1);
                        Toast.makeText(abnorresponse_activity.this, ((String[]) clickedData)[2], Toast.LENGTH_LONG).show();

                    }
                });

            }


            i = 0;
        }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) {
            if(resultCode == Activity.RESULT_OK){
                String result=data.getStringExtra("result");
                System.out.println("Result final>>>>>>>>>>" +result);
                RequestPresenter requestPresenter = new RequestPresenter(this);
                GraphAPIRequest request = new GraphAPIRequest();
              ArrayList<String> para=new ArrayList <>();
              para.add(DataHolder.getRlycode());
              para.add(DataHolder.getRoleid());
              request.setparamlist(para);

              requestPresenter.Request(request,"c",Constants.ABNORMALITYLIST);
                System.out.println("Key sucess>>>>>>>>>>isisiisis");

            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result
            }
        }
    }//onA
    private void showLogoutDialog(final Activity context, String msg, final boolean isLogout) {
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
                        dialog.dismiss();
                        if (isLogout) {
                            logOut();
                        } else {
                            context.finish();
                        }
                    }
                })
                .setIcon(R.drawable.icon_logo)
                .show();
    }

    private void logOut() {
        //userLoginPreferences.clearUser();
        DataHolder.setLevel(0);
        CommonClass.goToNextScreen(abnorresponse_activity.this, DetailController.class, true, Constants.LOGIN_OPTIONS);


        Intent landing = new Intent(abnorresponse_activity.this, LandingActivity.class);
        landing.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                | Intent.FLAG_ACTIVITY_CLEAR_TASK
                | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(landing);
        overridePendingTransition(R.animator.slide_in_right, R.animator.slide_out_left);
        finish();
    }

        @Override
    public void Error() {

    }

    @Override
    public void dismissProgress() {

    }

    @Override
    public void showProgress(String msg) {

    }

}
