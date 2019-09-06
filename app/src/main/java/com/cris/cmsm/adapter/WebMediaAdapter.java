package com.cris.cmsm.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.cris.cmsm.R;
import com.cris.cmsm.models.MenuModel;
import com.cris.cmsm.util.FontFamily;

import java.util.List;

public class WebMediaAdapter extends BaseAdapter {
	Context context;
	List<MenuModel> menuModelList;
	LayoutInflater inflater;
	Typeface typeFace;

	public WebMediaAdapter(Context context, List<MenuModel> menuModelList) {
		this.context = context;
		this.menuModelList = menuModelList;
		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		typeFace = new FontFamily(context).getRegular();
	}

	@Override
	public int getCount() {
		return menuModelList.size();
	}

	@Override
	public MenuModel getItem(int position) {
		return menuModelList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null)
			convertView = inflater.inflate(R.layout.social_webpage, parent,
					false);
		ImageView iv_social = convertView
				.findViewById(R.id.iv_social);
		iv_social.setImageResource(getItem(position).getIcon());
		return convertView;
	}

}
