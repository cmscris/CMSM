package com.cris.cmsm.models.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 *
 */

public class SsFeedersVO {

    @SerializedName("freederId")
    @Expose
    private String freederId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("connectedLoad")
    @Expose
    private String connectedLoad;

    public String getFreederId() {
        return freederId;
    }

    public void setFreederId(String freederId) {
        this.freederId = freederId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getConnectedLoad() {
        return connectedLoad;
    }

    public void setConnectedLoad(String connectedLoad) {
        this.connectedLoad = connectedLoad;
    }
}
