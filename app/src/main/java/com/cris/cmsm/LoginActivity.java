package com.cris.cmsm;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.InputFilter;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import com.cris.cmsm.database.DataHolder;
import com.cris.cmsm.models.request.LoginRequest;
import com.cris.cmsm.models.response.LoginResponse;
import com.cris.cmsm.prefrences.UserLoginPreferences;
import com.cris.cmsm.presenter.RequestPresenter;
import com.cris.cmsm.presenterview.ResponseView;
import com.cris.cmsm.util.CommonClass;
import com.cris.cmsm.util.Constants;
import com.cris.cmsm.util.FontFamily;

import java.util.ArrayList;

/**
 * Created by Anand.kumar on 10/24/2016.
 */
public class LoginActivity extends AppCompatActivity implements ResponseView, View.OnClickListener {
    private Button btn_login,btn_reset;

    private TextView tv_title, tv_copyright, tv_user_type;
    private TextInputLayout til_user, til_passowrd;
    private EditText et_user, et_password;
    private FontFamily fontFamily;
    private RequestPresenter requestPresenter;
    private CommonClass commonClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        fontFamily = new FontFamily(LoginActivity.this);
        commonClass = new CommonClass(LoginActivity.this);
        requestPresenter = new RequestPresenter(LoginActivity.this);


        til_user = findViewById(R.id.til_user);
        til_passowrd = findViewById(R.id.til_passowrd);


        et_user = findViewById(R.id.et_user);
        et_password = findViewById(R.id.et_password);
        btn_reset=findViewById(R.id.btn_reset);




        tv_user_type = findViewById(R.id.tv_user_type);
        tv_title = findViewById(R.id.tv_title);
        tv_copyright = findViewById(R.id.tv_copyright);
        btn_login = findViewById(R.id.btn_login);
        btn_login.setOnClickListener(this);


        tv_user_type.setTypeface(fontFamily.getBold());
        tv_title.setTypeface(fontFamily.getBold());
        til_user.setTypeface(fontFamily.getRegular());
        til_passowrd.setTypeface(fontFamily.getRegular());
        et_user.setTypeface(fontFamily.getRegular());
        et_password.setTypeface(fontFamily.getRegular());
        btn_login.setTypeface(fontFamily.getBold());
        tv_copyright.setTypeface(fontFamily.getRegular());
btn_reset.setVisibility(View.GONE);
        //commonClass.showToast("Login Type : " + DataHolder.getLogin_type());


        //commonClass.showToast("Login Type : " + DataHolder.getLogin_type());
        tv_user_type.setText(DataHolder.getLogin_type() + " Login");



