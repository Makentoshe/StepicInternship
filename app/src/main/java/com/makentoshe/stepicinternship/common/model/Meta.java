package com.makentoshe.stepicinternship.common.model;

/**
 * Created by Makentoshe on 22.04.2018.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Meta {

    @SerializedName("page")
    @Expose
    private Integer page;
    @SerializedName("has_next")
    @Expose
    private Boolean hasNext;
    @SerializedName("has_previous")
    @Expose
    private Boolean hasPrevious;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Boolean getHasNext() {
        return hasNext;
    }

    public void setHasNext(Boolean hasNext) {
        this.hasNext = hasNext;
    }

    public Boolean getHasPrevious() {
        return hasPrevious;
    }

    public void setHasPrevious(Boolean hasPrevious) {
        this.hasPrevious = hasPrevious;
    }

}