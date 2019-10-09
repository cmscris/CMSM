package com.cris.cmsm;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.cris.cmsm.adapter.Limovadapter;
import com.cris.cmsm.database.DataHolder;
import com.cris.cmsm.interfaces.OnItemClickListener;
import com.cris.cmsm.models.ReportHeaderView;
import com.cris.cmsm.models.response.LICrewMonitoredResponseVO;
import com.cris.cmsm.models.response.LoginIfoVO;
import com.cris.cmsm.prefrences.UserLoginPreferences;
import com.cris.cmsm.widget.PinchRecyclerView;

import java.util.ArrayList;

public class LI_activitydraftdetail extends AppCompatActivity implements OnItemClickListener  {

 PinchRecyclerView pinchRecyclerView;

    private LoginIfoVO loginInfoModel;
    private UserLoginPreferences userLoginPreferences;
    TableLayout tableLayout;

ArrayList<String> itemdatalist,listitem;
    ArrayList<Limovdraftresponse> senditemdatalist,senditemdatalist1;
    int i = 0;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_li_activitydraftdetail);
        pinchRecyclerView=(PinchRecyclerView)findViewById(R.id.pinchrecview);
       pinchRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        tableLayout = (TableLayout) findViewById(R.id.tablelayout);
        userLoginPreferences=new UserLoginPreferences(LI_activitydraftdetail.this);
        loginInfoModel = userLoginPreferences.getLoginUser();
        itemdatalist=new ArrayList <>();
        listitem=new ArrayList <>();
        senditemdatalist=new ArrayList<>();
        senditemdatalist1=new ArrayList <>();
        ArrayList innerList = new ArrayList();
        ReportHeaderView reportHeaderView = new ReportHeaderView();
        String header_str = "LI : " + loginInfoModel.getLoginid();

        header_str += "\n LI Movement detail";
        reportHeaderView.setEnergyConsume(header_str);




        System.out.println(">>>>>>>>>>>>>>>>>>LIMOVLIST" + DataHolder.getLimovmainlist());
        innerList.clear();
        innerList = (ArrayList)DataHolder.getLimovmainlist();
        System.out.println("....inside----3- "+innerList.size());
        i = 0;
        while (i < innerList.size()) {
            System.out.println("....inside----1- "+i);
            Limovdraftresponse limovdraftresponse = (Limovdraftresponse)innerList.get(i);
            System.out.println("id - "+limovdraftresponse.getId());
            System.out.println("from dt - "+limovdraftresponse.getFrmdttm());
            System.out.println("to dt - "+limovdraftresponse.getTodttm());
            i++;
        }

        pinchRecyclerView.setAdapter(new Limovadapter(LI_activitydraftdetail.this,reportHeaderView,LI_activitydraftdetail.this,innerList));



           }


    @Override
    public void OnItemClick(Object model) {

        if (model != null) {
            Limovdraftresponse li=(Limovdraftresponse)model;
            System.out.println("frmdt>>>>>>>>>"+li.getFrmdttm());
            System.out.println("todt>>>>>>>>>"+li.getTodttm());
            System.out.println("frmsttn>>>>>>>>>"+li.getFrmsttn());
            System.out.println("tosttn>>>>>>>>>"+li.getTosttn());
            System.out.println("dutytype>>>>>>>>>"+li.getDutytyp());
            System.out.println("loco>>>>>>>>>"+li.getLoco());
            System.out.println("train>>>>>>>>>"+li.getTrain());
            System.out.println("Remark>>>>>>>>>"+li.getRmk());
            ArrayList<String> formlist=new ArrayList();
            formlist.add(li.getId());
            formlist.add(li.getFrmdttm());
            formlist.add(li.getTodttm());
            formlist.add(li.getFrmsttn());
            formlist.add(li.getTosttn());
            formlist.add(li.getDutytyp());
            formlist.add(li.getLoco());
            formlist.add(li.getTrain());
            formlist.add(li.getRmk());
            formlist.add(li.getVia1());
            formlist.add(li.getVia2());
            formlist.add(li.getKm());
            DataHolder.setLimovlist(formlist);
            Intent intent = new Intent();
            intent.putExtra("keyName", formlist);
            setResult(Activity.RESULT_OK, intent);
            finish();


        }
    }

}
