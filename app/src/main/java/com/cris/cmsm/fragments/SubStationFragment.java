package com.cris.cmsm.fragments;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerTitleStrip;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cris.cmsm.R;
import com.cris.cmsm.tabviews.ConsumptionView;
import com.cris.cmsm.tabviews.RebateView;
import com.cris.cmsm.tabviews.SurchargeView;
import com.cris.cmsm.util.FontFamily;
import com.cris.cmsm.widget.NonSwipeViewPager;

import java.util.ArrayList;

public class SubStationFragment extends Fragment implements View.OnClickListener {
    public static NonSwipeViewPager viewPager;
    PagerTitleStrip pagerTabStrip;
    CreatePageAdapter adapter;
    ArrayList<String> tab_list;
    Typeface font;
    private ImageView iv_title_icon;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.base_tab_view, container, false);
        iv_title_icon = getActivity().findViewById(R.id.iv_title_icon);
        font = new FontFamily(getActivity()).getBold();
        viewPager = view.findViewById(R.id.pager);
        pagerTabStrip = view.findViewById(R.id.tabs);

        tab_list = new ArrayList<String>();
        tab_list.add("Consumption");
        tab_list.add("Surcharge/Penalties");
        tab_list.add("Rebate");
        adapter = new CreatePageAdapter(getChildFragmentManager());
        viewPager.setAdapter(adapter);
        viewPager.setPagingEnabled(false);
        iv_title_icon.setOnClickListener(this);
        viewPager.setOffscreenPageLimit(2);
        for (int i = 0; i < pagerTabStrip.getChildCount(); i++) {
            if (pagerTabStrip.getChildAt(i) instanceof TextView) {
                ((TextView) pagerTabStrip.getChildAt(i)).setTypeface(font);
            }
        }

        return view;
    }

    @Override
    public void onClick(View v) {
        if (viewPager != null) {
            switch (viewPager.getCurrentItem()) {
                case 2:
                    viewPager.setCurrentItem(1);
                    break;
                case 1:
                    viewPager.setCurrentItem(0);
                    break;
                case 0:
                    getActivity().onBackPressed();
                    break;
            }
        }
    }

    /*****************
     * Pager Adapter
     ********************/
    private class CreatePageAdapter extends FragmentStatePagerAdapter {

        public CreatePageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            System.out.println("" + position);
            switch (position) {
                case 0:
                    return new ConsumptionView();
                case 1:
                    return new SurchargeView();
                default:
                    return new RebateView();
            }
        }


        @Override
        public int getCount() {
            return tab_list.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tab_list.get(position);
        }

        @Override
        public void notifyDataSetChanged() {
            super.notifyDataSetChanged();
        }

    }

}
