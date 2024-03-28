package fun.pullock.incentive.core.manager;

import fun.pullock.incentive.core.dao.mapper.TaskMapper;
import fun.pullock.incentive.core.dao.model.TaskDO;
import fun.pullock.incentive.core.model.dto.TaskCompleteLimitRuleDTO;
import fun.pullock.incentive.core.model.dto.TaskCompleteRuleDTO;
import fun.pullock.incentive.core.model.dto.TaskDTO;
import fun.pullock.starter.json.Json;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TaskManager {

    @Resource
    private TaskMapper taskMapper;

    public List<TaskDTO> queryByEvent(String eventCode) {
        List<TaskDO> tasks = taskMapper.selectByEvent(eventCode);

        return tasks.stream()
                .map(this::toTaskDTO)
                .sorted(Comparator.comparing(TaskDTO::getSequenceWeight)
                        .thenComparing(TaskDTO::getId)
                )
                .collect(Collectors.toList());
    }

    private TaskDTO toTaskDTO(TaskDO source) {
        if (source == null) {
            return null;
        }

        TaskDTO target = new TaskDTO();
        target.setId(source.getId());
        target.setCreateTime(source.getCreateTime());
        target.setUpdateTime(source.getUpdateTime());
        target.setStatus(source.getStatus());
        target.setName(source.getName());
        target.setDescription(source.getDescription());
        target.setType(source.getType());
        target.setSequenceWeight(source.getSequenceWeight());
        target.setCompleteType(source.getCompleteType());
        target.setAfterCompleteType(source.getAfterCompleteType());
        target.setCompleteEngageWay(source.getCompleteEngageWay());
        target.setEvents(source.getEvents());
        target.setCompleteRule(Json.toObject(source.getCompleteRule(), TaskCompleteRuleDTO.class));
        target.setCompleteLimitRule(Json.toObject(source.getCompleteLimitRule(), TaskCompleteLimitRuleDTO.class));
        target.setAfterCompleteRule(source.getAfterCompleteRule());
        target.setCompleteEngageRule(source.getCompleteEngageRule());
        target.setButtonConfig(source.getButtonConfig());
        target.setRedirectLinkConfig(source.getRedirectLinkConfig());
        return target;
    }
}
