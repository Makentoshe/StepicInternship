package com.makentoshe.stepicinternship.common.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Makentoshe on 26.04.2018.
 */

public class StepModel {

    @SerializedName("meta")
    @Expose
    private Meta meta;
    @SerializedName("steps")
    @Expose
    private List<Step> steps = null;

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public List<Step> getSteps() {
        return steps;
    }

    public void setSteps(List<Step> steps) {
        this.steps = steps;
    }


    public class Actions {
        @SerializedName("submit")
        @Expose
        private String submit;

        public String getSubmit() {
            return submit;
        }

        public void setSubmit(String submit) {
            this.submit = submit;
        }
    }

    public class Block {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("text")
        @Expose
        private String text;
        @SerializedName("video")
        @Expose
        private Video video;
        @SerializedName("animation")
        @Expose
        private Object animation;
        @SerializedName("options")
        @Expose
        private Options options;
        @SerializedName("subtitle_files")
        @Expose
        private List<Object> subtitleFiles = null;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public Video getVideo() {
            return video;
        }

        public void setVideo(Video video) {
            this.video = video;
        }

        public Object getAnimation() {
            return animation;
        }

        public void setAnimation(Object animation) {
            this.animation = animation;
        }

        public Options getOptions() {
            return options;
        }

        public void setOptions(Options options) {
            this.options = options;
        }

        public List<Object> getSubtitleFiles() {
            return subtitleFiles;
        }

