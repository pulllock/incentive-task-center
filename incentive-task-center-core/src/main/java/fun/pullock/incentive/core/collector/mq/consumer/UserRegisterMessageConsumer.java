package fun.pullock.incentive.core.collector.mq.consumer;

import fun.pullock.incentive.api.model.reqeust.TriggerParam;
import fun.pullock.incentive.core.service.TaskService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

/**
 * 用户注册消息
 */
@Component
public class UserRegisterMessageConsumer {

    @Resource
    private TaskService taskService;

    private void consume() {
        TriggerParam param = new TriggerParam();
        param.setUserId(1L);
        param.setEventCode("user_register");
        param.setSource("user");
        param.setUniqueSourceId(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        Map<String, Object> eventRuleData = new HashMap<>();
        eventRuleData.put("value", true);
        param.setEventRuleData(eventRuleData);

        taskService.trigger(param);
    }

}
