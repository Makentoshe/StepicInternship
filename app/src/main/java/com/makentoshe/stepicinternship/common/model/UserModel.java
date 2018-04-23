package com.makentoshe.stepicinternship.common.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Makentoshe on 23.04.2018.
 */
public class UserModel {
    @SerializedName("meta")
    @Expose
    private Meta meta;
    @SerializedName("users")
    @Expose
    private List<User> users = null;

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
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

    public class User {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("profile")
        @Expose
        private Integer profile;
        @SerializedName("is_private")
        @Expose
        private Boolean isPrivate;
        @SerializedName("is_active")
        @Expose
        private Boolean isActive;
        @SerializedName("is_guest")
        @Expose
        private Boolean isGuest;
        @SerializedName("is_organization")
        @Expose
        private Boolean isOrganization;
        @SerializedName("short_bio")
        @Expose
        private String shortBio;
        @SerializedName("details")
        @Expose
        private String details;
        @SerializedName("first_name")
        @Expose
        private String firstName;
        @SerializedName("last_name")
        @Expose
        private String lastName;
        @SerializedName("full_name")
        @Expose
        private String fullName;
        @SerializedName("alias")
        @Expose
        private String alias;
        @SerializedName("avatar")
        @Expose
        private String avatar;
        @SerializedName("cover")
        @Expose
        private String cover;
        @SerializedName("city")
        @Expose
        private Object city;
        @SerializedName("level")
        @Expose
        private Integer level;
        @SerializedName("level_title")
        @Expose
        private String levelTitle;
        @SerializedName("tag_progresses")
        @Expose
        private List<Object> tagProgresses = null;
        @SerializedName("knowledge")
        @Expose
        private Integer knowledge;
        @SerializedName("knowledge_rank")
        @Expose
        private Object knowledgeRank;
        @SerializedName("reputation")
        @Expose
        private Integer reputation;
        @SerializedName("reputation_rank")
        @Expose
        private Object reputationRank;
        @SerializedName("join_date")
        @Expose
        private String joinDate;
        @SerializedName("social_profiles")
        @Expose
        private List<Object> socialProfiles = null;
        @SerializedName("solved_steps_count")
        @Expose
        private Integer solvedStepsCount;
        @SerializedName("created_courses_count")
        @Expose
        private Integer createdCoursesCount;
        @SerializedName("created_lessons_count")
        @Expose
        private Integer createdLessonsCount;
        @SerializedName("issued_certificates_count")
        @Expose
        private Integer issuedCertificatesCount;
        @SerializedName("followers_count")
        @Expose
        private Integer followersCount;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Integer getProfile() {
            return profile;
        }

        public void setProfile(Integer profile) {
            this.profile = profile;
        }

        public Boolean getIsPrivate() {
            return isPrivate;
        }

        public void setIsPrivate(Boolean isPrivate) {
            this.isPrivate = isPrivate;
        }

        public Boolean getIsActive() {
            return isActive;
        }

        public void setIsActive(Boolean isActive) {
            this.isActive = isActive;
        }

        public Boolean getIsGuest() {
            return isGuest;
        }

        public void setIsGuest(Boolean isGuest) {
            this.isGuest = isGuest;
        }

        public Boolean getIsOrganization() {
            return isOrganization;
        }

        public void setIsOrganization(Boolean isOrganization) {
            this.isOrganization = isOrganization;
        }

        public String getShortBio() {
            return shortBio;
        }

        public void setShortBio(String shortBio) {
            this.shortBio = shortBio;
        }

        public String getDetails() {
            return details;
        }

        public void setDetails(String details) {
            this.details = details;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public String getFullName() {
            return fullName;
        }

        public void setFullName(String fullName) {
            this.fullName = fullName;
        }

        public String getAlias() {
            return alias;
        }

        public void setAlias(String alias) {
            this.alias = alias;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }

        public Object getCity() {
            return city;
        }

        public void setCity(Object city) {
            this.city = city;
        }

        public Integer getLevel() {
            return level;
        }

        public void setLevel(Integer level) {
            this.level = level;
        }

        public String getLevelTitle() {
            return levelTitle;
        }

        public void setLevelTitle(String levelTitle) {
            this.levelTitle = levelTitle;
        }

        public List<Object> getTagProgresses() {
            return tagProgresses;
        }

        public void setTagProgresses(List<Object> tagProgresses) {
            this.tagProgresses = tagProgresses;
        }

        public Integer getKnowledge() {
            return knowledge;
        }

        public void setKnowledge(Integer knowledge) {
            this.knowledge = knowledge;
        }

        public Object getKnowledgeRank() {
            return knowledgeRank;
        }

        public void setKnowledgeRank(Object knowledgeRank) {
            this.knowledgeRank = knowledgeRank;
        }

        public Integer getReputation() {
            return reputation;
        }

        public void setReputation(Integer reputation) {
            this.reputation = reputation;
        }

        public Object getReputationRank() {
            return reputationRank;
        }

        public void setReputationRank(Object reputationRank) {
            this.reputationRank = reputationRank;
        }

        public String getJoinDate() {
            return joinDate;
        }

        public void setJoinDate(String joinDate) {
            this.joinDate = joinDate;
        }

        public List<Object> getSocialProfiles() {
            return socialProfiles;
        }

        public void setSocialProfiles(List<Object> socialProfiles) {
            this.socialProfiles = socialProfiles;
        }

        public Integer getSolvedStepsCount() {
            return solvedStepsCount;
        }

        public void setSolvedStepsCount(Integer solvedStepsCount) {
            this.solvedStepsCount = solvedStepsCount;
        }

        public Integer getCreatedCoursesCount() {
            return createdCoursesCount;
        }

        public void setCreatedCoursesCount(Integer createdCoursesCount) {
            this.createdCoursesCount = createdCoursesCount;
        }

        public Integer getCreatedLessonsCount() {
            return createdLessonsCount;
        }

        public void setCreatedLessonsCount(Integer createdLessonsCount) {
            this.createdLessonsCount = createdLessonsCount;
        }

        public Integer getIssuedCertificatesCount() {
            return issuedCertificatesCount;
        }

        public void setIssuedCertificatesCount(Integer issuedCertificatesCount) {
            this.issuedCertificatesCount = issuedCertificatesCount;
        }

        public Integer getFollowersCount() {
            return followersCount;
        }

        public void setFollowersCount(Integer followersCount) {
            this.followersCount = followersCount;
        }

    }

}


