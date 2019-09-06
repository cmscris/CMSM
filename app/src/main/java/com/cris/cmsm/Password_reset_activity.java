package com.cris.cmsm;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.cris.cmsm.database.DataHolder;
import com.cris.cmsm.models.request.GraphAPIRequest;
import com.cris.cmsm.models.response.Paramresponse;
import com.cris.cmsm.models.response.Passwordresponse;
import com.cris.cmsm.navcontrollers.DetailController;
import com.cris.cmsm.presenter.RequestPresenter;
import com.cris.cmsm.presenterview.ResponseView;
import com.cris.cmsm.util.CommonClass;
import com.cris.cmsm.util.Constants;
import com.cris.cmsm.util.FontFamily;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.Response;

public class Password_reset_activity extends AppCompatActivity implements ResponseView {
    private TextInputLayout tl_loginid, tl_current, tl_new, tl_confirm;
    TextView action_bar_title;

    ImageView iv_left, iv_middle, iv_right, iv_railway, iv_title_icon;
    EditText et_current_password, et_new_password, et_confirm_password, et_login;
    Button Save_button;
    private FontFamily fontFamily;
    //SeekBar pb_current,pb_new,pb_confirm;
    String specialChars = "~`!@#$%^&*()-_=+\\|[{]};:'\",<.>/?";

