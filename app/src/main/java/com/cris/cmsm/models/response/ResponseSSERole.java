package com.cris.cmsm.models.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 *
 */
public class ResponseSSERole {

    @SerializedName("roleid")
    @Expose
    private String roleid;
    @SerializedName("name")
    @Expose
    private String name;

    /**
     * @return The roleid
     */
    public String getRoleid() {
        return roleid;
    }

    /**
     * @param roleid The roleid
     */
    public void setRoleid(String roleid) {
        this.roleid = roleid;
    }

    /**
     * @return The name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name The name
     */
    public void setName(String name) {
        this.name = name;
    }
}
