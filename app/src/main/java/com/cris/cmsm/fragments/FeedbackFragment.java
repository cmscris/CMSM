package com.cris.cmsm.fragments;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatSpinner;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.cris.cmsm.R;
import com.cris.cmsm.adapter.SpinnerAdapter;
import com.cris.cmsm.models.request.FeedbackRequest;
import com.cris.cmsm.models.response.FeedbackResponse;
import com.cris.cmsm.prefrences.UserLoginPreferences;
import com.cris.cmsm.presenter.RequestPresenter;
import com.cris.cmsm.presenterview.ResponseView;
import com.cris.cmsm.util.CommonClass;
import com.cris.cmsm.util.Constants;
import com.cris.cmsm.util.FontFamily;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anand.kumar on 10/25/2016.
 */
public class FeedbackFragment extends Fragment implements View.OnClickListener, ResponseView {

    private TextInputLayout til_username, til_remarks;
    private EditText et_username, et_remarks;
    private Button btn_submit;
    private AppCompatSpinner spn_feedback;
    private Typeface font, fontBold;
    private UserLoginPreferences userLoginPreferences;
    private CommonClass commonClass;
    private RequestPresenter requestPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.feedback_view, container, false);
        commonClass = new CommonClass(getActivity());
        requestPresenter = new RequestPresenter(FeedbackFragment.this);
        FontFamily fontFamily = new FontFamily(getActivity());
        font = fontFamily.getRegular();
        fontBold = fontFamily.getBold();
        userLoginPreferences = new UserLoginPreferences(getActivity());
        spn_feedback = view.findViewById(R.id.spn_feedback);
        til_username = view.findViewById(R.id.til_username);
        til_remarks = view.findViewById(R.id.til_remarks);
        et_username = view.findViewById(R.id.et_username);
        et_remarks = view.findViewById(R.id.et_remarks);
        btn_submit = view.findViewById(R.id.btn_submit);

        til_username.setTypeface(font);
        til_remarks.setTypeface(font);
        et_username.setTypeface(font);
        et_remarks.setTypeface(font);
        btn_submit.setTypeface(fontBold);
        spn_feedback.setAdapter(new SpinnerAdapter(getActivity(), feedbackList()));
        btn_submit.setOnClickListener(this);

        et_username.setText(userLoginPreferences.getLoginUser().getFname());
        et_username.setFocusable(false);
        et_username.setFocusableInTouchMode(false);
        return view;
    }

    private List<String> feedbackList() {
        Resources res = getResources();
        List<String> list = new ArrayList<>();
        list.add("Select Menu For Feedback");
        list.add(res.getString(R.string.consumption_entry));
        list.add(res.getString(R.string.mis_report));
        list.add(res.getString(R.string.spn_station_consumption));
        list.add(res.getString(R.string.monthly_consumption));
        list.add(res.getString(R.string.consumption_analytics));
        list.add(res.getString(R.string.sub_station_cons));
        list.add(res.getString(R.string.sub_station_assets));
        list.add(res.getString(R.string.assets_report));
        list.add(res.getString(R.string.photo_gallery));
        list.add(res.getString(R.string.feedback));
        return list;
    }

    @Override
    public void onClick(View v) {
        if (isValid()) {
            CommonClass.hideKeyBoard(v, getActivity());
            callWebservice();
        }
    }


    private void callWebservice() {
        FeedbackRequest feedbackRequest = new FeedbackRequest();
        feedbackRequest.setLoginId(userLoginPreferences.getLoginUser().getLoginid());
        feedbackRequest.setForm((String) spn_feedback.getSelectedItem());
        feedbackRequest.setFeedback(et_remarks.getText().toString().trim());
        requestPresenter.Request(feedbackRequest, "Saving your feedback...", Constants.FEEDBACK_SERVICE);
    }


    private boolean isValid() {
        if (spn_feedback.getSelectedItemPosition() == 0) {
            commonClass.showToast("Please select menu for feedback.");
            return false;
        } else if (et_remarks.getText().toString().trim().isEmpty()) {
            commonClass.showToast("Please enter your remarks.");
            return false;
        } else if (!CommonClass.checkInternetConnection(getActivity())) {
            commonClass.showToast("No internet available.");
            return false;
        } else
            return true;
    }

    @Override
    public void ResponseOk(Object object, int position) {
        try {
            if (object instanceof FeedbackResponse) {
                FeedbackResponse response = (FeedbackResponse) object;
                if (response.getFlag()) {
                    successDialog(getActivity(), "Your Feedback has been submitted successfully.");

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            retryDialog(getActivity(), "Unable to save your feedback. Please try again. Do you can to retry");
        }
    }

    @Override
    public void Error() {
        retryDialog(getActivity(), "Unable to save your feedback. Please try again. Do you can to retry");
    }

    @Override
    public void dismissProgress() {
        commonClass.dismissDialog();
    }

    @Override
    public void showProgress(String msg) {
        commonClass.showProgressBar(msg);
    }


    private void retryDialog(final Activity activity, String msg) {
        new AlertDialog.Builder(activity).setCancelable(false)
                .setTitle(getResources().getString(R.string.cms))
                .setMessage(msg)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        callWebservice();
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
}
