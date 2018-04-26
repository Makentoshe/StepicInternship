package com.makentoshe.stepicinternship.common.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Makentoshe on 24.04.2018.
 */

public class LessonModel {

    @SerializedName("meta")
    @Expose
    private Meta meta;
    @SerializedName("lessons")
    @Expose
    private List<Lesson> lessons = null;

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public List<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(List<Lesson> lessons) {
        this.lessons = lessons;
    }


    public class Actions implements Serializable{


    }

    public class Lesson implements Serializable{

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("steps")
        @Expose
        private List<Integer> steps = null;
        @SerializedName("actions")
        @Expose
        private Actions actions;
        @SerializedName("tags")
        @Expose
        private List<Integer> tags = null;
        @SerializedName("required_tags")
        @Expose
        private List<Object> requiredTags = null;
        @SerializedName("progress")
        @Expose
        private String progress;
        @SerializedName("subscriptions")
        @Expose
        private List<String> subscriptions = null;
        @SerializedName("viewed_by")
        @Expose
        private Integer viewedBy;
        @SerializedName("passed_by")
        @Expose
        private Integer passedBy;
        @SerializedName("time_to_complete")
        @Expose
        private Integer timeToComplete;
        @SerializedName("cover_url")
        @Expose
        private String coverUrl;
        @SerializedName("is_comments_enabled")
        @Expose
        private Boolean isCommentsEnabled;
        @SerializedName("owner")
        @Expose
        private Integer owner;
        @SerializedName("language")
        @Expose
        private String language;
        @SerializedName("is_featured")
        @Expose
        private Boolean isFeatured;
        @SerializedName("is_public")
        @Expose
        private Boolean isPublic;
        @SerializedName("title")
        @Expose
        private String title;
        @SerializedName("slug")
        @Expose
        private String slug;
        @SerializedName("create_date")
        @Expose
        private String createDate;
        @SerializedName("update_date")
        @Expose
        private String updateDate;
        @SerializedName("learners_group")
        @Expose
        private Object learnersGroup;
        @SerializedName("testers_group")
        @Expose
        private Object testersGroup;
        @SerializedName("moderators_group")
        @Expose
        private Object moderatorsGroup;
        @SerializedName("teachers_group")
        @Expose
        private Object teachersGroup;
        @SerializedName("admins_group")
        @Expose
        private Object adminsGroup;
        @SerializedName("discussions_count")
        @Expose
        private Integer discussionsCount;
        @SerializedName("discussion_proxy")
        @Expose
        private String discussionProxy;
        @SerializedName("discussion_threads")
        @Expose
        private List<String> discussionThreads = null;
        @SerializedName("epic_count")
        @Expose
        private Integer epicCount;
        @SerializedName("abuse_count")
        @Expose
        private Integer abuseCount;
        @SerializedName("vote")
        @Expose
        private String vote;
        @SerializedName("lti_consumer_key")
        @Expose
        private String ltiConsumerKey;
        @SerializedName("lti_secret_key")
        @Expose
        private String ltiSecretKey;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public List<Integer> getSteps() {
            return steps;
        }

        public void setSteps(List<Integer> steps) {
            this.steps = steps;
        }

        public Actions getActions() {
            return actions;
        }

        public void setActions(Actions actions) {
            this.actions = actions;
        }

        public List<Integer> getTags() {
            return tags;
        }

        public void setTags(List<Integer> tags) {
            this.tags = tags;
        }

        public List<Object> getRequiredTags() {
            return requiredTags;
        }

        public void setRequiredTags(List<Object> requiredTags) {
            this.requiredTags = requiredTags;
        }

        public String getProgress() {
            return progress;
        }

        public void setProgress(String progress) {
            this.progress = progress;
        }

        public List<String> getSubscriptions() {
            return subscriptions;
        }

        public void setSubscriptions(List<String> subscriptions) {
            this.subscriptions = subscriptions;
        }

        public Integer getViewedBy() {
            return viewedBy;
        }

