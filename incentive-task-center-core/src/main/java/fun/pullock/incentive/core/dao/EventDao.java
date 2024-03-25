package fun.pullock.incentive.core.dao;

import jakarta.annotation.Resource;
import fun.pullock.incentive.core.dao.mapper.EventMapper;
import fun.pullock.incentive.core.dao.model.EventDO;
import org.springframework.stereotype.Repository;

@Repository
public class EventDao {

    @Resource
    private EventMapper eventMapper;

    public EventDO queryByCode(String code) {
        return eventMapper.selectByCode(code);
    }
}
