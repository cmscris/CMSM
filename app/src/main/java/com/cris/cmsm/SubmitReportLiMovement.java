package com.cris.cmsm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.cris.cmsm.adapter.LiMovementDetailAdapter;
import com.cris.cmsm.database.DataHolder;
import com.cris.cmsm.interfaces.OnItemClickListener;
import com.cris.cmsm.models.ReportHeaderView;
import com.cris.cmsm.models.request.GraphAPIRequest;
import com.cris.cmsm.models.response.LiMovementVOsResponse;
import com.cris.cmsm.models.response.Limovementresponse;
import com.cris.cmsm.models.response.LoginIfoVO;
import com.cris.cmsm.prefrences.UserLoginPreferences;
import com.cris.cmsm.presenter.RequestPresenter;
import com.cris.cmsm.presenterview.ResponseView;
import com.cris.cmsm.util.CommonClass;
import com.cris.cmsm.util.Constants;
import com.cris.cmsm.widget.PinchRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class SubmitReportLiMovement extends AppCompatActivity implements OnItemClickListener,ResponseView {
    PinchRecyclerView pinchvw;
    private RequestPresenter requestPresenter;
    GraphAPIRequest request;
    CommonClass commonClass;
    private LoginIfoVO loginInfoModel;
    private UserLoginPreferences userLoginPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_report_li_movement);
        pinchvw=(PinchRecyclerView)findViewById(R.id.pinchvw);
        pinchvw.setLayoutManager(new LinearLayoutManager(this));
        requestPresenter=new RequestPresenter(SubmitReportLiMovement.this);
        commonClass=new CommonClass(SubmitReportLiMovement.this);
        request=new GraphAPIRequest();
        userLoginPreferences=new UserLoginPreferences(this);
        loginInfoModel = userLoginPreferences.getLoginUser();
        Bundle extra = getIntent().getExtras();
        if (extra != null) {
            ArrayList date = extra.getStringArrayList("date");

            System.out.println("request----"+date);
            request.setparamlist(date);
            requestPresenter.Request(request,"Please wait", Constants.LIMOVEMENTDETAILS);

        }
    }

    @Override
    public void ResponseOk(Object object, int position) {
        if (object instanceof Limovementresponse) {
            System.out.println("Key sucess>>>>>>>>>>");
            Limovementresponse limovementresponse = (Limovementresponse) object;
            System.out.println("Key sucess>>>>>>>>>>");

            if(limovementresponse!= null && limovementresponse.getLiMovementVOsResponse()!=null){
                System.out.println("Key sucess>>>>>>>>>>");
                ReportHeaderView reportHeaderView = new ReportHeaderView();
                String header_str= "LI Movement Report";

                header_str += "\nMonth -" +loginInfoModel.getLoginid();
                reportHeaderView.setEnergyConsume(header_str );
                LiMovementVOsResponse liMovementVOsResponse=new LiMovementVOsResponse();
                liMovementVOsResponse.setSno("SN");
                liMovementVOsResponse.setFromDtTime("FromDtTime");
                liMovementVOsResponse.setToDtTime("ToDtTime");
                liMovementVOsResponse.setDutyType("DutyType");
                liMovementVOsResponse.setFromSttn("FromSttn");
                liMovementVOsResponse.setToSttn("ToSttn");
                liMovementVOsResponse.setVia1("Via1");
                liMovementVOsResponse.setVia2("Via2");
                liMovementVOsResponse.setKms("KMs");
                liMovementVOsResponse.setLocoNo("LocoNo.");
                liMovementVOsResponse.setTrainNo("TrainNo.");
                liMovementVOsResponse.setRemrk("Remarks");
                List <LiMovementVOsResponse> list = new ArrayList<>(limovementresponse.getLiMovementVOsResponse());
                list.add(0,liMovementVOsResponse );
                pinchvw.setAdapter(new LiMovementDetailAdapter(SubmitReportLiMovement.this,reportHeaderView,SubmitReportLiMovement.this,list));
            }
            else {
                commonClass.showToast("No Record Found");

            }



        }
    }

    @Override
    public void Error() {
        commonClass.showToast("No Record Found");

    }

    @Override
    public void dismissProgress() {
        commonClass.dismissDialog();

    }

    @Override
    public void showProgress(String msg) {
        commonClass.showProgressBar(msg);

    }

    @Override
    public void OnItemClick(Object model) {

    }
}
