package com.cris.cmsm.models;

/**
 * Created by rangasanju on 22-02-2018.
 */

public class Lobby {

    private Long id;
    private String lobbycode;
    private String sname;
    private String fname;
    private String divcode;
    private String rlycode;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLobbycode() {
        return lobbycode;
    }

    public void setLobbycode(String lobbycode) {
        this.lobbycode = lobbycode;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getDivcode() {
        return divcode;
    }

    public void setDivcode(String divcode) {
        this.divcode = divcode;
    }

    public String getRlycode() {
        return rlycode;
    }

    public void setRlycode(String rlycode) {
        this.rlycode = rlycode;
    }
}