        public void setViewedBy(Integer viewedBy) {
            this.viewedBy = viewedBy;
        }

        public Integer getPassedBy() {
            return passedBy;
        }

        public void setPassedBy(Integer passedBy) {
            this.passedBy = passedBy;
        }

        public Integer getTimeToComplete() {
            return timeToComplete;
        }

        public void setTimeToComplete(Integer timeToComplete) {
            this.timeToComplete = timeToComplete;
        }

        public String getCoverUrl() {
            return coverUrl;
        }

        public void setCoverUrl(String coverUrl) {
            this.coverUrl = coverUrl;
        }

        public Boolean getIsCommentsEnabled() {
            return isCommentsEnabled;
        }

        public void setIsCommentsEnabled(Boolean isCommentsEnabled) {
            this.isCommentsEnabled = isCommentsEnabled;
        }

        public Integer getOwner() {
            return owner;
        }

        public void setOwner(Integer owner) {
            this.owner = owner;
        }

        public String getLanguage() {
            return language;
        }

        public void setLanguage(String language) {
            this.language = language;
        }

        public Boolean getIsFeatured() {
            return isFeatured;
        }

        public void setIsFeatured(Boolean isFeatured) {
            this.isFeatured = isFeatured;
        }

        public Boolean getIsPublic() {
            return isPublic;
        }

        public void setIsPublic(Boolean isPublic) {
            this.isPublic = isPublic;
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

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }

        public String getUpdateDate() {
            return updateDate;
        }

        public void setUpdateDate(String updateDate) {
            this.updateDate = updateDate;
        }

        public Object getLearnersGroup() {
            return learnersGroup;
        }

        public void setLearnersGroup(Object learnersGroup) {
            this.learnersGroup = learnersGroup;
        }

        public Object getTestersGroup() {
            return testersGroup;
        }

        public void setTestersGroup(Object testersGroup) {
            this.testersGroup = testersGroup;
        }

        public Object getModeratorsGroup() {
            return moderatorsGroup;
        }

        public void setModeratorsGroup(Object moderatorsGroup) {
            this.moderatorsGroup = moderatorsGroup;
        }

        public Object getTeachersGroup() {
            return teachersGroup;
        }

        public void setTeachersGroup(Object teachersGroup) {
            this.teachersGroup = teachersGroup;
        }

        public Object getAdminsGroup() {
            return adminsGroup;
        }

        public void setAdminsGroup(Object adminsGroup) {
            this.adminsGroup = adminsGroup;
        }

        public Integer getDiscussionsCount() {
            return discussionsCount;
        }

        public void setDiscussionsCount(Integer discussionsCount) {
            this.discussionsCount = discussionsCount;
        }

        public String getDiscussionProxy() {
            return discussionProxy;
        }

        public void setDiscussionProxy(String discussionProxy) {
            this.discussionProxy = discussionProxy;
        }

        public List<String> getDiscussionThreads() {
            return discussionThreads;
        }

        public void setDiscussionThreads(List<String> discussionThreads) {
            this.discussionThreads = discussionThreads;
        }

        public Integer getEpicCount() {
            return epicCount;
        }

        public void setEpicCount(Integer epicCount) {
            this.epicCount = epicCount;
        }

        public Integer getAbuseCount() {
            return abuseCount;
        }

        public void setAbuseCount(Integer abuseCount) {
            this.abuseCount = abuseCount;
        }

        public String getVote() {
            return vote;
        }

        public void setVote(String vote) {
            this.vote = vote;
        }

        public String getLtiConsumerKey() {
            return ltiConsumerKey;
        }

        public void setLtiConsumerKey(String ltiConsumerKey) {
            this.ltiConsumerKey = ltiConsumerKey;
        }

        public String getLtiSecretKey() {
            return ltiSecretKey;
        }

        public void setLtiSecretKey(String ltiSecretKey) {
            this.ltiSecretKey = ltiSecretKey;
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
