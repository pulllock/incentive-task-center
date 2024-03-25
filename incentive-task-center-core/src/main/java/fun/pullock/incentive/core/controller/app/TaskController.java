package fun.pullock.incentive.core.controller.app;

import fun.pullock.incentive.core.service.TaskService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/app/task")
public class TaskController {

    @Resource
    private TaskService taskService;
}
