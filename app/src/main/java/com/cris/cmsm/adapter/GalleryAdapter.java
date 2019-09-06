package com.cris.cmsm.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.cris.cmsm.R;
import com.cris.cmsm.models.GalleryModel;
import com.cris.cmsm.util.CommonClass;
import com.cris.cmsm.util.FontFamily;
import com.squareup.picasso.Picasso;

import java.util.List;

public class GalleryAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private List<GalleryModel> list;
    Typeface font;
    Context context;

    public GalleryAdapter(Context context, List<GalleryModel> list) {
        this.context = context;
        font = new FontFamily(context).getRegular();
        this.list = list;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public GalleryModel getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.gallery_image, viewGroup, false);
        }
        GalleryModel galleryModel = getItem(position);
        ImageView img_photogallery = convertView.findViewById(R.id.img_photogallery);
        Picasso.with(context).load(CommonClass.encodeURL(galleryModel.getURL()))
                .into(img_photogallery);
        return convertView;
    }
}
