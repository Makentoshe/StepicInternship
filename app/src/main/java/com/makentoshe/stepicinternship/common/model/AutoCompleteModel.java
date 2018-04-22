package com.makentoshe.stepicinternship.common.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Makentoshe on 22.04.2018.
 */

public class AutoCompleteModel {

    @SerializedName("meta")
    @Expose
    private Meta meta;
    @SerializedName("queries")
    @Expose
    private List<Query> queries = null;

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public List<Query> getQueries() {
        return queries;
    }

    public void setQueries(List<Query> queries) {
        this.queries = queries;
    }

}
