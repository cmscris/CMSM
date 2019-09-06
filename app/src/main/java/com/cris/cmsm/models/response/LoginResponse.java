package com.cris.cmsm.models.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 *
 */
public class LoginResponse {
    @SerializedName("isSuccess")
    @Expose
    private Boolean isSuccess;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("loginIfoVO")
    @Expose
    private LoginIfoVO loginIfoVO;

    /**
     * @return The isSuccess
     */
    public Boolean getIsSuccess() {
        return isSuccess;
    }

    /**
     * @param isSuccess The isSuccess
     */
    public void setIsSuccess(Boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    /**
     * @return The message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message The message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @return The loginIfoVO
     */
    public LoginIfoVO getLoginIfoVO() {
        return loginIfoVO;
    }

    /**
     * @param loginIfoVO The loginIfoVO
     */
    public void setLoginIfoVO(LoginIfoVO loginIfoVO) {
        this.loginIfoVO = loginIfoVO;
    }
}
