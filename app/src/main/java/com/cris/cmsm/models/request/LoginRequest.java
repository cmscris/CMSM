package com.cris.cmsm.models.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 *
 */
public class LoginRequest {

    @SerializedName("user")
    @Expose
    private String user;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("logintype")
    @Expose
    private String logintype;



    /**
     *
     * @return
     * The user
     */
    public String getUser() {
        return user;
    }

    /**
     *
     * @param user
     * The user
     */
    public void setUser(String user) {
        this.user = user;
    }

    /**
     *
     * @return
     * The password
     */
    public String getPassword() {
        return password;
    }

    /**
     *
     * @param password
     * The password
     */
    public void setPassword(String password) {
        this.password = password;
    }



    public String getLogintype() {return logintype;}

    public void setLogintype(String logintype) {this.logintype = logintype;}

}