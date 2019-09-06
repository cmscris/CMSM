package com.cris.cmsm.fragments;

import android.app.Activity;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.cris.cmsm.R;
import com.cris.cmsm.database.DataHolder;
import com.cris.cmsm.models.request.LTConnectionRequest;
import com.cris.cmsm.models.request.SubmitLTConnection;
import com.cris.cmsm.models.response.LoginIfoVO;
import com.cris.cmsm.models.response.LtConnectionVO;
import com.cris.cmsm.models.response.ResSubmitLt;
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
public class LTConnectionFragment extends Fragment implements View.OnClickListener, ResponseView {

    private Button btn_submit;
    private TextInputLayout til_railway, til_division, til_sse_incharge, til_supc, til_no_connection, til_connected_load, til_energy_consumed, til_total_ebpd, til_avg_cost, til_remarks;
    private EditText et_railway, et_division, et_sse_inchagre, et_supc, et_no_connection, et_connected_load, et_energy_consumed, et_total_ebpd, et_avg_cost, et_remarks;
    private Typeface font, fontBold;
    private CommonClass commonClass;
    private InsertRequestPresenter insertRequestPresenter;
    private LTConnectionRequest connectionRequest;
    private LtConnectionVO ltConnectionVO;
    private LoginIfoVO loginIfoVO;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.lt_connection_view, container, false);
        loginIfoVO = new UserLoginPreferences(getActivity()).getLoginUser();
        insertRequestPresenter = new InsertRequestPresenter(LTConnectionFragment.this);
        commonClass = new CommonClass(getActivity());
        connectionRequest = DataHolder.getInstance().getLtConnectionRequest();
        ltConnectionVO = DataHolder.getInstance().getResLTConnection().getLtConnectionVOs().get(0);
        FontFamily fontFamily = new FontFamily(getActivity());
        font = fontFamily.getRegular();
        fontBold = fontFamily.getBold();
        til_railway = view.findViewById(R.id.til_railway);
        til_division = view.findViewById(R.id.til_division);
        til_sse_incharge = view.findViewById(R.id.til_sse_incharge);
        til_supc = view.findViewById(R.id.til_supc);
        til_no_connection = view.findViewById(R.id.til_no_connection);
        til_connected_load = view.findViewById(R.id.til_connected_load);
        til_energy_consumed = view.findViewById(R.id.til_energy_consumed);
        til_total_ebpd = view.findViewById(R.id.til_total_ebpd);
        til_avg_cost = view.findViewById(R.id.til_avg_cost);
        til_remarks = view.findViewById(R.id.til_remarks);

        et_railway = view.findViewById(R.id.et_railway);
        et_division = view.findViewById(R.id.et_division);
        et_sse_inchagre = view.findViewById(R.id.et_sse_inchagre);
        et_supc = view.findViewById(R.id.et_supc);
        et_no_connection = view.findViewById(R.id.et_no_connection);
        et_connected_load = view.findViewById(R.id.et_connected_load);
        et_energy_consumed = view.findViewById(R.id.et_energy_consumed);
        et_total_ebpd = view.findViewById(R.id.et_total_ebpd);
        et_avg_cost = view.findViewById(R.id.et_avg_cost);
        et_remarks = view.findViewById(R.id.et_remarks);

        btn_submit = view.findViewById(R.id.btn_submit);

        TextView tv_header_name = view.findViewById(R.id.tv_header_name);
        tv_header_name.setTypeface(font);

        til_railway.setTypeface(font);
        til_division.setTypeface(font);
        til_sse_incharge.setTypeface(font);
        til_supc.setTypeface(font);
        til_no_connection.setTypeface(font);
        til_connected_load.setTypeface(font);
        til_energy_consumed.setTypeface(font);
        til_total_ebpd.setTypeface(font);
        til_avg_cost.setTypeface(font);
        til_remarks.setTypeface(font);

        et_railway.setTypeface(font);
        et_division.setTypeface(font);
        et_sse_inchagre.setTypeface(font);
        et_supc.setTypeface(font);
        et_no_connection.setTypeface(font);
        et_connected_load.setTypeface(font);
        et_energy_consumed.setTypeface(font);
        et_total_ebpd.setTypeface(font);
        et_avg_cost.setTypeface(font);
        et_remarks.setTypeface(font);
        btn_submit.setTypeface(fontBold);


        et_railway.setText(connectionRequest.getRlycode());
        et_division.setText(connectionRequest.getDivcode());
        et_sse_inchagre.setText(connectionRequest.getSseInCharge());
        et_supc.setText(connectionRequest.getSebName());

        if (ltConnectionVO != null) {
            et_no_connection.setText(CommonClass.getRoundOff(ltConnectionVO.getNofeeder()));
            et_connected_load.setText(CommonClass.getRoundOff(ltConnectionVO.getLoad()));
            et_avg_cost.setText(CommonClass.getRoundOff(ltConnectionVO.getAverage()));
            et_energy_consumed.setText(CommonClass.getRoundOff(ltConnectionVO.getConsumption()));
            et_total_ebpd.setText(CommonClass.getRoundOff(ltConnectionVO.getBilling()));
            et_remarks.setText(ltConnectionVO.getRemarks());

            et_no_connection.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(2)});
            et_connected_load.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(2)});
            et_avg_cost.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(2)});
            et_energy_consumed.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(2)});
            et_total_ebpd.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(2)});

        }
        btn_submit.setOnClickListener(this);

        et_energy_consumed.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                getAverage(et_total_ebpd, s, false);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        et_total_ebpd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                getAverage(et_energy_consumed, s, true);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        return view;
    }

    private void getAverage(EditText et, CharSequence s, boolean isBill) {
        String cons, bill;
        if (isBill) {
            cons = et.getText().toString();
            bill = s.toString();
        } else {
            cons = s.toString();
            bill = et.getText().toString();
        }

        if (TextUtils.isEmpty(cons)) {
            et_avg_cost.setText("");
            return;
        } else if (TextUtils.isEmpty(bill)) {
            et_avg_cost.setText("");
            return;
        } else {
            Double avg = CommonClass.getRoundOff(cons, bill);
            if (Double.isNaN(avg))
                et_avg_cost.setText("Nan");
            else
                et_avg_cost.setText(CommonClass.getRoundOff(avg));
        }
    }

    @Override
    public void onClick(View v) {
        if (isValid()) {
            CommonClass.hideKeyBoard(v, getActivity());
            callWebService();
        }
    }

    private void callWebService() {
        SubmitLTConnection submitLTConnection = new SubmitLTConnection();
        submitLTConnection.setAnnexureId(ltConnectionVO.getAnnexureId());
        submitLTConnection.setAnnexureType(connectionRequest.getAnnexuretype());
        submitLTConnection.setAuthlevel(connectionRequest.getAuthlevel());
        submitLTConnection.setBilling(et_total_ebpd.getText().toString().trim());
        submitLTConnection.setConsumption(et_energy_consumed.getText().toString().trim());
        submitLTConnection.setDivision(connectionRequest.getDivcode());
        submitLTConnection.setRailCode(connectionRequest.getRlycode());
        submitLTConnection.setYear(connectionRequest.getAnnexurefinyear());
        submitLTConnection.setMonth(connectionRequest.getAnnexuremonth());
        submitLTConnection.setRoleId(TextUtils.isEmpty(connectionRequest.getRoleid()) ? "" : connectionRequest.getRoleid());
        submitLTConnection.setSsRolId(connectionRequest.getParameter());
        submitLTConnection.setSubstationId("");
        submitLTConnection.setSeb(connectionRequest.getSubstationId());
        submitLTConnection.setLoginId(loginIfoVO.getLoginid());
        submitLTConnection.setStatus("");
        submitLTConnection.setRemarks(et_remarks.getText().toString().trim());
        insertRequestPresenter.Request(submitLTConnection, "Saving LT Connection Records...", Constants.SAVE_LT_CONNECTION);
    }

    private boolean isValid() {
        if (et_energy_consumed.getText().toString().trim().isEmpty()) {
            commonClass.showToast("Please enter consumption.");
            return false;
        } else if (et_total_ebpd.getText().toString().trim().isEmpty()) {
            commonClass.showToast("Please enter energy bill paid.");
            return false;
        } else
            return true;
    }

    @Override
    public void ResponseOk(Object object, int position) {
        if (object != null && object instanceof ResSubmitLt) {
            ResSubmitLt response = (ResSubmitLt) object;
            if (response != null && response.getFlag()) {
                successDialog(getActivity(), "LTConnection Record saved successfully.");
            } else
                retryDialog(getActivity(), "Unable to save your record. Please try again. Do you can to retry");

        } else

            retryDialog(getActivity(), "Unable to save your record. Please try again. Do you can to retry");
    }

    @Override
    public void Error() {
        retryDialog(getActivity(), "Unable to save your record. Please try again. Do you can to retry");
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
                        callWebService();
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
