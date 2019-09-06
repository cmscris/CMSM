package com.cris.cmsm.fragments;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cris.cmsm.R;
import com.cris.cmsm.util.FontFamily;

/**
 *
 */
public class ContactUsFragment extends Fragment {

    TextView tv_contact, contact_1, contact_2, contact_3, contact_4, contact_5;
    Typeface font, fontBold;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.contact_us, container, false);
        FontFamily fontFamily = new FontFamily(getActivity());
        font = fontFamily.getRegular();
        fontBold = fontFamily.getBold();
        tv_contact = view.findViewById(R.id.tv_contact);
        contact_1 = view.findViewById(R.id.contact_1);
        contact_2 = view.findViewById(R.id.contact_2);
        contact_3 = view.findViewById(R.id.contact_3);
        contact_4 = view.findViewById(R.id.contact_4);
        contact_5 = view.findViewById(R.id.contact_5);

        tv_contact.setTypeface(fontBold);
        contact_1.setTypeface(font);
        contact_2.setTypeface(font);
        contact_3.setTypeface(font);
        contact_4.setTypeface(font);
        contact_5.setTypeface(font);
        return view;
    }
}
