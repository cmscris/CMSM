package com.cris.cmsm.navcontrollers;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.cris.cmsm.BaseActivity;
import com.cris.cmsm.R;
import com.cris.cmsm.fragments.ContactUsFragment;
import com.cris.cmsm.fragments.CrewUtilizationContainer;
import com.cris.cmsm.fragments.EnergyConsumptionContainer;
import com.cris.cmsm.fragments.FeedbackFragment;
import com.cris.cmsm.fragments.LTConnectionFragment;
import com.cris.cmsm.fragments.LiCrewDetailsFragment;
import com.cris.cmsm.fragments.LoginContainer;
import com.cris.cmsm.fragments.MisReportsContainer;
import com.cris.cmsm.fragments.PhotoGalleryFragment;
import com.cris.cmsm.fragments.SubStationFragment;
import com.cris.cmsm.fragments.WebSitesFragment;
import com.cris.cmsm.util.Constants;
import com.cris.cmsm.util.FontFamily;

/**
 * Created by Anand.kumar on 10/25/2016.
 */
public class DetailController extends BaseActivity implements View.OnClickListener {
    private ImageView iv_title_icon;
    private TextView action_bar_title;
    private Typeface font;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_controller);
        font = new FontFamily(DetailController.this).getBold();
        iv_title_icon = findViewById(R.id.iv_title_icon);
        action_bar_title = findViewById(R.id.action_bar_title);
        action_bar_title.setTypeface(font);
        switch (getIntent().getIntExtra(Constants.KEY, -1)) {


            case Constants.LOGIN_OPTIONS:
                action_bar_title.setText(getResources().getString(R.string.login_options));
                changeFragment(new LoginContainer());
                break;


            case Constants.CREW_UTILIZATION:
                action_bar_title.setText(getResources().getString(R.string.crew_utilization));
                changeFragment(new CrewUtilizationContainer());
                break;

            case Constants.ENERGY_CONSUMPTION:
                action_bar_title.setText(getResources().getString(R.string.energy_consumption));
                changeFragment(new EnergyConsumptionContainer());
                break;



            case Constants.LI_CREW_DETAILS:
                action_bar_title.setText(getResources().getString(R.string.li_crew_details));
                changeFragment(new LiCrewDetailsFragment());
                break;

            case Constants.Substation:
                action_bar_title.setText(getResources().getString(R.string.sub_station));
                changeFragment(new SubStationFragment());
                break;

            case Constants.LTConnection:
                action_bar_title.setText(getResources().getString(R.string.sub_station_lt));
                changeFragment(new LTConnectionFragment());
                break;
            case Constants.USERFUL_LINKS:
                action_bar_title.setText(getResources().getString(R.string.useful_links));
                changeFragment(new WebSitesFragment());
                break;
            case Constants.ANNEXURE_14:
                action_bar_title.setText(getResources().getString(R.string.mis_report));
                changeFragment(new MisReportsContainer());
                break;
            case Constants.PHOTO_GALLERY:
                action_bar_title.setText(getResources().getString(R.string.photo_gallery));
                changeFragment(new PhotoGalleryFragment());
                break;
            case Constants.FEEDBACK:
                action_bar_title.setText(getResources().getString(R.string.feedback));
                changeFragment(new FeedbackFragment());
                break;
            case Constants.CONTACT_US:
                action_bar_title.setText(getResources().getString(R.string.contact_us));
                changeFragment(new ContactUsFragment());
                break;
        }

        iv_title_icon.setImageResource(R.drawable.iv_back);
        iv_title_icon.setOnClickListener(this);
    }


    private void changeFragment(Fragment fragment) {
        String backStateName = fragment.getClass().getName();
        String fragmentTag = backStateName;

        FragmentManager manager = getSupportFragmentManager();
        boolean fragmentPopped = manager
                .popBackStackImmediate(backStateName, 0);

        if (!fragmentPopped && manager.findFragmentByTag(fragmentTag) == null) {
            FragmentTransaction ft = manager.beginTransaction();
            ft.replace(R.id.container, fragment, fragmentTag);
            ft.setTransition(FragmentTransaction.TRANSIT_NONE);
            ft.addToBackStack(backStateName);
            ft.commit();
        }
    }

    @Override
    public void onClick(View v) {
        onBackPressed();
    }


    @Override
    public void onBackPressed() {


        if (SubStationFragment.viewPager != null && SubStationFragment.viewPager.getCurrentItem() > 0) {
            if (SubStationFragment.viewPager.getCurrentItem() == 2) {
                SubStationFragment.viewPager.setCurrentItem(1);
            } else if (SubStationFragment.viewPager.getCurrentItem() == 1) {
                SubStationFragment.viewPager.setCurrentItem(0);
            }
        } else if (getSupportFragmentManager().getBackStackEntryCount() == 1) {
            finish();
            overridePendingTransition(R.animator.slide_in_right, R.animator.slide_out_left);
        } else {
            super.onBackPressed();
        }


    }
}
