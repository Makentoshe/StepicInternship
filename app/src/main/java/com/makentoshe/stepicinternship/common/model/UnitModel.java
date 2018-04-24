package com.makentoshe.stepicinternship.common.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Makentoshe on 24.04.2018.
 */

public class UnitModel {
    @SerializedName("meta")
    @Expose
    private Meta meta;
    @SerializedName("units")
    @Expose
    private List<Unit> units = null;

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public List<Unit> getUnits() {
        return units;
    }

    public void setUnits(List<Unit> units) {
        this.units = units;
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

    public class Unit {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("section")
        @Expose
        private Integer section;
        @SerializedName("lesson")
        @Expose
        private Integer lesson;
        @SerializedName("assignments")
        @Expose
        private List<Integer> assignments = null;
        @SerializedName("position")
        @Expose
        private Integer position;
        @SerializedName("progress")
        @Expose
        private Object progress;
        @SerializedName("begin_date")
        @Expose
        private Object beginDate;
        @SerializedName("end_date")
        @Expose
        private Object endDate;
        @SerializedName("soft_deadline")
        @Expose
        private Object softDeadline;
        @SerializedName("hard_deadline")
        @Expose
        private Object hardDeadline;
        @SerializedName("grading_policy")
        @Expose
        private String gradingPolicy;
        @SerializedName("begin_date_source")
        @Expose
        private Object beginDateSource;
        @SerializedName("end_date_source")
        @Expose
        private Object endDateSource;
        @SerializedName("soft_deadline_source")
        @Expose
        private Object softDeadlineSource;
        @SerializedName("hard_deadline_source")
        @Expose
        private Object hardDeadlineSource;
        @SerializedName("grading_policy_source")
        @Expose
        private Object gradingPolicySource;
        @SerializedName("is_active")
        @Expose
        private Boolean isActive;
        @SerializedName("create_date")
        @Expose
        private Object createDate;
        @SerializedName("update_date")
        @Expose
        private String updateDate;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Integer getSection() {
            return section;
        }

        public void setSection(Integer section) {
            this.section = section;
        }

        public Integer getLesson() {
            return lesson;
        }

        public void setLesson(Integer lesson) {
            this.lesson = lesson;
        }

        public List<Integer> getAssignments() {
            return assignments;
        }

        public void setAssignments(List<Integer> assignments) {
            this.assignments = assignments;
        }

        public Integer getPosition() {
            return position;
        }

        public void setPosition(Integer position) {
            this.position = position;
        }

        public Object getProgress() {
            return progress;
        }

        public void setProgress(Object progress) {
            this.progress = progress;
        }

        public Object getBeginDate() {
            return beginDate;
        }

        public void setBeginDate(Object beginDate) {
            this.beginDate = beginDate;
        }

        public Object getEndDate() {
            return endDate;
        }

        public void setEndDate(Object endDate) {
            this.endDate = endDate;
        }

        public Object getSoftDeadline() {
            return softDeadline;
        }

        public void setSoftDeadline(Object softDeadline) {
            this.softDeadline = softDeadline;
        }

        public Object getHardDeadline() {
            return hardDeadline;
        }

        public void setHardDeadline(Object hardDeadline) {
            this.hardDeadline = hardDeadline;
        }

        public String getGradingPolicy() {
            return gradingPolicy;
        }

        public void setGradingPolicy(String gradingPolicy) {
            this.gradingPolicy = gradingPolicy;
        }

        public Object getBeginDateSource() {
            return beginDateSource;
        }

        public void setBeginDateSource(Object beginDateSource) {
            this.beginDateSource = beginDateSource;
        }

        public Object getEndDateSource() {
            return endDateSource;
        }

        public void setEndDateSource(Object endDateSource) {
            this.endDateSource = endDateSource;
        }

        public Object getSoftDeadlineSource() {
            return softDeadlineSource;
        }

        public void setSoftDeadlineSource(Object softDeadlineSource) {
            this.softDeadlineSource = softDeadlineSource;
        }

        public Object getHardDeadlineSource() {
            return hardDeadlineSource;
        }

        public void setHardDeadlineSource(Object hardDeadlineSource) {
            this.hardDeadlineSource = hardDeadlineSource;
        }

        public Object getGradingPolicySource() {
            return gradingPolicySource;
        }

        public void setGradingPolicySource(Object gradingPolicySource) {
            this.gradingPolicySource = gradingPolicySource;
        }

        public Boolean getIsActive() {
            return isActive;
        }

        public void setIsActive(Boolean isActive) {
            this.isActive = isActive;
        }

        public Object getCreateDate() {
            return createDate;
        }

        public void setCreateDate(Object createDate) {
            this.createDate = createDate;
        }

        public String getUpdateDate() {
            return updateDate;
        }

        public void setUpdateDate(String updateDate) {
            this.updateDate = updateDate;
        }

    }
}
