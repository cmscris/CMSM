package com.cris.cmsm.models.response;

        import com.google.gson.annotations.Expose;
        import com.google.gson.annotations.SerializedName;

/**
 * Created by rangasanju on 20/02/2018.
 */
public class CrewPositionSummaryResponse {
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("total")
    @Expose
    private String total;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
}
