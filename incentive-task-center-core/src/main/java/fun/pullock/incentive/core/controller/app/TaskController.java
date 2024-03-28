package fun.pullock.incentive.core.controller.app;

import fun.pullock.incentive.core.model.app.vo.UserTaskVO;
import fun.pullock.incentive.core.model.reqeust.TriggerParam;
import fun.pullock.incentive.core.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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


    @Operation(summary = "任务列表")
    @GetMapping("/list")
    public List<UserTaskVO> list() {
        Long userId = 1L;
        return taskService.list(userId);
    }

    @Operation(
            summary = "领取奖励",
            parameters = {
                    @Parameter(description = "任务ID", required = true)
            }
    )
    @GetMapping("/claim")
    public void claim(@RequestParam("id") Long id) {
        Long userId = 1L;
        taskService.claim(userId, id);
    }
}
