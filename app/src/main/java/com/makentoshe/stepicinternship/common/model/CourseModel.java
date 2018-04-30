package com.makentoshe.stepicinternship.common.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Makentoshe on 24.04.2018.
 */

public class CourseModel {

    @SerializedName("meta")
    @Expose
    private Meta meta;
    @SerializedName("courses")
    @Expose
    private List<Course> courses = null;
    @SerializedName("enrollments")
    @Expose
    private List<Enrollment> enrollments = null;

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public List<Enrollment> getEnrollments() {
        return enrollments;
    }

    public void setEnrollments(List<Enrollment> enrollments) {
        this.enrollments = enrollments;
    }

    public class Actions {
    }

    public class Course implements Serializable{

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("summary")
        @Expose
        private String summary;
        @SerializedName("workload")
        @Expose
        private String workload;
        @SerializedName("cover")
        @Expose
        private String cover;
        @SerializedName("intro")
        @Expose
        private String intro;
        @SerializedName("course_format")
        @Expose
        private String courseFormat;
        @SerializedName("target_audience")
        @Expose
        private String targetAudience;
        @SerializedName("certificate_footer")
        @Expose
        private String certificateFooter;
        @SerializedName("certificate_cover_org")
        @Expose
        private String certificateCoverOrg;
        @SerializedName("is_certificate_auto_issued")
        @Expose
        private Boolean isCertificateAutoIssued;
        @SerializedName("certificate_regular_threshold")
        @Expose
        private Integer certificateRegularThreshold;
        @SerializedName("certificate_distinction_threshold")
        @Expose
        private Integer certificateDistinctionThreshold;
        @SerializedName("instructors")
        @Expose
        private List<Integer> instructors = null;
        @SerializedName("certificate")
        @Expose
        private String certificate;
        @SerializedName("requirements")
        @Expose
        private String requirements;
        @SerializedName("description")
        @Expose
        private String description;
        @SerializedName("sections")
        @Expose
        private List<Integer> sections = null;
        @SerializedName("total_units")
        @Expose
        private Integer totalUnits;
        @SerializedName("enrollment")
        @Expose
        private Integer enrollment;
        @SerializedName("is_favorite")
        @Expose
        private Boolean isFavorite;
        @SerializedName("actions")
        @Expose
        private transient Actions actions;
        @SerializedName("progress")
        @Expose
        private String progress;
        @SerializedName("first_lesson")
        @Expose
        private Integer firstLesson;
        @SerializedName("first_unit")
        @Expose
        private Integer firstUnit;
        @SerializedName("certificate_link")
        @Expose
        private Object certificateLink;
        @SerializedName("certificate_regular_link")
        @Expose
        private Object certificateRegularLink;
        @SerializedName("certificate_distinction_link")
        @Expose
        private Object certificateDistinctionLink;
        @SerializedName("schedule_link")
        @Expose
        private String scheduleLink;
        @SerializedName("schedule_long_link")
        @Expose
        private String scheduleLongLink;
        @SerializedName("first_deadline")
        @Expose
        private Object firstDeadline;
        @SerializedName("last_deadline")
        @Expose
        private Object lastDeadline;
        @SerializedName("subscriptions")
        @Expose
        private List<String> subscriptions = null;
        @SerializedName("announcements")
        @Expose
        private List<Integer> announcements = null;
        @SerializedName("is_contest")
        @Expose
        private Boolean isContest;
        @SerializedName("is_self_paced")
        @Expose
        private Boolean isSelfPaced;
        @SerializedName("is_adaptive")
        @Expose
        private Boolean isAdaptive;
        @SerializedName("is_idea_compatible")
        @Expose
        private Boolean isIdeaCompatible;
        @SerializedName("last_step")
        @Expose
        private String lastStep;
        @SerializedName("intro_video")
        @Expose
        private Object introVideo;
        @SerializedName("social_providers")
        @Expose
        private List<Object> socialProviders = null;
        @SerializedName("authors")
        @Expose
        private List<Integer> authors = null;
        @SerializedName("tags")
        @Expose
        private List<Integer> tags = null;
        @SerializedName("has_tutors")
        @Expose
        private Boolean hasTutors;
        @SerializedName("is_promoted")
        @Expose
        private Boolean isPromoted;
        @SerializedName("is_enabled")
        @Expose
        private Boolean isEnabled;
        @SerializedName("is_proctored")
        @Expose
        private Boolean isProctored;
        @SerializedName("proctor_url")
        @Expose
        private String proctorUrl;
        @SerializedName("review_summary")
        @Expose
        private Integer reviewSummary;
        @SerializedName("schedule_type")
        @Expose
        private String scheduleType;
        @SerializedName("certificates_count")
        @Expose
        private Integer certificatesCount;
        @SerializedName("learners_count")
        @Expose
        private Integer learnersCount;
        @SerializedName("time_to_complete")
        @Expose
        private Integer timeToComplete;
        @SerializedName("is_popular")
        @Expose
        private Boolean isPopular;
        @SerializedName("similar_courses")
        @Expose
        private List<Integer> similarCourses = null;
        @SerializedName("is_unsuitable")
        @Expose
        private Boolean isUnsuitable;
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
        @SerializedName("lti_consumer_key")
        @Expose
        private String ltiConsumerKey;
        @SerializedName("lti_secret_key")
        @Expose
        private String ltiSecretKey;

