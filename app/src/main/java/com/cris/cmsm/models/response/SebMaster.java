package com.cris.cmsm.models.response;

/**
 *
 */
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SebMaster {

    @SerializedName("sebName")
    @Expose
    private String sebName;
    @SerializedName("sebCode")
    @Expose
    private String sebCode;

    /**
     *
     * @return
     * The sebName
     */
    public String getSebName() {
        return sebName;
    }

    /**
     *
     * @param sebName
     * The sebName
     */
    public void setSebName(String sebName) {
        this.sebName = sebName;
    }

    /**
     *
     * @return
     * The sebCode
     */
    public String getSebCode() {
        return sebCode;
    }

    /**
     *
     * @param sebCode
     * The sebCode
     */
    public void setSebCode(String sebCode) {
        this.sebCode = sebCode;
    }

}
