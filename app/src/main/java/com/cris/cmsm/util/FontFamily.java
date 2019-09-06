package com.cris.cmsm.util;

import android.content.Context;
import android.graphics.Typeface;

public class FontFamily {
	Context context;

	public FontFamily(Context context) {
		this.context = context;
	}

	public Typeface getBold() {
		Typeface font = Typeface.createFromAsset(context.getAssets(),
				"quick_bold.ttf");
		return font;
	}

	public Typeface getRegular() {
		Typeface font = Typeface.createFromAsset(context.getAssets(), "quicksane_regular.ttf");
		return font;
	}


}
