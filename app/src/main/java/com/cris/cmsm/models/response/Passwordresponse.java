package com.cris.cmsm.models.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Passwordresponse {
    @SerializedName("vosList")
    @Expose
    private List<Integer> vosList = null;

    public List<Integer> getVosList() {
        return vosList;
    }

    public void setVosList(List<Integer> vosList) {
        this.vosList = vosList;
    }

}

