package com.cris.cmsm;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.cris.cmsm.util.CommonClass;
import com.cris.cmsm.util.Constants;
import com.cris.cmsm.widget.TouchImageView;
import com.squareup.picasso.Picasso;

public class ZoomImageActivity extends Activity {

    private ImageView iv_title_icon;
    private TextView action_bar_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zoom_activity);
        action_bar_title = findViewById(R.id.action_bar_title);
        iv_title_icon = findViewById(R.id.iv_title_icon);
        TouchImageView iv_scale_image = findViewById(R.id.iv_scale_image);
        Intent imageURL = getIntent();
        String URL = imageURL.getStringExtra(Constants.IMAGE_URL);
        Picasso.with(ZoomImageActivity.this).load(CommonClass.encodeURL(URL)).into(iv_scale_image);

        action_bar_title.setVisibility(View.GONE);
        iv_title_icon.setImageResource(R.drawable.iv_back);
        iv_title_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ZoomImageActivity.this.finish();
            }
        });

    }
}