        private String author = null;

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getSummary() {
            return summary;
        }

        public void setSummary(String summary) {
            this.summary = summary;
        }

        public String getWorkload() {
            return workload;
        }

        public void setWorkload(String workload) {
            this.workload = workload;
        }

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }

        public String getIntro() {
            return intro;
        }

        public void setIntro(String intro) {
            this.intro = intro;
        }

        public String getCourseFormat() {
            return courseFormat;
        }

        public void setCourseFormat(String courseFormat) {
            this.courseFormat = courseFormat;
        }

        public String getTargetAudience() {
            return targetAudience;
        }

        public void setTargetAudience(String targetAudience) {
            this.targetAudience = targetAudience;
        }

        public String getCertificateFooter() {
            return certificateFooter;
        }

        public void setCertificateFooter(String certificateFooter) {
            this.certificateFooter = certificateFooter;
        }

        public String getCertificateCoverOrg() {
            return certificateCoverOrg;
        }

        public void setCertificateCoverOrg(String certificateCoverOrg) {
            this.certificateCoverOrg = certificateCoverOrg;
        }

        public Boolean getIsCertificateAutoIssued() {
            return isCertificateAutoIssued;
        }

        public void setIsCertificateAutoIssued(Boolean isCertificateAutoIssued) {
            this.isCertificateAutoIssued = isCertificateAutoIssued;
        }

        public Integer getCertificateRegularThreshold() {
            return certificateRegularThreshold;
        }

        public void setCertificateRegularThreshold(Integer certificateRegularThreshold) {
            this.certificateRegularThreshold = certificateRegularThreshold;
        }

        public Integer getCertificateDistinctionThreshold() {
            return certificateDistinctionThreshold;
        }

        public void setCertificateDistinctionThreshold(Integer certificateDistinctionThreshold) {
            this.certificateDistinctionThreshold = certificateDistinctionThreshold;
        }

        public List<Integer> getInstructors() {
            return instructors;
        }

        public void setInstructors(List<Integer> instructors) {
            this.instructors = instructors;
        }

        public String getCertificate() {
            return certificate;
        }

        public void setCertificate(String certificate) {
            this.certificate = certificate;
        }

        public String getRequirements() {
            return requirements;
        }

