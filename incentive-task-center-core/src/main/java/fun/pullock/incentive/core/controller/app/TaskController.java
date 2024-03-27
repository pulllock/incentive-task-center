package fun.pullock.incentive.core.controller.app;

import fun.pullock.incentive.api.model.reqeust.TriggerParam;
import fun.pullock.incentive.core.service.TaskService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/app/task")
public class TaskController {

    @Resource
    private TaskService taskService;

    @PostMapping("/trigger")
    public void trigger(@RequestBody TriggerParam param) {
        taskService.trigger(param);
    }
}
