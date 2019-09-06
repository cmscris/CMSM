package com.cris.cmsm.models.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by cms on 4/12/18.
 */

public class KeyValueRequest {


    @SerializedName("user")
    @Expose
    private String user;
    @SerializedName("type")
    @Expose
    private String type;

    @SerializedName("pay_month")
    @Expose
    private String pay_month;


    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPay_month() {
        return pay_month;
    }

    public void setPay_month(String pay_month) {
        this.pay_month = pay_month;
    }
}