        public void setRequirements(String requirements) {
            this.requirements = requirements;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public List<Integer> getSections() {
            return sections;
        }

        public void setSections(List<Integer> sections) {
            this.sections = sections;
        }

        public Integer getTotalUnits() {
            return totalUnits;
        }

        public void setTotalUnits(Integer totalUnits) {
            this.totalUnits = totalUnits;
        }

        public Integer getEnrollment() {
            return enrollment;
        }

        public void setEnrollment(Integer enrollment) {
            this.enrollment = enrollment;
        }

        public Boolean getIsFavorite() {
            return isFavorite;
        }

        public void setIsFavorite(Boolean isFavorite) {
            this.isFavorite = isFavorite;
        }

        public Actions getActions() {
            return actions;
        }

        public void setActions(Actions actions) {
            this.actions = actions;
        }

        public String getProgress() {
            return progress;
        }

        public void setProgress(String progress) {
            this.progress = progress;
        }

        public Integer getFirstLesson() {
            return firstLesson;
        }

        public void setFirstLesson(Integer firstLesson) {
            this.firstLesson = firstLesson;
        }

        public Integer getFirstUnit() {
            return firstUnit;
        }

        public void setFirstUnit(Integer firstUnit) {
            this.firstUnit = firstUnit;
        }

        public Object getCertificateLink() {
            return certificateLink;
        }

        public void setCertificateLink(Object certificateLink) {
            this.certificateLink = certificateLink;
        }

        public Object getCertificateRegularLink() {
            return certificateRegularLink;
        }

        public void setCertificateRegularLink(Object certificateRegularLink) {
            this.certificateRegularLink = certificateRegularLink;
        }

        public Object getCertificateDistinctionLink() {
            return certificateDistinctionLink;
        }

        public void setCertificateDistinctionLink(Object certificateDistinctionLink) {
            this.certificateDistinctionLink = certificateDistinctionLink;
        }

        public String getScheduleLink() {
            return scheduleLink;
        }

        public void setScheduleLink(String scheduleLink) {
            this.scheduleLink = scheduleLink;
        }

        public String getScheduleLongLink() {
            return scheduleLongLink;
        }

        public void setScheduleLongLink(String scheduleLongLink) {
            this.scheduleLongLink = scheduleLongLink;
        }

        public Object getFirstDeadline() {
            return firstDeadline;
        }

        public void setFirstDeadline(Object firstDeadline) {
            this.firstDeadline = firstDeadline;
        }

        public Object getLastDeadline() {
            return lastDeadline;
        }

        public void setLastDeadline(Object lastDeadline) {
            this.lastDeadline = lastDeadline;
        }

        public List<String> getSubscriptions() {
            return subscriptions;
        }

        public void setSubscriptions(List<String> subscriptions) {
            this.subscriptions = subscriptions;
        }

        public List<Integer> getAnnouncements() {
            return announcements;
        }

        public void setAnnouncements(List<Integer> announcements) {
            this.announcements = announcements;
        }

        public Boolean getIsContest() {
            return isContest;
        }

        public void setIsContest(Boolean isContest) {
            this.isContest = isContest;
        }

        public Boolean getIsSelfPaced() {
            return isSelfPaced;
        }

        public void setIsSelfPaced(Boolean isSelfPaced) {
            this.isSelfPaced = isSelfPaced;
        }

        public Boolean getIsAdaptive() {
            return isAdaptive;
        }

        public void setIsAdaptive(Boolean isAdaptive) {
            this.isAdaptive = isAdaptive;
        }

        public Boolean getIsIdeaCompatible() {
            return isIdeaCompatible;
        }

        public void setIsIdeaCompatible(Boolean isIdeaCompatible) {
            this.isIdeaCompatible = isIdeaCompatible;
        }

        public String getLastStep() {
            return lastStep;
        }

        public void setLastStep(String lastStep) {
            this.lastStep = lastStep;
        }

        public Object getIntroVideo() {
            return introVideo;
        }

        public void setIntroVideo(Object introVideo) {
            this.introVideo = introVideo;
        }

        public List<Object> getSocialProviders() {
            return socialProviders;
        }

        public void setSocialProviders(List<Object> socialProviders) {
            this.socialProviders = socialProviders;
        }

        public List<Integer> getAuthors() {
            return authors;
        }

        public void setAuthors(List<Integer> authors) {
            this.authors = authors;
        }

        public List<Integer> getTags() {
            return tags;
        }

        public void setTags(List<Integer> tags) {
            this.tags = tags;
        }

        public Boolean getHasTutors() {
            return hasTutors;
        }

        public void setHasTutors(Boolean hasTutors) {
            this.hasTutors = hasTutors;
        }

        public Boolean getIsPromoted() {
            return isPromoted;
        }

        public void setIsPromoted(Boolean isPromoted) {
            this.isPromoted = isPromoted;
        }

        public Boolean getIsEnabled() {
            return isEnabled;
        }

        public void setIsEnabled(Boolean isEnabled) {
            this.isEnabled = isEnabled;
        }

        public Boolean getIsProctored() {
            return isProctored;
        }

        public void setIsProctored(Boolean isProctored) {
            this.isProctored = isProctored;
        }

        public String getProctorUrl() {
            return proctorUrl;
        }

        public void setProctorUrl(String proctorUrl) {
            this.proctorUrl = proctorUrl;
        }

        public Integer getReviewSummary() {
            return reviewSummary;
        }

        public void setReviewSummary(Integer reviewSummary) {
            this.reviewSummary = reviewSummary;
        }

        public String getScheduleType() {
            return scheduleType;
        }

        public void setScheduleType(String scheduleType) {
            this.scheduleType = scheduleType;
        }

        public Integer getCertificatesCount() {
            return certificatesCount;
        }

        public void setCertificatesCount(Integer certificatesCount) {
            this.certificatesCount = certificatesCount;
        }

        public Integer getLearnersCount() {
            return learnersCount;
        }

        public void setLearnersCount(Integer learnersCount) {
            this.learnersCount = learnersCount;
        }

        public Integer getTimeToComplete() {
            return timeToComplete;
        }

        public void setTimeToComplete(Integer timeToComplete) {
            this.timeToComplete = timeToComplete;
        }

        public Boolean getIsPopular() {
            return isPopular;
        }

        public void setIsPopular(Boolean isPopular) {
            this.isPopular = isPopular;
        }

        public List<Integer> getSimilarCourses() {
            return similarCourses;
        }

        public void setSimilarCourses(List<Integer> similarCourses) {
            this.similarCourses = similarCourses;
        }

        public Boolean getIsUnsuitable() {
            return isUnsuitable;
        }

        public void setIsUnsuitable(Boolean isUnsuitable) {
            this.isUnsuitable = isUnsuitable;
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

    public class Enrollment {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("course")
        @Expose
        private Integer course;

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
