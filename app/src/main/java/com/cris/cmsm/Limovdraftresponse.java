package com.cris.cmsm;

import com.cris.cmsm.models.response.LICrewMonitoredResponseVO;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Limovdraftresponse {
    ArrayList<String>limovdraftlist=null;
    String id="";
    String frmdttm="";
    String todttm="";
    String dutytyp="";
    String via2="";

    String frmsttn="";
    String tosttn="";
    String loco="";
    String edit="";
    String train="";
    String rmk="";
    String via1="";
    String km="";

    String del="";

    public String getDel() {
        return del;
    }

    public void setDel(String del) {
        this.del = del;
    }






    /*public Limovdraftresponse(ArrayList<String>limovdraftlist){
      this.limovdraftlist=limovdraftlist;

    }*/
    public Limovdraftresponse(){
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFrmdttm() {
        return frmdttm;
    }

    public void setFrmdttm(String frmdttm) {
        this.frmdttm = frmdttm;
    }

    public String getTodttm() {
        return todttm;
    }

    public void setTodttm(String todttm) {
        this.todttm = todttm;
    }

    public String getDutytyp() {
        return dutytyp;
    }

    public void setDutytyp(String dutytyp) {
        this.dutytyp = dutytyp;
    }

    public String getVia1() {
        return via1;
    }

    public void setVia1(String via1) {
        this.via1 = via1;
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

    public String getLoco() {
        return loco;
    }

    public void setLoco(String loco) {
        this.loco = loco;
    }

    public String getTrain() {
        return train;
    }

    public void setTrain(String train) {
        this.train = train;
    }

    public String getRmk() {
        return rmk;
    }

    public void setRmk(String rmk) {
        this.rmk = rmk;
    }

    public String getVia2() {
        return via2;
    }

    public void setVia2(String via2) {
        this.via2 = via2;
    }

    public String getKm() {
        return km;
    }

    public void setKm(String km) {
        this.km = km;
    }
    public String getEdit() {
        return edit;
    }

    public void setEdit(String edit) {
        this.edit = edit;
    }



    public ArrayList <String> getLimovdraftlist() {
        return limovdraftlist;
    }

    public void setLimovdraftlist(ArrayList <String> limovdraftlist) {
        this.limovdraftlist = limovdraftlist;
    }




}
