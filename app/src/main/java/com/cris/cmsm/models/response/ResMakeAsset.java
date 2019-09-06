package com.cris.cmsm.models.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 *
 */

public class ResMakeAsset {
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("assetMakeVOs")
    @Expose
    private List<AssetMakeVO> assetMakeVOs = null;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<AssetMakeVO> getAssetMakeVOs() {
        return assetMakeVOs;
    }

    public void setAssetMakeVOs(List<AssetMakeVO> assetMakeVOs) {
        this.assetMakeVOs = assetMakeVOs;
    }
}
