package com.cris.cmsm.models.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class IrregularCrew {


    @SerializedName("lobby")
    @Expose
    private String lobby;
    @SerializedName("notAckGt24")
    @Expose
    private String notAckGt24;
    @SerializedName("notSignonGt24")
    @Expose
    private String notSignonGt24;
    @SerializedName("osRestGt72")
    @Expose
    private String osRestGt72;
    @SerializedName("onTrainGt24")
    @Expose
    private String onTrainGt24;
    @SerializedName("sysNonRun")
    @Expose
    private String sysNonRun;
    @SerializedName("nonRunGt7Days")
    @Expose
    private String nonRunGt7Days;
    @SerializedName("crewInRestGt36")
    @Expose
    private String crewInRestGt36;
    @SerializedName("unplanned")
    @Expose
    private String unplanned;
    @SerializedName("approvalPendOnOff")
    @Expose
    private String approvalPendOnOff;
    @SerializedName("missedDutyCoachingLink48")
    @Expose
    private String missedDutyCoachingLink48;
    @SerializedName("missedDutyShuntingLink48")
    @Expose
    private String missedDutyShuntingLink48;


    public String getLobby() {
        return lobby;
    }

    public void setLobby(String lobby) {
        this.lobby = lobby;
    }

    public String getNotAckGt24() {
        return notAckGt24;
    }

    public void setNotAckGt24(String notAckGt24) {
        this.notAckGt24 = notAckGt24;
    }

    public String getNotSignonGt24() {
        return notSignonGt24;
    }

    public void setNotSignonGt24(String notSignonGt24) {
        this.notSignonGt24 = notSignonGt24;
    }

    public String getOsRestGt72() {
        return osRestGt72;
    }

    public void setOsRestGt72(String osRestGt72) {
        this.osRestGt72 = osRestGt72;
    }

    public String getOnTrainGt24() {
        return onTrainGt24;
    }

    public void setOnTrainGt24(String onTrainGt24) {
        this.onTrainGt24 = onTrainGt24;
    }

    public String getSysNonRun() {
        return sysNonRun;
    }

    public void setSysNonRun(String sysNonRun) {
        this.sysNonRun = sysNonRun;
    }

    public String getNonRunGt7Days() {
        return nonRunGt7Days;
    }

    public void setNonRunGt7Days(String nonRunGt7Days) {
        this.nonRunGt7Days = nonRunGt7Days;
    }

    public String getCrewInRestGt36() {
        return crewInRestGt36;
    }

    public void setCrewInRestGt36(String crewInRestGt36) {
        this.crewInRestGt36 = crewInRestGt36;
    }

    public String getUnplanned() {
        return unplanned;
    }

    public void setUnplanned(String unplanned) {
        this.unplanned = unplanned;
    }

    public String getApprovalPendOnOff() {
        return approvalPendOnOff;
    }

    public void setApprovalPendOnOff(String approvalPendOnOff) {
        this.approvalPendOnOff = approvalPendOnOff;
    }

    public String getMissedDutyCoachingLink48() {
        return missedDutyCoachingLink48;
    }

    public void setMissedDutyCoachingLink48(String missedDutyCoachingLink48) {
        this.missedDutyCoachingLink48 = missedDutyCoachingLink48;
    }

    public String getMissedDutyShuntingLink48() {
        return missedDutyShuntingLink48;
    }

    public void setMissedDutyShuntingLink48(String missedDutyShuntingLink48) {
        this.missedDutyShuntingLink48 = missedDutyShuntingLink48;
    }
}
