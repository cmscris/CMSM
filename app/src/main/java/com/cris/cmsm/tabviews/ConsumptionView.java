package com.cris.cmsm.tabviews;

import android.app.DatePickerDialog;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatSpinner;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.cris.cmsm.R;
import com.cris.cmsm.adapter.SpinnerAdapter;
import com.cris.cmsm.database.DataHolder;
import com.cris.cmsm.fragments.SubStationFragment;
import com.cris.cmsm.models.request.SaveConsumption;
import com.cris.cmsm.models.request.SubStationConsumption;
import com.cris.cmsm.models.response.LoginIfoVO;
import com.cris.cmsm.models.response.SubstationInfos;
import com.cris.cmsm.prefrences.UserLoginPreferences;
import com.cris.cmsm.util.CommonClass;
import com.cris.cmsm.util.DecimalDigitsInputFilter;
import com.cris.cmsm.util.FontFamily;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Anand.kumar on 10/25/2016.
 */
public class ConsumptionView extends Fragment implements View.OnClickListener {

    private Button btn_save_next;
    private AppCompatSpinner spn_year;
    private TextInputLayout til_connected_load, til_to_time, til_from_time, til_mdi, til_pf, til_tecu, til_nmbill, til_totalbill, til_avg_cost, til_remarks;
    private TextView et_tebp, et_arre;
    private EditText et_connected_load, et_to_time, et_from_time, et_mdi, et_pf, et_tecu, et_nmbill, et_amout, et_totalbill, et_avg_cost, et_remarks;
    private Typeface font, fontBold;
    private CommonClass commonClass;
    private SaveConsumption saveConsumption;
    private SubstationInfos model;
    private SubStationConsumption consumption;
    private LoginIfoVO loginUser;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.consumption_view, container, false);
        loginUser = new UserLoginPreferences(getActivity()).getLoginUser();
        model = DataHolder.getInstance().getSubStationResponse().getSubstationInfos();
        consumption = DataHolder.getInstance().getConsumption();
        DataHolder.getInstance().setSaveConsumption(new SaveConsumption());
        saveConsumption = DataHolder.getInstance().getSaveConsumption();

        commonClass = new CommonClass(getActivity());
        FontFamily fontFamily = new FontFamily(getActivity());
        font = fontFamily.getRegular();
        fontBold = fontFamily.getBold();
        TextView tv_header_name = view.findViewById(R.id.tv_header_name);
        tv_header_name.setTypeface(font);
        til_connected_load = view.findViewById(R.id.til_connected_load);
        til_to_time = view.findViewById(R.id.til_to_time);
        til_from_time = view.findViewById(R.id.til_from_time);
        til_mdi = view.findViewById(R.id.til_mdi);
        til_pf = view.findViewById(R.id.til_pf);
        til_tecu = view.findViewById(R.id.til_tecu);
        til_nmbill = view.findViewById(R.id.til_nmbill);
        til_totalbill = view.findViewById(R.id.til_totalbill);
        til_avg_cost = view.findViewById(R.id.til_avg_cost);
        til_remarks = view.findViewById(R.id.til_remarks);


        et_tebp = view.findViewById(R.id.et_tebp);
        et_arre = view.findViewById(R.id.et_arre);
        et_connected_load = view.findViewById(R.id.et_connected_load);
        et_to_time = view.findViewById(R.id.et_to_time);
        et_from_time = view.findViewById(R.id.et_from_time);
        et_mdi = view.findViewById(R.id.et_mdi);
        et_pf = view.findViewById(R.id.et_pf);
        et_tecu = view.findViewById(R.id.et_tecu);
        et_nmbill = view.findViewById(R.id.et_nmbill);
        et_amout = view.findViewById(R.id.et_amout);
        et_totalbill = view.findViewById(R.id.et_totalbill);
        et_avg_cost = view.findViewById(R.id.et_avg_cost);
        et_remarks = view.findViewById(R.id.et_remarks);

        spn_year = view.findViewById(R.id.spn_year);
        btn_save_next = view.findViewById(R.id.btn_save_next);

        til_connected_load.setTypeface(font);
        til_to_time.setTypeface(font);
        til_from_time.setTypeface(font);
        til_mdi.setTypeface(font);
        til_pf.setTypeface(font);
        til_tecu.setTypeface(font);
        til_nmbill.setTypeface(font);
        til_totalbill.setTypeface(font);
        til_avg_cost.setTypeface(font);
        til_remarks.setTypeface(font);
        et_tebp.setTypeface(font);
        et_arre.setTypeface(font);
        et_connected_load.setTypeface(font);
        et_to_time.setTypeface(font);
        et_from_time.setTypeface(font);
        et_mdi.setTypeface(font);
        et_pf.setTypeface(font);
        et_tecu.setTypeface(font);
        et_nmbill.setTypeface(font);
        et_amout.setTypeface(font);
        et_totalbill.setTypeface(font);
        et_avg_cost.setTypeface(font);
        et_remarks.setTypeface(font);
        btn_save_next.setTypeface(fontBold);
        spn_year.setAdapter(new SpinnerAdapter(getActivity(), CommonClass.getSubYear()));
        spn_year.setSelection(commonClass.getYearIndex(model.getArryear()));
        btn_save_next.setOnClickListener(this);


        setEditText(et_connected_load, CommonClass.getRoundOff(model.getConnload()));
        setEditText(et_to_time, model.getTdate());
        setEditText(et_from_time, model.getFdate());
        setEditText(et_mdi, CommonClass.getRoundOff(model.getMdi()));
        setEditText(et_pf, CommonClass.getRoundOff(model.getPf()));
        setEditText(et_amout, CommonClass.getRoundOff(model.getArramount() == null ? "0" : model.getArramount()));
        setEditText(et_tecu, CommonClass.getRoundOff(model.getConsumption()));
        setEditText(et_nmbill, CommonClass.getRoundOff(model.getNormalbilling() == null ? "0" : model.getNormalbilling()));
        setEditText(et_totalbill, CommonClass.getRoundOff(model.getBilling()));
        setEditText(et_avg_cost, CommonClass.getRoundOff(model.getAverage()));
        setEditText(et_remarks, model.getRemarks());

        et_totalbill.setFocusable(false);
        et_totalbill.setFocusableInTouchMode(false);

        et_avg_cost.setFocusable(false);
        et_avg_cost.setFocusableInTouchMode(false);


        et_from_time.setFocusable(false);
        et_from_time.setClickable(true);
        et_from_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(true).show();
            }
        });

        et_to_time.setFocusable(false);
        et_to_time.setClickable(true);
        et_to_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (et_from_time.getText().toString().trim().isEmpty()) {
                    commonClass.showToast("Please select from date first.");
                } else {
                    showDialog(false).show();
                }
            }
        });

        et_nmbill.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                setValueTotal(et_amout, s);

            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        et_amout.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                setValueTotal(et_nmbill, s);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        et_tecu.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                showAverage();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

