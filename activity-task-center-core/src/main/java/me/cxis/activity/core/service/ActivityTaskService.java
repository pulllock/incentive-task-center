package me.cxis.activity.core.service;

import jakarta.annotation.Resource;
import me.cxis.activity.api.enums.TaskType;
import me.cxis.activity.api.model.common.PageQuery;
import me.cxis.activity.api.model.common.PageResult;
import me.cxis.activity.core.manager.ActivityEventManager;
import me.cxis.activity.core.manager.ActivityTaskManager;
import me.cxis.activity.core.manager.ActivityUserTaskManager;
import me.cxis.activity.core.model.ActivityEvent;
import me.cxis.activity.core.model.ActivityTask;
import me.cxis.activity.core.model.ActivityUserTask;
import me.cxis.activity.core.model.app.param.UserTaskPageReq;
import me.cxis.activity.core.model.app.result.UserTaskResp;
import me.cxis.activity.core.strategy.task.task.TaskTypeHandler;
import me.cxis.activity.core.strategy.task.task.TaskTypeHandlerFactory;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ActivityTaskService {

    private final static Logger LOGGER = LoggerFactory.getLogger(ActivityTaskService.class);

    @Resource
    private ActivityTaskManager activityTaskManager;

    @Resource
    private ActivityUserTaskManager activityUserTaskManager;

    @Resource
    private ActivityEventManager activityEventManager;

    @Resource
    private TaskTypeHandlerFactory taskTypeHandlerFactory;

    @Transactional
    public void executeActivityTask(Long userId, String eventCode) {
        String source = "TEST-SOURCE";

        // 查询event，校验event状态
        ActivityEvent activityEvent = activityEventManager.queryByCode(eventCode);

        // 查询event关联的有效的task，配置的时候需要保证每个事件只能有一个任务是有效的
        ActivityTask task = activityTaskManager.queryByEventId(activityEvent.getId());

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
        int count = activityUserTaskManager.countUserTasks(req);
        result.setTotal(count);

        if (count == 0) {
            return result;
        }

        List<ActivityUserTask> tasks = activityUserTaskManager.pageQueryUserTasks(req);
        if (CollectionUtils.isEmpty(tasks)) {
            return result;
        }

        result.setList(tasks.stream().map(this::toUserTaskResp).collect(Collectors.toList()));
        return result;
    }

    private UserTaskResp toUserTaskResp(ActivityUserTask source) {
        if (source == null) {
            return null;
        }

        UserTaskResp target = new UserTaskResp();
        // TODO
        return target;
    }
}
