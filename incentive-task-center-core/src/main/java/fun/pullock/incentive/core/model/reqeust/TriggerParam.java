package fun.pullock.incentive.core.model.reqeust;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * 任务触发的参数
 */
@Schema(title = "任务触发的参数")
public class TriggerParam {

    @Schema(title = "用户ID")
    private Long userId;

    @Schema(title = "事件编码")
    private String eventCode;

    @Schema(title = "事件规则数据")
    private Map<String, Object> eventRuleData;

    @Schema(title = "事件发生的时间", description = "格式：yyyy-MM-dd HH:mm:ss，默认为系统当前时间")
    private LocalDateTime eventTime = LocalDateTime.now();

    @Schema(title = "来源")
    private String source;

    @Schema(title = "唯一来源ID")
    private String uniqueSourceId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getEventCode() {
        return eventCode;
    }

    public void setEventCode(String eventCode) {
        this.eventCode = eventCode;
    }

    public Map<String, Object> getEventRuleData() {
        return eventRuleData;
    }

    public void setEventRuleData(Map<String, Object> eventRuleData) {
        this.eventRuleData = eventRuleData;
    }

    public LocalDateTime getEventTime() {
        return eventTime;
    }

    public void setEventTime(LocalDateTime eventTime) {
        this.eventTime = eventTime;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getUniqueSourceId() {
        return uniqueSourceId;
    }

    public void setUniqueSourceId(String uniqueSourceId) {
        this.uniqueSourceId = uniqueSourceId;
    }

    @Override
    public String toString() {
        return "TriggerParam{" +
                "userId=" + userId +
                ", eventCode='" + eventCode + '\'' +
                ", eventRuleData=" + eventRuleData +
                ", source='" + source + '\'' +
                ", uniqueSourceId='" + uniqueSourceId + '\'' +
                '}';
    }
}
