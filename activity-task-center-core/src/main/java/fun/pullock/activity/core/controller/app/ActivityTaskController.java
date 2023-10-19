package fun.pullock.activity.core.controller.app;

import fun.pullock.activity.core.model.app.param.UserTaskPageReq;
import fun.pullock.activity.core.model.app.result.UserTaskResp;
import jakarta.annotation.Resource;
import fun.pullock.activity.api.model.common.PageQuery;
import fun.pullock.activity.api.model.common.PageResult;
import fun.pullock.activity.api.model.common.Result;
import fun.pullock.activity.core.service.ActivityTaskService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/app/activity/task")
public class ActivityTaskController {

    @Resource
    private ActivityTaskService activityTaskService;

    @GetMapping("test-execute")
    private Result<Boolean> testExecute() {
        Long userId = 100000L;
        String eventCode = "start_read";
        activityTaskService.executeActivityTask(userId, eventCode);
        return Result.success(true);
    }

    /**
     * 查询所有任务，用于任务广场，任务的类型为全局任务
     */
    @GetMapping("/all-tasks")
    public void queryAllTasks() {

    }

    /**
     * 分页查询用户任务
     * @param req
     * @return
     */
    @PostMapping("/user-tasks")
    public Result<PageResult<UserTaskResp>> queryUserTasks(@RequestBody PageQuery<UserTaskPageReq> req) {
        // session中获取userId
        Long userId = 100000L;

        if (req == null) {
            req = new PageQuery<>();
        }

        // page number默认1
        if (req.getPageNo() <= 0) {
            req.setPageNo(1);
        }

        // page size默认10
        if (req.getPageSize() <= 0) {
            req.setPageSize(10);
        }

        if (req.getQuery() == null) {
            req.setQuery(new UserTaskPageReq());
        }

        req.getQuery().setUserId(userId);
        PageResult<UserTaskResp> tasks = activityTaskService.pageQueryUserTasks(req);
        return Result.success(tasks);
    }
}
