package me.cxis.activity.core.dao;

import jakarta.annotation.Resource;
import me.cxis.activity.api.model.common.PageQuery;
import me.cxis.activity.core.dao.mapper.ActivityUserTaskMapper;
import me.cxis.activity.core.dao.model.ActivityUserTaskDO;
import me.cxis.activity.core.model.app.param.UserTaskPageReq;
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
