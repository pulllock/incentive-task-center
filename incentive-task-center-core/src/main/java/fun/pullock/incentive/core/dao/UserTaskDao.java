package fun.pullock.incentive.core.dao;

import fun.pullock.incentive.core.model.app.param.UserTaskPageReq;
import jakarta.annotation.Resource;
import fun.pullock.incentive.api.model.common.PageQuery;
import fun.pullock.incentive.core.dao.mapper.UserTaskMapper;
import fun.pullock.incentive.core.dao.model.UserTaskDO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserTaskDao {

    @Resource
    private UserTaskMapper userTaskMapper;

    public List<UserTaskDO> pageQueryUserTasks(PageQuery<UserTaskPageReq> req) {
        // TODO
        return null;
    }

    public int countUserTasks(PageQuery<UserTaskPageReq> req) {
        // TODO
        return 0;
    }
}
