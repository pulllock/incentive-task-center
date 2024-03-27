package fun.pullock.incentive.core.service;

import fun.pullock.incentive.core.model.reqeust.TriggerParam;
import fun.pullock.incentive.core.dao.mapper.CompleteRecordMapper;
import fun.pullock.incentive.core.dao.model.CompleteRecordDO;
import fun.pullock.incentive.core.model.dto.CompleteRecordDTO;
import fun.pullock.incentive.core.model.dto.TaskCompleteResult;
import fun.pullock.incentive.core.model.dto.TaskDTO;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompleteRecordService {

    @Resource
    private CompleteRecordMapper completeRecordMapper;

    public List<CompleteRecordDTO> queryByUserTask(Long userId, Long taskId) {
        List<CompleteRecordDO> records = completeRecordMapper.selectByUserTask(userId, taskId);
        return records.stream().map(this::toCompleteRecordDTO).collect(Collectors.toList());
    }

    public List<CompleteRecordDTO> queryByUserTaskDateRange(Long userId,
                                                            Long taskId,
                                                            LocalDate completeDateStart,
                                                            LocalDate completeDateEnd) {
        List<CompleteRecordDO> records = completeRecordMapper.selectByUserTaskDate(
                userId, taskId, completeDateStart, completeDateEnd
        );
        return records.stream().map(this::toCompleteRecordDTO).collect(Collectors.toList());
    }

    public boolean create(TriggerParam param, TaskDTO task, TaskCompleteResult result, int status, LocalDateTime now) {
        CompleteRecordDO record = new CompleteRecordDO();
        record.setCreateTime(LocalDateTime.now());
        record.setUpdateTime(record.getCreateTime());
        record.setUserId(param.getUserId());
        record.setTaskId(task.getId());
        record.setStatus(status);
        record.setCompleteDate(now.toLocalDate());
        record.setCompleteTime(now.toLocalTime());
        record.setSource(param.getSource());
        record.setUniqueSourceId(param.getUniqueSourceId());
        record.setAfterCompleteType(task.getAfterCompleteType());
        record.setAfterCompleteRule(task.getAfterCompleteRule());
        return completeRecordMapper.insertSelective(record) == 1;
    }

    private CompleteRecordDTO toCompleteRecordDTO(CompleteRecordDO source) {
        if (source == null) {
            return null;
        }

        CompleteRecordDTO target = new CompleteRecordDTO();
        BeanUtils.copyProperties(source, target);
        return target;
    }
}
