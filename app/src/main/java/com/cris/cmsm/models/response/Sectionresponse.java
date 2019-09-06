package com.cris.cmsm.models.response;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Sectionresponse {

    @SerializedName("vosList")
    @Expose
    private List<String> vosList = null;

    public List<String> getVosList() {
        return vosList;
    }

    public void setVosList(List<String> vosList) {
        this.vosList = vosList;
    }

}