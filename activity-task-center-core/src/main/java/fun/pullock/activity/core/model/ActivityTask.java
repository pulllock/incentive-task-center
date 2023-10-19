package fun.pullock.activity.core.model;

import java.time.LocalDateTime;

public class ActivityTask {

    private Long id;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private Long creatorId;

    private Long modifierId;

    private Boolean deleted;

    private Long version;

    private String code;

    private Long eventId;

    private String name;

    private String description;

    private Boolean simpleType;

    private Integer type;

    private Integer completeType;

    private Long parentId;

    private Integer status;

    private Integer category;

    private Integer validPeriodType;

    private LocalDateTime validTimeStart;

    private LocalDateTime validTimeEnd;

    private Long validDays;

    private Integer ruleType;

    private Integer periodType;

    private Integer periodUnit;

    private Long periodInterval;

    private Long periodTimes;

    private String url;

    private Integer urlTargetType;

    private Integer rewardType;

    private Long rewardNumber;

    private Integer rewardProvideMode;

    private Long goal;

    private Long extraRewardNumber;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }

    public Long getModifierId() {
        return modifierId;
    }

    public void setModifierId(Long modifierId) {
        this.modifierId = modifierId;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getSimpleType() {
        return simpleType;
    }

    public void setSimpleType(Boolean simpleType) {
        this.simpleType = simpleType;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getCompleteType() {
        return completeType;
    }

    public void setCompleteType(Integer completeType) {
        this.completeType = completeType;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public Integer getValidPeriodType() {
        return validPeriodType;
    }

    public void setValidPeriodType(Integer validPeriodType) {
        this.validPeriodType = validPeriodType;
    }

    public LocalDateTime getValidTimeStart() {
        return validTimeStart;
    }

    public void setValidTimeStart(LocalDateTime validTimeStart) {
        this.validTimeStart = validTimeStart;
    }

    public LocalDateTime getValidTimeEnd() {
        return validTimeEnd;
    }

    public void setValidTimeEnd(LocalDateTime validTimeEnd) {
        this.validTimeEnd = validTimeEnd;
    }

    public Long getValidDays() {
        return validDays;
    }

    public void setValidDays(Long validDays) {
        this.validDays = validDays;
    }

    public Integer getRuleType() {
        return ruleType;
    }

    public void setRuleType(Integer ruleType) {
        this.ruleType = ruleType;
    }

    public Integer getPeriodType() {
        return periodType;
    }

    public void setPeriodType(Integer periodType) {
        this.periodType = periodType;
    }

    public Integer getPeriodUnit() {
        return periodUnit;
    }

    public void setPeriodUnit(Integer periodUnit) {
        this.periodUnit = periodUnit;
    }

    public Long getPeriodInterval() {
        return periodInterval;
    }

    public void setPeriodInterval(Long periodInterval) {
        this.periodInterval = periodInterval;
    }

    public Long getPeriodTimes() {
        return periodTimes;
    }

    public void setPeriodTimes(Long periodTimes) {
        this.periodTimes = periodTimes;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getUrlTargetType() {
        return urlTargetType;
    }

    public void setUrlTargetType(Integer urlTargetType) {
        this.urlTargetType = urlTargetType;
    }

    public Integer getRewardType() {
        return rewardType;
    }

    public void setRewardType(Integer rewardType) {
        this.rewardType = rewardType;
    }

    public Long getRewardNumber() {
        return rewardNumber;
    }

    public void setRewardNumber(Long rewardNumber) {
        this.rewardNumber = rewardNumber;
    }

    public Integer getRewardProvideMode() {
        return rewardProvideMode;
    }

    public void setRewardProvideMode(Integer rewardProvideMode) {
        this.rewardProvideMode = rewardProvideMode;
    }

    public Long getGoal() {
        return goal;
    }

    public void setGoal(Long goal) {
        this.goal = goal;
    }

    public Long getExtraRewardNumber() {
        return extraRewardNumber;
    }

    public void setExtraRewardNumber(Long extraRewardNumber) {
        this.extraRewardNumber = extraRewardNumber;
    }

    @Override
    public String toString() {
        return "ActivityTask{" +
                "id=" + id +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", creatorId=" + creatorId +
                ", modifierId=" + modifierId +
                ", deleted=" + deleted +
                ", version=" + version +
                ", code='" + code + '\'' +
                ", eventId=" + eventId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", simpleType=" + simpleType +
                ", type=" + type +
                ", completeType=" + completeType +
                ", parentId=" + parentId +
                ", status=" + status +
                ", category=" + category +
                ", validPeriodType=" + validPeriodType +
                ", validTimeStart=" + validTimeStart +
                ", validTimeEnd=" + validTimeEnd +
                ", validDays=" + validDays +
                ", ruleType=" + ruleType +
                ", periodType=" + periodType +
                ", periodUnit=" + periodUnit +
                ", periodInterval=" + periodInterval +
                ", periodTimes=" + periodTimes +
                ", url='" + url + '\'' +
                ", urlTargetType=" + urlTargetType +
                ", rewardType=" + rewardType +
                ", rewardNumber=" + rewardNumber +
                ", rewardProvideMode=" + rewardProvideMode +
                ", goal=" + goal +
                ", extraRewardNumber=" + extraRewardNumber +
                '}';
    }
}