package fun.pullock.incentive.core.service;

import fun.pullock.incentive.core.dao.mapper.TriggerLogMapper;
import fun.pullock.incentive.core.dao.model.TriggerLogDO;
import fun.pullock.incentive.core.model.dto.TriggerLogDTO;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

@Service
public class TriggerLogService {

    @Resource
    private TriggerLogMapper triggerLogMapper;

    public TriggerLogDTO queryByUniqueKey(Long userId, String source, String uniqueSourceId) {
        TriggerLogDO triggerLogDO = triggerLogMapper.selectByUniqueKey(userId, source, uniqueSourceId);
        return toTriggerLogDTO(triggerLogDO);
    }

    public boolean create(TriggerLogDTO triggerLog) {
        try {
            // TODO
        } catch (DuplicateKeyException e) {
            // TODO 抛异常
        }
        return false;
    }

    private TriggerLogDTO toTriggerLogDTO(TriggerLogDO source) {
        if (source == null) {
            return null;
        }

        TriggerLogDTO target = new TriggerLogDTO();
        BeanUtils.copyProperties(source, target);
        return target;
    }
}
