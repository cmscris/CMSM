package com.cris.cmsm.tabviews;

import android.app.Activity;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.InputFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cris.cmsm.R;
import com.cris.cmsm.database.DataHolder;
import com.cris.cmsm.models.request.SaveConsumption;
import com.cris.cmsm.models.response.LoginIfoVO;
import com.cris.cmsm.models.response.SaveConsumptionResponse;
import com.cris.cmsm.models.response.SubstationInfos;
import com.cris.cmsm.prefrences.UserLoginPreferences;
import com.cris.cmsm.presenter.InsertRequestPresenter;
import com.cris.cmsm.presenterview.ResponseView;
import com.cris.cmsm.util.CommonClass;
import com.cris.cmsm.util.Constants;
import com.cris.cmsm.util.DecimalDigitsInputFilter;
import com.cris.cmsm.util.FontFamily;

/**
 * Created by Anand.kumar on 10/25/2016.
 */
public class RebateView extends Fragment implements View.OnClickListener, ResponseView {

    private TextInputLayout til_tpr, til_tepr, til_total_rebate;
    private EditText et_tpr, et_tepr, et_total_rebate;
    private Button btn_submit, btn_forward;
    private Typeface font, fontBold;
    private LinearLayout linear_buttons;
    SaveConsumption saveConsumption;
    InsertRequestPresenter insertRequestPresenter;
    CommonClass commonClass;
    private SubstationInfos model;
    boolean isForward = false;
    private LoginIfoVO loginIfoVO;
    private View view_margin;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.rebate_view, container, false);
        loginIfoVO = new UserLoginPreferences(getActivity()).getLoginUser();
        model = DataHolder.getInstance().getSubStationResponse().getSubstationInfos();
        commonClass = new CommonClass(getActivity());
        insertRequestPresenter = new InsertRequestPresenter(RebateView.this);
        saveConsumption = DataHolder.getInstance().getSaveConsumption();
        FontFamily fontFamily = new FontFamily(getActivity());
        font = fontFamily.getRegular();
        fontBold = fontFamily.getBold();
        view_margin = view.findViewById(R.id.view_margin);
        TextView tv_header_name = view.findViewById(R.id.tv_header_name);
        tv_header_name.setTypeface(font);
        til_tpr = view.findViewById(R.id.til_tpr);
        til_tepr = view.findViewById(R.id.til_tepr);
        til_total_rebate = view.findViewById(R.id.til_total_rebate);

        et_tpr = view.findViewById(R.id.et_tpr);
        et_tepr = view.findViewById(R.id.et_tepr);
        et_total_rebate = view.findViewById(R.id.et_total_rebate);
        linear_buttons = view.findViewById(R.id.linear_buttons);
        btn_submit = view.findViewById(R.id.btn_submit);
        btn_forward = view.findViewById(R.id.btn_forward);

        til_tpr.setTypeface(font);
        til_tepr.setTypeface(font);
        til_total_rebate.setTypeface(font);

        et_tpr.setTypeface(font);
        et_tepr.setTypeface(font);
        et_total_rebate.setTypeface(font);

        btn_submit.setTypeface(fontBold);
        btn_forward.setTypeface(fontBold);
        btn_submit.setOnClickListener(this);
        btn_forward.setOnClickListener(this);
        setEditText(et_tpr, CommonClass.getRoundOff(model.getPfrebate()));
        setEditText(et_tepr, CommonClass.getRoundOff(model.getErpayrebate()));
        setEditText(et_total_rebate, CommonClass.getRoundOff(model.getEpaymentrebate()));

/*       -- commenting code is for forwarding---
if (!TextUtils.isEmpty(model.getStatus()) && model.getStatus().equalsIgnoreCase("C")) {
            et_tpr.setEnabled(false);
            et_tepr.setEnabled(false);
            et_total_rebate.setEnabled(false);
            linear_buttons.setVisibility(View.GONE);
        }

        if (!TextUtils.isEmpty(loginIfoVO.getAuthlevel()) && loginIfoVO.getAuthlevel().equalsIgnoreCase(Constants.AUTH_RAILWAY)) {
            btn_forward.setVisibility(View.GONE);
            view_margin.setVisibility(View.GONE);
        }*/
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_submit:
                isForward = false;
                callWebService("");
                break;
            case R.id.btn_forward:
                isForward = true;
                callWebService("C");
                break;
        }

        CommonClass.hideKeyBoard(v, getActivity());
    }


    private void setEditText(EditText editText, String value) {
        if (value != null) {
            editText.setText(value);
        } else {
            editText.setText("");
        }
        editText.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(2)});
    }

    private void callWebService(String task) {
        saveConsumption.setPfrebate(et_tpr.getText().toString());
        saveConsumption.setErpayrebate(et_tepr.getText().toString());
        saveConsumption.setEpaymentrebate(et_total_rebate.getText().toString());
        saveConsumption.setTask(task);
        insertRequestPresenter.Request(saveConsumption, "Saving your consumption...", Constants.SAVE_CONSUMPTION);
    }

    @Override
    public void ResponseOk(Object object, int position) {
        try {
            if (object instanceof SaveConsumptionResponse) {
                SaveConsumptionResponse saveConsumptionResponse = (SaveConsumptionResponse) object;
                if (saveConsumptionResponse.getFlag()) {
                    successDialog(getActivity(), "Consumption saved successfully.");
                }
            } else
                retryDialog(getActivity(), "Unable to save your consumption. Please try again. Do you can to retry");
        } catch (Exception e) {
            e.printStackTrace();
            retryDialog(getActivity(), "Unable to save your consumption. Please try again. Do you can to retry");
        }
    }

    @Override
    public void Error() {
        retryDialog(getActivity(), "Unable to save your consumption. Please try again. Do you can to retry");
    }

    @Override
    public void dismissProgress() {
        commonClass.dismissDialog();
    }

    @Override
    public void showProgress(String msg) {
        commonClass.showProgressBar(msg);
    }

    private void successDialog(final Activity activity, String msg) {
        new AlertDialog.Builder(activity).setCancelable(false)
                .setTitle(getResources().getString(R.string.cms))
                .setMessage(msg)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        activity.finish();
                    }
                })
                .setIcon(R.drawable.icon_logo)
                .show();
    }


    private void retryDialog(final Activity activity, String msg) {
        new AlertDialog.Builder(activity).setCancelable(false)
                .setTitle(getResources().getString(R.string.cms))
                .setMessage(msg)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        if (isForward)
                            callWebService("C");
                        else
                            callWebService("");
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setIcon(R.drawable.icon_logo)
                .show();
    }
}
