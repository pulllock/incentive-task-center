package fun.pullock.incentive.core.service;

import fun.pullock.general.model.ServiceException;
import fun.pullock.incentive.core.dao.mapper.EventMapper;
import fun.pullock.incentive.core.dao.model.EventDO;
import fun.pullock.incentive.core.enums.ErrorCode;
import fun.pullock.incentive.core.model.dto.EventDTO;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class EventService {

    @Resource
    private EventMapper eventMapper;

    public EventDTO queryByCode(String code) {
        if (StringUtils.isEmpty(code)) {
            throw new ServiceException(ErrorCode.PARAM_ERROR);
        }

        EventDO eventDO = eventMapper.selectByCode(code);
        return toEventDTO(eventDO);
    }

    private EventDTO toEventDTO(EventDO source) {
        if (source == null) {
            return null;
        }

        EventDTO target = new EventDTO();
        BeanUtils.copyProperties(source, target);
        return target;
    }
}
