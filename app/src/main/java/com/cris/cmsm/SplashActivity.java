package com.cris.cmsm;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.cris.cmsm.prefrences.UserLoginPreferences;
import com.cris.cmsm.util.CommonClass;
import com.cris.cmsm.util.FontFamily;

/**
 * Created by Anand.kumar on 10/24/2016.
 */
public class SplashActivity extends AppCompatActivity {

    private Handler handler = new Handler();
    private long DELAY_TIME = 3000;
    TextView tv_title;
    FontFamily fontFamily;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UserLoginPreferences userLoginPreferences = new UserLoginPreferences(SplashActivity.this);
        if (userLoginPreferences.isLogin()) {
            CommonClass.goToNextScreen(SplashActivity.this, HomeActivity.class, false, true);
        } else {
            setContentView(R.layout.activity_splash);
            fontFamily = new FontFamily(SplashActivity.this);
            tv_title = findViewById(R.id.tv_title);
            tv_title.setTypeface(fontFamily.getBold());
            handler.postDelayed(runnable, DELAY_TIME);
        }
    }

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            CommonClass.goToNextScreen(SplashActivity.this, LandingActivity.class, true, true);
        }
    };

    @Override
    public void onBackPressed() {
        handler.removeCallbacks(runnable);
        super.onBackPressed();
    }
}
