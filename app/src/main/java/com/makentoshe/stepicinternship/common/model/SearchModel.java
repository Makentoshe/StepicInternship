package com.makentoshe.stepicinternship.common.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Makentoshe on 22.04.2018.
 */

public class SearchModel {
    @SerializedName("meta")
    @Expose
    private Meta meta;
    @SerializedName("search-results")
    @Expose
    private List<SearchResult> searchResults = null;

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public List<SearchResult> getSearchResults() {
        return searchResults;
    }

    public void setSearchResults(List<SearchResult> searchResults) {
        this.searchResults = searchResults;
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

    public class SearchResult implements Serializable{

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("position")
        @Expose
        private Integer position;
        @SerializedName("score")
        @Expose
        private Double score;
        @SerializedName("target_id")
        @Expose
        private Integer targetId;
        @SerializedName("target_type")
        @Expose
        private String targetType;
        @SerializedName("course")
        @Expose
        private Integer course;
        @SerializedName("course_owner")
        @Expose
        private Integer courseOwner;
        @SerializedName("course_authors")
        @Expose
        private List<Integer> courseAuthors = null;
        @SerializedName("course_title")
        @Expose
        private String courseTitle;
        @SerializedName("course_slug")
        @Expose
        private String courseSlug;
        @SerializedName("course_cover")
        @Expose
        private String courseCover;
        @SerializedName("lesson")
        @Expose
        private Object lesson;
        @SerializedName("lesson_owner")
        @Expose
        private Object lessonOwner;
        @SerializedName("lesson_title")
        @Expose
        private Object lessonTitle;
        @SerializedName("lesson_slug")
        @Expose
        private Object lessonSlug;
        @SerializedName("lesson_cover_url")
        @Expose
        private Object lessonCoverUrl;
        @SerializedName("step")
        @Expose
        private Object step;
        @SerializedName("step_position")
        @Expose
        private Object stepPosition;
        @SerializedName("comment")
        @Expose
        private Object comment;
        @SerializedName("comment_parent")
        @Expose
        private Object commentParent;
        @SerializedName("comment_user")
        @Expose
        private Object commentUser;
        @SerializedName("comment_text")
        @Expose
        private Object commentText;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Integer getPosition() {
            return position;
        }

        public void setPosition(Integer position) {
            this.position = position;
        }

        public Double getScore() {
            return score;
        }

        public void setScore(Double score) {
            this.score = score;
        }

        public Integer getTargetId() {
            return targetId;
        }

        public void setTargetId(Integer targetId) {
            this.targetId = targetId;
        }

        public String getTargetType() {
            return targetType;
        }

        public void setTargetType(String targetType) {
            this.targetType = targetType;
        }

        public Integer getCourse() {
            return course;
        }

        public void setCourse(Integer course) {
            this.course = course;
        }

        public Integer getCourseOwner() {
            return courseOwner;
        }

        public void setCourseOwner(Integer courseOwner) {
            this.courseOwner = courseOwner;
        }

        public List<Integer> getCourseAuthors() {
            return courseAuthors;
        }

        public void setCourseAuthors(List<Integer> courseAuthors) {
            this.courseAuthors = courseAuthors;
        }

        public String getCourseTitle() {
            return courseTitle;
        }

        public void setCourseTitle(String courseTitle) {
            this.courseTitle = courseTitle;
        }

        public String getCourseSlug() {
            return courseSlug;
        }

        public void setCourseSlug(String courseSlug) {
            this.courseSlug = courseSlug;
        }

        public String getCourseCover() {
            return courseCover;
        }

        public void setCourseCover(String courseCover) {
            this.courseCover = courseCover;
        }

        public Object getLesson() {
            return lesson;
        }

        public void setLesson(Object lesson) {
            this.lesson = lesson;
        }

        public Object getLessonOwner() {
            return lessonOwner;
        }

        public void setLessonOwner(Object lessonOwner) {
            this.lessonOwner = lessonOwner;
        }

        public Object getLessonTitle() {
            return lessonTitle;
        }

        public void setLessonTitle(Object lessonTitle) {
            this.lessonTitle = lessonTitle;
        }

        public Object getLessonSlug() {
            return lessonSlug;
        }

        public void setLessonSlug(Object lessonSlug) {
            this.lessonSlug = lessonSlug;
        }

        public Object getLessonCoverUrl() {
            return lessonCoverUrl;
        }

        public void setLessonCoverUrl(Object lessonCoverUrl) {
            this.lessonCoverUrl = lessonCoverUrl;
        }

        public Object getStep() {
            return step;
        }

        public void setStep(Object step) {
            this.step = step;
        }

        public Object getStepPosition() {
            return stepPosition;
        }

        public void setStepPosition(Object stepPosition) {
            this.stepPosition = stepPosition;
        }

        public Object getComment() {
            return comment;
        }

        public void setComment(Object comment) {
            this.comment = comment;
        }

        public Object getCommentParent() {
            return commentParent;
        }

        public void setCommentParent(Object commentParent) {
            this.commentParent = commentParent;
        }

        public Object getCommentUser() {
            return commentUser;
        }

        public void setCommentUser(Object commentUser) {
            this.commentUser = commentUser;
        }

        public Object getCommentText() {
            return commentText;
        }

        public void setCommentText(Object commentText) {
            this.commentText = commentText;
        }
    }
}
