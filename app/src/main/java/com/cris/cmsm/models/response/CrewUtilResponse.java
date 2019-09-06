package com.cris.cmsm.models.response;

        import com.google.gson.annotations.Expose;
        import com.google.gson.annotations.SerializedName;

        import java.util.List;

/**
 *
 */

public class CrewUtilResponse {
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("crewUtilResponseVO")
    @Expose
    private List<CrewUtilResponseVO> crewUtilResponseVO = null;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<CrewUtilResponseVO> getCrewUtilResponseVO() {
        return crewUtilResponseVO;
    }

    public void setCrewUtilResponseVO(List<CrewUtilResponseVO> crewUtilResponseVO) {
        this.crewUtilResponseVO = crewUtilResponseVO;
    }
}
