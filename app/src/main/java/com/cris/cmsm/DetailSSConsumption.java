package com.cris.cmsm;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.cris.cmsm.adapter.CapacitorAdapter;
import com.cris.cmsm.adapter.FeederAdapter;
import com.cris.cmsm.adapter.TransformerAdapter;
import com.cris.cmsm.database.DataHolder;
import com.cris.cmsm.models.request.RequestSSAssets;
import com.cris.cmsm.models.request.SSConsumptionRequest;
import com.cris.cmsm.models.response.Cbvo;
import com.cris.cmsm.models.response.ResTabularReport;
import com.cris.cmsm.models.response.SsFeedersVO;
import com.cris.cmsm.models.response.SubStationRes;
import com.cris.cmsm.models.response.SubstationResponseVO;
import com.cris.cmsm.models.response.TransformersVO;
import com.cris.cmsm.navcontrollers.SubStationAssetsReportsController;
import com.cris.cmsm.presenter.MonthlyRequestPresenter;
import com.cris.cmsm.presenterview.ResponseView;
import com.cris.cmsm.util.CommonClass;
import com.cris.cmsm.util.Constants;
import com.cris.cmsm.util.FontFamily;
import com.cris.cmsm.widget.NonScrollListView;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */

public class DetailSSConsumption extends BaseActivity implements View.OnClickListener, ResponseView {

