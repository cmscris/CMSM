package com.cris.cmsm;

import android.support.v7.app.AppCompatActivity;

/**
 *
 */
public class BaseActivity extends AppCompatActivity {


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.animator.slide_in_right, R.animator.slide_out_left);
    }
}
