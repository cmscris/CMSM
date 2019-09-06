package com.cris.cmsm;

import android.app.Dialog;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.cris.cmsm.adapter.DivisonAdapter;
import com.cris.cmsm.adapter.LTConnectionAdapter;
import com.cris.cmsm.adapter.MenuAdapter;
import com.cris.cmsm.adapter.MonthAdapter;
import com.cris.cmsm.adapter.RailwayAdapter;
import com.cris.cmsm.adapter.SSEAdapter;
import com.cris.cmsm.adapter.SpinnerAdapter;
import com.cris.cmsm.adapter.SubStationAdapter;
import com.cris.cmsm.database.DataBaseManager;
import com.cris.cmsm.database.DataHolder;
import com.cris.cmsm.fragments.SubStationFragment;
import com.cris.cmsm.interfaces.RecyclerItemListener;
import com.cris.cmsm.models.Division;
import com.cris.cmsm.models.MenuModel;
import com.cris.cmsm.models.Month;
import com.cris.cmsm.models.Railway;
import com.cris.cmsm.models.request.LTConnectionRequest;
import com.cris.cmsm.models.request.RequestSSERole;
import com.cris.cmsm.models.request.SEBRequest;
import com.cris.cmsm.models.request.SubStationConsumption;
import com.cris.cmsm.models.request.SubStationRequest;
import com.cris.cmsm.models.response.LoginIfoVO;
import com.cris.cmsm.models.response.ResLTConnection;
import com.cris.cmsm.models.response.ResponseSSERole;
import com.cris.cmsm.models.response.SEBResponse;
import com.cris.cmsm.models.response.SebMaster;
import com.cris.cmsm.models.response.SubStation;
import com.cris.cmsm.models.response.SubStationResponse;
import com.cris.cmsm.navcontrollers.DetailController;
import com.cris.cmsm.prefrences.UserLoginPreferences;
import com.cris.cmsm.presenter.InsertRequestPresenter;
import com.cris.cmsm.presenterview.ResponseView;
import com.cris.cmsm.util.CommonClass;
import com.cris.cmsm.util.Constants;
import com.cris.cmsm.util.FontFamily;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Anand.kumar on 10/24/2016.
 */
public class SubStationActivity extends BaseActivity implements RecyclerItemListener, AdapterView.OnItemSelectedListener, ResponseView {

    private RecyclerView recyclerView;
    private TextView tv_copyright, action_bar_title;
    private FontFamily fontFamily;
    private ImageView iv_left, iv_middle, iv_right, iv_title_icon;
    private Resources res;
    private LoginIfoVO loginUser;
    private DataBaseManager dataBaseManager;
    private CommonClass commonClass;
    private InsertRequestPresenter insertRequestPresenter;
    private AppCompatSpinner spn_zone, spn_sssc, spn_division, spn_subdivision, spn_fy, spn_month;
    private boolean isSubStation = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        insertRequestPresenter = new InsertRequestPresenter(SubStationActivity.this);
        commonClass = new CommonClass(SubStationActivity.this);
        dataBaseManager = new DataBaseManager(SubStationActivity.this);
        dataBaseManager.createDatabase();
        res = getResources();
        loginUser = new UserLoginPreferences(SubStationActivity.this).getLoginUser();
        fontFamily = new FontFamily(SubStationActivity.this);
        iv_title_icon = findViewById(R.id.iv_title_icon);
        iv_left = findViewById(R.id.iv_left);
        iv_middle = findViewById(R.id.iv_middle);
        iv_right = findViewById(R.id.iv_right);
        action_bar_title = findViewById(R.id.action_bar_title);
        tv_copyright = findViewById(R.id.tv_copyright);
        recyclerView = findViewById(R.id.recyclerView);
        GridLayoutManager manager = new GridLayoutManager(SubStationActivity.this, 2);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(new MenuAdapter(SubStationActivity.this, SubStationActivity.this, getMenuList()));
        recyclerView.setNestedScrollingEnabled(false);