/*
-- commenting code is for forwarding---
if (!TextUtils.isEmpty(model.getStatus()) && model.getStatus().equalsIgnoreCase("C")) {
            et_connected_load.setEnabled(false);
            et_to_time.setEnabled(false);
            et_from_time.setEnabled(false);
            et_mdi.setEnabled(false);
            et_pf.setEnabled(false);
            et_tecu.setEnabled(false);
            et_nmbill.setEnabled(false);
            et_amout.setEnabled(false);
            et_totalbill.setEnabled(false);
            et_avg_cost.setEnabled(false);
            et_remarks.setEnabled(false);
            spn_year.setEnabled(false);
        }*/
        return view;
    }

    private void setValueTotal(EditText editText, CharSequence s) {
        try {
            String value = s.toString();
            String amount = editText.getText().toString();
            if (value.isEmpty() && amount.isEmpty()) {
                et_totalbill.setText("");
                showAverage();
            } else if (value.isEmpty() && !amount.isEmpty()) {
                et_totalbill.setText(CommonClass.getRoundOff(amount));
                showAverage();
            } else if (!value.isEmpty() && amount.isEmpty()) {
                et_totalbill.setText(CommonClass.getRoundOff(amount));
                showAverage();
            } else {
                Double d = Double.valueOf(amount) + Double.valueOf(value);
                et_totalbill.setText(CommonClass.getRoundOff(d));
                showAverage();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void showAverage() {
        try {
            if (et_totalbill.getText().toString().trim().isEmpty()) {
                et_avg_cost.setText("");
            } else if (et_tecu.getText().toString().trim().isEmpty()) {
                et_avg_cost.setText("");
            } else {
                Double averageCost = Double.valueOf(et_totalbill.getText().toString()) / Double.valueOf(et_tecu.getText().toString());
                et_avg_cost.setText(CommonClass.getRoundOff(averageCost));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        if (isValid()) {
            CommonClass.hideKeyBoard(v, getActivity());
            saveConsumption.setAnnexureHdrId(model.getAnnexureHDRID());
            saveConsumption.setAuthLevel(consumption.getAuthLevel());
            saveConsumption.setDevisionCode(consumption.getDivCode());
            saveConsumption.setFyYear(consumption.getFyYear());
            saveConsumption.setMonth(consumption.getMonth());
            saveConsumption.setRailCode(consumption.getRailCode());
            saveConsumption.setRoleCode(consumption.getRoleId());
            saveConsumption.setSubstaionID(consumption.getSubstationId());
            saveConsumption.setAnnexureType(consumption.getAnnextureType());
            saveConsumption.setLoginId(loginUser.getLoginid());
            saveConsumption.setSsRolId(model.getSsroleid());
            saveConsumption.setSsseb(model.getSsseb());
            saveConsumption.setConnectedLoad(et_connected_load.getText().toString());
            saveConsumption.setFdate(et_from_time.getText().toString());
            saveConsumption.setTdate(et_to_time.getText().toString());
            saveConsumption.setMdi(et_mdi.getText().toString());
            saveConsumption.setPf(et_pf.getText().toString());
            saveConsumption.setConsumption(et_tecu.getText().toString());
            saveConsumption.setNormalBilling(et_nmbill.getText().toString());
            saveConsumption.setBilling(et_totalbill.getText().toString());
            saveConsumption.setArrAmount(et_amout.getText().toString());
            saveConsumption.setArryear(spn_year.getSelectedItemPosition() == 0 ? "" : (String) spn_year.getSelectedItem());
            saveConsumption.setAverage(et_avg_cost.getText().toString());
            saveConsumption.setRoleCode(loginUser.getRoleid());
            saveConsumption.setTask("");
            saveConsumption.setCategory(loginUser.getCategory());
            saveConsumption.setRemark(et_remarks.getText().toString());
            if (SubStationFragment.viewPager != null) {
                SubStationFragment.viewPager.setCurrentItem(1);
            }
        }

    }

    private boolean isValid() {
        if (et_from_time.getText().toString().trim().isEmpty()) {
            commonClass.showToast("Please select from date");
            return false;
        } else if (et_from_time.getText().toString().trim().isEmpty()) {
            commonClass.showToast("Please select from date");
            return false;
        } else if (!et_pf.getText().toString().isEmpty()) {
            double d = Double.valueOf(et_pf.getText().toString());
            if (d <= 1)
                return true;
            else
                commonClass.showToast("PF(Power Factor) cannot be greater than 1.");
            return false;
        } else
            return true;
    }

    private void setEditText(EditText editText, String value) {
        if (value != null) {
            editText.setText(value);
        } else {
            editText.setText("");
        }
        editText.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(2)});
    }


    public DatePickerDialog showDialog(final boolean isFrom) {
        final Calendar calendar = Calendar.getInstance();
        android.app.DatePickerDialog datePickerDialog = new android.app.DatePickerDialog(getActivity(), new android.app.DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                String dayOfMonth;
                String monthOfYear;
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, day);
                if (day < 10) {
                    dayOfMonth = "0" + day;
                } else {
                    dayOfMonth = "" + day;
                }
                if (month + 1 < 10) {
                    monthOfYear = "0" + (month + 1);
                } else {
                    monthOfYear = "" + (month + 1);
                }
                if (isFrom) {
                    et_from_time.setText(dayOfMonth + "/" + monthOfYear + "/" + year);
                    et_to_time.setText("");
                } else {
                    et_to_time.setText(dayOfMonth + "/" + monthOfYear + "/" + year);
                }
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.getDatePicker().setMaxDate(new Date().getTime());
        System.out.println("value of from " + isFrom);
        if (isFrom) {
            // from date anything.
        } else {
            try {
                DateFormat inputDF = new SimpleDateFormat("dd/MM/yyyy");
                Date date1 = inputDF.parse(et_from_time.getText().toString().trim());
                datePickerDialog.getDatePicker().setMinDate(date1.getTime());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return datePickerDialog;
    }

}