    public CommonClass commonClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_reset_activity);
        tl_loginid = (TextInputLayout) findViewById(R.id.userid);
        tl_current = (TextInputLayout) findViewById(R.id.tl_current_password);
        et_login = (EditText) findViewById(R.id.et_userid);
        tl_new = (TextInputLayout) findViewById(R.id.tl_new_password);
        tl_confirm = (TextInputLayout) findViewById(R.id.tl_confirm_password);
        action_bar_title = (TextView) findViewById(R.id.action_bar_title);
        et_current_password = (EditText) findViewById(R.id.current_password);
        et_new_password = (EditText) findViewById(R.id.new_password);
        et_confirm_password = (EditText) findViewById(R.id.confirm_password);
        iv_middle = (ImageView) findViewById(R.id.iv_middle);
        iv_right = (ImageView) findViewById(R.id.iv_right);
        iv_left = (ImageView) findViewById(R.id.iv_left);
        iv_railway = (ImageView) findViewById(R.id.iv_railway2);
        Save_button = (Button) findViewById(R.id.save_password);
        fontFamily= new FontFamily(Password_reset_activity.this);
        //pb_new=(SeekBar)findViewById(R.id.pb_new);
        //pb_confirm =(SeekBar)findViewById(R.id.pb_confirm);
        //pb_current =(SeekBar)findViewById(R.id.pb_current);
        tl_loginid.setTypeface(fontFamily.getBold());
        tl_current.setTypeface(fontFamily.getBold());
        tl_new.setTypeface(fontFamily.getBold());
        tl_confirm.setTypeface(fontFamily.getBold());
        iv_right.setVisibility(View.VISIBLE);
        iv_title_icon = findViewById(R.id.iv_title_icon);
        iv_title_icon.setImageResource(R.drawable.iv_back);
        RequestPresenter requestPresenter = new RequestPresenter(this);
        GraphAPIRequest request = new GraphAPIRequest();


        Save_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList <String> password = new ArrayList <>();
                if ((et_confirm_password.getText().toString().equalsIgnoreCase("")) | et_current_password.getText().toString().equalsIgnoreCase("") | et_new_password.getText().toString().equalsIgnoreCase("")) {
                    Toast.makeText(getBaseContext(), "Please Complete the Information", Toast.LENGTH_SHORT).show();
                } else if (!et_new_password.getText().toString().equalsIgnoreCase(et_confirm_password.getText().toString())) {
                    Toast.makeText(getBaseContext(), "The Confirm Password Don't Match !", Toast.LENGTH_SHORT).show();
                }

                if (!CommonClass.checkInternetConnection(Password_reset_activity.this)) {
                    commonClass.showToast("No internet available.");
                }
                if ((et_new_password.getText().toString().length() <= 12) && (et_new_password.getText().toString().length() >= 5)) {
                    Password_Validation(et_new_password.getText().toString());


                    if (!Password_Validation(et_new_password.getText().toString())) {
                        System.out.println("Not Valid");
                        //pb_current.setProgress(0);
                        Toast.makeText(getBaseContext(), "Please enter Password containing 1 special character,1 capital letter,1 small letter,1 numeric digit.", Toast.LENGTH_SHORT).show();

                    } else {
                        System.out.println("Valid");
                        if (et_new_password.getText().toString().equals(et_confirm_password.getText().toString()) && Password_Validation(et_new_password.getText().toString()) == true) {
                            //pb_confirm.setProgress(100,true);
                            password.add(et_login.getText().toString());
                            password.add(et_current_password.getText().toString());
                            password.add(et_confirm_password.getText().toString());
                            request.setparamlist(password);
                            System.out.println("Requestpassword>>>>>>>>>>>>" + request);
                            System.out.println("Requestpassword>>>>>>>>>>>>" + password);
                            requestPresenter.Request(request, "c", Constants.CHANGEPASSWORD);
                            System.out.println("Requestpassword>>>>>>>>>>>>" + password);

                        }
                    }
                } else {
                    Toast.makeText(getBaseContext(), "Please enter minimum character of Password containing 1 special character,1 capital letter,1 small letter,1 numeric digit.", Toast.LENGTH_LONG).show();
                    System.out.println("Please enter minimum character of Password ");
                }
            }

        });

        iv_title_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }
        });


        // Bundle extra = getIntent().getExtras();
        //if (extra != null) {

        // String username = extra.getString("object");
        action_bar_title.setText("Back");
        //}
        iv_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.iv_right:
                        showLogoutDialog(Password_reset_activity.this, "Do you want to logout?", true);
                        break;
                }
            }
        });

        iv_left.setVisibility(View.GONE);
    }


    private void showLogoutDialog(final Activity context, String msg, final boolean isLogout) {
        new AlertDialog.Builder(context).setCancelable(isLogout)
                .setTitle(getResources().getString(R.string.cms))
                .setMessage(msg)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        if (isLogout) {
                            logOut();
                        }
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        if (isLogout) {
                            logOut();
                        } else {
                            context.finish();
                        }
                    }
                })
                .setIcon(R.drawable.icon_logo)
                .show();
    }

    private void logOut() {
        //userLoginPreferences.clearUser();
        DataHolder.setLevel(0);
        CommonClass.goToNextScreen(Password_reset_activity.this, DetailController.class, true, Constants.LOGIN_OPTIONS);


        Intent landing = new Intent(Password_reset_activity.this, LandingActivity.class);
        landing.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                | Intent.FLAG_ACTIVITY_CLEAR_TASK
                | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(landing);
        overridePendingTransition(R.animator.slide_in_right, R.animator.slide_out_left);
        finish();
    }


    @Override
    public void ResponseOk(Object object, int position) {
        if (object instanceof Passwordresponse) {
            Passwordresponse passwordresponse = (Passwordresponse) object;
            if (passwordresponse.getVosList().get(0).equals(1)) {
                System.out.println(" Password Save");
                Toast.makeText(getBaseContext(), "Password is Changed and Saved", Toast.LENGTH_SHORT).show();
                et_new_password.setText("");
                et_current_password.setText("");
                et_confirm_password.setText("");
                et_login.setText("");
            }
            if (passwordresponse.getVosList().get(0).equals(10)) {
                System.out.println(" Password not Save");
                Toast.makeText(getBaseContext(), "Wrong User", Toast.LENGTH_SHORT).show();
                et_new_password.setText("");
                et_current_password.setText("");
                et_confirm_password.setText("");
                et_login.setText("");
            }
            if (passwordresponse.getVosList().get(0).equals(11)) {
                System.out.println(" Password not Save");
                Toast.makeText(getBaseContext(), "Current Password is Wrong", Toast.LENGTH_SHORT).show();
                et_new_password.setText("");
                et_current_password.setText("");
                et_confirm_password.setText("");
                et_login.setText("");

            }
        }
    }

    public static boolean Password_Validation(String password) {

        if ((password.length()) <= 12) {
            Pattern Uppercaseletter = Pattern.compile("[A-Z]");

            Pattern Lowercaseletter = Pattern.compile("[a-z]");
            Pattern digit = Pattern.compile("[0-9]");
            Pattern special = Pattern.compile("[!@#$%&*()_+=|<>?{}\\[\\]~-]");
            //Pattern eight = Pattern.compile (".{8}");

            Matcher hasUpperLetter = Uppercaseletter.matcher(password);

            Matcher hasLowerLetter = Lowercaseletter.matcher(password);
            Matcher hasDigit = digit.matcher(password);
            Matcher hasSpecial = special.matcher(password);

            return hasUpperLetter.find() && hasLowerLetter.find() && hasDigit.find() && hasSpecial.find();
        } else
            return false;

    }


    @Override
    public void Error() {

    }

    @Override
    public void dismissProgress() {

    }

    @Override
    public void showProgress(String msg) {

    }


}


