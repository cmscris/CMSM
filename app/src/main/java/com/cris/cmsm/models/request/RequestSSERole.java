package com.cris.cmsm.models.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 *
 */
public class RequestSSERole {
    @SerializedName("authLevel")
    @Expose
    private Integer authLevel;
    @SerializedName("category")
    @Expose
    private String category;
    @SerializedName("divcode")
    @Expose
    private String divcode;
    @SerializedName("hqstnCode")
    @Expose
    private String hqstnCode;
    @SerializedName("loginid")
    @Expose
    private String loginid;

    /**
     *
     * @return
     * The authLevel
     */
    public Integer getAuthLevel() {
        return authLevel;
    }

    /**
     *
     * @param authLevel
     * The authLevel
     */
    public void setAuthLevel(Integer authLevel) {
        this.authLevel = authLevel;
    }

    /**
     *
     * @return
     * The category
     */
    public String getCategory() {
        return category;
    }

    /**
     *
     * @param category
     * The category
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     *
     * @return
     * The divcode
     */
    public String getDivcode() {
        return divcode;
    }

    /**
     *
     * @param divcode
     * The divcode
     */
    public void setDivcode(String divcode) {
        this.divcode = divcode;
    }

    /**
     *
     * @return
     * The hqstnCode
     */
    public String getHqstnCode() {
        return hqstnCode;
    }

    /**
     *
     * @param hqstnCode
     * The hqstnCode
     */
    public void setHqstnCode(String hqstnCode) {
        this.hqstnCode = hqstnCode;
    }

    public String getLoginid() {
        return loginid;
    }

    public void setLoginid(String loginid) {
        this.loginid = loginid;
    }
}
