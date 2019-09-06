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
import com.cris.cmsm.navcontrollers.CrewDetailsController;
import com.cris.cmsm.prefrences.UserLoginPreferences;
import com.cris.cmsm.util.CommonClass;
import com.cris.cmsm.util.Constants;

import java.util.ArrayList;
import java.util.List;




public class LiCrewDetailsFragment extends Fragment implements RecyclerItemListener {
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
        recyclerView.setAdapter(new MenuAdapter(getActivity(), LiCrewDetailsFragment.this, getMenuList()));
        recyclerView.setNestedScrollingEnabled(false);
        return view;
    }


    private List<MenuModel> getMenuList() {
        List<MenuModel> list = new ArrayList<>();
        Resources res = getResources();

        list.add(new MenuModel(R.color.colorCardOrange, R.drawable.crew_position, res.getString(R.string.biodata)));
        list.add(new MenuModel(R.color.colorCardFive, R.drawable.crew_strength, res.getString(R.string.training_details)));
        list.add(new MenuModel(R.color.colorCardFive, R.drawable.consumption_analytics, res.getString(R.string.loco_competency)));
        list.add(new MenuModel(R.color.colorCardOrange, R.drawable.availability, res.getString(R.string.road_learnings)));
        list.add(new MenuModel(R.color.colorCardOrange, R.drawable.availability, res.getString(R.string.crew_mileage)));
        list.add(new MenuModel(R.color.colorTeal, R.drawable.feedback, res.getString(R.string.feedback)));

        return list;
    }

    @Override
    public void onItemClickListener(MenuModel model) {
        Resources res = getResources();


        if (model.getMenuTitle().equals(res.getString(R.string.biodata))) {
            DataHolder.setType(Constants.CREW_BIODATA);
            CommonClass.goToNextScreen(getActivity(), CrewDetailsController.class, true, false);
        }else if (model.getMenuTitle().equals(res.getString(R.string.training_details))) {
            DataHolder.setType(Constants.TRAINING_DETAILS);
            CommonClass.goToNextScreen(getActivity(), CrewDetailsController.class, true, false);
        }else if (model.getMenuTitle().equals(res.getString(R.string.loco_competency))) {
            DataHolder.setType(Constants.LOCO_COMPETENCY_DETAILS);
            CommonClass.goToNextScreen(getActivity(), CrewDetailsController.class, true, false);
        }else if (model.getMenuTitle().equals(res.getString(R.string.road_learnings))) {
            DataHolder.setType(Constants.ROAD_LEARNING_DETAILS);
            CommonClass.goToNextScreen(getActivity(), CrewDetailsController.class, true, false);
        }else if (model.getMenuTitle().equals(res.getString(R.string.crew_mileage))) {
            DataHolder.setType(Constants.MILEAGE_DETAILS);
            CommonClass.goToNextScreen(getActivity(), CrewDetailsController.class, true, false);
        }


    }
}

