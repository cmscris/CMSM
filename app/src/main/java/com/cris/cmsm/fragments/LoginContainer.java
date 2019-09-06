package com.cris.cmsm.fragments;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cris.cmsm.LoginActivity;
import com.cris.cmsm.R;
import com.cris.cmsm.adapter.MenuAdapter;
import com.cris.cmsm.database.DataHolder;
import com.cris.cmsm.interfaces.RecyclerItemListener;
import com.cris.cmsm.models.MenuModel;
import com.cris.cmsm.models.response.LoginIfoVO;
import com.cris.cmsm.navcontrollers.EnergyConsumptionController;
import com.cris.cmsm.prefrences.UserLoginPreferences;
import com.cris.cmsm.util.CommonClass;
import com.cris.cmsm.util.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cms on 3/15/18.
 */


public class LoginContainer extends Fragment implements RecyclerItemListener {
    private RecyclerView recyclerView;
    LoginIfoVO loginUser;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mis_container, container, false);
        loginUser = new UserLoginPreferences(getActivity()).getLoginUser();
        recyclerView = view.findViewById(R.id.recyclerView);
        GridLayoutManager manager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(new MenuAdapter(getActivity(), LoginContainer.this, getMenuList()));
        recyclerView.setNestedScrollingEnabled(false);
        return view;
    }


    private List<MenuModel> getMenuList() {
        List<MenuModel> list = new ArrayList<>();
        Resources resources = getResources();

        list.add(new MenuModel(R.color.colorGrayBackground, R.drawable.blank, resources.getString(R.string.blank)));
        list.add(new MenuModel(R.color.colorGrayBackground, R.drawable.blank, resources.getString(R.string.blank)));
        list.add(new MenuModel(R.color.colorCardOrange, R.drawable.railboarduser, resources.getString(R.string.ir_login)));
        list.add(new MenuModel(R.color.colorCardGreen, R.drawable.crewusericon, resources.getString(R.string.crew_login)));
        list.add(new MenuModel(R.color.colorCyan, R.drawable.railboarduser, resources.getString(R.string.Departmental_Abnormality_User)));

        return list;
    }

    @Override
    public void onItemClickListener(MenuModel model) {
        Resources resources = getResources();
        if (model.getMenuTitle().equals(resources.getString(R.string.ir_login))) {
            DataHolder.setLogin_type("IR");
        } else if (model.getMenuTitle().equals(resources.getString(R.string.crew_login))) {
            DataHolder.setLogin_type("CREW");
        }
        if(model.getMenuTitle().equals("Departmental Abnormality User")){
            DataHolder.setLogin_type("ABNORMALITY USER");
        }

        CommonClass.goToNextScreen(getActivity(), LoginActivity.class, true, Constants.LOGIN);

    }

}
