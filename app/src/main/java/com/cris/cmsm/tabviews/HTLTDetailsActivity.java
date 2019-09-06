package com.cris.cmsm.tabviews;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import com.cris.cmsm.BaseActivity;
import com.cris.cmsm.R;
import com.cris.cmsm.database.DataHolder;
import com.cris.cmsm.fragments.ChargingFragment;
import com.cris.cmsm.fragments.InputFeederFragment;
import com.cris.cmsm.fragments.OutputFeederFragment;
import com.cris.cmsm.models.request.RequestSSAssets;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */

public class HTLTDetailsActivity extends BaseActivity {

    private TabLayout tabs;
    private ViewPager pager;
    private List<String> tabList;
    private RequestSSAssets reportHeaderView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_htlt_panel);
        reportHeaderView = DataHolder.getInstance().getRequestSSAssets();
        tabs = findViewById(R.id.tabs);
        pager = findViewById(R.id.pager);
        tabList = new ArrayList<>();
        tabList.add("Incoming Feeders");
        tabList.add("Outgoing Feeders");
        if (reportHeaderView != null && reportHeaderView.getAssetsName().equalsIgnoreCase("HT PANEL"))
            tabList.add("Charging System");
        CreatePageAdapter pageAdapter = new CreatePageAdapter(getSupportFragmentManager());
        pager.setAdapter(pageAdapter);
        tabs.setupWithViewPager(pager);
        pager.setOffscreenPageLimit(1);
    }


    private class CreatePageAdapter extends FragmentStatePagerAdapter {

        public CreatePageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new InputFeederFragment();
                case 1:
                    return new OutputFeederFragment();
                default:
                    return new ChargingFragment();
            }
        }


        @Override
        public int getCount() {
            return tabList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabList.get(position);
        }

        @Override
        public void notifyDataSetChanged() {
            super.notifyDataSetChanged();
        }

    }
}
