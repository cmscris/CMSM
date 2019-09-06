package com.cris.cmsm.models.request;

/**
 *
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SEBRequest {

    @SerializedName("roleId")
    @Expose
    private String roleId;

    /**
     * @return The roleId
     */
    public String getRoleId() {
        return roleId;
    }

    /**
     * @param roleId The roleId
     */
    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

}