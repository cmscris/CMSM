
package com.cris.cmsm.models.response;

import com.cris.cmsm.models.response.VosList;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import static com.cris.cmsm.util.URLS.GETREMARKS;

public class Paramresponse {


    @SerializedName("vosList")
    @Expose
    private List<VosList> vosList = null;
    @SerializedName("flag")
    @Expose
    private String flag;
    int sequence = 0;
    String abnormalityNo = "";
    String crewId = "";
    String appRemarks= "";

    public Paramresponse(int seq, String crewId, String abnrNo,String appRemarks) {
        this.sequence = seq;
        this.crewId = crewId;
        this.abnormalityNo = abnrNo;
        this.appRemarks=appRemarks;

    }

    public String getAppRemarks() {
        return appRemarks;
    }

    public void setAppRemarks(String appRemarks) {
        this.appRemarks = appRemarks;
    }

    public int getSequence() {
        return sequence;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }

    public String getAbnormalityNo() {
        return abnormalityNo;
    }

    public void setAbnormalityNo(String abnormalityNo) {
        this.abnormalityNo = abnormalityNo;
    }

    public String getCrewId() {
        return crewId;
    }

    public void setCrewId(String crewId) {
        this.crewId = crewId;
    }

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