        if (!DataHolder.getLogin_type().equals("IR") && !DataHolder.getLogin_type().equals("ABNORMALITY USER") ) {
            et_user.setFilters(new InputFilter[]{new InputFilter.AllCaps()});
        }
        if(DataHolder.getLogin_type().equals("ABNORMALITY USER")){
            btn_reset.setVisibility(View.VISIBLE);
            btn_reset.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CommonClass.goToNextScreen( LoginActivity.this,Password_reset_activity.class, true, Constants.RESET);

                    //Intent i=new Intent(LoginActivity.this,Password_reset_activity.class);
                    //i.putExtra("object",action_bar_title.getText().toString());
                    //startActivity(i);
                }
            });
        }
    }



    @Override
    public void onClick(View v) {
        if (isValid()) {
            CommonClass.hideKeyBoard(v, LoginActivity.this);
            callLoginWebService();
        }
    }


    private void callLoginWebService() {
        LoginRequest request = new LoginRequest();
        request.setUser(et_user.getText().toString());
        request.setPassword(et_password.getText().toString());
        request.setLogintype(DataHolder.getLogin_type());
        if (DataHolder.getLogin_type().equals("ABNORMALITY USER")) {
            DataHolder.setPassword(et_password.getText().toString());
            DataHolder.setUserid(et_user.getText().toString());

            requestPresenter.Request(request, "Please wait", Constants.Loginot);


            System.out.println("Logging in progress");
        }
        else requestPresenter.Request(request, "Please wait", Constants.LOGIN);
    }




    private boolean isValid() {
        if (et_user.getText().toString().trim().isEmpty()) {
            commonClass.showToast("Please enter Login Id.");
            return false;
        } else if (et_password.getText().toString().isEmpty()) {
            commonClass.showToast("Please enter Password.");
            return false;
        } else if (!CommonClass.checkInternetConnection(LoginActivity.this)) {
            commonClass.showToast("No internet available.");
            return false;
        } else
            return true;
    }


    @Override
    public void ResponseOk(Object object, int position) {
        try {
            if (object instanceof LoginResponse) {
                LoginResponse loginResponse = (LoginResponse) object;
                if (loginResponse.getIsSuccess()) {


                    UserLoginPreferences userLoginPreferences = new UserLoginPreferences(LoginActivity.this);
                    userLoginPreferences.setLogin(loginResponse.getIsSuccess());
                    userLoginPreferences.setLoginUser(loginResponse.getLoginIfoVO());
                    DataHolder.setRlycode(loginResponse.getLoginIfoVO().getRlycode());
                    DataHolder.setRoleid(loginResponse.getLoginIfoVO().getRoleid());
                    DataHolder.setFname(loginResponse.getLoginIfoVO().getFname());

                    //System.out.println(" >>>>LoginId" + loginResponse.getLoginIfoVO().getCrewid());
                    DataHolder.setLoginid(loginResponse.getLoginIfoVO().getLoginid());
                   // System.out.println(" >>>>CrewId" + loginResponse.getLoginIfoVO().getCrewid());
                    System.out.println(" >>>>designation" + loginResponse.getLoginIfoVO().getDesignation());
                    DataHolder.setDesignation(loginResponse.getLoginIfoVO().getDesignation());
                    if (DataHolder.getLogin_type().equals("ABNORMALITY USER")) {
                        System.out.println("Logging sucess>>>>>>>>>>" + loginResponse.getLoginIfoVO().getRlycode());
                        System.out.println("Logging sucess>>>>>>>>>>" + loginResponse.getLoginIfoVO().getRoleid());

                        String Rlycode = loginResponse.getLoginIfoVO().getRlycode();
                        String Roleid = loginResponse.getLoginIfoVO().getRoleid();
                        String username=loginResponse.getLoginIfoVO().getFname();
                        final ArrayList <String> ablist = new ArrayList <>();
                        ablist.add(Rlycode);
                        ablist.add(Roleid);
                        ablist.add(username);

                       Intent i = new Intent(LoginActivity.this, Before_abnoresponselist_activity.class);
                        i.putExtra("object", ablist);
                        startActivity(i);
                       /*Intent i = new Intent(LoginActivity.this,abnoresponselist.class);
                        i.putExtra("object", ablist);
                        startActivity(i);
                       /*Intent i=new Intent(LoginActivity.this,Remarks.class);
                       startActivity(i);*/
                    }

                    else{
                        CommonClass.goToHome(LoginActivity.this,HomeActivity.class, true);


                } }
                if(loginResponse.getIsSuccess().booleanValue()==false){
                    retryDialog(LoginActivity.this, "Loginid and Password not exist. Do you want to retry?");

                }
                else
                {
                    retryDialog(LoginActivity.this, "Unable to Login. Do you want to retry?");
                } }
    } catch (Exception e) {
            retryDialog(LoginActivity.this, "Unable to Login. Do you want to retry?");
            e.printStackTrace();
        }

    }

    @Override
    public void Error() {
        retryDialog(LoginActivity.this, "Unable to Login. Do you want to retry?");
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
    public void onBackPressed() {
        Intent landing = new Intent(LoginActivity.this, LandingActivity.class);
        startActivity(landing);
        overridePendingTransition(R.animator.slide_in_right, R.animator.slide_out_left);
        finish();
    }

    private void retryDialog(final Activity activity, String msg) {
        new AlertDialog.Builder(activity)
                .setTitle(getResources().getString(R.string.cms))
                .setMessage(msg)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        callLoginWebService();
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        finish();
                    }
                })
                .setIcon(R.drawable.icon_logo)
                .show();
    }

}
