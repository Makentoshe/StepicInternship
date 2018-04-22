package com.makentoshe.stepicinternship.common.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Class for describing autocomplete response.
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

    public class Query {

        @SerializedName("text")
        @Expose
        private String text;

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

    }

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
}
