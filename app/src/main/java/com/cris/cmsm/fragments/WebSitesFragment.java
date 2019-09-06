package com.cris.cmsm.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

import com.cris.cmsm.R;
import com.cris.cmsm.WebsitesLink;
import com.cris.cmsm.adapter.WebMediaAdapter;
import com.cris.cmsm.models.MenuModel;
import com.cris.cmsm.util.CommonClass;

import java.util.ArrayList;
import java.util.List;

public class WebSitesFragment extends Fragment {
    GridView grid_helpline;
    List<MenuModel> websitesList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.social_main, container, false);
        websitesList = new ArrayList<>();
        grid_helpline = view.findViewById(R.id.grid_helpline);
        grid_helpline.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                MenuModel menuModel = (MenuModel) parent.getItemAtPosition(position);
                CommonClass.goToNextScreen(getActivity(), WebsitesLink.class, true, "", menuModel.getURL());
            }
        });
        WebListLink("http://cris.org.in/", R.drawable.cris_logo);
        WebListLink("http://indianrailways.gov.in/", R.drawable.ir_logo);
        WebListLink("http://www.irieen.indianrailways.gov.in/", R.drawable.ireen);
        WebListLink("http://www.indianrail.gov.in/other_Rly_Sites.html", R.drawable.ir_logo);
        grid_helpline.setAdapter(new WebMediaAdapter(getActivity(),websitesList));
        return view;
    }

    private void WebListLink(String URL, int logo) {
        MenuModel websites = new MenuModel();
        websites.setMenuTitle("");
        websites.setIcon(logo);
        websites.setURL(URL);
        websitesList.add(websites);
    }

}
