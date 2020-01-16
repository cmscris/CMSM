package com.cris.cmsm.models.response;

import com.cris.cmsm.models.LiInspectionRecord;

import java.util.List;

public class LiInspectionResponse {


    private String message;
    private List<LiInspectionRecord> liInspectionRecord = null;


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<LiInspectionRecord> getLiInspectionRecord() {
        return liInspectionRecord;
    }

    public void setLiInspectionRecord(List<LiInspectionRecord> liInspectionRecord) {
        this.liInspectionRecord = liInspectionRecord;
    }
}
