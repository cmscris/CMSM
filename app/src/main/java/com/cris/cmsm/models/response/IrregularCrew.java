package com.cris.cmsm.models.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class IrregularCrew extends AbnormalityResponseVO {


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

    @SerializedName("Abnormality_head")
    @Expose
    private String Abnormality_head;
    @SerializedName("Lt_8")
    @Expose
    private String Lt_8;
    @SerializedName("Bw_8_16")
    @Expose
    private String Bw_8_16;
    @SerializedName("Bw_16_24")
    @Expose
    private String Bw_16_24;

    @SerializedName("Bw_72_240")
    @Expose
    private String Bw_72_240;
    @SerializedName("setStill_open")
    @Expose
    private String Still_open;
    @SerializedName("Bw_24_72")
    @Expose
    private String Bw_24_72;
    @SerializedName("Gt_240")
    @Expose
    private String Gt_240;

    public String getAbnormality_head() {
        return Abnormality_head;
    }

    public void setAbnormality_head(String abnormality_head) {
        Abnormality_head = abnormality_head;
    }

    public String getLt_8() {
        return Lt_8;
    }

    public void setLt_8(String lt_8) {
        Lt_8 = lt_8;
    }

    public String getBw_8_16() {
        return Bw_8_16;
    }

    public void setBw_8_16(String bw_8_16) {
        Bw_8_16 = bw_8_16;
    }

    public String getBw_16_24() {
        return Bw_16_24;
    }

    public void setBw_16_24(String bw_16_24) {
        Bw_16_24 = bw_16_24;
    }

    public String getBw_72_240() {
        return Bw_72_240;
    }

    public void setBw_72_240(String bw_72_240) {
        Bw_72_240 = bw_72_240;
    }

    public String getStill_open() {
        return Still_open;
    }

    public void setStill_open(String Still_open) {
        this.Still_open = Still_open;
    }

    public String getBw_24_72() {
        return Bw_24_72;
    }

    public void setBw_24_72(String bw_24_72) {
        Bw_24_72 = bw_24_72;
    }

    public String getGt_240() {
        return Gt_240;
    }

    public void setGt_240(String gt_240) {
        Gt_240 = gt_240;
    }



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
