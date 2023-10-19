package fun.pullock.activity.core.model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class ActivityUserTask implements Serializable {

    private Long id;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private Long creatorId;

    private Long modifierId;

    private Boolean deleted;

    private Long version;

    private Long userId;

    private Long taskId;

    private Integer status;

    private LocalDateTime validTimeStart;

    private LocalDateTime validTimeEnd;

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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    @Override
    public String toString() {
        return "ActivityUserTask{" +
                "id=" + id +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", creatorId=" + creatorId +
                ", modifierId=" + modifierId +
                ", deleted=" + deleted +
                ", version=" + version +
                ", userId=" + userId +
                ", taskId=" + taskId +
                ", status=" + status +
                ", validTimeStart=" + validTimeStart +
                ", validTimeEnd=" + validTimeEnd +
                '}';
    }
}
