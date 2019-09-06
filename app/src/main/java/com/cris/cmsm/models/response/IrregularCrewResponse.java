package com.cris.cmsm.models.response;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class IrregularCrewResponse {

    @SerializedName("irregularCrew")
    @Expose
    private List<IrregularCrew> irregularCrewList = null;


    public List<IrregularCrew> getIrregularCrewList() {
        return irregularCrewList;
    }

    public void setIrregularCrewList(List<IrregularCrew> irregularCrewList) {
        this.irregularCrewList = irregularCrewList;
    }
}