package com.makentoshe.stepicinternship.common.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Makentoshe on 24.04.2018.
 */

public class SectionModel {

    @SerializedName("meta")
    @Expose
    private Meta meta;
    @SerializedName("sections")
    @Expose
    private List<Section> sections = null;

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public List<Section> getSections() {
        return sections;
    }

    public void setSections(List<Section> sections) {
        this.sections = sections;
    }


    public class Actions {

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

    public class Section {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("course")
        @Expose
        private Integer course;
        @SerializedName("units")
        @Expose
        private List<Integer> units = null;
        @SerializedName("position")
        @Expose
        private Integer position;
        @SerializedName("discounting_policy")
        @Expose
        private String discountingPolicy;
        @SerializedName("progress")
        @Expose
        private Object progress;
        @SerializedName("actions")
        @Expose
        private Actions actions;
        @SerializedName("required_section")
        @Expose
        private Object requiredSection;
        @SerializedName("required_percent")
        @Expose
        private Integer requiredPercent;
        @SerializedName("is_requirement_satisfied")
        @Expose
        private Boolean isRequirementSatisfied;
        @SerializedName("is_exam")
        @Expose
        private Boolean isExam;
        @SerializedName("exam_duration_minutes")
        @Expose
        private Integer examDurationMinutes;
        @SerializedName("exam_session")
        @Expose
        private Object examSession;
        @SerializedName("proctor_session")
        @Expose
        private Object proctorSession;
        @SerializedName("description")
        @Expose
        private String description;
        @SerializedName("title")
        @Expose
        private String title;
        @SerializedName("slug")
        @Expose
        private String slug;
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
        private String gradingPolicySource;
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

        public Integer getCourse() {
            return course;
        }

        public void setCourse(Integer course) {
            this.course = course;
        }

        public List<Integer> getUnits() {
            return units;
        }

        public void setUnits(List<Integer> units) {
            this.units = units;
        }

        public Integer getPosition() {
            return position;
        }

        public void setPosition(Integer position) {
            this.position = position;
        }

        public String getDiscountingPolicy() {
            return discountingPolicy;
        }

        public void setDiscountingPolicy(String discountingPolicy) {
            this.discountingPolicy = discountingPolicy;
        }

        public Object getProgress() {
            return progress;
        }

        public void setProgress(Object progress) {
            this.progress = progress;
        }

        public Actions getActions() {
            return actions;
        }

        public void setActions(Actions actions) {
            this.actions = actions;
        }

        public Object getRequiredSection() {
            return requiredSection;
        }

        public void setRequiredSection(Object requiredSection) {
            this.requiredSection = requiredSection;
        }

        public Integer getRequiredPercent() {
            return requiredPercent;
        }

        public void setRequiredPercent(Integer requiredPercent) {
            this.requiredPercent = requiredPercent;
        }

        public Boolean getIsRequirementSatisfied() {
            return isRequirementSatisfied;
        }

        public void setIsRequirementSatisfied(Boolean isRequirementSatisfied) {
            this.isRequirementSatisfied = isRequirementSatisfied;
        }

        public Boolean getIsExam() {
            return isExam;
        }

        public void setIsExam(Boolean isExam) {
            this.isExam = isExam;
        }

        public Integer getExamDurationMinutes() {
            return examDurationMinutes;
        }

        public void setExamDurationMinutes(Integer examDurationMinutes) {
            this.examDurationMinutes = examDurationMinutes;
        }

        public Object getExamSession() {
            return examSession;
        }

        public void setExamSession(Object examSession) {
            this.examSession = examSession;
        }

        public Object getProctorSession() {
            return proctorSession;
        }

        public void setProctorSession(Object proctorSession) {
            this.proctorSession = proctorSession;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getSlug() {
            return slug;
        }

        public void setSlug(String slug) {
            this.slug = slug;
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

        public String getGradingPolicySource() {
            return gradingPolicySource;
        }

        public void setGradingPolicySource(String gradingPolicySource) {
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
