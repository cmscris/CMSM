package com.cris.cmsm.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.cris.cmsm.R;
import com.cris.cmsm.ZoomImageActivity;
import com.cris.cmsm.adapter.GalleryAdapter;
import com.cris.cmsm.models.GalleryModel;
import com.cris.cmsm.util.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class PhotoGalleryFragment extends Fragment {
    private GridView grid_helpline;
    private List<GalleryModel> galleryModelList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.social_main, container, false);
        grid_helpline = view.findViewById(R.id.grid_helpline);
        galleryModelList = new ArrayList<>();
        setImage("https://www.cms.indianrail.gov.in/slideshow/images/frontsplash1.jpg");
        setImage("https://www.cms.indianrail.gov.in/slideshow/images/frontsplash2.jpg");
        setImage("https://www.cms.indianrail.gov.in/slideshow/images/frontsplash3.jpg");
        setImage("https://www.cms.indianrail.gov.in/slideshow/images/frontsplash4.jpg");
        setImage("https://www.cms.indianrail.gov.in/slideshow/images/frontsplash5.jpg");
        setImage("https://www.cms.indianrail.gov.in/slideshow/images/frontsplash6.jpg");
        setImage("https://www.cms.indianrail.gov.in/slideshow/images/frontsplash7.jpg");
        setImage("https://www.cms.indianrail.gov.in/slideshow/images/frontsplash8.jpg");
        setImage("https://www.cms.indianrail.gov.in/slideshow/images/frontsplash9.jpg");
        setImage("https://www.cms.indianrail.gov.in/slideshow/images/frontsplash10.jpg");
        setImage("https://www.cms.indianrail.gov.in/slideshow/images/frontsplash11.jpg");
        setImage("https://www.cms.indianrail.gov.in/slideshow/images/frontsplash12.jpg");
        setImage("https://www.cms.indianrail.gov.in/slideshow/images/frontsplash13.jpg");
        setImage("https://www.cms.indianrail.gov.in/slideshow/images/frontsplash14.jpg");

       /*
        setImage("http://www.cms.indianrail.gov.in/slideshow/images/frontsplash15.jpg");
        setImage("http://www.cms.indianrail.gov.in/slideshow/images/frontsplash16.jpg");
        setImage("http://www.cms.indianrail.gov.in/slideshow/images/frontsplash17.jpg");
        setImage("http://www.cms.indianrail.gov.in/slideshow/images/frontsplash18.jpg");
        setImage("http://www.cms.indianrail.gov.in/slideshow/images/frontsplash19.jpg");
        setImage("http://www.cms.indianrail.gov.in/slideshow/images/frontsplash20.jpg");
        setImage("http://www.cms.indianrail.gov.in/slideshow/images/frontsplash21.jpg");
        setImage("http://www.cms.indianrail.gov.in/slideshow/images/frontsplash22.jpg");
        setImage("http://www.cms.indianrail.gov.in/slideshow/images/frontsplash23.jpg");
        setImage("http://www.cms.indianrail.gov.in/slideshow/images/frontsplash24.jpg");
        setImage("http://www.cms.indianrail.gov.in/slideshow/images/frontsplash25.jpg");
        setImage("http://www.cms.indianrail.gov.in/slideshow/images/frontsplash26.jpg");

*/

        grid_helpline.setAdapter(new GalleryAdapter(getActivity(), galleryModelList));
        grid_helpline.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                GalleryModel model = (GalleryModel) parent.getItemAtPosition(position);
                Intent intent = new Intent(getActivity(), ZoomImageActivity.class);
                intent.putExtra(Constants.IMAGE_URL, model.getURL());
                startActivity(intent);
            }
        });
        return view;
    }

    private void setImage(String URL) {
        galleryModelList.add(new GalleryModel(URL));
    }
}
