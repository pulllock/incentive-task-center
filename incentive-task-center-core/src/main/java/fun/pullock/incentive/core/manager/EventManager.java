package fun.pullock.incentive.core.manager;

import jakarta.annotation.Resource;
import fun.pullock.incentive.core.dao.EventDao;
import fun.pullock.incentive.core.dao.model.EventDO;
import fun.pullock.incentive.core.model.Event;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class EventManager {

    @Resource
    private EventDao eventDao;

    public Event queryByCode(String eventCode) {
        EventDO eventDO = eventDao.queryByCode(eventCode);
        if (eventCode == null) {
            return null;
        }
        return toEvent(eventDO);
    }

    private Event toEvent(EventDO source) {
        if (source == null) {
            return null;
        }

        Event target = new Event();
        // TODO
        BeanUtils.copyProperties(source, target);
        return target;
    }
}
