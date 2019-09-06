package com.cris.cmsm.tabviews;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.text.InputFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.cris.cmsm.R;
import com.cris.cmsm.database.DataHolder;
import com.cris.cmsm.fragments.SubStationFragment;
import com.cris.cmsm.models.request.SaveConsumption;
import com.cris.cmsm.models.response.LoginIfoVO;
import com.cris.cmsm.models.response.SubstationInfos;
import com.cris.cmsm.prefrences.UserLoginPreferences;
import com.cris.cmsm.util.CommonClass;
import com.cris.cmsm.util.Constants;
import com.cris.cmsm.util.DecimalDigitsInputFilter;
import com.cris.cmsm.util.FontFamily;

/**
 * Created by Anand.kumar on 10/25/2016.
 */
public class SurchargeView extends Fragment implements View.OnClickListener {

    private Button btn_save_next;
    private TextInputLayout til_total_mdi, til_total_pf, til_tdps, til_tptm, til_tefs;
    private EditText et_total_mdi, et_total_pf, et_tdps, et_tptm, et_tefs;
    private Typeface font, fontBold;
    SaveConsumption saveConsumption;
    private SubstationInfos model;
    private LoginIfoVO loginUser;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.surcharge, container, false);
        loginUser = new UserLoginPreferences(getActivity()).getLoginUser();
        model = DataHolder.getInstance().getSubStationResponse().getSubstationInfos();
        saveConsumption = DataHolder.getInstance().getSaveConsumption();
        FontFamily fontFamily = new FontFamily(getActivity());
        font = fontFamily.getRegular();
        fontBold = fontFamily.getBold();
        TextView tv_header_name = view.findViewById(R.id.tv_header_name);
        tv_header_name.setTypeface(font);
        til_total_mdi = view.findViewById(R.id.til_total_mdi);
        til_total_pf = view.findViewById(R.id.til_total_pf);
        til_tdps = view.findViewById(R.id.til_tdps);
        til_tptm = view.findViewById(R.id.til_tptm);
        til_tefs = view.findViewById(R.id.til_tefs);

        et_total_mdi = view.findViewById(R.id.et_total_mdi);
        et_total_pf = view.findViewById(R.id.et_total_pf);
        et_tdps = view.findViewById(R.id.et_tdps);
        et_tptm = view.findViewById(R.id.et_tptm);
        et_tefs = view.findViewById(R.id.et_tefs);

        btn_save_next = view.findViewById(R.id.btn_save_next);

        til_total_mdi.setTypeface(font);
        til_total_pf.setTypeface(font);
        til_tdps.setTypeface(font);
        til_tptm.setTypeface(font);
        til_tefs.setTypeface(font);
        et_total_mdi.setTypeface(font);
        et_total_pf.setTypeface(font);
        et_tdps.setTypeface(font);
        et_tptm.setTypeface(font);
        et_tefs.setTypeface(font);
        btn_save_next.setTypeface(fontBold);

        btn_save_next.setOnClickListener(this);

        setEditText(et_total_mdi, CommonClass.getRoundOff(model.getMdipenalty()));
        setEditText(et_total_pf, CommonClass.getRoundOff(model.getPfpenalty()));
        setEditText(et_tdps, CommonClass.getRoundOff(model.getLtpaypenalty()));
        setEditText(et_tptm, CommonClass.getRoundOff(model.getTarrifpenalty()));
        setEditText(et_tefs, CommonClass.getRoundOff(model.getExcessfca()));

  /*   -- commenting code is for forwarding---
     if (!TextUtils.isEmpty(model.getStatus()) && model.getStatus().equalsIgnoreCase("C")) {
            et_total_mdi.setEnabled(false);
            et_total_pf.setEnabled(false);
            et_tdps.setEnabled(false);
            et_tptm.setEnabled(false);
            et_tefs.setEnabled(false);
        }*/
        return view;
    }


    private void setEditText(EditText editText, String value) {
        if (value != null) {
            editText.setText(value);
        } else {
            editText.setText("");
        }
        editText.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(2)});
    }

    @Override
    public void onClick(View v) {
        try {
            CommonClass.hideKeyBoard(v, getActivity());
            if (loginUser.getCategory().equals(Constants.CATEGORY_TRANSACTION)) {
                saveConsumption.setExcessmd(et_total_mdi.getText().toString());
                saveConsumption.setLowPF(et_total_pf.getText().toString());
                saveConsumption.setDelayedpayment(et_tdps.getText().toString());
                saveConsumption.setExcessconsuption(et_tptm.getText().toString());
            } else {
                saveConsumption.setMdiPenalty(et_total_mdi.getText().toString());
                saveConsumption.setPfPenalty(et_total_pf.getText().toString());
                saveConsumption.setLtPenalty(et_tdps.getText().toString());
                saveConsumption.setTarrfipenalty(et_tptm.getText().toString());
            }
            saveConsumption.setExcessfca(et_tefs.getText().toString());


            if (SubStationFragment.viewPager != null) {
                SubStationFragment.viewPager.setCurrentItem(2);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
