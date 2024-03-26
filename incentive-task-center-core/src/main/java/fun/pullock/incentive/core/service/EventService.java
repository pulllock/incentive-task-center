package fun.pullock.incentive.core.service;

import fun.pullock.incentive.core.model.dto.EventDTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class EventService {

    public EventDTO queryByCode(String code) {
        if (StringUtils.isEmpty(code)) {
            // TODO 抛异常
            return null;
        }

        // TODO 查询事件

        return new EventDTO();
    }
}
