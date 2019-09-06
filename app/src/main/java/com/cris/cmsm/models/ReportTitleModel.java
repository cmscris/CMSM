package com.cris.cmsm.models;

/**
 *
 */
public class ReportTitleModel {
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubheading() {
        return subheading;
    }

    public void setSubheading(String subheading) {
        this.subheading = subheading;
    }

    String title;
    String subheading;

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    int position;

    public ReportTitleModel(String title, String subheading, int position) {
        this.title = title;
        this.subheading = subheading;
        this.position = position;
    }
}
