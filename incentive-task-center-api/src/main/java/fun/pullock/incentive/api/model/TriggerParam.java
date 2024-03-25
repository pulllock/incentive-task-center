package fun.pullock.incentive.api.model;

import java.util.Map;

/**
 * 任务触发的参数
 */
public class TriggerParam {

    private Long userId;

    private String eventCode;

    private Map<String, Object> eventRuleData;

    private String source;

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
