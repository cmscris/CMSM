package com.cris.cmsm.fragments;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cris.cmsm.R;
import com.cris.cmsm.adapter.MenuAdapter;
import com.cris.cmsm.database.DataHolder;
import com.cris.cmsm.interfaces.RecyclerItemListener;
import com.cris.cmsm.models.MenuModel;
import com.cris.cmsm.models.response.LoginIfoVO;
import com.cris.cmsm.navcontrollers.CrewUtilizationController;
import com.cris.cmsm.navcontrollers.EnergyConsumptionController;
import com.cris.cmsm.prefrences.UserLoginPreferences;
import com.cris.cmsm.util.CommonClass;
import com.cris.cmsm.util.Constants;

import java.util.ArrayList;
import java.util.List;



public class CrewUtilizationContainer extends Fragment implements RecyclerItemListener {
    private RecyclerView recyclerView;
    private LoginIfoVO loginUser;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mis_container, container, false);
        loginUser = new UserLoginPreferences(getActivity()).getLoginUser();
        recyclerView = view.findViewById(R.id.recyclerView);
        GridLayoutManager manager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(new MenuAdapter(getActivity(), CrewUtilizationContainer.this, getMenuList()));
        recyclerView.setNestedScrollingEnabled(false);
        return view;
    }


    private List<MenuModel> getMenuList() {
        List<MenuModel> list = new ArrayList<>();
        Resources resources = getResources();
        //if (loginUser.getCategory().equals(Constants.CATEGORY_BOTH) || loginUser.getCategory().equals(Constants.CATEGORY_TRANSACTION) || loginUser.getAuthlevel().equals(Constants.AUTH_BOARD))
        list.add(new MenuModel(R.color.colorTraction, R.drawable.traction, resources.getString(R.string.monthly_utilization)));

        //if (loginUser.getCategory().equals(Constants.CATEGORY_BOTH) || loginUser.getCategory().equals(Constants.CATEGORY_GENERAL_SERVICE) || loginUser.getCategory().equals(Constants.CATEGORY_WORKSHOP) || loginUser.getAuthlevel().equals(Constants.AUTH_BOARD))
        list.add(new MenuModel(R.color.colorNonTraction, R.drawable.non_traction, resources.getString(R.string.fortnightly_utilization)));

        return list;
    }

    @Override
    public void onItemClickListener(MenuModel model) {
        Resources resources = getResources();
        if (model.getMenuTitle().equals(resources.getString(R.string.monthly_utilization))) {
            CommonClass.goToNextScreen(getActivity(), CrewUtilizationController.class, true, Constants.CREW_UTILIZATION);
            DataHolder.setType(0);
        } else if (model.getMenuTitle().equals(resources.getString(R.string.fortnightly_utilization))) {
            DataHolder.setType(1);
            CommonClass.goToNextScreen(getActivity(), CrewUtilizationController.class, true, Constants.CREW_UTILIZATION_FORTNIGHT);
        }
    }
}
