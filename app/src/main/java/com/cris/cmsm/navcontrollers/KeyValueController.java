package com.cris.cmsm.navcontrollers;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;

import com.cris.cmsm.R;
import com.cris.cmsm.adapter.KeyValueAdapter;
import com.cris.cmsm.adapter.LICrewMonitoredAdapter;
import com.cris.cmsm.database.DataHolder;
import com.cris.cmsm.interactor.WebServices;
import com.cris.cmsm.interfaces.OnItemClickListener;
import com.cris.cmsm.models.KeyValue;
import com.cris.cmsm.models.ReportHeaderView;
import com.cris.cmsm.models.request.GraphAPIRequest;
import com.cris.cmsm.models.request.KeyValueRequest;
import com.cris.cmsm.models.request.LoginRequest;
import com.cris.cmsm.models.response.LICrewMonitoredResponse;
import com.cris.cmsm.models.response.LICrewMonitoredResponseVO;
import com.cris.cmsm.models.response.LoginIfoVO;
import com.cris.cmsm.models.response.SubstationResponseVO;
import com.cris.cmsm.prefrences.UserLoginPreferences;
import com.cris.cmsm.presenter.InsertRequestPresenter;
import com.cris.cmsm.presenter.RequestPresenter;
import com.cris.cmsm.presenterview.ResponseView;
import com.cris.cmsm.util.CommonClass;
import com.cris.cmsm.util.Constants;
import com.cris.cmsm.widget.PinchRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cms on 4/12/18.
 */



public class KeyValueController extends AppCompatActivity implements OnItemClickListener, ResponseView {
    private PinchRecyclerView recyclerView;
    private CommonClass commonClass;
    private InsertRequestPresenter insertRequestPresenter;
    private SubstationResponseVO substationResponseVO;
    private LoginIfoVO loginInfoModel;
    private UserLoginPreferences userLoginPreferences;
    private RequestPresenter requestPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sub_station_report);
        requestPresenter = new RequestPresenter(KeyValueController.this);
        commonClass = new CommonClass(KeyValueController.this);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        userLoginPreferences = new UserLoginPreferences(KeyValueController.this);
        loginInfoModel = userLoginPreferences.getLoginUser();

        callWebService();

    }

    @Override
    public void OnItemClick(Object model) {
        if (model != null) {
            substationResponseVO = (SubstationResponseVO) model;
            insertRequestPresenter.Request(substationResponseVO.getAssest(), "Getting Details...", Constants.SS_DETAILS);
            DataHolder.getInstance().setSubstationResponseVO(substationResponseVO);
        }

    }





    private void callWebService() {
        try {

            KeyValueRequest request = new KeyValueRequest();
            Log.d("USER " , loginInfoModel.getLoginid());

            request.setUser(loginInfoModel.getLoginid());
            requestPresenter.Request(request, "Getting Data", Constants.KEY_VALUE);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }





    @Override
    public void ResponseOk(Object object, int position) {
        try {

            List<KeyValue> list = (ArrayList<KeyValue>)object;

            //System.out.println("Response is " + new Gson().toJson(liCrewMonitoredResponse));

            if (list != null ) {

                ReportHeaderView reportHeaderView = new ReportHeaderView();
                String header_str= "CREW : " + loginInfoModel.getLoginid() ;


                header_str += "\n Crew Biodata";
                reportHeaderView.setEnergyConsume(header_str );

                recyclerView.setAdapter(new KeyValueAdapter(KeyValueController.this, reportHeaderView, KeyValueController.this, list));
            }



        } catch (Exception e) {
            e.printStackTrace();
            commonClass.showToast("Unable to get data. Please try again.");
        }
    }


    @Override
    public void onBackPressed() {


            WebServices.getInstance().cancelAllRequest();
            super.onBackPressed();


    }



    @Override
    public void Error() {
        commonClass.showToast("Unable to get data. Please try again.");
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