        iv_title_icon.setImageResource(R.drawable.iv_back);

        iv_left.setVisibility(View.GONE);
        iv_middle.setVisibility(View.GONE);
        iv_right.setVisibility(View.GONE);

        action_bar_title.setTypeface(fontFamily.getBold());
        action_bar_title.setText(getResources().getString(R.string.consumption_entry));
        tv_copyright.setTypeface(fontFamily.getRegular());
        iv_title_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }


    @Override
    protected void onResume() {
        super.onResume();
        if (SubStationFragment.viewPager != null) {
            SubStationFragment.viewPager = null;
        }
        DataHolder.getInstance().setSaveConsumption(null);
    }

    private List<MenuModel> getMenuList() {
        List<MenuModel> list = new ArrayList<>();
        try {
            list.add(new MenuModel(R.color.colorCardRed, R.drawable.sub_station, res.getString(R.string.sub_station)));
            if (!TextUtils.isEmpty(loginUser.getCategory()) && (loginUser.getAuthlevel().equalsIgnoreCase(Constants.AUTH_DIVISION) || loginUser.getAuthlevel().equalsIgnoreCase(Constants.AUTH_SSE)) &&
                    (loginUser.getCategory().equals(Constants.CATEGORY_GENERAL_SERVICE)
                            || loginUser.getCategory().equals(Constants.CATEGORY_WORKSHOP))) {
                list.add(new MenuModel(R.color.colorCardMagenta, R.drawable.sub_station_lt, res.getString(R.string.sub_station_lt)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }


    @Override
    public void onItemClickListener(MenuModel model) {
        if (model.getMenuTitle().equals(res.getString(R.string.sub_station))) {
            showPopup(model.getMenuTitle(), Constants.Substation);
        } else if (model.getMenuTitle().equals(res.getString(R.string.sub_station_lt))) {
            showPopup(model.getMenuTitle(), Constants.LTConnection);
        }
    }


    private void showPopup(String title, final int redirect) {
        final Dialog dialog;
        if (Build.VERSION.SDK_INT >= 21) {
            dialog = new Dialog(SubStationActivity.this, android.R.style.Theme_Material_Light_Dialog_NoActionBar);
        } else {
            dialog = new Dialog(SubStationActivity.this);
        }
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCanceledOnTouchOutside(false);
        dialog.setContentView(R.layout.pop_up);
        TextView action_bar_title = dialog.findViewById(R.id.action_bar_title);
        TextView tv_title = dialog.findViewById(R.id.tv_title);
        spn_zone = dialog.findViewById(R.id.spn_zone);
        spn_division = dialog.findViewById(R.id.spn_division);
        List<Railway> railwayList = dataBaseManager.getRailwayList(false);
        spn_zone.setAdapter(new RailwayAdapter(SubStationActivity.this, railwayList));
        spn_zone.setSelection(commonClass.getUserRailwayIndex(railwayList, loginUser.getRlycode() != null ? loginUser.getRlycode() : ""));
        if (!TextUtils.isEmpty(loginUser.getAuthlevel()) && loginUser.getAuthlevel().equalsIgnoreCase(Constants.AUTH_BOARD)) {
            spn_zone.setEnabled(true);
        } else {
            spn_zone.setEnabled(false);
        }
        List<Division> divisionList = dataBaseManager.getDivisionList(loginUser.getRlycode());
        spn_division.setAdapter(new DivisonAdapter(SubStationActivity.this, divisionList));
        spn_division.setSelection(commonClass.getDivisionIndex(divisionList, loginUser.getDivcode() != null ? loginUser.getDivcode() : ""));
        if (!TextUtils.isEmpty(loginUser.getAuthlevel()) && (loginUser.getAuthlevel().equalsIgnoreCase(Constants.AUTH_BOARD)
                || loginUser.getAuthlevel().equalsIgnoreCase(Constants.AUTH_RAILWAY))) {
            spn_division.setEnabled(true);
        } else {
            spn_division.setEnabled(false);
        }
        spn_sssc = dialog.findViewById(R.id.spn_sssc);
        spn_sssc.setOnItemSelectedListener(SubStationActivity.this);
        spn_subdivision = dialog.findViewById(R.id.spn_subdivision);
        setSSEAdapter();
        if (redirect == Constants.Substation) {
            isSubStation = true;
            setSpn_subdivision();
        } else if (redirect == Constants.LTConnection) {
            isSubStation = false;
            setSEBAdapter();
        }
        spn_fy = dialog.findViewById(R.id.spn_fy);
        spn_fy.setAdapter(new SpinnerAdapter(SubStationActivity.this, CommonClass.getFinancialYear()));
        spn_month = dialog.findViewById(R.id.spn_month);
        spn_month.setAdapter(new MonthAdapter(SubStationActivity.this, CommonClass.getMonthsList()));
        tv_title.setText(title);
        action_bar_title.setTypeface(fontFamily.getBold());
        tv_title.setTypeface(fontFamily.getBold());
        Button btn_submit = dialog.findViewById(R.id.btn_submit);
        btn_submit.setTypeface(fontFamily.getBold());
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isValid()) {
                    dialog.dismiss();
                    if (spn_subdivision.getSelectedItem() instanceof SubStation) {
                        subStationConsumption();
                    } else if (spn_subdivision.getSelectedItem() instanceof SebMaster) {
                        ltConnection();
                    }
                }
            }
        });

        dialog.show();
        callWebService();
    }


    private void subStationConsumption() {
        SubStationConsumption consumption = new SubStationConsumption();
        consumption.setRailCode(((Railway) spn_zone.getSelectedItem()).getRlycode());
        consumption.setDivCode(((Division) spn_division.getSelectedItem()).getDivcode());
        consumption.setFyYear((String) spn_fy.getSelectedItem());
        consumption.setMonth(((Month) spn_month.getSelectedItem()).getMonthCode());
        consumption.setSubstationId(((SubStation) spn_subdivision.getSelectedItem()).getSubstationId());
        consumption.setParameter(((ResponseSSERole) spn_sssc.getSelectedItem()).getRoleid());
        consumption.setPauthlevel("3");
        consumption.setRoleId(loginUser.getRoleid());
        consumption.setLoginid(loginUser.getLoginid());
        consumption.setAuthLevel(loginUser.getAuthlevel());
        if (loginUser.getCategory().equals(Constants.CATEGORY_TRANSACTION)) {
            consumption.setAnnextureType("AT1");
        } else
            consumption.setAnnextureType("A14");
        DataHolder.getInstance().setConsumption(consumption);
        insertRequestPresenter.Request(consumption, "Getting consumption details", Constants.Substation);
    }


    private void ltConnection() {
        LTConnectionRequest ltConnectionRequest = new LTConnectionRequest();
        ltConnectionRequest.setRlycode(((Railway) spn_zone.getSelectedItem()).getRlycode());
        ltConnectionRequest.setDivcode(((Division) spn_division.getSelectedItem()).getDivcode());
        ltConnectionRequest.setAnnexurefinyear((String) spn_fy.getSelectedItem());
        ltConnectionRequest.setAnnexuremonth(((Month) spn_month.getSelectedItem()).getMonthCode());
        ltConnectionRequest.setParameter(((ResponseSSERole) spn_sssc.getSelectedItem()).getRoleid());
        ltConnectionRequest.setSubstationId(((SebMaster) spn_subdivision.getSelectedItem()).getSebCode());
        ltConnectionRequest.setPauthlevel("3");
        ltConnectionRequest.setRoleid(loginUser.getRoleid());
        ltConnectionRequest.setLoginid(loginUser.getLoginid());
        ltConnectionRequest.setAnnexuretype("A34");
        ltConnectionRequest.setAuthlevel(loginUser.getAuthlevel());
        insertRequestPresenter.Request(ltConnectionRequest, "Getting LTConnection details", Constants.GET_LT_CONNECTION);
        // These parameter will be set after api calling.
        ltConnectionRequest.setSseInCharge(((ResponseSSERole) spn_sssc.getSelectedItem()).getName());
        ltConnectionRequest.setSebName(((SebMaster) spn_subdivision.getSelectedItem()).getSebName());
        DataHolder.getInstance().setLtConnectionRequest(ltConnectionRequest);
    }

    private boolean isValid() {
        int month = Calendar.getInstance().get(Calendar.MONTH);
        if (spn_sssc != null && spn_sssc.isShown() && ((ResponseSSERole) spn_sssc.getSelectedItem()).getRoleid().equals("-100")) {
            commonClass.showToast("Please select SSE");
            return false;
        } else if (spn_subdivision.getSelectedItem() instanceof SubStation && ((SubStation) spn_subdivision.getSelectedItem()).getSubstationId().equals("-100")) {
            commonClass.showToast("Please select SubStation");
            return false;
        } else if (spn_subdivision.getSelectedItem() instanceof SebMaster && ((SebMaster) spn_subdivision.getSelectedItem()).getSebCode().equals("-100")) {
            commonClass.showToast("Please select SEB");
            return false;
        } else
            return true;
    }

    private void callWebService() {
        RequestSSERole requestSSERole = new RequestSSERole();
        requestSSERole.setAuthLevel(Integer.valueOf(loginUser.getAuthlevel()));
        requestSSERole.setCategory(loginUser.getCategory());
        requestSSERole.setLoginid(loginUser.getLoginid());
        requestSSERole.setDivcode(((Division) spn_division.getSelectedItem()).getDivcode());
        if (((Railway) spn_zone.getSelectedItem()).getFlag().equalsIgnoreCase("R"))
            requestSSERole.setHqstnCode("");
        else
            requestSSERole.setHqstnCode(((Railway) spn_zone.getSelectedItem()).getRlycode());
        insertRequestPresenter.Request(requestSSERole, "Getting SSE Role...", Constants.GET_SSE);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Object object = parent.getItemAtPosition(position);
        if (object instanceof ResponseSSERole) {
            ResponseSSERole responseSSERole = (ResponseSSERole) object;
            if (responseSSERole.getRoleid().equals("-100")) {
                commonClass.showToast("Please select sse");
            } else {
                if (isSubStation) {
                    SubStationRequest subStationRequest = new SubStationRequest();
                    subStationRequest.setRoleId(responseSSERole.getRoleid());
                    if (!TextUtils.isEmpty(loginUser.getCategory()) && loginUser.getCategory().equalsIgnoreCase(Constants.CATEGORY_TRANSACTION))
                        subStationRequest.setCategory("TRD");
                    else
                        subStationRequest.setCategory("GEN");
                    insertRequestPresenter.Request(subStationRequest, "Getting SubStation", Constants.GET_SUBSTATION);
                } else {
                    SEBRequest sebRequest = new SEBRequest();
                    sebRequest.setRoleId(responseSSERole.getRoleid());
                    System.out.println("Role id " + responseSSERole.getRoleid());
                    insertRequestPresenter.Request(sebRequest, "Getting Seb", Constants.GET_SEB);
                }
            }
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void ResponseOk(Object object, int position) {
        try {
            switch (position) {
                case Constants.GET_SSE:
                    List<ResponseSSERole> list = (List<ResponseSSERole>) object;
                    if (spn_sssc != null && list != null && list.size() > 0) {
                        ResponseSSERole hintModel = new ResponseSSERole();
                        hintModel.setName("Select SSE");
                        hintModel.setRoleid("-100");
                        list.add(0, hintModel);
                        spn_sssc.setAdapter(new SSEAdapter(SubStationActivity.this, list));
                        if (!TextUtils.isEmpty(loginUser.getRoleid())) {
                            spn_sssc.setSelection(commonClass.getSSEIndex(list, loginUser.getRoleid()));
                            spn_sssc.setEnabled(false);
                        }
                    }
                    break;

                case Constants.GET_SUBSTATION:
                    List<SubStation> subStationList = (List<SubStation>) object;
                    if (spn_subdivision != null && subStationList != null && subStationList.size() > 0) {
                        SubStation hintModel = new SubStation();
                        hintModel.setSubstationName("Select Sub Station");
                        hintModel.setSubstationId("-100");
                        subStationList.add(0, hintModel);
                        spn_subdivision.setAdapter(new SubStationAdapter(SubStationActivity.this, subStationList));
                    } else {
                        commonClass.showToast("No Data Available.");
                        setSpn_subdivision();
                    }
                    break;

                case Constants.GET_SEB:
                    SEBResponse sebResponse = (SEBResponse) object;
                    if (sebResponse.getIsSuccess()) {
                        List<SebMaster> sebMasterList = sebResponse.getSebMasters();
                        if (spn_subdivision != null && sebMasterList != null && sebMasterList.size() > 0) {
                            SebMaster sebMaster = new SebMaster();
                            sebMaster.setSebCode("-100");
                            sebMaster.setSebName("Select SEB");
                            sebMasterList.add(0, sebMaster);
                            spn_subdivision.setAdapter(new LTConnectionAdapter(SubStationActivity.this, sebMasterList));
                        } else {
                            commonClass.showToast("No Data Available.");
                            setSEBAdapter();
                        }
                    }
                    break;
                case Constants.Substation:
                    SubStationResponse subStationResponse = (SubStationResponse) object;
                    if (subStationResponse != null && subStationResponse.getIsSuccess()) {
                        DataHolder.getInstance().setSubStationResponse(subStationResponse);
                        CommonClass.goToNextScreen(SubStationActivity.this, DetailController.class, true, Constants.Substation);
                    } else {
                        commonClass.showToast("No Data Available.");
                    }
                    break;
                case Constants.GET_LT_CONNECTION:
                    ResLTConnection resLTConnection = (ResLTConnection) object;
                    if (resLTConnection != null && resLTConnection.getIsSuccess()) {
                        DataHolder.getInstance().setResLTConnection(resLTConnection);
                        CommonClass.goToNextScreen(SubStationActivity.this, DetailController.class, true, Constants.LTConnection);
                    } else {
                        commonClass.showToast("No Data Available.");
                    }
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void Error() {
        commonClass.showToast("Unable to fetch data. Please try again");
    }

    @Override
    public void dismissProgress() {
        commonClass.dismissDialog();
    }

    @Override
    public void showProgress(String msg) {
        commonClass.showProgressBar(msg);
    }


    private void setSSEAdapter() {
        List<ResponseSSERole> subStationList = new ArrayList<>();
        ResponseSSERole hintModel = new ResponseSSERole();
        hintModel.setName("Select SSE");
        hintModel.setRoleid("-100");
        subStationList.add(hintModel);
        if (spn_sssc != null)
            spn_sssc.setAdapter(new SSEAdapter(SubStationActivity.this, subStationList));
    }

    private void setSpn_subdivision() {
        List<SubStation> subStationList = new ArrayList<>();
        SubStation subStation = new SubStation();
        subStation.setSubstationId("-100");
        subStation.setSubstationName("Select Sub Station");
        subStationList.add(subStation);
        if (spn_subdivision != null)
            spn_subdivision.setAdapter(new SubStationAdapter(SubStationActivity.this, subStationList));
    }

    private void setSEBAdapter() {
        List<SebMaster> sebMasterList = new ArrayList<>();
        SebMaster sebMaster = new SebMaster();
        sebMaster.setSebCode("-100");
        sebMaster.setSebName("Select SEB");
        sebMasterList.add(sebMaster);
        if (spn_subdivision != null)
            spn_subdivision.setAdapter(new LTConnectionAdapter(SubStationActivity.this, sebMasterList));
    }


}
