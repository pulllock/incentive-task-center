package fun.pullock.incentive.core.controller.app;

import fun.pullock.incentive.core.model.reqeust.TriggerParam;
import fun.pullock.incentive.core.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "激励任务APP端接口")
@RestController
@RequestMapping("/app/task")
public class TaskController {

    @Resource
    private TaskService taskService;

    @Operation(
            summary = "触发激励任务",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "触发激励任务参数", required = true
            )
    )
    @PostMapping("/trigger")
    public void trigger(@RequestBody TriggerParam param) {
        taskService.trigger(param);
    }
}
