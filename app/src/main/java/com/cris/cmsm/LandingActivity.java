package com.cris.cmsm;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import com.cris.cmsm.adapter.MenuAdapter;
import com.cris.cmsm.interfaces.RecyclerItemListener;
import com.cris.cmsm.models.MenuModel;
import com.cris.cmsm.navcontrollers.DetailController;
import com.cris.cmsm.util.CommonClass;
import com.cris.cmsm.util.Constants;
import com.cris.cmsm.util.FontFamily;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anand.kumar on 10/24/2016.
 */
public class LandingActivity extends AppCompatActivity implements RecyclerItemListener {

    private RecyclerView recyclerView;
    private ScrollView scrollView;
    TextView tv_title, tv_copyright;
    FontFamily fontFamily;
    Resources res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);
        fontFamily = new FontFamily(LandingActivity.this);
        res = getResources();
        tv_title = findViewById(R.id.tv_title);
        tv_copyright = findViewById(R.id.tv_copyright);
        scrollView = findViewById(R.id.scrollView);
        recyclerView = findViewById(R.id.recyclerView);
        GridLayoutManager manager = new GridLayoutManager(LandingActivity.this, 2);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(new MenuAdapter(LandingActivity.this, LandingActivity.this, getMenuList()));
        recyclerView.setNestedScrollingEnabled(false);

        scrollView
                .setDescendantFocusability(ViewGroup.FOCUS_BEFORE_DESCENDANTS);
        scrollView.setFocusable(true);
        scrollView.setFocusableInTouchMode(true);
        scrollView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                v.requestFocusFromTouch();
                return false;
            }
        });

        tv_title.setTypeface(fontFamily.getBold());
        tv_copyright.setTypeface(fontFamily.getRegular());
    }

    private List<MenuModel> getMenuList() {
        List<MenuModel> list = new ArrayList<>();
        list.add(new MenuModel(R.color.colorCardRed, R.drawable.cms_white, res.getString(R.string.cms_login)));
        list.add(new MenuModel(R.color.colorCardMagenta, R.drawable.irgreenri_white, res.getString(R.string.ir_greenri)));
        list.add(new MenuModel(R.color.colorCardGreen, R.drawable.icon_websites, res.getString(R.string.useful_links)));
        list.add(new MenuModel(R.color.colorCardOrange, R.drawable.contact_us, res.getString(R.string.contact_us)));
        return list;
    }


    @Override
    public void onItemClickListener(MenuModel model) {
        if (model.getMenuTitle().equals(res.getString(R.string.cms_login))) {
            CommonClass.goToNextScreen(LandingActivity.this, DetailController.class, true, Constants.LOGIN_OPTIONS);
            //CommonClass.goToNextScreen(LandingActivity.this, LoginActivity.class, true, true);
        } else if (model.getMenuTitle().equals(res.getString(R.string.ir_greenri))) {
            CommonClass.goToNextScreen(LandingActivity.this, WebsitesLink.class, true, res.getString(R.string.ir_greenri), Constants.IRGREENRI);
        } else if (model.getMenuTitle().equals(res.getString(R.string.useful_links))) {
            CommonClass.goToNextScreen(LandingActivity.this, DetailController.class, true, Constants.USERFUL_LINKS);
        } else if (model.getMenuTitle().equals(res.getString(R.string.contact_us))) {
            CommonClass.goToNextScreen(LandingActivity.this, DetailController.class, true, Constants.CONTACT_US);
        }
    }
}