        public void setSubtitleFiles(List<Object> subtitleFiles) {
            this.subtitleFiles = subtitleFiles;
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

    public class Options {

        @SerializedName("execution_time_limit")
        @Expose
        private Integer executionTimeLimit;
        @SerializedName("code_templates")
        @Expose
        private CodeTemplates codeTemplates;
        @SerializedName("execution_memory_limit")
        @Expose
        private Integer executionMemoryLimit;
        @SerializedName("samples")
        @Expose
        private List<List<String>> samples = null;
        @SerializedName("limits")
        @Expose
        private Limits limits;

        public Integer getExecutionTimeLimit() {
            return executionTimeLimit;
        }

        public void setExecutionTimeLimit(Integer executionTimeLimit) {
            this.executionTimeLimit = executionTimeLimit;
        }

        public CodeTemplates getCodeTemplates() {
            return codeTemplates;
        }

        public void setCodeTemplates(CodeTemplates codeTemplates) {
            this.codeTemplates = codeTemplates;
        }

        public Integer getExecutionMemoryLimit() {
            return executionMemoryLimit;
        }

        public void setExecutionMemoryLimit(Integer executionMemoryLimit) {
            this.executionMemoryLimit = executionMemoryLimit;
        }

        public List<List<String>> getSamples() {
            return samples;
        }

        public void setSamples(List<List<String>> samples) {
            this.samples = samples;
        }

        public Limits getLimits() {
            return limits;
        }

        public void setLimits(Limits limits) {
            this.limits = limits;
        }

    }

    public class Java8 {

        @SerializedName("time")
        @Expose
        private Integer time;
        @SerializedName("memory")
        @Expose
        private Integer memory;

        public Integer getTime() {
            return time;
        }

        public void setTime(Integer time) {
            this.time = time;
        }

        public Integer getMemory() {
            return memory;
        }

        public void setMemory(Integer memory) {
            this.memory = memory;
        }

    }

    public class CodeTemplates {

        @SerializedName("java8")
        @Expose
        private String java8;

        public String getJava8() {
            return java8;
        }

        public void setJava8(String java8) {
            this.java8 = java8;
        }

    }

    public class Limits {

        @SerializedName("java8")
        @Expose
        private Java8 java8;

        public Java8 getJava8() {
            return java8;
        }

        public void setJava8(Java8 java8) {
            this.java8 = java8;
        }

    }

    public class Step {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("lesson")
        @Expose
        private Integer lesson;
        @SerializedName("position")
        @Expose
        private Integer position;
        @SerializedName("status")
        @Expose
        private String status;
        @SerializedName("block")
        @Expose
        private Block block;
        @SerializedName("actions")
        @Expose
        private Actions actions;
        @SerializedName("progress")
        @Expose
        private String progress;
        @SerializedName("subscriptions")
        @Expose
        private List<String> subscriptions = null;
        @SerializedName("instruction")
        @Expose
        private Object instruction;
        @SerializedName("session")
        @Expose
        private Object session;
        @SerializedName("instruction_type")
        @Expose
        private Object instructionType;
        @SerializedName("viewed_by")
        @Expose
        private Integer viewedBy;
        @SerializedName("passed_by")
        @Expose
        private Integer passedBy;
        @SerializedName("correct_ratio")
        @Expose
        private Object correctRatio;
        @SerializedName("worth")
        @Expose
        private Integer worth;
        @SerializedName("is_solutions_unlocked")
        @Expose
        private Boolean isSolutionsUnlocked;
        @SerializedName("solutions_unlocked_attempts")
        @Expose
        private Integer solutionsUnlockedAttempts;
        @SerializedName("has_submissions_restrictions")
        @Expose
        private Boolean hasSubmissionsRestrictions;
        @SerializedName("max_submissions_count")
        @Expose
        private Integer maxSubmissionsCount;
        @SerializedName("variation")
        @Expose
        private Integer variation;
        @SerializedName("variations_count")
        @Expose
        private Integer variationsCount;
        @SerializedName("create_date")
        @Expose
        private String createDate;
        @SerializedName("update_date")
        @Expose
        private String updateDate;
        @SerializedName("discussions_count")
        @Expose
        private Integer discussionsCount;
        @SerializedName("discussion_proxy")
        @Expose
        private String discussionProxy;
        @SerializedName("discussion_threads")
        @Expose
        private List<String> discussionThreads = null;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Integer getLesson() {
            return lesson;
        }

        public void setLesson(Integer lesson) {
            this.lesson = lesson;
        }

        public Integer getPosition() {
            return position;
        }

        public void setPosition(Integer position) {
            this.position = position;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public Block getBlock() {
            return block;
        }

        public void setBlock(Block block) {
            this.block = block;
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

        public List<String> getSubscriptions() {
            return subscriptions;
        }

        public void setSubscriptions(List<String> subscriptions) {
            this.subscriptions = subscriptions;
        }

        public Object getInstruction() {
            return instruction;
        }

        public void setInstruction(Object instruction) {
            this.instruction = instruction;
        }

        public Object getSession() {
            return session;
        }

        public void setSession(Object session) {
            this.session = session;
        }

        public Object getInstructionType() {
            return instructionType;
        }

        public void setInstructionType(Object instructionType) {
            this.instructionType = instructionType;
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

        public Object getCorrectRatio() {
            return correctRatio;
        }

        public void setCorrectRatio(Object correctRatio) {
            this.correctRatio = correctRatio;
        }

        public Integer getWorth() {
            return worth;
        }

        public void setWorth(Integer worth) {
            this.worth = worth;
        }

        public Boolean getIsSolutionsUnlocked() {
            return isSolutionsUnlocked;
        }

        public void setIsSolutionsUnlocked(Boolean isSolutionsUnlocked) {
            this.isSolutionsUnlocked = isSolutionsUnlocked;
        }

        public Integer getSolutionsUnlockedAttempts() {
            return solutionsUnlockedAttempts;
        }

        public void setSolutionsUnlockedAttempts(Integer solutionsUnlockedAttempts) {
            this.solutionsUnlockedAttempts = solutionsUnlockedAttempts;
        }

        public Boolean getHasSubmissionsRestrictions() {
            return hasSubmissionsRestrictions;
        }

        public void setHasSubmissionsRestrictions(Boolean hasSubmissionsRestrictions) {
            this.hasSubmissionsRestrictions = hasSubmissionsRestrictions;
        }

        public Integer getMaxSubmissionsCount() {
            return maxSubmissionsCount;
        }

        public void setMaxSubmissionsCount(Integer maxSubmissionsCount) {
            this.maxSubmissionsCount = maxSubmissionsCount;
        }

        public Integer getVariation() {
            return variation;
        }

        public void setVariation(Integer variation) {
            this.variation = variation;
        }

        public Integer getVariationsCount() {
            return variationsCount;
        }

        public void setVariationsCount(Integer variationsCount) {
            this.variationsCount = variationsCount;
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

    }

    public class Url {

        @SerializedName("url")
        @Expose
        private String url;
        @SerializedName("quality")
        @Expose
        private String quality;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getQuality() {
            return quality;
        }

        public void setQuality(String quality) {
            this.quality = quality;
        }

    }

    public class Video {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("thumbnail")
        @Expose
        private String thumbnail;
        @SerializedName("urls")
        @Expose
        private List<Url> urls = null;
        @SerializedName("duration")
        @Expose
        private Integer duration;
        @SerializedName("status")
        @Expose
        private String status;
        @SerializedName("upload_date")
        @Expose
        private String uploadDate;
        @SerializedName("filename")
        @Expose
        private String filename;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getThumbnail() {
            return thumbnail;
        }

        public void setThumbnail(String thumbnail) {
            this.thumbnail = thumbnail;
        }

        public List<Url> getUrls() {
            return urls;
        }

        public void setUrls(List<Url> urls) {
            this.urls = urls;
        }

        public Integer getDuration() {
            return duration;
        }

        public void setDuration(Integer duration) {
            this.duration = duration;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getUploadDate() {
            return uploadDate;
        }

        public void setUploadDate(String uploadDate) {
            this.uploadDate = uploadDate;
        }

        public String getFilename() {
            return filename;
        }

        public void setFilename(String filename) {
            this.filename = filename;
        }

    }

}
