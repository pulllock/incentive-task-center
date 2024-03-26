package fun.pullock.incentive.core.service;

import fun.pullock.incentive.core.model.dto.TriggerLogDTO;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

@Service
public class TriggerLogService {

    public TriggerLogDTO queryByUniqueKey(Long userId, String source, String uniqueSourceId) {
        // TODO 查询
        return new TriggerLogDTO();
    }

    public boolean create(TriggerLogDTO triggerLog) {
        try {
            // TODO
        } catch (DuplicateKeyException e) {
            // TODO 抛异常
        }
        return false;
    }
}
