package fun.pullock.activity.core.dao;

import fun.pullock.activity.core.model.app.param.UserTaskPageReq;
import jakarta.annotation.Resource;
import fun.pullock.activity.api.model.common.PageQuery;
import fun.pullock.activity.core.dao.mapper.ActivityUserTaskMapper;
import fun.pullock.activity.core.dao.model.ActivityUserTaskDO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ActivityUserTaskDao {

    @Resource
    private ActivityUserTaskMapper activityUserTaskMapper;

    public List<ActivityUserTaskDO> pageQueryUserTasks(PageQuery<UserTaskPageReq> req) {
        // TODO
        return null;
    }

    public int countUserTasks(PageQuery<UserTaskPageReq> req) {
        // TODO
        return 0;
    }
}
