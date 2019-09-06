package com.cris.cmsm.util;

import com.github.mikephil.charting.utils.ValueFormatter;

import java.text.DecimalFormat;

/**
 *
 */
public class UnitValueFormatter implements ValueFormatter {
    String unit;
    String pattern = "###.##";

    public UnitValueFormatter(String unit) {
        this.unit = unit;
    }

    @Override
    public String getFormattedValue(float value) {
        DecimalFormat mFormat = new DecimalFormat(pattern);
        return mFormat.format(Math.abs(value)) + " " + unit;
    }
}
