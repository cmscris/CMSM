package com.cris.cmsm.models.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Remarksresponse {

    @SerializedName("vosList")
    @Expose
    private List<VosList> vosList = null;
    @SerializedName("flag")
    @Expose
    private String flag;
    public String getFlag(){
        return flag;}
    public void setFlag(String flag) {
        this.flag = flag;
    }

    public List<VosList> getVosList() {
        return vosList;
    }

    public void setVosList(List<VosList> vosList) {
        this.vosList = vosList;
    }

}



