package com.cris.cmsm;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import com.cris.cmsm.fragments.TenPartOneFragment;
import com.cris.cmsm.fragments.TenPartTwoFragment;

/**
 *
 */

public class AnnexureTen extends BaseActivity {
    private ViewPager viewPager;
    private CreatePageAdapter pageAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.annexure_ten);
        viewPager = findViewById(R.id.viewPager);
        pageAdapter = new CreatePageAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pageAdapter);
        viewPager.setOffscreenPageLimit(1);
        viewPager.setPageMargin(5);
        viewPager.setPageMarginDrawable(R.color.colorPrimary);
    }


    private class CreatePageAdapter extends FragmentStatePagerAdapter {

        public CreatePageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new TenPartOneFragment();
                default:
                    return new TenPartTwoFragment();
            }
        }


        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public void notifyDataSetChanged() {
            super.notifyDataSetChanged();
        }

    }
}
