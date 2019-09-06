package com.cris.cmsm.models.response;

import com.cris.cmsm.models.KeyValue;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CrewDetailsResponse {

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("crewid")
    @Expose
    private String crewid;
    @SerializedName("trainingDetailsVO")
    @Expose
    private List<TrainingDetailsVO> trainingDetailsVO;
    @SerializedName("locoCompetencyVO")
    @Expose
    private List<LocoCompetencyVO> locoCompetencyVO;
    @SerializedName("roadLearningDetails")
    @Expose
    private List<RoadLearningDetailsVO> roadLearningDetails;
    @SerializedName("crewMileageDetailsVO")
    @Expose
    private List<CrewMileageDetailsVO> crewMileageDetailsVO;
    @SerializedName("crewOvertimeDetailsVO")
    @Expose
    private List<CrewOvertimeDetailsVO> crewOvertimeDetailsVO;
    @SerializedName("keyval")
    @Expose
    private List<KeyValue> keyval;


    public List<KeyValue> getKeyval() {
        return keyval;
    }

    public void setKeyval(List<KeyValue> keyval) {
        this.keyval = keyval;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCrewid() {
        return crewid;
    }

    public void setCrewid(String crewid) {
        this.crewid = crewid;
    }

    public List<TrainingDetailsVO> getTrainingDetailsVO() {
        return trainingDetailsVO;
    }

    public void setTrainingDetailsVO(List<TrainingDetailsVO> trainingDetailsVO) {
        this.trainingDetailsVO = trainingDetailsVO;
    }

    public List<LocoCompetencyVO> getLocoCompetencyVO() {
        return locoCompetencyVO;
    }

    public void setLocoCompetencyVO(List<LocoCompetencyVO> locoCompetencyVO) {
        this.locoCompetencyVO = locoCompetencyVO;
    }

    public List<RoadLearningDetailsVO> getRoadLearningDetails() {
        return roadLearningDetails;
    }

    public void setRoadLearningDetails(List<RoadLearningDetailsVO> roadLearningDetails) {
        this.roadLearningDetails = roadLearningDetails;
    }

    public List<CrewMileageDetailsVO> getCrewMileageDetailsVO() {
        return crewMileageDetailsVO;
    }

    public void setCrewMileageDetailsVO(List<CrewMileageDetailsVO> crewMileageDetailsVO) {
        this.crewMileageDetailsVO = crewMileageDetailsVO;
    }


    public List<CrewOvertimeDetailsVO> getCrewOvertimeDetailsVO() {
        return crewOvertimeDetailsVO;
    }

    public void setCrewOvertimeDetailsVO(List<CrewOvertimeDetailsVO> crewOvertimeDetailsVO) {
        this.crewOvertimeDetailsVO = crewOvertimeDetailsVO;
    }
}
