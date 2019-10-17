package com.cris.cmsm.models.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cms on 4/11/18.
 */

public class LICrewMonitoredResponse {
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("liCrewMonitoredResponseVO")
    @Expose
    private List<LICrewMonitoredResponseVO> liCrewMonitoredResponseVO = null;
    String sn="";
    String crewid="";
    String crewname="";
    String desig="";
    String status="";
    String avlsttn="";
    String frmsttn="";
    String tosttn="";
    String calltime="";
    String booktime="";
    String availtym="";
    String lastsignofftym="";
    public LICrewMonitoredResponse(String sn, String crewid, String crewname, String desig, String status, String avlsttn, String frmsttn, String tosttn, String calltime, String booktime, String availtym, String lastsignofftym) {
        this.sn=sn;
        this.crewid=crewid;
        this.crewname=crewname;
        this.desig=desig;
        this.status=status;
        this.avlsttn=avlsttn;
        this.frmsttn=frmsttn;
        this.tosttn=tosttn;
        this.calltime=calltime;
        this.booktime=booktime;
        this.availtym=availtym;
        this.lastsignofftym=lastsignofftym;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public List<LICrewMonitoredResponseVO> getLiCrewMonitoredResponseVO() {
        return liCrewMonitoredResponseVO;
    }

    public void setLiCrewMonitoredResponseVO(List<LICrewMonitoredResponseVO> liCrewMonitoredResponseVO) {
        this.liCrewMonitoredResponseVO = liCrewMonitoredResponseVO;
    }
    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getCrewid() {
        return crewid;
    }

    public void setCrewid(String crewid) {
        this.crewid = crewid;
    }

    public String getCrewname() {
        return crewname;
    }

    public void setCrewname(String crewname) {
        this.crewname = crewname;
    }

    public String getDesig() {
        return desig;
    }

    public void setDesig(String desig) {
        this.desig = desig;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAvlsttn() {
        return avlsttn;
    }

    public void setAvlsttn(String avlsttn) {
        this.avlsttn = avlsttn;
    }

    public String getFrmsttn() {
        return frmsttn;
    }

    public void setFrmsttn(String frmsttn) {
        this.frmsttn = frmsttn;
    }

    public String getTosttn() {
        return tosttn;
    }

    public void setTosttn(String tosttn) {
        this.tosttn = tosttn;
    }

    public String getCalltime() {
        return calltime;
    }

    public void setCalltime(String calltime) {
        this.calltime = calltime;
    }

    public String getBooktime() {
        return booktime;
    }

    public void setBooktime(String booktime) {
        this.booktime = booktime;
    }

    public String getAvailtym() {
        return availtym;
    }

    public void setAvailtym(String availtym) {
        this.availtym = availtym;
    }

    public String getLastsignofftym() {
        return lastsignofftym;
    }

    public void setLastsignofftym(String lastsignofftym) {
        this.lastsignofftym = lastsignofftym;
    }

}
