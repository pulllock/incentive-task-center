package fun.pullock.incentive.core.service;

import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.Expression;
import fun.pullock.incentive.api.model.TriggerParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class TaskService {

    private final static Logger LOGGER = LoggerFactory.getLogger(TaskService.class);

    public void trigger(TriggerParam param) {
        // 查询事件

        // 查询任务
        System.out.println(param);

        Map<String, Object> eventRuleData = param.getEventRuleData();
        String script = "value == \"true\"";
        Expression expression = AviatorEvaluator.getInstance().compile(script);
        Boolean result = (Boolean) expression.execute(eventRuleData);
        System.out.println(result);
    }
}
