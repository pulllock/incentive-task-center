package fun.pullock.incentive.core.service;

import fun.pullock.incentive.core.manager.EventManager;
import fun.pullock.incentive.core.manager.TaskManager;
import fun.pullock.incentive.core.manager.UserTaskManager;
import fun.pullock.incentive.core.model.Task;
import fun.pullock.incentive.core.model.app.param.UserTaskPageReq;
import fun.pullock.incentive.core.model.app.result.UserTaskResp;
import fun.pullock.incentive.core.strategy.task.task.TaskTypeHandler;
import jakarta.annotation.Resource;
import fun.pullock.incentive.api.enums.TaskType;
import fun.pullock.incentive.api.model.common.PageQuery;
import fun.pullock.incentive.api.model.common.PageResult;
import fun.pullock.incentive.core.model.Event;
import fun.pullock.incentive.core.model.UserTask;
import fun.pullock.incentive.core.strategy.task.task.TaskTypeHandlerFactory;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService {

    private final static Logger LOGGER = LoggerFactory.getLogger(TaskService.class);

    @Resource
    private TaskManager taskManager;

    @Resource
    private UserTaskManager userTaskManager;

    @Resource
    private EventManager eventManager;

    @Resource
    private TaskTypeHandlerFactory taskTypeHandlerFactory;

    @Transactional
    public void executeTask(Long userId, String eventCode) {
        String source = "TEST-SOURCE";

        // 查询event，校验event状态
        Event event = eventManager.queryByCode(eventCode);

        // 查询event关联的有效的task，配置的时候需要保证每个事件只能有一个任务是有效的
        Task task = taskManager.queryByEventId(event.getId());

        TaskTypeHandler taskTypeHandler = taskTypeHandlerFactory.getTaskTypeHandler(TaskType.of(task.getType()));
        if (taskTypeHandler == null) {
            return;
        }

        taskTypeHandler.executeTask(userId, source, task);

        // 如果是可下发的任务，则给用户下发任务

        // 根据规则和日志查询任务是否已经完成

        // 任务已完成，记录执行日志，不需要发放奖励

        // 任务未完成，记录执行日志，记录发放奖励日志，进行奖励发放

    }



    public PageResult<UserTaskResp> pageQueryUserTasks(PageQuery<UserTaskPageReq> req) {
        PageResult<UserTaskResp> result = new PageResult<>();
        int count = userTaskManager.countUserTasks(req);
        result.setTotal(count);

        if (count == 0) {
            return result;
        }

        List<UserTask> tasks = userTaskManager.pageQueryUserTasks(req);
        if (CollectionUtils.isEmpty(tasks)) {
            return result;
        }

        result.setList(tasks.stream().map(this::toUserTaskResp).collect(Collectors.toList()));
        return result;
    }

    private UserTaskResp toUserTaskResp(UserTask source) {
        if (source == null) {
            return null;
        }

        UserTaskResp target = new UserTaskResp();
        // TODO
        return target;
    }
}