    private ImageView iv_title_icon;
    private TextView action_bar_title;
    private Typeface font;
    private TextView tv_title, tv_rly, tv_divCode, tv_name, tv_seb, tv_state, tv_sse, tv_iv, tv_connected_load, tv_contract_demand, ty_meter_type;
    private NonScrollListView lv_feeder, lv_transformer, lv_capacitor;
    private LinearLayout ll_feeder, ll_transformer, ll_capacitor;
    private ScrollView scrollView;
    private TextView tv_dgset, tv_transfr, tv_fireextinguisher, tv_earthing, tv_apfc, tv_ltpanel, tv_htpanel, tv_servostabilizer;
    private CommonClass commonClass;
    private MonthlyRequestPresenter monthlyRequestPresenter;
    private SubstationResponseVO model;
    private LinearLayout linear_trd, linear_gen;
    private View view_trd;
    private TextView tv_date, tv_trans_line, tv_transline_rly, tv_no_transformer, tv_no_capacitor, tv_out_feeder, tv_location, tv_conn_type;
    private SSConsumptionRequest ssConsumptionRequest;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_ss_consumption);
        monthlyRequestPresenter = new MonthlyRequestPresenter(DetailSSConsumption.this);
        commonClass = new CommonClass(DetailSSConsumption.this);
        ssConsumptionRequest = DataHolder.getInstance().getSsConsumptionRequest();
        SubStationRes subStationRes = DataHolder.getInstance().getSubStationRes();
        font = new FontFamily(DetailSSConsumption.this).getBold();
        scrollView = findViewById(R.id.scrollView);
        iv_title_icon = findViewById(R.id.iv_title_icon);
        action_bar_title = findViewById(R.id.action_bar_title);

        action_bar_title.setTypeface(font);
        action_bar_title.setText("Sub-Stations Details");

        iv_title_icon.setImageResource(R.drawable.iv_back);
        iv_title_icon.setOnClickListener(this);

        linear_trd = findViewById(R.id.linear_trd);
        view_trd = findViewById(R.id.view_trd);
        tv_date = findViewById(R.id.tv_date);
        tv_trans_line = findViewById(R.id.tv_trans_line);
        tv_transline_rly = findViewById(R.id.tv_transline_rly);
        tv_no_transformer = findViewById(R.id.tv_no_transformer);
        tv_no_capacitor = findViewById(R.id.tv_no_capacitor);
        tv_out_feeder = findViewById(R.id.tv_out_feeder);
        tv_location = findViewById(R.id.tv_location);

        tv_rly = findViewById(R.id.tv_rly);
        tv_divCode = findViewById(R.id.tv_divCode);
        tv_name = findViewById(R.id.tv_name);
        tv_seb = findViewById(R.id.tv_seb);
        tv_state = findViewById(R.id.tv_state);
        tv_sse = findViewById(R.id.tv_sse);
        tv_iv = findViewById(R.id.tv_iv);
        tv_connected_load = findViewById(R.id.tv_connected_load);
        tv_contract_demand = findViewById(R.id.tv_contract_demand);
        ty_meter_type = findViewById(R.id.ty_meter_type);
        ll_feeder = findViewById(R.id.ll_feeder);
        ll_transformer = findViewById(R.id.ll_transformer);
        ll_capacitor = findViewById(R.id.ll_capacitor);
        tv_conn_type = findViewById(R.id.tv_conn_type);

        lv_feeder = findViewById(R.id.lv_feeder);
        lv_transformer = findViewById(R.id.lv_transformer);
        lv_capacitor = findViewById(R.id.lv_capacitor);
        linear_gen = findViewById(R.id.linear_gen);

        tv_title = findViewById(R.id.tv_title);
        tv_title.setText(getIntent().getStringExtra(Constants.PAGE_TITLE));


        if (Constants.CATEGORY_TRANSACTION.equalsIgnoreCase(ssConsumptionRequest.getCategory())) {
            linear_gen.setVisibility(View.GONE);
        }

        model = subStationRes.getSubstationResponseVO().get(0);
        if (model != null) {
            tv_rly.setText(DataHolder.getInstance().getSubstationResponseVO().getRlycode());
            tv_divCode.setText(DataHolder.getInstance().getSubstationResponseVO().getDivcode());
            tv_name.setText(model.getAssetname());
            tv_seb.setText(model.getSname());
            tv_state.setText(model.getStatecode());
            tv_sse.setText(model.getRoleName());
            tv_iv.setText(model.getIvolt());
            tv_connected_load.setText(model.getLoad());
            tv_contract_demand.setText(model.getCtdemand());
            ty_meter_type.setText(model.getMeteredss());
            tv_out_feeder.setText(model.getNofeeder());
            if (ssConsumptionRequest != null && ssConsumptionRequest.getCategory().equalsIgnoreCase(Constants.CATEGORY_TRANSACTION)) {
                view_trd.setVisibility(View.VISIBLE);
                linear_trd.setVisibility(View.VISIBLE);
                tv_conn_type.setText("Location");
                tv_location.setText(model.getLocation());
                tv_date.setText(model.getCommisiondate());
                tv_trans_line.setText(model.getCablelenght());
                tv_transline_rly.setText(model.getOwner());
                tv_no_transformer.setText(model.getTransformercount());
                tv_no_capacitor.setText(model.getCbcount());
            } else {
                view_trd.setVisibility(View.GONE);
                linear_trd.setVisibility(View.GONE);
                tv_conn_type.setText("Type of Connection");
                tv_location.setText(model.getTypeofconn());
            }
            if (subStationRes.getSsFeedersVOs() != null && subStationRes.getSsFeedersVOs().size() > 0) {
                ll_feeder.setVisibility(View.VISIBLE);
                SsFeedersVO title = new SsFeedersVO();
                title.setName("Name");
                title.setConnectedLoad("Connected Load");
                List<SsFeedersVO> list = new ArrayList<>(subStationRes.getSsFeedersVOs());
                list.add(0, title);
                lv_feeder.setAdapter(new FeederAdapter(DetailSSConsumption.this, list));
            }

            if (subStationRes.getTransformersVOs() != null && subStationRes.getTransformersVOs().size() > 0) {
                ll_transformer.setVisibility(View.VISIBLE);
                TransformersVO title = new TransformersVO();
                title.setName("Transformer Name");
                title.setType("Type (Owned By)");
                title.setCapacity("Capacity");
                List<TransformersVO> list = new ArrayList<>(subStationRes.getTransformersVOs());
                list.add(0, title);
                lv_transformer.setAdapter(new TransformerAdapter(DetailSSConsumption.this, list));
            }

            if (subStationRes.getCbvos() != null && subStationRes.getCbvos().size() > 0) {
                ll_capacitor.setVisibility(View.VISIBLE);
                Cbvo title = new Cbvo();
                title.setName("CB Name");
                title.setType("Type");
                title.setCapacity("Capacity  \n (in KVAR)");
                title.setActualCapacity("Actual Capacity  \n (in KVAR)");
                title.setCommissiondate("Date of Commissioning");
                title.setSupplier("Supplier");
                title.setInitcost("Inital Cost \n (in Rs.)");
                title.setPfold("PF before Commissioning");
                List<Cbvo> list = new ArrayList<>(subStationRes.getCbvos());
                list.add(0, title);
                lv_capacitor.setAdapter(new CapacitorAdapter(DetailSSConsumption.this, list));
            }

        }
        scrollView
                .setDescendantFocusability(ViewGroup.FOCUS_BEFORE_DESCENDANTS);
        scrollView.setFocusable(true);
        scrollView.setFocusableInTouchMode(true);
        scrollView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                v.requestFocusFromTouch();
                return false;
            }
        });


        tv_dgset = findViewById(R.id.tv_dgset);
        tv_transfr = findViewById(R.id.tv_transfr);
        tv_fireextinguisher = findViewById(R.id.tv_fireextinguisher);
        tv_earthing = findViewById(R.id.tv_earthing);
        tv_apfc = findViewById(R.id.tv_apfc);
        tv_ltpanel = findViewById(R.id.tv_ltpanel);
        tv_htpanel = findViewById(R.id.tv_htpanel);
        tv_servostabilizer = findViewById(R.id.tv_servostabilizer);


        tv_dgset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onModelClick("DGSET", "01");
            }
        });
        tv_transfr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onModelClick("TRANSFORMER", "02");
            }
        });
        tv_fireextinguisher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onModelClick("FIRE EXTINGUISHER", "03");
            }
        });
        tv_earthing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onModelClick("EARTHING", "04");
            }
        });
        tv_apfc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onModelClick("APFC", "05");
            }
        });
        tv_htpanel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onModelClick("HT PANEL", "06");
            }
        });
        tv_ltpanel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onModelClick("LT PANEL", "07");
            }
        });
        tv_servostabilizer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onModelClick("SERVO STABILIZER", "19");
            }
        });

    }


    private void onModelClick(String name, String id) {
        try {
            if (CommonClass.checkInternetConnection(DetailSSConsumption.this)) {
                if (model != null) {
                    RequestSSAssets requestSSAssets = new RequestSSAssets();
                    requestSSAssets.setRailCode(DataHolder.getInstance().getSubstationResponseVO().getRlycode());
                    requestSSAssets.setDivision(DataHolder.getInstance().getSubstationResponseVO().getDivcode());
                    requestSSAssets.setAssestType(id);
                    requestSSAssets.setSseIncharge(model.getRoleid());
                    requestSSAssets.setSubStation(model.getAssest());
                    monthlyRequestPresenter.Request(requestSSAssets, "Getting details...", Constants.TABULAR_REPORT);
                    requestSSAssets.setAssetsName(name);
                    DataHolder.getInstance().setRequestSSAssets(requestSSAssets);
                } else
                    commonClass.showToast("Unable to post request for data.");
            } else
                commonClass.showToast("No internet available.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        onBackPressed();
    }

    @Override
    public void ResponseOk(Object object, int position) {
        switch (position) {
            case Constants.TABULAR_REPORT:
                ResTabularReport resTabularReport = (ResTabularReport) object;
                DataHolder.getInstance().setTabularReport(resTabularReport);
                CommonClass.goToNextScreen(DetailSSConsumption.this, SubStationAssetsReportsController.class, true, false);
                break;
        }
    }

    @Override
    public void Error() {
        commonClass.showToast("Unable to fetch record. Please try again.");
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
