package fun.pullock.incentive.core.service;

import fun.pullock.general.model.ServiceException;
import fun.pullock.incentive.core.dao.mapper.TriggerLogMapper;
import fun.pullock.incentive.core.dao.model.TriggerLogDO;
import fun.pullock.incentive.core.enums.ErrorCode;
import fun.pullock.incentive.core.model.dto.TriggerLogDTO;
import fun.pullock.incentive.core.model.dto.TriggerLogProcessResultDTO;
import fun.pullock.starter.json.Json;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static fun.pullock.incentive.core.enums.TriggerLogStatus.PROCESSING;

@Service
public class TriggerLogService {

    @Resource
    private TriggerLogMapper triggerLogMapper;

    public TriggerLogDTO queryByUniqueKey(Long userId, String source, String uniqueSourceId) {
        TriggerLogDO triggerLogDO = triggerLogMapper.selectByUniqueKey(userId, source, uniqueSourceId);
        return toTriggerLogDTO(triggerLogDO);
    }

    public void create(TriggerLogDTO triggerLog) {
        try {
            TriggerLogDO triggerLogDO = new TriggerLogDO();
            triggerLogDO.setCreateTime(triggerLog.getCreateTime());
            triggerLogDO.setUpdateTime(triggerLogDO.getCreateTime());
            triggerLogDO.setUserId(triggerLog.getUserId());
            triggerLogDO.setEventCode(triggerLog.getEventCode());
            triggerLogDO.setStatus(PROCESSING.getStatus());
            triggerLogDO.setSource(triggerLog.getSource());
            triggerLogDO.setUniqueSourceId(triggerLog.getUniqueSourceId());
            triggerLogMapper.insertSelective(triggerLogDO);
            triggerLog.setId(triggerLogDO.getId());
        } catch (DuplicateKeyException e) {
            throw new ServiceException(ErrorCode.CONCURRENCY_ERROR);
        }
    }

    public boolean updateStatus(int newStatus, int oldStatus, Long id) {
        return triggerLogMapper.updateStatus(newStatus, oldStatus, id);
    }

    public boolean failed(Long id, int errorCode, String errorMsg) {
        TriggerLogProcessResultDTO processResult = new TriggerLogProcessResultDTO(errorCode, errorMsg);
        List<TriggerLogProcessResultDTO> results = new ArrayList<>();
        results.add(processResult);
        return triggerLogMapper.failed(id, Json.toJson(results));
    }

    public boolean updateResult(Long id, Integer oldStatus, int newStatus, List<TriggerLogProcessResultDTO> processResults) {
        return triggerLogMapper.updateResult(id, oldStatus, newStatus, processResults) == 1;
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
